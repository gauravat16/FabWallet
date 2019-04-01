package com.fab.wallet.exceptions;

import org.springframework.http.HttpStatus;

public class InternalException extends BaseException {

	private static final long serialVersionUID = 1L;

	public InternalException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

	@Override
	public HttpStatus getHTTPStatusCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
