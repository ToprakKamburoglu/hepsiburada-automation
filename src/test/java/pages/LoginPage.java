package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LocatorReader;

public class LoginPage extends BasePage {

    private static final String PAGE = "LoginPage";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        WebElement emailInput = waitVisible(LocatorReader.getLocator(PAGE, "emailInput"));
        emailInput.click();
        emailInput.clear();
        typeSlowly(emailInput, email);

        WebElement passwordInput = driver.findElement(LocatorReader.getLocator(PAGE, "passwordInput"));
        passwordInput.click();
        passwordInput.clear();
        typeSlowly(passwordInput, password);

        waitClickable(LocatorReader.getLocator(PAGE, "submitLoginButton")).click();
    }
}