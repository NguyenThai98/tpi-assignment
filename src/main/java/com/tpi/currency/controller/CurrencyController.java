package com.tpi.currency.controller;

import com.tpi.currency.dto.common.DIRECTION;
import com.tpi.currency.dto.common.ResultMessage;
import com.tpi.currency.dto.currency.CoinDTO;
import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.service.CurrencyService;
import com.tpi.currency.utils.ResultUtil;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @created 27/04/2024 - 16:19
 * @project currency
 * @author: thainguyen
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/currencies")
public class CurrencyController {
  private final CurrencyService currencyService;

  @GetMapping("/{currency}/{direction}")
  public ResultMessage<List<CoinDTO>> getCurrency(
      @PathVariable String currency, @PathVariable DIRECTION direction) {
    return ResultUtil.data(currencyService.getCurrency(currency, direction));
  }

  @PostMapping
  public ResultMessage<?> addCurrency(@RequestBody @Valid AddCurrencyDTO currencyDTO) {
    currencyService.save(currencyDTO);
    return ResultUtil.success();
  }

  @PutMapping
  public ResultMessage<?> modifyCurrency(@RequestBody @Valid UpdateCurrencyDTO updateCurrencyDTO) {
    currencyService.update(updateCurrencyDTO);
    return ResultUtil.success();
  }

  @DeleteMapping("/{currency}")
  public ResultMessage<?> delCurrency(@PathVariable String currency) {
    currencyService.delete(currency);
    return ResultUtil.success();
  }
}
