package com.act.common;

import java.io.Serializable;
import java.util.Vector;

public class CounselleeTFCBTStage implements Serializable{

	private static final long serialVersionUID = 7583233751767996402L;

	private TFCBTStage tfcbtStageObj;
	
	private int stageNumber;
	private Vector<CnsleeTFCBTTask> vStageTasks;
	private Vector <CnsleeTFCBTMileStone> vStageMileStones;
	
	
	public void addCnsleeTFCBTTask(CnsleeTFCBTTask cnsleeTask){
		
		if (vStageTasks == null)
			vStageTasks = new Vector<CnsleeTFCBTTask>();
		
		vStageTasks.add(cnsleeTask);
		
	}
	
	public void addCnsleeTFCBTMilestone(CnsleeTFCBTMileStone cnsleeMilestone){
		
		if (vStageMileStones == null)
			vStageMileStones = new Vector <CnsleeTFCBTMileStone>();
		
		vStageMileStones.add(cnsleeMilestone);
		
	}
	
	public int getStageNumber() {
		return stageNumber;
	}

	public Vector<CnsleeTFCBTTask> getStageTasks() {
		return vStageTasks;
	}
	public void setStageTasks(Vector<CnsleeTFCBTTask> vStageTasks) {
		this.vStageTasks = vStageTasks;
	}
	public Vector <CnsleeTFCBTMileStone> getStageMileStones() {
		return vStageMileStones;
	}
	public void setStageMileStones(Vector <CnsleeTFCBTMileStone> vStageMileStones) {
		this.vStageMileStones = vStageMileStones;
	}
	public TFCBTStage getTfcbtStageObj() {
		return tfcbtStageObj;
	}
	public void setTfcbtStageObj(TFCBTStage tfcbtStageObj) {
		this.tfcbtStageObj = tfcbtStageObj;
	}

	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}


}
