package br.com.brn.feedrapi.security;

import br.com.brn.feedrapi.application.domain.User;
import br.com.brn.feedrapi.application.ports.services.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service()
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServicePort userService;

    public UserDetailsServiceImpl(@Lazy UserServicePort userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username);
        }
       return new UserPrincipal(user);
    }
}
