package com.tpi.currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  public void formatDateTime() {
    String dateTime = "Apr 27, 2024 07:28:35 UTC";

    // Define the format of the input string
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss z");
    // Parse the input string into a LocalDateTime object
    LocalDateTime dateTimeFormat = LocalDateTime.parse(dateTime, formatter);
    System.out.println("dateTimeFormat = " + dateTimeFormat);
  }
}
