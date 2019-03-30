package com.fab.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fab.wallet.bean.UserRequest;
import com.fab.wallet.entities.User;
import com.fab.wallet.services.UserService;

/**
 * This controller will handle everything related to a user.
 * @author gaurav
 *
 */
@RestController
@RequestMapping("/wallet/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public User addnewUser(@RequestBody UserRequest request) throws Exception {
		return userService.registerUser(request);
	}
	
	@PostMapping("/login")
	public Authentication logInUser(@ModelAttribute UserRequest request) throws Exception {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
