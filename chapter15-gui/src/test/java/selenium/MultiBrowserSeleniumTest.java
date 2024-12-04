package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MultiBrowserSeleniumTest {
    
    private WebDriver driver;
    
//    테스트의 파라미터로 주입하기 위한 소스 메서드
//    테스트에 하나씩 주입할 웹 드라이버 리스트를 반환한다
    public static Collection<WebDriver> getBrowserVersions() {
//        사용자의 환경에 따라 변경
//        System.setProperty("webdriver.gecko.driver", "D:\\tools\\webdriver\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:\\tools\\webdriver\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "D:\\tools\\webdriver\\msedgedriver.exe");

        return Arrays.asList(new WebDriver[]{new ChromeDriver(), new FirefoxDriver(), new EdgeDriver()});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testManningAccess(WebDriver driver){
        this.driver = driver;
        driver.get("https://www.manning.com");

        assertThat(driver.getTitle(), is("Manning"));
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleAccess(WebDriver driver){
        this.driver = driver;
        driver.get("https://www.google.com");

        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
