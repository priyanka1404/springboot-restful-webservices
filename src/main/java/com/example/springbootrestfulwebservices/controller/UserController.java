package com.example.springbootrestfulwebservices.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;
import com.example.springbootrestfulwebservices.exception.ErrorDetails;
import com.example.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.example.springbootrestfulwebservices.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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


// swagger rest api annotation
@Tag(  //Customizing Swagger API Documentation with Annotation
    name = "crud rest api for user resource",
    description =" CRUD REST API's -create user,update user,get user,get all users,delete user"
)
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

@Operation(
    summary = "Delete   user Rest API",
    description =" Delete  user By  ID Rest API is used to delete particular  user from the  database"
)

@ApiResponse(
    responseCode = "200",
    description = "Http Status  200  successful" 
)
// build delete user in Rest API
@DeleteMapping("{id}")
public ResponseEntity<String>deleteUser(@PathVariable("id") Long userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>("user successfully deleted",HttpStatus.OK);
}









                           /******* USER DTO  **********/


@Operation(  // swagger rest api annotation
    summary = "create user Rest API",
    description =" create user Rest API is used to save user in database"
)

@ApiResponse(
    responseCode = "201",
    description = "Http Status  201 CREATED" 
)
@PostMapping("/dto")
//http://localhost:8080/api/users/dto
public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
  // #validation:create user rest api takes userdto as a  request  object,we need to  add java bean validation annotation
 // adding @valid annotation to enable validation for userdto class

    //@Requestbody:it will convert json to java object,internally it uses http msg converters .
       
       UserDto savedUser = userService.creatUser(userDto);
       /* this createUser internally save the user object in database and 
       returns the saved user object that we are stored in  user object */

    return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    /* here we are returing the stored object and http status  to client  */

    }

 // build getUserById in Rest API
//http:localhost:8080/api/users/dto/1

@Operation(
    summary = "Get  user Rest API",
    description =" Get user By  ID Rest API is used to get single  user from  database"
)

@ApiResponse(
    responseCode = "200",
    description = "Http Status  200  successful" 
)
@GetMapping("/dto/{id}")  // it maps incoming http request to this method 
public ResponseEntity<UserDto> getUserByIdDto( @PathVariable("id") Long userId){
 // @PathVariable to bind the url template  with method argument 
  UserDto userdto=userService.getUserByIdDto(userId);

    return new ResponseEntity<>(userdto, HttpStatus.OK);

}

 // build findAllUsers in Rest API
//http:localhost:8080/api/users/dto

@Operation(
    summary = "Get all user Rest API",
    description =" Get  all users from  Rest API is used to get single  user from  database"
)

@ApiResponse(
    responseCode = "200",
    description = "Http Status  200  successful" 
)
@GetMapping("/dto")
public ResponseEntity<List<UserDto>> getAllUsersDto(){
      List<UserDto> users= userService.getAllUsersDto();
    
    
    return new ResponseEntity<>(users,HttpStatus.OK);
                         
                       
}

// build update user  in Rest API

@Operation( 
    summary = "update   user Rest API",
    description =" update  user By  ID Rest API is used to update particular user in   database"
)

@ApiResponse(
    responseCode = "200",
    description = "Http Status  200  successful" 
)
@PutMapping("/dto/{id}")

public ResponseEntity<UserDto> updateUserDto( @PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto){
    //pathvariable 

    // adding @valid annotation to enable validation for userdto class
    // #validation:update  user rest api takes userdto as a  request  object,we need to  add java bean validation annotation


    //it will update the user info in db and return  updatedUser  

    userDto.setId(userId);
    //we are using this userid in update user method
    UserDto  updatedUser=  userService.updatUserDto(userDto);

    return  new ResponseEntity<>(updatedUser,HttpStatus.OK);

    
}

// to handle specific exception

//@ExceptionHandler(ResourceNotFoundException.class)// we need to pass the exception we are going to handle specific exception
//public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
   
    /*we need to pass two arguments to this method 
    * 1)type of exception
    *2) web request( WE NEED SOME DETAILS FROM WEB REQUEST )
    */

 /* we need to create the instance of error details */

 //ErrorDetails errorDetails = new ErrorDetails(
//LocalDateTime.now(),
//exception.getMessage(),
//webRequest.getDescription(false),"User _not_found");

//if we pass true ,it will fetch client info 

  //  return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//}


}
