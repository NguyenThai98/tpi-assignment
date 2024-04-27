package com.tpi.currency.mapper;

import static com.tpi.currency.utils.DateUtils.COINDESK_FORMAT_TIME;
import static com.tpi.currency.utils.DateUtils.FORMAT_DATE_TIME;

import com.tpi.currency.client.coindesk.response.CurrencyDataResponse;
import com.tpi.currency.dto.currency.CoinDTO;
import com.tpi.currency.dto.currency.Currency;
import com.tpi.currency.dto.currency.CurrencyDTO;
import com.tpi.currency.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", imports = DateUtils.class)
public interface CurrencyMapper {

  @Mapping(
      target = "updateTime",
      source = "currencyDataResponse.time.updated",
      qualifiedByName = "formatDateTime")
  @Mapping(
      target = "currencies",
      source = "currencyDataResponse.bpi",
      qualifiedByName = "extractToCurrencies")
  CurrencyDTO toCurrencyDTO(CurrencyDataResponse currencyDataResponse);

  @Mapping(target = "currency", source = "code")
  @Mapping(target = "name", source = "description")
  @Mapping(target = "rate", source = "rate")
  @Mapping(target = "rateFloat", source = "rateFloat")
  CoinDTO toCoinDTO(Currency currency);

  @Named("extractToCurrencies")
  default List<CoinDTO> extractToCurrencies(Map<String, Currency> bpi) {
    return new ArrayList<>(bpi.values().stream().map(this::toCoinDTO).toList());
  }

  @Named("formatDateTime")
  default String formatDateTime(String dateTime) {
    return DateUtils.convertDateTimeWithFormat(
        DateUtils.convertCoinDeskFormatTime(dateTime, COINDESK_FORMAT_TIME).toString(),
        FORMAT_DATE_TIME);
  }
}
