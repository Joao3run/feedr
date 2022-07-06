package br.com.brn.feedrapi.security;


import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginRequest {

    private String user;
    private String password;

    public LoginRequest(String requestData) {
        if (requestData.indexOf("user=") >= 0 && requestData.indexOf("password=") > 0) {
            String loginData = requestData;
            String[] loginPart = loginData.split("&");
            String[] userPart = loginPart[0].split("%40");
            this.user = userPart[0].replace("login=", "");
            this.password = loginPart[1].replace("password=", "");
            ;
        }
    }

    public LoginRequest(HttpServletRequest request) {
        this.user = request.getParameter("user").trim().toLowerCase();
        this.password = request.getParameter("password").trim().toLowerCase();

    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public boolean hasLoginData() {
        return StringUtils.hasText(this.user) && StringUtils.hasText(this.password);

    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

}
