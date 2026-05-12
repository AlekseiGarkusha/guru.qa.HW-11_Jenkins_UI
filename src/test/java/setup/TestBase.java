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
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @BeforeAll
  static void beforeAll() {
    ChromeOptions options = new ChromeOptions();
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserSize = System.getProperty("remoteBrowserSize", "1920x1080");
    Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
    Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
      "enableVNC", true,
      "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
    Configuration.remote = System.getProperty("remote");
  }


  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
    //        Attach.attachAsText("Some file", "Some content");
    closeWebDriver();
  }
}
