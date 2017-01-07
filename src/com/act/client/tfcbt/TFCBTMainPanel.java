package com.act.client.tfcbt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.act.client.ACEConnector;
import com.act.client.MHPanel;
import com.act.client.MHPanelObserver;
import com.act.client.models.MHMainTableModel;
import com.act.common.Counsellee;
import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CnsleeTFCBTTask;
import com.act.common.SwingUtils;
import com.act.common.CounselleeTFCBTStage;
import com.act.common.TFCBTStageMilestone;
import com.act.common.TFCBTStageTask;

public class TFCBTMainPanel extends MHPanel implements
												ActionListener,
												MouseListener{
	
	JButton btnClose;
	
	JPanel panelTitle, panelCenter, panelBottom,
			panelStageButtons, panelStage, panelTask, 
			panelButtons;
	
	JLabel labelTitle;
	JTFCBTStageButton btnStage1,
						btnStage2,
						btnStage3,
						btnStage4,
						btnStage5,
						btnStage6,
						btnStage7,
						btnStage8,
						btnStage9,
						btnStage10;
	JLabel stageTitle;					
	JLabel titleStageObjective, stageObjective;
	
	MHPanelObserver panelObserver; 
	Counsellee cnslee;
	JTable tblStageTaskInfo,
			tblStageMilestoneInfo;
	TFCBTStageTaskModel modelStageTask;
	TFCBTStageMilestoneModel modelStageMilestone;
	private String cnslingSessionId;
	
	
	public TFCBTMainPanel(Counsellee cnslee,String cnslingSessionId){
		this.cnslee = cnslee;
		setCnslingSessionId(cnslingSessionId);
		
		try {
			initUI();
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

	private void initUI(){
		//set parent panel layout
		setLayout(new BorderLayout());

		panelTitle = new JPanel();
		add(panelTitle, BorderLayout.NORTH);
		panelTitle.setBackground(new Color (211, 239, 245));
		panelTitle.setPreferredSize(new Dimension(100, 50));
		panelTitle.setLayout(new GridBagLayout());
		
		//Title
		labelTitle = new JLabel("TF-CBT  (Trauma Focussed Cognitive Behavioural Therapy) for " +
				cnslee.getName().getFormattedName());
		panelTitle.add(labelTitle,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		labelTitle.setFont(new Font(Font.SANS_SERIF, labelTitle.getFont().getStyle(), 20));
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		add(panelCenter, BorderLayout.CENTER);
		
		//Buttons panel
		panelStageButtons = new JPanel();
		panelStageButtons.setLayout(new FlowLayout());
		panelCenter.add(panelStageButtons, BorderLayout.NORTH);
		
		
		Hashtable<String, CounselleeTFCBTStage> htCnsleeStages = ACEConnector
												.getInstance().
												getCounselleeTFCBTStages(cnslee.getCaseNumber());
		
		CounselleeTFCBTStage cnsleeStage = htCnsleeStages.get(String.valueOf(1));
		System.out.println("///////////// cnsleeStage " + cnsleeStage.getStageNumber());
//		System.out.println("///////////// cnsleeStage " + cnsleeStage.getStageNumber());
		btnStage1 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(1)));
		panelStageButtons.add(btnStage1);
		btnStage1.addActionListener(this);
		updateButtonColor(btnStage1);
		
		btnStage2 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(2)));
		panelStageButtons.add(btnStage2);
		btnStage2.addActionListener(this);
		updateButtonColor(btnStage2);
		
		btnStage3 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(3)));
		panelStageButtons.add(btnStage3);
		btnStage3.addActionListener(this);
		updateButtonColor(btnStage3);
		

		btnStage4 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(4)));
		panelStageButtons.add(btnStage4);
		btnStage4.addActionListener(this);
		updateButtonColor(btnStage4);
		

		btnStage5 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(5)));
		panelStageButtons.add(btnStage5);
		btnStage5.addActionListener(this);
		updateButtonColor(btnStage5);
		

		btnStage6 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(6)));
		panelStageButtons.add(btnStage6);
		btnStage6.addActionListener(this);
		updateButtonColor(btnStage6);
		

		btnStage7 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(7)));
		panelStageButtons.add(btnStage7);
		btnStage7.addActionListener(this);
		updateButtonColor(btnStage7);
		

		btnStage8 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(8)));
		panelStageButtons.add(btnStage8);
		btnStage8.addActionListener(this);
		updateButtonColor(btnStage8);
		

		btnStage9 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(9)));
		panelStageButtons.add(btnStage9);
		btnStage9.addActionListener(this);
		updateButtonColor(btnStage9);
		
		btnStage10 = new JTFCBTStageButton(htCnsleeStages.get(String.valueOf(10)));
		panelStageButtons.add(btnStage10);
		btnStage10.addActionListener(this);
		updateButtonColor(btnStage10);
		
		
		//Stage Panel (center)
		panelStage = new JPanel();
		panelStage.setLayout(new GridBagLayout());
		panelCenter.add(panelStage, BorderLayout.CENTER);
		
		//Stage Title
		JPanel panelStageTitle = new JPanel();
		panelStageTitle.setBackground(new Color (0,193,193));
		panelStageTitle.setPreferredSize(new Dimension(100, 35));
		panelStage.add(panelStageTitle,SwingUtils.getConstraints(0, 0, 
				GridBagConstraints.REMAINDER, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 0, 0, 0));
		
		stageTitle = new JLabel("TFCBT Stage Details");
		stageTitle.setFont(new Font(stageTitle.getFont().getFontName(),stageTitle.getFont().getStyle(), 18));
		panelStageTitle.add(stageTitle);
		
		//Stage Tasks
		
		//Stage Objective
		titleStageObjective = new JLabel("Objective: ");
		panelStage.add(titleStageObjective,SwingUtils.getConstraints(1, 0, 
				0, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				5, 10, 10, 5));
		
		stageObjective = new JLabel("");
//		panelStage.add(stageObjective,SwingUtils.getConstraints(1, 1, 
//				GridBagConstraints.REMAINDER, 0,0, 
//				GridBagConstraints.WEST, 
//				GridBagConstraints.HORIZONTAL, 
//				5, 5, 10, 10));
		
		//Stage Tasks
		JLabel titleRequiredTasks = new JLabel("Required Tasks");
		panelStage.add(titleRequiredTasks,SwingUtils.getConstraints(2, 0, 
						GridBagConstraints.REMAINDER, 0,0, 
						GridBagConstraints.LINE_START, 
						GridBagConstraints.HORIZONTAL, 
						0, 0, 0, 0));
		
		
		modelStageTask = new TFCBTStageTaskModel();
		//TODO Send the stage number to the server
//		modelStageTask.setList(ACEConnector.getInstance().getCnsleeTFCBTStageTasks(new Hashtable()));
		tblStageTaskInfo = new JTable(modelStageTask);
		JScrollPane scrollPaneTbl = new JScrollPane(tblStageTaskInfo);
		tblStageTaskInfo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneTbl.setBackground(Color.BLUE);
		
		tblStageTaskInfo.addMouseListener(this);
		
		panelStage.add(scrollPaneTbl,SwingUtils.getConstraints(3, 0, 
				GridBagConstraints.REMAINDER, 2,2, 
				GridBagConstraints.LINE_START, 
				GridBagConstraints.BOTH, 
				5, 5, 0, 10));
		
		//Stage Milestones
		JLabel titleRequiredMilestones = new JLabel("Required Milestones");
		panelStage.add(titleRequiredMilestones,SwingUtils.getConstraints(4, 0, 
						GridBagConstraints.REMAINDER, 0,0, 
						GridBagConstraints.LINE_START, 
						GridBagConstraints.HORIZONTAL, 
						0, 0, 0, 0));
		
		
		modelStageMilestone = new TFCBTStageMilestoneModel();
		//TODO Send the stage number to the server
//		modelStageMilestone.setList(ACEConnector.getInstance().getCnsleeTFCBTStageMilestones(new Hashtable()));
		tblStageMilestoneInfo = new JTable(modelStageMilestone);
		JScrollPane scrollPaneTblMileStones = new JScrollPane(tblStageMilestoneInfo);
		tblStageMilestoneInfo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneTblMileStones.setBackground(Color.BLUE);
		
		panelStage.add(scrollPaneTblMileStones,SwingUtils.getConstraints(5, 0, 
				GridBagConstraints.REMAINDER, 2,2, 
				GridBagConstraints.LINE_START, 
				GridBagConstraints.BOTH, 
				5, 5, 0, 10));
		
		tblStageMilestoneInfo.addMouseListener(this);
		
		
		//Bottom Panel
		panelBottom = new JPanel();
		panelBottom.setLayout(new FlowLayout());
		add(panelBottom, BorderLayout.SOUTH);
		
		//close button
		btnClose = new JButton("Close");
		panelBottom.add(btnClose);
		btnClose.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose){
			
			panelObserver.removePanel();
			
		}else if(e.getSource() instanceof JTFCBTStageButton){
			
			JTFCBTStageButton btn = (JTFCBTStageButton)e.getSource();
			//set the stage details
			setStageDetails(btn);
		}
	}

	private void setStageDetails(JTFCBTStageButton stageButton){
		
		if(stageButton ==null)
			return;
		
		try{
			
			//set the stage details
			CounselleeTFCBTStage stageObj = stageButton.getStageObject();

			titleStageObjective.setText("Objective :" + stageObj.getTfcbtStageObj().getObjective() );
			stageTitle.setText("TF-CBT Stage: " + String.valueOf(stageObj.getStageNumber())+ ": " + stageObj.getTfcbtStageObj().getTitle());

			//Get the required tasks, for filling up the rows of the table
			Vector <CnsleeTFCBTTaskRowObj> vTaskRowObjs = getTaskRowObjects(stageObj);
			
			//set the row objects list into the model
			modelStageTask.setList(vTaskRowObjs);
			
			//TODO similarly write a method to get milestones also
			modelStageMilestone.setList(getMilestoneRowObjs(stageObj));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Vector<CnsleeTFCBTMilestoneRowObj> getMilestoneRowObjs(CounselleeTFCBTStage cnsleeStageObj) {
		
		Vector <CnsleeTFCBTMilestoneRowObj> vMilestoneRowObjs = new Vector<CnsleeTFCBTMilestoneRowObj>();
		
		//Get the tfcbt Milestones titles for the stage
		Vector<TFCBTStageMilestone> vTFCBTStageMilestones = ACEConnector.getInstance().getTFCBTStageMilestones(cnsleeStageObj.getStageNumber());

		//Get the cnslee stage Milestone details
		Vector<CnsleeTFCBTMileStone> vCnsleeMilestones = cnsleeStageObj.getStageMileStones();
		
		if (vCnsleeMilestones == null ){
			vCnsleeMilestones = new Vector<CnsleeTFCBTMileStone>();
		}
		
		//If the TFCBT Milestone object is not set into the object, get it and set it.
		//This is required while showing the Milestone details in the table
		for (int i = 0; i < vTFCBTStageMilestones.size(); i++) {
			
			TFCBTStageMilestone MilestoneObj = vTFCBTStageMilestones.elementAt(i);
			boolean bMilestoneExists = false;
			
			CnsleeTFCBTMilestoneRowObj MilestoneRowObj = new CnsleeTFCBTMilestoneRowObj();
			MilestoneRowObj.setMilestoneObj(MilestoneObj);
			
			for (int j = 0; j < vCnsleeMilestones.size(); j++) {
				CnsleeTFCBTMileStone cnsleeMilestone = (CnsleeTFCBTMileStone)vCnsleeMilestones.elementAt(j);
				
				if (cnsleeMilestone.getMilestoneId() == MilestoneObj.getMilestoneId()){
					
					/////This step is extremely critical in ensuring that  		///////
					//////the latest cnslee milestone displayed to the user 			//////
					////// since we store every session cnslee milestone to the server  //////////
					CnsleeTFCBTMileStone existingCnsleeMilestone = MilestoneRowObj.getCnsleeMilestoneObj();
					
					if (existingCnsleeMilestone != null )
						System.out.println("existingCnsleeMilestone.getTaskDate(): " + existingCnsleeMilestone.getMilestoneDate());
					
					System.out.println("existingCnsleeMilestone.getTaskDate() : " + cnsleeMilestone.getMilestoneDate());
					
					if (existingCnsleeMilestone != null){
						
						if (existingCnsleeMilestone.getMilestoneDate().compareTo(cnsleeMilestone.getMilestoneDate()) <0){
							//set the latest
							MilestoneRowObj.setCnsleeMilestoneObj(cnsleeMilestone);
						}
					}else{
						MilestoneRowObj.setCnsleeMilestoneObj(cnsleeMilestone);
					}
					
					bMilestoneExists = true;
					break;
				}
				
			}
			
			if (!bMilestoneExists){
				CnsleeTFCBTMileStone cnsleeMilestone = new CnsleeTFCBTMileStone();
				cnsleeMilestone.setMilestoneId( MilestoneObj.getMilestoneId());
				cnsleeMilestone.setTfcbtStageId(MilestoneObj.getStageId());
				MilestoneRowObj.setCnsleeMilestoneObj(cnsleeMilestone);
			}
			
			vMilestoneRowObjs.addElement(MilestoneRowObj);
		}
		return vMilestoneRowObjs;
	}

	private Vector<CnsleeTFCBTTaskRowObj> getTaskRowObjects(CounselleeTFCBTStage cnsleeStageObj) {
		
		Vector <CnsleeTFCBTTaskRowObj> vTaskRowObjs = new Vector<CnsleeTFCBTTaskRowObj>();
		
		//Get the tfcbt tasks titles for the stage
		Vector<TFCBTStageTask> vTFCBTStageTasks = ACEConnector.getInstance().getTFCBTStageTasks(cnsleeStageObj.getStageNumber());

		//Get the cnslee stage task details
		Vector<CnsleeTFCBTTask> vCnsleeTasks = cnsleeStageObj.getStageTasks();
		
		if (vCnsleeTasks == null ){
			vCnsleeTasks = new Vector<CnsleeTFCBTTask>();
		}
		
		//If the TFCBT Task object is not set into the object, get it and set it.
		//This is required while showing the task details in the table
		
		for (int i = 0; i < vTFCBTStageTasks.size(); i++) {
			
			TFCBTStageTask taskObj = vTFCBTStageTasks.elementAt(i);
			boolean bTaskExists = false;
			
			CnsleeTFCBTTaskRowObj taskRowObj = new CnsleeTFCBTTaskRowObj();
			taskRowObj.setTaskObj(taskObj);
			
			for (int j = 0; j < vCnsleeTasks.size(); j++) {
				CnsleeTFCBTTask cnsleeTask = (CnsleeTFCBTTask)vCnsleeTasks.elementAt(j);
				
				if (cnsleeTask.getTaskId() == taskObj.getTaskId()){
					
					//set the latest cnlee task object into the row
					
					/////This step is extremely critical in ensuring that  		///////
					//////the latest cnslee task displayed to the user 			//////
					////// since we store every session cnslee task to the server  //////////
					CnsleeTFCBTTask existingCnsleeTask = taskRowObj.getCnsleeTaskObj();
					
					if (existingCnsleeTask != null )
						System.out.println("existingCnsleeTask.getTaskDate(): " + existingCnsleeTask.getTaskDate());
					
					System.out.println("cnsleeTask.getTaskDate() : " + cnsleeTask.getTaskDate());
					
					if (existingCnsleeTask != null){
						
						if (existingCnsleeTask.getTaskDate().compareTo(cnsleeTask.getTaskDate()) <0){
							//set the latest
							taskRowObj.setCnsleeTaskObj(cnsleeTask);
						}
					}else{
						taskRowObj.setCnsleeTaskObj(cnsleeTask);
					}
					
					bTaskExists = true;
					break;
				}
				
			}
			
			if (!bTaskExists){
				CnsleeTFCBTTask cnsleeTask = new CnsleeTFCBTTask();
				cnsleeTask.setCnsleeId(cnslee.getCaseNumber());
				cnsleeTask.setTaskId( taskObj.getTaskId());
				cnsleeTask.setTfcbtStageId(taskObj.getStageId());
				taskRowObj.setCnsleeTaskObj(cnsleeTask);
			}
			
			vTaskRowObjs.addElement(taskRowObj);
		}
		return vTaskRowObjs;
	}
	
	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		panelObserver = obs;
		
	}
	public void updateButtonColor(int stageId){
		modelStageTask.fireTableDataChanged();
		modelStageTask.fireTableRowsInserted(0, 1);
		if (stageId == 1){
			updateButtonColor(btnStage1);
		}else if(stageId == 2){
			updateButtonColor(btnStage2);
		}else if(stageId == 3){
			updateButtonColor(btnStage3);
		}else if(stageId == 4){
			updateButtonColor(btnStage4);
		}else if(stageId == 5){
			updateButtonColor(btnStage5);
		}else if(stageId == 6){
			updateButtonColor(btnStage6);
		}else if(stageId == 7){
			updateButtonColor(btnStage7);
		}else if(stageId == 8){
			updateButtonColor(btnStage8);
		}else if(stageId == 9){
			updateButtonColor(btnStage9);
		}else if(stageId == 10){
			updateButtonColor(btnStage10);
		}
	}

	private void updateButtonColor(JTFCBTStageButton btn){
		//default color
		btn.setBackground(new Color(33,173,75)); //completed
		
		Vector<CnsleeTFCBTTask> vCnsleeTasks = btn.getStageTasks();
		if (vCnsleeTasks == null)
			return;
		
		for (int i = 0; i < vCnsleeTasks.size(); i++) {
			CnsleeTFCBTTask cnsleeTask = vCnsleeTasks.elementAt(i);
			if (cnsleeTask.getTaskStatus().equals(CnsleeTFCBTTask.TFCBT_STAGE_TASK_STATUS_NEW)){
				btn.setBackground(new Color(239,50,60));
				break;
			}else if(cnsleeTask.getTaskStatus().equals(CnsleeTFCBTTask.TFCBT_STAGE_TASK_STATUS_IN_PROGRESS)){
				btn.setBackground(new Color(234,234,0));
				break;
			}			
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblStageTaskInfo){
			
			if (e.getClickCount() == 2){
				Point p = e.getPoint();
				int row = tblStageTaskInfo.rowAtPoint(p);
				CnsleeTFCBTTaskRowObj taskRowObj = modelStageTask.getRowObject(row);
				
				TFCBTActivityDlg dlg = new TFCBTActivityDlg(taskRowObj.getCnsleeTaskObj(),
															taskRowObj.getTaskObj(),this);
				dlg.setModal(true);
				dlg.setLocation(new Point (250, 250));//may need to center it
				dlg.setSize(new Dimension(750, 400));
				dlg.setVisible(true);

			}
		
		}
		else if (e.getSource() == tblStageMilestoneInfo){
			
			if (e.getClickCount() == 2){
				Point p = e.getPoint();
				int row = tblStageMilestoneInfo.rowAtPoint(p);
				CnsleeTFCBTMilestoneRowObj milestoneRowObj = modelStageMilestone.getRowObject(row); 
				
				TFCBTActivityDlg dlg = new TFCBTActivityDlg(milestoneRowObj.getCnsleeMilestoneObj(),
															milestoneRowObj.getMilestoneObj(),this);
				dlg.setModal(true);
				dlg.setLocation(new Point (250, 250));//may need to center it
				dlg.setSize(new Dimension(750, 400));
				dlg.setVisible(true);

			}
		
			
		}
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getCnslingSessionId() {
		return cnslingSessionId;
	}

	public void setCnslingSessionId(String cnslingSessionId) {
		this.cnslingSessionId = cnslingSessionId;
	}

}
