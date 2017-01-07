package com.act.common;

import java.io.Serializable;

public class GroupsObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8093051717019040820L;
	
	private String groupName, groupDesc;


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getGroupDesc() {
		return groupDesc;
	}
	
	public String toString() {
		return groupName;
	}
}
