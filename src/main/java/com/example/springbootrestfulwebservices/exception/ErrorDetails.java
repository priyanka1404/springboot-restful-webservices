package com.example.springbootrestfulwebservices.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// error details class to hold of the custom error response
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    
    private LocalDateTime timeStamp;
    private String message;
    private String path;
    private String errorCode;



}
