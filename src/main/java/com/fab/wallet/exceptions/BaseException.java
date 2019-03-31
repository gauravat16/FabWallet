package com.fab.wallet.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author gaurav
 *
 */
@JsonIgnoreProperties({ "cause", "stackTrace", "methodName", "message", "localizedMessage", "suppressed" })
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String timeStamp = new Date().toString();
	private String errorMsg;
	private String desc;
	private String resolution;

	public BaseException(String errorMsg, String desc, String resolution) {
		super();
		this.errorMsg = errorMsg;
		this.desc = desc;
		this.resolution = resolution;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "BaseException [timeStamp=" + timeStamp + ", errorMsg=" + errorMsg + ", desc=" + desc + ", resolution="
				+ resolution + "]";
	}

}
