package br.com.brn.feedrapi.adapters.configurations;

import br.com.brn.feedrapi.FeedrapiApplication;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import br.com.brn.feedrapi.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = FeedrapiApplication.class)
public class BeanConfiguration {

    @Bean
    UserService userService(UserRepositoryPort repository) {
        return new UserService(repository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
