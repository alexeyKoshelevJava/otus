package ru.otus.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.otus.pageobject.AbsPageObject;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class AbsBasePage<T> extends AbsPageObject {
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

  public void waitUntilElementsPresents(List<WebElement> elements) {
    new WebDriverWait(webDriver, Duration.ofSeconds(40))
        .until(ExpectedConditions.visibilityOfAllElements(
            elements));
  }

  public void waitUntilElementIsDisplayed(WebElement element) {
    Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    wait.until(d -> element.isDisplayed());
  }


  public void checkTitle(String title) {
    Assertions.assertTrue(webDriver.getTitle().contains(title));
  }

  public void checkTitleNotEmpty() {
    Assertions.assertFalse(webDriver.getTitle().isEmpty());
  }

  public void waitUntilElementWillBe(String xPath) {

    // Waiting 30 seconds for an element to be present on the page, checking
    // for its presence once every 5 seconds.
    Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
        .withTimeout(Duration.ofSeconds(30L))
        .pollingEvery(Duration.ofSeconds(5L))
        .ignoring(NoSuchElementException.class);

    wait.until(driver -> driver.findElement(By.xpath(xPath)));
  }
}
