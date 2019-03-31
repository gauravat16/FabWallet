package com.fab.wallet.exceptions.handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fab.wallet.exceptions.AuthenticationFailedException;
import com.fab.wallet.exceptions.BaseException;
import com.fab.wallet.exceptions.UserExistsException;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.exceptions.WalletBalanceLowException;
import com.fab.wallet.exceptions.WalletNotFoundException;

@RestControllerAdvice
public class ExceptionResponseHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

	@ExceptionHandler(value = { UserNotFoundException.class, AuthenticationFailedException.class,
			UserExistsException.class, WalletBalanceLowException.class, WalletNotFoundException.class })
	private <T extends BaseException> T genericCustomExceptionHandler(T exception) {
		logger.debug(exception.toString(), exception);
		return exception;
	}

	@ExceptionHandler(value = { Exception.class })
	private BaseException javaExceptionHandler(Exception exception) {
		logger.debug(exception.getMessage(), exception);
		return new BaseException(exception.getMessage(), "", "");
	}

}
