package com.fab.wallet.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fab.wallet.bean.UserRequest;
import com.fab.wallet.entities.User;

public interface UserService extends UserDetailsService {

	User registerUser(UserRequest request) throws Exception;
	
	boolean userExists(String userId);
	
	Optional<User> getUserDetails(String userId);
}
