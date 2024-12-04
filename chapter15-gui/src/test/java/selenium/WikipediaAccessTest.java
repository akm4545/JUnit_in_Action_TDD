package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaAccessTest {

//    RemoteWebDriver는 WebDriver 인터페이스를 구현한 클래스이며
//    기타 브라우저 driver 클래스가 상속할 수 있다
//    RemoteWebDriver가 있어야 웹 페이지로부터 특정 요소를 찾는 메서드를 호출할 수 있다
    private RemoteWebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    void testWikipediaAccess() {
        driver.get("https://en.wikipedia.org/");
        assertThat(driver.getTitle(), is("Wikipedia, the free encyclopedia"));

//      Talk 요소 찾기
        WebElement contents = driver.findElementByLinkText("Talk");
        assertTrue(contents.isDisplayed());

//        클릭 후 페이지 제목 검증
        contents.click();
        assertThat(driver.getTitle(), is("Talk:Main Page - Wikipedia"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
