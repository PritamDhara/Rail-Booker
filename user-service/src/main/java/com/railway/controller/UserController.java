package com.railway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.entity.User;
import com.railway.exceptions.NoUserFoundException;
import com.railway.exceptions.UserAlreadyExistException;
import com.railway.exceptions.UserIsNotRegisteredException;
import com.railway.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) throws UserAlreadyExistException
	{
		Optional<User> us = service.register(user);
		return new ResponseEntity<User>(us.get(), HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserIsNotRegisteredException
	{
		Optional<User> us = service.updateUser(user);
		return new ResponseEntity<User>(us.get(), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/removeUser/{emailId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Boolean> removeUser(@PathVariable String emailId) throws UserIsNotRegisteredException
	{
		boolean isDeleted = service.deleteUserByEmailId(emailId);
		return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() throws NoUserFoundException
	{
		List<User> userList = service.getAllUser();
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws UserIsNotRegisteredException
	{
		Optional<User> us = service.getUserById(userId);
		return new ResponseEntity<User>(us.get(),HttpStatus.OK);
	}
	
	@GetMapping("/getUserByEmailId/{emailId}")
	public ResponseEntity<User> getUserByEmailId(@PathVariable String emailId) throws UserIsNotRegisteredException
	{
		Optional<User> us = service.getUserByEmailId(emailId);
		return new ResponseEntity<User>(us.get(),HttpStatus.OK);
	}
	


}
