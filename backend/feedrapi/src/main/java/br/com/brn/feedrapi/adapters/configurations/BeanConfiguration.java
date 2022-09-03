package br.com.brn.feedrapi.adapters.configurations;

import br.com.brn.feedrapi.FeedrapiApplication;
import br.com.brn.feedrapi.adapters.outbounds.persistence.consumer.JPAConsumerRepository;
import br.com.brn.feedrapi.adapters.outbounds.persistence.consumer.JPAConsumerRepositoryImpl;
import br.com.brn.feedrapi.application.ports.repositories.CommunicationRepositoryPort;
import br.com.brn.feedrapi.application.ports.repositories.ConsumerRepositoryPort;
import br.com.brn.feedrapi.application.ports.repositories.FileRepositoryPort;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import br.com.brn.feedrapi.application.services.CommunicationService;
import br.com.brn.feedrapi.application.services.ConsumerService;
import br.com.brn.feedrapi.application.services.FileService;
import br.com.brn.feedrapi.application.services.UserService;
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
    ConsumerService consumerService(ConsumerRepositoryPort repository) {
        return new ConsumerService(repository);
    }

    @Bean
    CommunicationService communicationService(CommunicationRepositoryPort repository) {
        return new CommunicationService(repository);
    }

    @Bean
    UserService userService(UserRepositoryPort repository) {
        return new UserService(repository, passwordEncoder());
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

}
