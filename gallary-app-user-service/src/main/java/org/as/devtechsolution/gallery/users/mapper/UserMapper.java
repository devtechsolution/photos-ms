package org.as.devtechsolution.gallery.users.mapper;

import org.as.devtechsolution.gallery.users.bean.CreateUserReqBean;
import org.as.devtechsolution.gallery.users.bean.CreateUserResBean;
import org.as.devtechsolution.gallery.users.bean.UserResBean;
import org.as.devtechsolution.gallery.users.dto.UserDto;
import org.as.devtechsolution.gallery.users.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper { 
	
	UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
	
	UserDto toUserDtoFromUser(UserEntity user);
	UserDto toUserDtoFromBean(CreateUserReqBean userReqBean);
	UserEntity toUserFromUserDto(UserDto userDto);
	
	CreateUserResBean toUserResBeanFromUserDto(UserDto userDto);
	
	UserResBean toUserResBeansFromUserDto(UserDto dto);
	
	
 
}
