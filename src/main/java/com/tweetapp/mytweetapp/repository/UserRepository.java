package com.tweetapp.mytweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.mytweetapp.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	
	List<User> findByEmailLike(String email);

}
