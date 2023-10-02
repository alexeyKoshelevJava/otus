package ru.otus.listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class ListenerThatHighlightsElements implements WebDriverListener {
  private final WebDriver driver;
  private final JavascriptExecutor executor;

  public ListenerThatHighlightsElements(WebDriver driver) {
    this.driver = driver;
    this.executor = (JavascriptExecutor) driver;
  }

  /**
   * Выделение элемента цветом перед нажатием на него
   *
   * @param element web-элемент
   */
  @Override
  public void beforeClick(WebElement element) {
    flash(element, driver);
    executor.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  /**
   * Снятие выделения с элемента после нажатия на него
   * @param element web-элемент
   */

  @Override
  public void afterClick(WebElement element) {
    changeColorBack(element, executor);
  }

  public void flash(WebElement element, WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    changeColor(element, js);
  }

  public void changeColor(WebElement element, JavascriptExecutor js) {
    js.executeScript("arguments[0].style.backgroundColor = 'grey'", element);
    js.executeScript("arguments[0].style.border='4px solid red'", element);
  }

  public void changeColorBack(WebElement element, JavascriptExecutor js) {
    js.executeScript("arguments[0].style.backgroundColor = ''", element);
    js.executeScript("arguments[0].style.border=''", element);
  }
}

