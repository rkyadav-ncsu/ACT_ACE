package com.act.common;
// ND added/created on 24th Sep

import java.io.Serializable;

public class CopingSkill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8124791319643674772L;
	private int copSkID;
	private String copSk;
	
	public void setCopSkID(int copSkID) {
		this.copSkID = copSkID;
	}
	public int getCopSkID() {
		return copSkID;
	}
	public void setCopSk(String copSk) {
		this.copSk = copSk;
	}
	public String getCopSk() {
		return copSk;
	}
	
	public String toString(){
		return copSk;
	}
	
	
}
