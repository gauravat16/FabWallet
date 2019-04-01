package com.fab.wallet.exceptions;

import org.springframework.http.HttpStatus;

public class NegativeAmountException extends BaseException {

	private static final long serialVersionUID = 1L;

	public NegativeAmountException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}
	
	@Override
	public HttpStatus getHTTPStatusCode() {
		return HttpStatus.BAD_REQUEST;
	}

}
