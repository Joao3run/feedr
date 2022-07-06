package br.com.brn.feedrapi.security;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	private Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilter.class);

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
				response.setHeader("Access-Control-Allow-Origin", "*");
				LoginRequest loginRequest = new LoginRequest(request);
				if (loginRequest.hasLoginData()) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							loginRequest.getUser(), loginRequest.getPassword());
					return authenticationManager.authenticate(authenticationToken);
				} else {
					System.out.println("Invalid Credentials");
					throw new BadCredentialsException("Invalid Credentials");
				}
			}

		} catch (BadCredentialsException e) {
			logger.warn("Invalid Credentials");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
}
