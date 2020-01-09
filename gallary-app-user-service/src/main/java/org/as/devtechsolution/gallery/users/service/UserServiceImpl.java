package org.as.devtechsolution.gallery.users.service;

import java.util.UUID;

import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.entity.User;
import org.as.devtechsolution.gallery.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
  
	@Override
	public UserDto createUser(UserDto userDetails) {
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper= new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		User user = modelMapper.map(userDetails, User.class);
		//user.setEncryptedPassword("Test");
		UserRepository.save(user);
		UserDto dto= modelMapper.map(user, UserDto.class);
	
		return dto;
	}

}
