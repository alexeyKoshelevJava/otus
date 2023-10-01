package ru.otus.pages;

import org.openqa.selenium.WebDriver;
import ru.otus.pageobject.AbsPageObject;

public abstract class AbsBasePage<T> extends AbsPageObject {
  private static final String BASE_URL = System.getProperty("base.url");
  private String path = "";

  public AbsBasePage(WebDriver webDriver, String path) {
    super(webDriver);
    this.path = path;
  }

  public T open() {
    String url = BASE_URL;
    if (!BASE_URL.endsWith("/")) {
      url += "/";
    }
    webDriver.get(url + path);
    return (T) this;
  }
}
