package com.act.common;
// ND added on 11th Sep 2015 (new class)

import java.io.Serializable;
public class Religion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -33780805937294586L;
	private int relId ;
	private String relName;
	
	public int getRelId (){
		return relId;
	}
	
	public void setRelId (int relId) {
		this.relId = relId;
	}
	
	public String getRelName() {
		return relName;
	}
	
	public void setRelName(String relName) {
		this.relName = relName;
	}
	
	public String toString(){
		return relName;
	}
}
