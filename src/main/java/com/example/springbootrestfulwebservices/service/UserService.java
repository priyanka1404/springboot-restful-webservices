package com.example.springbootrestfulwebservices.service;

import java.util.List;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;

public interface UserService {

        //create a  createUser method, return type is User


        User creatUser(User user); // pass the entity as  argument
        User getUserById(Long userId);

        List<User> getAllUsers();
        User updatUser(User user);
        void deleteUser(Long userId);





        /******** UserDTO ********/
        UserDto creatUser(UserDto userDto);// we have changed return type and method argument to userDTO 
        UserDto getUserByIdDto(Long userId);
        List<UserDto> getAllUsersDto();
        UserDto updatUserDto(UserDto userDto);
 }
