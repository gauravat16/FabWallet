package com.fab.wallet.bean;

import org.springframework.stereotype.Component;

/**
 * Holds data for a transaction.
 * 
 * @author gaurav
 *
 */
@Component
public class TransactionRequest {

	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
