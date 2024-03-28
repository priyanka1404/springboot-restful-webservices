package com.example.springbootrestfulwebservices.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;
import com.example.springbootrestfulwebservices.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




//@Controller
//@ResponseBody
// instead of these two annotations we can use RestController 
@RestController
@AllArgsConstructor
@RequestMapping("api/users")// base url for all methods in the class
public class UserController {

    /* inject UserService 
    * after spring 4.3 
    * we cannot use @autowired 
    * because if a spring bean(class) it has single paramaterized constructor we can omit autowired annotation
    */
    private UserService userService;
    
   
    //build create user REST API

@PostMapping  // to make method as restapi we are using spring annotation

/* ResponseEntity  is  generic class,pass the type */

 public ResponseEntity<User> createUser(@RequestBody User user){


    //@Requestbody:it will convert json to java object,internally it uses http msg converters .
       
       User savedUser = userService.creatUser(user);
       /* this createUser internally save the user object in database and 
       returns the saved user object that we are stored in  user object */

    return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    /* here we are returing the stored object and http status  to client  */

    }


    // build getUserById in Rest API
//http:localhost:8080/api/users/1
    @GetMapping("{id}")  // it maps incomming http request to this method 
    public ResponseEntity<User> getUserById( @PathVariable("id") Long userId){
     // @PathVariable to bind the url template  with method argument 
      User user=userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    // build findAllUsers in Rest API
//http:localhost:8080/api/users
@GetMapping
public ResponseEntity<List<User>> getAllUsers(){
      List<User> users= userService.getAllUsers();
    
    
    return new ResponseEntity<>(users,HttpStatus.OK);
                         
                       
}

// build update user  in Rest API

@PutMapping("{id}")

public ResponseEntity<User> updateUser( @PathVariable("id") Long userId,@RequestBody User user){
    //pathvariable to bi

    //it will update the user info in db and return  updatedUser  

    user.setId(userId);
    //we are using this userid in update user method
    User updatedUser=  userService.updatUser(user);

    return  new ResponseEntity<>(updatedUser,HttpStatus.OK);

    
}
// build delete user in Rest API
@DeleteMapping("{id}")
public ResponseEntity<String>deleteUser(@PathVariable("id") Long userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>("user successfully deleted",HttpStatus.OK);
}









                           /******* USER DTO  **********/
@PostMapping("/dto")
//http://localhost:8080/api/users/dto
public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){


    //@Requestbody:it will convert json to java object,internally it uses http msg converters .
       
       UserDto savedUser = userService.creatUser(userDto);
       /* this createUser internally save the user object in database and 
       returns the saved user object that we are stored in  user object */

    return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    /* here we are returing the stored object and http status  to client  */

    }

 // build getUserById in Rest API
//http:localhost:8080/api/users/1
@GetMapping("/dto/{id}")  // it maps incomming http request to this method 
public ResponseEntity<UserDto> getUserByIdDto( @PathVariable("id") Long userId){
 // @PathVariable to bind the url template  with method argument 
  UserDto userdto=userService.getUserByIdDto(userId);

    return new ResponseEntity<>(userdto, HttpStatus.OK);

}

 // build findAllUsers in Rest API
//http:localhost:8080/api/users/dto
@GetMapping("/dto")
public ResponseEntity<List<UserDto>> getAllUsersDto(){
      List<UserDto> users= userService.getAllUsersDto();
    
    
    return new ResponseEntity<>(users,HttpStatus.OK);
                         
                       
}




}
