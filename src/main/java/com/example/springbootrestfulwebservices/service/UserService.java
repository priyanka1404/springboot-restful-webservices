package com.example.springbootrestfulwebservices.service;

import java.util.List;

import com.example.springbootrestfulwebservices.entity.User;

public interface UserService {

        //create a  createUser method, return type is User


        User creatUser(User user); // pass the entity as  argument
        User getUserById(Long userId);

        List<User> getAllUsers();

 }
