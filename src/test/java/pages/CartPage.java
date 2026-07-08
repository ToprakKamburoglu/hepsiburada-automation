package pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LocatorReader;
import utils.WaitUtils;

import java.util.List;

public class CartPage extends BasePage {

    private static final String PAGE = "CartPage";
    private static final By CONFIRM_DELETE_BUTTON =
            By.cssSelector("button.favoritesButton_3avg1[kind='secondary']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart() {
        try {
            WebElement product = waitVisible(LocatorReader.getLocator(PAGE, "cartProductName"));
            return product.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void removeProductFromCart() {
        click(LocatorReader.getLocator(PAGE, "removeFromCartButton"));
        WaitUtils.pause(1000);

        try {
            WebElement confirmDeleteBtn = waitVisible(CONFIRM_DELETE_BUTTON);
            jsClick(confirmDeleteBtn);

            By productLocator = LocatorReader.getLocator(PAGE, "cartProductName");
            WaitUtils.until(driver, Constants.SHORT_TIMEOUT,
                    d -> d.findElements(productLocator).isEmpty());

            WaitUtils.pause(1000);
        } catch (Exception ignored) {
        }
    }

    public boolean isCartEmpty() {
        List<WebElement> products = driver.findElements(LocatorReader.getLocator(PAGE, "cartProductName"));
        return products.isEmpty();
    }
}