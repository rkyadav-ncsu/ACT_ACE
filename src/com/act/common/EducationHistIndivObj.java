package com.act.common;

import java.io.Serializable;
import java.util.Vector;

public class EducationHistIndivObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 824645397543300751L;
	private String eduWhere, eduStd;
	private String eduComm;
	private boolean eduAttend, eduLitClass, eduCont;  
	private String eduSchExp; 
//	private Vector lstEduNonForm;
	private Vector eduNonForm;
	private String caseNumber;
	
	public void setEduWhere(String eduWhere) {
		this.eduWhere = eduWhere;
	}
	public String getEduWhere() {
		return eduWhere;
	}
	public void setEduStd(String eduStd) {
		this.eduStd = eduStd;
	}
	public String getEduStd() {
		return eduStd;
	}
	public void setEduComm(String eduComm) {
		this.eduComm = eduComm;
	}
	public String getEduComm() {
		return eduComm;
	}
	public void setEduAttend(boolean eduAttend) {
		this.eduAttend = eduAttend;
	}
	public boolean isEduAttend() {
		return eduAttend;
	}
	public void setEduLitClass(boolean eduLitClass) {
		this.eduLitClass = eduLitClass;
	}
	public boolean isEduLitClass() {
		return eduLitClass;
	}
	public void setEduCont(boolean eduCont) {
		this.eduCont = eduCont;
	}
	public boolean isEduCont() {
		return eduCont;
	}
	public void setEduSchExp(String eduSchExp) {
		this.eduSchExp = eduSchExp;
	}
	public String getEduSchExp() {
		return eduSchExp;
	}
	public void setEduNonForm(Vector eduNonForm) {
		this.eduNonForm = eduNonForm;
	}
	public Vector getEduNonForm() {
		return eduNonForm;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}		
	
	
}
