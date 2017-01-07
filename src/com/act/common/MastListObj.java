package com.act.common;

import java.io.Serializable;

public class MastListObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1096502258537316719L;
	private int Mast_ID;
	private String MastName;
	
	public void setMast_ID(int mast_ID) {
		Mast_ID = mast_ID;
	}
	public int getMast_ID() {
		return Mast_ID;
	}
	public void setMastName(String mastName) {
		MastName = mastName;
	}
	public String getMastName() {
		return MastName;
	}
}
