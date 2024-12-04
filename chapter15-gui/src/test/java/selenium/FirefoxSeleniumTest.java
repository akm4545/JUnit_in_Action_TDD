package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FirefoxSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    void testFirefoxManning() {
        driver.get("https://www.manning.com/");
        assertThat(driver.getTitle(), is("Manning"));
    }

    @Test
    void testFirefoxGoogle() {
        driver.get("https://www.google.com");
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
