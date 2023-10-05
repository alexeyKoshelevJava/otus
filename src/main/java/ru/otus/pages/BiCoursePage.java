package ru.otus.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class BiCoursePage extends AbsBasePage<BiCoursePage> {
  public static final String TITLE = "Курс по BI-аналитике";

  public BiCoursePage(WebDriver webDriver) {
    super(webDriver, "/learning/247619/");
    PageFactory.initElements(webDriver, this);
  }
}

