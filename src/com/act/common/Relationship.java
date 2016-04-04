package com.act.common;

// ND start 28th Sep
import java.io.Serializable;
public class Relationship implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1430436428135692474L;
// ND end 28th Sep
	private int reltnId;
	private String reltnName;
	
	public void setReltnId(int reltnId) {
		this.reltnId = reltnId;
	}
	public int getReltnId() {
		return reltnId;
	}
	public void setReltnName(String reltnName) {
		this.reltnName = reltnName;
	}
	public String getReltnName() {
		return reltnName;
	}
	public String toString(){
		return reltnName;
		
	}
}
