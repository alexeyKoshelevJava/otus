package ru.otus.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.Driver;
import ru.otus.extensions.UiExtension;
import ru.otus.pages.BiCoursePage;
import ru.otus.pages.MainPage;


@ExtendWith(UiExtension.class)

public class OtusTest {


  @Driver
  private WebDriver driver;


  @Test
  @DisplayName("Выбор курса по названию и проверка заголовка страницы курса")
  public void findCourseByNameAndCheckTitle() {
    String course = "BI-аналитика";
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickOnCourseByName(course);
    BiCoursePage biCoursePage = new BiCoursePage(driver);
    biCoursePage.checkTitle(BiCoursePage.TITLE);
  }

  @Test
  @DisplayName("Выбор курса с самой маленькой датой")
  public void findCourseByDateIfDateSmallerThanOthers() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickOnCourse(mainPage.findCourseByDateIfDateSmaller());
    mainPage.checkTitleNotEmpty();

  }

  @Test
  @DisplayName("Выбор курса с самой большой датой")
  public void findCourseByDateIfDateGraterThanOthers() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    mainPage.clickOnCourse(mainPage.findCourseByDateIfDateGrater());
    mainPage.checkTitleNotEmpty();
  }
}

