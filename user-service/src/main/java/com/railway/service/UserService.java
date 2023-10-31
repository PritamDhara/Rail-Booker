package com.railway.service;

import java.util.List;
import java.util.Optional;

import com.railway.entity.User;
import com.railway.exceptions.NoUserFoundException;
import com.railway.exceptions.UserAlreadyExistException;
import com.railway.exceptions.UserIsNotRegisteredException;

public interface UserService {
	
	Optional<User> register(User user) throws UserAlreadyExistException;
	
	Optional<User> updateUser(User user) throws UserIsNotRegisteredException;
	
	boolean deleteUserByEmailId(String emailId) throws UserIsNotRegisteredException;
	
	List<User> getAllUser() throws NoUserFoundException;
	
	Optional<User> getUserById(int userId) throws UserIsNotRegisteredException;
	
	Optional<User> getUserByEmailId(String emailId) throws UserIsNotRegisteredException;
	

	
	
	

}
