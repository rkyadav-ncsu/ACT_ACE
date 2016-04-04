package com.act.common;

import java.io.Serializable;
import java.util.Vector;

public class CounseleeRelativeIndivObj implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8040586052204561491L;
	
	private String caseNumber;
	private String relName;
	private String relationship;
	private String relStrength;
	private String relProfession;
	private String relAge;
	private boolean relAwareOfVictimsSituation;
	private String relComments;
	
	public CounseleeRelativeIndivObj(){
		
	}

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}


	public String getRelProfession() {
		return relProfession;
	}

	public void setRelProfession(String relProfession) {
		this.relProfession = relProfession;
	}

	public String getRelAge() {
		return relAge;
	}

	public void setRelAge(String relAge) {
		this.relAge = relAge;
	}

	public boolean isRelAwareOfVictimsSituation() {
		return relAwareOfVictimsSituation;
	}

	public void setRelAwareOfVictimsSituation(boolean relAwareOfVictimsSituation) {
		this.relAwareOfVictimsSituation = relAwareOfVictimsSituation;
	}

	public String getRelComments() {
		return relComments;
	}

	public void setRelComments(String relComments) {
		this.relComments = relComments;
	}

	public String getRelStrength() {
		return relStrength;
	}

	public void setRelStrength(String relStrength) {
		this.relStrength = relStrength;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}


	

}
