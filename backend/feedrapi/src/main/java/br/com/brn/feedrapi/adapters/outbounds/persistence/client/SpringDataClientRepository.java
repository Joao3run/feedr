package br.com.brn.feedrapi.adapters.outbounds.persistence.client;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByBase(String base);
}
