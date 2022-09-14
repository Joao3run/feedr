package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.models.Email;
import br.com.brn.feedrapi.application.ports.repositories.EmailRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.EmailServicePort;
import br.com.brn.feedrapi.application.ports.services.SendEmailServicePort;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.Date;

public class EmailService implements EmailServicePort {

    private final EmailRepositoryPort emailRepositoryPort;
    private final SendEmailServicePort
            sendEmailServicePort;

    public EmailService(final EmailRepositoryPort emailRepositoryPort, final SendEmailServicePort sendEmailServicePort) {
        this.emailRepositoryPort = emailRepositoryPort;
        this.sendEmailServicePort = sendEmailServicePort;
    }

    @Override
    public Email sendEmail(Email email) {
        try {
            sendEmailServicePort.sendEmailSmtp(email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return email;
    }

    public Email save(Email email) {
        return emailRepositoryPort.save(email);
    }
}
