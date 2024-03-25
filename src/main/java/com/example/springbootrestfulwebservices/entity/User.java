package com.example.springbootrestfulwebservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// with this entity hibernate will automatically create table in db with this variables as fields(columns)

// here we are using lombook annotation,so no eed of creating  setter method ,getter methods  seprately
// it will reduce boiler plate code

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity   // it species this class has an jpa entity 
@Table(name="users")//if we dont use table annotation  ,JPA will create table by using class name.

public class User {

    @Id // to configure primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// this  is for primary key generation strategy 
    /* Auto,sequence,identity,table->
    among this identity internally  uses  auto increament features to  increament the primary key  */
   
   
    private Long id;

    @Column( nullable = false)// to customize the coulmn,to make it this should be  not null
    private String firstName;  /* jpa will automatically  create the coulmn name in db as first_name when it combined with two words
      so need to mention coulmn name in @column*/

    @Column(nullable = false)
    private String lastName;

    @Column(nullable= false,unique = true) // to make this email column as unique
    private String email;



    
}
