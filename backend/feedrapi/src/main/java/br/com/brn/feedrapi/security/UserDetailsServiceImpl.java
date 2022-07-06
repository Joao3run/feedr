package br.com.brn.feedrapi.security;

import br.com.brn.feedrapi.application.domain.FeedrUser;
import br.com.brn.feedrapi.application.ports.services.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service()
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserServicePort userService;

    @Autowired
    public UserDetailsServiceImpl(UserServicePort userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FeedrUser feedrUser = userService.findByUsername(username);
        if (Objects.isNull(feedrUser)) {
            throw new UsernameNotFoundException(username);
        }
       return new UserPrincipal(feedrUser);
    }
}
