package org.as.devtechsolution.gallery.users.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginUserReqBean {
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be equal or grater than 8 characters and less than 16 characters")
	private String password;
	
	@NotNull(message="Email cannot be null")
	@Email
	private String email;


}
