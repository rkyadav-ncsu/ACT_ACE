package com.act.common;

import java.io.Serializable;

public class UsersandGroupsObj implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3498991231939141449L;
	
	private String userID;
	private String grpName;
	
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserID() {
		return userID;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getGrpName() {
		return grpName;
	}

}
