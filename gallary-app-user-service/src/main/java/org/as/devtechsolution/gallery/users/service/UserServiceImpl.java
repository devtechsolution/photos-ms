package org.as.devtechsolution.gallery.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.as.devtechsolution.gallery.users.bean.AlbumResBean;
import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.entity.UserEntity;
import org.as.devtechsolution.gallery.users.mapper.UserMapper;
import org.as.devtechsolution.gallery.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
	//@Autowired
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Environment environment;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder,Environment environment) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.environment=environment;
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
	
	@Override
	public UserDto getUserByUserId(String userId) {
		
        UserEntity user = userRepository.findByUserId(userId);     
        if(user == null) throw new UsernameNotFoundException("User not found");
        
        UserDto userDto =userMapper.toUserDtoFromUser(user);  
        String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
        
        ResponseEntity<List<AlbumResBean>> albumsListResponse = restTemplate
        		.exchange(albumsUrl, HttpMethod.GET, 
        		null, new ParameterizedTypeReference<List<AlbumResBean>>() {
        });
        List<AlbumResBean> albumsList = albumsListResponse.getBody(); 
        //List<AlbumResBean> albumsList =restTemplate.getForObject(albumsUrl, List.class);
		
        
        
		
		/*
		 * logger.info("Before calling albums Microservice"); List<AlbumResponseModel>
		 * albumsList = albumsServiceClient.getAlbums(userId);
		 * logger.info("After calling albums Microservice");
		 */
		         
		userDto.setAlbums(albumsList);
		
		return userDto;
		//return new UserDto();
	}
	

}
