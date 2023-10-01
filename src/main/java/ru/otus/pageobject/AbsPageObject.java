package ru.otus.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class AbsPageObject {
  protected WebDriver webDriver;
  protected Actions actions;

  public AbsPageObject(WebDriver webDriver) {
    this.webDriver = webDriver;
    this.actions = new Actions(webDriver);
  }
}
