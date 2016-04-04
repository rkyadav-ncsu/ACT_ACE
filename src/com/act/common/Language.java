package com.act.common;

// ND start 28th Sep
import java.io.Serializable;
public class Language implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7388664298013677304L;
// ND end 28th Sep
	private int langId ;
	private String langName = " ";
	
	
	public void setLangId(int langId) {
		this.langId = langId;
	}
	public int getLangId() {
		return langId;
	}
	public void setLangName(String langName) {
		this.langName = langName;
	}
	public String getLangName() {
		return langName;
	}
	// ND added on 25th Sep
	public String toString(){
		return langName;
	}
	
}
