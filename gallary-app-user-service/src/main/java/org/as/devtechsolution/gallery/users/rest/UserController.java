package org.as.devtechsolution.gallery.users.rest;

import javax.validation.Valid;

import org.as.devtechsolution.gallery.users.bean.CreateUserReqBean;
import org.as.devtechsolution.gallery.users.bean.CreateUserResBean;
import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Data
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment env;
	@Autowired
	private UserService userService;
	//UserMapper mapper;
	
	/*
	 * @Autowired public void setMapper(UserMapper mapper) { this.mapper = mapper; }
	 */
	 
	
	@GetMapping("/status/check")
	public String status() {
		return "working on port: "+ env.getProperty("local.server.port");
	}
	
	@PostMapping
	public ResponseEntity<CreateUserResBean> createUser(@Valid @RequestBody CreateUserReqBean userReqBean) {
		
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userReqBean, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		
		CreateUserResBean body = modelMapper.map(createdUser, CreateUserResBean.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

}
