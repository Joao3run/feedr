package br.com.brn.feedrapi.adapters.outbounds.persistence.email;

import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.EmailEntity;
import br.com.brn.feedrapi.application.domain.models.Email;
import br.com.brn.feedrapi.application.ports.repositories.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailRepository implements EmailRepositoryPort {

    private final JPAEmailRepository jpaEmailRepository;

    private final SpringDataEmailRepository dataEmailRepository;

    private final ModelMapper modelMapper;

    public EmailRepository(JPAEmailRepository jpaEmailRepository, SpringDataEmailRepository dataEmailRepository, ModelMapper modelMapper) {
        this.jpaEmailRepository = jpaEmailRepository;
        this.dataEmailRepository = dataEmailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Email save(Email email) {
        EmailEntity emailEntity = modelMapper.map(email, EmailEntity.class);
        emailEntity = dataEmailRepository.save(emailEntity);
        return modelMapper.map(emailEntity, Email.class);
    }
}
