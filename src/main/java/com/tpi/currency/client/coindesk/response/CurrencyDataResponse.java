package com.tpi.currency.client.coindesk.response;

import com.tpi.currency.dto.currency.Currency;
import com.tpi.currency.dto.currency.TimeUpdateCurrencyDTO;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CurrencyDataResponse {
  private TimeUpdateCurrencyDTO time;
  private String disclaimer;
  private String chartName;
  private Map<String, Currency> bpi;
}
