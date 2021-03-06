package com.fab.wallet.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException( String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}
	
	@Override
	public HttpStatus getHTTPStatusCode() {
		return HttpStatus.NO_CONTENT;
	}

}
