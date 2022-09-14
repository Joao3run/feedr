package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.models.Client;
import br.com.brn.feedrapi.application.domain.models.Email;
import br.com.brn.feedrapi.application.domain.models.User;
import br.com.brn.feedrapi.application.exception.DuplicateUserException;
import br.com.brn.feedrapi.application.ports.repositories.UserRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.ClientServicePort;
import br.com.brn.feedrapi.application.ports.services.EmailServicePort;
import br.com.brn.feedrapi.application.ports.services.UserServicePort;
import br.com.brn.feedrapi.application.utils.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    private final ClientServicePort clientServicePort;

    private final PasswordEncoder passwordEncoder;

    private final EmailServicePort emailServicePort;

    public UserService(UserRepositoryPort userRepositoryPort, ClientServicePort clientServicePort, PasswordEncoder passwordEncoder, EmailServicePort emailServicePort) {
        this.userRepositoryPort = userRepositoryPort;
        this.clientServicePort = clientServicePort;
        this.passwordEncoder = passwordEncoder;
        this.emailServicePort = emailServicePort;
    }

    @Override
    public User findByUsername(String username) {
        return userRepositoryPort.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryPort.findAll();
    }

    @Override
    public User save(User user) throws DuplicateUserException {
        user.setCreatAt(new Date());
        User existUser = userRepositoryPort.findByEmail(user.getEmail());
        if (!Objects.isNull(existUser)) {
            throw new DuplicateUserException("Duplicate User");
        }
        if (user.isNew()) {
            user.setPassword(Utils.getRandomHexString(6));
            sendEmailWithPassword(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setUpdateAt(new Date());
        }
        return userRepositoryPort.save(user);

    }

    @Override
    public User findById(Long id) {
        return userRepositoryPort.findById(id);
    }

    private void sendEmailWithPassword(User user) {
        Client client = clientServicePort.findByBase("cl0001");
        String content = client.getParam().getMailTemplateNewUser();
        content = content.replace("[FULLNAME]", user.getFullName());
        content = content.replace("[USERNAME]", user.getEmail());
        content = content.replace("[PASSWORD]", user.getPassword());
        Email email = new Email(0L, client.getBase(), user.getEmail(), "Bem-vindo ao Aplicativo Feedr", content, Instant.now(), false);
        emailServicePort.sendEmail(email);

    }
}
