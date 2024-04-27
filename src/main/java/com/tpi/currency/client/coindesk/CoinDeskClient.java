package com.tpi.currency.client.coindesk;

import com.tpi.currency.client.coindesk.response.CurrencyDataResponse;
import feign.RequestLine;

public interface CoinDeskClient {
  @RequestLine("GET /v1/bpi/currentprice.json")
  CurrencyDataResponse getPriceBitcoin();
}
