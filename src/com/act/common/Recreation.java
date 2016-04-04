package com.act.common;
// ND added on 12th Oct

import java.io.Serializable;

// moving the pointer over the class name 'Recreation' provides the option to 
// generate the serialVersionUID automatically
public class Recreation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5439234304667792584L;
	//private static final long serialVersionUID = 
	
	private int recId;
	private String recName;
	
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getRecName() {
		return recName;
	}
	
	public String toString() {
		return recName;
	}
}
