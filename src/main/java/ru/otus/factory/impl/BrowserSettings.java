package ru.otus.factory.impl;

import org.openqa.selenium.MutableCapabilities;

/**
 * Интерфейс для конфигурации браузера.
 */
public interface BrowserSettings {
  /**
   * Общий метод для конфигурации браузера.
   *
   * @return MutableCapabilities.
   */
  MutableCapabilities configureDriver();
}
