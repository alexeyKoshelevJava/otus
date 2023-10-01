
package ru.otus.factory;

import org.openqa.selenium.support.events.EventFiringWebDriver;
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
import ru.otus.listeners.ListenerThatHiglilightsElements;

import java.util.concurrent.TimeUnit;

public class FactoryWebDriver {
  private final String browserName = System.getProperty("browser", "chrome");

  private WebDriver create() throws BrowserNotSupportedException {
    switch (browserName) {
      case "chrome" -> {
        BrowserSettings chromeSettings = new ChromeSettings();
        return new ChromeDriver((ChromeOptions) chromeSettings
            .configureDriver());
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

  public EventFiringWebDriver getDriver() {
    WebDriver driver = create();
    EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
    eventFiringWebDriver.register(new ListenerThatHiglilightsElements());
    return eventFiringWebDriver;
  }
}

