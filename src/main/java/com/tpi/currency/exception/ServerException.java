package com.tpi.currency.exception;

public class ServerException extends RuntimeException {
  public ServerException(int code, String message) {
    super(message);
  }
}
