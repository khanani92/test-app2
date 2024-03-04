package com.muddassir.runtime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ABC {

  @Id private String id;

  private String name;

  /**
   * @return id
   */
  @Id
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return ABC
   */
  public <T extends ABC> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name name to set
   * @return ABC
   */
  public <T extends ABC> T setName(String name) {
    this.name = name;
    return (T) this;
  }
}
