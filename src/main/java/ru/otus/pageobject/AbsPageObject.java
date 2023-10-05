package ru.otus.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbsPageObject {
  protected WebDriver webDriver;
  protected Actions actions;

  public AbsPageObject(WebDriver webDriver) {
    this.webDriver = webDriver;
    this.actions = new Actions(webDriver);
  }

  public String getStringMatchingThePattern(String text, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    String patternText = "";
    while (matcher.find()) {
      patternText = text.substring(matcher.start(), matcher.end());
    }
    return patternText;
  }
}
