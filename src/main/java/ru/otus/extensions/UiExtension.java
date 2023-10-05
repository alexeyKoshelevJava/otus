package ru.otus.extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.Driver;
import ru.otus.factory.FactoryWebDriver;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class UiExtension implements BeforeEachCallback, AfterEachCallback {
  private WebDriver driver;

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = new FactoryWebDriver().getDriver();

    List<Field> fieldList = getAnnotationFields(extensionContext);

    for (Field field : fieldList) {
      if (field.getType().getName().equals(WebDriver.class.getName())) {

        try {
          field.setAccessible(true);
          field.set(extensionContext.getTestInstance().get(), driver);
        } catch (IllegalAccessException e) {
          throw new Error("Could not access or set driver into field");
        }
      }
    }
  }

  private List<Field> getAnnotationFields(ExtensionContext extensionContext) {
    List<Field> fieldList = new ArrayList<>();
    Class<?> testClass = extensionContext.getTestClass().get();
    while (testClass != null) {
      for (Field field : testClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Driver.class)) {
          fieldList.add(field);
        }
      }
      testClass = testClass.getSuperclass();
    }
    return fieldList;
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {

    if (driver != null) {
      driver.close();
      driver.quit();
    }
  }

//  @Override
//  public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
//    boolean testResult = extensionContext.getExecutionException().isPresent();
//    if (testResult) {
//      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//      FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
//      Allure.addAttachment("Failed screenshot",
//          new BufferedInputStream(new FileInputStream(screenshot)));
//    }
//  }
}
