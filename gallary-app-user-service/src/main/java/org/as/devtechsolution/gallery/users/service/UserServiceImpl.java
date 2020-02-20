package org.as.devtechsolution.gallery.users.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.entity.UserEntity;
import org.as.devtechsolution.gallery.users.mapper.UserMapper;
import org.as.devtechsolution.gallery.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	//@Autowired
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}


	@Override
	public UserDto createUser(UserDto userDetails) {
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		UserEntity user= userMapper.toUserFromUserDto(userDetails);
		userRepository.save(user);
		UserDto userDto= userMapper.toUserDtoFromUser(user);
	
		return userDto;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userRepository.findByEmail(username);
		if(userEntity.isPresent()) {
			UserEntity user= userEntity.get();
			return new User(user.getEmail(), user.getEncryptedPassword(), true, true, true, 
					true, new ArrayList<>());
		}else 
			throw new UsernameNotFoundException(username);
		
		
	}


	@Override
	public UserDto getUserDetailsByEmail(String email) {
		Optional<UserEntity> userEntity = userRepository.findByEmail(email);
		if(userEntity.isPresent()) {
			UserEntity user= userEntity.get();
			return userMapper.toUserDtoFromUser(user);
		}else 
			throw new UsernameNotFoundException(email);
	}

}
