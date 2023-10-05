
package ru.otus.factory;

import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.exceptions.BrowserNotSupportedException;
import ru.otus.factory.impl.BrowserSettings;
import ru.otus.factory.impl.ChromeSettings;
import ru.otus.factory.impl.FireFoxSetting;
import ru.otus.factory.impl.OperaSetting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.otus.listeners.ListenerThatHighlightsElements;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class FactoryWebDriver {
  private final String browserName = System.getProperty("browser", "chrome");

  private WebDriver create() throws BrowserNotSupportedException {
    switch (browserName) {
      case "chrome" -> {
        BrowserSettings chromeSettings = new ChromeSettings();
        WebDriver currentDriver = (WebDriver) new ChromeDriver((ChromeOptions) chromeSettings
            .configureDriver());
        currentDriver
            .manage()
            .timeouts()
            .implicitlyWait(Duration.ofSeconds(60)
                .getSeconds(), TimeUnit.SECONDS);
        currentDriver.manage().deleteAllCookies();
        return currentDriver;
      }
      case "fireFox" -> {
        BrowserSettings fireFoxSetting = new FireFoxSetting();
        return new FirefoxDriver((FirefoxOptions) fireFoxSetting.configureDriver());
      }
      case "opera" -> {
        BrowserSettings operaSetting = new OperaSetting();
        return new FirefoxDriver((FirefoxOptions) operaSetting.configureDriver());

      }
      default -> throw new BrowserNotSupportedException(browserName);
    }
  }

  public WebDriver getDriver() {
    WebDriver driver = create();
    driver = new EventFiringDecorator<>(new ListenerThatHighlightsElements(driver))
        .decorate(driver);
    return driver;
  }
}