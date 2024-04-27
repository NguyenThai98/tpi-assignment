package com.tpi.currency.exception;

public class ClientException extends RuntimeException {
  public ClientException(int code, String message) {
    super(message);
  }
}
