package com.act.common;

import java.io.Serializable;


public class SummaryIndivObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8928447509890469410L;
	
	
	private String caseNumber;
	private String  psychoClinicFormulation, psychoNeedsCnsr, psychoIntervenPlan, psychoComments;		// ND Edited and added on 7th Dec

	public void setPsychoClinicFormulation(String psychoClinicFormulation) {
		this.psychoClinicFormulation = psychoClinicFormulation;
	}

	public String getPsychoClinicFormulation() {
		return psychoClinicFormulation;
	}

	public void setPsychoNeedsCnsr(String psychoNeedsCnsr) {
		this.psychoNeedsCnsr = psychoNeedsCnsr;
	}

	public String getPsychoNeedsCnsr() {
		return psychoNeedsCnsr;
	}

	public void setPsychoIntervenPlan(String psychoIntervenPlan) {
		this.psychoIntervenPlan = psychoIntervenPlan;
	}

	public String getPsychoIntervenPlan() {
		return psychoIntervenPlan;
	}

	public void setPsychoComments(String psychoComments) {
		this.psychoComments = psychoComments;
	}

	public String getPsychoComments() {
		return psychoComments;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

}
