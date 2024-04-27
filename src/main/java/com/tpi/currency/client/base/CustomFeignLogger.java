package com.tpi.currency.client.base;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomFeignLogger extends Logger {
  @Override
  protected void log(String configKey, String format, Object... args) {
    log.info(format(configKey, format, args));
  }

  protected String format(String configKey, String format, Object... args) {
    return String.format(methodTag(configKey) + format, args);
  }
}
