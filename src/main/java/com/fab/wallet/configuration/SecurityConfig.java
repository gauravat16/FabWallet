package com.fab.wallet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fab.wallet.security.DatabaseAuthProvider;

/**
 * Security Config.
 * 
 * @author gaurav
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DatabaseAuthProvider databaseAuthProvider;

	/**
	 * Adding custom authentication provider.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(databaseAuthProvider);
	}

	@Bean
	PasswordEncoder getDefaultPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/wallet/api/v1/user/login", "/wallet/api/v1/user/update", "/wallet/api/v1/wallet/txn/*")
				.hasAnyRole("USER").and().csrf().disable().headers().frameOptions().disable();
		http.logout().logoutUrl("/wallet/api/v1/user/logout").logoutSuccessUrl("/");
	}

}
