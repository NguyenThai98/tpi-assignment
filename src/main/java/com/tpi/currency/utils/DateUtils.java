package com.tpi.currency.utils;

import com.tpi.currency.exception.ServerException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;

public class DateUtils {
  public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
  public static final String COINDESK_FORMAT_TIME = "MMM dd, yyyy HH:mm:ss z";

  public static LocalDateTime convertCoinDeskFormatTime(String dateTime, String format) {
    try {
      return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
    } catch (Exception exception) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
  }

  public static String convertDateTimeWithFormat(String dateTime, String format) {
    try {
      return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME)
          .format(DateTimeFormatter.ofPattern(format));
    } catch (Exception exception) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
  }
}
