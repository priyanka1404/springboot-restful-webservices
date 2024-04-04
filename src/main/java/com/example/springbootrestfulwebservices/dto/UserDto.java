package com.example.springbootrestfulwebservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema( //// swagger rest api annotation
    description="userDto model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;

    // user firstname should not be null/empty

    @Schema(
    description="User first name"
)
    // customize validaton error message  for firstname
    @NotEmpty (message="user firstName should not be null or empty ")
    private String firstName;
    

    @Schema(
    description="User last name"
)
     // user lastname should not be null/empty
    // customize validaton error message  for lastname
    @NotEmpty(message="user lastName should not be null or empty ")
    private String lastName;


    @Schema(
    description="User email"
)
     // user Email should not be null/empty
     // customize validaton error message  for email
    @NotEmpty(message="user email should not be null or empty ")
    // Email address should be valid 
    @Email (message="email address should be valid ") 
    private String email;
}
