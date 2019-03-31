package com.fab.wallet.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fab.wallet.entities.Transaction;
import com.fab.wallet.entities.Transaction.TXN_TYPES;
import com.fab.wallet.entities.User;
import com.fab.wallet.entities.Wallet;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.exceptions.WalletBalanceLowException;
import com.fab.wallet.exceptions.WalletNotFoundException;
import com.fab.wallet.repositories.WalletRepository;

/**
 * Service handles all Wallet related actions.
 * 
 * @author gaurav
 *
 */
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private UserService userService;

	@Autowired
	private WalletRepository walletRepository;

	@Transactional(rollbackOn = { UserNotFoundException.class })
	@Override
	public Wallet getWalletDataForUser(String userId) throws UserNotFoundException, WalletNotFoundException {
		User user = userService.getUserDetails(userId);
		if (user.getWallet() == null) {
			throw new WalletNotFoundException("No Wallet info found", "User -" + userId + " has no wallet info",
					"Please add new wallet for user");
		}
		return user.getWallet();
	}

	@Transactional(rollbackOn = { UserNotFoundException.class, WalletNotFoundException.class,
			WalletBalanceLowException.class })
	@Override
	public Wallet performTransaction(String userId, TXN_TYPES txn_type, double amount)
			throws UserNotFoundException, WalletNotFoundException, WalletBalanceLowException {

		Wallet wallet = getWalletDataForUser(userId);
		double currentAmount = wallet.getBalance();

		Transaction transaction = null;
		switch (txn_type) {
		case CREDIT:
			wallet.setBalance(currentAmount + amount);
			transaction = new Transaction(amount, wallet.getBalance(), wallet, TXN_TYPES.CREDIT);
			break;

		case DEBIT:
			if (currentAmount - amount < 0) {
				throw new WalletBalanceLowException("Wallet balance too low!",
						"Wallet balance for user - " + userId + " is less than " + amount,
						"User must recharge their wallet");
			}
			wallet.setBalance(currentAmount - amount);
			transaction = new Transaction(amount, wallet.getBalance(), wallet, TXN_TYPES.DEBIT);
			break;

		}

		wallet.getTransactions().add(transaction);
		transaction.setWallet(wallet);
		walletRepository.save(wallet);

		return wallet;
	}

}
