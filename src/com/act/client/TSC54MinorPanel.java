package com.act.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.act.client.components.JAceDate;
import com.act.common.Counsellee;
import com.act.common.MHSymptChkListObj;
import com.act.common.SwingUtils;
import com.act.common.TSC40Obj;
import com.act.common.TSC54Obj;

public class TSC54MinorPanel extends MHPanel implements ActionListener{
	
	MHPanelObserver panelObserver;
	Counsellee cnslee;
	CounselleeMain parent;
	
	JPanel panelTop, panelCenter, panelCnsleeDetails, panelSymtoms, panelScore;
	JLabel labelTitle;
	JLabel cnsleName,
	cnslrName, caseNumber, testDate,testDesc;
	JLabel valCnsleName, valCnslrName, valCaseNumber;
	JAceDate valTestDate;
	JTextField txtTestDesc;

	JLabel lblBadDreams, lblFeelAfraidOfBad, lblScaryIdeas, lblDirtyWords,
			lblPretend, lblArgue, lblFeelLonely, lblTouchPrivParts, lblFeelSad,
			lblRemPastThings, lblGoingAway, lblRemScaryThings, lblYell, lblCrying,
			lblSuddenFear, lblGettingMad, lblThinkAbtSx, lblFeelDizzy, lblYellOthers,
			lblHurtSelf, lblHurtOthers, lblTouchOtherPrivParts, lblThinkSx, lblFearMen,
			lblFearWomen, lblWash, lblFeelStupid, lblFeelGuilt, lblFeelUnreal, lblForgetThings,
			lblFeelNotInBody, lblFeelNervous, lblFeelAfraid, lblNotTrustPeople, lblThinkBadPast,
			lblFights, lblFeelMean, lblPretendSomewhereElse, lblFearDark, lblUpsetAbtSx,
			lblWorry, lblFeelNooneLikesMe, lblRemThings, lblFeelSx, lblMindEmpty, 
			lblFeelHate, lblCantStopThinkAbtSx, lblTryNoFeelings, lblFeelMad, lblFeelKill, 
			lblWishBadDinHappen, lblWantToKillSelf, lblDayDream, lblUpsetTalkAbtSx;
	
	JComboBoxSymptoms cbBadDreams, cbFeelAfraidOfBad, cbScaryIdeas, cbDirtyWords,
			cbPretend, cbArgue, cbFeelLonely, cbTouchPrivParts, cbFeelSad,
			cbRemPastThings, cbGoingAway, cbRemScaryThings, cbYell, cbCrying,
			cbSuddenFear, cbGettingMad, cbThinkAbtSx, cbFeelDizzy, cbYellOthers,
			cbHurtSelf, cbHurtOthers, cbTouchOtherPrivParts, cbThinkSx, cbFearMen,
			cbFearWomen, cbWash, cbFeelStupid, cbFeelGuilt, cbFeelUnreal, cbForgetThings,
			cbFeelNotInBody, cbFeelNervous, cbFeelAfraid, cbNotTrustPeople, cbThinkBadPast,
			cbFights, cbFeelMean, cbPretendSomewhereElse, cbFearDark, cbUpsetAbtSx,
			cbWorry, cbFeelNooneLikesMe, cbRemThings, cbFeelSx, cbMindEmpty, 
			cbFeelHate, cbCantStopThinkAbtSx, cbTryNoFeelings, cbFeelMad, cbFeelKill, 
			cbWishBadDinHappen, cbWantToKillSelf, cbDayDream, cbUpsetTalkAbtSx;

	//Scores
	JLabel lblTotalScore, lblAnx, lblDep, lblAng, lblPts, lblDis, lblDisO, lblDisF, 
			lblSc, lblScP, lblScD;
	JTextField txtTotalScore, txtAnx, txtDep, txtAng, txtPts, txtDis, txtDisO, txtDisF, 
				txtSc, txtScP, txtScD;
	JButton btnGetScores, btnSave, btnClose;

	private boolean bEditMode = false;
	TSC54Obj tsc54Obj = null;
	
	public TSC54MinorPanel(Counsellee cnslee, CounselleeMain parent){
		this.cnslee = cnslee;
		this.parent = parent;
		
		try{
			initUI();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public TSC54MinorPanel(Counsellee cnslee, CounselleeMain parent, MHSymptChkListObj tsc54Obj){
		this.cnslee = cnslee;
		this.parent = parent;
	    this.tsc54Obj = (TSC54Obj)tsc54Obj;
	    bEditMode = true;
	    
		try{
			initUI();
			setValues();
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
		labelTitle = new JLabel("Trauma Symptom Checklist - 54 (TSC-54 for Minors)");
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
		panelCnsleeDetails.setPreferredSize(new Dimension(100, 50));
		
		cnsleName = new JLabel("Victim Name: ");
		panelCnsleeDetails.add(cnsleName ,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		valCnsleName = new JLabel (cnslee.getName().getFormattedName());
		panelCnsleeDetails.add(valCnsleName,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		caseNumber = new JLabel("Case Number: ");
		panelCnsleeDetails.add(caseNumber ,SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 25, 5, 5));
		
		valCaseNumber = new JLabel (cnslee.getCaseNumber()); 
		panelCnsleeDetails.add(valCaseNumber,SwingUtils.getConstraints(0, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		

		testDate = new JLabel("Test date: ");
		panelCnsleeDetails.add(testDate ,SwingUtils.getConstraints(0, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 25, 0, 5));
		
		valTestDate = new JAceDate(); 
		valTestDate.setEditable(false);
		panelCnsleeDetails.add(valTestDate,SwingUtils.getConstraints(0, 5, GridBagConstraints.REMAINDER,
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 5, 0, 5));
		
		testDesc = new JLabel("Test Description: ");
		panelCnsleeDetails.add(testDesc ,SwingUtils.getConstraints(0, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		txtTestDesc = new JTextField();
		panelCnsleeDetails.add(txtTestDesc,SwingUtils.getConstraints(0, 7, 1, 1,1, 
				GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, 
				10, 0, 5, 25));

		//////Symptoms Panel ////////////////
		panelSymtoms = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panelSymtoms);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//        scrollPane.setBounds(50, 30, 300, 50);

		panelCenter.add(scrollPane, BorderLayout.CENTER);
		panelSymtoms.setLayout(new GridBagLayout());
		panelSymtoms.setBorder(new TitledBorder("Symptoms"));
		
		//Symptoms Description
		JPanel panelSymptomsDesc = new JPanel();
		panelSymptomsDesc.setLayout(new GridBagLayout());
		panelSymptomsDesc.setPreferredSize(new Dimension(100,45));
		panelSymptomsDesc.setBackground(new Color (164,164,164));
		panelSymtoms.add(panelSymptomsDesc,SwingUtils.getConstraints(0, 0, GridBagConstraints.REMAINDER,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				0, 5, 5, 5));
		
		JLabel sympDesc, sympNever, sympOnceAWk, sympFewTimesWk, sympDaily;
		
		sympDesc = new JLabel("<html> How often have you experienced each <br> of the following in the last two months? </html>");
		panelSymptomsDesc.add(sympDesc, SwingUtils.getConstraints(0, 0, 1,
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 5, 5, 5));
		
		sympNever = new JLabel("0 - Never/ N/A");
		panelSymptomsDesc.add(sympNever, SwingUtils.getConstraints(0, 1, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		 
		sympOnceAWk = new JLabel("1 - Sometimes ");
		panelSymptomsDesc.add(sympOnceAWk, SwingUtils.getConstraints(0, 2, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		sympFewTimesWk = new JLabel("2 - Lots of Times");
		panelSymptomsDesc.add(sympFewTimesWk, SwingUtils.getConstraints(0, 3, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));
		
		sympDaily = new JLabel("3 - Almost all of the time");
		panelSymptomsDesc.add(sympDaily, SwingUtils.getConstraints(0, 4, GridBagConstraints.REMAINDER,
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 25, 5, 5));

		/////////////// Symptoms  ////////////////////
		//Bad dreams or Nightmares
		lblBadDreams = new JLabel("Bad dreams or Nightmares: ");
		panelSymtoms.add(lblBadDreams,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbBadDreams = new JComboBoxSymptoms();
		panelSymtoms.add(cbBadDreams,SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Feeling afraid something 
		lblFeelAfraidOfBad = new JLabel("<html> Feeling afraid something <br>bad might happen:</html> ");
		panelSymtoms.add(lblFeelAfraidOfBad,SwingUtils.getConstraints(1, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbFeelAfraidOfBad = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelAfraidOfBad,SwingUtils.getConstraints(1, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//ScaryIdeas
		lblScaryIdeas = new JLabel("<html>Scary ideas or pictures <br>just pop into my head</html>");
		panelSymtoms.add(lblScaryIdeas,SwingUtils.getConstraints(1, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbScaryIdeas = new JComboBoxSymptoms();
		panelSymtoms.add(cbScaryIdeas,SwingUtils.getConstraints(1, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Dirty Words
		lblDirtyWords = new JLabel("Wanting to say dirty words: ");
		panelSymtoms.add(lblDirtyWords,SwingUtils.getConstraints(1, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbDirtyWords = new JComboBoxSymptoms();
		panelSymtoms.add(cbDirtyWords,SwingUtils.getConstraints(1, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Pretending I am someone else
		lblPretend = new JLabel("Pretending I am someone else: ");
		panelSymtoms.add(lblPretend,SwingUtils.getConstraints(1, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbPretend = new JComboBoxSymptoms();
		panelSymtoms.add(cbPretend,SwingUtils.getConstraints(1, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 0, 5, 5));

		//Arguing too much
		lblArgue = new JLabel("Arguing too much: ");
		panelSymtoms.add(lblArgue,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbArgue = new JComboBoxSymptoms();
		panelSymtoms.add(cbArgue, SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling Lonely
		lblFeelLonely = new JLabel("Feeling Lonely: ");
		panelSymtoms.add(lblFeelLonely,SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelLonely = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelLonely, SwingUtils.getConstraints(2, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Touching my private parts too much
		lblTouchPrivParts = new JLabel("Touching my private parts too much: ");
		panelSymtoms.add(lblTouchPrivParts,SwingUtils.getConstraints(2, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbTouchPrivParts = new JComboBoxSymptoms();
		panelSymtoms.add(cbTouchPrivParts, SwingUtils.getConstraints(2, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling sad or unhappy
		lblFeelSad = new JLabel("Feeling sad or unhappy: ");
		panelSymtoms.add(lblFeelSad,SwingUtils.getConstraints(2, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelSad = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelSad, SwingUtils.getConstraints(2, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Remembering things that happened that I didn't like
		lblRemThings = new JLabel("<html>Remembering things that happened <br> that I didn't like: </html>");
		panelSymtoms.add(lblRemThings,SwingUtils.getConstraints(2, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbRemThings = new JComboBoxSymptoms();
		panelSymtoms.add(cbRemThings, SwingUtils.getConstraints(2, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Going away in mind, trying not to think
		lblGoingAway = new JLabel("Going away in mind, trying not to think: ");
		panelSymtoms.add(lblGoingAway,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbGoingAway = new JComboBoxSymptoms();
		panelSymtoms.add(cbGoingAway, SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Remembering scary things
		lblRemScaryThings = new JLabel("Remembering scary things: ");
		panelSymtoms.add(lblRemScaryThings,SwingUtils.getConstraints(3, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbRemScaryThings = new JComboBoxSymptoms();
		panelSymtoms.add(cbRemScaryThings, SwingUtils.getConstraints(3, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Wanting to yell and break things
		lblYell = new JLabel("Wanting to yell and break things: ");
		panelSymtoms.add(lblYell,SwingUtils.getConstraints(3, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbYell = new JComboBoxSymptoms();
		panelSymtoms.add(cbYell, SwingUtils.getConstraints(3, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Crying
		lblCrying = new JLabel("Crying: ");
		panelSymtoms.add(lblCrying,SwingUtils.getConstraints(3, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbCrying = new JComboBoxSymptoms();
		panelSymtoms.add(cbCrying, SwingUtils.getConstraints(3, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Getting scared all of a sudden and don't know why
		lblSuddenFear = new JLabel("<html>Getting scared all of a sudden <br>and don't know why: </html>");
		panelSymtoms.add(lblSuddenFear,SwingUtils.getConstraints(3, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbSuddenFear = new JComboBoxSymptoms();
		panelSymtoms.add(cbSuddenFear, SwingUtils.getConstraints(3, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Getting mad and can't calm down
		lblGettingMad = new JLabel("Getting mad and can't calm down: ");
		panelSymtoms.add(lblGettingMad,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbGettingMad = new JComboBoxSymptoms();
		panelSymtoms.add(cbGettingMad, SwingUtils.getConstraints(4, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Thinking about having sex
		lblThinkAbtSx = new JLabel("Thinking about having sex: ");
		panelSymtoms.add(lblThinkAbtSx,SwingUtils.getConstraints(4, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbThinkAbtSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbThinkAbtSx, SwingUtils.getConstraints(4, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Feeling dizzy
		lblFeelDizzy = new JLabel("Feeling dizzy: ");
		panelSymtoms.add(lblFeelDizzy,SwingUtils.getConstraints(4, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelDizzy = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelDizzy, SwingUtils.getConstraints(4, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Wanting to yell at people
		lblYellOthers = new JLabel("Wanting to yell at people: ");
		panelSymtoms.add(lblYellOthers,SwingUtils.getConstraints(4, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbYellOthers = new JComboBoxSymptoms();
		panelSymtoms.add(cbYellOthers, SwingUtils.getConstraints(4, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Wanting to hurt myself
		lblHurtSelf = new JLabel("Wanting to hurt myself:");
		panelSymtoms.add(lblHurtSelf,SwingUtils.getConstraints(4, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbHurtSelf = new JComboBoxSymptoms();
		panelSymtoms.add(cbHurtSelf, SwingUtils.getConstraints(4, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Wanting to hurt other people
		lblHurtOthers = new JLabel("Wanting to hurt other people: ");
		panelSymtoms.add(lblHurtOthers,SwingUtils.getConstraints(5, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbHurtOthers = new JComboBoxSymptoms();
		panelSymtoms.add(cbHurtOthers, SwingUtils.getConstraints(5, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Thinking about touching other people's private parts
		lblTouchOtherPrivParts = new JLabel("<html>Thinking about touching <br>other people's private parts:</html> ");
		panelSymtoms.add(lblTouchOtherPrivParts,SwingUtils.getConstraints(5, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbTouchOtherPrivParts = new JComboBoxSymptoms();
		panelSymtoms.add(cbTouchOtherPrivParts, SwingUtils.getConstraints(5, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
			
		//Thinking about sex when I don't want to
		lblThinkSx = new JLabel("Thinking about sex when I don't want to: ");
		panelSymtoms.add(lblThinkSx,SwingUtils.getConstraints(5, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbThinkSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbThinkSx, SwingUtils.getConstraints(5, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling scared of men
		lblFearMen = new JLabel("Feeling scared of men: ");
		panelSymtoms.add(lblFearMen,SwingUtils.getConstraints(5, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFearMen = new JComboBoxSymptoms();
		panelSymtoms.add(cbFearMen, SwingUtils.getConstraints(5, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling scared of women
		lblFearWomen = new JLabel("Feeling scared of women");
		panelSymtoms.add(lblFearWomen,SwingUtils.getConstraints(5, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFearWomen = new JComboBoxSymptoms();
		panelSymtoms.add(cbFearWomen, SwingUtils.getConstraints(5, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Washing myself because I feel dirty on the inside
		lblWash = new JLabel("<html>Washing myself because I feel <br>dirty on the inside: </html? ");
		panelSymtoms.add(lblWash,SwingUtils.getConstraints(6, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbWash = new JComboBoxSymptoms();
		panelSymtoms.add(cbWash, SwingUtils.getConstraints(6, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling stupid or bad
		lblFeelStupid = new JLabel("Feeling stupid or bad: ");
		panelSymtoms.add(lblFeelStupid,SwingUtils.getConstraints(6, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelStupid = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelStupid, SwingUtils.getConstraints(6, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Feeling like I did something wrong
		lblFeelGuilt = new JLabel("Feeling like I did something wrong: ");
		panelSymtoms.add(lblFeelGuilt,SwingUtils.getConstraints(6, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelGuilt = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelGuilt, SwingUtils.getConstraints(6, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling like things aren't real
		lblFeelUnreal = new JLabel("Feeling like things aren't real: ");
		panelSymtoms.add(lblFeelUnreal,SwingUtils.getConstraints(6, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelUnreal = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelUnreal, SwingUtils.getConstraints(6, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Forgetting things, can't remember things
		lblForgetThings = new JLabel("Forgetting things, can't remember things");
		panelSymtoms.add(lblForgetThings,SwingUtils.getConstraints(6, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbForgetThings = new JComboBoxSymptoms();
		panelSymtoms.add(cbForgetThings, SwingUtils.getConstraints(6, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));


		//Feeling like I'm not in my body
		lblFeelNotInBody = new JLabel("Feeling like I'm not in my body: ");
		panelSymtoms.add(lblFeelNotInBody,SwingUtils.getConstraints(7, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelNotInBody = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelNotInBody, SwingUtils.getConstraints(7, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling nervous or jumpy inside
		lblFeelNervous = new JLabel("Feeling nervous or jumpy inside: ");
		panelSymtoms.add(lblFeelNervous,SwingUtils.getConstraints(7, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelNervous = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelNervous, SwingUtils.getConstraints(7, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Feeling afraid
		lblFeelAfraid = new JLabel("Feeling afraid: ");
		panelSymtoms.add(lblFeelAfraid,SwingUtils.getConstraints(7, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelAfraid = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelAfraid, SwingUtils.getConstraints(7, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Not trusting people because they might want sex
		lblNotTrustPeople = new JLabel("<html>Not trusting people because <br>they might want sex:</html> ");
		panelSymtoms.add(lblNotTrustPeople,SwingUtils.getConstraints(7, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbNotTrustPeople = new JComboBoxSymptoms();
		panelSymtoms.add(cbNotTrustPeople, SwingUtils.getConstraints(7, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Can't stop thinking about something bad that happened to me
		lblThinkBadPast = new JLabel("<html>Can't stop thinking about something<br>bad that happened to me:</html>");
		panelSymtoms.add(lblThinkBadPast,SwingUtils.getConstraints(7, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbThinkBadPast = new JComboBoxSymptoms();
		panelSymtoms.add(cbThinkBadPast, SwingUtils.getConstraints(7, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Getting into fights
		lblFights = new JLabel("Getting into fights: ");
		panelSymtoms.add(lblFights,SwingUtils.getConstraints(8, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFights = new JComboBoxSymptoms();
		panelSymtoms.add(cbFights, SwingUtils.getConstraints(8, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling mean
		lblFeelMean = new JLabel("Feeling mean: ");
		panelSymtoms.add(lblFeelMean,SwingUtils.getConstraints(8, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelMean = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelMean, SwingUtils.getConstraints(8, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Pretending I'm somewhere else
		lblPretendSomewhereElse = new JLabel("Pretending I'm somewhere else: ");
		panelSymtoms.add(lblPretendSomewhereElse,SwingUtils.getConstraints(8, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbPretendSomewhereElse = new JComboBoxSymptoms();
		panelSymtoms.add(cbPretendSomewhereElse, SwingUtils.getConstraints(8, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Being afraid of the dark
		lblFearDark = new JLabel("Being afraid of the dark: ");
		panelSymtoms.add(lblFearDark,SwingUtils.getConstraints(8, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFearDark = new JComboBoxSymptoms();
		panelSymtoms.add(cbFearDark, SwingUtils.getConstraints(8, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Getting scared or upset when I think about sex
		lblUpsetAbtSx = new JLabel("Getting scared or upset when I think about sex: ");
		panelSymtoms.add(lblUpsetAbtSx,SwingUtils.getConstraints(8, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbUpsetAbtSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbUpsetAbtSx, SwingUtils.getConstraints(8, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Worrying about things
		lblWorry = new JLabel("Worrying about things: ");
		panelSymtoms.add(lblWorry,SwingUtils.getConstraints(9, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbWorry = new JComboBoxSymptoms();
		panelSymtoms.add(cbWorry, SwingUtils.getConstraints(9, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling like nobody likes me
		lblFeelNooneLikesMe = new JLabel("Feeling like nobody likes me: ");
		panelSymtoms.add(lblFeelNooneLikesMe,SwingUtils.getConstraints(9, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelNooneLikesMe = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelNooneLikesMe, SwingUtils.getConstraints(9, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Remembering things I don't want to remember
		lblRemPastThings = new JLabel("<html>Remembering things <br>I don't want to remember: </html>");
		panelSymtoms.add(lblRemPastThings,SwingUtils.getConstraints(9, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbRemPastThings = new JComboBoxSymptoms();
		panelSymtoms.add(cbRemPastThings, SwingUtils.getConstraints(9, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Having sex feelings in my body
		lblFeelSx = new JLabel("Having sex feelings in my body: ");
		panelSymtoms.add(lblFeelSx,SwingUtils.getConstraints(9, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelSx, SwingUtils.getConstraints(9, 7, 1,
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//My mind going empty or blank
		lblMindEmpty = new JLabel("My mind going empty or blank:");
		panelSymtoms.add(lblMindEmpty,SwingUtils.getConstraints(9, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbMindEmpty = new JComboBoxSymptoms();
		panelSymtoms.add(cbMindEmpty, SwingUtils.getConstraints(9, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling like I hate people
		lblFeelHate = new JLabel("Feeling like I hate people: ");
		panelSymtoms.add(lblFeelHate,SwingUtils.getConstraints(10, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelHate = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelHate, SwingUtils.getConstraints(10, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Can't stop thinking about sex
		lblCantStopThinkAbtSx = new JLabel("Can't stop thinking about sex: ");
		panelSymtoms.add(lblCantStopThinkAbtSx,SwingUtils.getConstraints(10, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbCantStopThinkAbtSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbCantStopThinkAbtSx, SwingUtils.getConstraints(10, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Trying not to have any feelings
		lblTryNoFeelings = new JLabel("Trying not to have any feelings: ");
		panelSymtoms.add(lblTryNoFeelings,SwingUtils.getConstraints(10, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbTryNoFeelings = new JComboBoxSymptoms();
		panelSymtoms.add(cbTryNoFeelings, SwingUtils.getConstraints(10, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling mad
		lblFeelMad = new JLabel("Feeling mad: ");
		panelSymtoms.add(lblFeelMad,SwingUtils.getConstraints(10, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelMad = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelMad, SwingUtils.getConstraints(10, 7, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Feeling afraid somebody will kill me
		lblFeelKill = new JLabel("Feeling afraid somebody will kill me:");
		panelSymtoms.add(lblFeelKill,SwingUtils.getConstraints(10, 8, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbFeelKill = new JComboBoxSymptoms();
		panelSymtoms.add(cbFeelKill, SwingUtils.getConstraints(10, 9, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Wishing bad things had never happened
		lblWishBadDinHappen = new JLabel("<html>Wishing bad things had <br>never happened:</html>");
		panelSymtoms.add(lblWishBadDinHappen,SwingUtils.getConstraints(11, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbWishBadDinHappen = new JComboBoxSymptoms();
		panelSymtoms.add(cbWishBadDinHappen, SwingUtils.getConstraints(11, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
//
		//Wanting to kill myself
		lblWantToKillSelf = new JLabel("Wanting to kill myself: ");
		panelSymtoms.add(lblWantToKillSelf,SwingUtils.getConstraints(11, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbWantToKillSelf = new JComboBoxSymptoms();
		panelSymtoms.add(cbWantToKillSelf, SwingUtils.getConstraints(11, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//Day dreaming
		lblDayDream = new JLabel("Feeling like I did something wrong: ");
		panelSymtoms.add(lblDayDream,SwingUtils.getConstraints(11, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbDayDream = new JComboBoxSymptoms();
		panelSymtoms.add(cbDayDream, SwingUtils.getConstraints(11, 5, 1,0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		//Getting upset when people talk about sex
		lblUpsetTalkAbtSx = new JLabel("<html>Getting upset when people <br>talk about sex:</html> ");
		panelSymtoms.add(lblUpsetTalkAbtSx,SwingUtils.getConstraints(11, 6, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbUpsetTalkAbtSx = new JComboBoxSymptoms();
		panelSymtoms.add(cbUpsetTalkAbtSx, SwingUtils.getConstraints(11, 7, GridBagConstraints.REMAINDER, 
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));

		
		//Scores
		panelScore = new JPanel();
		panelScore.setLayout(new GridBagLayout());
		panelScore.setBorder(new TitledBorder("Scores"));
		panelCenter.add(panelScore, BorderLayout.SOUTH);

		//Score button
		btnGetScores = new JButton("Get Scores");
		btnGetScores.addActionListener(this);
		panelScore.add(btnGetScores, SwingUtils.getConstraints(0, 0, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		/////Scores
		//ANX
		lblAnx = new JLabel("ANX: ");
		panelScore.add(lblAnx, SwingUtils.getConstraints(0, 1, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 40, 5, 5));
		
		txtAnx = new JTextField();
		txtAnx.setPreferredSize(new Dimension(40, 24));
		txtAnx.setEditable(false);
		panelScore.add(txtAnx, SwingUtils.getConstraints(0, 2, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//DEP
		lblDep = new JLabel("DEP: ");
		panelScore.add(lblDep, SwingUtils.getConstraints(0, 3, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtDep = new JTextField();
		txtDep.setEditable(false);
		txtDep.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtDep	, SwingUtils.getConstraints(0, 4, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//ANG
		lblAng = new JLabel("ANG: ");
		panelScore.add(lblAng, SwingUtils.getConstraints(0, 5, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtAng = new JTextField();
		txtAng.setEditable(false);
		txtAng.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtAng, SwingUtils.getConstraints(0, 6, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//PTS
		lblPts = new JLabel("PTS: ");
		panelScore.add(lblPts, SwingUtils.getConstraints(0, 7, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtPts = new JTextField();
		txtPts.setEditable(false);
		txtPts.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtPts, SwingUtils.getConstraints(0, 8, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//DIS
		lblDis = new JLabel("DIS: ");
		panelScore.add(lblDis, SwingUtils.getConstraints(0, 9, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtDis = new JTextField();
		txtDis.setEditable(false);
		txtDis.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtDis, SwingUtils.getConstraints(0, 10, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//DIS-O
		lblDisO = new JLabel("DIS-O: ");
		panelScore.add(lblDisO, SwingUtils.getConstraints(0, 11, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtDisO = new JTextField();
		txtDisO.setEditable(false);
		txtDisO.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtDisO, SwingUtils.getConstraints(0, 12, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//DIS-F
		lblDisF = new JLabel("DIS-F: ");
		panelScore.add(lblDisF, SwingUtils.getConstraints(0, 13, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtDisF = new JTextField();
		txtDisF.setEditable(false);
		txtDisF.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtDisF, SwingUtils.getConstraints(0, 14, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//SC
		lblSc = new JLabel("SC: ");
		panelScore.add(lblSc, SwingUtils.getConstraints(0, 15, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtSc = new JTextField();
		txtSc.setEditable(false);
		txtSc.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtSc, SwingUtils.getConstraints(0, 16, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//SC-P
		lblScP = new JLabel("SC-P: ");
		panelScore.add(lblScP, SwingUtils.getConstraints(0, 17, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtScP = new JTextField();
		txtScP.setEditable(false);
		txtScP.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtScP, SwingUtils.getConstraints(0, 18, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		//SC-D
		lblScD = new JLabel("SC-D: ");
		panelScore.add(lblScD, SwingUtils.getConstraints(0, 19, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 10, 5, 5));
		
		txtScD = new JTextField();
		txtScD.setEditable(false);
		txtScD.setPreferredSize(new Dimension(40, 24));
		panelScore.add(txtScD, SwingUtils.getConstraints(0, 20, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 0, 5, 5));
		
		btnSave =new JButton("Save");
		btnSave.addActionListener(this);
		panelScore.add(btnSave, SwingUtils.getConstraints(0, 21, 1, 
				0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 25, 5, 5));
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		panelScore.add(btnClose, SwingUtils.getConstraints(0, 22, 1, 
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGetScores){
			displayScores();
		}
		else if (e.getSource() == btnSave){
			save();
			panelObserver.removePanel();
		}
		else if (e.getSource() == btnClose){
			panelObserver.removePanel();
		}

		
	}
	
	private String getTotalScore(){
		String totalScore = "";
		
		int scores =  getScoreDep()+
				getScoreAng()+ 
				getScorePts()+
				getScoreDis()+
				getScoreDisO()+
				getScoreDisF()+
				getScoreSc()+
				getScoreScP()+
				getScoreScD();
		
		totalScore = String.valueOf(scores);
		return totalScore;
	}

	private boolean validateEntries(){
		
//		if (txtIndivLName.getText().trim().isEmpty()){
//			JOptionPane.showMessageDialog(this, "Please enter a name for the beneficiary.");
//			return;
//		}
//		if (cbPartnerOrg.getSelectedIndex() < 0){
//			JOptionPane.showMessageDialog(this, "Please enter a partner organization for beneficiary.");
//			return;
//		}
//		if (cbLocation.getSelectedIndex() < 0){
//			JOptionPane.showMessageDialog(this, "Please enter a location for beneficiary.");
//			return;
//		}
//		if (txtAge.getText().trim().isEmpty()){
//			JOptionPane.showMessageDialog(this, "Please enter age for beneficiary.");
//			return;
//		}

		return true;
	}
	
	private void save(){
		TSC54Obj tsc54Obj;
		
		if (bEditMode){
			tsc54Obj = this.tsc54Obj;
		}else{
			tsc54Obj = new TSC54Obj();
		}
		
		if (!validateEntries()){
			return;
		}
		tsc54Obj.setChkListDate(valTestDate.getDate());
		System.out.println("case Id " + cnslee.getCaseNumber());
		
		tsc54Obj.setCaseId(cnslee.getCaseNumber());
		tsc54Obj.setChkListTotalScore(getTotalScore());
		tsc54Obj.setChkListDesc(txtTestDesc.getText());

		tsc54Obj.setBadDreams(cbBadDreams.getScore());
		tsc54Obj.setFeelAfraidOfBad(cbFeelAfraidOfBad.getScore());
		tsc54Obj.setScaryIdeas(cbScaryIdeas.getScore());
		tsc54Obj.setDirtyWords(cbDirtyWords.getScore());
		tsc54Obj.setPretend(cbPretend.getScore());
		tsc54Obj.setArgue(cbArgue.getScore());
		tsc54Obj.setFeelLonely(cbFeelLonely.getScore());
		tsc54Obj.setTouchPrivParts(cbTouchPrivParts.getScore());
		tsc54Obj.setFeelSad(cbFeelSad.getScore());
		tsc54Obj.setRemPastThings(cbRemPastThings.getScore());
		tsc54Obj.setGoingAway(cbGoingAway.getScore());
		tsc54Obj.setRemScaryThings(cbRemScaryThings.getScore());
		tsc54Obj.setYell(cbYell.getScore());
		tsc54Obj.setCrying(cbCrying.getScore());
		tsc54Obj.setSuddenFear(cbSuddenFear.getScore());
		tsc54Obj.setGettingMad(cbGettingMad.getScore());
		tsc54Obj.setThinkAbtSx(cbThinkAbtSx.getScore());
		tsc54Obj.setFeelDizzy(cbFeelDizzy.getScore());
		tsc54Obj.setYellOthers(cbYellOthers.getScore());
		tsc54Obj.setHurtSelf(cbHurtSelf.getScore());
		tsc54Obj.setHurtOthers(cbHurtOthers.getScore());
		tsc54Obj.setTouchOtherPrivParts(cbTouchOtherPrivParts.getScore());
		tsc54Obj.setThinkSx(cbThinkAbtSx.getScore());
		tsc54Obj.setFearMen(cbFearMen.getScore());
		tsc54Obj.setFearWomen(cbFearWomen.getScore());
		tsc54Obj.setWash(cbWash.getScore());
		tsc54Obj.setFeelStupid(cbFeelStupid.getScore());
		tsc54Obj.setFeelGuilt(cbFeelGuilt.getScore());
		tsc54Obj.setFeelUnreal(cbFeelUnreal.getScore());
		tsc54Obj.setForgetThings(cbForgetThings.getScore());
		tsc54Obj.setFeelNotInBody(cbFeelNotInBody.getScore());
		tsc54Obj.setFeelNervous(cbFeelNervous.getScore());
		tsc54Obj.setFeelAfraid(cbFeelAfraid.getScore());
		tsc54Obj.setNotTrustPeople(cbNotTrustPeople.getScore());
		tsc54Obj.setThinkBadPast(cbThinkBadPast.getScore());
		tsc54Obj.setFights(cbFights.getScore());
		tsc54Obj.setFeelMean(cbFeelMean.getScore());
		tsc54Obj.setPretendSomewhereElse(cbPretendSomewhereElse.getScore());
		tsc54Obj.setFearDark(cbFearDark.getScore());
		tsc54Obj.setUpsetAbtSx(cbUpsetAbtSx.getScore());
		tsc54Obj.setWorry(cbWorry.getScore());
		tsc54Obj.setFeelNooneLikesMe(cbFeelNooneLikesMe.getScore());
		tsc54Obj.setRemThings(cbRemThings.getScore());
		tsc54Obj.setFeelSx(cbFeelSx.getScore());
		tsc54Obj.setMindEmpty(cbMindEmpty.getScore()); 
		tsc54Obj.setFeelHate(cbFeelHate.getScore());
		tsc54Obj.setCantStopThinkAbtSx(cbCantStopThinkAbtSx.getScore());
		tsc54Obj.setTryNoFeelings(cbTryNoFeelings.getScore());
		tsc54Obj.setFeelMad(cbFeelMad.getScore());
		tsc54Obj.setFeelKill(cbFeelKill.getScore()); 
		tsc54Obj.setWishBadDinHappen(cbWishBadDinHappen.getScore());
		tsc54Obj.setWantToKillSelf(cbWantToKillSelf.getScore());
		tsc54Obj.setDayDream(cbDayDream.getScore());
		tsc54Obj.setUpsetTalkAbtSx(cbUpsetTalkAbtSx.getScore());

		
		//save it to the server
		if (bEditMode){
			if (ACEConnector.getInstance().updateTSC54CheckLst(tsc54Obj)){
				parent.updateSymptChkLists(tsc54Obj);
			}else{
				JOptionPane.showMessageDialog(this, "Error updating TSC 54 details to server","Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else{
			if (ACEConnector.getInstance().saveTSC54CheckLst(tsc54Obj)){
				parent.addSymptChkLists(tsc54Obj);
			}else{
				JOptionPane.showMessageDialog(this, "Error saving TSC 54 details to server","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void setValues(){
		
		try{
			
			valTestDate.setDate(tsc54Obj.getChkListDate());
			
			cnsleName.setText(tsc54Obj.getCaseId());
//			tsc54Obj.getChkListTotalScore(setTotalScore());
			txtTestDesc.setText(tsc54Obj.getChkListDesc());

			cbBadDreams.setSelectedIndex(tsc54Obj.getBadDreams());
			cbFeelAfraidOfBad.setSelectedIndex(tsc54Obj.getFeelAfraidOfBad());
			cbScaryIdeas.setSelectedIndex(tsc54Obj.getScaryIdeas());
			cbDirtyWords.setSelectedIndex(tsc54Obj.getDirtyWords());
			cbPretend.setSelectedIndex(tsc54Obj.getPretend());
			cbArgue.setSelectedIndex(tsc54Obj.getArgue());
			cbFeelLonely.setSelectedIndex(tsc54Obj.getFeelLonely());
			cbTouchPrivParts.setSelectedIndex(tsc54Obj.getTouchPrivParts());
			cbFeelSad.setSelectedIndex(tsc54Obj.getFeelSad());
			cbRemPastThings.setSelectedIndex(tsc54Obj.getRemPastThings());
			cbGoingAway.setSelectedIndex(tsc54Obj.getGoingAway());
			cbRemScaryThings.setSelectedIndex(tsc54Obj.getRemScaryThings());
			cbYell.setSelectedIndex(tsc54Obj.getYell());
			cbCrying.setSelectedIndex(tsc54Obj.getCrying());
			cbSuddenFear.setSelectedIndex(tsc54Obj.getSuddenFear());
			cbGettingMad.setSelectedIndex(tsc54Obj.getGettingMad());
			cbThinkAbtSx.setSelectedIndex(tsc54Obj.getThinkAbtSx());
			cbFeelDizzy.setSelectedIndex(tsc54Obj.getFeelDizzy());
			cbYellOthers.setSelectedIndex(tsc54Obj.getYellOthers());
			cbHurtSelf.setSelectedIndex(tsc54Obj.getHurtSelf());
			cbHurtOthers.setSelectedIndex(tsc54Obj.getHurtOthers());
			cbTouchOtherPrivParts.setSelectedIndex(tsc54Obj.getTouchOtherPrivParts());
			cbThinkAbtSx.setSelectedIndex(tsc54Obj.getThinkSx());
			cbFearMen.setSelectedIndex(tsc54Obj.getFearMen());
			cbFearWomen.setSelectedIndex(tsc54Obj.getFearWomen());
			cbWash.setSelectedIndex(tsc54Obj.getWash());
			cbFeelStupid.setSelectedIndex(tsc54Obj.getFeelStupid());
			cbFeelGuilt.setSelectedIndex(tsc54Obj.getFeelGuilt());
			cbFeelUnreal.setSelectedIndex(tsc54Obj.getFeelUnreal());
			cbForgetThings.setSelectedIndex(tsc54Obj.getForgetThings());
			cbFeelNotInBody.setSelectedIndex(tsc54Obj.getFeelNotInBody());
			cbFeelNervous.setSelectedIndex(tsc54Obj.getFeelNervous());
			cbFeelAfraid.setSelectedIndex(tsc54Obj.getFeelAfraid());
			cbNotTrustPeople.setSelectedIndex(tsc54Obj.getNotTrustPeople());
			cbThinkBadPast.setSelectedIndex(tsc54Obj.getThinkBadPast());
			cbFights.setSelectedIndex(tsc54Obj.getFights());
			cbFeelMean.setSelectedIndex(tsc54Obj.getFeelMean());
			cbPretendSomewhereElse.setSelectedIndex(tsc54Obj.getPretendSomewhereElse());
			cbFearDark.setSelectedIndex(tsc54Obj.getFearDark());
			cbUpsetAbtSx.setSelectedIndex(tsc54Obj.getUpsetAbtSx());
			cbWorry.setSelectedIndex(tsc54Obj.getWorry());
			cbFeelNooneLikesMe.setSelectedIndex(tsc54Obj.getFeelNooneLikesMe());
			cbRemThings.setSelectedIndex(tsc54Obj.getRemThings());
			cbFeelSx.setSelectedIndex(tsc54Obj.getFeelSx());
			cbMindEmpty.setSelectedIndex(tsc54Obj.getMindEmpty()); 
			cbFeelHate.setSelectedIndex(tsc54Obj.getFeelHate());
			cbCantStopThinkAbtSx.setSelectedIndex(tsc54Obj.getCantStopThinkAbtSx());
			cbTryNoFeelings.setSelectedIndex(tsc54Obj.getTryNoFeelings());
			cbFeelMad.setSelectedIndex(tsc54Obj.getFeelMad());
			cbFeelKill.setSelectedIndex(tsc54Obj.getFeelKill()); 
			cbWishBadDinHappen.setSelectedIndex(tsc54Obj.getWishBadDinHappen());
			cbWantToKillSelf.setSelectedIndex(tsc54Obj.getWantToKillSelf());
			cbDayDream.setSelectedIndex(tsc54Obj.getDayDream());
			cbUpsetTalkAbtSx.setSelectedIndex(tsc54Obj.getUpsetTalkAbtSx());
			
		}catch(Exception e){
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error setting values to be edited","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void displayScores(){
		
		//Find All the scores
		int scoreAnx = getScoreAnx();
		int scoreDep = getScoreDep();
		int scoreAng = getScoreAng();
		int scorePts = getScorePts();
		int scoreDis = getScoreDis();
		int scoreDisO = getScoreDisO();
		int scoreDisF = getScoreDisF();
		int scoreSc = getScoreSc();
		int scoreScP = getScoreScP();
		int scoreScD = getScoreScD();
						
		//display Scores
		txtAnx.setText(String.valueOf(scoreAnx));
		txtDep.setText(String.valueOf(scoreDep));
		txtAng.setText(String.valueOf(scoreAng));
		txtPts.setText(String.valueOf(scorePts));
		txtDis.setText(String.valueOf(scoreDis));
		txtDisO.setText(String.valueOf(scoreDisO));
		txtDisF.setText(String.valueOf(scoreDisF));
		txtSc.setText(String.valueOf(scoreSc));
		txtScP.setText(String.valueOf(scoreScP));
		txtScD.setText(String.valueOf(scoreScD));
					
	}

	private int getScoreScD() {
		int scoreScD = cbThinkAbtSx.getScore() +
						cbNotTrustPeople.getScore() +
						cbUpsetAbtSx.getScore() +
						cbUpsetTalkAbtSx.getScore();
		return scoreScD;
	}

	private int getScoreScP() {
		int scoreScP = cbDirtyWords.getScore() +
						cbTouchPrivParts.getScore() +
						cbThinkAbtSx.getScore() +
						cbTouchOtherPrivParts.getScore() +
						cbThinkSx.getScore() +
						cbFeelSx.getScore() +
						cbCantStopThinkAbtSx.getScore();
		return scoreScP;
	}

	private int getScoreSc() {
		int scoreSc =  cbDirtyWords.getScore() +
						cbTouchPrivParts.getScore() +
						cbThinkSx.getScore() +
						cbTouchOtherPrivParts.getScore() +
						cbThinkAbtSx.getScore() +
						cbNotTrustPeople.getScore() +
						cbUpsetAbtSx.getScore() +
						cbFeelSx.getScore() +
						cbCantStopThinkAbtSx.getScore() +
						cbUpsetTalkAbtSx.getScore();
		return scoreSc;
	}

	private int getScoreDisF() {
		return cbPretend.getScore() +
						cbPretendSomewhereElse.getScore() +
						cbWantToKillSelf.getScore();
	}

	private int getScoreDisO() {
		int scoreDisO = cbGoingAway.getScore() +
						cbFeelDizzy.getScore() +
						cbFeelUnreal.getScore() +
						cbForgetThings.getScore() +
						cbFeelNotInBody.getScore() +
						cbMindEmpty.getScore() +
						cbTryNoFeelings.getScore();
		return scoreDisO;
	}

	private int getScoreDis() {
		int scoreDis = cbPretend.getScore() +
						cbGoingAway.getScore() +
						cbFeelDizzy.getScore() +
						cbFeelUnreal.getScore() +
						cbForgetThings.getScore() +
						cbFeelNotInBody.getScore() +
						cbPretendSomewhereElse.getScore() +
						cbMindEmpty.getScore() +
						cbTryNoFeelings.getScore() +
						cbDayDream.getScore();
		return scoreDis;
	}

	private int getScorePts() {
		int scorePts = cbBadDreams.getScore() +
						cbScaryIdeas.getScore() +
						cbRemThings.getScore() +
						cbGoingAway.getScore() +
						cbRemScaryThings.getScore() +
						cbFearMen.getScore() +
						cbFearWomen.getScore() +
						cbThinkBadPast.getScore() +
						cbRemPastThings.getScore() +
						cbWishBadDinHappen.getScore();
		return scorePts;
	}

	private int getScoreAng() {
		int scoreAng = cbArgue.getScore() +
						cbYell.getScore() +
						cbGettingMad.getScore() +
						cbYellOthers.getScore() +
						cbHurtOthers.getScore() +
						cbFights.getScore() +
						cbFeelMean.getScore() +
						cbFeelHate.getScore() +
						cbFeelMad.getScore();
		return scoreAng;
	}

	private int getScoreDep() {
		int scoreDep = cbFeelLonely.getScore() +
						cbFeelSad.getScore() +
						cbCrying.getScore() +
						cbHurtSelf.getScore() +
						cbWash.getScore() +
						cbFeelStupid.getScore() +
						cbFeelGuilt.getScore() +
						cbFeelNooneLikesMe.getScore() +
						cbWantToKillSelf.getScore();
		return scoreDep;
	}

	private int getScoreAnx() {
		int scoreAnx = cbFeelAfraidOfBad.getScore() +
						cbSuddenFear.getScore()+
						cbFearMen.getScore() +
						cbFearWomen.getScore() +
						cbFeelNervous.getScore() +
						cbFeelAfraid.getScore() +
						cbFearDark.getScore() +
						cbWorry.getScore() +
						cbFeelKill.getScore();
		return scoreAnx;
	}

	@Override
	public void setMHPanelObserver(MHPanelObserver obs) {
		panelObserver = obs;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
