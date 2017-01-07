package com.act.client;

import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


import com.act.client.components.CommLangPanel;
import com.act.client.components.JAceDate;
import com.act.client.components.JAceList;
import com.act.client.models.MHFamHistIndivModel;
import com.act.common.ACEDefines;
import com.act.common.AbuseIndivObj;
import com.act.common.CounseleeHistoryObj;
import com.act.common.CounseleeRelativeIndivObj;
import com.act.common.Counsellee;
import com.act.common.EducationHistIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.LangFluency;
import com.act.common.MentalStatusIndivObj;
import com.act.common.NonFormalEducation;
import com.act.common.PersonName;
import com.act.common.PhysicalHistIndivObj;
import com.act.common.SocialHistIndivObj;
import com.act.common.StrenghtIndivObj;
import com.act.common.SummaryIndivObj;
import com.act.common.SwingUtils;
import com.act.common.VocationFinIndivObj;
import com.act.common.LegalHistIndivObj;
import com.act.server.db.DB;

public class IntakeFormDlg extends JDialog implements ActionListener, ACEDefines{
	
	JPanel panelTop, panelCenter, panelBottom;
	JButton btnSave, btnCancel;
	
	JPanel panelTopTitle, panelTopIndiv;
	
	JPanel panelFamHist, panelFamEnv, panelEdu, panelVoc, panelSoc, panelPhys, panelPsyc;
	JPanel panelAbus, panelStre, panelSumm, panelLegal;
	JPanel panelMainFamHist, panelMainFamEnv, panelMainEdu, panelMainVoc, panelMainSoc, 
			panelMainPhys, panelMainPsyc,panelMainAbus, panelMainStre, 
			panelMainSumm, panelMainLegal;
	JPanel panelApplyFamHist, panelApplyFamEnv, panelApplyEdu, panelApplyVoc, panelApplySoc, 
			panelApplyPhys, panelApplyPsyc,panelApplyAbus, panelApplyStre, 
			panelApplySumm, panelApplyLegal;
	JButton btnApplyFamHist, btnApplyFamEnv, btnApplyEdu, btnApplyVoc, btnApplySoc, 
			btnApplyPhys, btnApplyPsyc,btnApplyAbus, btnApplyStre, 
			btnApplySumm, btnApplyLegal;
	
	JLabel lblTopTitle, lblIndivName, lblAlias, lblIndivID, lblLocation, lblPartnerOrg, lblAssessmentDt, lblTerminationDt,
						lblGender, lblAge,lblDOB;
	JTextField txtIndivLName, txtIndivFName, txtIndivMName, 
			   txtIndivID, txtAssessmentDt, txtAge, txtAlias;
	JAceDate dateDOB, dateTermination;
	JComboBox cbLocation, cbPartnerOrg;
	JComboBox cbGender;
	JAceDate dateAssessment;
	
	//Family History tab
	JLabel  lblRelName,
			lblRelationship, lblRelStre, lblRelAge, lblRelProfession;
	JCheckBox chkRelAware;
	
	JLabel lblRelAware, lblRelComments, lblRelDt;
	JTextField txtRelName, txtRelAge, txtRelProfession, txtRelComments, txtReldd; 
	JTextField txtRelmm, txtRelyy;
	JComboBox cmbRelStre, cmbRelShip, cmbRelAware;
	JButton btnAdd, btnEdit, btnDel;
	JTable tblRelatives;
	MHFamHistIndivModel modelFamHist;
	
	// ND added 22nd Apr 16
	JPopupMenu popRelTbl;									
	JMenuItem menuRelEdit;
	JMenuItem menuRelRemove;
	MHPanelObserver panelObserver;

	
	//Family Environment tab
	Vector vGrades = new Vector();
	Vector vGradeGFP = new Vector();

	JLabel lblEnvTitle, lblEnvGetsAlong, lblEnvVerAb, lblEnvSubAb, lblEnvPhyAb;
	JLabel lblEnvSexAb, lblEnvNeg, lblEnvPros, lblEnvComm, lblEnvDt;
	JTextField txtEnvdd, TxtEnvmm, TxtEnvyy;
	JTextArea txtEnvComm;
	JComboBox cbEnvGetsAlong, cbEnvVerAb, cbEnvSubAb, cbEnvPhyAb, cbEnvSexAb;
	JComboBox cbEnvNeg, cbEnvPros;
				// Never, Sometimes, Usual, Always

	JLabel lblEduTitle,  lblEduWhere, lblEduStd, lblEduLitClass;
	JLabel lblEduSchExp, lblEduCont, lblEduNonForm, lblEduComm, lblEduDt;
	JTextField txtEduWhere, txtEduStd, txtEdudd, txtEdumm, txtEdyy;
	JTextArea txtEduComm;
	JCheckBox chkEduAttend, chkEduLitClass, chkEduCont;
	JComboBox  cbEduSchExp; // list of courses - beautician, computers, cooking etc, (multiple choice) and  Like, Unsure, Dislike
	JList lstEduNonForm;
	
	JLabel lblVocTitle, lblVocPriorEmp, lblVocWork, lblVocLangFluent, lblVocLangWrite;
	JLabel lblVocLangRead, lblVocLangSpeak, lblVocFamDebt, lblVocComm, lblVocDt;
	JTextField txtVocPriorEmp, txtVocWork, txtVocFamDebt;
	
	JTextArea txtVocComm;
	JTextField txtVocdd, txtVocmm, txtVocyy;
//	JComboBox cbVocLangFluent ;  // multiple choice of languages
//	JCheckBox chkVocLangWrite, chkVocLangRead, chkVocLangSpeak;
	JPanel panelLang;
	JButton btnAddLang;
	CommLangPanel panelCommLang1, panelCommLang2,panelCommLang3,panelCommLang4;
	
	JLabel lblSocTitle, lblSocLivedAt, lblSocRelWithComm, lblSocHerFriends;
	JLabel lblSocRelWithFriends, lblSocInvolvement, lblSocRecreationandFun;
	JLabel lblSocReligionBelief, lblSocComments, lblSocDt;
	JTextField txtSocLivedAt, txtSocHerFriends;
	JTextArea  txtSocComments;
	JComboBox cbSocRelWithComm, cbSocRelWithFriends, cbSocInvolvement; // Good, Fair Poor  and Active, Nominal, Interested Non-Active
	JComboBox cbSocReligionBelief; 
	JList lstSocRecreationandFun;// Arts, Cards, Dance, Friends

	//Legal History
	JLabel lblLegHistOffences;
	JComboBox cbLegHistOffences;
	JTextField txtLegalComments;
	
	//Physical History components
	JLabel 	lblPhyHealthDescYou, lblPhyHealthDesc, lblPhyHealthHistory, lblPhyHealthAddict,
			lblPhyHealthDisord, lblPhyHealthWithdrawSympt, lblPhyHealthReprodHist,
			lblPhyHistComments,lblPhyHistBeentoCnsl,lblPhyHistPsychMed;
	JComboBox 	cbPhyHealthDescYou, cbPhyHealthDesc, 
				cbPhyHealthWithdrawSympt;
//	JList lstPhyHealthAddict;				// ND edited 16th Mar 16
	JAceList lstAddict;
	JCheckBox chkPhyHeaBirthCtrl,chkPhyHeaPreg, chkPhyHeaAbort, chkPhyHeaSTI, chkPhyHeaSTITreat,
				chkPhyHeaADHD,chkPhyHeaADD,chkPhyHeaAutism,chkPhyHeaOther,chkPhyHeaLearningDisab,
				chkPhyHeaPsychMed,chkPhyHeaBeentoCnsl; 						// ND edited on 19th Dec
	JTextField txtPhyHealthHistory, txtPhyHealthAddict, txtPhyHealthWithdrawSympt, 
				txtPhyHealthReprodHist;
	JTextArea txtPhyHealthComments;
	
	//Mental status assessment
	JLabel lblAppearHyg, lblMovement, lblAffect, lblMood, lblSpeech, 
			lblAttitude, lblIntellect, lblImpulse, lblUnderstanding, 
			lblOrient, lblMemory, lblAttention, lblSuicidal, 
			lblHomicidal, lblThoughtProc, lblHallucination,
			lblComments;
	
	JComboBox cbAppearHyg, cbMovement, cbAffect, cbMood, cbSpeech,
				cbAttitude, cbIntellect, cbImpulse, cbUnderstanding,
				cbOrientation, cbMemory, cbAttention, cbSuicidal,
				cbHomicidal, cbThoughtProc, cbHallucination;
//	JList lstStrengthCopeSkills;
	JAceList lstStrengthCopeSkills;
	JAceList lstHallucination, lstOrientation;
	
	JTextField txtMentalStatComments;
	
	//Abuse 
	JLabel lblAbuseVerbal, lblAbusePhys,lblAbuseSex, lblAbuseNeglect;
	JTextField txtAbuseVerbal, txtAbusePhys,txtAbuseSex, txtAbuseNeglect;
	
	//Strengths
	JLabel lblStrengthCopeSkills, lblStrength, lblStrengthGoal, lblStrengthComments;
	JComboBox cbStrengthCopeSkills, cbStrengthGoal;
//	JAceList lstStrengthGoal;
	JPanel panelStrGoals;
	JLabel lblStreGoalFamily, lblStreGoalEdu, lblStreGoalSocial, lblStreGoalPsycho,
			lblStreGoalVoc, lblStreGoalLegal, lblStreGoalPhyHealth;
	JTextField txtStreGoalFamily, txtStreGoalEdu, txtStreGoalSocial, txtStreGoalPsycho,
		txtStreGoalVoc, txtStreGoalLegal, txtStreGoalPhyHealth;
	JTextField txtStrength, txtCopingSkills, txtStrengthComments;
	
	//Summary
	JLabel lblSummaryTitle, lblPsychoNeedsCl, lblPsychoNeedsCounslr, lblIntervenPlan;
	JTextField  txtPsychoNeedsCl, txtPsychoNeedsCounslr, txtIntervenPlan;
	
	JTabbedPane tabPane;
	private URL codebase;
	private MentalHealthListPanel parent;
	
	private boolean isEditMode = false;
	private Counsellee cnslee;
	private CounseleeHistoryObj cnsleeHist;
	
	//first design the page by looking at the form, on a paper
	//add all the components one by one
	//add server side queries
	//add add query to add/ edit
	//add delete functionality
	
	public IntakeFormDlg(MentalHealthListPanel parent, URL codebase){
		this.parent = parent;
		this.codebase = codebase;
		System.out.println("B4 running init isEditMode = " + isEditMode);
		init();
	}

	public IntakeFormDlg(MentalHealthListPanel parent, URL codebase,
			Counsellee cnsl, CounseleeHistoryObj cnslHist){
		
		isEditMode = true;
		this.parent = parent;
		this.codebase = codebase;
		this.cnslee = cnsl;
		this.cnsleeHist = cnslHist;
		System.out.println("B4 running setEditValues isEditMode = " + isEditMode);
		init();
		setEditValues();
	}

	private void init(){
		
		try{

			//Set the parent panel layout
			setLayout(new BorderLayout());
			
			System.out.println("Edit Mode is " + isEditMode);
			
			//create and add Top Panel
			panelTop = new JPanel();
			panelTop.setLayout(new BorderLayout());
			panelTop.setBackground(Color.yellow);
			add(panelTop, BorderLayout.NORTH);
	
			//Top title panel
			panelTopTitle = new JPanel();
			panelTopTitle.setLayout(new BorderLayout());
			panelTopTitle.setPreferredSize(new Dimension(100,40));
			panelTopTitle.setBackground(new Color(180,230,180));
			panelTop.add(panelTopTitle, BorderLayout.NORTH);
			
			//title label
			lblTopTitle = new JLabel("This is the Intake Form for all ACT beneficiaries", JLabel.CENTER);
			panelTopTitle.add(lblTopTitle, BorderLayout.CENTER);
			
			//Panel Top Indiv details
			panelTopIndiv = new JPanel();
			panelTopIndiv.setBorder(new TitledBorder("Beneficiary's Basic Details"));
			panelTopIndiv.setLayout(new GridBagLayout());
			panelTop.add(panelTopIndiv, BorderLayout.CENTER);
			
			JPanel namePanel = new JPanel();
			namePanel.setLayout(new GridBagLayout());
			panelTopIndiv.add(namePanel,SwingUtils.getConstraints(0, 0, GridBagConstraints.REMAINDER, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					0, 0, 0, 0));
	
			//Beneficiary name
			namePanel.add(new JLabel("Last Name: "),SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
	
			txtIndivLName = new JTextField();
			namePanel.add(txtIndivLName,SwingUtils.getConstraints(0, 1, 1, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 0, 5, 5));
			
			namePanel.add(new JLabel("First Name: "),SwingUtils.getConstraints(0, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			
			txtIndivFName = new JTextField();
			namePanel.add(txtIndivFName,SwingUtils.getConstraints(0, 3, 1, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 0, 5, 5));
			
			namePanel.add(new JLabel("Middle Name: "),SwingUtils.getConstraints(0, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
	
			
			txtIndivMName = new JTextField();
			namePanel.add(txtIndivMName,SwingUtils.getConstraints(0, 5, 1, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 0, 5, 10));
			
			//Alias Name
			lblAlias = new JLabel("Alias Name: ");
			panelTopIndiv.add(lblAlias,SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtAlias = new JTextField();
			txtAlias.setPreferredSize(new Dimension(150,22));
			panelTopIndiv.add(txtAlias,SwingUtils.getConstraints(1, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));
			
			
			//Partner Organization
			lblPartnerOrg = new JLabel("Partner Organisation :");
			panelTopIndiv.add(lblPartnerOrg,SwingUtils.getConstraints(1, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 15, 5, 5));
			
			cbPartnerOrg = new JComboBox();			
			Vector vOrganisation = ACEConnector.getInstance(codebase).getOrganisationList();
			cbPartnerOrg.addItem("NA"); //TEST CODE
			populate(cbPartnerOrg, vOrganisation);			
			panelTopIndiv.add(cbPartnerOrg,SwingUtils.getConstraints(1, 3, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			
			//Location/Home
			lblLocation = new JLabel("Location :");
			panelTopIndiv.add(lblLocation,SwingUtils.getConstraints(1, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 15, 5, 5));
			
			cbLocation = new JComboBox();	
			Vector vCareHome = ACEConnector.getInstance(codebase).getCareHomeList();
			cbLocation.addItem("NA"); //test code
			populate(cbLocation, vCareHome);
			panelTopIndiv.add(cbLocation,SwingUtils.getConstraints(1, 5, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			//Age
			lblAge	= new JLabel("Age: ");
//			panelTopIndiv.add(lblAge,SwingUtils.getConstraints(2, 2, 1, 0,0, 
//			GridBagConstraints.WEST, 
//			GridBagConstraints.NONE, 
//			5, 15, 5, 5));
			panelTopIndiv.add(lblAge,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtAge = new JTextField();
			txtAge.setPreferredSize(new Dimension(20,22));
//			panelTopIndiv.add(txtAge,SwingUtils.getConstraints(2, 3, 1, 0,0, 
//			GridBagConstraints.WEST, 
//			GridBagConstraints.NONE	,			// HORIZONTAL, 
//			5, 5, 5, 10));
			panelTopIndiv.add(txtAge,SwingUtils.getConstraints(2, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));
			
			//DOB
			lblDOB	= new JLabel("Date of Birth: ");
//			panelTopIndiv.add(lblDOB,SwingUtils.getConstraints(2, 4, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 15, 5, 0));
			panelTopIndiv.add(lblDOB,SwingUtils.getConstraints(2, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 15, 5, 5));
			
			dateDOB = new JAceDate();
			dateDOB.setDate(""); //set no date initially
//			panelTopIndiv.add(dateDOB,SwingUtils.getConstraints(2, 5, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.HORIZONTAL, 
//					5, 5, 5, 10));
			panelTopIndiv.add(dateDOB,SwingUtils.getConstraints(2, 3, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			//Case Id
			lblIndivID	= new JLabel("Reference Number :");
			panelTopIndiv.add(lblIndivID,SwingUtils.getConstraints(2, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 15, 5, 0));
			
			txtIndivID = new JTextField(16);
			panelTopIndiv.add(txtIndivID,SwingUtils.getConstraints(2, 5, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 10));
			
			
			lblGender = new JLabel("Gender: ");
//			panelTopIndiv.add(lblGender,SwingUtils.getConstraints(2, 0, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
			panelTopIndiv.add(lblGender,SwingUtils.getConstraints(3, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbGender = new JComboBox();
			cbGender.addItem("Female");
			cbGender.addItem("Male");
			cbGender.addItem("Other");
//			panelTopIndiv.add(cbGender,SwingUtils.getConstraints(2, 1, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 10));
			panelTopIndiv.add(cbGender,SwingUtils.getConstraints(3, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));

			//Assessment date
			lblAssessmentDt = new JLabel("Date of Assessment: ");
//			panelTopIndiv.add(lblAssessmentDt,SwingUtils.getConstraints(3, 0, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 5));
			panelTopIndiv.add(lblAssessmentDt,SwingUtils.getConstraints(3, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 15, 5, 0));
			
			dateAssessment = new JAceDate();
//			panelTopIndiv.add(dateAssessment,SwingUtils.getConstraints(3, 1, 1, 0,0, 
//					GridBagConstraints.WEST, 
//					GridBagConstraints.NONE, 
//					5, 5, 5, 10));
			panelTopIndiv.add(dateAssessment,SwingUtils.getConstraints(3, 3, 0, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));
			
			//termination date
			lblTerminationDt = new JLabel("Date of Termination: ");
//			panelTopIndiv.add(lblTerminationDt,SwingUtils.getConstraints(3, 2, 1, 0,0, 
//			GridBagConstraints.WEST, 
//			GridBagConstraints.NONE, 
//			5, 15, 5, 0));
			panelTopIndiv.add(lblTerminationDt,SwingUtils.getConstraints(3, 4, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 15, 5, 0));
			
			dateTermination = new JAceDate();
//			panelTopIndiv.add(dateTermination,SwingUtils.getConstraints(3, 3, 0, 0,0, 
//			GridBagConstraints.WEST, 
//			GridBagConstraints.NONE, 
//			5, 5, 5, 10));
			panelTopIndiv.add(dateTermination,SwingUtils.getConstraints(3, 5, GridBagConstraints.REMAINDER, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 10));
			
			
			//Center Panel, where tabs are added
			panelCenter = new JPanel();
			panelCenter.setLayout(new BorderLayout());
			add(panelCenter, BorderLayout.CENTER);
			
			//Add the history tabbed panes
			tabPane = new JTabbedPane();
			panelCenter.add(tabPane,BorderLayout.CENTER);
			
			//Family History tab
			panelMainFamHist = new JPanel();
			panelMainFamHist.setLayout(new BorderLayout());
			tabPane.add(panelMainFamHist, "Family History");
			
			if (isEditMode == true){									// ND added 5th Apr 16
				panelApplyFamHist = new JPanel();
				panelApplyFamHist.setLayout(new BorderLayout());
	     			panelMainFamHist.add(panelApplyFamHist, BorderLayout.EAST);
				btnApplyFamHist = new JButton("Apply");
				btnApplyFamHist.addActionListener(this);			// ND added 5th Feb 16

				btnApplyFamHist.setBackground(new Color(249,184,172));
				panelApplyFamHist.add(btnApplyFamHist, BorderLayout.SOUTH);
			}
			
			panelFamHist = new JPanel();
			panelMainFamHist.add(panelFamHist, BorderLayout.CENTER);
			panelFamHist.setLayout(new BorderLayout());
			JPanel panelFamHistIndiv = new JPanel();
			panelFamHistIndiv.setLayout(new GridBagLayout());
			panelFamHist.add(panelFamHistIndiv, BorderLayout.NORTH);
			
			//Relative's Name
			lblRelName = new JLabel("Name of Relative: ");
			panelFamHistIndiv.add(lblRelName,SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			
			txtRelName = new JTextField(30);
			panelFamHistIndiv.add(txtRelName,SwingUtils.getConstraints(0, 1, 1, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 5, 5, 5));
			
			//Profession
			lblRelProfession = new JLabel ("Profession: ");
			panelFamHistIndiv.add(lblRelProfession,SwingUtils.getConstraints(0, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			
			txtRelProfession = new JTextField(30);
			panelFamHistIndiv.add(txtRelProfession,SwingUtils.getConstraints(0, 3, GridBagConstraints.REMAINDER, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					10, 5, 5, 5));
			
			//Relationship
			lblRelationship = new JLabel("Relationship: ");
			panelFamHistIndiv.add(lblRelationship,SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cmbRelShip = new JComboBox();
//			Vector vRelationship = ACEConnector.getInstance(codebase).getRelationshipList();					// ND commented 26th Apr 16
			cmbRelShip.addItem(NA);
			populate(cmbRelShip, ACEConnector.getInstance(codebase).getRelationshipList());						// ND edited 26th Apr 16
			
			panelFamHistIndiv.add(cmbRelShip,SwingUtils.getConstraints(1, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//Rel Age
			lblRelAge = new JLabel("Age :");
			panelFamHistIndiv.add(lblRelAge,SwingUtils.getConstraints(1, 2, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtRelAge = new JTextField(5);
			panelFamHistIndiv.add(txtRelAge,SwingUtils.getConstraints(1, 3, GridBagConstraints.REMAINDER, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			
			//Strength of relationship
			lblRelStre = new JLabel("Strength of Relationship: ");
			panelFamHistIndiv.add(lblRelStre,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cmbRelStre = new JComboBox();
			for (int i = 0; i < arrGradeGFP.length; i++) {
				cmbRelStre.addItem(arrGradeGFP[i]);
			}
			
			panelFamHistIndiv.add(cmbRelStre,SwingUtils.getConstraints(2, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			
			//Rel Aware
			chkRelAware = new JCheckBox("Aware of Victim's situation");
			panelFamHistIndiv.add(chkRelAware,SwingUtils.getConstraints(2, 2, GridBagConstraints.REMAINDER, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//Rel Comments
			lblRelComments = new JLabel("Comments: ");
			panelFamHistIndiv.add(lblRelComments,SwingUtils.getConstraints(3, 0, GridBagConstraints.RELATIVE, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtRelComments = new JTextField();
			panelFamHistIndiv.add(txtRelComments,SwingUtils.getConstraints(3, 1, GridBagConstraints.REMAINDER, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			
	
			//add button
			JPanel panelButtons = new JPanel();
			panelFamHistIndiv.add(panelButtons,SwingUtils.getConstraints(4, 0, GridBagConstraints.REMAINDER, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			
			btnAdd = new JButton ("Add");
			panelButtons.add(btnAdd);
			btnAdd.addActionListener(this);
			
			//table family history relationships
			tblRelatives = new JTable();
			modelFamHist = new MHFamHistIndivModel();
			tblRelatives.setModel(modelFamHist);
			JScrollPane scrollFamHist = new JScrollPane(tblRelatives);
			panelFamHist.add(scrollFamHist, BorderLayout.CENTER);
			
	// start ND added 22nd Apr 16				
			popRelTbl = new JPopupMenu();
			menuRelEdit = new JMenuItem("Edit");
			menuRelEdit.addActionListener(this);
			
			menuRelRemove = new JMenuItem("Delete");
			menuRelRemove.addActionListener(this);

			popRelTbl.add(menuRelEdit);
			popRelTbl.add(menuRelRemove);
			tblRelatives.setComponentPopupMenu(popRelTbl);

	//end ND added 22nd Apr 16
			
	//Family Environment Panel
			panelMainFamEnv = new JPanel();
			panelMainFamEnv.setLayout(new BorderLayout());
			tabPane.add(panelMainFamEnv, "Family Environment");
	
			if (isEditMode == true){									// ND added 5th Apr 16
				panelApplyFamEnv = new JPanel();
				panelApplyFamEnv.setLayout(new BorderLayout());
	     		panelMainFamEnv.add(panelApplyFamEnv, BorderLayout.EAST);
				btnApplyFamEnv = new JButton("Apply");
				btnApplyFamEnv.addActionListener(this);				// ND edited 5th Feb 16
	
				btnApplyFamEnv.setBackground(new Color(249,184,172));
				panelApplyFamEnv.add(btnApplyFamEnv, BorderLayout.SOUTH);
			}
			panelFamEnv = new JPanel();
			panelMainFamEnv.add(panelFamEnv, BorderLayout.CENTER);
			panelFamEnv.setLayout(new GridBagLayout());
	
			//Gets along with fam
			lblEnvGetsAlong  = new JLabel("Gets Along with Family: ");
			panelFamEnv.add(lblEnvGetsAlong,SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			
			cbEnvGetsAlong = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvGetsAlong.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvGetsAlong,SwingUtils.getConstraints(0, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//verbal abuse
			lblEnvVerAb  = new JLabel ("There is verbal abuse at home: ");
			panelFamEnv.add(lblEnvVerAb,SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbEnvVerAb = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvVerAb.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvVerAb,SwingUtils.getConstraints(1, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//physical abuse
			lblEnvPhyAb  = new JLabel ("There is physical abuse at home: ");
			panelFamEnv.add(lblEnvPhyAb,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbEnvPhyAb = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvPhyAb.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvPhyAb,SwingUtils.getConstraints(2, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//sexual abuse
			lblEnvSexAb  = new JLabel ("There is sexual abuse at home: ");
			panelFamEnv.add(lblEnvSexAb,SwingUtils.getConstraints(3, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbEnvSexAb = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvSexAb.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvSexAb,SwingUtils.getConstraints(3, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//alchoholism
			lblEnvSubAb  = new JLabel ("There is alcoholism and/or substance use in the home: ");
			panelFamEnv.add(lblEnvSubAb,SwingUtils.getConstraints(4, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbEnvSubAb = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvSubAb.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvSubAb,SwingUtils.getConstraints(4, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//neglect
			lblEnvNeg  = new JLabel ("There is neglect in the home: ");
			panelFamEnv.add(lblEnvNeg,SwingUtils.getConstraints(5, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbEnvNeg = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvNeg.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvNeg,SwingUtils.getConstraints(5, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			
			//prostitution
			lblEnvPros  = new JLabel ("There is prostitution in the home: ");
			panelFamEnv.add(lblEnvPros,SwingUtils.getConstraints(6, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			cbEnvPros = new JComboBox();
			for (int i = 0; i < arrGradeNeSoUsAl.length; i++) {
				cbEnvPros.addItem(arrGradeNeSoUsAl[i]);
				
			}
			panelFamEnv.add(cbEnvPros,SwingUtils.getConstraints(6, 1, GridBagConstraints.REMAINDER, 1,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			//Comments
			lblEnvComm = new JLabel ("Comments: ");
			panelFamEnv.add(lblEnvComm,SwingUtils.getConstraints(7, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtEnvComm = new JTextArea();
			panelFamEnv.add(txtEnvComm,SwingUtils.getConstraints(7, 1, 1, 1,1, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.BOTH, 
					5, 5, 5, 5));
		

		// Nathan 19 Aug 2015 start	
		// *** Some points to consider ***
		  // In the Educational History tab
			// The girls may have attended more than one school
			// They may have also attended more than one non-formal education course			DONE
			// or a course that is not mentioned in the combo list. So besides for choosing 'Other' can a new 
			// course be added by the user
				
		// Educational History tab
			panelMainEdu = new JPanel();
			panelMainEdu.setLayout(new BorderLayout());
			tabPane.add(panelMainEdu, "Educational History");
			
			if (isEditMode == true){								// ND added 5th Apr 16
				panelApplyEdu = new JPanel();
				panelApplyEdu.setLayout(new BorderLayout());
	     		panelMainEdu.add(panelApplyEdu, BorderLayout.EAST);
				btnApplyEdu = new JButton("Apply");
				btnApplyEdu.addActionListener(this);			// ND added 5th Feb 16
	
				btnApplyEdu.setBackground(new Color(249,184,172));
				panelApplyEdu.add(btnApplyEdu, BorderLayout.SOUTH);
			}
			panelEdu = new JPanel();
			panelMainEdu.add(panelEdu, BorderLayout.CENTER);
			panelEdu.setLayout(new GridBagLayout());

			chkEduAttend = new JCheckBox("Attended School");
			chkEduAttend.addActionListener(this);
			panelEdu.add(chkEduAttend,SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
	// start ND edited 06th Mar 16

			// Grade / Level completed
			lblEduStd = new JLabel("Highest Standard :");  
			panelEdu.add(lblEduStd,SwingUtils.getConstraints(0, 1, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtEduStd = new JTextField(20);
			panelEdu.add(txtEduStd,SwingUtils.getConstraints(0, 2, 1, 0.1,0, 
					GridBagConstraints.NORTHWEST, 
//					GridBagConstraints.HORIZONTAL,				// ND edited 6th Mar 16 
					1,
					5, 5, 5, 5));

			// Where educated		
			lblEduWhere = new JLabel("Name/place of school/s :"); 
			panelEdu.add(lblEduWhere,SwingUtils.getConstraints(0, 3, 1, 0,0, 
							GridBagConstraints.NORTHWEST, 
							GridBagConstraints.NONE, 
							5, 5, 5, 5));
			txtEduWhere = new JTextField(32);
			panelEdu.add(txtEduWhere,SwingUtils.getConstraints (0, 4, GridBagConstraints.REMAINDER, 0.15,0, 	// ND edited 6th Mar 16
							GridBagConstraints.NORTHWEST, 
							GridBagConstraints.HORIZONTAL, 
							5, 5, 5, 5));

	// end ND edited 06th Mar 16
			
			// Experience at School
			lblEduSchExp = new JLabel("Experience of School :");
			panelEdu.add(lblEduSchExp,SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));	
			cbEduSchExp = new JComboBox();
			cbEduSchExp.addItem("NA");
			cbEduSchExp.addItem("Like");
			cbEduSchExp.addItem("Unsure");
			cbEduSchExp.addItem("Dislike");
			panelEdu.add(cbEduSchExp,SwingUtils.getConstraints(1, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			enableSchool();
			
			// Attended Literacy classes?
			chkEduLitClass = new JCheckBox("Attended Literacy Classes");			
			panelEdu.add(chkEduLitClass,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));	


			// Continue studying
			chkEduCont = new JCheckBox("Continue Studies");
			panelEdu.add(chkEduCont,SwingUtils.getConstraints(2, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));	
			
			// Non Formal Courses
			lblEduNonForm = new JLabel("Non-Formal Education :"); 
			panelEdu.add(lblEduNonForm,SwingUtils.getConstraints(3, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			
			lstEduNonForm = new JList();	// list of courses - beautician, computers, cooking etc, (multiple choice)
//			Vector vNFEducation = ACEConnector.getInstance(codebase).getNonFormalEducationList();
			Vector<String> vStrNFEdu = ACEConnector.getInstance(codebase).getNonFormalEducationList();
//			populateLst(lstEduNonForm, vNFEducation);
			System.out.println("Non Formal Edu string list size " + vStrNFEdu.size());
			populateLst(lstEduNonForm,vStrNFEdu);						// ND edited 08th Mar 16
			panelEdu.add(lstEduNonForm,SwingUtils.getConstraints(3, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));	
			// Comments on education
			lblEduComm = new JLabel("Comments :"); 
			panelEdu.add(lblEduComm,SwingUtils.getConstraints(4, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));	
			txtEduComm = new JTextArea();
			panelEdu.add(txtEduComm,SwingUtils.getConstraints(4, 1, GridBagConstraints.REMAINDER,
					1, 1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					5, 5, 5, 5));	

	// Vocational tab
			panelMainVoc = new JPanel();
			panelMainVoc.setLayout(new BorderLayout());
			tabPane.add(panelMainVoc, "Vocational and Financial Info");
			
			if (isEditMode == true){								// ND added 5th Apr 16
				panelApplyVoc = new JPanel();
				panelApplyVoc.setLayout(new BorderLayout());
	     		panelMainVoc.add(panelApplyVoc, BorderLayout.EAST);
				btnApplyVoc = new JButton("Apply");
				btnApplyVoc.addActionListener(this);			// ND added 5th Feb 16
	
				btnApplyVoc.setBackground(new Color(249,184,172));
				panelApplyVoc.add(btnApplyVoc, BorderLayout.SOUTH);
			}
			panelVoc = new JPanel();
			panelMainVoc.add(panelVoc, BorderLayout.CENTER);
			panelVoc.setLayout(new GridBagLayout());
			
			
			//Prior Employment
			lblVocPriorEmp = new JLabel("Prior Employment :"); 
			panelVoc.add(lblVocPriorEmp,SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			txtVocPriorEmp = new JTextField(); 
			panelVoc.add(txtVocPriorEmp,SwingUtils.getConstraints(0, 1, 1, 0, 0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			
			//Work Interests
			lblVocWork = new JLabel("Work Interests :");  
			panelVoc.add(lblVocWork,SwingUtils.getConstraints(0, 2, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtVocWork = new JTextField();
			panelVoc.add(txtVocWork,SwingUtils.getConstraints(0, 3, GridBagConstraints.REMAINDER,
					0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			
			// Language fluency
//			panelLang = new JPanel();
			Vector vLanguage = ACEConnector.getInstance(codebase).getLanguageList();
			panelCommLang1 = new CommLangPanel();
			panelCommLang1.setLanguageList(vLanguage);
			panelVoc.add(panelCommLang1,SwingUtils.getConstraints(1, 0, 2, 0,0, 
			GridBagConstraints.NORTHWEST, 
			GridBagConstraints.NONE, 
			5, 5, 5, 5));
			
			panelCommLang2 = new CommLangPanel();
			panelCommLang2.setLanguageList(vLanguage);
			panelVoc.add(panelCommLang2,SwingUtils.getConstraints(2, 0, 2, 0,0, 
			GridBagConstraints.NORTHWEST, 
			GridBagConstraints.NONE, 
			5, 5, 5, 5));
			
			panelCommLang3 = new CommLangPanel();
			panelCommLang3.setLanguageList(vLanguage);
			panelVoc.add(panelCommLang3,SwingUtils.getConstraints(3, 0, 2, 0,0, 
			GridBagConstraints.NORTHWEST, 
			GridBagConstraints.NONE, 
			5, 5, 5, 5));
			
			panelCommLang4 = new CommLangPanel();
			panelCommLang4.setLanguageList(vLanguage);
			panelVoc.add(panelCommLang4,SwingUtils.getConstraints(4, 0, 2, 0,0, 
			GridBagConstraints.NORTHWEST, 
			GridBagConstraints.NONE, 
			5, 5, 5, 5));
			
			// Family Debt
			lblVocFamDebt = new JLabel("Family Debt :"); 
			panelVoc.add(lblVocFamDebt,SwingUtils.getConstraints(5, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			txtVocFamDebt = new JTextField(60);
			panelVoc.add(txtVocFamDebt,SwingUtils.getConstraints(5, 1, GridBagConstraints.REMAINDER,
					0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			// Vocation
			lblVocComm = new JLabel("Comments :"); 
			panelVoc.add(lblVocComm,SwingUtils.getConstraints(7, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			txtVocComm = new JTextArea(4,30);
			panelVoc.add(txtVocComm,SwingUtils.getConstraints(7, 1, GridBagConstraints.REMAINDER,
					1, 1, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.BOTH, 
					5, 5, 5, 5));	
			
			
	// Social Panel
			panelMainSoc = new JPanel();
			panelMainSoc.setLayout(new BorderLayout());
			tabPane.add(panelMainSoc,"Social History");
			
			if (isEditMode == true){								// ND added 5th Apr 16
				panelApplySoc = new JPanel();
				panelApplySoc.setLayout(new BorderLayout());
	     		panelMainSoc.add(panelApplySoc, BorderLayout.EAST);
				btnApplySoc = new JButton("Apply");
				btnApplySoc.addActionListener(this);			// ND added 5th Feb 16
	
				btnApplySoc.setBackground(new Color(249,184,172));
				panelApplySoc.add(btnApplySoc, BorderLayout.SOUTH);
			}
			panelSoc = new JPanel();
			panelMainSoc.add(panelSoc, BorderLayout.CENTER);
			panelSoc.setLayout(new GridBagLayout());
			
			
			// Lived At
			lblSocLivedAt = new JLabel("Lived At :");
			panelSoc.add(lblSocLivedAt,SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			txtSocLivedAt = new JTextField(30);		
			panelSoc.add(txtSocLivedAt,SwingUtils.getConstraints(0, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			// Relationship with Present community 
			lblSocRelWithComm = new JLabel("Relationship with community :");
			panelSoc.add(lblSocRelWithComm,SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			cbSocRelWithComm = new JComboBox();
			for (int i = 0; i < arrGradeGFP.length; i++) {
				cbSocRelWithComm.addItem(arrGradeGFP[i]); }
			panelSoc.add(cbSocRelWithComm,SwingUtils.getConstraints(1, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			// Friends 
			lblSocHerFriends = new JLabel("Friends :");
			panelSoc.add(lblSocHerFriends,SwingUtils.getConstraints(2, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtSocHerFriends = new JTextField(90);
			panelSoc.add(txtSocHerFriends,SwingUtils.getConstraints(2, 1, 1, 1,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			// Relationship with friends
			lblSocRelWithFriends = new JLabel("Relationship with friends :");
			panelSoc.add(lblSocRelWithFriends,SwingUtils.getConstraints(3, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			cbSocRelWithFriends = new JComboBox();
			for (int i = 0; i < arrGradeGFP.length; i++) {
				cbSocRelWithFriends.addItem(arrGradeGFP[i]); }
			panelSoc.add(cbSocRelWithFriends,SwingUtils.getConstraints(3, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			// Social Involvement
			lblSocInvolvement = new JLabel("Involvement here :");
			panelSoc.add(lblSocInvolvement,SwingUtils.getConstraints(4, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			cbSocInvolvement = new JComboBox();
			cbSocInvolvement.addItem("NA");
			cbSocInvolvement.addItem("Active");
			cbSocInvolvement.addItem("Nominal");
			cbSocInvolvement.addItem("Interested");
			cbSocInvolvement.addItem("Non-Active");
			panelSoc.add(cbSocInvolvement,SwingUtils.getConstraints(4, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			// Recreation and Fun
			lblSocRecreationandFun = new JLabel("Recreation and Fun :");
			panelSoc.add(lblSocRecreationandFun,SwingUtils.getConstraints(0, 2, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			lstSocRecreationandFun = new JList(); 	// This too is a multi-choice list
//			Vector<String> vStrRecreationandFun = ACEConnector.getInstance(codebase).getRecreationList();
			populateLst(lstSocRecreationandFun, 
					ACEConnector.getInstance(codebase).getRecreationList());
			panelSoc.add(lstSocRecreationandFun,SwingUtils.getConstraints(0, 3, 1, 6, .7,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					5, 5, 5, 5));
			// Religion or Beliefs
			lblSocReligionBelief = new JLabel("Religion / Belief :");
			panelSoc.add(lblSocReligionBelief,SwingUtils.getConstraints(5, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			cbSocReligionBelief = new JComboBox();	// multi-choice combo box
			cbSocReligionBelief.addItem(NA);
			populate(cbSocReligionBelief, ACEConnector.getInstance(codebase).getReligionList());
			panelSoc.add(cbSocReligionBelief,SwingUtils.getConstraints(5, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			// Comments on Social and Religious Info
			lblSocComments = new JLabel("Comments :");
			panelSoc.add(lblSocComments,SwingUtils.getConstraints(6, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			txtSocComments = new JTextArea();
			panelSoc.add(txtSocComments,SwingUtils.getConstraints(6, 1, 3, 1,1, 
					GridBagConstraints.WEST, 
					GridBagConstraints.BOTH, 
					5, 5, 5, 5));
			
			// Legal History
			panelMainLegal = new JPanel();
			panelMainLegal.setLayout(new BorderLayout());
			tabPane.add(panelMainLegal,"Legal History");
			
			if (isEditMode == true){								// ND added 5th Apr 16
				panelApplyLegal = new JPanel();
				panelApplyLegal.setLayout(new BorderLayout());
	     		panelMainLegal.add(panelApplyLegal, BorderLayout.EAST);
				btnApplyLegal = new JButton("Apply");
				btnApplyLegal.addActionListener(this);			// ND added 5th Feb 16
	
				btnApplyLegal.setBackground(new Color(249,184,172));
				panelApplyLegal.add(btnApplyLegal, BorderLayout.SOUTH);
			}
			panelLegal = new JPanel();
			panelMainLegal.add(panelLegal, BorderLayout.CENTER);
			panelLegal.setLayout(new GridBagLayout());
			
			// Offences
			lblLegHistOffences = new JLabel("Any history of legal offences:");
			panelLegal.add(lblLegHistOffences,SwingUtils.getConstraints(0, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			cbLegHistOffences = new JComboBox();	
			cbLegHistOffences.addItem(NA);
			cbLegHistOffences.addItem("Theft");
			cbLegHistOffences.addItem("Sexual Offences");
			panelLegal.add(cbLegHistOffences,SwingUtils.getConstraints(0, 1, 1, 0,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.NONE, 
					10, 5, 5, 5));
			
			JLabel lblComments = new JLabel("Comments: ");
			panelLegal.add(lblComments,SwingUtils.getConstraints(1, 0, 1, 0,0, 
					GridBagConstraints.NORTHWEST, 
					GridBagConstraints.NONE, 
					5, 5, 5, 5));
			
			txtLegalComments = new JTextField();
			panelLegal.add(txtLegalComments,SwingUtils.getConstraints(1, 1, 
					1, 1,0, 
					GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, 
					5, 5, 5, 5));
			
		//Physical History
		panelMainPhys = new JPanel();
		panelMainPhys.setLayout(new BorderLayout());
		tabPane.add(panelMainPhys, "Physical History");
		
		if (isEditMode == true){								// ND added 5th Apr 16
			panelApplyPhys = new JPanel();
			panelApplyPhys.setLayout(new BorderLayout());
	 		panelMainPhys.add(panelApplyPhys, BorderLayout.EAST);
			btnApplyPhys = new JButton("Apply");
			btnApplyPhys.addActionListener(this);			// ND added 5th Feb 16
	
			btnApplyPhys.setBackground(new Color(249,184,172));
			panelApplyPhys.add(btnApplyPhys, BorderLayout.SOUTH);
		}
		panelPhys = new JPanel();
		panelMainPhys.add(panelPhys, BorderLayout.CENTER);
		panelPhys.setLayout(new GridBagLayout());
			
		//Curr Phys Health you
		lblPhyHealthDescYou  = new JLabel ("Victim's current physical health: ");
		panelPhys.add(lblPhyHealthDescYou,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		cbPhyHealthDescYou = new JComboBox();
		String []arrPhyHealthDesc = {NA, GOOD, FAIR, POOR};
		for (int i = 0; i < arrPhyHealthDesc.length; i++) {
			cbPhyHealthDescYou.addItem(arrPhyHealthDesc[i]);
		}
		panelPhys.add(cbPhyHealthDescYou,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		//Curr Phys Health
		lblPhyHealthDesc  = new JLabel ("Victim's description of current physical health: ");
		panelPhys.add(lblPhyHealthDesc,SwingUtils.getConstraints(0, 2, 3, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 15, 5, 5));
		
		cbPhyHealthDesc = new JComboBox();
		for (int i = 0; i < arrPhyHealthDesc.length; i++) {
			cbPhyHealthDesc.addItem(arrPhyHealthDesc[i]);
		}
		panelPhys.add(cbPhyHealthDesc,SwingUtils.getConstraints(0, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		//Phys Health History
		lblPhyHealthHistory  = new JLabel ("History of serious illness, injury, disability etc. : ");
		panelPhys.add(lblPhyHealthHistory,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtPhyHealthHistory = new JTextField();
		panelPhys.add(txtPhyHealthHistory,SwingUtils.getConstraints(1, 1, GridBagConstraints.REMAINDER, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Disorders/Special Needs
		lblPhyHealthDisord  = new JLabel ("Disorders/Special Needs: ");
		panelPhys.add(lblPhyHealthDisord,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaLearningDisab = new JCheckBox("Learning Disabilities");
		panelPhys.add(chkPhyHeaLearningDisab,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaADHD = new JCheckBox("ADHD");
		panelPhys.add(chkPhyHeaADHD,SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaADD = new JCheckBox("ADD");
		panelPhys.add(chkPhyHeaADD,SwingUtils.getConstraints(2, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaAutism = new JCheckBox("Autism");
		panelPhys.add(chkPhyHeaAutism,SwingUtils.getConstraints(2, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaOther = new JCheckBox("Other");
		panelPhys.add(chkPhyHeaOther,SwingUtils.getConstraints(2, 5, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		//Been to Counselling before
		chkPhyHeaBeentoCnsl  = new JCheckBox("Has been to counselling before");
		panelPhys.add(chkPhyHeaBeentoCnsl,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaPsychMed = new JCheckBox("Taking Psychiatric Medication");
		panelPhys.add(chkPhyHeaPsychMed,SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		//Phys Health addictions
		lblPhyHealthAddict  = new JLabel ("Any addictions: ");
		panelPhys.add(lblPhyHealthAddict,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
//		lstPhyHealthAddict = new JList();
//		String []arrPhyHealthAddict = {ADDICT_CIGARETTES, ADDICT_ALCOHOL, ADDICT_TOBACCO,
//										ADDICT_STEROIDS, ADDICT_OTHER};
//		
//		for (int i = 0; i < arrPhyHealthAddict.length; i++) {
//			cbPhyHealthAddict.addItem(arrPhyHealthAddict[i]);
//		}
//		populateLst(lstPhyHealthAddict, 
//					ACEConnector.getInstance(codebase).getAddictionList());

		lstAddict = new JAceList();
//		lstAddict.setPreferredWidth(200);
		lstAddict.setItems(ACEConnector.getInstance(codebase).getAddictionList());
		panelPhys.add(lstAddict,SwingUtils.getConstraints(4, 1, 2, 0,0, 					// ND edited 06 Mar 16
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
//		txtPhyHealthAddict = new JTextField();
//		panelPhys.add(txtPhyHealthAddict,SwingUtils.getConstraints(4, 2, 2, 0,0, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.HORIZONTAL, 
//				5, 5, 5, 5));
		
		//Phys Health withdrawal sympt
		lblPhyHealthWithdrawSympt = new JLabel("Withdrawal Symptoms: ");
		panelPhys.add(lblPhyHealthWithdrawSympt,SwingUtils.getConstraints(4, 3, 1, 0,0, 		// ND edited 06th Mar 16
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		cbPhyHealthWithdrawSympt = new JComboBox();
		String [] arrPhyHealthWsysmpt = {NA, WSYMPT_RESTLESS, WSYMPT_BSHOT_EYES, WSYMPT_NEEDLE_TRACK,
										WSYMPT_POOR_CONC, WSYMPT_TREMOR, WSYMPT_OTHER};
		
		for (int i = 0; i < arrPhyHealthWsysmpt.length; i++) {
			cbPhyHealthWithdrawSympt.addItem(arrPhyHealthWsysmpt[i]);
		}
		panelPhys.add(cbPhyHealthWithdrawSympt,SwingUtils.getConstraints(4, 4, GridBagConstraints.REMAINDER,
					0,0,GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		//Reprod history
		lblPhyHealthReprodHist = new JLabel ("Sexual or reproductive history: ");
		panelPhys.add(lblPhyHealthReprodHist,SwingUtils.getConstraints(5, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaBirthCtrl = new JCheckBox("Birth Control Use");
		panelPhys.add(chkPhyHeaBirthCtrl,SwingUtils.getConstraints(5, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		chkPhyHeaPreg = new JCheckBox("Pregnancy");
		panelPhys.add(chkPhyHeaPreg,SwingUtils.getConstraints(5, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		chkPhyHeaAbort = new JCheckBox("Abortion");
		panelPhys.add(chkPhyHeaAbort,SwingUtils.getConstraints(5, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		chkPhyHeaSTI = new JCheckBox("STI");
		panelPhys.add(chkPhyHeaSTI,SwingUtils.getConstraints(5, 4, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		chkPhyHeaSTITreat = new JCheckBox("STI Treat");
		panelPhys.add(chkPhyHeaSTITreat,SwingUtils.getConstraints(5, 5, 2, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		//Comments
		lblPhyHistComments = new JLabel("Comments: ");
		panelPhys.add(lblPhyHistComments,SwingUtils.getConstraints(6, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		txtPhyHealthComments = new JTextArea();
		panelPhys.add(txtPhyHealthComments,SwingUtils.getConstraints(6, 1, 6, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				5, 5, 5, 5));
		
		//Mental Status Assessment
		panelMainPsyc = new JPanel();
		panelMainPsyc.setLayout(new BorderLayout());
		tabPane.add(panelMainPsyc, "Mental Status Assessment");
		
		if (isEditMode == true){									// ND added 5th Apr 16
			panelApplyPsyc = new JPanel();
			panelApplyPsyc.setLayout(new BorderLayout());
	 		panelMainPsyc.add(panelApplyPsyc, BorderLayout.EAST);
			btnApplyPsyc = new JButton("Apply");
			btnApplyPsyc.addActionListener(this);			// ND added 5th Feb 16
	
			btnApplyPsyc.setBackground(new Color(249,184,172));
			panelApplyPsyc.add(btnApplyPsyc, BorderLayout.SOUTH);
		}
		panelPsyc = new JPanel();
		panelPsyc.setLayout(new GridBagLayout());
		panelMainPsyc.add(panelPsyc, BorderLayout.CENTER);
		
		//Appearance/hygiene
		lblAppearHyg = new JLabel ("Appearance and hygiene: ");
		panelPsyc.add(lblAppearHyg,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		cbAppearHyg = new JComboBox();
		cbAppearHyg.addItem(NA);
		cbAppearHyg.addItem(WELL_GRROMED);
		cbAppearHyg.addItem(UNTIDY);
		cbAppearHyg.addItem(INAPPROPRIATE);
		panelPsyc.add(cbAppearHyg,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		//Movement
		lblMovement = new JLabel ("Movement: ");
		panelPsyc.add(lblMovement,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbMovement = new JComboBox();
		cbMovement.addItem(NA);
		cbMovement.addItem(CALM);
		cbMovement.addItem(HYPER);
		cbMovement.addItem(AGITATED);
		cbMovement.addItem(SLOW);
		cbMovement.addItem(TREMOR);
		panelPsyc.add(cbMovement,SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
	
		//Affect
		lblAffect = new JLabel ("Affect: ");
		panelPsyc.add(lblAffect,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbAffect = new JComboBox();
		cbAffect.addItem(NA);
		cbAffect.addItem(CONGRUENT);
		cbAffect.addItem(INCONGRUENT);
		cbAffect.addItem(RESTRICTED);
		cbAffect.addItem(FLAT);
		panelPsyc.add(cbAffect,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Mood
		lblMood = new JLabel ("Mood: ");
		panelPsyc.add(lblMood,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbMood = new JComboBox();
		cbMood.addItem(NA);
		cbMood.addItem(APPROPRIATE);
		cbMood.addItem(DEPRESSED);
		cbMood.addItem(ANXIOUS);
		cbMood.addItem(EUPHORIC);
		cbMood.addItem(ANGRY);
		panelPsyc.add(cbMood,SwingUtils.getConstraints(3, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Speech
		lblSpeech = new JLabel ("Speech: ");
		panelPsyc.add(lblSpeech,SwingUtils.getConstraints(4, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbSpeech = new JComboBox();
		cbSpeech.addItem(NA);
		cbSpeech.addItem(NORMAL);
		cbSpeech.addItem(SLURRED);
		cbSpeech.addItem(FAST);
		cbSpeech.addItem(LOUD);
		cbSpeech.addItem(SOFT);
		panelPsyc.add(cbSpeech,SwingUtils.getConstraints(4, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Attitude
		lblAttitude = new JLabel ("Attitude: ");
		panelPsyc.add(lblAttitude,SwingUtils.getConstraints(5, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbAttitude = new JComboBox();
		cbAttitude.addItem(NA);
		cbAttitude.addItem(FRIENDLY);
		cbAttitude.addItem(HOSTILE);
		cbAttitude.addItem(SHY);
		cbAttitude.addItem(WITHDRAWN);
		
		panelPsyc.add(cbAttitude,SwingUtils.getConstraints(5, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Impulse control
		lblImpulse = new JLabel ("Impulse Control: ");
		panelPsyc.add(lblImpulse,SwingUtils.getConstraints(6, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbImpulse = new JComboBox();
		cbImpulse.addItem(NA);
		cbImpulse.addItem(GOOD);
		cbImpulse.addItem(LIMITED);
		cbImpulse.addItem(IMPAIRED);
		panelPsyc.add(cbImpulse,SwingUtils.getConstraints(6, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Understanding
		lblUnderstanding = new JLabel ("Understanding: ");
		panelPsyc.add(lblUnderstanding,SwingUtils.getConstraints(7, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbUnderstanding = new JComboBox();
		cbUnderstanding.addItem(NA);
		cbUnderstanding.addItem(GOOD);
		cbUnderstanding.addItem(LIMITED);
		cbUnderstanding.addItem(ABSENT);
		cbUnderstanding.addItem(NA_AGE);
		panelPsyc.add(cbUnderstanding,SwingUtils.getConstraints(7, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Memory
		lblMemory = new JLabel ("Memory: ");
		panelPsyc.add(lblMemory,SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		cbMemory = new JComboBox();
		cbMemory.addItem(INTACT);
		cbMemory.addItem(IMPAIRED);
		cbMemory.addItem(IMMEDIATE);
		cbMemory.addItem(RECENT);
		cbMemory.addItem(DISTANT);
		panelPsyc.add(cbMemory,SwingUtils.getConstraints(0, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		//Attention
		lblAttention = new JLabel ("Attention: ");
		panelPsyc.add(lblAttention,SwingUtils.getConstraints(1,2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbAttention = new JComboBox();
		cbAttention.addItem(NA);
		cbAttention.addItem(GOOD);
		cbAttention.addItem(FAIR);
		cbAttention.addItem(HIGH_DISTRACTED);
		panelPsyc.add(cbAttention,SwingUtils.getConstraints(1, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Suicidal Ideation
		lblSuicidal = new JLabel ("Suicidal Ideation: ");
		panelPsyc.add(lblSuicidal,SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbSuicidal = new JComboBox();
		cbSuicidal.addItem(NA);
		cbSuicidal.addItem(NONE);
		cbSuicidal.addItem(INTENT);
		cbSuicidal.addItem(PLAN);
		cbSuicidal.addItem(MEANS);
		panelPsyc.add(cbSuicidal,SwingUtils.getConstraints(2, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Homicidal Ideation
		lblHomicidal = new JLabel ("Homicidal Ideation: ");
		panelPsyc.add(lblHomicidal,SwingUtils.getConstraints(3, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbHomicidal = new JComboBox();
		cbHomicidal.addItem(NA);
		cbHomicidal.addItem(NONE);
		cbHomicidal.addItem(INTENT);
		cbHomicidal.addItem(PLAN);
		cbHomicidal.addItem(MEANS);
		panelPsyc.add(cbHomicidal,SwingUtils.getConstraints(3, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Thought Process
		lblThoughtProc = new JLabel ("Thought Process: ");
		panelPsyc.add(lblThoughtProc,SwingUtils.getConstraints(4, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbThoughtProc = new JComboBox();
		cbThoughtProc.addItem(NA);
		cbThoughtProc.addItem(GOAL_DIR);
		cbThoughtProc.addItem(RANDOM);
		cbThoughtProc.addItem(CONFUSED);
		cbThoughtProc.addItem(INTRUSIVE);
		panelPsyc.add(cbThoughtProc,SwingUtils.getConstraints(4, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Hallucinations
		lblHallucination = new JLabel ("Hallucinations: ");
		panelPsyc.add(lblHallucination,SwingUtils.getConstraints(5, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
//		cbHallucination = new JComboBox();
//		cbHallucination.addItem(NONE);
//		cbHallucination.addItem(SOUND);
//		cbHallucination.addItem(SMELL);
//		cbHallucination.addItem(SIGHT);
		lstHallucination = new JAceList();					
		String hl[] = { NA, NONE, SOUND, SMELL, SIGHT};
		lstHallucination.setItems(hl);
		panelPsyc.add(lstHallucination,SwingUtils.getConstraints(5, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Orientation
		lblOrient = new JLabel ("Orientation: ");
		panelPsyc.add(lblOrient,SwingUtils.getConstraints(6, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
//		cbOrientation = new JComboBox();
//		cbOrientation.addItem(ALL_SPHERES);
//		cbOrientation.addItem(IMPAIRED);
//		cbOrientation.addItem(PERSON);
//		cbOrientation.addItem(PLACE);
//		cbOrientation.addItem(TIME);
//		cbOrientation.addItem(PURPOSE);
		lstOrientation = new JAceList();					
		String ot[] = {NA, ALL_SPHERES, IMPAIRED, PERSON, PLACE, TIME, PURPOSE};
		lstOrientation.setItems(ot);
		panelPsyc.add(lstOrientation , SwingUtils.getConstraints(6, 3, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		//Intellectual Level
		lblIntellect = new JLabel ("Intellectual Level: ");
		panelPsyc.add(lblIntellect,SwingUtils.getConstraints(7, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		
		cbIntellect = new JComboBox();
		cbIntellect.addItem(ABOVE_AVG);
		cbIntellect.addItem(AVG);
		cbIntellect.addItem(BELOW_AVG);
		cbIntellect.addItem(DIFF_ASSESS);
		
		panelPsyc.add(cbIntellect,SwingUtils.getConstraints(7, 3, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				0, 5, 5, 5));
		

		/////Abuse
		panelMainAbus = new JPanel();
		panelMainAbus.setLayout(new BorderLayout());
		tabPane.add(panelMainAbus, "Abuse");
		
		if (isEditMode == true){									// ND added 5th Apr 16
			panelApplyAbus = new JPanel();
			panelApplyAbus.setLayout(new BorderLayout());
	 		panelMainAbus.add(panelApplyAbus, BorderLayout.EAST);
			btnApplyAbus = new JButton("Apply");
			btnApplyAbus.addActionListener(this);
			btnApplyAbus.setBackground(new Color(249,184,172));
			panelApplyAbus.add(btnApplyAbus, BorderLayout.SOUTH);
		}
		panelAbus = new JPanel();
		panelAbus.setLayout(new GridBagLayout());
		panelMainAbus.add(panelAbus, BorderLayout.CENTER);
		
		//Verbally abused
		lblAbuseVerbal = new JLabel("Verbally abused? ");
		panelAbus.add(lblAbuseVerbal,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		txtAbuseVerbal = new JTextField();
		panelAbus.add(txtAbuseVerbal,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				10, 5, 5, 5));
		
		//Physically abused
		lblAbusePhys = new JLabel("Physically abused? ");
		panelAbus.add(lblAbusePhys,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtAbusePhys = new JTextField();
		panelAbus.add(txtAbusePhys,SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Sexually abused
		lblAbuseSex = new JLabel("Sexually abused? ");
		panelAbus.add(lblAbuseSex,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtAbuseSex = new JTextField();
		panelAbus.add(txtAbuseSex,SwingUtils.getConstraints(2, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Neglected
		lblAbuseNeglect = new JLabel("Neglected? ");
		panelAbus.add(lblAbuseNeglect,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtAbuseNeglect = new JTextField();
		panelAbus.add(txtAbuseNeglect,SwingUtils.getConstraints(3, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		
		
		///// Strengths
		panelMainStre = new JPanel();
		panelMainStre.setLayout(new BorderLayout());
		tabPane.add(panelMainStre, "Strengths");
		
		if (isEditMode == true){								// ND added 5th Apr 16
			panelApplyStre = new JPanel();
			panelApplyStre.setLayout(new BorderLayout());
	 		panelMainStre.add(panelApplyStre, BorderLayout.EAST);
			btnApplyStre = new JButton("Apply");
			btnApplyStre.addActionListener(this);			// ND added 5th Feb 16
	
			btnApplyStre.setBackground(new Color(249,184,172));
			panelApplyStre.add(btnApplyStre, BorderLayout.SOUTH);
		}
		panelStre = new JPanel();
		panelStre.setLayout(new GridBagLayout());
		panelMainStre.add(panelStre, BorderLayout.CENTER);
		
		//Positive coping skills
		lblStrengthCopeSkills  = new JLabel ("Positive coping skills: ");
		panelStre.add(lblStrengthCopeSkills,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		
//		txtCopingSkills = new JTextField();
		lstStrengthCopeSkills = new JAceList();			
		Vector vCopingSkill = ACEConnector.getInstance(codebase).getCopingSkillList();
		lstStrengthCopeSkills.setItems(vCopingSkill);  		// need to change code in polulateLst()
		panelStre.add(lstStrengthCopeSkills,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				10, 5, 5, 5));
		
		///// Strengths /////
		lblStrength  = new JLabel ("Strengths: ");
		panelStre.add(lblStrength,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStrength = new JTextField();
		panelStre.add(txtStrength,SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
//		//Goals
//		lblStrengthGoal  = new JLabel ("Goals: ");
//		panelStre.add(lblStrengthGoal,SwingUtils.getConstraints(2, 0, 1, 0,0, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.NONE, 
//				5, 5, 5, 5));
		
		//// Panel Goals
		panelStrGoals = new JPanel();
		panelStrGoals.setLayout(new GridBagLayout());
		panelStrGoals.setBorder(new TitledBorder("Does the counsellee have goals for"));
		panelStre.add(panelStrGoals,SwingUtils.getConstraints(2, 0, GridBagConstraints.REMAINDER, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, 
				5, 5, 5, 5));
		
		//Family Goals
		lblStreGoalFamily  = new JLabel ("Family: ");
		panelStrGoals.add(lblStreGoalFamily,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalFamily = new JTextField();
		panelStrGoals.add(txtStreGoalFamily, SwingUtils.getConstraints(0, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Education Goals
		lblStreGoalEdu  = new JLabel ("Education: ");
		panelStrGoals.add(lblStreGoalEdu,SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalEdu = new JTextField();
		panelStrGoals.add(txtStreGoalEdu, SwingUtils.getConstraints(0, 3, GridBagConstraints.REMAINDER,
				1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Social Goals
		lblStreGoalSocial  = new JLabel ("Social: ");
		panelStrGoals.add(lblStreGoalSocial,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalSocial = new JTextField();
		panelStrGoals.add(txtStreGoalSocial, SwingUtils.getConstraints(1, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Psychological Health Goals
		lblStreGoalPsycho  = new JLabel ("Psychological Health: ");
		panelStrGoals.add(lblStreGoalPsycho,SwingUtils.getConstraints(1, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalPsycho = new JTextField();
		panelStrGoals.add(txtStreGoalPsycho, SwingUtils.getConstraints(1, 3, 
				GridBagConstraints.REMAINDER, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Vocational Goals
		lblStreGoalVoc  = new JLabel ("Vocational: ");
		panelStrGoals.add(lblStreGoalVoc,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalVoc = new JTextField();
		panelStrGoals.add(txtStreGoalVoc, SwingUtils.getConstraints(2, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Legal Goals
		lblStreGoalLegal  = new JLabel ("Legal: ");
		panelStrGoals.add(lblStreGoalLegal,SwingUtils.getConstraints(2, 2, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalLegal = new JTextField();
		panelStrGoals.add(txtStreGoalLegal, SwingUtils.getConstraints(2, 3, 
				GridBagConstraints.REMAINDER, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Physical Health Goals
		lblStreGoalPhyHealth  = new JLabel ("Physical Health: ");
		panelStrGoals.add(lblStreGoalPhyHealth,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStreGoalPhyHealth = new JTextField();
		panelStrGoals.add(txtStreGoalPhyHealth, SwingUtils.getConstraints(3, 1, 
				GridBagConstraints.REMAINDER, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		
		//Comments
		lblStrengthComments  = new JLabel ("Comments: ");
		panelStre.add(lblStrengthComments,SwingUtils.getConstraints(3, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));
		
		txtStrengthComments = new JTextField();
		panelStre.add(txtStrengthComments,SwingUtils.getConstraints(3, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 5));
		
		//Bottom Panel
		panelBottom = new JPanel();
		panelBottom.setLayout(new BorderLayout());
		add(panelBottom, BorderLayout.SOUTH);
		
		////Summary of History and Needs   ////
		panelSumm = new JPanel();
//		panelSumm.setBackground(new Color(180,205,232));
		TitledBorder tbSum = new TitledBorder("Summary and Clinical Formulation");
		
		panelSumm.setBorder(tbSum);
		panelSumm.setLayout(new GridBagLayout());
		panelBottom.add(panelSumm, BorderLayout.CENTER);
		
		//Summary
//		lblSummaryTitle = new JLabel("Psychological Needs Assessment");
//		panelSumm.add(lblSummaryTitle,SwingUtils.getConstraints(0, 0, 1, 0,0, 
//				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.HORIZONTAL, 
//				10, 5, 5, 5));

		lblPsychoNeedsCl = new JLabel("Psychological Needs as verbalised by client: ");
		panelSumm.add(lblPsychoNeedsCl,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		txtPsychoNeedsCl = new JTextField();
		panelSumm.add(txtPsychoNeedsCl,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 10));
		
		lblPsychoNeedsCounslr = new JLabel("Counsellor's Assessment: ");
		panelSumm.add(lblPsychoNeedsCounslr,SwingUtils.getConstraints(1, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		txtPsychoNeedsCounslr = new JTextField();
		panelSumm.add(txtPsychoNeedsCounslr,SwingUtils.getConstraints(1, 1, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.HORIZONTAL, 
				5, 5, 5, 10));
		
		lblIntervenPlan = new JLabel("Intervention Plan: ");
		panelSumm.add(lblIntervenPlan,SwingUtils.getConstraints(2, 0, 1, 0,0, 
				GridBagConstraints.NORTHWEST, 
				GridBagConstraints.NONE, 
				5, 5, 5, 5));

		txtIntervenPlan = new JTextField();
		panelSumm.add(txtIntervenPlan,SwingUtils.getConstraints(2, 1, 1, 1,1, 
				GridBagConstraints.NORTHWEST, 
//				GridBagConstraints.HORIZONTAL, 						// ND edited 06 Mar 16
				1,
				5, 5, 5, 10));
		
//		panelApplySumm = new JPanel();					// ND added 5th Mar 16
//		panelApplySumm.setLayout(new BorderLayout());
// 		panelSumm.add(panelApplySumm, BorderLayout.EAST);

		// Apply button not required for Summary panel.
//		btnApplySumm = new JButton("Apply");
//		btnApplySumm.addActionListener(this);			
//		btnApplySumm.setBackground(new Color(249,184,172));
////		panelApplySumm.add(btnApplySumm, BorderLayout.SOUTH);
//		panelSumm.add(btnApplySumm, SwingUtils.getConstraints(2, 2, 1, 0, 0, 
//				GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
//				4, 5, 4, 5));
		
		JPanel panelBottButtons = new JPanel();
		panelBottom.add(panelBottButtons, BorderLayout.SOUTH);
		
		//Save button
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		panelBottButtons.add(btnSave);
		
		//Cancel button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		panelBottButtons.add(btnCancel);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().equals(btnAdd)){
			CounseleeRelativeIndivObj obj = new CounseleeRelativeIndivObj();

			if (txtRelName != null){								// ND added 1st Apr 16
			//add row to the table
				// txtIndivID cannot be null if record being edited but can be null if new record being added i.e. isEditMode = false
			if (isEditMode == true){				// ND added 21st Apr 16
				if (txtIndivID != null)
					obj.setCaseNumber(txtIndivID.getText());
			}
			obj.setRelName(txtRelName.getText());
			obj.setRelAge(txtRelAge.getText());
			obj.setRelationship(cmbRelShip.getSelectedItem().toString());
			obj.setRelStrength(cmbRelStre.getSelectedItem().toString());
			obj.setRelAwareOfVictimsSituation(chkRelAware.isSelected());
			obj.setRelProfession(txtRelProfession.getText());
			obj.setRelComments(txtRelComments.getText());
			
			//add the rest of the code
			modelFamHist.addRowObject(obj);
			}
		}
		
		if (e.getSource().equals(btnApplyAbus)){
			updateAbuseHist();
		}

// start ND added 05th Feb 16
		else if (e.getSource().equals(btnApplyFamHist)) {
			updateFamHist();
		}
	// start ND added 25th Apr 16
		else if (e.getSource().equals(menuRelEdit)){
			editFamHist();
		}
		else if (e.getSource().equals(menuRelRemove)){
			delRelFamHist();
		}
	// end ND added 25th Apr 16
		else if (e.getSource().equals(btnApplyFamEnv)){
			updateFamEnv();
		}
		else if (e.getSource().equals(btnApplyEdu)) { 
			updateEdu();
		}
		else if (e.getSource().equals(btnApplyVoc)){
			updateVoc();
		}
		else if (e.getSource().equals(btnApplySoc)){
			updateSoc();
		}
		else if (e.getSource().equals(btnApplyPhys)) {
			updatePhys();
		}
		else if (e.getSource().equals(btnApplyPsyc)){
			updatePsyc();
		}
		else if (e.getSource().equals(btnApplyStre)){ 
			updateStre();
		}
		else if (e.getSource().equals(btnApplySumm)){
			updateSumm();
		}
		else if (e.getSource().equals(btnApplyLegal)){
			updateLegal();
		}
// end ND added 5th Feb 16
		
		else if (e.getSource().equals(btnCancel)){
			this.setVisible(false);
		}
		// ND edited 5th Apr 16
		else if (e.getSource().equals(btnSave)){
			if (chkIntegIndiv() == true){

			if (isEditMode == true){	
				updateCnslee();
				updateSumm();
			}
			else {
					save();
			}
		}
		}
		else if (e.getSource().equals(chkEduAttend)){
			enableSchool();
		}
		
	}
	
	private void enableSchool(){
		txtEduWhere.setEnabled(chkEduAttend.isSelected());
		txtEduStd.setEnabled(chkEduAttend.isSelected());
		cbEduSchExp.setEnabled(chkEduAttend.isSelected());
	}
	
	// ND edited 5th Apr 16
	private boolean chkIntegIndiv(){
		
		if (txtIndivLName.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Please enter a name for the beneficiary.");
			return (false);
		}
		if (cbPartnerOrg.getSelectedIndex() < 0){
			JOptionPane.showMessageDialog(this, "Please enter a partner organization for beneficiary.");
			return (false);
		}
		if (cbLocation.getSelectedIndex() < 0){
			JOptionPane.showMessageDialog(this, "Please enter a location for beneficiary.");
			return (false);
		}
		if (txtAge.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(this, "Please enter age for beneficiary.");
			return (false);
		}
		
		return (true);
	}
	
	
	private void save() {
		try{
			
			//Save counsellee basic data
			Counsellee cnslee = new Counsellee();
			cnslee.setName(new PersonName(txtIndivLName.getText() +"^" + 
								txtIndivFName.getText() + "^" +
								txtIndivMName.getText()));
			cnslee.setOtherName(txtAlias.getText());
			System.out.println(cnslee.getOtherName() + "%%%%%%%%%%%%%%%%%");
			cnslee.setAge(Integer.parseInt(txtAge.getText()));
			cnslee.setLocation(cbLocation.getSelectedItem().toString());
			cnslee.setParentOrg(cbPartnerOrg.getSelectedItem().toString());
			cnslee.setDate(dateAssessment.getDate());
			
			cnslee.setDob(dateDOB.getDate());								// ND added 02nd Apr 16
			cnslee.setDtTerm(dateTermination.getDate());					// ND added 02nd Apr 16
			cnslee.setGender(cbGender.getSelectedItem().toString());		// ND added 02nd Apr 16

			
			
			
			//Save counselee History details
			CounseleeHistoryObj cnslHist = new CounseleeHistoryObj();
//			cnslHist.setCounseleeID(cnslee.getCaseNumber());
			//TODO
			//Get all the history details into this object
			//Add the CounselleHistoryObject to the saveBeneficiary method
			AbuseIndivObj abuObj = new AbuseIndivObj();
			EducationHistIndivObj eduObj = new EducationHistIndivObj();
			Vector vCnslRelnObj;
			FamilyEnvIndivObj famEnv = new FamilyEnvIndivObj();
			PhysicalHistIndivObj phyHistObj = new PhysicalHistIndivObj();
			MentalStatusIndivObj menStatObj = new MentalStatusIndivObj();
			SocialHistIndivObj socHistObj = new SocialHistIndivObj();
			StrenghtIndivObj cnslStrObj = new StrenghtIndivObj();
			SummaryIndivObj cnslSumObj = new SummaryIndivObj();
			VocationFinIndivObj vocObj = new VocationFinIndivObj();
			LegalHistIndivObj legalObj = new LegalHistIndivObj();

			abuObj.setAbuseVerbal(txtAbuseVerbal.getText());
			abuObj.setAbusePhys(txtAbusePhys.getText());
			abuObj.setAbuseSex(txtAbuseSex.getText());
			abuObj.setAbuseNeglect(txtAbuseNeglect.getText());
			cnslHist.setabuseIndivObj(abuObj);
			
			eduObj.setEduAttend(chkEduAttend.isSelected());
			eduObj.setEduWhere(txtEduWhere.getText());
			eduObj.setEduStd(txtEduStd.getText());
			eduObj.setEduSchExp(cbEduSchExp.getSelectedItem().toString());
			eduObj.setEduLitClass(chkEduLitClass.isSelected());
			eduObj.setEduCont(chkEduCont.isSelected());
			eduObj.setEduNonForm(new Vector(lstEduNonForm.getSelectedValuesList()));
			eduObj.setEduComm(txtEduComm.getText());
			cnslHist.seteduIndivObj(eduObj);
			
			vCnslRelnObj = modelFamHist.getRowObjects();
			cnslHist.setvCnslRelnObj(vCnslRelnObj);

			// ND added this on 8th Dec
			famEnv.setFamEnvGetsAlong(cbEnvGetsAlong.getSelectedItem().toString());
			famEnv.setFamEnvVerAb(cbEnvVerAb.getSelectedItem().toString());
			famEnv.setFamEnvPhyAb(cbEnvPhyAb.getSelectedItem().toString());
			famEnv.setFamEnvSexAb(cbEnvSexAb.getSelectedItem().toString());
			famEnv.setFamEnvSubAb(cbEnvSubAb.getSelectedItem().toString());
			famEnv.setFamEnvNeg(cbEnvNeg.getSelectedItem().toString());
			famEnv.setFamEnvPros(cbEnvPros.getSelectedItem().toString());
			famEnv.setFamEnvComm(txtEnvComm.getText());		
			cnslHist.setfamEnvIndivObj(famEnv);

			phyHistObj.setPhyHistCurrYou(cbPhyHealthDescYou.getSelectedItem().toString());
			phyHistObj.setPhyHistCurr(cbPhyHealthDesc.getSelectedItem().toString());			// Counselee's input
			phyHistObj.setPhysicalHistSerious(txtPhyHealthHistory.getText());					// Serious illness
			// the Disorder and Special needs data may need to go under Mental Status
			phyHistObj.setLearnDisable(chkPhyHeaLearningDisab.isSelected());
			// ND added 19th Dec
			phyHistObj.setADHDisorder(chkPhyHeaADHD.isSelected());
			phyHistObj.setADDisorder(chkPhyHeaADD.isSelected());
			phyHistObj.setAutism(chkPhyHeaAutism.isSelected());
			phyHistObj.setOtherDisorder(chkPhyHeaOther.isSelected());
			phyHistObj.setBeenCounseled(chkPhyHeaBeentoCnsl.isSelected());
			phyHistObj.setPsychMedsCurr(chkPhyHeaPsychMed.isSelected());					// ND edited 16th Mar 16

			// lstAddict (JAceList) has replaced lstPhyHealthAddict and the change needs to be done here
//			phyHistObj.setPhyHistAddict(new Vector(lstPhyHealthAddict.getSelectedValuesList()));
			phyHistObj.setPhyHistAddict(lstAddict.getSelectedItems());

			phyHistObj.setPhyHistWithdrawSympt(cbPhyHealthWithdrawSympt.getSelectedItem().toString());
			phyHistObj.setPhyHistBirthCtrl(chkPhyHeaBirthCtrl.isSelected());
			phyHistObj.setPhyHistPreg(chkPhyHeaPreg.isSelected());
			phyHistObj.setPhyHistAbort(chkPhyHeaAbort.isSelected());
			phyHistObj.setPhyHistSTI(chkPhyHeaSTI.isSelected());
			phyHistObj.setPhyHistSTITreat(chkPhyHeaSTITreat.isSelected());
			phyHistObj.setPhyHistComments(txtPhyHealthComments.getText());
			cnslHist.setphyIndivObj(phyHistObj);
			
			menStatObj.setMentalStAppearHyg(cbAppearHyg.getSelectedItem().toString());
			menStatObj.setMentalStMovement(cbMovement.getSelectedItem().toString());
			menStatObj.setMentalStAffect(cbAffect.getSelectedItem().toString());
			menStatObj.setMentalStMood(cbMood.getSelectedItem().toString());
			menStatObj.setMentalStSpeech(cbSpeech.getSelectedItem().toString());
			menStatObj.setMentalStAttitude(cbAttitude.getSelectedItem().toString());
			menStatObj.setMentalStImpulse(cbImpulse.getSelectedItem().toString());
			menStatObj.setMentalStUnderstanding(cbUnderstanding.getSelectedItem().toString());
			menStatObj.setMentalStMemory(cbMemory.getSelectedItem().toString());
			menStatObj.setMentalStAttention(cbAttention.getSelectedItem().toString());
			menStatObj.setMentalStSuicidal(cbSuicidal.getSelectedItem().toString());
			menStatObj.setMentalStHomicidal(cbHomicidal.getSelectedItem().toString());
			menStatObj.setMentalStThoughtProc(cbThoughtProc.getSelectedItem().toString());
			menStatObj.setMentalStHallucination(lstHallucination.getSelectedItems());
			menStatObj.setMentalStOrientation(lstOrientation.getSelectedItems());
			menStatObj.setMentalStIntellect(cbIntellect.getSelectedItem().toString());
			cnslHist.setmenStatIndivObj(menStatObj);
			
			socHistObj.setSocLivedAt(txtSocLivedAt.getText());
			socHistObj.setSocRelWithComm(cbSocRelWithComm.getSelectedItem().toString());
			socHistObj.setSocHerFriends(txtSocHerFriends.getText());
			socHistObj.setSocRelWithFriends(cbSocRelWithFriends.getSelectedItem().toString());
			socHistObj.setSocInvolvement(cbSocInvolvement.getSelectedItem().toString());
			socHistObj.setSocReligionBelief(cbSocReligionBelief.getSelectedItem().toString());
			socHistObj.setSocRecreationandFun(new Vector(lstSocRecreationandFun.getSelectedValuesList()));
			socHistObj.setSocComments(txtSocComments.getText());
			cnslHist.setsocIndivObj(socHistObj);
			
			
			cnslStrObj.setvStrengthCopeSkills(lstStrengthCopeSkills.getSelectedItems());
			cnslStrObj.setStrengthStrengths(txtStrength.getText());
			cnslStrObj.setStengthFlyGoals(txtStreGoalFamily.getText());
			cnslStrObj.setStrengthSocialGoals(txtStreGoalSocial.getText());
			cnslStrObj.setStrengthVocGoals(txtStreGoalVoc.getText());
			cnslStrObj.setStrengthPhysicHealthGoals(txtStreGoalPhyHealth.getText());
			cnslStrObj.setStrengthEduGoals(txtStreGoalEdu.getText());
			cnslStrObj.setStrengthPsychoHealthGoals(txtStreGoalPsycho.getText());
			cnslStrObj.setStrengthLegalGoals(txtStreGoalLegal.getText());
			cnslStrObj.setStrengthComments(txtStrengthComments.getText());
			cnslHist.setstrengthIndivObj(cnslStrObj);
			
			cnslSumObj.setPsychoClinicFormulation(txtPsychoNeedsCl.getText());				// ND edited on 7th Dec
			cnslSumObj.setPsychoNeedsCnsr(txtPsychoNeedsCounslr.getText());
			cnslSumObj.setPsychoIntervenPlan(txtIntervenPlan.getText());					// ND edited on 7th Dec
			cnslHist.setsummIndivObj(cnslSumObj);
			
			vocObj.setVocPriorEmp(txtVocPriorEmp.getText());
			vocObj.setVocWork(txtVocWork.getText());
			Vector vCommLang = new Vector();
			if (panelCommLang1.getLanguage() != null)
				vCommLang.add(panelCommLang1.getLanguage());
			if (panelCommLang2.getLanguage() != null)
				vCommLang.add(panelCommLang2.getLanguage());
			if (panelCommLang3.getLanguage() != null)
				vCommLang.add(panelCommLang3.getLanguage());
			if (panelCommLang4.getLanguage() != null)
				vCommLang.add(panelCommLang4.getLanguage());	
			vocObj.setvLanguages(vCommLang);
			
			vocObj.setVocFamDebt(txtVocFamDebt.getText());
			vocObj.setVocComm(txtVocComm.getText());
			cnslHist.setvocIndivObj(vocObj);
			
			legalObj.setLegalOffences(cbLegHistOffences.getSelectedItem().toString());		//ND added on 18th Dec
			legalObj.setLegalComments(txtLegalComments.getText());
			cnslHist.setLegalHistIndivObj(legalObj);
			
			if (ACEConnector.getInstance(codebase).saveCounsellee(cnslee, cnslHist)){
				System.out.println("INTAKE FORM DLG - save success");
				//close the dialog
				this.setVisible(false);
				parent.refreshData();
			}else{
				JOptionPane.showMessageDialog(this, "Error saving details to server","Error", JOptionPane.ERROR_MESSAGE);
			}

			// ND commented this on 8th Dec
//			if (ACEConnector.getInstance(codebase).saveCounseleeHist(cnslHist)) {
//				System.out.println("INTAKE FORM DETAILS - save Counselee History success");
//				//close the dialog
//				this.setVisible(false);
//				parent.refreshData();
//			}else{
//				JOptionPane.showMessageDialog(this, "Error saving history details to server","Error", JOptionPane.ERROR_MESSAGE);
//			}
		 	
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Error saving History details!");
			e.printStackTrace();
		}
	}
	
						// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Beginning of Updating each tab ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
	
	private void updateCnslee() {								// ND added 5th Apr 16
		Counsellee cnslee = new Counsellee();
		cnslee.setName(new PersonName(txtIndivLName.getText() +"^" + 
							txtIndivFName.getText() + "^" +
							txtIndivMName.getText()));
		cnslee.setOtherName(txtAlias.getText());
		System.out.println(cnslee.getOtherName() + "%%%%%%%%%%%%%%%%%");
		cnslee.setAge(Integer.parseInt(txtAge.getText()));
		cnslee.setLocation(cbLocation.getSelectedItem().toString());
		cnslee.setParentOrg(cbPartnerOrg.getSelectedItem().toString());
		cnslee.setDate(dateAssessment.getDate());
		
		cnslee.setDob(dateDOB.getDate());								
		cnslee.setDtTerm(dateTermination.getDate());					
		cnslee.setGender(cbGender.getSelectedItem().toString());		
		cnslee.setCaseNumber(txtIndivID.getText().toString());						
		System.out.println("Old ID b4 updating:" + txtIndivID.getText().toString());
		
		if (ACEConnector.getInstance(codebase).updateCounselee(cnslee)){
			System.out.println("INTAKE FORM DLG - Update Counselee basic info success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Counselee basic info to server","Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void updateAbuseHist(){
		AbuseIndivObj abuObj = new AbuseIndivObj();
		abuObj.setCaseNumber(cnslee.getCaseNumber());
		abuObj.setAbuseVerbal(txtAbuseVerbal.getText());
		abuObj.setAbusePhys(txtAbusePhys.getText());
		abuObj.setAbuseSex(txtAbuseSex.getText());
		abuObj.setAbuseNeglect(txtAbuseNeglect.getText());

		if (ACEConnector.getInstance(codebase).updateAbuHist(abuObj)){
			System.out.println("INTAKE FORM DLG - Update Abuse Hist success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Abuse history details to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	// start ND added 5th Feb 16
	private void updateFamEnv() {
		FamilyEnvIndivObj famEnvObj = new FamilyEnvIndivObj();
		famEnvObj.setCaseNumber(cnslee.getCaseNumber());
		famEnvObj.setFamEnvGetsAlong(cbEnvGetsAlong.getSelectedItem().toString());	
		famEnvObj.setFamEnvVerAb(cbEnvVerAb.getSelectedItem().toString());
		famEnvObj.setFamEnvPhyAb(cbEnvPhyAb.getSelectedItem().toString());
		famEnvObj.setFamEnvSexAb(cbEnvSexAb.getSelectedItem().toString());
		famEnvObj.setFamEnvSubAb(cbEnvSubAb.getSelectedItem().toString());
		famEnvObj.setFamEnvNeg(cbEnvNeg.getSelectedItem().toString());
		famEnvObj.setFamEnvPros(cbEnvPros.getSelectedItem().toString());
		famEnvObj.setFamEnvComm(txtEnvComm.getText());		
		
		if (ACEConnector.getInstance(codebase).updateFamEnv(famEnvObj)) {
			System.out.println("INTAKE FORM DLG - Update Family environment success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Family environment details to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateFamHist() {															// ND edited on 19th Apr 16
		Vector vFamHistObj ;
		String caseNum = null;
		vFamHistObj = modelFamHist.getRowObjects();
		
//		if (vFamHistObj.size() > 0) {																		// ND added 19th Apr 16
		
		caseNum = cnslee.getCaseNumber();													// ND added 02nd May 16
		if (ACEConnector.getInstance(codebase).updateCnslReln(vFamHistObj, caseNum)) {
			System.out.println("INTAKE FORM DLG - Update Family History success");
		} else {
			JOptionPane.showMessageDialog(this, "Error updating Family History to server" , "Error", JOptionPane.ERROR_MESSAGE);
		}
//		}
	}
	
	private void editFamHist() {											// ND added 15th Apr 16
		Vector <CounseleeRelativeIndivObj> vFamHistEditObj;
		CounseleeRelativeIndivObj cnslRObj;
		int selRow;
		
		selRow = tblRelatives.getSelectedRow();
		cnslRObj = modelFamHist.getRowObject( tblRelatives.getSelectedRow());
//		vFamHistEditObj = modelFamHist.getRowObjects( );
		if (cnslRObj != null){
		
	//		System.out.println("The relative details for editing: " + cnslRObj.getRelationship().toString());
			txtRelName.setText(cnslRObj.getRelName().toString() );
			txtRelAge.setText(cnslRObj.getRelAge().toString());
			txtRelComments.setText(cnslRObj.getRelComments().toString());
			txtRelProfession.setText(cnslRObj.getRelProfession().toString());
			cmbRelShip.setSelectedItem(cnslRObj.getRelationship());
			chkRelAware.setSelected(cnslRObj.isRelAwareOfVictimsSituation());
			cmbRelStre.setSelectedItem(cnslRObj.getRelStrength().toString());
			modelFamHist.delTableRow(selRow);
		}
	}
	private void delRelFamHist() {												// ND added 26th Apr 16
		int[] selRows = new int[20];
		Vector<CounseleeRelativeIndivObj> vFamRel = new Vector<CounseleeRelativeIndivObj>();
		int selRow;
		if (tblRelatives.getSelectedRowCount() > 0){
			selRows = tblRelatives.getSelectedRows();
			for (int i = 0; i < selRows.length; i++){
				CounseleeRelativeIndivObj cnslRelObj = new CounseleeRelativeIndivObj();
				cnslRelObj = modelFamHist.getRowObject(selRows[i]);
				vFamRel.add(cnslRelObj);
			}
			modelFamHist.delTableRows(vFamRel, selRows);
			//modelFamHist.delTableRow(selRows[i]);
		} 
	}
	private void updateEdu(){
		EducationHistIndivObj eduObj = new EducationHistIndivObj();
		eduObj.setCaseNumber(cnslee.getCaseNumber());
		eduObj.setEduAttend(chkEduAttend.isSelected());
		eduObj.setEduWhere(txtEduWhere.getText());
		eduObj.setEduStd(txtEduStd.getText());
		eduObj.setEduSchExp(cbEduSchExp.getSelectedItem().toString());
		eduObj.setEduLitClass(chkEduLitClass.isSelected());
		eduObj.setEduCont(chkEduCont.isSelected());
		eduObj.setEduNonForm(new Vector(lstEduNonForm.getSelectedValuesList()));			
		eduObj.setEduComm(txtEduComm.getText());

		if (ACEConnector.getInstance(codebase).updateEduHist(eduObj)) {
			System.out.println("INTAKE FORM DLG - Update Education hist success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Education history to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updatePhys(){
		PhysicalHistIndivObj phyHistObj = new PhysicalHistIndivObj();
		phyHistObj.setCaseNumber(cnslee.getCaseNumber());
		phyHistObj.setPhyHistCurrYou(cbPhyHealthDescYou.getSelectedItem().toString());
		phyHistObj.setPhyHistCurr(cbPhyHealthDesc.getSelectedItem().toString());			// Counselee's input
		phyHistObj.setPhysicalHistSerious(txtPhyHealthHistory.getText());					// Serious illness
		phyHistObj.setLearnDisable(chkPhyHeaLearningDisab.isSelected());
		phyHistObj.setADHDisorder(chkPhyHeaADHD.isSelected());
		phyHistObj.setADDisorder(chkPhyHeaADD.isSelected());
		phyHistObj.setAutism(chkPhyHeaAutism.isSelected());
		phyHistObj.setOtherDisorder(chkPhyHeaOther.isSelected());
		phyHistObj.setBeenCounseled(chkPhyHeaBeentoCnsl.isSelected());
		phyHistObj.setBeenCounseled(chkPhyHeaPsychMed.isSelected());
		
//		phyHistObj.setPhyHistAddict(new Vector(lstPhyHealthAddict.getSelectedValuesList()));
		phyHistObj.setPhyHistAddict(lstAddict.getSelectedItems());						// ND edited 16th Mar 16
		phyHistObj.setPhyHistWithdrawSympt(cbPhyHealthWithdrawSympt.getSelectedItem().toString());
		phyHistObj.setPhyHistBirthCtrl(chkPhyHeaBirthCtrl.isSelected());
		phyHistObj.setPhyHistPreg(chkPhyHeaPreg.isSelected());
		phyHistObj.setPhyHistAbort(chkPhyHeaAbort.isSelected());
		phyHistObj.setPhyHistSTI(chkPhyHeaSTI.isSelected());
		phyHistObj.setPhyHistSTITreat(chkPhyHeaSTITreat.isSelected());
		phyHistObj.setPhyHistComments(txtPhyHealthComments.getText());
	
		if (ACEConnector.getInstance(codebase).updatePhyHist(phyHistObj)) {
			System.out.println("INTAKE FORM DLG - Update Physical hist success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Physical history to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updatePsyc(){
		MentalStatusIndivObj menStatObj = new MentalStatusIndivObj();
		menStatObj.setCaseNumber(cnslee.getCaseNumber());
		menStatObj.setMentalStAppearHyg(cbAppearHyg.getSelectedItem().toString());
		menStatObj.setMentalStMovement(cbMovement.getSelectedItem().toString());
		menStatObj.setMentalStAffect(cbAffect.getSelectedItem().toString());
		menStatObj.setMentalStMood(cbMood.getSelectedItem().toString());
		menStatObj.setMentalStSpeech(cbSpeech.getSelectedItem().toString());
		menStatObj.setMentalStAttitude(cbAttitude.getSelectedItem().toString());
		menStatObj.setMentalStImpulse(cbImpulse.getSelectedItem().toString());
		menStatObj.setMentalStUnderstanding(cbUnderstanding.getSelectedItem().toString());
		menStatObj.setMentalStMemory(cbMemory.getSelectedItem().toString());
		menStatObj.setMentalStAttention(cbAttention.getSelectedItem().toString());
		menStatObj.setMentalStSuicidal(cbSuicidal.getSelectedItem().toString());
		menStatObj.setMentalStHomicidal(cbHomicidal.getSelectedItem().toString());
		menStatObj.setMentalStThoughtProc(cbThoughtProc.getSelectedItem().toString());
		menStatObj.setMentalStHallucination(lstHallucination.getSelectedItems());
		menStatObj.setMentalStOrientation(lstOrientation.getSelectedItems());
		menStatObj.setMentalStIntellect(cbIntellect.getSelectedItem().toString());
	
		if (ACEConnector.getInstance(codebase).updateMenStat(menStatObj)) {
			System.out.println("INTAKE FORM DLG - Update Psychological hist success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Psychological history to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateSoc() {
		SocialHistIndivObj socHistObj = new SocialHistIndivObj();
		socHistObj.setCaseNumber(cnslee.getCaseNumber());
		socHistObj.setSocLivedAt(txtSocLivedAt.getText());
		socHistObj.setSocRelWithComm(cbSocRelWithComm.getSelectedItem().toString());
		socHistObj.setSocHerFriends(txtSocHerFriends.getText());
		socHistObj.setSocRelWithFriends(cbSocRelWithFriends.getSelectedItem().toString());
		socHistObj.setSocInvolvement(cbSocInvolvement.getSelectedItem().toString());
		socHistObj.setSocReligionBelief(cbSocReligionBelief.getSelectedItem().toString());
		socHistObj.setSocRecreationandFun(new Vector(lstSocRecreationandFun.getSelectedValuesList()));
		socHistObj.setSocComments(txtSocComments.getText());
		
		if (ACEConnector.getInstance(codebase).updateSocHist(socHistObj)) {
			System.out.println("INTAKE FORM DLG - Update Social hist success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Social history to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateStre(){
		StrenghtIndivObj cnslStrObj = new StrenghtIndivObj();
		cnslStrObj.setCaseNumber(cnslee.getCaseNumber());
		cnslStrObj.setvStrengthCopeSkills(lstStrengthCopeSkills.getSelectedItems());
		cnslStrObj.setStrengthStrengths(txtStrength.getText());
		cnslStrObj.setStengthFlyGoals(txtStreGoalFamily.getText());
		cnslStrObj.setStrengthSocialGoals(txtStreGoalSocial.getText());
		cnslStrObj.setStrengthVocGoals(txtStreGoalVoc.getText());
		cnslStrObj.setStrengthPhysicHealthGoals(txtStreGoalPhyHealth.getText());
		cnslStrObj.setStrengthEduGoals(txtStreGoalEdu.getText());
		cnslStrObj.setStrengthPsychoHealthGoals(txtStreGoalPsycho.getText());
		cnslStrObj.setStrengthLegalGoals(txtStreGoalLegal.getText());
		cnslStrObj.setStrengthComments(txtStrengthComments.getText());
	
		if (ACEConnector.getInstance(codebase).updateCnslStrength(cnslStrObj)) {
			System.out.println("INTAKE FORM DLG - Update Counselee strength success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Counselee strength to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateSumm(){
		SummaryIndivObj cnslSumObj = new SummaryIndivObj();
		cnslSumObj.setCaseNumber(cnslee.getCaseNumber());
		cnslSumObj.setPsychoClinicFormulation(txtPsychoNeedsCl.getText());				// ND edited on 7th Dec
		cnslSumObj.setPsychoNeedsCnsr(txtPsychoNeedsCounslr.getText());
		cnslSumObj.setPsychoIntervenPlan(txtIntervenPlan.getText());					// ND edited on 7th Dec
		System.out.println("Sending Summary info for updating");
		if (ACEConnector.getInstance(codebase).updateCnslSumm(cnslSumObj)) {
			System.out.println("INTAKE FORM DLG - Update Counselee summary info success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Counselee summary info to server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateVoc(){
		VocationFinIndivObj vocObj = new VocationFinIndivObj();
		vocObj.setCaseNumber(cnslee.getCaseNumber());
		vocObj.setVocPriorEmp(txtVocPriorEmp.getText());
		vocObj.setVocWork(txtVocWork.getText());
		Vector vCommLang = new Vector();
		if (panelCommLang1.getLanguage() != null)
			vCommLang.add(panelCommLang1.getLanguage());
		if (panelCommLang2.getLanguage() != null)
			vCommLang.add(panelCommLang2.getLanguage());
		if (panelCommLang3.getLanguage() != null)
			vCommLang.add(panelCommLang3.getLanguage());
		if (panelCommLang4.getLanguage() != null)
			vCommLang.add(panelCommLang4.getLanguage());	
		vocObj.setvLanguages(vCommLang);
		
		vocObj.setVocFamDebt(txtVocFamDebt.getText());
		vocObj.setVocComm(txtVocComm.getText());
	
		if (ACEConnector.getInstance(codebase).updateVocHist(vocObj)) {
			System.out.println("INTAKE FORM DLG - Update Vocational history success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Vocational history server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateLegal() {
		LegalHistIndivObj legalObj = new LegalHistIndivObj();
		legalObj.setCaseNumber(cnslee.getCaseNumber());
		legalObj.setLegalOffences(cbLegHistOffences.getSelectedItem().toString());		//ND added on 18th Dec
		legalObj.setLegalComments(txtLegalComments.getText());
	
		if (ACEConnector.getInstance(codebase).updateLegalHist(legalObj)) {
			System.out.println("INTAKE FORM DLG - Update Legal info success");
		}else{
			JOptionPane.showMessageDialog(this, "Error updating Legal info server","Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// end ND added 5th Feb 16
	
	private void populate (JComboBox cb, Vector vItems){
		if (cb == null || vItems == null)
			return;
		
		for (int i = 0; i < vItems.size(); i++) {
			
			cb.addItem(vItems.elementAt(i));
		}
	}
	
	private void populateLst (JList lst, Vector vItems){
		
		int a = 1;
		if (lst == null || vItems == null)
			return;
		System.out.println("The values in the list b4 Jlist populate: " + vItems.elementAt(a) + ", "+ vItems.elementAt(a+1));
		lst.setListData(vItems);
		
	}
								// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Beginning of setEditValues ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //
	private void setEditValues(){
	try {
//		Counsellee cnslee_all = cnsleeHist.getCounseleeObj();
		
		txtIndivLName.setText(cnslee.getName().getLastName());
// start ND added / edited on 2nd Apr 16
		txtIndivFName.setText(cnslee.getName().getFName());
		txtIndivMName.setText(cnslee.getName().getMName());
		txtAlias.setText(cnslee.getOtherName());				
		cbPartnerOrg.setSelectedItem(cnslee.getParentOrg());				
		System.out.println("Displaying Parent Orgn: " + cnslee.getParentOrg() + " at: " + cnslee.getLocation() + " age: " + cnslee.getAge()
				+ " Term: " + cnslee.getDtTerm());
		cbLocation.setSelectedItem(cnslee.getLocation());
		txtAge.setText(String.valueOf(cnslee.getAge()));
		dateDOB.setDate(cnslee.getDob());
		txtIndivID.setText(cnslee.getCaseNumber());
		dateAssessment.setDate(cnslee.getDate());
		dateTermination.setDate(cnslee.getDtTerm());
		
// end ND added / edited on 2nd Apr 16
		
		if (cnsleeHist == null){
			System.out.println("Counselee History obj null in setEditValues");
			return;
		}
		AbuseIndivObj abuObj = cnsleeHist.getabuseIndivObj();
		if (abuObj != null){
			System.out.println("Abuse Object not null");
			txtAbuseVerbal.setText(abuObj.getAbuseVerbal());
			txtAbusePhys.setText(abuObj.getAbusePhys());
			txtAbuseSex.setText(abuObj.getAbuseSex());
			txtAbuseNeglect.setText(abuObj.getAbuseNeglect());
		}
		else
			System.out.println("Abuse Object for editing is null");
		
		EducationHistIndivObj eduObj = cnsleeHist.geteduIndivObj();
//		System.out.println(eduObj.getEduComm());
		if (eduObj != null){
			Vector vEduNonForm = eduObj.getEduNonForm();
			System.out.println("B4 editing the non formal Edu list: " );
			if (vEduNonForm != null){
				for (int i=0; i< vEduNonForm.size(); i++){
				String nFE = vEduNonForm.elementAt(i).toString();
				for (int j = 0; j < lstEduNonForm.getModel().getSize(); j++) {
					
					if (lstEduNonForm.getModel().getElementAt(j).equals(nFE)) {
						lstEduNonForm.addSelectionInterval(j, j);
						System.out.println(j + " " + nFE +  " Non formal edu");
					}
				}
			    System.out.println(vEduNonForm.elementAt(i) + " course");
				}
			}
			
			chkEduAttend.setSelected(eduObj.isEduAttend());
			txtEduWhere.setText(eduObj.getEduWhere());
			txtEduStd.setText(eduObj.getEduStd());
			cbEduSchExp.setSelectedItem(eduObj.getEduSchExp());
			chkEduLitClass.setSelected(eduObj.isEduLitClass());
			chkEduCont.setSelected(eduObj.isEduCont());
			txtEduComm.setText(cnsleeHist.geteduIndivObj().getEduComm());
		} else System.out.println("No information to pass to the Intake form Education tab");
		
		
//		vCnslRelnObj = modelFamHist.getRowObjects();
///		cnslHist.setvCnslRelnObj(vCnslRelnObj);
// start ND added 19th Apr 16
		Vector <CounseleeRelativeIndivObj> vCnslRelnObj = cnsleeHist.getvCnslRelnObj();
		modelFamHist.setRelTableValues(vCnslRelnObj);
//		for (int i = 0; i < vCnslRelnObj.size(); i++){
//			CounseleeRelativeIndivObj RelnObj = new CounseleeRelativeIndivObj();
//			RelnObj = vCnslRelnObj.elementAt(i);
//			modelFamHist.addRowObject(RelnObj);
//		}
// end ND added 19th Apr 16
		
		FamilyEnvIndivObj fEnvObj = cnsleeHist.getfamEnvIndivObj();
		if (fEnvObj != null) { 
			cbEnvGetsAlong.setSelectedItem(fEnvObj.getFamEnvGetsAlong());
			cbEnvVerAb.setSelectedItem(fEnvObj.getFamEnvVerAb());
			cbEnvPhyAb.setSelectedItem(fEnvObj.getFamEnvPhyAb());
			cbEnvSexAb.setSelectedItem(fEnvObj.getFamEnvSexAb());
			cbEnvSubAb.setSelectedItem(fEnvObj.getFamEnvSubAb());
			cbEnvNeg.setSelectedItem(fEnvObj.getFamEnvNeg());
			cbEnvPros.setSelectedItem(fEnvObj.getFamEnvPros());
			txtEnvComm.setText(fEnvObj.getFamEnvComm());		
		} 
		else
		System.out.println("Family Environ obj is null in setEditValues");
		
		PhysicalHistIndivObj phyObj = cnsleeHist.getphyIndivObj();
		if (phyObj != null) {
			cbPhyHealthDescYou.setSelectedItem(phyObj.getPhyHistCurrYou());
			cbPhyHealthDesc.setSelectedItem(phyObj.getPhyHistCurr());			// Counselee's input
			txtPhyHealthHistory.setText(phyObj.getPhysicalHistSerious());			
			chkPhyHeaLearningDisab.setSelected(phyObj.isLearnDisable());
			chkPhyHeaADHD.setSelected(phyObj.isADHDisorder());
			chkPhyHeaADD.setSelected(phyObj.isADDisorder());
			chkPhyHeaAutism.setSelected(phyObj.isAutism());
			chkPhyHeaOther.setSelected(phyObj.isOtherDisorder());
			chkPhyHeaBeentoCnsl.setSelected(phyObj.isBeenCounseled());
			chkPhyHeaPsychMed.setSelected(phyObj.isPsychMedsCurr());
			Vector vPhyAddict = phyObj.getPhyHistAddict();
//			if (vPhyAddict != null){
//			for (int i = 0; i < vPhyAddict.size(); i++) {
//			lstPhyHealthAddict.setSelectedValue(vPhyAddict.elementAt(i), true);
//			}
//			}
			if (phyObj.getPhyHistAddict() != null) {
				lstAddict.setText(phyObj.getPhyHistAddict().toString());
			}

			cbPhyHealthWithdrawSympt.setSelectedItem(phyObj.getPhyHistWithdrawSympt());
			chkPhyHeaBirthCtrl.setSelected(phyObj.isPhyHistBirthCtrl());
			chkPhyHeaPreg.setSelected(phyObj.isPhyHistPreg());
			chkPhyHeaAbort.setSelected(phyObj.isPhyHistAbort());
			chkPhyHeaSTI.setSelected(phyObj.isPhyHistSTI());
			chkPhyHeaSTITreat.setSelected(phyObj.isPhyHistSTITreat());
			txtPhyHealthComments.setText(phyObj.getPhyHistComments());
		} 
		else System.out.println("Physical History obj is null in setEditValues");

		MentalStatusIndivObj menObj = cnsleeHist.getmenStatIndivObj();
		if (menObj != null) {
			cbAppearHyg.setSelectedItem(menObj.getMentalStAppearHyg());
			cbMovement.setSelectedItem(menObj.getMentalStMovement());
			cbAffect.setSelectedItem(menObj.getMentalStAffect());
			cbMood.setSelectedItem(menObj.getMentalStMood());
			cbSpeech.setSelectedItem(menObj.getMentalStSpeech());
			cbAttitude.setSelectedItem(menObj.getMentalStAttitude());
			cbImpulse.setSelectedItem(menObj.getMentalStImpulse());
			cbUnderstanding.setSelectedItem(menObj.getMentalStUnderstanding());
			cbMemory.setSelectedItem(menObj.getMentalStMemory());
			cbAttention.setSelectedItem(menObj.getMentalStAttention());
			cbSuicidal.setSelectedItem(menObj.getMentalStSuicidal());
			cbHomicidal.setSelectedItem(menObj.getMentalStHomicidal());
			cbThoughtProc.setSelectedItem(menObj.getMentalStThoughtProc());
			if (menObj.getMentalStHallucination() != null) {
				lstHallucination.setText(menObj.getMentalStHallucination().toString());
			}
			if (menObj.getMentalStOrientation() != null) {
				lstOrientation.setText(menObj.getMentalStOrientation().toString());
			}
			cbIntellect.setSelectedItem(menObj.getMentalStIntellect());
		} 
		else System.out.println("Mental Status obj is null in setEditValues");

		SocialHistIndivObj socObj = cnsleeHist.getsocIndivObj();
		if (socObj != null){
			txtSocLivedAt.setText(socObj.getSocLivedAt());
			cbSocRelWithComm.setSelectedItem(socObj.getSocRelWithComm());
			System.out.println(socObj.getSocReligionBelief()  + " is her religion");
			cbSocReligionBelief.setSelectedItem(socObj.getSocReligionBelief());
			txtSocHerFriends.setText(socObj.getSocHerFriends());
			cbSocRelWithFriends.setSelectedItem(socObj.getSocRelWithFriends());
			cbSocInvolvement.setSelectedItem(socObj.getSocInvolvement());

//			Vector vEduNonForm = eduObj.getEduNonForm();
//			System.out.println("B4 editing the non formal Edu list: " );
//			if (vEduNonForm != null){
//				for (int i=0; i< vEduNonForm.size(); i++){
//				String nFE = vEduNonForm.elementAt(i).toString();
//				for (int j = 0; j < lstEduNonForm.getModel().getSize(); j++) {
//					
//					if (lstEduNonForm.getModel().getElementAt(j).equals(nFE)) {
//						lstEduNonForm.addSelectionInterval(j, j);
//						System.out.println(j + " Non formal edu");
//					}
//				}
//			    System.out.println(vEduNonForm.elementAt(i) + " course");
//				}
		
			Vector vSocRecFun = socObj.getSocRecreationandFun();
			System.out.println("B4 editing the Rec Fun list");
			if (vSocRecFun != null) {
				for (int i = 0; i < vSocRecFun.size(); i++){
				String recFun = vSocRecFun.elementAt(i).toString();
				for (int j = 0; j < lstSocRecreationandFun.getModel().getSize(); j++) {
					if (lstSocRecreationandFun.getModel().getElementAt(j).equals(recFun)) {
						lstSocRecreationandFun.addSelectionInterval(j, j);
						System.out.println(j + " " + recFun + " Recreation and Fun");
					}  
					System.out.println( "RecFun List :" +  lstSocRecreationandFun.getModel().getElementAt(j));
				}
				System.out.println(vSocRecFun.elementAt(i) + " rec and fun");
				}
			} else System.out.println("Social: Rec and Fun empty");
			
			txtSocComments.setText(socObj.getSocComments());
		}
		else System.out.println("Social History obj is null in setEditValues");
	
		StrenghtIndivObj streObj = cnsleeHist.getstrengthIndivObj();
		if (streObj != null){
			lstStrengthCopeSkills.setText(streObj.getvStrengthCopeSkills().toString());
			txtStrength.setText(streObj.getStrengthStrengths());
			txtStreGoalFamily.setText(streObj.getStengthFlyGoals());
			txtStreGoalSocial.setText(streObj.getStrengthSocialGoals());
			txtStreGoalVoc.setText(streObj.getStrengthVocGoals());
			txtStreGoalPhyHealth.setText(streObj.getStrengthPhysicHealthGoals());
			txtStreGoalEdu.setText(streObj.getStrengthEduGoals());
			txtStreGoalPsycho.setText(streObj.getStrengthPsychoHealthGoals());
			txtStreGoalLegal.setText(streObj.getStrengthLegalGoals());
			txtStrengthComments.setText(streObj.getStrengthComments());
		} else System.out.println("Indiv Strength obj is null in setEditValues");
		
		SummaryIndivObj summObj = cnsleeHist.getsummIndivObj();
		if (summObj != null) {
		txtPsychoNeedsCl.setText(summObj.getPsychoClinicFormulation());				
		txtPsychoNeedsCounslr.setText(summObj.getPsychoNeedsCnsr());
		txtIntervenPlan.setText(summObj.getPsychoIntervenPlan());					
		} else System.out.println("Summary obj is null in setEditValues");
		
		VocationFinIndivObj vocFinObj = cnsleeHist.getvocIndivObj();
		if (vocFinObj != null) {
			txtVocPriorEmp.setText(vocFinObj.getVocPriorEmp());
			txtVocWork.setText(vocFinObj.getVocWork());
//			Vector vCommLang = new Vector();
//			if (panelCommLang1.getLanguage() != null)
//				vCommLang.add(panelCommLang1.getLanguage());
//			if (panelCommLang2.getLanguage() != null)
//				vCommLang.add(panelCommLang2.getLanguage());
//			if (panelCommLang3.getLanguage() != null)
//				vCommLang.add(panelCommLang3.getLanguage());
//			if (panelCommLang4.getLanguage() != null)
//				vCommLang.add(panelCommLang4.getLanguage());	
//			vocObj.setvLanguages(vCommLang);
//			
			txtVocFamDebt.setText(vocFinObj.getVocFamDebt());
			txtVocComm.setText(vocFinObj.getVocComm());
			System.out.println(vocFinObj.getVocComm());
		// The following has been commented as the values have been got into the LangFluency object on 26th Mar 16
//			String sLangR, sLangW, sLangS;
//			sLangR = vocFinObj.getVocLangR();
//			sLangW = vocFinObj.getVocLangW();
//			sLangS = vocFinObj.getVocLangS();
//			Hashtable<String, String> htLangAbility = DB.transferLangToHash(sLangR, sLangW, sLangS);
//			System.out.println("R:" + sLangR + " W:" + sLangW + " S:" + sLangS);
			Vector <LangFluency> vlangFluent = new Vector<LangFluency>();
			vlangFluent = vocFinObj.getvLanguages();
			for (int i = 0; i < vlangFluent.size() ; i++) {
				System.out.println(vlangFluent.elementAt(i).getLangName() + " being put into panel for editing");
				switch (i) {
				case 0:
					panelCommLang1.selectLanguageFluency(vlangFluent.elementAt(i));
					break;
				case 1:
					panelCommLang2.selectLanguageFluency(vlangFluent.elementAt(i));
					break;
				case 2:
					panelCommLang3.selectLanguageFluency(vlangFluent.elementAt(i));
					break;
				case 3:
					panelCommLang4.selectLanguageFluency(vlangFluent.elementAt(i));
					break;
				default:
					System.out.println("No more panels to display info");
				}	
				
			}
		} else System.out.println("Vocational Financial obj is null in setEditValues");
		
		LegalHistIndivObj legObj = cnsleeHist.getLegalHistIndivObj();
		if (legObj != null) {
			cbLegHistOffences.setSelectedItem(legObj.getLegalOffences());		
			txtLegalComments.setText(legObj.getLegalComments());
		} else System.out.println("Legal obj is null in setEditValues");
		
	} catch (Exception e){
		JOptionPane.showMessageDialog(this, "Error displaying info!");
		e.printStackTrace();
	}
	}

//	private Hashtable<String, String> transferLangToHash(String sRead, String sWrite, String sSpeak) {
//		// TODO Auto-generated method stub
//		Hashtable <String, String> htLangUse = new Hashtable<String, String>();
//		Vector vRead, vWrite, vSpeak, vLangKnown;
//		String sLangKnown = new String();
//		
//		vRead = DB.stringToVector(sRead);
//		vWrite = DB.stringToVector(sWrite);
//		vSpeak = DB.stringToVector(sSpeak);
//		
//		for (int l=0; l < 3; l++){
//			switch (l) {
//			case 0: vLangKnown = vSpeak;
//					break;
//			case 1: vLangKnown = vRead;
//					break;
//			case 2: vLangKnown = vWrite;
//					break;
//			default: vLangKnown = null;
//			}
//			if(vLangKnown != null) {
//				String sLangAbil = new String();
//				String langToEnter = new String();
//				for (int i = 0; i <= vLangKnown.size(); i++) {
//				String sLangInVector = vLangKnown.elementAt(i).toString();
//				System.out.println(sLangInVector + " Number of langs:" + vLangKnown.size() + " - " + l);
//				switch ( l) {
//				case  0: 
//					langToEnter = "Speak";
//					htLangUse.put(sLangInVector, langToEnter);			// all the languages in the Speak field will be added to the hash table
//					break;															// one after the other : case 0
//				case 1:
//					if (!htLangUse.containsKey(sLangInVector)){
//						langToEnter = "Read";
//						htLangUse.put(sLangInVector, langToEnter);
//					}
//					else {
//						langToEnter = "Speak, Read";
//						htLangUse.put(sLangInVector, langToEnter);
//					}
//					break;
//				case 2:
//					if (!htLangUse.containsKey(sLangInVector)) {
//						htLangUse.put(sLangInVector, "Write");
//					}
//					else {
//						sLangAbil = htLangUse.get(sLangInVector);
//						sLangAbil = sLangAbil + ", Write";
//						htLangUse.put(sLangInVector, sLangAbil);
//					}
//					break;
//				default:
//					System.out.println("No languagess known and none transferred into the hash table");
//					break;
//			}
//		}
//		}
//	}
//	if (htLangUse != null){
//		String sKeyLangs = new String();
//		sKeyLangs = htLangUse.keys().toString();
//		System.out.println("The diff languages known are : " + sKeyLangs);
//		return htLangUse;
//	} else {
//		htLangUse.put("Error", "No Languages used");
//	}
//	return htLangUse;
//	}
}

