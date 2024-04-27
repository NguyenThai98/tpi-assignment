package com.tpi.currency.service;

import com.tpi.currency.client.coindesk.CoinDeskClient;
import com.tpi.currency.dto.currency.CurrencyDTO;
import com.tpi.currency.mapper.CurrencyMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinDeskService {
  private final CoinDeskClient coinDeskClient;
  private final CurrencyMapper currencyMapper;

  public CurrencyDTO getPriceBitcoin() {
    return Optional.ofNullable(coinDeskClient.getPriceBitcoin())
        .map(currencyMapper::toCurrencyDTO)
        .orElseGet(() -> null);
  }
}
