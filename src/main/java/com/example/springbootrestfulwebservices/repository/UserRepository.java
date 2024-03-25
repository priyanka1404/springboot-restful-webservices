package com.example.springbootrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootrestfulwebservices.entity.User;

import jakarta.transaction.Transactional;

//to perform crud operations on user 
/*JPA repository is an generic interface  it is from springframework 
we  need to pass two arguments one is type of entity(User) and type of primary key (Long)*/

//jpa repo will provide all crud methods for users entity
//no need to implement methods for userRepository

/*
*Jpa repo internally extends crud repository interface
*implementation  for crud repo  interface available in  
JpaRepository interface-> SimpleJpaRepository.class -> which implements ->JpaRepositoryImplementation interface->internally extends
JpaRepository interface .

* for User Repository we no need to specify @Repository annotation because its already defined in  SimpleJpaRepository.class
*for User Repository we no need to specify @Transactional annotation because its all the merthods  in  SimpleJpaRepository.class are  transactional


*/

//@Repository
//@Transactional
public interface UserRepository  extends JpaRepository<User,Long>{

}
