package com.act.common;

import java.io.Serializable;

public class CounsellingSessionObj implements Serializable{
	
	/**
	 * Generated
	 */
	private static final long serialVersionUID = 368706144974892838L;

	private String  cnslingSessionId,
					counselleeName, 
					cnslrId,
					caseNumber, 
					sessionDate,
					location,
					startTime,
					duration, 
					status,   //NEW, CLOSED, IN PROGRESS
					sessionSetting,
					sessionContents,
					sesionObjective,
					sessionFollowupPrep,
					caseManagement,
					cnslrComments;
	
	private String sAppetite, sSleep, sRapport, sAppearance,
					sMood, sAffect, sThought, sPerceptDisturb,
					sOrientation, sInsight, sJudgement,
					sAppetiteComments, sSleepComments, sRapportComments, sAppearanceComments,
					sMoodComments, sAffectComments, sThoughtComments, sPerceptDisturbComments,
					sOrientationComments, sInsightComments, sJudgementComments;
	
	private String openToCnsling, opentTofutureOutsidePros, openTogrpHome;
	
	public static String COUNSELLING_SESSION_STATUS_NEW = "CounsellingSessionNew";
	public static String COUNSELLING_SESSION_STATUS_CLOSED = "CounsellingSessionClosed";
	public static String COUNSELLING_SESSION_STATUS_IN_PROGRESS = "CounsellingSessionInProgress";
	
	private String therapy;

	public String getCounselleeName() {
		return counselleeName;
	}

	public void setCounselleeName(String counselleeName) {
		this.counselleeName = counselleeName;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public String getTherapyName() {
		return therapy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}

	public String getCnslingSessionId() {
		return cnslingSessionId;
	}

	public void setCnslingSessionId(String cnslingSessionId) {
		this.cnslingSessionId = cnslingSessionId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCaseManagement() {
		return caseManagement;
	}

	public void setCaseManagement(String caseManagement) {
		this.caseManagement = caseManagement;
	}

	public String getCnslrId() {
		return cnslrId;
	}

	public void setCnslrId(String cnslrId) {
		this.cnslrId = cnslrId;
	}

	public String getSessionSetting() {
		return sessionSetting;
	}

	public void setSessionSetting(String sessionSetting) {
		this.sessionSetting = sessionSetting;
	}

	public String getSessionContents() {
		return sessionContents;
	}

	public void setSessionContents(String sessionContents) {
		this.sessionContents = sessionContents;
	}

	public String getSesionObjective() {
		return sesionObjective;
	}

	public void setSesionObjective(String sesionObjective) {
		this.sesionObjective = sesionObjective;
	}

	public String getSessionFollowupPrep() {
		return sessionFollowupPrep;
	}

	public void setSessionFollowupPrep(String sessionFollowupPrep) {
		this.sessionFollowupPrep = sessionFollowupPrep;
	}

	public String getAppetite() {
		return sAppetite;
	}

	public void setAppetite(String sAppetite) {
		this.sAppetite = sAppetite;
	}

	public String getSleep() {
		return sSleep;
	}

	public void setSleep(String sSleep) {
		this.sSleep = sSleep;
	}

	public String getRapport() {
		return sRapport;
	}

	public void setRapport(String sRapport) {
		this.sRapport = sRapport;
	}

	public String getAppearance() {
		return sAppearance;
	}

	public void setAppearance(String sAppearance) {
		this.sAppearance = sAppearance;
	}

	public String getMood() {
		return sMood;
	}

	public void setMood(String sMood) {
		this.sMood = sMood;
	}

	public String getAffect() {
		return sAffect;
	}

	public void setAffect(String sAffect) {
		this.sAffect = sAffect;
	}

	public String getThought() {
		return sThought;
	}

	public void setThought(String sThought) {
		this.sThought = sThought;
	}

	public String getPerceptDisturb() {
		return sPerceptDisturb;
	}

	public void setPerceptDisturb(String sPerceptDisturb) {
		this.sPerceptDisturb = sPerceptDisturb;
	}

	public String getOrientation() {
		return sOrientation;
	}

	public void setOrientation(String sOrientation) {
		this.sOrientation = sOrientation;
	}

	public String getInsight() {
		return sInsight;
	}

	public void setInsight(String sInsight) {
		this.sInsight = sInsight;
	}

	public String getJudgement() {
		return sJudgement;
	}

	public void setJudgement(String sJudgement) {
		this.sJudgement = sJudgement;
	}

	public String getOpenToCnsling() {
		return openToCnsling;
	}

	public void setOpenToCnsling(String openToCnsling) {
		this.openToCnsling = openToCnsling;
	}

	public String getOpentTofutureOutsidePros() {
		return opentTofutureOutsidePros;
	}

	public void setOpentTofutureOutsidePros(String opentTofutureOutsidePros) {
		this.opentTofutureOutsidePros = opentTofutureOutsidePros;
	}

	public String getOpenTogrpHome() {
		return openTogrpHome;
	}

	public void setOpenTogrpHome(String openTogrpHome) {
		this.openTogrpHome = openTogrpHome;
	}

	public String getCnslrComments() {
		return cnslrComments;
	}

	public void setCnslrComments(String cnslrComments) {
		this.cnslrComments = cnslrComments;
	}

	public String getAppetiteComments() {
		return sAppetiteComments;
	}

	public void setAppetiteComments(String sAppetiteComments) {
		this.sAppetiteComments = sAppetiteComments;
	}

	public String getSleepComments() {
		return sSleepComments;
	}

	public void setSleepComments(String sSleepComments) {
		this.sSleepComments = sSleepComments;
	}

	public String getRapportComments() {
		return sRapportComments;
	}

	public void setRapportComments(String sRapportComments) {
		this.sRapportComments = sRapportComments;
	}

	public String getAppearanceComments() {
		return sAppearanceComments;
	}

	public void setAppearanceComments(String sAppearanceComments) {
		this.sAppearanceComments = sAppearanceComments;
	}

	public String getMoodComments() {
		return sMoodComments;
	}

	public void setMoodComments(String sMoodComments) {
		this.sMoodComments = sMoodComments;
	}

	public String getAffectComments() {
		return sAffectComments;
	}

	public void setAffectComments(String sAffectComments) {
		this.sAffectComments = sAffectComments;
	}

	public String getThoughtComments() {
		return sThoughtComments;
	}

	public void setThoughtComments(String sThoughtComments) {
		this.sThoughtComments = sThoughtComments;
	}

	public String getPerceptDisturbComments() {
		return sPerceptDisturbComments;
	}

	public void setPerceptDisturbComments(String sPerceptDisturbComments) {
		this.sPerceptDisturbComments = sPerceptDisturbComments;
	}

	public String getOrientationComments() {
		return sOrientationComments;
	}

	public void setOrientationComments(String sOrientationComments) {
		this.sOrientationComments = sOrientationComments;
	}

	public String getInsightComments() {
		return sInsightComments;
	}

	public void setInsightComments(String sInsightComments) {
		this.sInsightComments = sInsightComments;
	}

	public String getJudgementComments() {
		return sJudgementComments;
	}

	public void setJudgementComments(String sJudgementComments) {
		this.sJudgementComments = sJudgementComments;
	}

}
