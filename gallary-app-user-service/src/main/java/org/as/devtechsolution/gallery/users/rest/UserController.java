package org.as.devtechsolution.gallery.users.rest;

import javax.validation.Valid;

import org.as.devtechsolution.gallery.users.bean.CreateUserReqBean;
import org.as.devtechsolution.gallery.users.bean.CreateUserResBean;
import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.mapper.UserMapper;
import org.as.devtechsolution.gallery.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment env;
	@Autowired
	private UserService userService;
	
	private final UserMapper userMapper;
	
	public UserController(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	
	@GetMapping("/status/check")
	public String status() {
		
		return "working on port: "+ 
		env.getProperty("local.server.port")+", with token=" +  env.getProperty("token.secret");
	}
	
	
	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)

	public ResponseEntity<CreateUserResBean> createUser(@Valid @RequestBody CreateUserReqBean userReqBean) {

		UserDto userDto= userMapper.toUserDtoFromBean(userReqBean);
		UserDto createdUser = userService.createUser(userDto);
		CreateUserResBean body= userMapper.toUserResBeanFromUserDto(createdUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

}
