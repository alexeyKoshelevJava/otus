package ru.otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class MainPage extends AbsBasePage<MainPage> {
  String coursesPath = "//*[@class='sc-1pljn7g-6 kbUYTE' or @class='sc-12yergf-11 fgNPoG']";
  List<WebElement> courses;
  String panelPath = "//*[@class='sc-10izyea-0 kZwiYW']";
  DateTimeFormatter formatter;


  public MainPage(WebDriver driver) {
    super(driver, "/");
    formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        .withLocale(new Locale("ru"));
  }

  public WebElement findCourseByName(String courseName) {
    waitUntilElementWillBe(panelPath);
    courses = webDriver.findElements(By.xpath(coursesPath));
    waitUntilElementsPresents(courses);
    return courses.stream().filter(course -> course.getText()
            .contains(courseName)).findFirst()
        .orElseThrow(AbstractMethodError::new);
  }

  public void clickOnCourseByName(String courseName) {
    WebElement course = findCourseByName(courseName);
    actions.scrollToElement(course)
        .moveToElement(course)
        .pause(100)
        .build()
        .perform();
    course.click();
  }

  public void clickOnCourse(WebElement courseName) {
    waitUntilElementsPresents(courses);
    actions.scrollToElement(courseName).moveToElement(courseName).pause(100).build()
        .perform();
    courseName.click();
  }

  public WebElement findCourseByDateIfDateSmaller() {
    waitUntilElementWillBe(panelPath);
    courses = webDriver.findElements(By.xpath(coursesPath));
    waitUntilElementsPresents(courses);

    return courses.stream().reduce((x, y) -> {
      String textForX = getDateStringForWebElement(x);
      String textForY = getDateStringForWebElement(y);

      LocalDate dDateX = LocalDate.parse(textForX, formatter);
      LocalDate dDateY = LocalDate.parse(textForY, formatter);

      if (dDateX.isBefore(dDateY)) {
        return x;
      }
      if (dDateX.isAfter(dDateY)) {
        return y;
      }
      if (dDateX.isEqual(dDateY)) {
        return x;
      }
      throw new RuntimeException("Что то с датами не так");
    }).orElseThrow(AssertionFailedError::new);
  }

  public WebElement findCourseByDateIfDateGrater() {
    waitUntilElementWillBe(panelPath);
    courses = webDriver.findElements(By.xpath(coursesPath));
    waitUntilElementsPresents(courses);

    return courses.stream().reduce((x, y) -> {
      String textForX = getDateStringForWebElement(x);
      String textForY = getDateStringForWebElement(y);

      LocalDate dDateX = LocalDate.parse(textForX, formatter);
      LocalDate dDateY = LocalDate.parse(textForY, formatter);

      if (dDateX.isBefore(dDateY)) {
        return y;
      }
      if (dDateX.isAfter(dDateY)) {
        return x;
      }
      if (dDateX.isEqual(dDateY)) {
        return x;
      }
      throw new RuntimeException("Что то с датами не так");
    }).orElseThrow(AssertionFailedError::new);
  }


  private String getDateStringForWebElement(WebElement element) {
    DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy")
        .withLocale(new Locale("ru"));
    String currentYearString = LocalDate.now(ZoneId.systemDefault()).format(year);

    String date = getStringMatchingThePattern(element
        .getText(), "(С\\s\\d+\\s\\W*\\s\\d{4})");
    if (date.isEmpty()) {
      date = getStringMatchingThePattern(element
          .getText(), "(С\\s\\d+\\s\\W*\\s)")
          .substring(2)
          .replaceAll("\n", "")
          .trim();
    } else {
      date = date.
          substring(2)
          .replaceAll("\n", "")
          .trim();
    }
    if (!date.isEmpty()) {

      String[] textArr = date.split(" ");
      if (textArr.length == 2) {
        if (textArr[0].length() < 2) {
          textArr[0] = "0".concat(textArr[0]);
        }
        date = String.join(" ", textArr[0], textArr[1], currentYearString);
      }
      if (textArr.length == 3) {
        if (textArr[0].length() < 2) {
          textArr[0] = "0".concat(textArr[0]);
        }
        date = String.join(" ", textArr[0], textArr[1], textArr[2]);
      }
      return date;
    }
    throw new AssertionFailedError("Дата не найдена");
  }
}
