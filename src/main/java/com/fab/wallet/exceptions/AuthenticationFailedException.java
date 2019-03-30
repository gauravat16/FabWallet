package com.fab.wallet.exceptions;

public class AuthenticationFailedException extends BaseException {

	private static final long serialVersionUID = 1L;

	public AuthenticationFailedException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

}
