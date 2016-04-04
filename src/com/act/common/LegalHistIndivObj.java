package com.act.common;

import java.io.Serializable;

public class LegalHistIndivObj implements Serializable{
	
	/**
	 * 
	 */
	
	private String caseNumber;
	private static final long serialVersionUID = -4588706396289319186L;
	private String legalOffences, legalComments;
	
	
	public void setLegalOffences(String legalOffences) {
		this.legalOffences = legalOffences;
	}
	public String getLegalOffences() {
		return legalOffences;
	}
	public void setLegalComments(String legalComments) {
		this.legalComments = legalComments;
	}
	public String getLegalComments() {
		return legalComments;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}

}
