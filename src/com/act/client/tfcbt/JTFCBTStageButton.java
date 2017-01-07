package com.act.client.tfcbt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import com.act.client.CnsleeSymptChkListsPanel;
import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CounselleeTFCBTStage;
import com.act.common.CnsleeTFCBTTask;
import com.act.common.TFCBTStageMilestone;
import com.act.common.TFCBTStageTask;

public class JTFCBTStageButton extends JButton {
	
	private boolean isAttempted = false;
	private CounselleeTFCBTStage stageObj;
	
	public static Color colorNormal = new Color(255,185,255);
	public static Color colorAttempted = new Color(237,228,65);
	public static Color colorCompleted = new Color(18,188,48);
	
	
	public JTFCBTStageButton(CounselleeTFCBTStage stageObj){
		super();
		
		this.stageObj = stageObj;
		String title = "Stage: "+stageObj.getStageNumber();
		setText(title);
		updateStatus();
		
//		addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				//display the stage details on a panel
//				
//				//stage number
//				
//				//stage title
//				
//				//description
//				
//				//Objective
//				
//				// List of Required tasks 
//				
//				// .... Sub list of subtitles for each task
//				
//				//List of Milestones
//				
//				// .....sublist of milestones
//				
//			}
//		});
	}
	
	public CounselleeTFCBTStage getStageObject(){
		return stageObj;
	}
	
	public Vector<CnsleeTFCBTTask> getStageTasks() {
		return stageObj.getStageTasks();
	}
	public Vector <CnsleeTFCBTMileStone> getvStageMileStones() {
		return stageObj.getStageMileStones();
	}
	public boolean isAttempted() {
		return isAttempted;
	}
	public void setAttempted(boolean isAttempted) {
		this.isAttempted = isAttempted;
	}

	public void updateStatus(){
		if (isAttempted)
			setBackground(colorAttempted);
		else
			setBackground(colorNormal);
	}
}
