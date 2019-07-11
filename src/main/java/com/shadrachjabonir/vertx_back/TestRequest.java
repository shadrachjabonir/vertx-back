package com.shadrachjabonir.vertx_back;

import java.io.Serializable;

public class TestRequest implements Serializable {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
