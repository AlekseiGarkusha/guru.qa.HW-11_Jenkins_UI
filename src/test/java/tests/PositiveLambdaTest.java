  /**
   * Позитивный тест:
   * - переход к странице формы
   * - проверка на загрузку мормы (нахождение form wrapper)
   * - заполненме всех полей
   * - проверка на открытие результирующей формы - метод поиска title формы
   * - сравнение результирующий полей с вводимыми
   */

  package tests;

  import data.TestData;
  import io.qameta.allure.*;
  import org.junit.jupiter.api.*;
  import pages.RegistrationPage;
  import pages.components.ComparisonFieldsComponent;
  import setup.TestBase;

  import static com.codeborne.selenide.Selenide.open;
  import static io.qameta.allure.Allure.step;

  public class PositiveLambdaTest extends TestBase {
    static RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

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

      step ("Open foarm", () -> {
          registrationPage.openPage();
      });

      step ("fillFields", () -> {
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
      });

      step ("Comparison fields", () -> {
        comparisonFields.comparisonFieldsPositiveTest(testData);
      });
    }
  }
