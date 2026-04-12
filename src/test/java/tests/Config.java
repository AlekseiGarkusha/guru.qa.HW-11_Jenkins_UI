package tests;

import static java.lang.Boolean.parseBoolean;

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

  public static String getBrowser() {
    String browser = System.getProperty(
      "browser",
      "chrome");
    System.out.println("Browser is: " + browser);
    return browser;
  }

  public static String getBaseUrl() {
    String baseAddress = System.getProperty(
      "baseUrl",
      "https://demoqa.com");

    System.out.println("Base Address is: " + baseAddress);
    return baseAddress;
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

  public static String getBrowserVersion() {
    String browserVersion = System.getProperty("browserVersion", "128.0");
    System.out.println("Browser version is: " + browserVersion);
    return browserVersion;
  }

  public static Boolean getBrowserHeadless() {
    boolean browserHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
    System.out.println("Browser Headless is: " + browserHeadless);
    return browserHeadless;
  }

  public static String getBrowserSize() {
    String remoteBrowserSize = System.getProperty("remoteBrowserSize", "1920*1080");

    System.out.println("Base  size is: " + remoteBrowserSize);
    return remoteBrowserSize;
  }

}
