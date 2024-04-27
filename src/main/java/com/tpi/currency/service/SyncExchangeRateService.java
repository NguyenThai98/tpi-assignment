package com.tpi.currency.service;

import com.tpi.currency.dto.currency.CoinDTO;
import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.exception.ClientException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @created 28/04/2024 - 00:09
 * @project currency
 * @author: thainguyen
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SyncExchangeRateService {
  private final CoinDeskService coinDeskService;
  private final CurrencyService currencyService;

  public void execution() {
    LocalDateTime startedTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
    log.info("Task sync exchange rate started at: {}", startedTime);
    Optional.ofNullable(coinDeskService.getPriceBitcoin())
        .ifPresent(
            currencyDTO -> {
              currencyDTO
                  .getCurrencies()
                  .forEach(
                      coinDTO -> {
                        try {
                          upsertCoin(coinDTO);
                        } catch (ClientException exception) {
                          addNewCoin(coinDTO);
                        }
                      });
            });
    LocalDateTime endTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
    log.info(
        "Task sync exchange rate end, execution with {} millisecond",
        ChronoUnit.MILLIS.between(startedTime, endTime));
  }

  private void addNewCoin(CoinDTO coinDTO) {
    currencyService.save(
        new AddCurrencyDTO(coinDTO.currency(), coinDTO.name(), coinDTO.rateFloat()));
  }

  private void upsertCoin(CoinDTO coinDTO) {
    Optional.ofNullable(currencyService.getCoinByCurrency(coinDTO.currency()))
        .ifPresent(
            coin -> {
              currencyService.update(new UpdateCurrencyDTO(coinDTO.currency(), null, coinDTO.rateFloat()));
            });
  }
}
