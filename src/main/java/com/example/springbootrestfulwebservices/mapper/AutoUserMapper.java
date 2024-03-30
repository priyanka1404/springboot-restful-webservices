package com.example.springbootrestfulwebservices.mapper;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.springbootrestfulwebservices.dto.UserDto;
import com.example.springbootrestfulwebservices.entity.User;

@Mapper
public interface AutoUserMapper {

    // we need to create  mapstruct methods 

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    //we can use this Mapper instance to call this mapping methods

    UserDto mapToUserDto(User user);  //dto -> jpa
    User mapToUser(UserDto userDto);  //jpa->dto

    // map struct will provide implementation during compilation time 
    // no need to write code to implement these methods


    // inorder to map one object to another objects both fields same field name
    //if both are not same we can use map struct(@mapping) annotation
/*example 
   * @Mapping(source="email",target="emailAddress")
   * src=jpa
   * target=dto
   */
    

   /* we cannot create object  for interface,here in mapstruct no need to provide implementation
   inorder  to  provide implementation for this interface  we  need to use mapper utility class */
     

}
