package com.tpi.currency.repository;

import com.tpi.currency.entity.Coin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @created 27/04/2024 - 16:46
 * @project currency
 * @author: thainguyen
 */
@Repository
public interface CoinRepository extends JpaRepository<Coin, Long>, JpaSpecificationExecutor<Coin> {
  Optional<Coin> findByCode(String code);
}
