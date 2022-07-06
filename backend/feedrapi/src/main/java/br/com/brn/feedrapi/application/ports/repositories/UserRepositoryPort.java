package br.com.brn.feedrapi.application.ports.repositories;


import br.com.brn.feedrapi.application.domain.FeedrUser;

public interface UserRepositoryPort {

    FeedrUser findByUsername(String username);

}
