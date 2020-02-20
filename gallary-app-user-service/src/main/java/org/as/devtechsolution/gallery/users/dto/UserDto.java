package org.as.devtechsolution.gallery.users.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 168427766238135658L;
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String password;
	private  String userId;
	private  String encryptedPassword;

	
	
	
}
