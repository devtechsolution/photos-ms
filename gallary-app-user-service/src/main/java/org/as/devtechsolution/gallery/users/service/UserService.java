package org.as.devtechsolution.gallery.users.service;

import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	
	public UserDto createUser(UserDto userDetails);
	public UserDto getUserDetailsByEmail(String email);

}
