package com.act.common;
// ND start 28 Sep 
import java.io.Serializable;
public class Addiction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5094354253283867345L;
	private int addictId;
// ND end 28 Sep
	private String addictName;				// ND changed 23rd Sep 
	
	public void setAddictId(int addictId) {
		this.addictId = addictId;
	}
	public int getAddictId() {
		return addictId;
	}
	public void setAddictName(String addictName) {			// ND changed 23rd Sep
		this.addictName = addictName;
	}
	public String getAddictName() {			// ND changed 23rd Sep
		return addictName;
	}
	public String toString(){			//ND added 23rd Sep
		return addictName;
	}
}
