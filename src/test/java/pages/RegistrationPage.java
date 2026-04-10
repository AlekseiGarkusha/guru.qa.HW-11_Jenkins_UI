package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalenderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

  private final CalenderComponent calendar = new CalenderComponent();

  private final SelenideElement
    userFirstNameInput = $("#firstName"),
    userLastNameInput = $("#lastName"),
    userEmailInput = $("#userEmail"),
    userGender = $("#genterWrapper"),

  userNumberInput = $("#userNumber"),
    userSubjects = $("#subjectsInput"),

  hobbySports = $("#hobbies-checkbox-1"),
    hobbyReading = $("#hobbies-checkbox-2"),
    hobbyMusic = $("#hobbies-checkbox-3"),

  userState = $("#react-select-3-input"),
    userCity = $("#react-select-4-input"),
    userUploadPicture = $("#uploadPicture"),
    userCurrentAddress = $("#currentAddress"),

  btnSubmit = $("#submit");

  @Step("Открытие формы с проверкой на доступность")
  public RegistrationPage openPage() {
    open("/automation-practice-form");
    $("#firstName").shouldBe(visible);

    return this;
  }

  @Step("Заполнение полей: имя и фамилии")
  public RegistrationPage typeUserName(String name, String userLastName) {
    userFirstNameInput.setValue(name);
    userLastNameInput.setValue(userLastName);

    return this;
  }

  @Step("Заполнение поля Email")
  public RegistrationPage typeUserEmail(String value) {
    userEmailInput.setValue(value);

    return this;
  }
  @Step("Заполнение поля выбора гендера")
  public RegistrationPage chooseGender(String value) {
    userGender.$(byText(value)).click();

    return this;
  }
  @Step("Заполнение поля телефон")
  public RegistrationPage typeUserNumber(String value) {
    userNumberInput.setValue(value);

    return this;
  }

  @Step("Заполнение поля Дата рождения")
  public RegistrationPage setDateOfBirth(int day, String month, int year) {
    calendar.setDate(day, month, year);

    return this;
  }

  @Step("Заполнение поля выбор предметов")
  public RegistrationPage setUserSubjets(String value) {
    userSubjects.setValue(value).pressEnter();

    return this;
  }

  @Step("Заполнение поля выбор хобби")
  public RegistrationPage setUserHobbies(String value) {
    switch (value) {
      case "Sports" -> $(hobbySports).click();
      case "Reading" -> $(hobbyReading).click();
      case "Music" -> $(hobbyMusic).click();
    }

    return this;
  }

  @Step("Загрузка картинки")
  public RegistrationPage uploadPicture(String value) {
    userUploadPicture.uploadFromClasspath(value);

    return this;
  }

  @Step("Заполнение адреса")
  public RegistrationPage typeUserCurrentAddress(String value) {
    userCurrentAddress.sendKeys(value);

    return this;
  }

  @Step("Выбор Штата и Города")
  public RegistrationPage setStateAndCity(String state, String city) {
    userState.setValue(state).pressEnter();
    userCity.setValue(city).pressEnter();

    return this;
  }

  @Step("Подтвержение формы")
  public RegistrationPage clickBtnSubmit() {
    btnSubmit.scrollTo().click();
    $(".modal-content").shouldBe(visible, Duration.ofSeconds(10));

    return this;
  }

}
