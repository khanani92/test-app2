package com.muddassir.runtime;

public class MyRequest {

  private String name;

  /**
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name name to set
   * @return MyRequest
   */
  public <T extends MyRequest> T setName(String name) {
    this.name = name;
    return (T) this;
  }
}
