package com.myeventsapp.user.mapper;

import com.myeventsapp.user.UserEntity;
import com.myeventsapp.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(UserEntity e);
}
