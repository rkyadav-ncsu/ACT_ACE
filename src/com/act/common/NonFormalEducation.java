package com.act.common;

import java.io.Serializable;

public class NonFormalEducation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1932558638058570994L;
	private int nfEduId;
	private String nfEduName;
	
	public void setNfEduId(int nfEduId) {
		this.nfEduId = nfEduId;
	}
	public int getNfEduId() {
		return nfEduId;
	}
	public void setNfEduName(String nfEduName) {
		this.nfEduName = nfEduName;
	}
	public String getNfEduName() {
		return nfEduName;
	}
	// ND added on 25th Sep
	public String toString(){
		return nfEduName;
	}
	
}
