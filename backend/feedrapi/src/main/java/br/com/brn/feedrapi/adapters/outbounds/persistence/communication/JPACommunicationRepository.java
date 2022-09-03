package br.com.brn.feedrapi.adapters.outbounds.persistence.communication;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.CommunicationEntity;
import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPACommunicationRepository {

    List<CommunicationEntity> listCommunicationsByFilter(CommunicationFilter filter);
}
