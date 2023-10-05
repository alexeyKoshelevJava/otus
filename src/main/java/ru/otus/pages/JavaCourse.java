package ru.otus.pages;

import org.openqa.selenium.WebDriver;

public class JavaCourse extends AbsBasePage <JavaCourse> {
  public static final String TITLE = "Cпециализация Java-разработчик: с нуля до Middle";
  public JavaCourse(WebDriver webDriver) {
    super(webDriver, "/java-specialization/");
  }
}
