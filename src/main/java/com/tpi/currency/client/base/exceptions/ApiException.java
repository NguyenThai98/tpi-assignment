package com.tpi.currency.client.base.exceptions;

public class ApiException extends NoRetryException {

  private String code;
  private String reason;

  public ApiException(String code, String reason, String message) {
    super(message);
    this.code = code;
    this.reason = reason;
  }

  public String getErrorCode() {
    return code;
  }

  public String getReason() {
    return reason;
  }
}
