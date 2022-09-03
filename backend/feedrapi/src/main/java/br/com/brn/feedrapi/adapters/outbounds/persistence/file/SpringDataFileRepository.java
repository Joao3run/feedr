package br.com.brn.feedrapi.adapters.outbounds.persistence.file;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.GlobalFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataFileRepository extends JpaRepository<GlobalFileEntity, String> {
}
