package ru.otus.factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.opera.OperaOptions;

public class OperaSetting implements BrowserSettings {
  @Override
  public MutableCapabilities configureDriver() {
    OperaOptions options = new OperaOptions();
    options.addArguments("--start-maximized");
    return options;
  }
}
