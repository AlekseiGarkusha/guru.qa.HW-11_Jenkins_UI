package tests;

import org.junit.jupiter.api.Test;

public class PropertyTests {

  @Test
  void propertyTest() {
    String environment = System.getProperty("environment");

    System.out.println("Test environment is: " + environment);
  }

  @Test
  void propertyTestName() {
    String name = System.getProperty("name");

    System.out.println("My name is: " + name);
  }

  @Test
  void propertyBrowserTest() {
    String browser = System.getProperty("browser", "chrome");

    System.out.println("Browser is: " + browser);
  }

}
