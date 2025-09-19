package com.tech2tech.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.tech2tech.store.dtos.RegisterUserRequest;
import com.tech2tech.store.dtos.UpdateUserRequest;
import com.tech2tech.store.dtos.UserDto;
import com.tech2tech.store.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
   
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);

}
