package org.as.devtechsolution.gallery.users.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateUserResBean {
	
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String userId;



}
