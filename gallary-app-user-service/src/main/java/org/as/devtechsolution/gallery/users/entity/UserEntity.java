package org.as.devtechsolution.gallery.users.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="users")
@Data
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7922574351175790809L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=50)
	private  String firstName;
	@Column(nullable=false, length=50)
	private  String lastName;
	@Column(nullable=false, length=100, unique=true)
	private  String email;
	@Column(nullable=false, unique=true)
	private  String userId;
	@Column(nullable=false, unique=true)
	private  String encryptedPassword;


}
