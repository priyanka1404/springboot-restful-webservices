package com.example.springbootrestfulwebservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRestfulWebservicesApplication {



// we need to configure spring bean of model mapper
@Bean  // it will register in application context
 public ModelMapper modelMapper(){
    return  new ModelMapper();
 }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
