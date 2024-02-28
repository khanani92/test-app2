package com.muddassir.runtime;

import com.muddassir.runtime.model.Genre;
import java.util.List;

public class Testobj {

  private List<String> ic;

  private Genre ie;

  private String iname;

  /**
   * @return ic
   */
  public List<String> getIc() {
    return this.ic;
  }

  /**
   * @param ic ic to set
   * @return Testobj
   */
  public <T extends Testobj> T setIc(List<String> ic) {
    this.ic = ic;
    return (T) this;
  }

  /**
   * @return ie
   */
  public Genre getIe() {
    return this.ie;
  }

  /**
   * @param ie ie to set
   * @return Testobj
   */
  public <T extends Testobj> T setIe(Genre ie) {
    this.ie = ie;
    return (T) this;
  }

  /**
   * @return iname
   */
  public String getIname() {
    return this.iname;
  }

  /**
   * @param iname iname to set
   * @return Testobj
   */
  public <T extends Testobj> T setIname(String iname) {
    this.iname = iname;
    return (T) this;
  }
}
