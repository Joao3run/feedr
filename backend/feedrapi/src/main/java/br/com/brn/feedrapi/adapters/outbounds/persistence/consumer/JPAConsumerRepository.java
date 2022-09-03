package br.com.brn.feedrapi.adapters.outbounds.persistence.consumer;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.ConsumerEntity;
import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAConsumerRepository {

    List<ConsumerEntity> listConsumersByFilter(ConsumerFilter filter);
}
