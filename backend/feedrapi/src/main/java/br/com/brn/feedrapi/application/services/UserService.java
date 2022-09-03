package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.models.User;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.UserServicePort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepositoryPort.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryPort.findAll();
    }

    @Override
    public User save(User user) {
        user.setCreatAt(new Date());
        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setUpdateAt(new Date());
        }
        return userRepositoryPort.save(user);
    }
}
