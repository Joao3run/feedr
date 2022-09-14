package br.com.brn.feedrapi.adapters.outbounds.persistence.email;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEmailRepository extends JpaRepository<EmailEntity, Long> {
}
