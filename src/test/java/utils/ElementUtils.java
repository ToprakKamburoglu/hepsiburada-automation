package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils {

    public static void jsClick(WebDriver driver, WebElement element) {
        scrollIntoView(driver, element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public static void scrollBy(WebDriver driver, int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy({top:" + pixels + ", behavior:'smooth'});");
    }

    public static void typeSlowly(WebElement element, String text, int perCharDelayMs) {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            WaitUtils.pause(perCharDelayMs);
        }
    }
}