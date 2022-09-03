package br.com.brn.feedrapi.adapters.outbounds.persistence.consumer;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataConsumerRepository extends JpaRepository<ConsumerEntity, Long> {
}
