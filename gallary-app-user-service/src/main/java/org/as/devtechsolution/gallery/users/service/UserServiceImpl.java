package org.as.devtechsolution.gallery.users.service;

import java.util.UUID;

import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.entity.User;
import org.as.devtechsolution.gallery.users.mapper.UserMapper;
import org.as.devtechsolution.gallery.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	//@Autowired
	private final UserRepository UserRepository;
	private final UserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(org.as.devtechsolution.gallery.users.repository.UserRepository userRepository,
			UserMapper userMapper) {
		UserRepository = userRepository;
		this.userMapper = userMapper;
	}


	@Override
	public UserDto createUser(UserDto userDetails) {
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
//		ModelMapper modelMapper= new ModelMapper(); 
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		
//		
//		
//		User user = modelMapper.map(userDetails, User.class);
		User user= userMapper.toUserFromUserDto(userDetails);
		//user.setEncryptedPassword("Test");
		UserRepository.save(user);
		//UserDto dto= modelMapper.map(user, UserDto.class);
		UserDto userDto= userMapper.toUserDtoFromUser(user);
	
		return userDto;
	}

}
