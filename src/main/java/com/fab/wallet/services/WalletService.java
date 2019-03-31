package com.fab.wallet.services;

import com.fab.wallet.entities.Transaction.TXN_TYPES;
import com.fab.wallet.entities.Wallet;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.exceptions.WalletBalanceLowException;
import com.fab.wallet.exceptions.WalletNotFoundException;

public interface WalletService {

	/**
	 * Get wallet data for a user id.
	 * 
	 * @param userId
	 * @return Wallet info
	 * @throws UserNotFoundException
	 * @throws WalletNotFoundException
	 */
	Wallet getWalletDataForUser(String userId) throws UserNotFoundException, WalletNotFoundException;

	/**
	 * Perform a {@link TXN_TYPES#CREDIT} or {@link TXN_TYPES#DEBIT} transaction on
	 * a user's wallet.
	 * 
	 * @param userId
	 * @param txn_type
	 * @param amount
	 * @return
	 * @throws UserNotFoundException
	 * @throws WalletNotFoundException
	 * @throws WalletBalanceLowException
	 */
	Wallet performTransaction(String userId, TXN_TYPES txn_type, double amount)
			throws UserNotFoundException, WalletNotFoundException, WalletBalanceLowException;

}
