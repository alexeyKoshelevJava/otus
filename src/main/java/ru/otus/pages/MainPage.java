package ru.otus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends AbsBasePage<MainPage> {

  @FindBy(xpath = "//button[contains(text(),'Больше курсов')]")
  WebElement moreCoursesElement;
  @FindBy(xpath = "//*[contains(text(), 'Популярные курсы')]")
  WebElement popularCourses;

  public MainPage(WebDriver driver) {
    super(driver, "/");
    PageFactory.initElements(webDriver, this);

  }

  public void clickOn() {
//    waitUntilElementBeVisible(moreCoursesElement);
//    moreCoursesElement.click();
    waitUntilElementBeVisible(popularCourses);
    popularCourses.click();
  }
}
