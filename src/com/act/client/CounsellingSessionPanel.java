package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.act.client.components.JAceDate;
import com.act.common.Counsellee;
import com.act.common.SwingUtils;

public class CounsellingSessionPanel extends MHPanel implements ActionListener{

	JPanel panelTitle,panelCnsleeDetails, panelCenter, panelBottom, panelSession;
	JLabel labelTitle;
	JLabel cnsleName, cnslrName, caseNumber, sessionDate, Location, sessionSetting,sessionObjectives,sessionContents;
	JLabel valCnsleName, valCnslrName, valCaseNumber;
	JTextField txtLocation;
	JTextArea taSessionSetting, taSessionObjectives, taSessionContents;
	
	JAceDate valSessionDate;
	JButton btnClose;

	MHPanelObserver panelObserver; 
	Counsellee cnslee;
	
	public CounsellingSessionPanel(Counsellee cnslee){
		this.cnslee = cnslee;
		initUI();
		
	}
	
	private void initUI(){
		
		//set parent panel layout
		setLayout(new BorderLayout());

		panelTitle = new JPanel();
		add(panelTitle, BorderLayout.NORTH);
		panelTitle.setBackground(new Color (167,224,168));
		panelTitle.setPreferredSize(new Dimension(100, 40));
		panelTitle.setLayout(new GridBagLayout());
		
		//Title
		labelTitle = new JLabel("Counselling Session Details");
		panelTitle.add(labelTitle,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		labelTitle.setFont(new Font(Font.SANS_SERIF, labelTitle.getFont().getStyle(), 22));
		
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		add(panelCenter, BorderLayout.CENTER);
		
		panelCnsleeDetails = new JPanel();
		panelCnsleeDetails.setBorder(new TitledBorder("Basic Info"));
		panelCenter.add(panelCnsleeDetails, BorderLayout.NORTH);
		panelCnsleeDetails.setLayout(new GridBagLayout());
		
		cnsleName = new JLabel("Victim Name: ");
		panelCnsleeDetails.add(cnsleName ,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		valCnsleName = new JLabel (cnslee.getName().getFormattedName());
		panelCnsleeDetails.add(valCnsleName,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		caseNumber = new JLabel("Case Number: ");
		panelCnsleeDetails.add(caseNumber ,SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		valCaseNumber = new JLabel (cnslee.getCaseNumber()); 
		panelCnsleeDetails.add(valCaseNumber,SwingUtils.getConstraints(0, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		

		sessionDate = new JLabel("Sessiion date: ");
		panelCnsleeDetails.add(sessionDate ,SwingUtils.getConstraints(0, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		valSessionDate = new JAceDate();
		panelCnsleeDetails.add(valSessionDate,SwingUtils.getConstraints(0, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));

		Location = new JLabel("Location: ");
		panelCnsleeDetails.add(Location ,SwingUtils.getConstraints(0, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		txtLocation = new JTextField("Aasha Bhavan");
		panelCnsleeDetails.add(txtLocation,SwingUtils.getConstraints(0, 8, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));


		panelSession = new JPanel();
		panelSession.setLayout(new GridBagLayout());
		panelCenter.add(panelSession, BorderLayout.CENTER);
		
		sessionSetting = new JLabel("Session Setting");
		panelSession.add(sessionSetting,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taSessionSetting = new JTextArea();
		taSessionSetting.setPreferredSize(new Dimension(100, 100));
		panelSession.add(taSessionSetting,SwingUtils.getConstraints(1, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				10, 0, 5, 5));
		
		sessionObjectives = new JLabel("Session Objective");
		panelSession.add(sessionObjectives,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taSessionObjectives = new JTextArea();
		taSessionObjectives.setPreferredSize(new Dimension(100, 100));
		panelSession.add(taSessionObjectives,SwingUtils.getConstraints(3, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				10, 0, 5, 5));
		
		sessionContents = new JLabel("Session Contents");
		panelSession.add(sessionContents,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taSessionContents = new JTextArea();
		taSessionContents.setPreferredSize(new Dimension(100, 100));
		panelSession.add(taSessionContents,SwingUtils.getConstraints(4, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				10, 0, 5, 5));
		
		
		JPanel panelBtn = new JPanel();
		panelBtn.setPreferredSize(new Dimension(100,40));
		panelBtn.setLayout(new FlowLayout());
		add(panelBtn,BorderLayout.SOUTH);
		
		btnClose =new JButton("Close");
		btnClose.addActionListener(this);
		panelBtn.add(btnClose);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose){
			panelObserver.removePanel();
		}
		
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		panelObserver = obs;
		
	}
	
}
