package com.act.common;

import java.io.Serializable;
import java.util.Vector;



public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3072964565067057789L;
	private String userID;
	private String passWord;
	private String userDescription;
	private String fName, mName, lName;
	private String gender, desig, dob;
	private String address, pincode, email, mobile, phone;
	private Vector<String> groups;
	
	public User(){
		
	}
	
	public User(String userID, String passWord, String userDesc){
		this.userID = userID;
		this.passWord = passWord;
		this.userDescription = userDesc;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	
	
	public String getIDAndPassword(){
		return userID+passWord;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfName() {
		return fName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmName() {
		return mName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getlName() {
		return lName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public String getDesig() {
		return desig;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDob() {
		return dob;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPincode() {
		return pincode;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}

	public void setGroups(Vector<String> groups) {
		this.groups = groups;
	}

	public Vector<String> getGroups() {
		return groups;
	}

	

}
