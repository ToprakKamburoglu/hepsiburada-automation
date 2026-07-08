# Hepsiburada Otomasyon Projesi

Gauge + Selenium + Java ile yazılmış, Hepsiburada.com üzerinde login, arama, sepete ekleme ve sepetten silme senaryolarını test eden UI otomasyon projesi.

## Kullanılan Teknolojiler
- Java 17
- Selenium WebDriver
- Gauge Framework (BDD)
- Maven

## Proje Yapısı
- `specs/` – Gauge senaryoları (.spec) ve concept dosyaları (.cpt)
- `src/test/java/pages/` – Page Object sınıfları
- `src/test/java/steps/` – Step implementasyonları
- `src/test/java/driver/` – WebDriver yönetimi
- `src/test/java/utils/` – Yardımcı sınıflar (wait, element, locator, config)
- `src/test/java/constants/` – Sabitler
- `src/test/resources/locators/` – Locator tanımları (JSON)

## Kurulum

1. Repoyu klonlayın.
2. `src/test/resources/config.properties.example` dosyasını kopyalayıp `config.properties` olarak kaydedin, kendi Hepsiburada hesap bilgilerinizi girin.
3. Gauge CLI'ı kurun: https://docs.gauge.org/getting_started/installing-gauge.html
4. Java plugin'i kurun: `gauge install java`

## Çalıştırma

- mvn clean test veya spec içerisinden projeyi runlayın.