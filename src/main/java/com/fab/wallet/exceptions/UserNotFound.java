package com.fab.wallet.exceptions;

public class UserNotFound extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserNotFound( String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

}
