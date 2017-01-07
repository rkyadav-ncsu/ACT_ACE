package com.act.common;

import java.io.Serializable;
import java.util.Vector;

public class TFCBTStage implements Serializable{
	/**
	 * Serial ver UID
	 */
	private static final long serialVersionUID = -7318640775794317398L;

	private int stageNumber;
	
	private String title, description, objective;
	
	public int getStageNumber() {
		return stageNumber;
	}
	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}


}
