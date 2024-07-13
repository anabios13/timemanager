package com.example.timeTracker.mappers;


import com.example.timeTracker.models.User;
import com.example.timeTracker.DTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
