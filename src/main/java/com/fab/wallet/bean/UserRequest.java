package com.fab.wallet.bean;

import org.springframework.stereotype.Component;

/**
 * Pojo representing the request for User registration.
 * 
 * @author gaurav
 *
 */
@Component
public class UserRequest {

	private String userId;

	private String fname;

	private String lName;

	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
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

	@Override
	public String toString() {
		return "UserRequest [userId=" + userId + ", fname=" + fname + ", lName=" + lName + "]";
	}

}
