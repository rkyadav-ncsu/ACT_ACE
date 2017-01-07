package com.act.common;

import java.io.Serializable;
import java.util.Vector;

public class Organisation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2057494968515154970L;
	private int orgnId;
	private String orgnName;
	private int orgnType;
	private Vector serviceTypes;						// ND edited 20th Aug 16
	private String add1, add2, add3, addCity, addPin;
	
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
	public void setServiceTypes(Vector serviceTypes) {			// ND added on 10th Jan 16		// ND edited on 8th 15th Sep 16
		this.serviceTypes = serviceTypes;
	}
	public Vector getServiceTypes() {							// ND added on 10th Jan 16		// ND edited on 8th 15th Sep 16
		return serviceTypes;
	}

	public String toString() {
		return orgnName;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd3(String add3) {
		this.add3 = add3;
	}
	public String getAdd3() {
		return add3;
	}
	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}
	public String getAddCity() {
		return addCity;
	}
	public void setAddPin(String addPin) {
		this.addPin = addPin;
	}
	public String getAddPin() {
		return addPin;
	}
}

