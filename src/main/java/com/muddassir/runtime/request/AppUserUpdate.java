package com.muddassir.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muddassir.runtime.model.AppUser;
import com.muddassir.runtime.validation.IdValid;
import com.muddassir.runtime.validation.Update;

/** Object Used to Update AppUser */
@IdValid.List({
  @IdValid(
      targetField = "appUser",
      field = "id",
      fieldType = AppUser.class,
      groups = {Update.class})
})
public class AppUserUpdate extends AppUserCreate {

  @JsonIgnore private AppUser appUser;

  private String id;

  /**
   * @return appUser
   */
  @JsonIgnore
  public AppUser getAppUser() {
    return this.appUser;
  }

  /**
   * @param appUser appUser to set
   * @return AppUserUpdate
   */
  public <T extends AppUserUpdate> T setAppUser(AppUser appUser) {
    this.appUser = appUser;
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
   * @return AppUserUpdate
   */
  public <T extends AppUserUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
