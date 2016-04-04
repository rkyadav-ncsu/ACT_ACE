package com.act.common;

import java.io.Serializable;

public class AbuseIndivObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7742190876743621123L;

	private String abuseVerbal, abusePhys, abuseSex, abuseNeglect;
	
	private String abuseComments, caseNumber;			// ND added on 19th Jan 16
	

	public void setAbuseVerbal(String abuseVerbal) {
		this.abuseVerbal = abuseVerbal;
	}

	public String getAbuseVerbal() {
		return abuseVerbal;
	}

	public void setAbusePhys(String abusePhys) {
		this.abusePhys = abusePhys;
	}

	public String getAbusePhys() {
		return abusePhys;
	}

	public void setAbuseSex(String abuseSex) {
		this.abuseSex = abuseSex;
	}

	public String getAbuseSex() {
		return abuseSex;
	}

	public void setAbuseNeglect(String abuseNeglect) {
		this.abuseNeglect = abuseNeglect;
	}

	public String getAbuseNeglect() {
		return abuseNeglect;
	}

	public void setAbuseComments(String abuseComments) {				// ND added on 1st Nov
		this.abuseComments = abuseComments;
	}

	public String getAbuseComments() {
		return abuseComments;
	}

	public void setCaseNumber(String caseNumber) {			// ND added on 19th Jan 16
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}
}
