package br.com.brn.feedrapi.interceptors;

import br.com.brn.feedrapi.application.domain.User;
import br.com.brn.feedrapi.hibernate.Tenant;
import br.com.brn.feedrapi.security.SecurityConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!Objects.isNull(token)) {
            User user = this.decoderJWT(token);
            Tenant.setIdentificador(user.getClientSchema());
        }
        return true;
    }

    private User decoderJWT(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user;
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
//            user = objectMapper.readValue(body, User.class);
        user = new User();
        user.setClientSchema("CL0001");
        return user;
    }
}
