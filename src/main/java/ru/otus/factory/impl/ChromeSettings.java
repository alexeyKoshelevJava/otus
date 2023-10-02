package ru.otus.factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Класс настройки браузера Chrome.
 */

public final class ChromeSettings implements BrowserSettings {

  @Override
  public MutableCapabilities configureDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-full-screen");
//    chromeOptions.addArguments("--homepage=about:blank");
    chromeOptions.addArguments("--enable-extensions");
//    chromeOptions.addArguments("--remote-allow-origins=*");
    chromeOptions.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir"));
    return chromeOptions;
  }
}
