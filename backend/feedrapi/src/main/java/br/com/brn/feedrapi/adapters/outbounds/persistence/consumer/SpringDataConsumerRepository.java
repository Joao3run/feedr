package br.com.brn.feedrapi.adapters.outbounds.persistence.consumer;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataConsumerRepository extends JpaRepository<ConsumerEntity, Long> {

    Optional<ConsumerEntity> findByEmail(String email);

}
