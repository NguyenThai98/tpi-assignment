package com.tpi.currency.client.base;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClientConfig {
  @NotNull private String type;
  private Map<String, String> auth;
  private ApiConfig apiConfig;

  @Getter
  @Setter
  public static class ApiConfig {
    private String name;
    private String url;
    private int connectionTimeOut;
    private int readTimeOut;
    private int period;
    private int maxPeriod;
    private int maxAttempts;
    private List<Integer> retryableErrors;
    private List<Integer> nonRetryableErrors;
  }
}
