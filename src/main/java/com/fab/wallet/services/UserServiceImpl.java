package com.fab.wallet.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fab.wallet.bean.UserRequest;
import com.fab.wallet.entities.User;
import com.fab.wallet.exceptions.UserExistsException;
import com.fab.wallet.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(UserRequest request) throws UserExistsException {

		Assert.notNull(request, "New user request is null!");

		if (userExists(request.getUserId())) {
			throw new UserExistsException("Request to register user failed", "User already exists in the DB",
					"Ask user to log in");
		}

		User user = new User();
		user.setfName(request.getFname());
		user.setlName(request.getlName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setUserId(request.getUserId());

		return userRepository.save(user);
	}

	@Override
	public boolean userExists(String userId) {
		return userRepository.existsById(userId);
	}

	@Override
	public Optional<User> getUserDetails(String userId) {
		return userRepository.findById(userId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = getUserDetails(username);
		if (!userOptional.isPresent()) {
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			User user = userOptional.get();

			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					new ArrayList<>());
		}
	}

}
