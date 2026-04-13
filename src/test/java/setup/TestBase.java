package setup;

/* Расширяющий класс для:
 * Вход в систему
 * Открытие страницы
 * Закрытие страницы
 */

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.Config;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @BeforeAll
  static void beforeAll() {
    String browser = Config.getBrowser().toLowerCase();

    Configuration.baseUrl = Config.getBaseUrl();
    Configuration.browser = browser;
    Configuration.browserSize = Config.getBrowserSize();
    Configuration.pageLoadStrategy = "eager";
    Configuration.remote = Config.getRemoteUrl();

    Map<String, Object> selenoidOptions = Map.of(
      "enableVNC", true,
      "enableVideo", true
    );

    switch (browser) {

      case "chrome" -> {
        ChromeOptions options = new ChromeOptions();

        options.setCapability("browserVersion", Config.getBrowserVersion());

        options.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = options;
      }

      case "firefox" -> {
        FirefoxOptions options = new FirefoxOptions();

        options.setCapability("browserVersion", Config.getBrowserVersion());

        options.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = options;
      }

      case "opera" -> {
        ChromeOptions options = new ChromeOptions();

        options.setBinary("/usr/bin/opera"); // важно для Selenoid image

        options.setCapability("browserVersion", Config.getBrowserVersion());

        options.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = options;
      }

      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
    Attach.attachAsText("Some file", "Some content");
    closeWebDriver();
  }
}
