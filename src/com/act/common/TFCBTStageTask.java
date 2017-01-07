package com.act.common;

import java.io.Serializable;

public class TFCBTStageTask implements Serializable{

	/**
	 * Serial ver UID
	 */
	private static final long serialVersionUID = 4364149412370701230L;
	
	private String  title, subTitles;
	private int taskId, stageId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitles() {
		return subTitles;
	}

	public void setSubTitles(String subTitles) {
		this.subTitles = subTitles;
	}

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

}
