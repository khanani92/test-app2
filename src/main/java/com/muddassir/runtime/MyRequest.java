package com.muddassir.runtime;

import com.muddassir.runtime.model.Genre;
import java.util.List;

public class MyRequest {

  private Genre genre;

  private String name;

  private List<String> name2;

  /**
   * @return genre
   */
  public Genre getGenre() {
    return this.genre;
  }

  /**
   * @param genre genre to set
   * @return MyRequest
   */
  public <T extends MyRequest> T setGenre(Genre genre) {
    this.genre = genre;
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
   * @return MyRequest
   */
  public <T extends MyRequest> T setName(String name) {
    this.name = name;
    return (T) this;
  }

  /**
   * @return name2
   */
  public List<String> getName2() {
    return this.name2;
  }

  /**
   * @param name2 name2 to set
   * @return MyRequest
   */
  public <T extends MyRequest> T setName2(List<String> name2) {
    this.name2 = name2;
    return (T) this;
  }
}
