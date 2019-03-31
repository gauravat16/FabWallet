package com.fab.wallet.exceptions;

public class UserNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException( String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

}
