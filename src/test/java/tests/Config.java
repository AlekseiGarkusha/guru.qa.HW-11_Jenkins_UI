package tests;

public class Config {

  public static String propertyTest() {
    String environment = System.getProperty("environment");

    System.out.println("Test environment is: " + environment);
    return environment;
  }

  public static String propertyName() {
    String name = System.getProperty("name");
    System.out.println("My name is: " + name);
    return name;
  }

  public static String getRemoteUrl() {
    String login = "user1";
    String password = "1234";

    if (login == null || password == null) {
      throw new RuntimeException("Login/password not provided");
    }

      return "https://" + login + ":" + password
        + "@selenoid.autotests.cloud/wd/hub";
    }


}
