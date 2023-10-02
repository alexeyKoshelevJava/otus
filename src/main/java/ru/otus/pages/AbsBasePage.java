package ru.otus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.otus.pageobject.AbsPageObject;

import java.time.Duration;

public abstract class AbsBasePage<T> extends AbsPageObject {
  private static final String BASE_URL = System.getProperty("base.url");
  private String path = "";

  public AbsBasePage(WebDriver webDriver, String path) {
    super(webDriver);
    this.path = path;
  }

  public T open() {
    String url = BASE_URL;
    if (!BASE_URL.endsWith("/")) {
      url += "/";
    }
    webDriver.get(url + path);
    return (T) this;
  }
  public void waitUntilElementBeVisible(WebElement element) {
    new WebDriverWait(webDriver, Duration.ofSeconds(40)).until(
        ExpectedConditions.visibilityOf(element));
  }
}
