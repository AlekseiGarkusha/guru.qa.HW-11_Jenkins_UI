package setup;

/* Расширяющий класс для:
 * Вход в систему
 * Открытие страницы
 * Закрытие страницы
 */

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Options;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
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
    Configuration.baseUrl = Config.getBaseUrl();
    Configuration.browser = Config.getBrowser();
    Configuration.browserSize = Config.getBrowserSize();
    Configuration.browserVersion = Config.getBrowserVersion();
    Configuration.headless = Config.getBrowserHeadless();
    Configuration.pageLoadStrategy = "eager";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
      "enableVNC", true,
      "enableVideo", true
    ));
    ChromeOptions options = new ChromeOptions();
    Configuration.remote = Config.getRemoteUrl();
    Configuration.browserCapabilities = options;
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
