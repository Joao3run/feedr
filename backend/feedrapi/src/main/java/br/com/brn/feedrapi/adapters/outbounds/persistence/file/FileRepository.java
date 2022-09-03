package br.com.brn.feedrapi.adapters.outbounds.persistence.file;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.GlobalFileEntity;
import br.com.brn.feedrapi.application.domain.models.GlobalFile;
import br.com.brn.feedrapi.application.ports.repositories.FileRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class FileRepository implements FileRepositoryPort {

    private final SpringDataFileRepository dataFileRepository;

    private final ModelMapper modelMapper;

    public FileRepository(SpringDataFileRepository dataFileRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ModelMapper modelMapper1) {
        this.dataFileRepository = dataFileRepository;
        this.modelMapper = modelMapper1;
    }

    @Override
    public GlobalFile save(GlobalFile globalFile) {
        GlobalFileEntity globalFileEntity = modelMapper.map(globalFile, GlobalFileEntity.class);
        globalFileEntity = dataFileRepository.save(globalFileEntity);
        return modelMapper.map(globalFileEntity, GlobalFile.class);

    }

    @Override
    public GlobalFile findById(String id) {
        Optional<GlobalFileEntity> globalFileEntity = dataFileRepository.findById(id);
        return modelMapper.map(globalFileEntity, GlobalFile.class);
    }
}
