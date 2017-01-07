package com.act.common;

import java.io.Serializable;

import com.act.client.JComboBoxSymptoms;

public class TSC40Obj extends MHSymptChkListObj {
	
	
	private static final long serialVersionUID = 7963487726828204528L;

	private int nHeadAche, nInsomnia, nWghtLoss, nStomachProb, nSexProb, nIsolation, 
					nFlashBack, nRestlssSleep, nLowSxDrive, nLoneliness, nNightmares, nSpaceOut,
					nAnxAttck, nSadness, nDizziness, nDissatSxDrive, nCtrlTemper, nWakeEarly,
					nSxOveract, nUncontrolCry, nFearMen, nNotRestMorn, nSxNoEnjoy, nTrblGetAlong,
					nMemProb, nPhysicHurt, nFearWomen, nWakeMidnight, nBadThoughtsSx, 
					nPassOut, nUnrealFeel, nFreqWash, nInferiority, nTension, nSxConfusion,
					nHurtOthers, nGuilt, nFeelNotInBody, nBreatheTrouble, nSxFeelingUntimely;
	
	private String caseId;//, assessmentId ;

	public TSC40Obj(){
		setChkListType(SYMPT_CKLIST_TYPE_TSC40);
	}
	
	public int getHeadAche() {
		return nHeadAche;
	}

	public void setHeadAche(int nHeadAche) {
		this.nHeadAche = nHeadAche;
	}

	public int getInsomnia() {
		return nInsomnia;
	}

	public void setInsomnia(int nInsomnia) {
		this.nInsomnia = nInsomnia;
	}

	public int getWghtLoss() {
		return nWghtLoss;
	}

	public void setWghtLoss(int nWghtLoss) {
		this.nWghtLoss = nWghtLoss;
	}

	public int getStomachProb() {
		return nStomachProb;
	}

	public void setStomachProb(int nStomachProb) {
		this.nStomachProb = nStomachProb;
	}

	public int getSexProb() {
		return nSexProb;
	}

	public void setSexProb(int nSexProb) {
		this.nSexProb = nSexProb;
	}

	public int getIsolation() {
		return nIsolation;
	}

	public void setIsolation(int nIsolation) {
		this.nIsolation = nIsolation;
	}

	public int getFlashBack() {
		return nFlashBack;
	}

	public void setFlashBack(int nFlashBack) {
		this.nFlashBack = nFlashBack;
	}

	public int getRestlssSleep() {
		return nRestlssSleep;
	}

	public void setRestlssSleep(int nRestlssSleep) {
		this.nRestlssSleep = nRestlssSleep;
	}

	public int getLowSxDrive() {
		return nLowSxDrive;
	}

	public void setLowSxDrive(int nLowSxDrive) {
		this.nLowSxDrive = nLowSxDrive;
	}

	public int getLoneliness() {
		return nLoneliness;
	}

	public void setLoneliness(int nLoneliness) {
		this.nLoneliness = nLoneliness;
	}

	public int getNightmares() {
		return nNightmares;
	}

	public void setNightmares(int nNightmares) {
		this.nNightmares = nNightmares;
	}

	public int getSpaceOut() {
		return nSpaceOut;
	}

	public void setSpaceOut(int nSpaceOut) {
		this.nSpaceOut = nSpaceOut;
	}

	public int getAnxAttck() {
		return nAnxAttck;
	}

	public void setAnxAttck(int nAnxAttck) {
		this.nAnxAttck = nAnxAttck;
	}

	public int getSadness() {
		return nSadness;
	}

	public void setSadness(int nSadness) {
		this.nSadness = nSadness;
	}

	public int getDizziness() {
		return nDizziness;
	}

	public void setDizziness(int nDizziness) {
		this.nDizziness = nDizziness;
	}

	public int getDissatSxDrive() {
		return nDissatSxDrive;
	}

	public void setDissatSxDrive(int nDissatSxDrive) {
		this.nDissatSxDrive = nDissatSxDrive;
	}

	public int getCtrlTemper() {
		return nCtrlTemper;
	}

	public void setCtrlTemper(int nCtrlTemper) {
		this.nCtrlTemper = nCtrlTemper;
	}

	public int getWakeEarly() {
		return nWakeEarly;
	}

	public void setWakeEarly(int nWakeEarly) {
		this.nWakeEarly = nWakeEarly;
	}

	public int getSxOveract() {
		return nSxOveract;
	}

	public void setSxOveract(int nSxOveract) {
		this.nSxOveract = nSxOveract;
	}

	public int getUncontrolCry() {
		return nUncontrolCry;
	}

	public void setUncontrolCry(int nUncontrolCry) {
		this.nUncontrolCry = nUncontrolCry;
	}

	public int getFearMen() {
		return nFearMen;
	}

	public void setFearMen(int nFearMen) {
		this.nFearMen = nFearMen;
	}

	public int getNotRestMorn() {
		return nNotRestMorn;
	}

	public void setNotRestMorn(int nNotRestMorn) {
		this.nNotRestMorn = nNotRestMorn;
	}

	public int getSxNoEnjoy() {
		return nSxNoEnjoy;
	}

	public void setSxNoEnjoy(int nSxNoEnjoy) {
		this.nSxNoEnjoy = nSxNoEnjoy;
	}

	public int getTrblGetAlong() {
		return nTrblGetAlong;
	}

	public void setTrblGetAlong(int nTrblGetAlong) {
		this.nTrblGetAlong = nTrblGetAlong;
	}

	public int getMemProb() {
		return nMemProb;
	}

	public void setMemProb(int nMemProb) {
		this.nMemProb = nMemProb;
	}

	public int getPhysicHurt() {
		return nPhysicHurt;
	}

	public void setPhysicHurt(int nPhysicHurt) {
		this.nPhysicHurt = nPhysicHurt;
	}

	public int getFearWomen() {
		return nFearWomen;
	}

	public void setFearWomen(int nFearWomen) {
		this.nFearWomen = nFearWomen;
	}

	public int getWakeMidnight() {
		return nWakeMidnight;
	}

	public void setWakeMidnight(int nWakeMidnight) {
		this.nWakeMidnight = nWakeMidnight;
	}

	public int getBadThoughtsSx() {
		return nBadThoughtsSx;
	}

	public void setBadThoughtsSx(int nBadThoughtsSx) {
		this.nBadThoughtsSx = nBadThoughtsSx;
	}

	public int getPassOut() {
		return nPassOut;
	}

	public void setPassOut(int nPassOut) {
		this.nPassOut = nPassOut;
	}

	public int getUnrealFeel() {
		return nUnrealFeel;
	}

	public void setUnrealFeel(int nUnrealFeel) {
		this.nUnrealFeel = nUnrealFeel;
	}

	public int getFreqWash() {
		return nFreqWash;
	}

	public void setFreqWash(int nFreqWash) {
		this.nFreqWash = nFreqWash;
	}

	public int getInferiority() {
		return nInferiority;
	}

	public void setInferiority(int nInferiority) {
		this.nInferiority = nInferiority;
	}

	public int getTension() {
		return nTension;
	}

	public void setTension(int nTension) {
		this.nTension = nTension;
	}

	public int getSxConfusion() {
		return nSxConfusion;
	}

	public void setSxConfusion(int nSxConfusion) {
		this.nSxConfusion = nSxConfusion;
	}

	public int getHurtOthers() {
		return nHurtOthers;
	}

	public void setHurtOthers(int nHurtOthers) {
		this.nHurtOthers = nHurtOthers;
	}

	public int getGuilt() {
		return nGuilt;
	}

	public void setGuilt(int nGuilt) {
		this.nGuilt = nGuilt;
	}

	public int getFeelNotInBody() {
		return nFeelNotInBody;
	}

	public void setFeelNotInBody(int nFeelNotInBody) {
		this.nFeelNotInBody = nFeelNotInBody;
	}

	public int getBreatheTrouble() {
		return nBreatheTrouble;
	}

	public void setBreatheTrouble(int nBreatheTrouble) {
		this.nBreatheTrouble = nBreatheTrouble;
	}

	public int getSxFeelingUntimely() {
		return nSxFeelingUntimely;
	}

	public void setSxFeelingUntimely(int nSxFeelingUntimely) {
		this.nSxFeelingUntimely = nSxFeelingUntimely;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

//	public String getAssessmentId() {
//		return assessmentId;
//	}
//
//	public void setAssessmentId(String assessmentId) {
//		this.assessmentId = assessmentId;
//	}


}
