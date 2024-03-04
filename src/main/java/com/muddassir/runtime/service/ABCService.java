package com.muddassir.runtime.service;

import com.muddassir.runtime.data.ABCRepository;
import com.muddassir.runtime.model.ABC;
import com.muddassir.runtime.request.ABCCreate;
import com.muddassir.runtime.request.ABCFilter;
import com.muddassir.runtime.request.ABCUpdate;
import com.muddassir.runtime.response.PaginationResponse;
import com.muddassir.runtime.security.UserSecurityContext;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ABCService {

  @Autowired private ABCRepository repository;

  /**
   * @param aBCCreate Object Used to Create ABC
   * @param securityContext
   * @return created ABC
   */
  public ABC createABC(ABCCreate aBCCreate, UserSecurityContext securityContext) {
    ABC aBC = createABCNoMerge(aBCCreate, securityContext);
    this.repository.merge(aBC);
    return aBC;
  }

  /**
   * @param aBCCreate Object Used to Create ABC
   * @param securityContext
   * @return created ABC unmerged
   */
  public ABC createABCNoMerge(ABCCreate aBCCreate, UserSecurityContext securityContext) {
    ABC aBC = new ABC();
    aBC.setId(UUID.randomUUID().toString());
    updateABCNoMerge(aBC, aBCCreate);

    return aBC;
  }

  /**
   * @param aBCCreate Object Used to Create ABC
   * @param aBC
   * @return if aBC was updated
   */
  public boolean updateABCNoMerge(ABC aBC, ABCCreate aBCCreate) {
    boolean update = false;

    if (aBCCreate.getName() != null && (!aBCCreate.getName().equals(aBC.getName()))) {
      aBC.setName(aBCCreate.getName());
      update = true;
    }

    return update;
  }

  /**
   * @param aBCUpdate
   * @param securityContext
   * @return aBC
   */
  public ABC updateABC(ABCUpdate aBCUpdate, UserSecurityContext securityContext) {
    ABC aBC = aBCUpdate.getABC();
    if (updateABCNoMerge(aBC, aBCUpdate)) {
      this.repository.merge(aBC);
    }
    return aBC;
  }

  /**
   * @param aBCFilter Object Used to List ABC
   * @param securityContext
   * @return PaginationResponse<ABC> containing paging information for ABC
   */
  public PaginationResponse<ABC> getAllABCs(
      ABCFilter aBCFilter, UserSecurityContext securityContext) {
    List<ABC> list = listAllABCs(aBCFilter, securityContext);
    long count = this.repository.countAllABCs(aBCFilter, securityContext);
    return new PaginationResponse<>(list, aBCFilter.getPageSize(), count);
  }

  /**
   * @param aBCFilter Object Used to List ABC
   * @param securityContext
   * @return List of ABC
   */
  public List<ABC> listAllABCs(ABCFilter aBCFilter, UserSecurityContext securityContext) {
    return this.repository.listAllABCs(aBCFilter, securityContext);
  }

  public <T, I> List<T> listByIds(Class<T> c, SingularAttribute<T, I> idField, Set<I> ids) {
    return repository.listByIds(c, idField, ids);
  }

  public <T, I> T getByIdOrNull(Class<T> c, SingularAttribute<T, I> idField, I id) {
    return repository.getByIdOrNull(c, idField, id);
  }

  public void merge(java.lang.Object base) {
    this.repository.merge(base);
  }

  public void massMerge(List<?> toMerge) {
    this.repository.massMerge(toMerge);
  }
}
