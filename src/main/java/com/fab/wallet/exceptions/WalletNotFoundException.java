package com.fab.wallet.exceptions;

public class WalletNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public WalletNotFoundException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

}
