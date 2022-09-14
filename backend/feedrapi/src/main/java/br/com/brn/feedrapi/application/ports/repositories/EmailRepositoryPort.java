package br.com.brn.feedrapi.application.ports.repositories;

import br.com.brn.feedrapi.application.domain.models.Email;

public interface EmailRepositoryPort {

    Email save(Email email);
}
