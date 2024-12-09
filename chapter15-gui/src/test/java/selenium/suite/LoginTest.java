package selenium.suite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.pages.HomePage;

import java.util.Arrays;
import java.util.Collection;

public class LoginTest {

    private HomePage homePage;
    private WebDriver webDriver;

    public static Collection<WebDriver> getBrowserVersions() {
        // 사용자의 환경에 따라 변경
//        System.setProperty("webdriver.gecko.driver", "D:\\tools\\webdriver\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:\\tools\\webdriver\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "D:\\tools\\webdriver\\msedgedriver.exe");

        return Arrays.asList(new WebDriver[]{new FirefoxDriver(), new ChromeDriver(), new EdgeDriver()});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void loginWithValidCredentials(WebDriver webDriver){
        this.webDriver = webDriver;

        homePage = new HomePage(webDriver);
        homePage
                .openFormAuthentication()
                .loginWith("tomsmith", "SuperSecretPassword!")
                .thenLoginSuccessful();
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void loginWithInvalidCredentials(WebDriver webDriver){
        this.webDriver = webDriver;

        homePage = new HomePage(webDriver);
        homePage
                .openFormAuthentication()
                .loginWith("tomsmith", "SuperSecretPassword")
                .thenLoginUnsuccessful();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}
