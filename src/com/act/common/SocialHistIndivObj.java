package com.act.common;

import java.io.Serializable;
import java.util.Vector;


public class SocialHistIndivObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5509903953744921078L;
	
	private String caseNumber;
	private String socLivedAt, socHerFriends;
	private String  socComments;
	private String socRelWithComm, socRelWithFriends, socInvolvement; // Good, Fair Poor  and Active, Nominal, Interested Non-Active
	private String socReligionBelief; 
	private Vector socRecreationandFun;
	
	public void setSocLivedAt(String socLivedAt) {
		this.socLivedAt = socLivedAt;
	}
	public String getSocLivedAt() {
		return socLivedAt;
	}
	public void setSocHerFriends(String socHerFriends) {
		this.socHerFriends = socHerFriends;
	}
	public String getSocHerFriends() {
		return socHerFriends;
	}
	public void setSocComments(String socComments) {
		this.socComments = socComments;
	}
	public String getSocComments() {
		return socComments;
	}
	public void setSocRelWithComm(String socRelWithComm) {
		this.socRelWithComm = socRelWithComm;
	}
	public String getSocRelWithComm() {
		return socRelWithComm;
	}
	public void setSocRelWithFriends(String socRelWithFriends) {
		this.socRelWithFriends = socRelWithFriends;
	}
	public String getSocRelWithFriends() {
		return socRelWithFriends;
	}
	public void setSocInvolvement(String socInvolvement) {
		this.socInvolvement = socInvolvement;
	}
	public String getSocInvolvement() {
		return socInvolvement;
	}
	public void setSocReligionBelief(String socReligionBelief) {
		this.socReligionBelief = socReligionBelief;
	}
	public String getSocReligionBelief() {
		return socReligionBelief;
	}
	public void setSocRecreationandFun(Vector socRecreationandFun) {
		this.socRecreationandFun = socRecreationandFun;
	}
	public Vector getSocRecreationandFun() {
		return socRecreationandFun;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	


}
