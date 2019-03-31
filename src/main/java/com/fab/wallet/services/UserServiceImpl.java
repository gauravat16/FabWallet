package com.fab.wallet.services;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fab.wallet.bean.UserRequest;
import com.fab.wallet.entities.User;
import com.fab.wallet.entities.Wallet;
import com.fab.wallet.exceptions.UserExistsException;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Autowired
	protected EntityManager entityManager;

	@Transactional(rollbackFor = { UserExistsException.class })
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

		Wallet wallet = new Wallet(0.0, user);

		user.setWallet(wallet);

		logger.debug("User to register - " + user);

		return userRepository.save(user);
	}

	@Override
	public boolean userExists(String userId) {
		return userRepository.existsById(userId);
	}

	@Override
	public User getUserDetails(String userId) throws UserNotFoundException {

		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("Failed to furninsh user details",
						"No entry in database for user id - " + userId, "please check if userid is correct"));
	}

	@Transactional(rollbackFor = { UserNotFoundException.class })
	@Override
	public User updateDetails(UserRequest request) throws UserNotFoundException {
		Assert.notNull(request, "Update user request is null!");

		/*
		 * User id that user used to auth is used to fetch the data from DB in case user
		 * wants to modify their email.
		 */
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();

		Assert.notNull(userid, "No user has logged in so can't update the DB");

		User user = getUserDetails(userid);

		user.setfName(
				(request.getFname() != null || !request.getFname().isEmpty()) ? request.getFname() : user.getfName());
		user.setlName(
				(request.getlName() != null || !request.getlName().isEmpty()) ? request.getlName() : user.getlName());
		user.setPassword((request.getPassword() != null || !request.getPassword().isEmpty())
				? passwordEncoder.encode(request.getPassword())
				: user.getPassword());

		return userRepository.save(user);

	}

}
