package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitUtils {

    public static WebElement visible(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement presence(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> allVisible(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static <T> T until(WebDriver driver, int seconds, Function<WebDriver, T> condition) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(condition);
    }

    public static void urlContains(WebDriver driver, String fragment, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.urlContains(fragment));
    }

    // Yalnızca kasıtlı efektler için (yavaş yazma tempo'su)
    public static void pause(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
}