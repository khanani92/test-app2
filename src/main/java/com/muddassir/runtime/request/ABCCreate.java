package com.muddassir.runtime.request;

/** Object Used to Create ABC */
public class ABCCreate {

  private String name;

  /**
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name name to set
   * @return ABCCreate
   */
  public <T extends ABCCreate> T setName(String name) {
    this.name = name;
    return (T) this;
  }
}
