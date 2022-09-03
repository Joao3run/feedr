package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;
import br.com.brn.feedrapi.application.domain.models.Communication;

import java.util.List;

public interface CommunicationServicePort {

    List<Communication> listCommunicationsByFilter(CommunicationFilter filter);

    Communication findById(Long id);

}
