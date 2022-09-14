package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.models.Client;
import br.com.brn.feedrapi.application.ports.repositories.ClientRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.ClientServicePort;

public class ClientService implements ClientServicePort {

    private final ClientRepositoryPort clientRepositoryPort;

    public ClientService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Client findById(Long id) {
        return clientRepositoryPort.findById(id);
    }

    @Override
    public Client findByBase(String base) {
        return clientRepositoryPort.findByBase(base);
    }
}
