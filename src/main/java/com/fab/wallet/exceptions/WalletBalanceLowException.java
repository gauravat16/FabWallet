package com.fab.wallet.exceptions;

public class WalletBalanceLowException extends BaseException {

	private static final long serialVersionUID = 1L;

	public WalletBalanceLowException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}

}
