package com.act.common;

import java.io.Serializable;
import java.util.Vector;


public class PhysicalHistIndivObj implements Serializable {
	/**
	 * 
	 */
	private String caseNumber;
	private static final long serialVersionUID = 2778405410775899840L;
	
	private String 	physicalHistCurrYou, physicalHistCurr, physicalHistWithdrawSympt;
	private Vector	physicalHistAddict;				// ND edited on 19th Dec Changed withrawalSymptoms to String
	private String withdrawalSymptoms;
	private boolean physicalHistBirthCtrl,physicalHistPreg, physicalHistAbort, physicalHistSTI, physicalHistSTITreat; 
	private String physicalHistCurrSpecsYou, physicalHistCurrSpecs, physicalHistSerious;
	private String physicalHistComments;
	private boolean LearnDisable, ADHDisorder, ADDisorder, Autism, OtherDisorder, BeenCounseled, PsychMedsCurr;
	
	
	public void setPhyHistCurrYou(String physicalHistCurrYou) {
		this.physicalHistCurrYou = physicalHistCurrYou;
	}
	public String getPhyHistCurrYou() {
		return physicalHistCurrYou;
	}
	public void setPhyHistCurr(String physicalHistCurr) {
		this.physicalHistCurr = physicalHistCurr;
	}
	public String getPhyHistCurr() {
		return physicalHistCurr;
	}
	public void setPhyHistWithdrawSympt(String physicalHistWithdrawSympt) {
		this.physicalHistWithdrawSympt = physicalHistWithdrawSympt;
	}
	public String getPhyHistWithdrawSympt() {
		return physicalHistWithdrawSympt;
	}
	public void setPhyHistAddict(Vector physicalHistAddict) {
		this.physicalHistAddict = physicalHistAddict;
	}
	public Vector getPhyHistAddict() {
		return physicalHistAddict;
	}
	// ND edited on 19th Dec Changed withdrawalSymptoms from vector to string 
//	public void setWithdrawalSymptoms(Vector withdrawalSymptoms) {
//		this.withdrawalSymptoms = withdrawalSymptoms;
//	}
//	public Vector getWithdrawalSymptoms() {
//		return withdrawalSymptoms;
//	}
	public void setPhyHistBirthCtrl(boolean physicalHistBirthCtrl) {
		this.physicalHistBirthCtrl = physicalHistBirthCtrl;
	}
	public boolean isPhyHistBirthCtrl() {
		return physicalHistBirthCtrl;
	}
	public void setPhyHistPreg(boolean physicalHistPreg) {
		this.physicalHistPreg = physicalHistPreg;
	}
	public boolean isPhyHistPreg() {
		return physicalHistPreg;
	}
	public void setPhyHistAbort(boolean physicalHistAbort) {
		this.physicalHistAbort = physicalHistAbort;
	}
	public boolean isPhyHistAbort() {
		return physicalHistAbort;
	}
	public void setPhyHistSTI(boolean physicalHistSTI) {
		this.physicalHistSTI = physicalHistSTI;
	}
	public boolean isPhyHistSTI() {
		return physicalHistSTI;
	}
	public void setPhyHistSTITreat(boolean physicalHistSTITreat) {
		this.physicalHistSTITreat = physicalHistSTITreat;
	}
	public boolean isPhyHistSTITreat() {
		return physicalHistSTITreat;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	// Current Heath Specifications could be entered in the Comments field
	public void setPhyHistCurrSpecsYou(String physicalHistCurrSpecsYou) {
		this.physicalHistCurrSpecsYou = physicalHistCurrSpecsYou;
	}
	public String getPhyHistCurrSpecsYou() {
		return physicalHistCurrSpecsYou;
	}
	public void setPhyHistCurrSpecs(String physicalHistCurrSpecs) {
		this.physicalHistCurrSpecs = physicalHistCurrSpecs;
	}
	public String getPhyHistCurrSpecs() {
		return physicalHistCurrSpecs;
	}
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public void setPhysicalHistSerious(String physicalHistSerious) {
		this.physicalHistSerious = physicalHistSerious;
	}
	public String getPhysicalHistSerious() {
		return physicalHistSerious;
	}
	public void setPhyHistComments(String physicalHistComments) {
		this.physicalHistComments = physicalHistComments;
	}
	public String getPhyHistComments() {
		return physicalHistComments;
	}
	public void setLearnDisable(boolean learnDisable) {
		LearnDisable = learnDisable;
	}
	public boolean isLearnDisable() {
		return LearnDisable;
	}
	public void setADHDisorder(boolean aDHDisorder) {
		ADHDisorder = aDHDisorder;
	}
	public boolean isADHDisorder() {
		return ADHDisorder;
	}
	public void setADDisorder(boolean aDDisorder) {
		ADDisorder = aDDisorder;
	}
	public boolean isADDisorder() {
		return ADDisorder;
	}
	public void setAutism(boolean autism) {
		Autism = autism;
	}
	public boolean isAutism() {
		return Autism;
	}
	public void setOtherDisorder(boolean otherDisorder) {
		OtherDisorder = otherDisorder;
	}
	public boolean isOtherDisorder() {
		return OtherDisorder;
	}
	public void setBeenCounseled(boolean beenCounseled) {
		BeenCounseled = beenCounseled;
	}
	public boolean isBeenCounseled() {
		return BeenCounseled;
	}
	public void setPsychMedsCurr(boolean psychMedsCurr) {
		PsychMedsCurr = psychMedsCurr;
	}
	public boolean isPsychMedsCurr() {
		return PsychMedsCurr;
	}
	public void setWithdrawalSymptoms(String withdrawalSymptoms) {
		this.withdrawalSymptoms = withdrawalSymptoms;
	}
	public String getWithdrawalSymptoms() {
		return withdrawalSymptoms;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	
	

}
