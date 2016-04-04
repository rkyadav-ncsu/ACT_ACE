package com.act.common;

import java.io.Serializable;

public class Organisation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2057494968515154970L;
	private int orgnId;
	private String orgnName;
	private int orgnType;
	private String serviceTypes;
	
	public void setOrgnId(int orgnId) {
		this.orgnId = orgnId;
	}
	public int getOrgnId() {
		return orgnId;
	}
	public void setOrgnName(String orgnName) {
		this.orgnName = orgnName;
	}
	public String getOrgnName() {
		return orgnName;
	}
	public void setOrgnType(int orgnType) {		// ND added on 10th Jan 16
		this.orgnType = orgnType;
	}
	public int getOrgnType() {					// ND added on 10th Jan 16
		return orgnType;
	}
	public void setServiceTypes(String serviceTypes) {			// ND added on 10th Jan 16
		this.serviceTypes = serviceTypes;
	}
	public String getServiceTypes() {							// ND added on 10th Jan 16
		return serviceTypes;
	}

	public String toString() {
		return orgnName;
	}
}

