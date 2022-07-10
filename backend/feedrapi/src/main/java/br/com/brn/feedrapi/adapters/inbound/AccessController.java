package br.com.brn.feedrapi.adapters.inbound;


import br.com.brn.feedrapi.application.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AccessController {

    public static String getClientSchoolSchema() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ObjectMapper objectMapper = new ObjectMapper();
        User user;
        try {
            user = objectMapper.readValue(authentication.getName(), User.class);
        } catch (JsonProcessingException e) {
            return "";
        }
        return user.getClientSchema();
    }

    public static User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ObjectMapper objectMapper = new ObjectMapper();
        User user;
        try {
            user = objectMapper.readValue(authentication.getName(), User.class);
        } catch (JsonProcessingException e) {
            return null;
        }
        return user;
    }

}
