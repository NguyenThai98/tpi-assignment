package com.tpi.currency.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.currency.client.base.ApiErrorDecoder;
import com.tpi.currency.client.base.ClientConfig;
import com.tpi.currency.client.base.CustomFeignLogger;
import com.tpi.currency.client.coindesk.CoinDeskClient;
import com.tpi.currency.client.coindesk.CoinDeskConfig;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class FeignClientConfiguration {
  @Bean
  public CoinDeskClient getCoinDeskClient(CoinDeskConfig clientConfig, ObjectMapper objectMapper) {
    return getFeignClient(
        CoinDeskClient.class,
        clientConfig,
        new JacksonEncoder(objectMapper),
        new JacksonDecoder(objectMapper));
  }

  private <T> T getFeignClient(
      Class<T> clazz, ClientConfig clientConfig, Encoder encoder, Decoder decoder) {
    ClientConfig.ApiConfig apiConfig = clientConfig.getApiConfig();
    Feign.Builder feignBuilder =
        Feign.builder()
            .client(new OkHttpClient())
            .logger(new CustomFeignLogger())
            .logLevel(Logger.Level.FULL)
            .encoder(encoder)
            .decoder(decoder)
            .errorDecoder(new ApiErrorDecoder(apiConfig));

    if (apiConfig.getConnectionTimeOut() > 0) {
      Request.Options options =
          new Request.Options(
              apiConfig.getConnectionTimeOut(),
              TimeUnit.SECONDS,
              apiConfig.getReadTimeOut(),
              TimeUnit.SECONDS,
              true);
      feignBuilder.options(options);
    }

    if (apiConfig.getMaxAttempts() > 0) {
      Retryer retryer =
          new Retryer.Default(
              apiConfig.getPeriod(), apiConfig.getMaxPeriod(), apiConfig.getMaxAttempts());
      feignBuilder.retryer(retryer);
    }

    feignBuilder.requestInterceptor(
        x -> {
          x.header("Content-Type", "application/json");

          if (clientConfig.getAuth() != null && !clientConfig.getAuth().isEmpty()) {
            x.headers(
                clientConfig.getAuth().entrySet().stream()
                    .collect(
                        Collectors.toMap(
                            Map.Entry::getKey, headers -> List.of(headers.getValue()))));
          }
        });

    return feignBuilder.target(clazz, apiConfig.getUrl());
  }
}
