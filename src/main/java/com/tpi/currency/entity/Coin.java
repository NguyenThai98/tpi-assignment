package com.tpi.currency.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @created 27/04/2024 - 16:47
 * @project currency
 * @author: thainguyen
 */
@Entity
@Table(name = "coin")
@Data
public class Coin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime updated;
  private String disclaimer;
  private String chartName;
  private String code;
  private String symbol;
  private String rate;
  private String description;
  private Float rateFloat;
}
