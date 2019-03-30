package com.fab.wallet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fab.wallet.security.DatabaseAuthProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DatabaseAuthProvider databaseAuthProvider;

	@Autowired
	private UserDetailsService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.authenticationProvider(databaseAuthProvider);
//		auth.userDetailsService(userService).passwordEncoder(getDefaultPasswordEncoder());
	}

	@Bean
	PasswordEncoder getDefaultPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers("/wallet/api/v1/user/login").hasAnyRole("USER").and()
        .csrf().disable().headers().frameOptions().disable();
	}

}
