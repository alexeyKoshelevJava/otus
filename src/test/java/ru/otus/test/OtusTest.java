package ru.otus.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.otus.annotations.Driver;
import ru.otus.extensions.UiExtension;
import ru.otus.pages.MainPage;

@ExtendWith(UiExtension.class)
public class OtusTest {


  @Driver
  private EventFiringWebDriver driver;


  @ParameterizedTest
  @ValueSource(strings = {"Специализация QA Automation Engineer"})
  @DisplayName("Выбор курса по названию и проверка полной стоимости")
  public void findCourseByNameAndCheckPrice(String course) throws InterruptedException {
    System.out.println("мой тест начал");
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    System.out.println("тест закончил");
  }
}
