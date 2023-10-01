package ru.otus.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage<MainPage> {
  public MainPage(WebDriver webDriver) {
    super(webDriver, "/");
  }
}
