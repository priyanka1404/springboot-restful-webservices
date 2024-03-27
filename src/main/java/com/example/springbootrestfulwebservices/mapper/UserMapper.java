package com.example.springbootrestfulwebservices.mapper;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;

public class UserMapper {

    // creating to static methods

// to convert jpa to DTO 
    public static UserDto mapToUserDto(User user) {
        UserDto userDto =  new UserDto(user.getId(),
         user.getFirstName(),
         user.getLastName(),
         user.getEmail());
        return userDto;
        
    }

    // to convert DTO to jpa

    public static User mapToUser(UserDto userDto){
        User user = new User(userDto.getId(),
                            userDto.getFirstName(),
                            userDto.getLastName(),
                            userDto.getEmail());
        
        
        return user;

    }
    
}
