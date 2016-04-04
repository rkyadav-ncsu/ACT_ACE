package com.act.common;

import java.io.Serializable;

public class CareHome implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7333690409348877854L;
	private int careHomeId;
	private String careHomeName;
	
	
	public void setCareHomeId(int careHomeId) {
		this.careHomeId = careHomeId;
	}
	public int getCareHomeId() {
		return careHomeId;
	}
	public void setCareHomeName(String careHomeName) {
		this.careHomeName = careHomeName;
	}
	public String getCareHomeName() {
		return careHomeName;
	}
	
	public String toString() {
		return careHomeName;
	}

}
