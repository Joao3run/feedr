package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.models.Client;
import br.com.brn.feedrapi.application.domain.models.User;

import java.util.List;

public interface ClientServicePort {

    Client save(Client client);

    Client findById(Long id);


    Client findByBase(String base);
}
