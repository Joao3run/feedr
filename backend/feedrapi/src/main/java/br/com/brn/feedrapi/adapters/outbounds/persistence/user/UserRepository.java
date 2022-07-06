package br.com.brn.feedrapi.adapters.outbounds.persistence.user;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.UserEntity;
import br.com.brn.feedrapi.application.domain.FeedrUser;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class UserRepository implements UserRepositoryPort {

    private final SpringDataUserRepository dataUserRepository;

    public UserRepository(SpringDataUserRepository dataUserRepository) {
        this.dataUserRepository = dataUserRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public FeedrUser findByUsername(String username) {
        Optional<UserEntity> userEntity = dataUserRepository.findByUsername(username);
        return modelMapper.map(userEntity, FeedrUser.class);
    }
}
