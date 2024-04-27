package com.tpi.currency.controller;

import com.tpi.currency.dto.common.DIRECTION;
import com.tpi.currency.dto.common.ResultMessage;
import com.tpi.currency.dto.currency.CoinDTO;
import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.service.CurrencyService;
import com.tpi.currency.utils.ResultUtil;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  /**
   *
   * @param currency
   * @param direction
   * @return List of coin object
   */
  @GetMapping
  public ResultMessage<List<CoinDTO>> getCurrency(
      @RequestParam(name = "currency") String currency,
      @RequestParam(name = "direction") DIRECTION direction) {
    return ResultUtil.data(currencyService.getCurrency(currency, direction));
  }

  /**
   *
   * @param currencyDTO - The object we need to add new coin
   * @return
   */
  @PostMapping
  public ResultMessage<?> addCurrency(@RequestBody @Valid AddCurrencyDTO currencyDTO) {
    currencyService.save(currencyDTO);
    return ResultUtil.success();
  }

  /**
   *
   * @param updateCurrencyDTO - The object we need to update coin exists
   * @return
   */

  @PutMapping
  public ResultMessage<?> updateCurrency(@RequestBody @Valid UpdateCurrencyDTO updateCurrencyDTO) {
    currencyService.update(updateCurrencyDTO);
    return ResultUtil.success();
  }

  /**
   *
   * @param currency The currency code we need to remove
   * @return
   */
  @DeleteMapping("/{currency}")
  public ResultMessage<?> delCurrency(@PathVariable String currency) {
    currencyService.delete(currency);
    return ResultUtil.success();
  }
}
