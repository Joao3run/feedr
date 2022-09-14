package br.com.brn.feedrapi.application.ports.repositories;

import br.com.brn.feedrapi.application.domain.models.User;

import java.util.List;

public interface UserRepositoryPort {

    User findByUsername(String username);

    List<User> findAll();

    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

}
