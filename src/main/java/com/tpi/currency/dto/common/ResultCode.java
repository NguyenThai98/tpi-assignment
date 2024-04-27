package com.tpi.currency.dto.common;

public enum ResultCode {
  SUCCESS(200, "SUCCESS"),
  ERROR(400, "ERROR"),
  INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");
  private final Integer code;
  private final String message;

  ResultCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer code() {
    return this.code;
  }

  public String message() {
    return this.message;
  }
}
