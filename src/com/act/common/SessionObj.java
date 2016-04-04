package com.act.common;

public class SessionObj {
	
	private String counselleeName, caseNumber, sessionDate,
					status, therapyName,comments;

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTherapyName() {
		return therapyName;
	}

	public void setTherapyName(String therapyName) {
		this.therapyName = therapyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
