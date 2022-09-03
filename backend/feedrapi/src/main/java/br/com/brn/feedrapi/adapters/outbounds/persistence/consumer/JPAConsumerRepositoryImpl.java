package br.com.brn.feedrapi.adapters.outbounds.persistence.consumer;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ConsumerEntity;
import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;

import javax.persistence.EntityManager;
import java.util.List;

public class JPAConsumerRepositoryImpl implements JPAConsumerRepository {

    private final EntityManager entityManager;

    public JPAConsumerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ConsumerEntity> listConsumersByFilter(ConsumerFilter filter) {
        String sql = "SELECT C FROM ConsumerEntity AS C WHERE 1 = 1 ";

        if (filter.getOnlyActives()) {
            sql += " AND C.active = :active ";
        }

        var query = entityManager.createQuery(sql, ConsumerEntity.class);
        if (filter.getOnlyActives()) {
            query.setParameter("active", filter.getOnlyActives());

        }

        return query.getResultList();
    }


}
