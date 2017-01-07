package com.act.common;

import java.io.Serializable;

public class CnsleeTFCBTTask implements Serializable{
	
	private static final long serialVersionUID = 8800245404375965357L;
	private String taskNotes, 
					dateCompleted;
//	private boolean isTaskCompleted;
	
	private String cnsleeId, 
					cnslrId, 
					taskDate,
					counsellingSessionId;
					
	private int stageId,taskId;
	private String taskStatus = TFCBT_STAGE_TASK_STATUS_NEW;
	
	
	public static String TFCBT_STAGE_TASK_STATUS_NEW = "NEW";
	public static String TFCBT_STAGE_TASK_STATUS_IN_PROGRESS = "IN PROGRESS";
	public static String TFCBT_STAGE_TASK_STATUS_COMPLETED = "COMPLETED";
	
	
	public boolean isNewTask(){
		return (taskStatus.equals(TFCBT_STAGE_TASK_STATUS_NEW));
	}

	public String getTaskNotes() {
		return taskNotes;
	}

	public void setTaskNotes(String taskNotes) {
		this.taskNotes = taskNotes;
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
		return stageId;
	}

	public void setTfcbtStageId(int tfcbtStageId) {
		this.stageId = tfcbtStageId;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public boolean isTaskCompleted() {
		return (taskStatus.equals(TFCBT_STAGE_TASK_STATUS_COMPLETED));
	}

//	public void setTaskCompleted(boolean isTaskCompleted) {
//		this.isTaskCompleted = isTaskCompleted;
//	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getCounsellingSessionId() {
		return counsellingSessionId;
	}

	public void setCounsellingSessionId(String counsellingSessionId) {
		this.counsellingSessionId = counsellingSessionId;
	}

}
