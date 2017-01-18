package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.act.client.components.JAceDate;
import com.act.client.components.JAceTime;
import com.act.client.tfcbt.TFCBTMainPanel;
import com.act.common.ACEDefines;
import com.act.common.Counsellee;
import com.act.common.CounsellingSessionObj;
import com.act.common.CounsellingTherapy;
import com.act.common.SwingUtils;

public class CounsellingSessionPanel extends MHPanel implements ActionListener{

	JPanel panelTitle,panelCnsleeDetails, panelCenter, 
			panelBottom, panelSession, panelTherapy, 
			panelTherapyTop, panelTherapyMain,
			panelLeft, panelOpenness, panelSymptoms;
	
	JLabel labelTitle;
	JLabel cnsleName, cnslrName, caseNumber, sessionDate, Location, 
				sessionSetting,sessionObjectives,sessionContents, 
				followUpSessionPrep, lblCnslrComments, caseMgmt;
	JLabel valCnsleName, valCnslrName, valCaseNumber, startingTime, duration;
	JTextField txtLocation;
	JTextArea taSessionSetting, taSessionObjectives, taSessionContents,
				taFollowUpSessionPrep, taCnslrComments, taCaseMgmt;
	
	//openness to counselling
	JLabel scoreDetails, openToCnsling, opentTofutureOutsidePros, openTogrpHome;
	JComboBox<String> cbOpenCnsling, cbFutureOutsidePros, cbGrpHome;
	
	//symptoms
	JLabel labelAppetite, labelSleep, labelRapport, labelAppearance,
			labelMood, labelAffect, labelThought, labelPerceptDisturb,
			labelOrientation, labelInsight, labelJudgement;
	JLabel labelSubjective, labelObjective;
			
	JComboBox<String> cbAppetite, cbSleep, cbRapport, cbAppearance,
	
					cbMood, cbAffect, cbThought, cbPerceptDisturb,
					cbOrientation, cbInsight, cbJudgement;
	
	JTextField txtAppetite, txtSleep, txtRapport, txtAppearance,
				txtMood, txtAffect, txtThought, txtPerceptDisturb,
				txtOrientation, txtInsight, txtJudgement;

	JLabel chooseTherapy, therapyName, therapyNameVal;
	JTextArea taTherapySummary;
	JComboBox<CounsellingTherapy> cbTherapies;
	
	JAceDate valSessionDate;
	JAceTime timeSession;
	JComboBox<String> cbDurationHrs, cbDurationMins;
	JButton btnSaveSession, btnTherapyGo, btnClose;

	MHPanelObserver panelObserver; 
	Counsellee cnslee;
	
	CounsellingSessionObj counsellingSessionObj;
	CounselleeMain parent;
	
	public CounsellingSessionPanel(Counsellee cnslee, CounselleeMain parent){
		this.cnslee = cnslee;
		this.parent = parent;
		initUI();
		
	}
	
	/**
	 * This Constructor is to be called while in Edit mode
	 * @param cnslee
	 * @param counsellingSessionObj
	 */
	public CounsellingSessionPanel(Counsellee cnslee, CounselleeMain parent, CounsellingSessionObj counsellingSessionObj){
		this.counsellingSessionObj = counsellingSessionObj;
		this.cnslee = cnslee;
		this.parent = parent;
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
		
		cnsleName = new JLabel("Victim Name:");
		panelCnsleeDetails.add(cnsleName ,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		valCnsleName = new JLabel (cnslee.getName().getFormattedName());
		panelCnsleeDetails.add(valCnsleName,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		
		caseNumber = new JLabel("Case Number:");
		panelCnsleeDetails.add(caseNumber ,SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		valCaseNumber = new JLabel (cnslee.getCaseNumber()); 
		panelCnsleeDetails.add(valCaseNumber,SwingUtils.getConstraints(0, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		

		sessionDate = new JLabel("Session date:");
		panelCnsleeDetails.add(sessionDate ,SwingUtils.getConstraints(0, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		valSessionDate = new JAceDate();
		panelCnsleeDetails.add(valSessionDate,SwingUtils.getConstraints(0, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));

		//Starting TIme
		startingTime = new JLabel("Starting Time:");
		panelCnsleeDetails.add(startingTime,SwingUtils.getConstraints(0, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 10, 10, 0));
		
		timeSession = new JAceTime();
		panelCnsleeDetails.add(timeSession,SwingUtils.getConstraints(0, 10, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 10, 0));
		
		//Duration
		duration = new JLabel("Duration:");
		panelCnsleeDetails.add(duration,SwingUtils.getConstraints(0, 11, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 20, 10, 0));
		
		cbDurationHrs = new JComboBox<String>();
		cbDurationHrs.setPreferredSize(new Dimension(40,22));
		for (int i = 0; i < 23; i++) {
			String val = String.valueOf(i);
			if (i<10)
				val = "0"+val;
			cbDurationHrs.addItem(val);
		}
		panelCnsleeDetails.add(cbDurationHrs,SwingUtils.getConstraints(0, 12, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 10, 0));
		
		panelCnsleeDetails.add(new JLabel("hrs : "),SwingUtils.getConstraints(0, 13, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 10, 0));
		
		cbDurationMins = new JComboBox<String>();
		for (int i = 0; i < 56; i+=5) {
			String val = String.valueOf(i);
			if (i<10)
				val = "0"+val;
			cbDurationMins.addItem(val);
		}
		
		cbDurationMins.setPreferredSize(new Dimension(40,22));
		panelCnsleeDetails.add(cbDurationMins,SwingUtils.getConstraints(0, 14, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 10, 0));
		
		panelCnsleeDetails.add(new JLabel("mins"),SwingUtils.getConstraints(0, 15, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 10, 0));
		
		Location = new JLabel("Location:");
		panelCnsleeDetails.add(Location ,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		txtLocation = new JTextField("Aasha Bhavan");
		txtLocation.setPreferredSize(new Dimension(150,22));
		panelCnsleeDetails.add(txtLocation,SwingUtils.getConstraints(1, 1, 3, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 0, 5, 5));

		//Main Body (center area)
		JPanel panelSessionMain = new JPanel();
		panelSessionMain.setLayout(new GridBagLayout());
		panelCenter.add(panelSessionMain, BorderLayout.CENTER);
		
		//Left panel
		panelLeft = new JPanel();
		panelLeft.setLayout(new GridBagLayout());
		panelSessionMain.add(panelLeft, SwingUtils.getConstraints(0, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 0, 0));
		
		//Openness to counselling
		panelOpenness = new JPanel();
		panelOpenness.setBorder(new TitledBorder("Openness to"));
		panelOpenness.setLayout(new GridBagLayout());
		panelLeft.add(panelOpenness, SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 0, 0, 0));
		
		openToCnsling = new JLabel("Counseling:");
		panelOpenness.add(openToCnsling, SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		cbOpenCnsling = new JComboBox<String>();
		cbOpenCnsling.setPreferredSize(new Dimension(35,22));
		populateOpennessScores(cbOpenCnsling);
		panelOpenness.add(cbOpenCnsling, SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		opentTofutureOutsidePros = new JLabel("Future outside prostitution:");
		panelOpenness.add(opentTofutureOutsidePros, SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 15, 0, 0));
		cbFutureOutsidePros = new JComboBox<String>();
		populateOpennessScores(cbFutureOutsidePros);
		cbFutureOutsidePros.setPreferredSize(new Dimension(35,22));
		panelOpenness.add(cbFutureOutsidePros, SwingUtils.getConstraints(0, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		openTogrpHome = new JLabel("Group home:");
		panelOpenness.add(openTogrpHome, SwingUtils.getConstraints(0, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 15, 0, 0));
		cbGrpHome = new JComboBox<String>();
		cbGrpHome.setPreferredSize(new Dimension(35,22));
		populateOpennessScores(cbGrpHome);
		panelOpenness.add(cbGrpHome, SwingUtils.getConstraints(0, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));

		JLabel labelScoreOpenness = new JLabel("1=very closed, 2=somewhat closed, 3=neutral, 4=somewhat open");
		panelOpenness.add(labelScoreOpenness, SwingUtils.getConstraints(1, 0, 6, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Symptoms
		panelSymptoms = new JPanel();
		panelSymptoms.setBorder(new TitledBorder("Symptoms"));
		panelSymptoms.setLayout(new GridBagLayout());
		panelLeft.add(panelSymptoms, SwingUtils.getConstraints(1, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 0, 0, 0));
		
		//Appetite
		labelAppetite = new JLabel("Appetite:");
		panelSymptoms.add(labelAppetite, SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 0, 0));
		cbAppetite = new JComboBox<String>();
		cbAppetite.addItem(ACEDefines.NORMAL);
		cbAppetite.addItem(ACEDefines.GOOD);
		cbAppetite.addItem(ACEDefines.FAIR);
		cbAppetite.addItem(ACEDefines.POOR);
		cbAppetite.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbAppetite, SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 0, 0));
		txtAppetite = new JTextField();
		txtAppetite.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtAppetite, SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 5, 0, 0));
		
		labelSleep = new JLabel("Sleep:");
		panelSymptoms.add(labelSleep, SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		cbSleep = new JComboBox<String>();
		cbSleep.setPreferredSize(new Dimension(150,22));
		cbSleep.addItem(ACEDefines.NORMAL);
		cbSleep.addItem(ACEDefines.GOOD);
		cbSleep.addItem(ACEDefines.FAIR);
		cbSleep.addItem(ACEDefines.POOR);

		panelSymptoms.add(cbSleep, SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtSleep = new JTextField();
		txtSleep.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtSleep, SwingUtils.getConstraints(1, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Rapport
		labelRapport = new JLabel("Rapport:");
		panelSymptoms.add(labelRapport, SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbRapport = new JComboBox<String>();
		cbRapport.addItem(ACEDefines.SYMPT_RAPPORT_APPROPRIATE);
		cbRapport.addItem(ACEDefines.SYMPT_RAPPORT_DISTANT);
		cbRapport.addItem(ACEDefines.SYMPT_RAPPORT_EVASIVE);
		cbRapport.addItem(ACEDefines.SYMPT_RAPPORT_HOSTILE);
		cbRapport.addItem(ACEDefines.SYMPT_RAPPORT_INATTENTIVE);
		cbRapport.addItem(ACEDefines.SYMPT_RAPPORT_POOR_EYE_CONT);
		
		
		cbRapport.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbRapport, SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtRapport = new JTextField();
		txtRapport.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtRapport, SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Appearance
		labelAppearance = new JLabel("Appearance:");
		panelSymptoms.add(labelAppearance, SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbAppearance = new JComboBox<String>();
		cbAppearance.addItem(ACEDefines.SYMPT_APPEAR_APPROP_DRESSED);
		cbAppearance.addItem(ACEDefines.SYMPT_APPEAR_POORLY_DRESSED);
		cbAppearance.addItem(ACEDefines.SYMPT_APPEAR_UNKEMPT);
		
		cbAppearance.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbAppearance, SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtAppearance = new JTextField();
		txtAppearance.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtAppearance, SwingUtils.getConstraints(3, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Mood
		labelMood = new JLabel("Mood:");
		panelSymptoms.add(labelMood, SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbMood = new JComboBox<String>();
		cbMood.addItem(ACEDefines.SYMPT_MOOD_ANGRY);
		cbMood.addItem(ACEDefines.SYMPT_MOOD_ANXIOUS);
		cbMood.addItem(ACEDefines.SYMPT_MOOD_DEPRESSED);
		cbMood.addItem(ACEDefines.SYMPT_MOOD_ELATED);
		cbMood.addItem(ACEDefines.SYMPT_MOOD_IRRITABLE);

		cbMood.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbMood, SwingUtils.getConstraints(4, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtMood = new JTextField();
		txtMood.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtMood, SwingUtils.getConstraints(4, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Affect
		labelAffect = new JLabel("Affect:");
		panelSymptoms.add(labelAffect, SwingUtils.getConstraints(5, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbAffect = new JComboBox<String>();
		cbAffect.addItem(ACEDefines.SYMPT_AFFECT_APPRO);
		cbAffect.addItem(ACEDefines.SYMPT_Affect_Blunted);
		cbAffect.addItem(ACEDefines.SYMPT_Affect_Depressed);
		cbAffect.addItem(ACEDefines.SYMPT_Affect_Flat);
		cbAffect.addItem(ACEDefines.SYMPT_Affect_Labile);
		
		cbAffect.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbAffect, SwingUtils.getConstraints(5, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtAffect = new JTextField();
		txtAffect.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtAffect, SwingUtils.getConstraints(5, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Thought
		labelThought = new JLabel("Thought content and process:");
		panelSymptoms.add(labelThought, SwingUtils.getConstraints(6, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbThought = new JComboBox<String>();
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_CLEAR);
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_FLIGHT_OF_IDEAS);
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_IMPOVERISHED);
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_INCOHERENT);
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_LOOSE);
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_RAPID);
		cbThought.addItem(ACEDefines.SYMPT_THOUGHT_TANGENTIAL);
		
		cbThought.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbThought, SwingUtils.getConstraints(6, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtThought = new JTextField();
		txtThought.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtThought, SwingUtils.getConstraints(6, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Precept Disturbances
		labelPerceptDisturb = new JLabel("Perceptual disturbances:");
		panelSymptoms.add(labelPerceptDisturb, SwingUtils.getConstraints(7, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbPerceptDisturb = new JComboBox<String>();
		cbPerceptDisturb.addItem(ACEDefines.SYMPT_DISTURB_HALLUCINATIONS);
		cbPerceptDisturb.addItem(ACEDefines.SYMPT_DISTURB_DELUSIONS);
		cbPerceptDisturb.addItem(ACEDefines.SYMPT_DISTURB_NONE);
		
		cbPerceptDisturb.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbPerceptDisturb, SwingUtils.getConstraints(7, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtPerceptDisturb = new JTextField();
		txtPerceptDisturb.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtPerceptDisturb, SwingUtils.getConstraints(7, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Orientations
		labelOrientation = new JLabel("Orientation:");
		panelSymptoms.add(labelOrientation, SwingUtils.getConstraints(8, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbOrientation = new JComboBox<String>();
		cbOrientation.addItem(ACEDefines.SYMPT_ORIENTED);

		cbOrientation.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbOrientation, SwingUtils.getConstraints(8, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtOrientation = new JTextField();
		txtOrientation.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtOrientation, SwingUtils.getConstraints(8, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Insight
		labelInsight = new JLabel("Insight:");
		panelSymptoms.add(labelInsight, SwingUtils.getConstraints(9, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbInsight = new JComboBox<String>();
		cbInsight.addItem(ACEDefines.SYMPT_EXCELLENT);
		cbInsight.addItem(ACEDefines.SYMPT_GOOD);
		cbInsight.addItem(ACEDefines.SYMPT_FAIR);
		cbInsight.addItem(ACEDefines.SYMPT_POOR);
		cbInsight.addItem(ACEDefines.SYMPT_GROSSLY_IMPAIRED);
		cbInsight.setPreferredSize(new Dimension(150,22));
		
		panelSymptoms.add(cbInsight, SwingUtils.getConstraints(9, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtInsight = new JTextField();
		txtInsight.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtInsight, SwingUtils.getConstraints(9, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		//Judgements
		labelJudgement = new JLabel("Judgement:");
		panelSymptoms.add(labelJudgement, SwingUtils.getConstraints(10, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		
		cbJudgement = new JComboBox<String>();
		cbJudgement.addItem(ACEDefines.SYMPT_EXCELLENT);
		cbJudgement.addItem(ACEDefines.SYMPT_GOOD);
		cbJudgement.addItem(ACEDefines.SYMPT_FAIR);
		cbJudgement.addItem(ACEDefines.SYMPT_POOR);
		cbJudgement.addItem(ACEDefines.SYMPT_GROSSLY_IMPAIRED);

		cbJudgement.setPreferredSize(new Dimension(150,22));
		panelSymptoms.add(cbJudgement, SwingUtils.getConstraints(10, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 0, 0));
		txtJudgement = new JTextField();
		txtJudgement.setPreferredSize(new Dimension(180,22));
		panelSymptoms.add(txtJudgement, SwingUtils.getConstraints(10, 2, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 0, 0));
		
		
		//Session details
		panelSession = new JPanel();
		panelSession.setBorder(new TitledBorder("Session"));
		panelSession.setLayout(new GridBagLayout());
		panelSessionMain.add(panelSession, SwingUtils.getConstraints(0, 1, 1, 1, 1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 0, 0));
		

		//Session Details		
		sessionSetting = new JLabel("Session Setting");
		panelSession.add(sessionSetting,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		Dimension taDim = new Dimension(200, 100);
		
		taSessionSetting = new JTextArea();
		JScrollPane spSessionSetting = new JScrollPane(taSessionSetting);
//		taSessionSetting.setPreferredSize(taDim);
//		taSessionSetting.setPreferredSize(new Dimension(100, 100));
		panelSession.add(spSessionSetting,SwingUtils.getConstraints(1, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 5, 5));
		
		sessionObjectives = new JLabel("Session Objective");
		panelSession.add(sessionObjectives,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taSessionObjectives = new JTextArea();
		JScrollPane spSessionObjectives = new JScrollPane(taSessionObjectives);
//		taSessionObjectives.setPreferredSize(taDim);
//		taSessionObjectives.setPreferredSize(new Dimension(100, 100));
		panelSession.add(spSessionObjectives,SwingUtils.getConstraints(1, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 5, 5));
		
		sessionContents = new JLabel("Session contents/Areas of concern");
		panelSession.add(sessionContents,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taSessionContents = new JTextArea();
		JScrollPane spSessionContents = new JScrollPane(taSessionContents);
		panelSession.add(spSessionContents,SwingUtils.getConstraints(3, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 5, 5));
		
		followUpSessionPrep = new JLabel("Follow up Session Prep");
		panelSession.add(followUpSessionPrep,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taFollowUpSessionPrep = new JTextArea();
		JScrollPane spFollowUpSessionPrep = new JScrollPane(taFollowUpSessionPrep);
//		taFollowUpSessionPrep.setPreferredSize(taDim);
//		taFollowUpSessionPrep.setPreferredSize(new Dimension(100, 100));
		panelSession.add(spFollowUpSessionPrep,SwingUtils.getConstraints(3, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 5, 5));
		
		lblCnslrComments = new JLabel("Counsellor's comments");
		panelSession.add(lblCnslrComments,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taCnslrComments = new JTextArea();
		JScrollPane spCnslrComments = new JScrollPane(taCnslrComments);
		panelSession.add(spCnslrComments, SwingUtils.getConstraints(5, 0, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 5, 5));
		
		caseMgmt = new JLabel("Case Management Details");
		panelSession.add(caseMgmt,SwingUtils.getConstraints(4, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 5));
		
		taCaseMgmt = new JTextArea();
		JScrollPane spcaseMgmt = new JScrollPane(taCaseMgmt);
//		spcaseMgmt.setAutoscrolls(true);
//		taCaseMgmt.setPreferredSize(new Dimension(100,50));
		panelSession.add(spcaseMgmt,SwingUtils.getConstraints(5, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				0, 0, 5, 5));
		
		//choose therapy Panel
		panelTherapyTop = new JPanel();
		panelTherapyTop.setBorder(new TitledBorder("Therapy"));
		panelTherapyTop.setLayout(new GridBagLayout());
//		panelSessionMain.add(panelTherapyTop,SwingUtils.getConstraints(1, 0, 2,
//				0,0, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.HORIZONTAL, 
//				0, 0, 0, 0));
		panelSession.add(panelTherapyTop,SwingUtils.getConstraints(6, 0, 2,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 0, 0, 0));
		
		chooseTherapy = new JLabel("Choose Therapy");
		panelTherapyTop.add(chooseTherapy,SwingUtils.getConstraints(0, 0, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 0, 5, 5));
		 
		cbTherapies = new JComboBox<CounsellingTherapy>();
		CounsellingTherapy therapy = new CounsellingTherapy();
		therapy.setTherapyObjId(CounsellingTherapy.THERAPY_TFCBT);
		cbTherapies.addItem(therapy);
		cbTherapies.addActionListener(this);
		panelTherapyTop.add(cbTherapies,SwingUtils.getConstraints(0, 1, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 10));
		
		btnTherapyGo = new JButton("Go");
		btnTherapyGo.addActionListener(this);
		panelTherapyTop.add(btnTherapyGo,SwingUtils.getConstraints(0, 2, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 10));
		
//		//Therapy Summary Panel
//		panelTherapyMain = new JPanel();
//		panelTherapy.add(panelTherapyMain, BorderLayout.CENTER);
//		panelTherapyMain.setLayout(new GridBagLayout());
//		
//		therapyName = new JLabel("Therapy Name: ");
//		panelTherapyMain.add(therapyName,SwingUtils.getConstraints(0, 0, 1,
//				0,0, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.NONE, 
//				10, 0, 5, 10));
//		 
//		therapyNameVal = new JLabel("");
//		panelTherapyMain.add(therapyNameVal,SwingUtils.getConstraints(0, 1, 1,
//				0,0, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.NONE, 
//				10, 0, 5, 10));
//		
//		taTherapySummary = new JTextArea();
//		JScrollPane spTherapySummary = new JScrollPane(taTherapySummary);
//		panelTherapyMain.add(spTherapySummary,SwingUtils.getConstraints(1, 0, 1,
//				1,1, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.BOTH, 
//				0, 0, 5, 10));
		 
		//Button panel
		JPanel panelBtn = new JPanel();
		panelBtn.setPreferredSize(new Dimension(100,40));
		panelBtn.setLayout(new FlowLayout());
		add(panelBtn,BorderLayout.SOUTH);
		
		btnSaveSession = new JButton("Save Session");
		btnSaveSession.addActionListener(this);
		panelBtn.add(btnSaveSession);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		panelBtn.add(btnClose);
		
		setSessionDetails();
	}
	
	private void populateOpennessScores(JComboBox<String> cb) {
		if (cb == null)
			return;
		
		for (int i = 1; i < 6; i++) {
			cb.addItem(String.valueOf(i));
			
		}
		
	}

	private void setSessionDetails(){
		
		//Check if in Edit mode
		if (counsellingSessionObj == null)
			return;
		
		valSessionDate.setDate(counsellingSessionObj.getSessionDate());
		txtLocation.setText(counsellingSessionObj.getLocation());
		timeSession.setTime(counsellingSessionObj.getStartTime());
		String duration = counsellingSessionObj.getDuration();
		if (duration != null && duration.trim().length() >0 ){
			cbDurationHrs.setSelectedItem(duration.substring(0,2));
			cbDurationMins.setSelectedItem(duration.substring(2,4));
		}
		taSessionObjectives.setText(counsellingSessionObj.getSesionObjective());
		taSessionContents.setText(counsellingSessionObj.getSessionContents());
		taSessionSetting.setText(counsellingSessionObj.getSessionSetting());
		taFollowUpSessionPrep.setText(counsellingSessionObj.getSessionFollowupPrep());
		taCaseMgmt.setText(counsellingSessionObj.getCaseManagement());
		taCnslrComments.setText(counsellingSessionObj.getCnslrComments());
//		cbTherapies.getSelectedItem()); //TODO

		if(counsellingSessionObj.getOpenToCnsling() != null)
			cbOpenCnsling.setSelectedItem(counsellingSessionObj.getOpenToCnsling().toString());
		if(counsellingSessionObj.getOpentTofutureOutsidePros() != null)
			cbFutureOutsidePros.setSelectedItem(counsellingSessionObj.getOpentTofutureOutsidePros().toString());
		if(counsellingSessionObj.getOpenTogrpHome() != null) 
			cbGrpHome.setSelectedItem(counsellingSessionObj.getOpenTogrpHome().toString());
		if (counsellingSessionObj.getAppetite() != null)
			cbAppetite.setSelectedItem(counsellingSessionObj.getAppetite().toString());
		if (counsellingSessionObj.getSleep() != null)
			cbSleep.setSelectedItem(counsellingSessionObj.getSleep().toString());
		if (counsellingSessionObj.getRapport() != null)
			cbRapport.setSelectedItem(counsellingSessionObj.getRapport().toString());
		if (counsellingSessionObj.getAppearance() != null)
			cbAppearance.setSelectedItem(counsellingSessionObj.getAppearance().toString());
		if (counsellingSessionObj.getMood() != null)
			cbMood.setSelectedItem(counsellingSessionObj.getMood().toString());
		if (counsellingSessionObj.getAffect() != null)
			cbAffect.setSelectedItem(counsellingSessionObj.getAffect().toString());
		if (counsellingSessionObj.getThought() != null)
			cbThought.setSelectedItem(counsellingSessionObj.getThought().toString());
		if (counsellingSessionObj.getPerceptDisturb() != null)
			cbPerceptDisturb.setSelectedItem(counsellingSessionObj.getPerceptDisturb().toString());
		if (counsellingSessionObj.getOrientation() != null)
			cbOrientation.setSelectedItem(counsellingSessionObj.getOrientation().toString());
		if (counsellingSessionObj.getInsight() != null)
			cbInsight.setSelectedItem(counsellingSessionObj.getInsight().toString());
		if (counsellingSessionObj.getJudgement() != null)
			cbJudgement.setSelectedItem(counsellingSessionObj.getJudgement().toString());
		txtAppetite.setText(counsellingSessionObj.getAppetiteComments());
		txtSleep.setText(counsellingSessionObj.getSleepComments());
		txtRapport.setText(counsellingSessionObj.getRapportComments());
		txtAppearance.setText(counsellingSessionObj.getAppearanceComments());
		txtMood.setText(counsellingSessionObj.getMoodComments());
		txtAffect.setText(counsellingSessionObj.getAffectComments());
		txtThought.setText(counsellingSessionObj.getThoughtComments());
		txtPerceptDisturb.setText(counsellingSessionObj.getPerceptDisturbComments());
		txtOrientation.setText(counsellingSessionObj.getOrientationComments());
		txtInsight.setText(counsellingSessionObj.getInsightComments());
		txtJudgement.setText(counsellingSessionObj.getJudgementComments());

	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose){
			panelObserver.removePanel();
		}else if(e.getSource() == btnTherapyGo){
			if (counsellingSessionObj == null){
				JOptionPane.showMessageDialog(this, "Save your session first please.", "WARNING", JOptionPane.WARNING_MESSAGE);
			}else{
				TFCBTMainPanel tfcbtPanel = new TFCBTMainPanel(cnslee, counsellingSessionObj.getCnslingSessionId());
				tfcbtPanel.setMHPanelObserver(panelObserver);
				panelObserver.addPanel(tfcbtPanel);
			}
		}else if(e.getSource() == btnSaveSession){
			//if in ADD mode
			if (counsellingSessionObj == null){
				saveCounsellingSession();
			}else{
				updateCounsellingSesion();
			}
		}
	}

	//get all the values and set
	private void getValues(CounsellingSessionObj counsellingSessionObj){
		
		counsellingSessionObj.setCaseNumber(cnslee.getCaseNumber());
		counsellingSessionObj.setSessionDate(valSessionDate.getDate());
		counsellingSessionObj.setLocation(txtLocation.getText());
		counsellingSessionObj.setStartTime(timeSession.getTime());
		counsellingSessionObj.setDuration(cbDurationHrs.getSelectedItem().toString() + cbDurationMins.getSelectedItem().toString());
		counsellingSessionObj.setSesionObjective(taSessionObjectives.getText());
		counsellingSessionObj.setSessionContents(taSessionContents.getText());
		counsellingSessionObj.setCnslrComments(taCnslrComments.getText());
		counsellingSessionObj.setSessionSetting(taSessionSetting.getText());
		counsellingSessionObj.setSessionFollowupPrep(taFollowUpSessionPrep.getText());
		counsellingSessionObj.setCaseManagement(taCaseMgmt.getText());
		counsellingSessionObj.setTherapy(((CounsellingTherapy)cbTherapies.getSelectedItem()).toString());
		
		counsellingSessionObj.setOpenToCnsling(cbOpenCnsling.getSelectedItem().toString());
		counsellingSessionObj.setOpentTofutureOutsidePros(cbFutureOutsidePros.getSelectedItem().toString());
		counsellingSessionObj.setOpenTogrpHome(cbGrpHome.getSelectedItem().toString());
		counsellingSessionObj.setAppetite(cbAppetite.getSelectedItem().toString());
		counsellingSessionObj.setSleep(cbSleep.getSelectedItem().toString());
		counsellingSessionObj.setRapport(cbRapport.getSelectedItem().toString());
		counsellingSessionObj.setAppearance(cbAppearance.getSelectedItem().toString());
		counsellingSessionObj.setMood(cbMood.getSelectedItem().toString());
		counsellingSessionObj.setAffect(cbAffect.getSelectedItem().toString());
		counsellingSessionObj.setThought(cbThought.getSelectedItem().toString());
		counsellingSessionObj.setPerceptDisturb(cbPerceptDisturb.getSelectedItem().toString());
		counsellingSessionObj.setOrientation(cbOrientation.getSelectedItem().toString());
		counsellingSessionObj.setInsight(cbInsight.getSelectedItem().toString());
		counsellingSessionObj.setJudgement(cbJudgement.getSelectedItem().toString());
		counsellingSessionObj.setAppetiteComments(txtAppetite.getText());
		counsellingSessionObj.setSleepComments(txtSleep.getText());
		counsellingSessionObj.setRapportComments(txtRapport.getText());
		counsellingSessionObj.setAppearanceComments(txtAppearance.getText());
		counsellingSessionObj.setMoodComments(txtMood.getText());
		counsellingSessionObj.setAffectComments(txtAffect.getText());
		counsellingSessionObj.setThoughtComments(txtThought.getText());
		counsellingSessionObj.setPerceptDisturbComments(txtPerceptDisturb.getText());
		counsellingSessionObj.setOrientationComments(txtOrientation.getText());
		counsellingSessionObj.setInsightComments(txtInsight.getText());
		counsellingSessionObj.setJudgementComments(txtJudgement.getText());
		
	}
	
	private void saveCounsellingSession(){
	
		counsellingSessionObj = new CounsellingSessionObj();
		getValues(counsellingSessionObj);

		String sResult = ACEConnector.getInstance().saveCounsellingSession(counsellingSessionObj); 
		if (sResult.equals("ERROR")){
			JOptionPane.showMessageDialog(this, "Error saving counselling session details to server.","Error", JOptionPane.ERROR_MESSAGE);
		}else{
			System.out.println("counselling session object auto generated ID : " + sResult);
			counsellingSessionObj.setCnslingSessionId(sResult);
			parent.addCnslingSession(counsellingSessionObj);
			panelObserver.removePanel();
		}
	}
	
	private void updateCounsellingSesion(){
		
		getValues(counsellingSessionObj);
//		counsellingSessionObj.setSessionDate(valSessionDate.getDate());
//		counsellingSessionObj.setLocation(txtLocation.getText());
//		counsellingSessionObj.setStartTime(timeSession.getTime());
//		counsellingSessionObj.setDuration(cbDurationHrs.getSelectedItem().toString() + cbDurationMins.getSelectedItem().toString());
//		counsellingSessionObj.setSesionObjective(taSessionObjectives.getText());
//		counsellingSessionObj.setSessionContents(taSessionContents.getText());
//		counsellingSessionObj.setSessionSetting(taSessionSetting.getText());
//		counsellingSessionObj.setSessionFollowupPrep(taFollowUpSessionPrep.getText());
//		counsellingSessionObj.setCaseManagement(taCaseMgmt.getText());
//		
		if (ACEConnector.getInstance().updateCounsellingSession(counsellingSessionObj)){
			parent.updateCnslingSession(counsellingSessionObj);
			panelObserver.removePanel();
		}else{
			JOptionPane.showMessageDialog(this, "Error updating counselling session details to server.","Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		// TODO Auto-generated method stub
		panelObserver = obs;
		
	}
	
}
