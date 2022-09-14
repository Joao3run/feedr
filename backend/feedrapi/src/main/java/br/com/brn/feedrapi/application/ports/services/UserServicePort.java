package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.models.User;
import br.com.brn.feedrapi.application.exception.DuplicateUserException;

import java.util.List;

public interface UserServicePort {
    User findByUsername(String username);

    List<User> findAll();

    User save(User user) throws DuplicateUserException;

    User findById(Long id);
}
