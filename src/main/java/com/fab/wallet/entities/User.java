package com.fab.wallet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "USER")
@Entity
public class User {

	/**
	 * User id represents an email id.
	 */
	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "first_name")
	private String fName;

	@Column(name = "last_name")
	private String lName;

	@Column(name = "password")
	private String password;

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

}
