package com.fab.wallet.exceptions;

public class UserExistsException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UserExistsException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

}
