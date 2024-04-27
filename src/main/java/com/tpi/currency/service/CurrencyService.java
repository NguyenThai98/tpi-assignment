package com.tpi.currency.service;

import static java.lang.String.format;

import com.tpi.currency.dto.common.DIRECTION;
import com.tpi.currency.dto.currency.CoinDTO;
import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.entity.Coin;
import com.tpi.currency.exception.ClientException;
import com.tpi.currency.mapper.CoinMapper;
import com.tpi.currency.repository.CoinRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {
  private final CoinRepository coinRepository;
  private final CoinMapper coinMapper;

  public List<CoinDTO> getCurrency(String currency, DIRECTION direction) {
    final Specification<Coin> specification =
        (root, query, builder) -> builder.and(resolveCurrency(currency, root, builder));
    return coinRepository.findAll(specification, resolveDirection(direction)).stream()
        .map(coinMapper::toCoinDTO)
        .toList();
  }

  private Predicate resolveCurrency(String currency, Root<Coin> root, CriteriaBuilder builder) {
    if (StringUtils.isEmpty(currency)) {
      return builder.conjunction();
    }
    return builder.equal(root.get("code"), currency);
  }

  private Sort resolveDirection(DIRECTION direction) {
    if (direction.equals(DIRECTION.DESC)) {
      return Sort.by(Sort.Direction.DESC, "code");
    }
    return Sort.by(Sort.Direction.ASC, "code");
  }

  public void save(AddCurrencyDTO addCurrencyDTO) {
    if (isExistsCurrency(addCurrencyDTO.currency())) {
      throw new ClientException(
          HttpStatus.BAD_REQUEST.value(), format("Record exists: %s", addCurrencyDTO.currency()));
    }
    coinRepository.save(coinMapper.toCoinEntity(addCurrencyDTO));
  }

  public void update(UpdateCurrencyDTO dto) {
    Optional.of(getCoinByCurrency(dto.currency()))
        .map(coin -> coinMapper.updateCoin(coin, dto))
        .map(coinRepository::save);
  }

  public void delete(String currency) {
    Optional.of(getCoinByCurrency(currency)).ifPresent(coinRepository::delete);
  }

  private Coin getCoinByCurrency(String currency) {
    return coinRepository
        .findByCode(currency)
        .orElseThrow(
            () ->
                new ClientException(
                    HttpStatus.BAD_REQUEST.value(),
                    format("Record does not exists: %s", currency)));
  }

  private boolean isExistsCurrency(String currency) {
    return coinRepository.findByCode(currency).map(coin -> Boolean.TRUE).orElse(Boolean.FALSE);
  }
}
