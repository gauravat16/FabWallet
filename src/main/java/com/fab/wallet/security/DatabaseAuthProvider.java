package com.fab.wallet.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fab.wallet.entities.User;
import com.fab.wallet.services.UserService;

@Component
public class DatabaseAuthProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Optional<User> user = userService.getUserDetails(userName);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User doesn't exist in the DB!");
		}

		if (encoder.matches(password, user.get().getPassword())) {
			Set<GrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken(user, user.get().getPassword(), authorities);
		} else {
			throw new BadCredentialsException("Passwords didn't match!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
