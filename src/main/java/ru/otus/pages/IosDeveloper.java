package ru.otus.pages;

import org.openqa.selenium.WebDriver;

public class IosDeveloper extends AbsBasePage<IosDeveloper> {
  public static final String TITLE = "Специализация iOS Developer";

  public IosDeveloper(WebDriver webDriver) {
    super(webDriver, "/lessons/ios-specialization/");
  }
}
