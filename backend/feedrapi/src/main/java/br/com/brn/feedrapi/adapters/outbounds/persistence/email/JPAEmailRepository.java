package br.com.brn.feedrapi.adapters.outbounds.persistence.email;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.EmailEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAEmailRepository {

    List<EmailEntity> findWithLimit(int limit);
}
