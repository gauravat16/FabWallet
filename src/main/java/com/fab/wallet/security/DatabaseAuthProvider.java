package com.fab.wallet.security;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.services.UserService;

/**
 * Provides custom DB authentication.
 * 
 * @author gaurav
 *
 */
@Component
public class DatabaseAuthProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseAuthProvider.class);

	@Autowired
	private UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		User user = null;
		try {
			user = userService.getUserDetails(userName);
			if (encoder.matches(password, user.getPassword())) {
				Set<GrantedAuthority> authorities = new HashSet<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				return new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword(), authorities);
			} else {
				throw new BadCredentialsException("Passwords didn't match!");
			}
		} catch (UserNotFoundException e) {
			logger.debug(e.getErrorMsg(), e);
		}

		throw new UsernameNotFoundException("Userid not found in DB");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
