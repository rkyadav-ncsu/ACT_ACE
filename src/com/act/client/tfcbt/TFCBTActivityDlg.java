package com.act.client.tfcbt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.act.client.ACEConnector;
import com.act.client.components.JAceDate;
import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CounselleeTFCBTStage;
import com.act.common.CnsleeTFCBTTask;
import com.act.common.SwingUtils;
import com.act.common.TFCBTStageMilestone;
import com.act.common.TFCBTStageTask;

/**
 * This is the dialog class that is used to update details about any
 * TFCTBT stage task/milestone for a client.
 * 
 * @author Geena Renjan
 *
 */
public class TFCBTActivityDlg extends JDialog implements ActionListener{
	
	JPanel panelTop, panelTitle, panelCenter, panelBottom;
	JLabel title, taskTitle, taskNotes,completedDate;
	JTextArea taActivityDesc, taNotes;
	JCheckBox chkCompleted;
	JAceDate dateCompleted;
	JButton btnSave, btnCancel;
	
	CnsleeTFCBTTask cnsleeTask;
	CnsleeTFCBTMileStone cnsleeMilestone;
	TFCBTMainPanel parent;
	TFCBTStageTask taskObj;
	TFCBTStageMilestone milestoneObj;
	
	public TFCBTActivityDlg(CnsleeTFCBTTask cnsleeTask, 
							TFCBTStageTask taskObj, 
							TFCBTMainPanel parent){
		this.cnsleeTask = cnsleeTask;
		this.taskObj = taskObj;
		this.parent = parent;
		init();
	}
	
	
	public TFCBTActivityDlg(CnsleeTFCBTMileStone cnsleeMilestone,
							TFCBTStageMilestone milestoneObj, 
							TFCBTMainPanel parent){
		this.cnsleeMilestone = cnsleeMilestone;
		this.milestoneObj = milestoneObj;
		this.parent = parent;
		init();
	}
	

	private void init(){
		try{
			setLayout(new BorderLayout());
			
			//Top Panel
			panelTop = new JPanel();
			panelTop.setPreferredSize(new Dimension(100, 30));
			add(panelTop, BorderLayout.NORTH);
			
			title = new JLabel();
			panelTop.add(title);
			
			panelCenter = new JPanel();
			panelCenter.setLayout(new GridBagLayout());
			add(panelCenter, BorderLayout.CENTER);
			
			taskTitle = new JLabel("Activity Description:");
			panelCenter.add(taskTitle, SwingUtils.getConstraints(0, 0, 1, 0, 0,
										GridBagConstraints.WEST, 
										GridBagConstraints.NONE, 
										10, 10, 5, 5));
			
			
			taskNotes = new JLabel("Notes/Comments:");
			panelCenter.add(taskNotes, SwingUtils.getConstraints(0, 1, 1, 0, 0,
										GridBagConstraints.WEST, 
										GridBagConstraints.NONE, 
										10, 10, 5, 5));
			
			taActivityDesc = new JTextArea();
			JScrollPane scrollDesc = new JScrollPane(taActivityDesc);
//			scrollDesc.setPreferredSize(new Dimension (150, 100));
			taActivityDesc.setEditable(false);
			panelCenter.add(scrollDesc, SwingUtils.getConstraints(1, 0, 1, 1,1,
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					5, 10, 10, 5));

			taNotes = new JTextArea();
			JScrollPane scrollNotes = new JScrollPane(taNotes);
			panelCenter.add(scrollNotes, SwingUtils.getConstraints(1, 1, 1, 2,2,
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					5, 10, 10, 5));

			panelBottom = new JPanel();
			add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new GridBagLayout());
			
			chkCompleted = new JCheckBox("Completed");
			chkCompleted.addActionListener(this);
			panelBottom.add(chkCompleted, SwingUtils.getConstraints(0, 0, 1, 0,0,
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 10, 10, 5));
			
			completedDate = new JLabel("Completed Date:");
			panelBottom.add(completedDate, SwingUtils.getConstraints(0, 1, 1, 0,0,
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 30, 10, 5));
			
			dateCompleted = new JAceDate();
			panelBottom.add(dateCompleted, SwingUtils.getConstraints(0, 2, 1, 0,0,
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 5, 10, 5));
			
			JPanel panelButtons = new JPanel();
			panelButtons.setLayout(new FlowLayout());
			panelButtons.setBorder(new TitledBorder(""));
			panelBottom.add(panelButtons, SwingUtils.getConstraints(1, 0, 
					GridBagConstraints.REMAINDER, 1,1,
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 5, 10, 5));
			
			btnSave = new JButton("Save");
			btnSave.addActionListener(this);
			panelButtons.add(btnSave);
			
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(this);
			panelButtons.add(btnCancel);
			
			
			initValues();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void initValues(){
		boolean bTaskMode = (cnsleeTask != null);
		
		try{
			if (bTaskMode){
				title.setText("TFCBT Stage: " + taskObj.getStageId()+
						"    Required Task ");
				System.out.println("%%%%%%%%%%%%%%%%" + taskObj.getTitle());
				taActivityDesc.setText(taskObj.getTitle()  +
							"\n\n" +taskObj.getSubTitles() );
				taNotes.setText(cnsleeTask.getTaskNotes());
				chkCompleted.setSelected(cnsleeTask.isTaskCompleted());
			}else{
				title.setText("TFCBT Stage: " + milestoneObj.getStageId()+
						"    Milestone");
				taActivityDesc.setText(milestoneObj.getTitle() +
						"\n\n" +milestoneObj.getSubTitles() );
				taNotes.setText(cnsleeMilestone.getMilestoneNotes());
				chkCompleted.setSelected(cnsleeMilestone.isMilestoneCompleted());
	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		taNotes.setText("asdsadfdfsdfsfsfadgasdgasdgrg\nbbbbbbbb");
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSave){
			
			save();
			this.setVisible(false);
			
		}else if(e.getSource() == btnCancel){
			this.setVisible(false);
		}
	}

	private void save(){
		
		if(cnsleeTask != null){
			if(chkCompleted.isSelected())
				cnsleeTask.setTaskStatus(CnsleeTFCBTTask.TFCBT_STAGE_TASK_STATUS_COMPLETED);
			else{
				if(taNotes.getText().trim().length() > 0)
					cnsleeTask.setTaskStatus(CnsleeTFCBTTask.TFCBT_STAGE_TASK_STATUS_IN_PROGRESS);
			}
			cnsleeTask.setDateCompleted(dateCompleted.getDate());
			cnsleeTask.setTaskNotes(taNotes.getText());
			parent.updateButtonColor(taskObj.getStageId());
			
			cnsleeTask.setCounsellingSessionId(parent.getCnslingSessionId());
			if (!ACEConnector.getInstance().saveCnsleeTFCBTTask(cnsleeTask).equals("SUCCESS")){
				JOptionPane.showMessageDialog(this, "Error saving TFCBT Task to server","Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (cnsleeMilestone != null){
			
			cnsleeMilestone.setCounsellingSessionId(parent.getCnslingSessionId());
			cnsleeMilestone.setMilestoneNotes(taNotes.getText());
			parent.updateButtonColor(milestoneObj.getStageId());
			cnsleeMilestone.setDateCompleted(dateCompleted.getDate());
			
			if (!ACEConnector.getInstance().saveCnsleeTFCBTMilestone(cnsleeMilestone).equals("SUCCESS")){
				JOptionPane.showMessageDialog(this, "Error saving TFCBT milestone to server","Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}

