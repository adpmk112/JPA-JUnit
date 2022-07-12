package com.example.studentJPA.repository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.studentJPA.model.UserBean;
@Repository
public interface UserRepository extends CrudRepository<UserBean,Integer> {
	
	@Query(value ="select * from user where email=?",nativeQuery= true)
	public UserBean selectOneByEmail(String email);
}
