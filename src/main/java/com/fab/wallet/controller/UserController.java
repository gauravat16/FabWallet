package com.fab.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fab.wallet.bean.UserRequest;
import com.fab.wallet.entities.User;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This controller will handle everything related to a user.
 * 
 * @author gaurav
 *
 */
@Api(value = "This controller will handle everything related to a user.")
@RestController
@RequestMapping("/wallet/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Add new user to the database", response = User.class)
	@PostMapping("/add")
	public User addnewUser(@RequestBody UserRequest request) throws Exception {
		return userService.registerUser(request);
	}

	@ApiOperation(value = "Log the user into the application", response = User.class)
	@PostMapping("/login")
	public Authentication logInUser() throws Exception {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@ApiOperation(value = "Check who is logged in currently", response = Authentication.class)
	@GetMapping("/who")
	public Authentication whoisloggedIn() throws Exception {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@ApiOperation(value = "Get registered user's details", response = User.class)
	@GetMapping("/get/{username}")
	public User getUserDetails(@PathVariable(value = "username") String userId) throws UserNotFoundException {
		return userService.getUserDetails(userId);
	}

	@ApiOperation(value = "update user details", response = User.class)
	@PostMapping("/update")
	public User updateUserDetails(@RequestBody UserRequest request) throws UserNotFoundException {
		return userService.updateDetails(request);
	}

	@ApiOperation(value = "Log the user out.", response = User.class)
	@GetMapping("/logout")
	public String logout() throws Exception {
		return "You have been logged out!";
	}

}
