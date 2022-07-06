package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.FeedrUser;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.UserServicePort;

public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public FeedrUser findByUsername(String username) {
        return userRepositoryPort.findByUsername(username);
    }
}
