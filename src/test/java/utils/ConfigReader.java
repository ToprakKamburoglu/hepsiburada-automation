package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("config.properties dosyası okunamadı! " +
                    "config.properties.example dosyasını kopyalayıp config.properties olarak kaydettiğinden emin ol.", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            value = value.trim();
        }
        return value;
    }

}