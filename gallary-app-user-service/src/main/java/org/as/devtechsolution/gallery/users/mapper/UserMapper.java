package org.as.devtechsolution.gallery.users.mapper;

import org.as.devtechsolution.gallery.users.bean.CreateUserReqBean;
import org.as.devtechsolution.gallery.users.bean.CreateUserResBean;
import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper { 
	
	UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
	
	UserDto toUserDtoFromUser(User user);
	UserDto toUserDtoFromBean(CreateUserReqBean userReqBean);
	User toUserFromUserDto(UserDto userDto);
	
	CreateUserResBean toUserResBeanFromUserDto(UserDto userDto);
	
	
 
}
