package br.com.brn.feedrapi.adapters.outbounds.persistence.consumer;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ConsumerEntity;
import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import br.com.brn.feedrapi.application.domain.models.Consumer;
import br.com.brn.feedrapi.application.ports.repositories.ConsumerRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class ConsumerRepository implements ConsumerRepositoryPort {

    private final SpringDataConsumerRepository dataConsumerRepository;

    private final JPAConsumerRepository jpaConsumerRepository;

    private final ModelMapper modelMapper;

    public ConsumerRepository(SpringDataConsumerRepository dataConsumerRepository, JPAConsumerRepository jpaConsumerRepository, ModelMapper modelMapper) {
        this.dataConsumerRepository = dataConsumerRepository;
        this.jpaConsumerRepository = jpaConsumerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Consumer> listConsumersByFilter(ConsumerFilter filter) {
        List<ConsumerEntity> consumers = jpaConsumerRepository.listConsumersByFilter(filter);
        return consumers
                .stream()
                .map(consumerEntity -> modelMapper.map(consumerEntity, Consumer.class))
                .collect(Collectors.toList());
    }

    @Override
    public Consumer findById(Long id) {
        Optional<ConsumerEntity> consumerEntity = dataConsumerRepository.findById(id);
        return modelMapper.map(consumerEntity, Consumer.class);
    }

    @Override
    public Consumer save(Consumer consumer) {
        ConsumerEntity consumerEntity = modelMapper.map(consumer, ConsumerEntity.class);
        consumerEntity = dataConsumerRepository.save(consumerEntity);
        return modelMapper.map(consumerEntity, Consumer.class);
    }

    @Override
    public Consumer findByEmail(String email) {
        Optional<ConsumerEntity> consumerEntity = dataConsumerRepository.findByEmail(email);
        return modelMapper.map(consumerEntity, Consumer.class);
    }
}
