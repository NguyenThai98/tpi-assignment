package com.tpi.currency.client.base.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends NoRetryException {

  private String code;
  private String reason;

  public ApiException(String code, String reason, String message) {
    super(message);
    this.code = code;
    this.reason = reason;
  }
}
