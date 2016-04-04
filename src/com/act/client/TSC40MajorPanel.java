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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.act.client.components.JAceDate;
import com.act.common.Counsellee;
import com.act.common.SwingUtils;
import com.act.common.TSC40Obj;

public class TSC40MajorPanel extends  MHPanel implements ActionListener{
	
	MHPanelObserver panelObserver; 
	CounselleeMain parent;
	
	JPanel panelTop, panelCenter, panelCnsleeDetails, panelSymtoms;
	JLabel labelTitle;
	JLabel cnsleName, cnslrName, caseNumber, testDate;
	JLabel valCnsleName, valCnslrName, valCaseNumber;
	JAceDate valTestDate;
	
	JLabel headAche, insomnia, wghtLoss, stomachProb, sexProb, isolation, 
			flashBack, restlssSleep, lowSxDrive, anxAttck, 
			loneliness, nightmares, spaceOut, sxOveract,
			sadness, dizziness, dissatSxDrive, ctrlTemper, wakeEarly,
			uncontrolCry, fearMen, notRestMorn, sxNoEnjoy, trblGetAlong,
			memProb, physicHurt, fearWomen, wakeMidnight, badThoughtsSx, 
			passOut, unrealFeel, freqWash, inferiority, tension, sxConfusion,
			hurtOthers, guilt, feelNotInBody, breatheTrouble, sxFeelingUntimely;
	
	JComboBoxSymptoms cbHeadAche, cbInsomnia, cbWghtLoss, cbStomachProb, cbSexProb, cbIsolation, 
				cbFlashBack, cbRestlssSleep, cbLowSxDrive, cbLoneliness, cbNightmares, cbSpaceOut,
				cbAnxAttck, cbSadness, cbDizziness, cbDissatSxDrive, cbCtrlTemper, cbWakeEarly,
				cbSxOveract, cbUncontrolCry, cbFearMen, cbNotRestMorn, cbSxNoEnjoy, cbTrblGetAlong,
				cbMemProb, cbPhysicHurt, cbFearWomen, cbWakeMidnight, cbBadThoughtsSx, 
				cbPassOut, cbUnrealFeel, cbFreqWash, cbInferiority, cbTension, cbSxConfusion,
				cbHurtOthers, cbGuilt, cbFeelNotInBody, cbBreatheTrouble, cbSxFeelingUntimely;
	//Scores
	JLabel lblTotalScore, lblDissacQtn, lblAnxietyQtn, lblDepressQtn, lblSATIQtn, 
			lblSleepDisturbQtn, lblSxProbQtn;

	JTextField txtTotalScore, txtDissacQtn, txtAnxietyQtn, txtDepressQtn, txtSATIQtn, 
		txtSleepDisturbQtn, txtSxProbQtn;
	
	JButton btnGetScores, btnSave, btnClose;
	Counsellee cnslee;
	
	public TSC40MajorPanel(Counsellee cnslee, CounselleeMain parent){
		this.cnslee = cnslee;
		this.parent = parent;
		try{
			initUI();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	private void initUI(){
		
		//set parent panel layout
		setLayout(new BorderLayout());

		panelTop = new JPanel();
		add(panelTop, BorderLayout.NORTH);
		panelTop.setBackground(new Color (185,230,240));
		panelTop.setPreferredSize(new Dimension(100, 40));
		panelTop.setLayout(new GridBagLayout());
		
		//Title
		labelTitle = new JLabel("Trauma Symptom Checklist - 40 (TSC-40)");
		panelTop.add(labelTitle,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		labelTitle.setFont(new Font(Font.SANS_SERIF, labelTitle.getFont().getStyle(), 22));
		
		panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout());
		
		//Cnslee Details
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
		

		testDate = new JLabel("Test date: ");
		panelCnsleeDetails.add(testDate ,SwingUtils.getConstraints(0, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		valTestDate = new JAceDate();
		panelCnsleeDetails.add(valTestDate,SwingUtils.getConstraints(0, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		//////Symptoms Panel ////////////////
		panelSymtoms = new JPanel();
		panelCenter.add(panelSymtoms, BorderLayout.CENTER);
		panelSymtoms.setLayout(new GridBagLayout());
		panelSymtoms.setBorder(new TitledBorder("Symptoms"));
		
		//Symptoms Description
		JPanel panelSymptomsDesc = new JPanel();
		panelSymptomsDesc.setLayout(new GridBagLayout());
		panelSymptomsDesc.setPreferredSize(new Dimension(100,60));
		panelSymptomsDesc.setBackground(new Color (164,164,164));
		panelSymtoms.add(panelSymptomsDesc,SwingUtils.getConstraints(0, 0, GridBagConstraints.REMAINDER,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 5, 5, 5));
		
		JLabel sympDesc, sympNever, sympOnceAWk, sympFewTimesWk, sympDaily;
		
		sympDesc = new JLabel("<html> How often have you experienced each <br> of the following in the last two months? </html>");
		panelSymptomsDesc.add(sympDesc, SwingUtils.getConstraints(0, 0, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		sympNever = new JLabel("0 - Never/ N/A");
		panelSymptomsDesc.add(sympNever, SwingUtils.getConstraints(0, 1, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		 
		sympOnceAWk = new JLabel("1 - Once a week or < 1x/week ");
		panelSymptomsDesc.add(sympOnceAWk, SwingUtils.getConstraints(0, 2, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		sympFewTimesWk = new JLabel("<html> 2 - A few times a week <br> or 2-4x/week </html>");
		panelSymptomsDesc.add(sympFewTimesWk, SwingUtils.getConstraints(0, 3, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		sympDaily = new JLabel("3 - Almost every day 5-7x/week");
		panelSymptomsDesc.add(sympDaily, SwingUtils.getConstraints(0, 4, GridBagConstraints.REMAINDER,
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		//Symptoms
		//Head ache
		headAche = new JLabel("Head Ache: ");
		panelSymtoms.add(headAche,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		cbHeadAche = new JComboBoxSymptoms();
		panelSymtoms.add(cbHeadAche,SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		//Insomnia
		insomnia = new JLabel("Insomnia (Trouble getting to sleep): ");
		panelSymtoms.add(insomnia,SwingUtils.getConstraints(1, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 30, 5, 5));
		
		cbInsomnia = new JComboBoxSymptoms();
		panelSymtoms.add(cbInsomnia,SwingUtils.getConstraints(1, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		//Weight Loss
		wghtLoss = new JLabel("Weight Loss: ");
		panelSymtoms.add(wghtLoss,SwingUtils.getConstraints(1, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 30, 5, 5));
		
		cbWghtLoss = new JComboBoxSymptoms();
		panelSymtoms.add(cbWghtLoss,SwingUtils.getConstraints(1, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		//Stomach Problems
		stomachProb = new JLabel("Stomach Problems: ");
		panelSymtoms.add(stomachProb,SwingUtils.getConstraints(1, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 30, 5, 5));
		
		cbStomachProb = new JComboBoxSymptoms();
		panelSymtoms.add(cbStomachProb,SwingUtils.getConstraints(1, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		
		//Sexual Problems
		sexProb = new JLabel("Sexual Problems: ");
		panelSymtoms.add(sexProb,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbSexProb = new JComboBoxSymptoms();
		panelSymtoms.add(cbSexProb,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Feeling isolated from others
		isolation = new JLabel("Feeling isolated from others: ");
		panelSymtoms.add(isolation,SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbIsolation = new JComboBoxSymptoms();
		panelSymtoms.add(cbIsolation,SwingUtils.getConstraints(2, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Flashbacks
		flashBack = new JLabel("<html>Flash backs: <br> (sudden, vivid, distracting memories)</html> ");
		panelSymtoms.add(flashBack,SwingUtils.getConstraints(2, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbFlashBack = new JComboBoxSymptoms();
		panelSymtoms.add(cbFlashBack,SwingUtils.getConstraints(2, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Restless sleep
		restlssSleep = new JLabel("Restless sleep: ");
		panelSymtoms.add(restlssSleep,SwingUtils.getConstraints(2, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbRestlssSleep = new JComboBoxSymptoms();
		panelSymtoms.add(cbRestlssSleep,SwingUtils.getConstraints(2, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Low sex drive
		lowSxDrive = new JLabel("Low sex drive: ");
		panelSymtoms.add(lowSxDrive,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbLowSxDrive = new JComboBoxSymptoms();
		panelSymtoms.add(cbLowSxDrive,SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Anxiety attacks
		anxAttck = new JLabel("Anxiety attacks: ");
		panelSymtoms.add(anxAttck,SwingUtils.getConstraints(3, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbAnxAttck = new JComboBoxSymptoms();
		panelSymtoms.add(cbAnxAttck,SwingUtils.getConstraints(3, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Sexual over activity
		sxOveract = new JLabel("Sexual over activity");
		panelSymtoms.add(sxOveract,SwingUtils.getConstraints(3, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbSxOveract = new JComboBoxSymptoms();
		panelSymtoms.add(cbSxOveract,SwingUtils.getConstraints(3, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Loneliness
		loneliness = new JLabel("Loneliness: ");
		panelSymtoms.add(loneliness,SwingUtils.getConstraints(3, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbLoneliness = new JComboBoxSymptoms();
		panelSymtoms.add(cbLoneliness,SwingUtils.getConstraints(3, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Nightmares
		nightmares = new JLabel("Nightmares: ");
		panelSymtoms.add(nightmares,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbNightmares = new JComboBoxSymptoms();
		panelSymtoms.add(cbNightmares,SwingUtils.getConstraints(4, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Spacing out
		spaceOut = new JLabel("<html>Spacing out: <br> (going away in  your mind)</html> ");
		panelSymtoms.add(spaceOut,SwingUtils.getConstraints(4, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbSpaceOut = new JComboBoxSymptoms();
		panelSymtoms.add(cbSpaceOut,SwingUtils.getConstraints(4, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Sadness
		sadness = new JLabel("Sadness: ");
		panelSymtoms.add(sadness,SwingUtils.getConstraints(4, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbSadness = new JComboBoxSymptoms();
		panelSymtoms.add(cbSadness,SwingUtils.getConstraints(4, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Dizziness
		dizziness= new JLabel("Dizziness: ");
		panelSymtoms.add(dizziness,SwingUtils.getConstraints(4, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbDizziness = new JComboBoxSymptoms();
		panelSymtoms.add(cbDizziness,SwingUtils.getConstraints(4, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
	
		//Not feeling satisfied with your sex life 
		dissatSxDrive = new JLabel("Dissatisfied with your sex life: ");
		panelSymtoms.add(dissatSxDrive,SwingUtils.getConstraints(5, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbDissatSxDrive = new JComboBoxSymptoms();
		panelSymtoms.add(cbDissatSxDrive,SwingUtils.getConstraints(5, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Trouble controlling your temper
		ctrlTemper = new JLabel("Trouble controlling your temper: ");
		panelSymtoms.add(ctrlTemper,SwingUtils.getConstraints(5, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbCtrlTemper = new JComboBoxSymptoms();
		panelSymtoms.add(cbCtrlTemper,SwingUtils.getConstraints(5, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Waking up early in the morning and can't get back to sleep 
		wakeEarly = new JLabel("<html>Waking up early in the morning <br>and can't get back to sleep</html>");
		panelSymtoms.add(wakeEarly,SwingUtils.getConstraints(5, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbWakeEarly= new JComboBoxSymptoms();
		panelSymtoms.add(cbWakeEarly,SwingUtils.getConstraints(5, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Uncontrollable crying 
		uncontrolCry = new JLabel("Uncontrollable crying: ");
		panelSymtoms.add(uncontrolCry,SwingUtils.getConstraints(5, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbUncontrolCry= new JComboBoxSymptoms();
		panelSymtoms.add(cbUncontrolCry,SwingUtils.getConstraints(5, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Fear of men 
		fearMen = new JLabel("Fear of men: ");
		panelSymtoms.add(fearMen,SwingUtils.getConstraints(6, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbFearMen= new JComboBoxSymptoms();
		panelSymtoms.add(cbFearMen,SwingUtils.getConstraints(6, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Not feeling rested in the morning
		notRestMorn = new JLabel("Not feeling rested in the morning: ");
		panelSymtoms.add(notRestMorn,SwingUtils.getConstraints(6, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbNotRestMorn = new JComboBoxSymptoms();
		panelSymtoms.add(cbNotRestMorn,SwingUtils.getConstraints(6, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Having sex that you didn't enjoy 
		sxNoEnjoy = new JLabel("Having sex that you didn't enjoy: ");
		panelSymtoms.add(sxNoEnjoy,SwingUtils.getConstraints(6, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbSxNoEnjoy = new JComboBoxSymptoms();
		panelSymtoms.add(cbSxNoEnjoy,SwingUtils.getConstraints(6, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Trouble getting along with others 
		trblGetAlong= new JLabel("Trouble getting along with others: ");
		panelSymtoms.add(trblGetAlong,SwingUtils.getConstraints(6, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbTrblGetAlong = new JComboBoxSymptoms();
		panelSymtoms.add(cbTrblGetAlong,SwingUtils.getConstraints(6, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
	
		//Memory problems 
		memProb = new JLabel("Memory problems: ");
		panelSymtoms.add(memProb,SwingUtils.getConstraints(7, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbMemProb = new JComboBoxSymptoms();
		panelSymtoms.add(cbMemProb,SwingUtils.getConstraints(7, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Desire to physically hurt yourself   
		physicHurt = new JLabel("Desire to physically hurt yourself: ");
		panelSymtoms.add(physicHurt,SwingUtils.getConstraints(7, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbPhysicHurt= new JComboBoxSymptoms();
		panelSymtoms.add(cbPhysicHurt,SwingUtils.getConstraints(7, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Fear of women 
		fearWomen = new JLabel("Fear of women: ");
		panelSymtoms.add(fearWomen,SwingUtils.getConstraints(7, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbFearWomen= new JComboBoxSymptoms();
		panelSymtoms.add(cbFearWomen,SwingUtils.getConstraints(7, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Waking up in the middle of the night 	
		wakeMidnight = new JLabel("Waking up in the middle of the night: ");
		panelSymtoms.add(wakeMidnight,SwingUtils.getConstraints(7, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbWakeMidnight = new JComboBoxSymptoms();
		panelSymtoms.add(cbWakeMidnight,SwingUtils.getConstraints(7, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Bad thoughts or feelings during sex
		badThoughtsSx = new JLabel("Bad thoughts or feelings during sex: ");
		panelSymtoms.add(badThoughtsSx,SwingUtils.getConstraints(8, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbBadThoughtsSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbBadThoughtsSx,SwingUtils.getConstraints(8, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Passing out 
		passOut = new JLabel("Passing out: ");
		panelSymtoms.add(passOut,SwingUtils.getConstraints(8, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbPassOut = new JComboBoxSymptoms();
		panelSymtoms.add(cbPassOut,SwingUtils.getConstraints(8, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Feeling that things are unreal
		unrealFeel = new JLabel("Feeling that things are unreal: ");
		panelSymtoms.add(unrealFeel,SwingUtils.getConstraints(8, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbUnrealFeel = new JComboBoxSymptoms();
		panelSymtoms.add(cbUnrealFeel,SwingUtils.getConstraints(8, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Unnecessary or over-frequent washing 
		freqWash= new JLabel("Unnecessary or over-frequent washing: ");
		panelSymtoms.add(freqWash,SwingUtils.getConstraints(8, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbFreqWash = new JComboBoxSymptoms();
		panelSymtoms.add(cbFreqWash,SwingUtils.getConstraints(8, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
	
		//Feelings of inferiority
		inferiority = new JLabel("Feelings of inferiority: ");
		panelSymtoms.add(inferiority,SwingUtils.getConstraints(9, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbInferiority = new JComboBoxSymptoms();
		panelSymtoms.add(cbInferiority,SwingUtils.getConstraints(9, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Feeling tense all the time 
		tension = new JLabel("Feeling tense all the time: ");
		panelSymtoms.add(tension,SwingUtils.getConstraints(9, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbTension = new JComboBoxSymptoms();
		panelSymtoms.add(cbTension,SwingUtils.getConstraints(9, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Being confused about your sexual feelings
		sxConfusion = new JLabel("<html>Being confused about your <br> sexual feelings: </html> ");
		panelSymtoms.add(sxConfusion,SwingUtils.getConstraints(9, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbSxConfusion = new JComboBoxSymptoms();
		panelSymtoms.add(cbSxConfusion,SwingUtils.getConstraints(9, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Desire to physically hurt others 
		hurtOthers = new JLabel("Desire to physically hurt others: ");
		panelSymtoms.add(hurtOthers,SwingUtils.getConstraints(9, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbHurtOthers = new JComboBoxSymptoms();
		panelSymtoms.add(cbHurtOthers,SwingUtils.getConstraints(9, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Feelings of guilt 
		guilt = new JLabel("Feelings of guilt: ");
		panelSymtoms.add(guilt,SwingUtils.getConstraints(10, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbGuilt = new JComboBoxSymptoms();
		panelSymtoms.add(cbGuilt,SwingUtils.getConstraints(10, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Feelings that you are not  always in your body 
		feelNotInBody = new JLabel("<html>Feeling that you are not<br> always in your body:</html> ");
		panelSymtoms.add(feelNotInBody,SwingUtils.getConstraints(10, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbFeelNotInBody = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelNotInBody, SwingUtils.getConstraints(10, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Having trouble breathing 
		breatheTrouble= new JLabel("Having trouble breathing: ");
		panelSymtoms.add(breatheTrouble,SwingUtils.getConstraints(10, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbBreatheTrouble = new JComboBoxSymptoms();
		panelSymtoms.add(cbBreatheTrouble,SwingUtils.getConstraints(10, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		//Sexual feelings when you shouldn't have them
		sxFeelingUntimely= new JLabel("<html>Sexual feelings when you <br>shouldn't have them: <html>");
		panelSymtoms.add(sxFeelingUntimely,SwingUtils.getConstraints(10, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 30, 5, 5));
		
		cbSxFeelingUntimely = new JComboBoxSymptoms();
		panelSymtoms.add(cbSxFeelingUntimely,SwingUtils.getConstraints(10, 7, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		/////// Scores   ///////
		JPanel panelScore = new JPanel();
		panelScore.setBorder(new TitledBorder("Scores"));
		panelScore.setPreferredSize(new Dimension(200,100));
//		panelScore.setBackground(Color.blue);
		panelCenter.add(panelScore, BorderLayout.EAST);
		panelScore.setLayout(new GridBagLayout());

		//Total Score
		lblTotalScore= new JLabel("Total Score: ");
		panelScore.add(lblTotalScore,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		txtTotalScore = new JTextField();
		txtTotalScore.setPreferredSize(new Dimension (50,22));
		txtTotalScore.setEditable(false);
		panelScore.add(txtTotalScore,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		
		panelScore.add(new JLabel ("<html> <b> Subscale Scores: </html>"),SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				40, 0, 5, 5));
		
		//Dissociation
		lblDissacQtn= new JLabel("Dissociation: ");
		panelScore.add(lblDissacQtn,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		txtDissacQtn = new JTextField();
		txtDissacQtn.setPreferredSize(new Dimension (50,22));
		txtDissacQtn.setEditable(false);
		panelScore.add(txtDissacQtn,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		

		//Anxiety
		lblAnxietyQtn = new JLabel("Anxiety: ");
		panelScore.add(lblAnxietyQtn,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		txtAnxietyQtn = new JTextField();
		txtAnxietyQtn.setPreferredSize(new Dimension (50,22));
		txtAnxietyQtn.setEditable(false);
		panelScore.add(txtAnxietyQtn,SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 5));
		
		//Depression
		lblDepressQtn= new JLabel("Depression: ");
		panelScore.add(lblDepressQtn,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		txtDepressQtn = new JTextField();
		txtDepressQtn.setPreferredSize(new Dimension (50,22));
		txtDepressQtn.setEditable(false);
		panelScore.add(txtDepressQtn,SwingUtils.getConstraints(4, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 5));
		
		//SATI
		lblSATIQtn = new JLabel("<html> SATI: <br>(Sexual Abuse <br> Trauma Index)</html>");
		panelScore.add(lblSATIQtn,SwingUtils.getConstraints(5, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		txtSATIQtn = new JTextField();
		txtSATIQtn.setPreferredSize(new Dimension (50,22));
		txtSATIQtn.setEditable(false);
		panelScore.add(txtSATIQtn,SwingUtils.getConstraints(5, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 5));
		
		//Sleep Disturbances
		lblSleepDisturbQtn = new JLabel("Sleep Disturbance: ");
		panelScore.add(lblSleepDisturbQtn,SwingUtils.getConstraints(6, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		txtSleepDisturbQtn = new JTextField();
		txtSleepDisturbQtn.setPreferredSize(new Dimension (50,22));
		txtSleepDisturbQtn.setEditable(false);
		panelScore.add(txtSleepDisturbQtn,SwingUtils.getConstraints(6, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 5));
		
		//Sexual Problems
		lblSxProbQtn = new JLabel("Sexual Problems");
		panelScore.add(lblSxProbQtn,SwingUtils.getConstraints(7, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		txtSxProbQtn = new JTextField();
		txtSxProbQtn.setPreferredSize(new Dimension (50,22));
		txtSxProbQtn.setEditable(false);
		panelScore.add(txtSxProbQtn,SwingUtils.getConstraints(7, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 0, 5, 5));
		
		btnGetScores = new JButton("Get Scores");
		btnGetScores.addActionListener(this);
		panelScore.add(btnGetScores,SwingUtils.getConstraints(8, 0, 2, 0,0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		panelScore.add(panelBtn,SwingUtils.getConstraints(9, 0, 2, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		
		btnSave =new JButton("Save");
		btnSave.addActionListener(this);
		panelBtn.add(btnSave);
		
		btnClose =new JButton("Close");
		btnClose.addActionListener(this);
		panelBtn.add(btnClose);
		
		
	}
	
	private double findTotalScore(){
		double score = 0.0;
		double total = getScore(cbHeadAche) +
				getScore(cbInsomnia) +
				getScore(cbWghtLoss) +
				getScore(cbStomachProb) +
				getScore(cbSexProb) +
				getScore(cbIsolation) + 
				getScore(cbFlashBack) +
				getScore(cbRestlssSleep) +
				getScore(cbLowSxDrive) +
				getScore(cbLoneliness) +
				getScore(cbNightmares) +
				getScore(cbSpaceOut) +
				getScore(cbAnxAttck) +
				getScore(cbSadness) +
				getScore(cbDizziness) +
				getScore(cbDissatSxDrive) +
				getScore(cbCtrlTemper) +
				getScore(cbWakeEarly) +
				getScore(cbSxOveract) + 
				getScore( cbUncontrolCry) + 
				getScore( cbFearMen) + 
				getScore( cbNotRestMorn) + 
				getScore( cbSxNoEnjoy) + 
				getScore( cbTrblGetAlong) + 
				getScore(cbMemProb) + 
				getScore( cbPhysicHurt) + 
				getScore( cbFearWomen) + 
				getScore( cbWakeMidnight) + 
				getScore( cbBadThoughtsSx) + 
				getScore( cbPassOut) + 
				getScore( cbUnrealFeel) + 
				getScore( cbFreqWash) + 
				getScore( cbInferiority) + 
				getScore( cbTension) + 
				getScore( cbSxConfusion) + 
				getScore( cbHurtOthers) + 
				getScore( cbGuilt) + 
				getScore( cbFeelNotInBody) + 
				getScore( cbBreatheTrouble) + 
				getScore( cbSxFeelingUntimely);
		
		System.out.println("total =  " + total);
		score = (total/120.0) *10;
		System.out.println("total double" +score );
		
		return score;
	}
	
	private int getScore(JComboBox cb){
		int val = ((Integer)cb.getSelectedItem()).intValue();
		return val;
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGetScores){
			showScores();
		}
		else if (e.getSource() == btnSave){
			save();
			panelObserver.removePanel();
		}
		else if (e.getSource() == btnClose){
			panelObserver.removePanel();
		}
		
	}
	
	private void save(){
		TSC40Obj tsc40Obj = new TSC40Obj();
		tsc40Obj.setCaseId(cnslee.getCaseNumber());

		tsc40Obj.setChkListDate(valTestDate.getDate());
		tsc40Obj.setChkListTotalScore(txtTotalScore.getText());
		tsc40Obj.setHeadAche(cbHeadAche.getScore());
		tsc40Obj.setInsomnia(cbInsomnia.getScore());
		tsc40Obj.setWghtLoss(cbWghtLoss.getScore());
		tsc40Obj.setStomachProb(cbStomachProb.getScore());
		tsc40Obj.setSexProb(cbSexProb.getScore());
		tsc40Obj.setIsolation(cbIsolation.getScore()); 
		tsc40Obj.setFlashBack(cbFlashBack.getScore());
		tsc40Obj.setRestlssSleep(cbRestlssSleep.getScore());
		tsc40Obj.setLowSxDrive(cbLowSxDrive.getScore());
		tsc40Obj.setLoneliness(cbLoneliness.getScore());
		tsc40Obj.setNightmares(cbNightmares.getScore());
		tsc40Obj.setSpaceOut(cbSpaceOut.getScore());
		tsc40Obj.setAnxAttck(cbAnxAttck.getScore());
		tsc40Obj.setSadness(cbSadness.getScore());
		tsc40Obj.setDizziness(cbDizziness.getScore());
		tsc40Obj.setDissatSxDrive(cbDissatSxDrive.getScore());
		tsc40Obj.setCtrlTemper(cbCtrlTemper.getScore());
		tsc40Obj.setWakeEarly(cbWakeEarly.getScore());
		tsc40Obj.setSxOveract(cbSxOveract.getScore());
		tsc40Obj.setUncontrolCry(cbUncontrolCry.getScore());
		tsc40Obj.setFearMen(cbFearMen.getScore());
		tsc40Obj.setNotRestMorn(cbNotRestMorn.getScore());
		tsc40Obj.setSxNoEnjoy(cbSxNoEnjoy.getScore());
		tsc40Obj.setTrblGetAlong(cbTrblGetAlong.getScore());
		tsc40Obj.setMemProb(cbMemProb.getScore());
		tsc40Obj.setPhysicHurt(cbPhysicHurt.getScore());
		tsc40Obj.setFearWomen(cbFearWomen.getScore());
		tsc40Obj.setWakeMidnight(cbWakeMidnight.getScore());
		tsc40Obj.setBadThoughtsSx(cbBadThoughtsSx.getScore()); 
		tsc40Obj.setPassOut(cbPassOut.getScore());
		tsc40Obj.setUnrealFeel(cbUnrealFeel.getScore());
		tsc40Obj.setFreqWash(cbFreqWash.getScore());
		tsc40Obj.setInferiority(cbInferiority.getScore());
		tsc40Obj.setTension(cbTension.getScore());
		tsc40Obj.setSxConfusion(cbSxConfusion.getScore());
		tsc40Obj.setHurtOthers(cbHurtOthers.getScore());
		tsc40Obj.setGuilt(cbGuilt.getScore());
		tsc40Obj.setFeelNotInBody(cbFeelNotInBody.getScore());
		tsc40Obj.setBreatheTrouble(cbBreatheTrouble.getScore());
		tsc40Obj.setSxFeelingUntimely(cbSxFeelingUntimely.getScore());
		
		//save it to the server
		if (ACEConnector.getInstance().saveTSC40CheckLst(tsc40Obj)){
			parent.addSymptChkLists(tsc40Obj);
		}else{
			JOptionPane.showMessageDialog(this, "Error saving TSC 40 details to server","Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void showScores(){
		
		//total score
		double val = findTotalScore();
		String sVal = String.format("%.3f", val);
		txtTotalScore.setText(sVal);
		
		//Dissociation
		double dissocScore = getScore(cbFlashBack) +
				getScore(cbSpaceOut) +
				getScore(cbDizziness) +
				getScore(cbMemProb) + 
				getScore( cbUnrealFeel) + 
				getScore( cbFeelNotInBody);
		dissocScore = (dissocScore/18)*10;
		txtDissacQtn.setText(String.format("%.3f", dissocScore));
		
		//Anxiety
		double anxScore = getScore(cbHeadAche) +
				getScore(cbStomachProb) +
				getScore(cbDizziness) +
				getScore(cbTrblGetAlong) + 
				getScore( cbFearWomen) + 
				getScore( cbFreqWash)+
				getScore( cbTension) +
				getScore( cbBreatheTrouble) ;
		anxScore = (anxScore/27)*10;
		txtAnxietyQtn.setText(String.format("%.3f", anxScore));
		
		//Depression
		double depressScore = getScore(cbInsomnia) +
				getScore(cbWghtLoss) +
				getScore(cbLowSxDrive) +
				getScore(cbSadness) + 
				getScore( cbWakeEarly) + 
				getScore( cbUncontrolCry) +
				getScore( cbPhysicHurt) +
				getScore( cbInferiority) +
				getScore( cbGuilt) ;
		depressScore = (depressScore/27)*10;
		txtDepressQtn.setText(String.format("%.3f", depressScore));
		
		//SATI
		double SATIScore = getScore(cbSexProb) +
				getScore(cbFlashBack) +
				getScore(cbNightmares) +
				getScore(cbFearMen) + 
				getScore( cbMemProb) + 
				getScore( cbBadThoughtsSx)+
				getScore( cbUnrealFeel);
		SATIScore = (SATIScore/21)*10;
		txtSATIQtn.setText(String.format("%.3f", SATIScore));
		
		//Sleep Disturbance
		double sleepDisturbScore = getScore(cbInsomnia) +
				getScore(cbRestlssSleep) +
				getScore(cbNightmares) +
				getScore(cbWakeEarly) + 
				getScore( cbNotRestMorn) + 
				getScore( cbWakeMidnight);
		sleepDisturbScore = (sleepDisturbScore/18)*10;
		txtSleepDisturbQtn.setText(String.format("%.3f", sleepDisturbScore));
		
		//Sexual Problems
		double sxProbScore = getScore(cbSexProb) +
				getScore(cbLowSxDrive) +				
				getScore(cbSxOveract) +
				getScore(cbDissatSxDrive) + 
				getScore( cbSxNoEnjoy) + 
				getScore( cbBadThoughtsSx)+
				getScore( cbSxConfusion) + 
				getScore( cbSxFeelingUntimely);
		sxProbScore = (sxProbScore/24)*10;
		txtSxProbQtn.setText(String.format("%.3f", sxProbScore));
		
		
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		panelObserver = obs;
		
	}
}
