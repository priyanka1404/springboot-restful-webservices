package com.example.springbootrestfulwebservices.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;
//import com.example.springbootrestfulwebservices.mapper.UserMapper;
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
    private ModelMapper modelMapper;

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
/**********REFACTORING THE  methods to use MODEL Mapper  class   ********/


@Override
public UserDto creatUser(UserDto userDto) {
    /*  here the userDto is method argument
    * we need to  store jpa entity in db
    * so here we need to convert userdto to userjpa  entity  object
    */
    
 // 1)INSTEAD OF WRITING THIS LOGIC FOR EVERY MAPPING WE ARE GOING TO USE <MAPPER CLASS> WHERE IT HOLD CONVERSION LOGIC METHODS
    //convert userDTO to JPA entity object
     /* ----------- User user1 =new User(userDto.getId(),
                             userDto.getFirstName(),
                             userDto.getLastName(),
                             userDto.getEmail());  ----------------------*/
//2)mapper  class method
/*-- User user = UserMapper.mapToUser(userDto);--*/

// 3) model mapper 
User user = modelMapper.map(userDto,User.class);


// we need to save user jpa entity  object into db by using save method
            
      /*--------User savedUser =  userRepository.save(user1);--------*/
      User savedUser = userRepository.save(user);
            // convert jpa entity into userDto
            // 1) restapi will expect saved user in response it contains primary key
            /*---------------UserDto savedUserDto =  new UserDto(savedUser.getId(),
                                                savedUser.getFirstName(),
                                                savedUser.getLastName(),
                                                savedUser.getEmail());-----------------*/


     // 2)mapper class method
     /***---UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);---***/
     
     
     // 3) model mapper 

     UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
     
    return  savedUserDto;
    
}

@Override
public UserDto getUserByIdDto(Long userId) {

    Optional<User> optionalUser=userRepository.findById(userId);
   
    User user = optionalUser.get(); // it retrieves user object by id 
/****------2) return UserMapper.mapToUserDto(user);-------*******/
  return modelMapper.map(user, UserDto.class);
    
}

@Override
public List<UserDto> getAllUsersDto() {
   List<User> users= userRepository.findAll();

   /****------2) return  users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList()); ****/

   //3)model mapper
   return  users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());


    // to convert  jpa to dto
}

@Override
public UserDto updatUserDto(UserDto userDto) {//  user object as a method argument

    //this user object contain all the updated information sent by the client 
    
    
    User  existingUser= userRepository.findById(userDto.getId()).get();
    // we got the exsisting user object from db and  
    //we will update this existing user and will save back to db 

    existingUser.setFirstName(userDto.getFirstName());
    existingUser.setLastName(userDto.getLastName());
    existingUser.setEmail(userDto.getEmail());
    User updatedUser=userRepository.save(existingUser);

    /****--2)---return  UserMapper.mapToUserDto(updatedUser);-----*/

    //3)modelmapper
    return  modelMapper.map(updatedUser ,UserDto.class);
   
}


} 
