package com.fab.wallet.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Transaction entity object.
 * 
 * @author gaurav
 *
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	public static enum TXN_TYPES {
		DEBIT, CREDIT;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "txn_id")
	private long txnId;

	@Column(name = "txn_amount")
	private double txnAmount;

	@Column(name = "txn_total_amount")
	private double txnTotalAmount;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;

	@Column(name = "txn_type")
	private String txnType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "txn_time_stamp")
	private Date date;

	public Transaction() {
	}

	public Transaction(double txnAmount, double txnTotalAmount, Wallet wallet, TXN_TYPES txnType) {
		super();
		this.txnAmount = txnAmount;
		this.wallet = wallet;
		this.txnType = txnType.name();
		this.txnTotalAmount = txnTotalAmount;
		this.date = new Date();
	}

	public double getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(double txnAmount) {
		this.txnAmount = txnAmount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public long getTxnId() {
		return txnId;
	}

	public double getTxnTotalAmount() {
		return txnTotalAmount;
	}

	public void setTxnTotalAmount(double txnTotalAmount) {
		this.txnTotalAmount = txnTotalAmount;
	}

}
