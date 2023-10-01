package ru.otus.factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxSetting implements BrowserSettings {
  @Override
  public MutableCapabilities configureDriver() {
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--start-maximized");
    return options;
  }
}
