package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.User;

import java.util.List;

public interface UserServicePort {
    User findByUsername(String username);
    List<User> findAll();
    User save(User user);
}
