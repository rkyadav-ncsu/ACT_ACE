package com.act.common;

import java.io.Serializable;

public class OrgnIDNameObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2446130265446088418L;
	private int orgnId;
	private String orgnName;
	private String city;
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
	public void setOrgnType(int orgnType) {
		this.orgnType = orgnType;
	}
	public int getOrgnType() {
		return orgnType;
	}
	public void setServiceTypes(String serviceTypes) {
		this.serviceTypes = serviceTypes;
	}
	public String getServiceTypes() {
		return serviceTypes;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}					

}
