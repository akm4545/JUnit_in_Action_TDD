package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class LoginPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public LoginPage loginWith(String username, String password){
        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.cssSelector("#login button")).click();

        return this;
    }

    public void thenLoginSuccessful() {
        assertTrue(webDriver.findElement(By.cssSelector("#flash.success")).isDisplayed());
        assertTrue(webDriver.findElement(By.cssSelector("[href=\"logout\"]")).isDisplayed());
    }

//    로그인 실패 시 아이디, 비밀번호 입력창이 그대로 남아있으므로
    public void thenLoginUnsuccessful(){
        assertTrue(webDriver.findElement(By.id("username")).isDisplayed());
        assertTrue(webDriver.findElement(By.id("password")).isDisplayed());
    }
}
