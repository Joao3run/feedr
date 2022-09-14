package br.com.brn.feedrapi.application.ports.services;

import br.com.brn.feedrapi.application.domain.models.Email;

public interface EmailServicePort {

    Email sendEmail(Email email);

    Email save(Email email);
}
