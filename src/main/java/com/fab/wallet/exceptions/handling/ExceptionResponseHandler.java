package com.fab.wallet.exceptions.handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fab.wallet.exceptions.AuthenticationFailedException;
import com.fab.wallet.exceptions.BaseException;
import com.fab.wallet.exceptions.InternalException;
import com.fab.wallet.exceptions.NegativeAmountException;
import com.fab.wallet.exceptions.UserExistsException;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.exceptions.WalletBalanceLowException;
import com.fab.wallet.exceptions.WalletNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;

@RestControllerAdvice
public class ExceptionResponseHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

	@ExceptionHandler(value = { UserNotFoundException.class, AuthenticationFailedException.class,
			UserExistsException.class, WalletBalanceLowException.class, WalletNotFoundException.class,
			NegativeAmountException.class })
	private <T extends BaseException> ResponseEntity<T> genericCustomExceptionHandler(T exception) {
		logger.debug(exception.toString(), exception);

		return new ResponseEntity<>(exception, new HttpHeaders(), exception.getHTTPStatusCode());
	}

	@ExceptionHandler(value = { Exception.class, JsonParseException.class })
	private ResponseEntity<Object> javaExceptionHandler(Exception exception) {
		logger.debug(exception.getMessage(), exception);
		InternalException internalException = new InternalException(exception.getClass().getName(),
				exception.getMessage(), "Please Check your request.");
		return new ResponseEntity<>(internalException, new HttpHeaders(), internalException.getHTTPStatusCode());
	}

}
