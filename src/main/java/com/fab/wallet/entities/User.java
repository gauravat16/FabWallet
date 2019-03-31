package com.fab.wallet.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User entity object.
 * 
 * @author gaurav
 *
 */
@Table(name = "USER")
@Entity
public class User {

	/**
	 * User id represents an email id.
	 */
	@Id
	@Column(name = "id")
	private String userId;

	@Column(name = "first_name")
	private String fName;

	@Column(name = "last_name")
	private String lName;

	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@JsonIgnore
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	private Wallet wallet;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fName=" + fName + ", lName=" + lName + "]";
	}

}
