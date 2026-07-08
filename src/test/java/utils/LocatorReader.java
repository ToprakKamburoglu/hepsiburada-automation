package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;

public class LocatorReader {

    private static final String LOCATORS_PATH = "src/test/resources/locators/AllLocators.json";
    private static JsonObject root;

    static {
        try {
            Gson gson = new Gson();
            root = gson.fromJson(new FileReader(LOCATORS_PATH), JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException("AllLocators.json okunamadı", e);
        }
    }

    public static By getLocator(String pageName, String elementName) {
        JsonObject pageNode = root.getAsJsonObject(pageName);
        if (pageNode == null) {
            throw new RuntimeException("Sayfa bulunamadı: " + pageName);
        }
        JsonObject element = pageNode.getAsJsonObject(elementName);
        if (element == null) {
            throw new RuntimeException(pageName + " içinde element bulunamadı: " + elementName);
        }

        String type = element.get("type").getAsString();
        String value = element.get("value").getAsString();

        switch (type.toLowerCase()) {
            case "id": return By.id(value);
            case "css": return By.cssSelector(value);
            case "xpath": return By.xpath(value);
            case "name": return By.name(value);
            default: throw new RuntimeException("Bilinmeyen locator tipi: " + type);
        }
    }
}