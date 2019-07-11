package com.shadrachjabonir.vertx_back;

import java.io.Serializable;

public class TestReponse implements Serializable {
  private String message;
  private String code;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
