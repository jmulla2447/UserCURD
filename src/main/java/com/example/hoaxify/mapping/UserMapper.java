package com.example.hoaxify.mapping;

import com.example.hoaxify.dto.UserDto;
import com.example.hoaxify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(UserDto userDto);

    UserDto entityToDto(User user);
}
