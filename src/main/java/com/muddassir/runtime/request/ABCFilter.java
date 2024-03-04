package com.muddassir.runtime.request;

import jakarta.validation.constraints.Min;
import java.util.Set;

/** Object Used to List ABC */
public class ABCFilter {

  @Min(value = 0)
  private Integer currentPage;

  private Set<String> id;

  private Set<String> name;

  @Min(value = 1)
  private Integer pageSize;

  /**
   * @return currentPage
   */
  public Integer getCurrentPage() {
    return this.currentPage;
  }

  /**
   * @param currentPage currentPage to set
   * @return ABCFilter
   */
  public <T extends ABCFilter> T setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return (T) this;
  }

  /**
   * @return id
   */
  public Set<String> getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return ABCFilter
   */
  public <T extends ABCFilter> T setId(Set<String> id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return name
   */
  public Set<String> getName() {
    return this.name;
  }

  /**
   * @param name name to set
   * @return ABCFilter
   */
  public <T extends ABCFilter> T setName(Set<String> name) {
    this.name = name;
    return (T) this;
  }

  /**
   * @return pageSize
   */
  public Integer getPageSize() {
    return this.pageSize;
  }

  /**
   * @param pageSize pageSize to set
   * @return ABCFilter
   */
  public <T extends ABCFilter> T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }
}
