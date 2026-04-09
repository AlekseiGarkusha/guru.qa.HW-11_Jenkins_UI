  /**
   * Позитивный тест:
   * - переход к странице формы
   * - проверка на загрузку мормы (нахождение form wrapper)
   * - заполненме всех полей
   * - проверка на открытие результирующей формы - метод поиска title формы
   * - сравнение результирующий полей с вводимыми
   */

  package tests;

  import com.codeborne.selenide.Configuration;
  import com.codeborne.selenide.logevents.SelenideLogger;
  import data.TestData;
  import io.qameta.allure.*;
  import io.qameta.allure.selenide.AllureSelenide;
  import org.junit.jupiter.api.*;
  import pages.RegistrationPage;
  import pages.components.ComparisonFieldsComponent;

  import static com.codeborne.selenide.Selenide.open;

  public class PositivePageObjectTest {
    static RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @BeforeAll
    public static void openPage() {
      Configuration.browser = "chrome";
      Configuration.baseUrl = "https://demoqa.com";
      Configuration.browserSize = "1920x1080";
      Configuration.browserVersion = "128.0";
      Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

      SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @Epic("test")
    @Feature("Форма студента")
    @Story("Заполнение всех полей")
    @Owner("AlexeyGarkusha")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Practice-form", url = "/automation-practice-form")
    @DisplayName("Заполнение всех полей формы студента, и проверка на соответствие")
    public void practiceFormTest() {
      ComparisonFieldsComponent comparisonFields = new ComparisonFieldsComponent();

      open("/automation-practice-form");

      registrationPage
        .typeUserName(testData.userFakerFirstName,testData.userFakerLastName)
        .typeUserEmail(testData.userFakerEmail)
        .chooseGender(testData.userFakerGender)
        .typeUserNumber(testData.userFakerNumber)
        .setDateOfBirth(testData.userFakerDay, testData.userFakerMonth,testData.userFakerYear)
        .setUserSubjets(testData.userFakerSubject)
        .setUserHobbies(testData.userFakerHobby)
        .uploadPicture(testData.userPicture)
        .typeUserCurrentAddress(testData.userCurrentAddress)
        .setStateAndCity(testData.userFakerState, testData.userFakerCity)
        .clickBtnSubmit();

      comparisonFields.comparisonFieldsPositiveTest(testData);
    }
  }
