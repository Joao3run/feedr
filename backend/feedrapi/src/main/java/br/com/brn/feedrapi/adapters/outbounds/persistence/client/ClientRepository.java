package br.com.brn.feedrapi.adapters.outbounds.persistence.client;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ClientEntity;
import br.com.brn.feedrapi.application.domain.models.Client;
import br.com.brn.feedrapi.application.ports.repositories.ClientRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class ClientRepository implements ClientRepositoryPort {

    private final SpringDataClientRepository dataUserRepository;

    private final ModelMapper modelMapper;

    public ClientRepository(SpringDataClientRepository dataUserRepository, ModelMapper modelMapper) {
        this.dataUserRepository = dataUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Client findById(Long id) {
        Optional<ClientEntity> clientEntity = dataUserRepository.findById(id);
        return modelMapper.map(clientEntity, Client.class);
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
        clientEntity = dataUserRepository.save(clientEntity);
        return modelMapper.map(clientEntity, Client.class);
    }

    @Override
    public Client findByBase(String base) {
        Optional<ClientEntity> clientEntity = dataUserRepository.findByBase(base);
        return modelMapper.map(clientEntity, Client.class);
    }
}
