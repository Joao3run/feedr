package br.com.brn.feedrapi.adapters.outbounds.persistence.communication;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.CommunicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCommunicationRepository extends JpaRepository<CommunicationEntity, Long> {
}
