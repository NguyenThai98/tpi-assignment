package com.tpi.currency.controller;

import com.tpi.currency.dto.common.ResultMessage;
import com.tpi.currency.dto.currency.CurrencyDTO;
import com.tpi.currency.service.CoinDeskService;
import com.tpi.currency.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/coin")
public class CoinDeskController {
  private final CoinDeskService coinDeskService;

  @GetMapping("/price")
  public ResultMessage<CurrencyDTO> getPriceBitcoin() {
    return ResultUtil.data(coinDeskService.getPriceBitcoin());
  }
}
