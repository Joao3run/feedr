package br.com.brn.feedrapi.application.ports.repositories;

import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;
import br.com.brn.feedrapi.application.domain.models.Communication;

import java.util.List;

public interface CommunicationRepositoryPort {

    List<Communication> listCommunicationsByFilter(CommunicationFilter filter);

    Communication findById(Long id);
}
