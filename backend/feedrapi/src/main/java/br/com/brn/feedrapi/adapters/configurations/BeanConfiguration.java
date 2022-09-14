package br.com.brn.feedrapi.adapters.configurations;

import br.com.brn.feedrapi.FeedrapiApplication;
import br.com.brn.feedrapi.adapters.outbounds.persistence.communication.JPACommunicationRepository;
import br.com.brn.feedrapi.adapters.outbounds.persistence.communication.JPACommunicationRepositoryImpl;
import br.com.brn.feedrapi.adapters.outbounds.persistence.consumer.JPAConsumerRepository;
import br.com.brn.feedrapi.adapters.outbounds.persistence.consumer.JPAConsumerRepositoryImpl;
import br.com.brn.feedrapi.adapters.outbounds.persistence.email.JPAEmailRepository;
import br.com.brn.feedrapi.adapters.outbounds.persistence.email.JPAEmailRepositoryImpl;
import br.com.brn.feedrapi.application.ports.repositories.*;
import br.com.brn.feedrapi.application.ports.services.ClientServicePort;
import br.com.brn.feedrapi.application.ports.services.EmailServicePort;
import br.com.brn.feedrapi.application.ports.services.SendEmailServicePort;
import br.com.brn.feedrapi.application.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan(basePackageClasses = FeedrapiApplication.class)
public class BeanConfiguration {

    @Bean
    FileService fileService(FileRepositoryPort repository) {
        return new FileService(repository);
    }

    @Bean
    ConsumerService consumerService(ConsumerRepositoryPort repository, ClientServicePort clientServicePort, EmailServicePort emailServicePort) {
        return new ConsumerService(repository, clientServicePort, passwordEncoder(), emailServicePort);
    }

    @Bean
    CommunicationService communicationService(CommunicationRepositoryPort repository) {
        return new CommunicationService(repository);
    }

    @Bean
    UserService userService(UserRepositoryPort repository, ClientServicePort clientServicePort, EmailServicePort servicePort) {
        return new UserService(repository, clientServicePort, passwordEncoder(), servicePort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JPAConsumerRepository jpaConsumerRepository(EntityManager entityManager) {
        return new JPAConsumerRepositoryImpl(entityManager);
    }

    @Bean
    public JPACommunicationRepository jpaCommunicationRepository(EntityManager entityManager) {
        return new JPACommunicationRepositoryImpl(entityManager);
    }

    @Bean
    public JPAEmailRepository jpaEmailRepository(EntityManager entityManager) {
        return new JPAEmailRepositoryImpl(entityManager);
    }

    @Bean
    EmailService emailService(EmailRepositoryPort repository, SendEmailServicePort sendEmailServicePort) {
        return new EmailService(repository, sendEmailServicePort);
    }

    @Bean
    ClientService clientService(ClientRepositoryPort repository) {
        return new ClientService(repository);
    }

}
