package com.act.common;

import java.io.Serializable;
import java.util.Vector;

public class MentalStatusIndivObj implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1145903456481191286L;
	
	private String caseNumber;
	private String  mentalStAppearHyg,  mentalStAffect, mentalStMood, mentalStSpeech,
					mentalStAttitude, mentalStIntellect, mentalStImpulse, mentalStUnderstanding;	
	private String	mentalStAttention, mentalStSuicidal, mentalStHomicidal, mentalStThoughtProc;
	private Vector    mentalStHallucination, mentalStOrientation;
	// ND changed mentalStMovement from Vector to String on 23rd Nov
	private String  mentalStMovement, mentalStMemory, mentalStComments;
	
	public void setMentalStAppearHyg(String mentalStAppearHyg) {
		this.mentalStAppearHyg = mentalStAppearHyg;
	}
	public String getMentalStAppearHyg() {
		return mentalStAppearHyg;
	}
	public void setMentalStAffect(String mentalStAffect) {
		this.mentalStAffect = mentalStAffect;
	}
	public String getMentalStAffect() {
		return mentalStAffect;
	}
	public void setMentalStMood(String mentalStMood) {
		this.mentalStMood = mentalStMood;
	}
	public String getMentalStMood() {
		return mentalStMood;
	}
	public void setMentalStSpeech(String mentalStSpeech) {
		this.mentalStSpeech = mentalStSpeech;
	}
	public String getMentalStSpeech() {
		return mentalStSpeech;
	}
	public void setMentalStAttitude(String mentalStAttitude) {
		this.mentalStAttitude = mentalStAttitude;
	}
	public String getMentalStAttitude() {
		return mentalStAttitude;
	}
	public void setMentalStIntellect(String mentalStIntellect) {
		this.mentalStIntellect = mentalStIntellect;
	}
	public String getMentalStIntellect() {
		return mentalStIntellect;
	}
	public void setMentalStImpulse(String mentalStImpulse) {
		this.mentalStImpulse = mentalStImpulse;
	}
	public String getMentalStImpulse() {
		return mentalStImpulse;
	}
	public void setMentalStUnderstanding(String mentalStUnderstanding) {
		this.mentalStUnderstanding = mentalStUnderstanding;
	}
	public String getMentalStUnderstanding() {
		return mentalStUnderstanding;
	}
	public void setMentalStAttention(String mentalStAttention) {
		this.mentalStAttention = mentalStAttention;
	}
	public String getMentalStAttention() {
		return mentalStAttention;
	}
	public void setMentalStSuicidal(String mentalStSuicidal) {
		this.mentalStSuicidal = mentalStSuicidal;
	}
	public String getMentalStSuicidal() {
		return mentalStSuicidal;
	}
	public void setMentalStHomicidal(String mentalStHomicidal) {
		this.mentalStHomicidal = mentalStHomicidal;
	}
	public String getMentalStHomicidal() {
		return mentalStHomicidal;
	}
	public void setMentalStThoughtProc(String mentalStThoughtProc) {
		this.mentalStThoughtProc = mentalStThoughtProc;
	}
	public String getMentalStThoughtProc() {
		return mentalStThoughtProc;
	}
	public void setMentalStMemory(String mentalStMemory) {
		this.mentalStMemory = mentalStMemory;
	}
	public String getMentalStMemory() {
		return mentalStMemory;
	}
	public void setMentalStMovement(String mentalStMovement) {
		this.mentalStMovement = mentalStMovement;
	}
	public String getMentalStMovement() {
		return mentalStMovement;
	}
	public void setMentalStHallucination(Vector mentalStHallucination) {
		this.mentalStHallucination = mentalStHallucination;
	}
	public Vector getMentalStHallucination() {
		return mentalStHallucination;
	}
	public void setMentalStOrientation(Vector mentalStOrientation) {
		this.mentalStOrientation = mentalStOrientation;
	}
	public Vector getMentalStOrientation() {
		return mentalStOrientation;
	}
//	public void setMentalStComments(String mentalStComments) {
//		this.mentalStComments = mentalStComments;
//	}
//	public String getMentalStComments() {
//		return mentalStComments;
//	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}


}
