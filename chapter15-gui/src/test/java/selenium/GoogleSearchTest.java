package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {

    private RemoteWebDriver driver;

    public static Collection<RemoteWebDriver> getBrowserVersions() {
        // 사용자의 환경에 따라 변경
//        System.setProperty("webdriver.gecko.driver", "D:\\tools\\webdriver\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:\\tools\\webdriver\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "D:\\tools\\webdriver\\msedgedriver.exe");
        return Arrays.asList(new RemoteWebDriver[]{new ChromeDriver(), new FirefoxDriver(), new EdgeDriver()});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleSearch(RemoteWebDriver driver){
        this.driver = driver;
        driver.get("http://www.google.com");

//        이름이 q인 HTML 요소를 찾는다 (input)
        WebElement element = driver.findElement(By.name("q"));
//        input에 텍스트 입력
        element.sendKeys("en.wikipedia.org");
//        Enter키 입력
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

//        구글 검색 결과가 표시될 때까지 대기
//        대기 시간은 10초를 넘지 않아야 한다
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));

//        XPath를 사용하여 구글 검색에서 반환한 모든 요소를 가져온다
//        XPath는 XML에서 요소를 찾기 위한 쿼리 언어다
//        Selenium에서 XPath를 사용한 작업에 대한 자세한 내용은 www.guru99.com/xpath-selenium.html을 참고
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//a/h3"));

//        첫 번째 HTML 요소를 클릭하고 접속
        findElements.get(0).click();

        assertEquals("https://en.wikipedia.org/wiki/Main_Page", driver.getCurrentUrl());
        assertThat(driver.getTitle(), is("Wikipedia, the free encyclopedia"));

        WebElement contents = driver.findElementByLinkText("Talk");
        assertTrue(contents.isDisplayed());
        contents.click();

        assertThat(driver.getTitle(), is("Talk:Main Page - Wikipedia"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
