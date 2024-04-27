package com.tpi.currency;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT1M")
@SpringBootApplication
public class CurrencyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyApplication.class, args);
  }
}
