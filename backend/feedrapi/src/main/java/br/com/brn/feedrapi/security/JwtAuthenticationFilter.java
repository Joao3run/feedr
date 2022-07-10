package br.com.brn.feedrapi.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Value("${secret-token}")
	private String secret;

	private final AuthenticationManager authenticationManager;

	private Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilter.class);

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		LoginRequest loginRequest = new LoginRequest(request);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getUser(), loginRequest.getPassword());
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											FilterChain filterChain, Authentication authentication) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		UserPrincipal user = ((UserPrincipal) authentication.getPrincipal());
		byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			LocalDate nowPlusYear = LocalDate.now().plusYears(1);
			String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
					.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE).setIssuer(SecurityConstants.TOKEN_ISSUER)
					.setAudience(SecurityConstants.TOKEN_AUDIENCE)
					.setSubject(objectMapper.writeValueAsString(user.getUser()))
					.setExpiration(new Date(nowPlusYear.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()))
					.compact();
			response.getWriter().write(SecurityConstants.TOKEN_PREFIX + token);
			response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.TEXT_PLAIN_VALUE);
		response.getWriter().print("Invalid Credentials");
		response.getWriter().flush();
	}
}
