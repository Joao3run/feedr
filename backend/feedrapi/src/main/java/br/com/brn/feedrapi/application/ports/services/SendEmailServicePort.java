package br.com.brn.feedrapi.application.ports.services;


import br.com.brn.feedrapi.application.domain.models.Email;

import javax.mail.MessagingException;

public interface SendEmailServicePort {

    void sendEmailSmtp(Email email) throws MessagingException;
}
