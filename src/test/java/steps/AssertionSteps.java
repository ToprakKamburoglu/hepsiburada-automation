package steps;

import com.thoughtworks.gauge.Step;
import constants.Constants;
import driver.Driver;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;
import utils.ConfigReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AssertionSteps {

    private HomePage homePage() { return new HomePage(Driver.driver); }
    private LoginPage loginPage() { return new LoginPage(Driver.driver); }
    private SearchResultsPage searchResultsPage() { return new SearchResultsPage(Driver.driver); }
    private CartPage cartPage() { return new CartPage(Driver.driver); }

    @Step("Tarayıcıyı aç ve Hepsiburada'ya git")
    public void openHepsiburada() {
        Driver.driver.get(Constants.HOME_URL);
    }

    @Step("Çerez banner'ını kapat")
    public void acceptCookies() {
        homePage().acceptCookies();
    }

    @Step("Giriş yap butonuna tıkla")
    public void clickLogin() {
        homePage().clickLogin();
        homePage().acceptCookies();
    }

    @Step("Email ve şifre ile giriş yap")
    public void loginWithCredentials() {
        loginPage().login(ConfigReader.get("hepsiburada.email"), ConfigReader.get("hepsiburada.password"));
    }

    @Step("Arama kutusuna <key> yaz ve ara")
    public void searchProduct(String key) {
        homePage().search(key);
    }

    @Step("İkinci satırdaki ilk ürünü sepete ekle")
    public void addSecondRowFirstProductToCart() {
        searchResultsPage().addProductFromRowToCart(Constants.SECOND_ROW_INDEX, Constants.FIRST_COLUMN_INDEX);
    }

    @Step("Sepeti aç")
    public void openCart() {
        homePage().clickCartIcon();
        homePage().acceptCookies();
    }

    @Step("Sepetten ürünü sil")
    public void removeFromCart() {
        cartPage().removeProductFromCart();
    }

    @Step("Assertion|Verify User Logged In Successfully")
    public void verifyUserLoggedInSuccessfully() {
        assertThat(homePage().isUserLoggedIn()).as("Login Failed").isTrue();
    }

    @Step("Assertion|Verify The Search Response for <key>")
    public void verifySearchResultsContent(String key) {
        String title = homePage().getSearchResultTitle().toLowerCase();
        assertThat(title).as("Search Result isn't matched").contains(key.toLowerCase());
    }

    @Step("Assertion|Verify Product Added To Cart")
    public void verifyProductAddedToCart() {
        assertThat(homePage().getCartItemCount())
                .as("Product wasn't added to the cart")
                .isEqualTo(Constants.EXPECTED_CART_COUNT);
    }

    @Step("Assertion|Verify Product Visible In Cart")
    public void verifyProductVisibleInCart() {
        assertThat(cartPage().isProductInCart()).as("Product can't be found in the cart").isTrue();
    }

    @Step("Assertion|Verify Cart Is Empty")
    public void verifyCartIsEmpty() {
        assertThat(cartPage().isCartEmpty()).as("Cart is not empty").isTrue();
    }
}