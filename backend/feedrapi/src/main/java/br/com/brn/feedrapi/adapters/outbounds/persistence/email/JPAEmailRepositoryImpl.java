package br.com.brn.feedrapi.adapters.outbounds.persistence.email;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.EmailEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class JPAEmailRepositoryImpl implements JPAEmailRepository {

    private final EntityManager entityManager;

    public JPAEmailRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<EmailEntity> findWithLimit(int limit) {
        String sql = "SELECT E FROM EmailEntity AS E ";
        return entityManager.createQuery(sql, EmailEntity.class).setMaxResults(limit).getResultList();
    }

}
