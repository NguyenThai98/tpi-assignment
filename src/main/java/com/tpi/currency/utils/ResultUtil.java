package com.tpi.currency.utils;

import com.tpi.currency.dto.common.ResultCode;
import com.tpi.currency.dto.common.ResultMessage;

public class ResultUtil<T> {
  private final ResultMessage<T> resultMessage;
  private static final Integer SUCCESS = 200;

  public ResultUtil() {
    resultMessage = new ResultMessage<>();
    resultMessage.setSuccess(true);
    resultMessage.setMessage("SUCCESS");
    resultMessage.setCode(SUCCESS);
  }

  public ResultMessage<T> setData(T t) {
    this.resultMessage.setResult(t);
    return this.resultMessage;
  }

  public ResultMessage<T> setSuccessMsg(ResultCode resultCode) {
    this.resultMessage.setSuccess(true);
    this.resultMessage.setMessage(resultCode.message());
    this.resultMessage.setCode(resultCode.code());
    return this.resultMessage;
  }

  public static <T> ResultMessage<T> data(T t) {
    return new ResultUtil<T>().setData(t);
  }

  public static <T> ResultMessage<T> success(ResultCode resultCode) {
    return new ResultUtil<T>().setSuccessMsg(resultCode);
  }

  public static <T> ResultMessage<T> success() {
    return new ResultUtil<T>().setSuccessMsg(ResultCode.SUCCESS);
  }

  public static <T> ResultMessage<T> error(ResultCode resultCode) {
    return new ResultUtil<T>().setErrorMsg(resultCode);
  }

  public static <T> ResultMessage<T> error(Integer code, String msg) {
    return new ResultUtil<T>().setErrorMsg(code, msg);
  }

  public ResultMessage<T> setErrorMsg(ResultCode resultCode) {
    this.resultMessage.setSuccess(false);
    this.resultMessage.setMessage(resultCode.message());
    this.resultMessage.setCode(resultCode.code());
    return this.resultMessage;
  }

  public ResultMessage<T> setErrorMsg(Integer code, String msg) {
    this.resultMessage.setSuccess(false);
    this.resultMessage.setMessage(msg);
    this.resultMessage.setCode(code);
    return this.resultMessage;
  }
}
