package com.act.common;

import java.io.Serializable;

public class CounselleeReportDataObject implements Serializable {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = 3334285873028780006L;
	
	private String  caseId,
					sCnsleeName, 
					sCnsleeAge,
					sOrganization,
					sLocation,
					sessionId,
					sSessionDate,
					sDuration,
					sSessionContents,
					sTfcbtStage,
					sTfcbtActivities,
					sTfcbtComments,
					sNextObjectives,
					sCaseManagement;

	public String getsCnsleeName() {
		return sCnsleeName;
	}

	public void setsCnsleeName(String sCnsleeName) {
		this.sCnsleeName = sCnsleeName;
	}

	public String getsCnsleeAge() {
		return sCnsleeAge;
	}

	public void setsCnsleeAge(String sCnsleeAge) {
		this.sCnsleeAge = sCnsleeAge;
	}

	public String getsOrganization() {
		return sOrganization;
	}

	public void setsOrganization(String sOrganization) {
		this.sOrganization = sOrganization;
	}

	public String getsLocation() {
		return sLocation;
	}

	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}

	public String getsSessionDate() {
		return sSessionDate;
	}

	public void setsSessionDate(String sSessionDate) {
		this.sSessionDate = sSessionDate;
	}

	public String getsDuration() {
		return sDuration;
	}

	public void setsDuration(String sDuration) {
		this.sDuration = sDuration;
	}

	public String getsSessionContents() {
		return sSessionContents;
	}

	public void setsSessionContents(String sSessionContents) {
		this.sSessionContents = sSessionContents;
	}

	public String getsTfcbtStage() {
		return sTfcbtStage;
	}

	public void setsTfcbtStage(String sTfcbtStage) {
		this.sTfcbtStage = sTfcbtStage;
	}

	public String getsTfcbtActivities() {
		return sTfcbtActivities;
	}

	public void setsTfcbtActivities(String sTfcbtActivities) {
		this.sTfcbtActivities = sTfcbtActivities;
	}

	public String getsTfcbtComments() {
		return sTfcbtComments;
	}

	public void setsTfcbtComments(String sTfcbtComments) {
		this.sTfcbtComments = sTfcbtComments;
	}

	public String getsNextObjectives() {
		return sNextObjectives;
	}

	public void setsNextObjectives(String sNextObjectives) {
		this.sNextObjectives = sNextObjectives;
	}

	public String getsCaseManagement() {
		return sCaseManagement;
	}

	public void setsCaseManagement(String sCaseManagement) {
		this.sCaseManagement = sCaseManagement;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
