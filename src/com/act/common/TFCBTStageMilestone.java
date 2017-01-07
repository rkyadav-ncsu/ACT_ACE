package com.act.common;

import java.io.Serializable;

public class TFCBTStageMilestone implements Serializable {

	/**
	 * Generated serial ver UID
	 */
	private static final long serialVersionUID = 8816390881808082891L;
	
	private String  title, subTitles;
	private int milestoneId, stageId;

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

	public int getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(int milestoneId) {
		this.milestoneId = milestoneId;
	}
}
