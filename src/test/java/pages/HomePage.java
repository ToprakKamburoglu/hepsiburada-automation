package pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.LocatorReader;
import utils.WaitUtils;

import java.util.List;

public class HomePage extends BasePage {

    private static final String PAGE = "HomePage";
    private static final String COOKIE_SHADOW_HOST = "efilli-layout-dynamic";
    private static final String COOKIE_ACCEPT_TEXT = "Kabul Et";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        try {
            WebElement host = WaitUtils.presence(driver, By.cssSelector(COOKIE_SHADOW_HOST), Constants.SHORT_TIMEOUT + 3);
            org.openqa.selenium.SearchContext shadowRoot = host.getShadowRoot();
            List<WebElement> divs = shadowRoot.findElements(By.cssSelector("div"));
            for (WebElement div : divs) {
                if (div.getText().trim().equals(COOKIE_ACCEPT_TEXT)) {
                    jsClick(div);
                    return;
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void clickLogin() {
        WebElement accountIcon = driver.findElement(By.cssSelector("a[title='Hesabım'], span[title='Giriş Yap']"));
        new Actions(driver).moveToElement(accountIcon).perform();
        click(LocatorReader.getLocator(PAGE, "loginButton"));
    }

    public boolean isUserLoggedIn() {
        try {
            // Login sonrası yönlendirme/SMS gecikmesi olabileceği için uzun timeout kullanılır
            WebElement indicator = WaitUtils.visible(driver, LocatorReader.getLocator(PAGE, "loggedInUserIndicator"), Constants.LONG_TIMEOUT);
            return indicator.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void search(String keyword) {
        By searchLocator = LocatorReader.getLocator(PAGE, "searchInput");

        jsClick(driver.findElement(searchLocator));
        waitClickable(searchLocator);

        clickSafely(searchLocator);

        for (char c : keyword.toCharArray()) {
            sendKeySafely(searchLocator, String.valueOf(c));
            WaitUtils.pause(Constants.TYPE_CHAR_DELAY_MS);
        }

        sendKeySafely(searchLocator, org.openqa.selenium.Keys.ENTER.toString());
    }

    private void clickSafely(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            driver.findElement(locator).click();
        }
    }

    private void sendKeySafely(By locator, String key) {
        try {
            driver.findElement(locator).sendKeys(key);
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            driver.findElement(locator).sendKeys(key);
        }
    }

    public String getSearchResultTitle() {
        return waitVisible(LocatorReader.getLocator(PAGE, "searchResultTitle")).getText();
    }

    public void clickCartIcon() {
        click(LocatorReader.getLocator(PAGE, "cartIcon"));
        WaitUtils.until(driver, Constants.DEFAULT_TIMEOUT, d -> d.getCurrentUrl().contains("sepetim"));
    }

    public String getCartItemCount() {
        By locator = LocatorReader.getLocator(PAGE, "cartItemCount");
        try {
            WaitUtils.until(driver, Constants.DEFAULT_TIMEOUT, d -> {
                try {
                    return !d.findElement(locator).getText().trim().isEmpty();
                } catch (Exception e) {
                    return false;
                }
            });
        } catch (Exception ignored) {
        }
        return driver.findElement(locator).getText().trim();
    }
}