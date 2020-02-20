package org.as.devtechsolution.gallery.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.as.devtechsolution.gallery.users.bean.LoginUserReqBean;
import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserService userService;
	private Environment env;
	
	

	@Autowired
	public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.env = env;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			LoginUserReqBean creds= new ObjectMapper()
					.readValue(request.getInputStream(), LoginUserReqBean.class);
			return getAuthenticationManager().authenticate(
						 new UsernamePasswordAuthenticationToken(
								creds.getEmail(), 
								creds.getPassword(), 
								new ArrayList<>()
								));
						
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		//super.successfulAuthentication(request, response, chain, authResult);
		
		String userName = ((User)authResult.getPrincipal()).getUsername();
		UserDto userDetails = userService.getUserDetailsByEmail(userName);
		String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + 
                		Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret") )
                .compact();
		
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getUserId());
	}
}
