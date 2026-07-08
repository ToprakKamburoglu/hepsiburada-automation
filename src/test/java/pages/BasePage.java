package pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementUtils;
import utils.WaitUtils;

import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final JavascriptExecutor js;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    protected WebElement waitVisible(By locator) {
        return WaitUtils.visible(driver, locator, Constants.DEFAULT_TIMEOUT);
    }

    protected WebElement waitClickable(By locator) {
        return WaitUtils.clickable(driver, locator, Constants.DEFAULT_TIMEOUT);
    }

    protected List<WebElement> waitAllVisible(By locator) {
        return WaitUtils.allVisible(driver, locator, Constants.DEFAULT_TIMEOUT);
    }

    protected void click(By locator) {
        ElementUtils.jsClick(driver, waitClickable(locator));
    }

    protected void jsClick(WebElement element) {
        ElementUtils.jsClick(driver, element);
    }

    protected void typeSlowly(WebElement element, String text) {
        ElementUtils.typeSlowly(element, text, Constants.TYPE_CHAR_DELAY_MS);
    }

    protected void scrollBy(int pixels) {
        ElementUtils.scrollBy(driver, pixels);
    }
}