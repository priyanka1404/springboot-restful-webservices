package com.example.springbootrestfulwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;

    // user firstname should not be null/empty

    // customize validaton error message  for firstname
    @NotEmpty (message="user firstName should not be null or empty ")
    private String firstName;
    

     // user lastname should not be null/empty
    // customize validaton error message  for lastname
    @NotEmpty(message="user lastName should not be null or empty ")
    private String lastName;

     // user Email should not be null/empty
     // customize validaton error message  for email
    @NotEmpty(message="user email should not be null or empty ")
    // Email address should be valid 
    @Email (message="email address should be valid ") 
    private String email;
}
