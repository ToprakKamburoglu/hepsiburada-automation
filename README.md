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
1. Repoyu klonlayın: git clone https://github.com/ToprakKamburoglu/hepsiburada-automation.git
2. `src/test/resources/config.properties.example` dosyasını kopyalayıp `config.properties` olarak kaydedin, kendi Hepsiburada hesap bilgilerinizi girin.
3. Gauge CLI'ı kurun: https://docs.gauge.org/getting_started/installing-gauge.html
4. Java plugin'i kurun: `gauge install java`
5. **İlk çalıştırma (2FA doğrulaması için):** `steps/AssertionSteps.java` içindeki `loginWithCredentials()` metodunda `utils.WaitUtils.pause(60000);` satırı eklenmelidir. Bu satır, login sonrası tarayıcıyı 60 saniye bekletir; bu sürede mailinize/telefonunuza gelen doğrulama kodunu tarayıcıya manuel girin. Doğrulama tamamlanınca tarayıcı profili güvenilir olarak işaretlenir ve bu satırı silebilir/yorum satırı yapabilirsiniz — sonraki çalıştırmalarda 2FA tekrar istenmez.

## Çalıştırma
`mvn clean test` veya spec dosyası içerisinden projeyi çalıştırabilirsiniz.
