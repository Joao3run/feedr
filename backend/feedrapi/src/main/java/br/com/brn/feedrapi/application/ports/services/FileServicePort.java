package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.models.GlobalFile;

public interface FileServicePort {
    GlobalFile save(GlobalFile globalFile);

    GlobalFile findById(String id);

}
