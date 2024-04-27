package com.tpi.currency.client.base;

import com.tpi.currency.client.base.exceptions.ApiException;
import com.tpi.currency.client.base.exceptions.ApiRetryableException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import java.nio.charset.Charset;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;

@Log4j2
public class ApiErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder errorDecoder = new Default();

  private final ClientConfig.ApiConfig apiConfig;

  public ApiErrorDecoder(ClientConfig.ApiConfig apiConfig) {
    this.apiConfig = apiConfig;
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    APIErrorResponse apiErrorResponse = exceptionMessage(response);
    if (apiConfig.getRetryableErrors().contains(response.status())) {
      return new ApiRetryableException(response, FeignException.errorStatus(methodKey, response));
    }

    if (apiConfig.getNonRetryableErrors().contains(response.status())) {
      return new ApiException(
          String.valueOf(response.status()), apiErrorResponse.reason(), apiErrorResponse.message());
    }

    return errorDecoder.decode(methodKey, response);
  }

  private APIErrorResponse exceptionMessage(Response response) {
    try (Reader reader = response.body().asReader(Charset.defaultCharset())) {
      return new APIErrorResponse(response.reason(), IOUtils.toString(reader));
    } catch (Exception e) {
      log.error(
          "Unable to extract exception details from response [responseStatus={}]",
          response.status(),
          e);
      return new APIErrorResponse(response.reason(), e.getMessage());
    }
  }
}
