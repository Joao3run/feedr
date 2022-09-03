package br.com.brn.feedrapi.adapters.outbounds.persistence.communication;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.CommunicationEntity;
import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ConsumerEntity;
import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;
import br.com.brn.feedrapi.application.domain.models.Communication;
import br.com.brn.feedrapi.application.domain.models.Consumer;
import br.com.brn.feedrapi.application.ports.repositories.CommunicationRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class CommunicationRepository implements CommunicationRepositoryPort {

    private final SpringDataCommunicationRepository dataCommunicationRepository;

    private final JPACommunicationRepository jpaCommunicationRepository;

    private final ModelMapper modelMapper;

    public CommunicationRepository(SpringDataCommunicationRepository dataCommunicationRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JPACommunicationRepository jpaCommunicationRepository, ModelMapper modelMapper1) {
        this.dataCommunicationRepository = dataCommunicationRepository;
        this.jpaCommunicationRepository = jpaCommunicationRepository;
        this.modelMapper = modelMapper1;
    }

    @Override
    public List<Communication> listCommunicationsByFilter(CommunicationFilter filter) {
        List<CommunicationEntity> communications = jpaCommunicationRepository.listCommunicationsByFilter(filter);
        return communications
                .stream()
                .map(consumerEntity -> modelMapper.map(consumerEntity, Communication.class))
                .collect(Collectors.toList());
    }

    @Override
    public Communication findById(Long id) {
        Optional<CommunicationEntity> communication = dataCommunicationRepository.findById(id);
        return modelMapper.map(communication, Communication.class);
    }
}
