package com.act.common;

import java.io.Serializable;

public class CnsleeTFCBTMileStone implements Serializable{

	private static final long serialVersionUID = 3379685092560706260L;

	private String milestoneNotes, 
				dateCompleted;

//	private TFCBTStageMilestone milestoneObj = null;
	
	private String cnsleeId, 
		cnslrId, 
		milestoneDate,
		counsellingSessionId;
	
	private int milestoneId;	
	private int tfcbtStageId;
	private String milestoneStatus = TFCBT_STAGE_MILESTONE_STATUS_NEW;
	
//	private boolean isMilestoneCompleted;
	
	public static String TFCBT_STAGE_MILESTONE_STATUS_NEW = "NEW";
	public static String TFCBT_STAGE_MILESTONE_STATUS_IN_PROGRESS = "IN PROGRESS";
	public static String TFCBT_STAGE_MILESTONE_STATUS_COMPLETED = "COMPLETED";
	
	
	public boolean isNewmilestone(){
		return (milestoneStatus.equals(TFCBT_STAGE_MILESTONE_STATUS_NEW));
	}


	public String getMilestoneNotes() {
		return milestoneNotes;
	}


	public void setMilestoneNotes(String milestoneNotes) {
		this.milestoneNotes = milestoneNotes;
	}


	public String getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}


	public String getCnsleeId() {
		return cnsleeId;
	}


	public void setCnsleeId(String cnsleeId) {
		this.cnsleeId = cnsleeId;
	}


	public String getCnslrId() {
		return cnslrId;
	}


	public void setCnslrId(String cnslrId) {
		this.cnslrId = cnslrId;
	}


	public int getTfcbtStageId() {
		return tfcbtStageId;
	}


	public void setTfcbtStageId(int tfcbtStageId) {
		this.tfcbtStageId = tfcbtStageId;
	}


	public String getMilestoneDate() {
		return milestoneDate;
	}


	public void setMilestoneDate(String milestoneDate) {
		this.milestoneDate = milestoneDate;
	}


	public boolean isMilestoneCompleted() {
		return (milestoneStatus.equals(TFCBT_STAGE_MILESTONE_STATUS_COMPLETED));
	}


	public int getMilestoneId() {
		return milestoneId;
	}


	public void setMilestoneId(int milestoneId) {
		this.milestoneId = milestoneId;
	}


	public String getCounsellingSessionId() {
		return counsellingSessionId;
	}


	public void setCounsellingSessionId(String counsellingSessionId) {
		this.counsellingSessionId = counsellingSessionId;
	}
	

}
