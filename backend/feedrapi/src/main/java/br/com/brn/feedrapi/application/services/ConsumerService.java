package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import br.com.brn.feedrapi.application.domain.models.Consumer;
import br.com.brn.feedrapi.application.ports.repositories.ConsumerRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.ConsumerServicePort;

import java.util.List;
import java.util.Optional;

public class ConsumerService implements ConsumerServicePort {

    private final ConsumerRepositoryPort consumerRepository;

    public ConsumerService(ConsumerRepositoryPort consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public List<Consumer> listConsumersByFilter(ConsumerFilter filter) {
        return consumerRepository.listConsumersByFilter(filter);
    }

    @Override
    public Consumer findById(Long id) {
        return consumerRepository.findById(id);
    }

    @Override
    public Consumer save(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

}
