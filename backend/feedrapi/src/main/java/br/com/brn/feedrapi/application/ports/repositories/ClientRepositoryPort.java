package br.com.brn.feedrapi.application.ports.repositories;


import br.com.brn.feedrapi.application.domain.models.Client;

public interface ClientRepositoryPort {

    Client save(Client client);

    Client findById(Long id);

    Client findByBase(String base);
}
