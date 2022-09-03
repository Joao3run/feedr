package br.com.brn.feedrapi.adapters.outbounds.persistence.communication;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.CommunicationEntity;
import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;

import javax.persistence.EntityManager;
import java.util.List;

public class JPACommunicationRepositoryImpl implements JPACommunicationRepository {

    private final EntityManager entityManager;

    public JPACommunicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<CommunicationEntity> listCommunicationsByFilter(CommunicationFilter filter) {
        String sql = "SELECT C FROM CommunicationEntity AS C WHERE 1 = 1 ";
        var query = entityManager.createQuery(sql, CommunicationEntity.class);
        return query.getResultList();
    }


}
