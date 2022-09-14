package br.com.brn.feedrapi.adapters.outbounds.jobs;

import br.com.brn.feedrapi.adapters.outbounds.persistence.email.JPAEmailRepository;
import br.com.brn.feedrapi.adapters.outbounds.persistence.email.SpringDataEmailRepository;
import br.com.brn.feedrapi.adapters.outbounds.persistence.entities.EmailEntity;
import br.com.brn.feedrapi.application.domain.models.Email;
import br.com.brn.feedrapi.application.ports.jobs.EmailJobPort;
import br.com.brn.feedrapi.application.ports.services.EmailServicePort;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;


@Component
public class EmailJob implements EmailJobPort {
    Logger logger = LoggerFactory.getLogger(EmailJob.class);

    private final SpringDataEmailRepository dataEmailRepository;

    private final JPAEmailRepository emailRepository;

    private final EmailServicePort emailServicePort;

    private final ModelMapper modelMapper;

    public EmailJob(SpringDataEmailRepository dataEmailRepository, JPAEmailRepository emailRepository, EmailServicePort emailServicePort, ModelMapper modelMapper) {
        this.dataEmailRepository = dataEmailRepository;
        this.emailRepository = emailRepository;
        this.emailServicePort = emailServicePort;
        this.modelMapper = modelMapper;
    }

//    @Scheduled(cron = "0 0/1 * * * ?")
    @Override
    public void sendEmail() {
        logger.info("==> START SEND EMAIL JOB");
        // TODO buscar pelo service
        List<EmailEntity> emails = emailRepository.findWithLimit(10);
        if (!emails.isEmpty()) {
            emails.forEach(emailError -> {
                Email email = modelMapper.map(emailError, Email.class);
                emailServicePort.sendEmail(email);
                emailError.setSendDate(Instant.now());
                emailError.setStatus(true);
                dataEmailRepository.save(emailError);
                logger.info("==> SEND EMAIL TO " + email.getTo());
            });
        }
        logger.info("==> END SEND EMAIL JOB");
    }

}
