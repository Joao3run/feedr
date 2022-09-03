package br.com.brn.feedrapi.adapters.inbound.controllers;


import br.com.brn.feedrapi.application.domain.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccessController {

    public static String getClientSchema() {
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
