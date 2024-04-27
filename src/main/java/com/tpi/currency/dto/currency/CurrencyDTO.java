package com.tpi.currency.dto.currency;

import java.util.List;
import lombok.Data;

@Data
public class CurrencyDTO {
  private String updateTime;
  private List<CoinDTO> currencies;
}
