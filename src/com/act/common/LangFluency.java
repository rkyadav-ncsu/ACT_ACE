package com.act.common;

import java.io.Serializable;

public class LangFluency implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6707606564141297515L;
	private String langName;
	private boolean canSpeak;
	private boolean canRead;
	private boolean canWrite;

	public LangFluency(){
		
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public boolean isCanSpeak() {
		return canSpeak;
	}

	public void setCanSpeak(boolean canSpeak) {
		this.canSpeak = canSpeak;
	}

	public boolean isCanRead() {
		return canRead;
	}

	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}

	public boolean isCanWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}
	
	
}
