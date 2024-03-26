package com.example.springbootrestfulwebservices.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springbootrestfulwebservices.entity.User;
import com.example.springbootrestfulwebservices.repository.UserRepository;
import com.example.springbootrestfulwebservices.service.UserService;

import lombok.AllArgsConstructor;


@Service  //define @service annotation  for service class
@AllArgsConstructor
public class UserServiceImpl  implements UserService {

    /* inject UserRepository 
    * after spring 4.3 
    * we cannot use @autowired 
    * because if a spring bean(class) it has single paramaterized constructor we can omit autowired annotation
    */
    private  UserRepository userRepository;

    @Override
    public User creatUser(User user) {  // rest api controller will call createUser method
        // it will save user jpa entity  objectin db by calling save mthod and passing user object
        return  userRepository.save(user); 
    }

    @Override
    public User getUserById(Long userId) {

        Optional<User> optionalUser=userRepository.findById(userId);
       
    return optionalUser.get(); // it retrieves user object by id 
        
    }
    
} 
