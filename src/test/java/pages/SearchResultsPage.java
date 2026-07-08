package pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LocatorReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchResultsPage extends BasePage {

    private static final String PAGE = "SearchResultsPage";
    private static final String PRODUCT_TITLE_SELECTOR = "a.title-module_titleText__8FlNQ";

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void addProductFromRowToCart(int rowIndex, int columnIndex) {
        By productCardLocator = LocatorReader.getLocator(PAGE, "productCards");
        scrollBy(Constants.SEARCH_RESULTS_SCROLL_PIXELS);

        List<WebElement> visibleProducts;
        try {
            visibleProducts = waitAllVisible(productCardLocator);
        } catch (Exception e) {
            visibleProducts = driver.findElements(productCardLocator);
        }
        visibleProducts.removeIf(p -> !p.isDisplayed());

        if (visibleProducts.isEmpty()) {
            throw new IllegalStateException("Hiç görünür ürün bulunamadı.");
        }

        Map<Integer, List<WebElement>> rows = groupByRow(visibleProducts);
        if (rows.size() <= rowIndex) {
            throw new IllegalStateException("İstenen satır (" + rowIndex + ") bulunamadı.");
        }

        List<List<WebElement>> rowList = new ArrayList<>(rows.values());
        List<WebElement> targetRow = rowList.get(rowIndex);
        targetRow.sort(Comparator.comparingInt(e -> e.getLocation().getX()));

        if (targetRow.size() <= columnIndex) {
            throw new IllegalStateException("Satırda istenen kolon (" + columnIndex + ") bulunamadı.");
        }

        WebElement targetProduct = targetRow.get(columnIndex);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", targetProduct);

        logProductTitle(targetProduct);
        addToCart(targetProduct);
    }

    private Map<Integer, List<WebElement>> groupByRow(List<WebElement> products) {
        Map<Integer, List<WebElement>> rows = new TreeMap<>();
        for (WebElement product : products) {
            Point point = product.getLocation();
            int rowKey = (point.getY() / Constants.ROW_TOLERANCE_PX) * Constants.ROW_TOLERANCE_PX;
            rows.computeIfAbsent(rowKey, k -> new ArrayList<>()).add(product);
        }
        return rows;
    }

    private void logProductTitle(WebElement product) {
        try {
            WebElement titleLink = product.findElement(By.cssSelector(PRODUCT_TITLE_SELECTOR));
            System.out.println("Seçilen ürün: " + titleLink.getAttribute("title"));
        } catch (Exception e) {
            System.out.println("Ürün adı okunamadı.");
        }
    }

    private void addToCart(WebElement product) {
        By addToCartLocator = LocatorReader.getLocator(PAGE, "addToCartButtons");
        WebElement addButton = product.findElement(addToCartLocator);
        jsClick(addButton);

        try {
            By modalAddToCartLocator = LocatorReader.getLocator(PAGE, "modalAddToCartButton");
            WebElement modalButton = waitVisible(modalAddToCartLocator);
            jsClick(modalButton);
        } catch (Exception e) {
            System.out.println("Varyasyon modalı çıkmadı, direkt sepete eklendi.");
        }
    }
}