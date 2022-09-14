package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import br.com.brn.feedrapi.application.domain.models.Client;
import br.com.brn.feedrapi.application.domain.models.Consumer;
import br.com.brn.feedrapi.application.domain.models.Email;
import br.com.brn.feedrapi.application.domain.models.User;
import br.com.brn.feedrapi.application.exception.DuplicateUserException;
import br.com.brn.feedrapi.application.ports.repositories.ConsumerRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.ClientServicePort;
import br.com.brn.feedrapi.application.ports.services.ConsumerServicePort;
import br.com.brn.feedrapi.application.ports.services.EmailServicePort;
import br.com.brn.feedrapi.application.utils.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class ConsumerService implements ConsumerServicePort {

    private final ConsumerRepositoryPort consumerRepository;

    private final ClientServicePort clientServicePort;


    private final PasswordEncoder passwordEncoder;

    private final EmailServicePort emailServicePort;

    public ConsumerService(ConsumerRepositoryPort consumerRepository, ClientServicePort clientServicePort, PasswordEncoder passwordEncoder, EmailServicePort emailServicePort) {
        this.consumerRepository = consumerRepository;
        this.clientServicePort = clientServicePort;
        this.passwordEncoder = passwordEncoder;
        this.emailServicePort = emailServicePort;
    }

    @Override
    public List<Consumer> listConsumersByFilter(ConsumerFilter filter) {
        return consumerRepository.listConsumersByFilter(filter);
    }

    @Override
    public Consumer findById(Long id) {
        return consumerRepository.findById(id);
    }

    @Override
    public Consumer save(Consumer consumer) throws DuplicateUserException {
        Consumer existingConsumer = consumerRepository.findByEmail(consumer.getEmail());
        if (!Objects.isNull(existingConsumer)) {
            throw  new DuplicateUserException();
        }
        if (consumer.isNew()) {
            consumer.setPassword(Utils.getRandomHexString(8));
            sendEmailWithPassword(consumer);
            consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        }
        return consumerRepository.save(consumer);
    }

    private void sendEmailWithPassword(Consumer consumer) {
        Client client = clientServicePort.findByBase("cl0001");
        String content = client.getParam().getMailTemplateNewUser();
        content = content.replace("[FULLNAME]", consumer.getName());
        content = content.replace("[USERNAME]", consumer.getEmail());
        content = content.replace("[PASSWORD]", consumer.getPassword());
        Email email = new Email(0L, client.getBase(), consumer.getEmail(), "Bem-vindo ao Feedr", content, Instant.now(), false);
        emailServicePort.sendEmail(email);

    }

}
