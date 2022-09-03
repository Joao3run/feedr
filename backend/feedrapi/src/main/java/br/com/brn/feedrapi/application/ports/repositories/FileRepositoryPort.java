package br.com.brn.feedrapi.application.ports.repositories;

import br.com.brn.feedrapi.application.domain.models.GlobalFile;

public interface FileRepositoryPort {
    GlobalFile save(GlobalFile globalFile);

    GlobalFile findById(String id);

}
