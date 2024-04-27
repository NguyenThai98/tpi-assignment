package com.tpi.currency.client.base.exceptions;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;

public class ApiRetryableException extends RetryableException {
  public ApiRetryableException(Response response, FeignException exception) {
    super(
        response.status(),
        String.format(
            "Failed to make %s to %s", response.request().httpMethod(), response.request().url()),
        response.request().httpMethod(),
        exception.getCause(),
        Long.valueOf(response.status()),
        response.request());
  }
}
