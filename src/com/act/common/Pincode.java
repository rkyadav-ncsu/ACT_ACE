package com.act.common;

import java.io.Serializable;

public class Pincode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927756915296629757L;
	
	private String pinID, pinPO, pinCode, pinCitDis, pinState;

	public void setPinID(String pinID) {
		this.pinID = pinID;
	}

	public String getPinID() {
		return pinID;
	}

	public void setPinPO(String pinPO) {
		this.pinPO = pinPO;
	}

	public String getPinPO() {
		return pinPO;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCitDis(String pinCitDis) {
		this.pinCitDis = pinCitDis;
	}

	public String getPinCitDis() {
		return pinCitDis;
	}

	public void setPinState(String pinState) {
		this.pinState = pinState;
	}

	public String getPinState() {
		return pinState;
	}
	

}
