package com.tpi.currency.client.base.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoRetryException extends RuntimeException {
  public NoRetryException(String errorCode) {
    super(errorCode);
  }
}
