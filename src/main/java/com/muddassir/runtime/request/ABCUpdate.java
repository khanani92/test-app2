package com.muddassir.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir.runtime.model.ABC;
import com.muddassir.runtime.validation.IdValid;
import com.muddassir.runtime.validation.Update;

/** Object Used to Update ABC */
@IdValid.List({
  @IdValid(
      targetField = "aBC",
      field = "id",
      fieldType = ABC.class,
      groups = {Update.class})
})
public class ABCUpdate extends ABCCreate {

  @JsonIgnore private ABC aBC;

  private String id;

  /**
   * @return aBC
   */
  @JsonIgnore
  public ABC getABC() {
    return this.aBC;
  }

  /**
   * @param aBC aBC to set
   * @return ABCUpdate
   */
  public <T extends ABCUpdate> T setABC(ABC aBC) {
    this.aBC = aBC;
    return (T) this;
  }

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return ABCUpdate
   */
  public <T extends ABCUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
