package com.act.common;

import java.io.Serializable;

public class TSC54Obj extends MHSymptChkListObj implements Serializable{
	
	private static final long serialVersionUID = -6716054809734512529L;
	
	private int nBadDreams, nFeelAfraidOfBad, nScaryIdeas, nDirtyWords,
					nPretend, nArgue, nFeelLonely, nTouchPrivParts, nFeelSad,
					nRemPastThings, nGoingAway, nRemScaryThings, nYell, nCrying,
					nSuddenFear, nGettingMad, nThinkAbtSx, nFeelDizzy, nYellOthers,
					nHurtSelf, nHurtOthers, nTouchOtherPrivParts, nThinkSx, nFearMen,
					nFearWomen, nWash, nFeelStupid, nFeelGuilt, nFeelUnreal, nForgetThings,
					nFeelNotInBody, nFeelNervous, nFeelAfraid, nNotTrustPeople, nThinkBadPast,
					nFights, nFeelMean, nPretendSomewhereElse, nFearDark, nUpsetAbtSx,
					nWorry, nFeelNooneLikesMe, nRemThings, nFeelSx, nMindEmpty, 
					nFeelHate, nCantStopThinkAbtSx, nTryNoFeelings, nFeelMad, nFeelKill, 
					nWishBadDinHappen, nWantToKillSelf, nDayDream, nUpsetTalkAbtSx;
	private String sCaseNumber,assessmentId;
	
	public TSC54Obj(){
		setChkListType(SYMPT_CKLIST_TYPE_TSC54);
	}

	public String getCaseId() {
		return sCaseNumber;
	}

	public void setCaseId(String nCaseNumber) {
		this.sCaseNumber = sCaseNumber;
	}
	
	public int getBadDreams() {
		return nBadDreams;
	}

	public void setBadDreams(int nBadDreams) {
		this.nBadDreams = nBadDreams;
	}

	public int getFeelAfraidOfBad() {
		return nFeelAfraidOfBad;
	}

	public void setFeelAfraidOfBad(int nFeelAfraidOfBad) {
		this.nFeelAfraidOfBad = nFeelAfraidOfBad;
	}

	public int getScaryIdeas() {
		return nScaryIdeas;
	}

	public void setScaryIdeas(int nScaryIdeas) {
		this.nScaryIdeas = nScaryIdeas;
	}

	public int getDirtyWords() {
		return nDirtyWords;
	}

	public void setDirtyWords(int nDirtyWords) {
		this.nDirtyWords = nDirtyWords;
	}

	public int getPretend() {
		return nPretend;
	}

	public void setPretend(int nPretend) {
		this.nPretend = nPretend;
	}

	public int getArgue() {
		return nArgue;
	}

	public void setArgue(int nArgue) {
		this.nArgue = nArgue;
	}

	public int getFeelLonely() {
		return nFeelLonely;
	}

	public void setFeelLonely(int nFeelLonely) {
		this.nFeelLonely = nFeelLonely;
	}

	public int getTouchPrivParts() {
		return nTouchPrivParts;
	}

	public void setTouchPrivParts(int nTouchPrivParts) {
		this.nTouchPrivParts = nTouchPrivParts;
	}

	public int getFeelSad() {
		return nFeelSad;
	}

	public void setFeelSad(int nFeelSad) {
		this.nFeelSad = nFeelSad;
	}

	public int getRemPastThings() {
		return nRemPastThings;
	}

	public void setRemPastThings(int nRemPastThings) {
		this.nRemPastThings = nRemPastThings;
	}

	public int getGoingAway() {
		return nGoingAway;
	}

	public void setGoingAway(int nGoingAway) {
		this.nGoingAway = nGoingAway;
	}

	public int getRemScaryThings() {
		return nRemScaryThings;
	}

	public void setRemScaryThings(int nRemScaryThings) {
		this.nRemScaryThings = nRemScaryThings;
	}

	public int getYell() {
		return nYell;
	}

	public void setYell(int nYell) {
		this.nYell = nYell;
	}

	public int getCrying() {
		return nCrying;
	}

	public void setCrying(int nCrying) {
		this.nCrying = nCrying;
	}

	public int getSuddenFear() {
		return nSuddenFear;
	}

	public void setSuddenFear(int nSuddenFear) {
		this.nSuddenFear = nSuddenFear;
	}

	public int getGettingMad() {
		return nGettingMad;
	}

	public void setGettingMad(int nGettingMad) {
		this.nGettingMad = nGettingMad;
	}

	public int getThinkAbtSx() {
		return nThinkAbtSx;
	}

	public void setThinkAbtSx(int nThinkAbtSx) {
		this.nThinkAbtSx = nThinkAbtSx;
	}

	public int getFeelDizzy() {
		return nFeelDizzy;
	}

	public void setFeelDizzy(int nFeelDizzy) {
		this.nFeelDizzy = nFeelDizzy;
	}

	public int getYellOthers() {
		return nYellOthers;
	}

	public void setYellOthers(int nYellOthers) {
		this.nYellOthers = nYellOthers;
	}

	public int getHurtSelf() {
		return nHurtSelf;
	}

	public void setHurtSelf(int nHurtSelf) {
		this.nHurtSelf = nHurtSelf;
	}

	public int getHurtOthers() {
		return nHurtOthers;
	}

	public void setHurtOthers(int nHurtOthers) {
		this.nHurtOthers = nHurtOthers;
	}

	public int getTouchOtherPrivParts() {
		return nTouchOtherPrivParts;
	}

	public void setTouchOtherPrivParts(int nTouchOtherPrivParts) {
		this.nTouchOtherPrivParts = nTouchOtherPrivParts;
	}

	public int getThinkSx() {
		return nThinkSx;
	}

	public void setThinkSx(int nThinkSx) {
		this.nThinkSx = nThinkSx;
	}

	public int getFearMen() {
		return nFearMen;
	}

	public void setFearMen(int nFearMen) {
		this.nFearMen = nFearMen;
	}

	public int getFearWomen() {
		return nFearWomen;
	}

	public void setFearWomen(int nFearWomen) {
		this.nFearWomen = nFearWomen;
	}

	public int getWash() {
		return nWash;
	}

	public void setWash(int nWash) {
		this.nWash = nWash;
	}

	public int getFeelStupid() {
		return nFeelStupid;
	}

	public void setFeelStupid(int nFeelStupid) {
		this.nFeelStupid = nFeelStupid;
	}

	public int getFeelGuilt() {
		return nFeelGuilt;
	}

	public void setFeelGuilt(int nFeelGuilt) {
		this.nFeelGuilt = nFeelGuilt;
	}

	public int getFeelUnreal() {
		return nFeelUnreal;
	}

	public void setFeelUnreal(int nFeelUnreal) {
		this.nFeelUnreal = nFeelUnreal;
	}

	public int getForgetThings() {
		return nForgetThings;
	}

	public void setForgetThings(int nForgetThings) {
		this.nForgetThings = nForgetThings;
	}

	public int getFeelNotInBody() {
		return nFeelNotInBody;
	}

	public void setFeelNotInBody(int nFeelNotInBody) {
		this.nFeelNotInBody = nFeelNotInBody;
	}

	public int getFeelNervous() {
		return nFeelNervous;
	}

	public void setFeelNervous(int nFeelNervous) {
		this.nFeelNervous = nFeelNervous;
	}

	public int getFeelAfraid() {
		return nFeelAfraid;
	}

	public void setFeelAfraid(int nFeelAfraid) {
		this.nFeelAfraid = nFeelAfraid;
	}

	public int getNotTrustPeople() {
		return nNotTrustPeople;
	}

	public void setNotTrustPeople(int nNotTrustPeople) {
		this.nNotTrustPeople = nNotTrustPeople;
	}

	public int getThinkBadPast() {
		return nThinkBadPast;
	}

	public void setThinkBadPast(int nThinkBadPast) {
		this.nThinkBadPast = nThinkBadPast;
	}

	public int getFights() {
		return nFights;
	}

	public void setFights(int nFights) {
		this.nFights = nFights;
	}

	public int getFeelMean() {
		return nFeelMean;
	}

	public void setFeelMean(int nFeelMean) {
		this.nFeelMean = nFeelMean;
	}

	public int getPretendSomewhereElse() {
		return nPretendSomewhereElse;
	}

	public void setPretendSomewhereElse(int nPretendSomewhereElse) {
		this.nPretendSomewhereElse = nPretendSomewhereElse;
	}

	public int getFearDark() {
		return nFearDark;
	}

	public void setFearDark(int nFearDark) {
		this.nFearDark = nFearDark;
	}

	public int getUpsetAbtSx() {
		return nUpsetAbtSx;
	}

	public void setUpsetAbtSx(int nUpsetAbtSx) {
		this.nUpsetAbtSx = nUpsetAbtSx;
	}

	public int getWorry() {
		return nWorry;
	}

	public void setWorry(int nWorry) {
		this.nWorry = nWorry;
	}

	public int getFeelNooneLikesMe() {
		return nFeelNooneLikesMe;
	}

	public void setFeelNooneLikesMe(int nFeelNooneLikesMe) {
		this.nFeelNooneLikesMe = nFeelNooneLikesMe;
	}

	public int getRemThings() {
		return nRemThings;
	}

	public void setRemThings(int nRemThings) {
		this.nRemThings = nRemThings;
	}

	public int getFeelSx() {
		return nFeelSx;
	}

	public void setFeelSx(int nFeelSx) {
		this.nFeelSx = nFeelSx;
	}

	public int getMindEmpty() {
		return nMindEmpty;
	}

	public void setMindEmpty(int nMindEmpty) {
		this.nMindEmpty = nMindEmpty;
	}

	public int getFeelHate() {
		return nFeelHate;
	}

	public void setFeelHate(int nFeelHate) {
		this.nFeelHate = nFeelHate;
	}

	public int getCantStopThinkAbtSx() {
		return nCantStopThinkAbtSx;
	}

	public void setCantStopThinkAbtSx(int nCantStopThinkAbtSx) {
		this.nCantStopThinkAbtSx = nCantStopThinkAbtSx;
	}

	public int getTryNoFeelings() {
		return nTryNoFeelings;
	}

	public void setTryNoFeelings(int nTryNoFeelings) {
		this.nTryNoFeelings = nTryNoFeelings;
	}

	public int getFeelMad() {
		return nFeelMad;
	}

	public void setFeelMad(int nFeelMad) {
		this.nFeelMad = nFeelMad;
	}

	public int getFeelKill() {
		return nFeelKill;
	}

	public void setFeelKill(int nFeelKill) {
		this.nFeelKill = nFeelKill;
	}

	public int getWishBadDinHappen() {
		return nWishBadDinHappen;
	}

	public void setWishBadDinHappen(int nWishBadDinHappen) {
		this.nWishBadDinHappen = nWishBadDinHappen;
	}

	public int getWantToKillSelf() {
		return nWantToKillSelf;
	}

	public void setWantToKillSelf(int nWantToKillSelf) {
		this.nWantToKillSelf = nWantToKillSelf;
	}

	public int getDayDream() {
		return nDayDream;
	}

	public void setDayDream(int nDayDream) {
		this.nDayDream = nDayDream;
	}

	public int getUpsetTalkAbtSx() {
		return nUpsetTalkAbtSx;
	}

	public void setUpsetTalkAbtSx(int nUpsetTalkAbtSx) {
		this.nUpsetTalkAbtSx = nUpsetTalkAbtSx;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}


}
