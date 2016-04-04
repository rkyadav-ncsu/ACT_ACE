package com.act.common;

import java.io.Serializable;
import java.util.Vector;


public class StrenghtIndivObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7533652662606039639L;
// Should there be separate fields for different type of goals	

	// ND added this on 12th Dec 2015 - till the end of the file
	
	private String caseNumber;
	private String  strengthIndivID, strengthStrengths;
	private Vector  vStrengthCopeSkills;
	private String  stengthFlyGoals, strengthEduGoals, strengthSocialGoals;
	private String strengthPsychoHealthGoals, strengthVocGoals, strengthLegalGoals;
	private String strengthPhysicHealthGoals, strengthComments;
	
	
	public void setStrengthIndivID(String strengthIndivID) {
		this.strengthIndivID = strengthIndivID;
	}
	public String getStrengthIndivID() {
		return strengthIndivID;
	}
	public void setStrengthStrengths(String strengthStrengths) {
		this.strengthStrengths = strengthStrengths;
	}
	public String getStrengthStrengths() {
		return strengthStrengths;
	}
	public void setvStrengthCopeSkills(Vector vStrengthCopeSkills) {
		this.vStrengthCopeSkills = vStrengthCopeSkills;
	}
	public Vector getvStrengthCopeSkills() {
		return vStrengthCopeSkills;
	}
	public void setStengthFlyGoals(String stengthFlyGoals) {
		this.stengthFlyGoals = stengthFlyGoals;
	}
	public String getStengthFlyGoals() {
		return stengthFlyGoals;
	}
	public void setStrengthEduGoals(String strengthEduGoals) {
		this.strengthEduGoals = strengthEduGoals;
	}
	public String getStrengthEduGoals() {
		return strengthEduGoals;
	}
	public void setStrengthSocialGoals(String strengthSocialGoals) {
		this.strengthSocialGoals = strengthSocialGoals;
	}
	public String getStrengthSocialGoals() {
		return strengthSocialGoals;
	}
	public void setStrengthPsychoHealthGoals(String strengthPsychoHealthGoals) {
		this.strengthPsychoHealthGoals = strengthPsychoHealthGoals;
	}
	public String getStrengthPsychoHealthGoals() {
		return strengthPsychoHealthGoals;
	}
	public void setStrengthVocGoals(String strengthVocGoals) {
		this.strengthVocGoals = strengthVocGoals;
	}
	public String getStrengthVocGoals() {
		return strengthVocGoals;
	}
	public void setStrengthLegalGoals(String strengthLegalGoals) {
		this.strengthLegalGoals = strengthLegalGoals;
	}
	public String getStrengthLegalGoals() {
		return strengthLegalGoals;
	}
	
	public void setStrengthPhysicHealthGoals(String strengthPhysicHealthGoals){
		this.strengthPhysicHealthGoals = strengthPhysicHealthGoals;
	}
	
	public String getStrengthPhysicHealthGoals() {
		return strengthPhysicHealthGoals;
	}
	
	public void setStrengthComments(String strengthComments){
		this.strengthComments = strengthComments;
	}
	
	public String getStrengthComments() {
		return strengthComments;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
}
