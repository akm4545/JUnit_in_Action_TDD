package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public LoginPage openFormAuthentication() {
        webDriver.get("https://the-internet.herokuapp.com/");
        webDriver.findElement(By.cssSelector("[href=\"/login\"]")).click();

        return new LoginPage(webDriver);
    }
}
