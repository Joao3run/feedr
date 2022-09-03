package br.com.brn.feedrapi.application.ports.repositories;

import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import br.com.brn.feedrapi.application.domain.models.Consumer;

import java.util.List;

public interface ConsumerRepositoryPort {

    List<Consumer> listConsumersByFilter(ConsumerFilter filter);

    Consumer findById(Long id);

    Consumer save(Consumer consumer);
}
