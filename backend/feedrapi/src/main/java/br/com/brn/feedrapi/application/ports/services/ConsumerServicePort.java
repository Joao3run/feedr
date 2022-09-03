package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import br.com.brn.feedrapi.application.domain.models.Consumer;

import java.util.List;
import java.util.Optional;

public interface ConsumerServicePort {

    List<Consumer> listConsumersByFilter(ConsumerFilter filter);

    Consumer findById(Long id);

    Consumer save(Consumer consumer);
}
