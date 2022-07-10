package br.com.brn.feedrapi.adapters.outbounds.persistence.user;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.UserEntity;
import br.com.brn.feedrapi.application.domain.User;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class UserRepository implements UserRepositoryPort {

    private final SpringDataUserRepository dataUserRepository;

    private final ModelMapper modelMapper;

    public UserRepository(SpringDataUserRepository dataUserRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ModelMapper modelMapper1) {
        this.dataUserRepository = dataUserRepository;
        this.modelMapper = modelMapper1;
    }



    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> userEntity = dataUserRepository.findByUsername(username);
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> users = dataUserRepository.findAll();
        return users
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity = dataUserRepository.save(userEntity);
        return modelMapper.map(userEntity, User.class);
    }
}
