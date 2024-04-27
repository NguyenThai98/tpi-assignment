package com.tpi.currency.mapper;

import com.tpi.currency.dto.currency.CoinDTO;
import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.entity.Coin;
import com.tpi.currency.exception.ServerException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;

/**
 * @created 27/04/2024 - 17:11
 * @project currency
 * @author: thainguyen
 */
@Mapper(componentModel = "spring")
public interface CoinMapper {

  @Mapping(target = "currency", source = "code")
  @Mapping(target = "name", source = "description")
  @Mapping(target = "rate", source = "rate")
  CoinDTO toCoinDTO(Coin coin);

  @Mapping(target = "code", source = "currency")
  @Mapping(target = "description", source = "name")
  @Mapping(target = "rate", source = "rate")
  @Mapping(target = "rateFloat", source = "rate", qualifiedByName = "convertToFloat")
  Coin toCoinEntity(AddCurrencyDTO coin);

  default Coin updateCoin(Coin coin, UpdateCurrencyDTO updateCurrencyDTO) {
    if (StringUtils.isNotEmpty(updateCurrencyDTO.currency())) {
      coin.setCode(updateCurrencyDTO.currency());
    }
    if (ObjectUtils.isNotEmpty(updateCurrencyDTO.rate())) {
      coin.setRate(String.valueOf(updateCurrencyDTO.rate()));
      coin.setRateFloat(updateCurrencyDTO.rate());
    }
    if (ObjectUtils.isNotEmpty(updateCurrencyDTO.name())) {
      coin.setDescription(updateCurrencyDTO.name());
    }
    return coin;
  }

  @Named("convertToFloat")
  default float convertToFloat(String rate) {
    return parseFloat(rate);
  }

  private static float parseFloat(String rate) {
    try {
      return Float.parseFloat(rate);
    } catch (Exception exception) {
      throw new ServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
  }
}
