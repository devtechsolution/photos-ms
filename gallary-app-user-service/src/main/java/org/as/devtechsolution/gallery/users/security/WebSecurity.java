package org.as.devtechsolution.gallery.users.security;

import javax.servlet.Filter;

import org.as.devtechsolution.gallery.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private Environment env;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.env = env;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/users/**").permitAll();
		// To Allow only IP address of Zuul API Gateway instead of users
		http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
		.and()
		.addFilter(getAuthenticationFilter());
		
		http.headers().frameOptions().disable();//Disable to show h2-console
	}

	private Filter getAuthenticationFilter() throws Exception {
		/*
		 * AuthenticationFilter authenticationFilter= new AuthenticationFilter();
		 * authenticationFilter.setAuthenticationManager(authenticationManager());
		 */
		AuthenticationFilter authenticationFilter= new AuthenticationFilter(userService,env,authenticationManager());
		authenticationFilter.setFilterProcessesUrl(env.getProperty("loginurl.path"));
		
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

}
