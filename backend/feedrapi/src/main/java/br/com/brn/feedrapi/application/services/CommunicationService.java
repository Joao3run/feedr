package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;
import br.com.brn.feedrapi.application.domain.models.Communication;
import br.com.brn.feedrapi.application.ports.repositories.CommunicationRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.CommunicationServicePort;

import java.util.List;

public class CommunicationService implements CommunicationServicePort {
    private final CommunicationRepositoryPort communicationRepository;

    public CommunicationService(CommunicationRepositoryPort communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    public List<Communication> listCommunicationsByFilter(CommunicationFilter filter) {
        return communicationRepository.listCommunicationsByFilter(filter);
    }

    @Override
    public Communication findById(Long id) {
        return communicationRepository.findById(id);
    }
}
