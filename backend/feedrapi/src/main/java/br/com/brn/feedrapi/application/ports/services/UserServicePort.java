package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.FeedrUser;

public interface UserServicePort {
    FeedrUser findByUsername(String username);
}
