package com.fab.wallet.services;

import com.fab.wallet.bean.UserRequest;
import com.fab.wallet.entities.User;
import com.fab.wallet.exceptions.UserNotFoundException;

public interface UserService {

	/**
	 * Register the user and return the created {@link User}.
	 * 
	 * @param request
	 * @return User info
	 * @throws Exception
	 */
	User registerUser(UserRequest request) throws Exception;

	/**
	 * Checks if a {@link User} for userId exists.
	 * 
	 * @param userId
	 * @return true if user exists.
	 */
	boolean userExists(String userId);

	/**
	 * Get user details for a registered user.
	 * 
	 * @param userId
	 * @return User info
	 * @throws UserNotFoundException
	 */
	User getUserDetails(String userId) throws UserNotFoundException;

	/**
	 * Update user details for a user.
	 * 
	 * @param request
	 * @return Updated User info
	 * @throws UserNotFoundException
	 */
	User updateDetails(UserRequest request) throws UserNotFoundException;
}
