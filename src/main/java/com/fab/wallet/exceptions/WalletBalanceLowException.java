package com.fab.wallet.exceptions;

import org.springframework.http.HttpStatus;

public class WalletBalanceLowException extends BaseException {

	private static final long serialVersionUID = 1L;

	public WalletBalanceLowException(String errorMsg, String desc, String resolution) {
		super(errorMsg, desc, resolution);
	}
	
	@Override
	public HttpStatus getHTTPStatusCode() {
		return HttpStatus.PRECONDITION_FAILED;
	}

}
