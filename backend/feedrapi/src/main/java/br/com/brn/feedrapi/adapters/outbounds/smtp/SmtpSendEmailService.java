package br.com.brn.feedrapi.adapters.outbounds.smtp;

import br.com.brn.feedrapi.application.domain.models.Email;
import br.com.brn.feedrapi.application.ports.services.SendEmailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SmtpSendEmailService implements SendEmailServicePort {

    @Autowired
    JavaMailSender emailSender;

    @Override
    @Async
    public void sendEmailSmtp(Email email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
        message.setContent(email.getContent(), "text/html;charset=utf-8");
        messageHelper.setSubject(email.getSubject());
        messageHelper.setTo(email.getTo());
        emailSender.send(message);
    }
}
