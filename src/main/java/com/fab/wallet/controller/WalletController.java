package com.fab.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fab.wallet.entities.Transaction.TXN_TYPES;
import com.fab.wallet.entities.Wallet;
import com.fab.wallet.exceptions.UserNotFoundException;
import com.fab.wallet.exceptions.WalletBalanceLowException;
import com.fab.wallet.exceptions.WalletNotFoundException;
import com.fab.wallet.services.WalletService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This controller will handle everything related to User's wallet.
 * 
 * @author gaurav
 *
 */
@RestController
@RequestMapping("/wallet/api/v1/wallet")
@Api(value = "This controller will handle everything related to a User's wallet.")
public class WalletController {

	@Autowired
	private WalletService walletService;

	/**
	 * Returns a authenticated user's Wallet info/Passbook.
	 * 
	 * @return Wallet Details
	 * @throws UserNotFoundException
	 * @throws WalletNotFoundException
	 */
	@ApiOperation(value = "Returns a authenticated user's Wallet info/Passbook.", response = Wallet.class)
	@PostMapping("/get/details")
	public Wallet getUserDetails() throws UserNotFoundException, WalletNotFoundException {
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		Assert.notNull(userid, "No user has logged in so can't send wallet details");

		return walletService.getWalletDataForUser(userid);
	}

	/**
	 * This end point will allow user to perform a credit transaction on their
	 * wallet.
	 * 
	 * @param amount
	 * @return Wallet Details
	 * @throws UserNotFoundException
	 * @throws WalletNotFoundException
	 * @throws NumberFormatException
	 * @throws WalletBalanceLowException
	 */
	@ApiOperation(value = "This end point will allow user to perform a credit transaction on their wallet.", response = Wallet.class)
	@PostMapping("/txn/credit/{amount}")
	public Wallet performCreditTxn(@PathVariable(value = "amount") String amount)
			throws UserNotFoundException, WalletNotFoundException, NumberFormatException, WalletBalanceLowException {
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		Assert.notNull(userid, "No user has logged in so can't send wallet details");
		return walletService.performTransaction(userid, TXN_TYPES.CREDIT, Double.valueOf(amount));
	}

	/**
	 * This end point will allow user to perform a debit transaction on their
	 * wallet.
	 * 
	 * @param amount
	 * @return Wallet Details
	 * @throws UserNotFoundException
	 * @throws WalletNotFoundException
	 * @throws NumberFormatException
	 * @throws WalletBalanceLowException
	 */
	@ApiOperation(value = "This end point will allow user to perform a debit transaction on their wallet", response = Wallet.class)
	@PostMapping("/txn/debit/{amount}")
	public Wallet performDebittTxn(@PathVariable(value = "amount") String amount)
			throws UserNotFoundException, WalletNotFoundException, NumberFormatException, WalletBalanceLowException {
		String userid = SecurityContextHolder.getContext().getAuthentication().getName();
		Assert.notNull(userid, "No user has logged in so can't send wallet details");
		return walletService.performTransaction(userid, TXN_TYPES.DEBIT, Double.valueOf(amount));
	}

}
