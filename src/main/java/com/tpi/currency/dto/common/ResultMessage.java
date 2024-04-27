package com.tpi.currency.dto.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Data;

@Data
public class ResultMessage<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  private boolean success;
  private String message;
  private Integer code;
  private LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
  private T result;
}
