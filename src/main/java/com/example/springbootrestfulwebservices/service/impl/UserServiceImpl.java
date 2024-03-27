package com.example.springbootrestfulwebservices.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springbootrestfulwebservices.dto.UserDto;
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


    @Override
    public List<User> getAllUsers() {
        
        return  userRepository.findAll();
    }

    @Override
    public User updatUser(User user) {//  user object as a method argument

        //this user object contain all the updated information sent by the client 
        
        
        User  existingUser= userRepository.findById(user.getId()).get();
        // we got the exsisting user object from db and  
        //we will update this existing user and will save back to db 

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser=userRepository.save(existingUser);
        return  updatedUser;
       
    }

    @Override
    public void deleteUser(Long userId) {
          userRepository.deleteById(userId);
        
    
    }

              /****************USER DTO*************/


    @Override
    public UserDto creatUser(UserDto userDto) {
        /*  here the userDto is method argument
        * we need to  store jpa entity in db
        * so here we need to convert userdto to userjpa  entity  object
        */
        
        //convert userDTO to JPA entity object
            User user1 =new User(userDto.getId(),
                                 userDto.getFirstName(),
                                 userDto.getLastName(),
                                 userDto.getEmail());  
// we need to save user jpa entity  object into db by using save method
                User savedUser =  userRepository.save(user1);

                // convert jpa entity into userDto
                //restapi will expect saved user in response it contains primary key
                UserDto savedUserDto =  new UserDto(savedUser.getId(),
                                                    savedUser.getFirstName(),
                                                    savedUser.getLastName(),
                                                    savedUser.getEmail());
        return  savedUserDto;
        
    }
} 
