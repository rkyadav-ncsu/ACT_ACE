package com.act.common;



public class User {
	
	private String userName;
	private String passWord;
	private String userDescription;
	
	public User(){
		
	}
	
	public User(String userName, String passWord, String userDesc){
		this.userName = userName;
		this.passWord = passWord;
		this.userDescription = userDesc;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserNameW(String userName) {
		this.userName = userName;
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
	
	
	public String getNameAndPassword(){
		return userName+passWord;
	}
}
