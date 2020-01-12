package org.as.devtechsolution.gallery.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/users/**").permitAll();
		// To Allow only IP address of Zuul API Gateway instead of users
		//http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"));
		
		http.headers().frameOptions().disable();//Disable to show h2-console
	}

}
