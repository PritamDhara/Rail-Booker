package com.railway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.railway.client.EmailClient;
import com.railway.entity.User;
import com.railway.exceptions.NoUserFoundException;
import com.railway.exceptions.UserAlreadyExistException;
import com.railway.exceptions.UserIsNotRegisteredException;
import com.railway.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailClient eClient;

	
	@Override
	public Optional<User> register(User user) throws UserAlreadyExistException {
		
		Optional<User> u = repo.findByUserEmail(user.getUserEmail());
		
		if(u.isPresent())
		{
			throw new UserAlreadyExistException();
		}
		
		user.setUserId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User us = repo.insert(user);
		
//		String mailBody = "Hi "+user.getUserName()+", Your are successfully registered with our railway"
//				+ " ticket booking application . Thanks for choosing us . Happy Booking "
//				+" Regards from the team. Have a happy and safe journey.";
		
		String mailBody =
		        "Dear "+user.getUserName()+",\n\n"
		        + "Welcome to Rail Booker, your new destination for effortless train travel.\n\n"
		        + "Rail Booker makes train booking a breeze. Here's what we offer:\n\n"
		        + "1. Effortless Search: Find trains from source to destination with ease.\n"
		        + "2. Quick Booking: Book your tickets in just a few clicks.\n"
		        + "3. Stay Informed: Real-time updates on schedules and delays.\n"
		        + "4. Manage Bookings: Modify or cancel reservations hassle-free.\n"
		        + "5. Secure & Reliable: Your data is safe with us.\n\n"
		        + "Get ready for a seamless travel experience. We're here to simplify your journey.\n\n"
		        + "Questions or feedback? Reach out anytime.\n\n"
		        + "Best regards,\n"
		        + "Rail Booker Team\n"
		        + "+91 8016276157";
		
		eClient.sendMail(user.getUserEmail(),"Welcome to Rail Booker - Simplify Your Train Travel", mailBody);

		return Optional.of(us);
	}

	@Override
	public Optional<User> updateUser(User user) throws UserIsNotRegisteredException {
		Optional<User> u = repo.findByUserEmail(user.getUserEmail());
		if(u.isEmpty())
		{
			throw new UserIsNotRegisteredException();
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User us = repo.save(user);

		return Optional.of(us);
	}

	@Override
	public boolean deleteUserByEmailId(String emailId) throws UserIsNotRegisteredException {
		
		Optional<User> u = repo.findByUserEmail(emailId);
		if(u.isEmpty())
		{
			throw new UserIsNotRegisteredException();
		}
			repo.deleteByUserEmail(emailId);
		return true;
	}

	@Override
	public List<User> getAllUser() throws NoUserFoundException {

		List<User> users = repo.findAll();
		
		if(users.isEmpty())
		{
			throw new NoUserFoundException();
		}
		return users;
	}

	@Override
	public Optional<User> getUserById(int userId) throws UserIsNotRegisteredException {
		
		Optional<User> us = repo.findById(userId);
		
		if(us.isEmpty())
		{
			throw new UserIsNotRegisteredException();
		}

		return us;
	}

	@Override
	public Optional<User> getUserByEmailId(String emailId) throws UserIsNotRegisteredException {
		Optional<User> us = repo.findByUserEmail(emailId);
		
		if(us.isEmpty())
		{
			throw new UserIsNotRegisteredException();
		}

		return us;
	}



}
