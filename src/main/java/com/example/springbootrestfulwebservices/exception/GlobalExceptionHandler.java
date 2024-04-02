package com.example.springbootrestfulwebservices.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice  // to handle exception globally 
/* here we will ahndle specific exception and global exception in single place  */
// inorder to customise validation error response  we need to extends ResponseEntityExceptionHandler
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{
    

// to handl specific  exception in  global exception

@ExceptionHandler(ResourceNotFoundException.class)// we need to pass the exception we are going to handle specific exception
public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
   
    /*we need to pass two arguments to this method 
    * 1)type of exception
    *2) web request( WE NEED SOME DETAILS FROM WEB REQUEST )
    */

 /* we need to create the instance of error details */

 ErrorDetails errorDetails = new ErrorDetails(
LocalDateTime.now(),
exception.getMessage(),
webRequest.getDescription(false),"User _not_found");

//if we pass true ,it will fetch client info 

    return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
}





// another specific exception
/*  if user email already exist in db then we need to throw exception with proper error message and status code  */
//EmailAlreadyExistsException

@ExceptionHandler(EmailAlreadyExistsException.class)// we need to pass the exception we are going to handle specific exception
public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception, WebRequest webRequest){
   
    /*we need to pass two arguments to this method 
    * 1)type of exception
    *2) web request( WE NEED SOME DETAILS FROM WEB REQUEST )
    */

 /* we need to create the instance of error details */

 ErrorDetails errorDetails = new ErrorDetails(
LocalDateTime.now(),
exception.getMessage(),
webRequest.getDescription(false),"User_Email _Already_Exists");

//if we pass true ,it will fetch client info 

    return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
}


// run time exception->exception->throwable->object
// excepton is a super class for both checked and unchecked exception

// here  the logic is to handle all the  exceptions other than specific exceptions handled above

// global exception handler 

@ExceptionHandler(Exception.class)// we need to pass the exception we are going to handle specific exception
public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
   
    /*we need to pass two arguments to this method 
    * 1)type of exception
    *2) web request( WE NEED SOME DETAILS FROM WEB REQUEST )
    */

 /* we need to create the instance of error details */

 ErrorDetails errorDetails = new ErrorDetails(
LocalDateTime.now(),
exception.getMessage(),
webRequest.getDescription(false),"Internal_Server_Error");

//if we pass true ,it will fetch client info 

    return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
}




//-> this method can be used to customize the validation errors
//we need to override this method in global exception handler  class and we need to provide implementation


@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                             HttpHeaders headers, 
                                                            HttpStatusCode status,
                                                           WebRequest request) {

             // to store multiple error message in object and will send this object  back to client 
             //to store multiple error messages  we are using map

           Map <String ,String> errors= new HashMap<>();
            List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

             // to get field and  message from this list ,we are using for-each method for iteration

            errorList.forEach((error)->{// we are passing error object,(lambda  expression)
                      String fieldName =((FieldError) error).getField();
                         // we are provide casting for error  object  to get getfield method 

                         // to get corresponding validation error message
                     String message= error.getDefaultMessage();

                         //we need to add this two values to the map 1)fieldName 2)message

                         errors.put(fieldName,message);
                                                             });

                //we stored all the validaton error msgs in this map and we are returing this map  to client

            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    

            
}

}
