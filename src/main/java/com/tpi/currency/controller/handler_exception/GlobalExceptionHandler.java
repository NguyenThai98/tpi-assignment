package com.tpi.currency.controller.handler_exception;

import com.tpi.currency.dto.common.ResultMessage;
import com.tpi.currency.exception.ClientException;
import com.tpi.currency.exception.ServerException;
import com.tpi.currency.utils.ResultUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ClientException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResultMessage<?> handleClientException(ClientException exception) {
    log.error("Client exception was thrown: {}", exception.getMessage());
    return ResultUtil.error(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
  }

  @ExceptionHandler(ServerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResultMessage<?> handleServerException(ServerException exception) {
    log.error("Server error: {}", exception.getMessage(), exception);
    return ResultUtil.error(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResultMessage<?> handleException(Exception exception) {
    log.error("Server error: {}", exception.getMessage(), exception);
    return ResultUtil.error(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResultMessage<?> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    exception
        .getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ResultUtil.error(HttpStatus.BAD_REQUEST.value(), errors.toString());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResultMessage<?> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception) {
    log.error("Validation error: {}", exception.getMessage(), exception);
    return ResultUtil.error(
        HttpStatus.BAD_REQUEST.value(),
        String.format("Invalid format field: %s", exception.getName()));
  }
}
