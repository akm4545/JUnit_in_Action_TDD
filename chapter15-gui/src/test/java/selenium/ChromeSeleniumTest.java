package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChromeSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void testChromeManning() {
        driver.get("https://www.manning.com/");
//        페이지 제목이 Manning인지 검증
//        Hamcrest 매처인 is를 사용하여 가독성을 높임
        assertThat(driver.getTitle(), is("Manning"));
    }

    @Test
    void testChromeGoogle() {
        driver.get("https://www.google.com");
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
//        열려있는 브라우저 창을 모두 닫는다
//        드라이버 인스턴스는 가비지 컬렉션이 가능한 상태가 되므로 자동으로 메모리에서 제거
        driver.quit();
    }
}
