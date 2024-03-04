package com.muddassir.runtime.data;

import com.muddassir.runtime.model.ABC;
import com.muddassir.runtime.model.ABC_;
import com.muddassir.runtime.request.ABCFilter;
import com.muddassir.runtime.security.UserSecurityContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ABCRepository {
  @PersistenceContext private EntityManager em;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  /**
   * @param aBCFilter Object Used to List ABC
   * @param securityContext
   * @return List of ABC
   */
  public List<ABC> listAllABCs(ABCFilter aBCFilter, UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<ABC> q = cb.createQuery(ABC.class);
    Root<ABC> r = q.from(ABC.class);
    List<Predicate> preds = new ArrayList<>();
    addABCPredicate(aBCFilter, cb, q, r, preds, securityContext);
    q.select(r).where(preds.toArray(new Predicate[0]));
    TypedQuery<ABC> query = em.createQuery(q);

    if (aBCFilter.getPageSize() != null
        && aBCFilter.getCurrentPage() != null
        && aBCFilter.getPageSize() > 0
        && aBCFilter.getCurrentPage() > -1) {
      query
          .setFirstResult(aBCFilter.getPageSize() * aBCFilter.getCurrentPage())
          .setMaxResults(aBCFilter.getPageSize());
    }

    return query.getResultList();
  }

  public <T extends ABC> void addABCPredicate(
      ABCFilter aBCFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      UserSecurityContext securityContext) {

    if (aBCFilter.getName() != null && !aBCFilter.getName().isEmpty()) {
      preds.add(r.get(ABC_.name).in(aBCFilter.getName()));
    }

    if (aBCFilter.getId() != null && !aBCFilter.getId().isEmpty()) {
      preds.add(r.get(ABC_.id).in(aBCFilter.getId()));
    }
  }

  /**
   * @param aBCFilter Object Used to List ABC
   * @param securityContext
   * @return count of ABC
   */
  public Long countAllABCs(ABCFilter aBCFilter, UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<ABC> r = q.from(ABC.class);
    List<Predicate> preds = new ArrayList<>();
    addABCPredicate(aBCFilter, cb, q, r, preds, securityContext);
    q.select(cb.count(r)).where(preds.toArray(new Predicate[0]));
    TypedQuery<Long> query = em.createQuery(q);
    return query.getSingleResult();
  }

  public <T, I> List<T> listByIds(Class<T> c, SingularAttribute<T, I> idField, Set<I> ids) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> q = cb.createQuery(c);
    Root<T> r = q.from(c);
    q.select(r).where(r.get(idField).in(ids));
    return em.createQuery(q).getResultList();
  }

  public <T, I> T getByIdOrNull(Class<T> c, SingularAttribute<T, I> idField, I id) {
    return listByIds(c, idField, Collections.singleton(id)).stream().findFirst().orElse(null);
  }

  @Transactional
  public void merge(java.lang.Object base) {
    em.merge(base);
    applicationEventPublisher.publishEvent(base);
  }

  @Transactional
  public void massMerge(List<?> toMerge) {
    for (Object o : toMerge) {
      em.merge(o);
      applicationEventPublisher.publishEvent(o);
    }
  }
}
