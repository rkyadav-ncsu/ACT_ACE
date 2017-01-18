package com.act.server.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;


public class DB{
	
		
	//////////////////////////
	//   TABLES
	//////////////////////////
	//Define all table names here
	public static final String DB_TBL_USER = "userstbl";
	public static final String DB_TBL_GROUPSTBL = "groupstbl";					
	public static final String DB_TBL_USERSANDGROUPS = "usersandgroupstbl";		

	public static final String DB_TBL_INDIV = "tblindiv";
	public static final String DB_TBL_ADDICTION = "addictionstbl";	
    public static final String DB_TBL_LANGUAGE = "languagetbl";		
	public static final String DB_TBL_NONFORMALEDUCATION = "nonformaleducationtbl";	
    public static final String DB_TBL_RELATIONSHIP = "relativetypestbl";  	
	public static final String DB_TBL_RELIGION = "religionstbl";	

	public static final String DB_TBL_COPINGSKILL = "copingskillstbl";
	public static final String DB_TBL_RECREATION = "recreationtbl";		
	
	public static final String DB_TBL_ORGANISATION = "organisationstbl";
	public static final String DB_TBL_CAREHOME = "carehomestbl";	
	
	public static final String DB_TBL_ABUSEHIST = "tblindivabusehist";
	public static final String DB_TBL_EDUHIST = "tblindiveducationHist";
	public static final String DB_TBL_FAMENV = "tblindivfamilyenvironment";		
	public static final String DB_TBL_CNSLRELN = "tblindivfamilyHist";			
	public static final String DB_TBL_PHYSICALHIST = "tblindivphysicalHist";	
	public static final String DB_TBL_MENTALSTAT = "tblindivpsychologicalhist";		
	public static final String DB_TBL_SOCIALHIST = "tblindivsocialhist";		
	public static final String DB_TBL_CNSLSTRENGTH = "tblindivstrengths";		
	public static final String DB_TBL_CNSLSUMM = "tblindivsummary";					
	public static final String DB_TBL_VOCHIST = "tblindivvocationalhist";			
	public static final String DB_TBL_LEGAL = "tblindivLegalhist";				
	public static final String DB_TBL_CHK_LST_TSC40 = "tsc40assessment";
	public static final String DB_TBL_CHK_LST_TSC54 = "tsc54assessment";
	
	public static final String DB_TBL_COUNSELLING_SESSION = "counsellingsession";
	public static final String DB_TBL_COUNSELINGTHERAPY = "counselingtherapy";		

	//TF-CBT
	public static final String DB_TBL_TFCBT_STAGE_MASTER = "tfcbtstagemaster";
	public static final String DB_TBL_TFCBT_TASK_MASTER = "tfcbttaskmaster";
	public static final String DB_TBL_TFCBT_CNSLEE_TASK = "counselleetfcbttask";

	//////////////////////////
	//   COLUMNS
	//////////////////////////
	//Define all column names here
	
	//User table columns
//	public static final String DB_COL_USER_USERNAME = "UserName";
//	public static final String DB_COL_USER_USERID = "UserId";
//	
	public static final String DB_COL_USER_PWD = "UserPassword";
	public static final String DB_COL_USER_ID = "User_ID";
	public static final String DB_COL_USER_FNAME = "UserFName";
	public static final String DB_COL_USER_MNAME = "UserMName";
	public static final String DB_COL_USER_LNAME = "UserLName";
	public static final String DB_COL_USER_GENDER = "UserGender";
	public static final String DB_COL_USER_DOB = "UserDOB";
	public static final String DB_COL_USER_ADDRESS = "UserAddress";
	public static final String DB_COL_USER_PIN = "UserPin";
	public static final String DB_COL_USER_MOB = "UserMobile";
	public static final String DB_COL_USER_PHONE = "UserPhone";
	public static final String DB_COL_USER_DESIG = "UserDesignation";
	public static final String DB_COL_USER_EMAIL = "UserEmail";

	// UsersandGroupstbl table columns
	public static final String DB_COL_USER_GRP_USRID = "User_ID";
	public static final String DB_COL_USER_GRP_GRPNAME = "GroupName";

	// groupstbl table columns
	public static final String DB_COL_GROUP_NAME = "GroupName";
	public static final String DB_COL_GROUP_DESC = "GroupDesc";

	//Indiv table columns
	public static final String DB_COL_INDIV_ID= "Indiv_Id";
	public static final String DB_COL_INDIV_NAME= "Indiv_Name";
	public static final String DB_COL_INDIV_CNSLR_NAME= "CounsellorName";
	public static final String DB_COL_INDIV_NAME_CHANGED= "NameChanged";
	public static final String DB_COL_INDIV_PARTNER_ORGN= "PartnerOrgn";
	public static final String DB_COL_INDIV_OTHER_NAME= "OName";
	public static final String DB_COL_INDIV_DATE_ASSESSMENT= "DtAssessment";
	public static final String DB_COL_INDIV_AGE  = "Age";						// ND added 2nd Apt 16
	public static final String DB_COL_INDIV_HOME = "HomeLocation"; 				// ND added 2nd Apr 16
	public static final String DB_COL_INDIV_DOB = "DOB";						// ND edited 2nd Apr 16
	public static final String DB_COL_INDIV_DTTERM = "DtTermination";			// ND added 2nd Apr 16
	public static final String DB_COL_INDIV_GENDER = "Gender";					// ND added 2nd Apr 16

	//counselingtherapy table columns
	public static final String DB_COL_COUNSELINGTHERAPY_ID		= "CounselTherapy_ID";
	public static final String DB_COL_COUNSELINGTHERAPYDESC 	= "TherapyDesc";

	// Addictions table columns
	public static final String DB_COL_ADDICTION_ID = "Addiction_ID";
	public static final String DB_COL_ADDICTION = "Addiction";

	// CopingSkillstbl table columns			ND added on 24th Sep
	public static final String DB_COL_COPINGSKILL_ID = "CopingSkills_ID";
	public static final String DB_COL_COPINGSKILL = "CopingSkills";
	
	// Languagetbl table columns				ND added on 9th Sept
	public static final String DB_COL_LANGUAGE_ID = "LanguageID";
	public static final String DB_COL_LANGUAGE = "Language";
	
	// Nonformaleduationtbl table columns		ND added on 10th Sept
	public static final String DB_COL_NONFORMALEDU_ID = "NonFormalEdu_ID";
	public static final String DB_COL_NONFORMALEDUCATION = "NonFormalEducation";
	
	// Relativetypestbl table columns			ND added on 10th Sept
	public static final String DB_COL_RELATIVETYPE_ID = "RelativeTypesID";
	public static final String DB_COL_RELATIVETYPE = "RelativeTypes";
	
	// Religionstbl table columns				ND added on 9th Sept
	public static final String DB_COL_RELIGION_ID = "ReligionID";
	public static final String DB_COL_RELIGION = "Religion";
	
	// Recreationtbl table columns				ND added on 12th Oct
	public static final String DB_COL_RECREATION_ID = "Recreation_ID";
	public static final String DB_COL_RECREATIONACTIVITY = "RecreationActivity";  	
	
	public static final String DB_TBL_SERVICETYPES = "servicetypestbl";	// ND added on 4th Aug 16;
	public static final String DB_COL_SERVICETYPES_ID = "ServiceTypes_ID";
	public static final String DB_COL_SERVICETYPES = "ServiceTypesName";

	public static final String DB_TBL_PINCODE = "pincodestbl";				// ND added on 25th Aug 16
	
	// Pincodestbl								ND added on 25th Aug 16
	public static final String DB_COL_PO = "PostOffice";
	public static final String DB_COL_PINCODE = "Pincode";
	public static final String DB_COL_CITYDIST = "CityDistrict";
	public static final String DB_COL_SATE = "State";

	// Organisationstbl table columns
	public static final String DB_COL_ORGN_ID = "Orgn_ID";
	public static final String DB_COL_ORGN_NAME = "Orgn_Name";
	public static final String DB_COL_ORGN_TYPE = "Orgn_Type";
	public static final String DB_COL_ORGN_FRAMEWORK = "Orgn_Framework";
	public static final String DB_COL_ORGN_LANG_ID = "Languages";
	public static final String DB_COL_ORGN_MAINORG_ID = "Orgn_Main";
	public static final String DB_COL_ORGN_ADD1 = "Orgn_Add1";
	public static final String DB_COL_ORGN_ADD2 = "Orgn_Add2";
	public static final String DB_COL_ORGN_ADD3 = "Orgn_Add3";
	public static final String DB_COL_ORGN_CITY = "Orgn_City";
	public static final String DB_COL_ORGN_PIN = "Orgn_Pin";
	public static final String DB_COL_ORGN_PHONE = "Orgn_Phone";
	public static final String DB_COL_ORGN_MOBILE = "Orgn_Mobile";
	public static final String DB_COL_ORGN_WEBSITE = "Orgn_Website";
	public static final String DB_COL_ORGN_EMAIL = "Orgn_Email";
	public static final String DB_COL_ORGN_DENOMINATION = "Denomination";
	public static final String DB_COL_ORGN_DETAILS = "Orgn_Details";
	public static final String DB_COL_ORGN_SERVICETYPES = "Service_types";
	public static final String DB_COL_ORGN_SERVDETAILS = "Orgn_ServDetails";
	
	// 	CareHomestbl table columns
	public static final String DB_COL_CAREHOME_ID = "CareHome_ID";		// Long Integer	4
	public static final String DB_COL_CAREHOME_NAME = "CareHome_Name";	//	Text		50
	public static final String DB_COL_CAREHOME_DESC = "CareHome_Desc";	//	Memo	-
	public static final String DB_COL_CAREHOME_LOCATION = "CareHome_Location";	//	Text		50
	public static final String DB_COL_CAREHOME_MAINORG = "CareHome_MainOrg";	//Long Integer	4
	public static final String DB_COL_CAREHOME_TYPE = "Type";			//Text		255

	
	public static final String DB_COL_ABUSEHIST_ABUSEHIST_ID = "AbuseHist_ID";		//	Long Integer	4	Not being used
	public static final String DB_COL_ABUSEHIST_INDIV_ID = "Indiv_ID";				//	Long Integer	4
	public static final String DB_COL_ABUSEHIST_VERBAL = "Verbal";					//	Memo	-
	public static final String DB_COL_ABUSEHIST_PHYSICAL = "Physical";				//	Memo	-
	public static final String DB_COL_ABUSEHIST_SEXUAL = "SexuaL";					//	Memo	-
	public static final String DB_COL_ABUSEHIST_NEGLECT = "Neglect";				//	Memo	-
	public static final String DB_COL_ABUSEHIST_ABUSECOMMENTS = "AbuseComment";	//	Memo	-

	// tblIndivEducationHist table columns								ND added on 1st Nov
	public static final String DB_COL_EDUHIST_INDIV_ID = "indivID";					//	Long Integer	4
	public static final String DB_COL_EDUHIST_ATTENDEDSCHOOL = "AttendedSchool";	//	Yes/No	1
	public static final String DB_COL_EDUHIST_WHEREED = "WhereEd";					//	Text	50
	public static final String DB_COL_EDUHIST_HIGHSTD = "HighStd";					//	Text	15
	public static final String DB_COL_EDUHIST_LITERACYCLASSES = "LiteracyClasses";	//	Yes/No	1
	public static final String DB_COL_EDUHIST_SCHOOLEXPERIENCE = "SchoolExperience";	//	Text	10
	public static final String DB_COL_EDUHIST_CONTINUESTUDIES = "ContinueStudies";	//	Yes/No	1
	public static final String DB_COL_EDUHIST_NON_FORMALED = "Non_FormalEd";		// 	Long Integer	4
	public static final String DB_COL_EDUHIST_EDCOMMENTS = "EdComments";			//	Memo	-
	
		
	public static final String DB_COL_FAMENV_INDIV_ID = "Indiv_ID";
	public static final String DB_COL_FAMENV_GETSALONG = "GetsAlong";		//	Text 	15
	public static final String DB_COL_FAMENV_PHYABUSE = "PhysicalAbuse";	//	Text	15
	public static final String DB_COL_FAMENV_VERBALABUSE = "VerbalAbuse";	//	Text	15
	public static final String DB_COL_FAMENV_SEXUALABUSE = "SexualAbuse";	//	Text	15
	public static final String DB_COL_FAMENV_SUBSTANCEABUSE = "SubstanceAbuse";		// 	Text	15
	public static final String DB_COL_FAMENV_NEGLECT = "Neglect";			//	Text	15
	public static final String DB_COL_FAMENV_PROSTITUTION = "Prostitution";	//	Text	15
	public static final String DB_COL_FAMENV_FAMCOMMENTS = "FamComments";	//	Memo	-
	//	public static final String DB_COL_FAMENV_FAMENTRYDT = "FamEntry_dt"	;	//	Date/Time	8 	Not being used
	
	// tblIndivFamilyHist table columns 								ND added on 29th Oct
	// public static final String DB_COL_CNSLRELN_RELN_ID = "IndivReln_ID"; 		//	Long Integer	4	Not being used
	public static final String DB_COL_CNSLRELN_INDIV_ID = "Indiv_ID";			//	Long Integer	4
	public static final String DB_COL_CNSLRELN_RELNNAME = "RelnName";			//	Text	30
	public static final String DB_COL_CNSLRELN_RELNSTRENGTH = "RelnStrength";	//	Text	5
	public static final String DB_COL_CNSLRELN_RELNAGE = "RelnAge";				//	Byte	1
	public static final String DB_COL_CNSLRELN_RELNAWARE = "RelnAware";			//	Yes/No	1
	// public static final String DB_COL_CNSLRELN_RELNENTRY_DT = "RelnEntry_Dt";	//	Date/Time	8	Not being used
	public static final String DB_COL_CNSLRELN_RELNCOMMENTS = "RelnComments";	//	Memo	-
	public static final String DB_COL_CNSLRELN_RELATIONSHIP = "Relationship";	//	Long Integer	4
	public static final String DB_COL_CNSLRELN_RELNPROFESSION = "RelnProfession";	//	Text	30

	//tblIndivPhysicalHist	table columns								ND added on 29th Oct
	// public static final String DB_COL_PHYSICALHIST_PHYSHIST_ID = "PhysicHist_ID";			//	Long Integer	4  	Not being used
	public static final String DB_COL_PHYSICALHIST_INDIV_ID = "Indiv_ID";					//	Long Integer	4
	public static final String DB_COL_PHYSICALHIST_CURRHEALTH_CNSR = "CurrHealth_Counselor";	//	Text	10
	//public static final String DB_COL_PHYSICALHIST_CURRHEALTHSPECS_CNSR = "CurrHealthSpecs_Counselor";	//	Memo	- can be entered in the Comments
	public static final String DB_COL_PHYSICALHIST_CURRHEALTH_CNSL = "CurrHealth_Girl";		//	Text	10
	//public static final String DB_COL_PHYSICALHIST_CURRHEALTHSPECS_CNSL = "CurrHealthSpecs_Girl";		//	Memo	-  can be entered in the Comments
	public static final String DB_COL_PHYSICALHIST_SERIOUSILLINJDISABLE = "Seriousillinjdisable";		//	Memo	-
	public static final String DB_COL_PHYSICALHIST_ADDICTIONS = "Addictions";				// 	Long Integer	4
	public static final String DB_COL_PHYSICALHIST_BIRTHCONTROLHIST = "BirthControlHist";	//	Text	120
	public static final String DB_COL_PHYSICALHIST_PREGNANCYHIST = "PregnancyHist";			//	Text	120
	public static final String DB_COL_PHYSICALHIST_ABORTIONHIST = "AbortionHist";			//	Text	120
	public static final String DB_COL_PHYSICALHIST_STTHIST = "STIHist";						//	Text	120
	public static final String DB_COL_PHYSICALHIST_STITREATMENT = "STITreatment";			//	Text	120
	// public static final String DB_COL_PHYSICALHIST_PHYSICENTRY_DT = "PhysicEntry_Dt";		//	Date/Time	8		Not being used
	public static final String DB_COL_PHYSICALHIST_WITHDRAWAL = "WithdrawalSymptoms";		// VARCHAR	256
	public static final String DB_COL_PHYSICALHIST_LEARNDISABLE = "LearnDisable";
	public static final String DB_COL_PHYSICALHIST_ADHDISORDER = "ADHDisorder";
	public static final String DB_COL_PHYSICALHIST_ADDISORDER = "ADDisorder";
	public static final String DB_COL_PHYSICALHIST_AUTISM = "Autism";
	public static final String DB_COL_PHYSICALHIST_OTHERDISORDER = "OtherDisorder";
	public static final String DB_COL_PHYSICALHIST_BEENCOUNSELED = "BeenCounseled";
	public static final String DB_COL_PHYSICALHIST_PSYCHMEDSCURR = "PsychMedsCurr";
	public static final String DB_COL_PHYSICALHIST_PHYSICCOMMENTS = "PhysicComments";		//	Memo	-
	//public static final String DB_COL_PHYSICALHIST_WITHDRAWSYMPT = "WithdrawSympt";				// ND edited on 5th Dec - duplicate field


	// tblIndivPsychologicalHist table columns							ND added on 29th Oct
	// public static final String DB_COL_MENTALSTAT_MENTALSTATk_ID = "PsychoHist_ID";			//	Long Integer	4	Not being used
	public static final String DB_COL_MENTALSTAT_INDIV_ID = "Indiv_ID";						//	Long Integer	4
	public static final String DB_COL_MENTALSTAT_APPHYGIENE = "AppHygiene";					//	Text	15
	public static final String DB_COL_MENTALSTAT_MOVEMENT = "Movement";						// 	Long Integer	4
	public static final String DB_COL_MENTALSTAT_AFFECT = "Affect";							//	Text	15
	public static final String DB_COL_MENTALSTAT_MOOD = "Mood";								//	Text	15
	public static final String DB_COL_MENTALSTAT_SPEECH = "Speech";							//	Text	10
	public static final String DB_COL_MENTALSTAT_ATTITUDE = "Attitude";						//	Text	10
	public static final String DB_COL_MENTALSTAT_INTELLECTUALLEVEL = "IntellectualLevel";	//	Text	10
	public static final String DB_COL_MENTALSTAT_IMPULSECONTROL = "ImpulseControl";			//	Text	10
	public static final String DB_COL_MENTALSTAT_UNDERSTANDING = "Understanding";			//	Text	10
	public static final String DB_COL_MENTALSTAT_ORIENTATION = "Orientation";				//	Long Integer	4
	public static final String DB_COL_MENTALSTAT_MEMORY = "Memory";							//	Long Integer	4
	public static final String DB_COL_MENTALSTAT_ATTENTION = "Attention";					//	Text	15
	public static final String DB_COL_MENTALSTAT_SUICIDALIDEATION = "SuicidalIdeation";		//	Text	10
	public static final String DB_COL_MENTALSTAT_HOMICIDALIDEATION = "HomicidalIdeation";	//	Text	10
	public static final String DB_COL_MENTALSTAT_THOUGHTPROCESS = "ThoughtProcess";			//	Text	15
	public static final String DB_COL_MENTALSTAT_HALLUCINATION = "Hallucination";			//	Long Integer	4
	// public static final String DB_COL_MENTALSTAT_PSYCHOENTRY_DT = "PsychoEntry_Dt";			//	Date/Time	8		Not being used		
	public static final String DB_COL_MENTALSTAT_PSYCHOCOMMENTS = "PsychoComments";			//	Memo	-

	//tblIndivSocialHist table columns								ND added on 29th Oct
	// public static final String DB_COL_SOCHIST_SOCHIST_ID = "SocHist_ID";					//	Long Integer	4		Not being used
	public static final String DB_COL_SOCHIST_INDIV_ID = "Indiv_ID";						//	Long Integer	4
	public static final String DB_COL_SOCHIST_LIVEDAT = "LivedAt";							//	Text	30
	public static final String DB_COL_SOCHIST_RELWITHCOMM = "RelWithComm";					//	Text	255
	public static final String DB_COL_SOCHIST_HERFRIENDS = "HerFriends";					//	Text	90
	public static final String DB_COL_SOCHIST_RELWITHFRIENDS = "RelWithFriends";			//	Text	255
	public static final String DB_COL_SOCHIST_INVOLVEMENT = "Involvement";					//	Text	255
	public static final String DB_COL_SOCHIST_SOCCOMMENTS = "SocComments";					//	Memo	-
	public static final String DB_COL_SOCHIST_RECREATIONANDFUN = "RecreationandFun";		//	Long Integer	4
	public static final String DB_COL_SOCHIST_RELIGIONBELIEF = "ReligionBelief";			//	Long Integer	4
	// public static final String DB_COL_SOCHIST_SOCENTRY_DT = "SocEntry_Dt";					//	Date/Time	8			Not being used

	// tblIndivStrengths table columns
	// public static final String DB_COL_CNSLSTRENGTH_STRENGTHS_ID = "Strengths_ID";			//	Long Integer	4		Not being used
	public static final String DB_COL_CNSLSTRENGTH_INDIV_ID = "Indiv_ID";					//	Long Integer	4
	public static final String DB_COL_CNSLSTRENGTH_COPINGSKILL = "CopingSkills";			// 	Long Integer	4
	public static final String DB_COL_CNSLSTRENGTH_STRENGTHS = "Strengths";					//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_FLYGOALS = "FlyGoals";					//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_EDUGOALS = "EduGoals";					//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_SOCIALGOALS = "SocialGoals";				//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_PSYCHOHEALTHGOALS = "PsychoHealthGoals";	//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_VOCATIONALGOALS = "VocationalGoals";		//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_LEGALGOALS = "LegalGoals";				//	Memo	-
	public static final String DB_COL_CNSLSTRENGTH_PHYSICHEALTHGOALS = "PhysicHealthGoals";	//	Memo	-
	// public static final String DB_COL_CNSLSTRENGTH_STRENGTHSENTRY_DT = "StrengthsEntry_Dt";	//	Date/Time	8			Not being used
	public static final String DB_COL_CNSLSTRENGTH_STRENGTHCOMMENTS = "StrengthComments";	//	Memo	-

	
	// tblIndivSummary table columns
	// public static final String DB_COL_CNSLSUMM_SUMMARY_ID = "Summary_ID";					//	Long Integer	4		Not being used
	public static final String DB_COL_CNSLSUMM_INDIV_ID = "Indiv_ID";						//	Long Integer	4
	public static final String DB_COL_CNSLSUMM_SUMMCLINICFORM = "SummClinicFormulation";	//	Memo	-
	public static final String DB_COL_CNSLSUMM_INTERVENTIONPLAN = "InterventionPlan";			//	Memo	-
	public static final String DB_COL_CNSLSUMM_PSYCHONEEDSCNSR = "PsychoNeedsCounselor";	//	Memo	-
	public static final String DB_COL_CNSLSUMM_SUMMCOMMENTS = "SummComments";				//	Memo	-
	// public static final String DB_COL_CNSLSUMM_SUMMENTRY_DT = "SummEntry_Dt";				//	Date/Time	8			Not being used
	
	// tblIndivVocationalHist table columns
	// public static final String DB_COL_VOCHIST_VOCHIST_ID = "VocHist_ID";					//	Long Integer	4			Not being used
	public static final String DB_COL_VOCHIST_INDIV_ID = "Indiv_ID";						//	Long Integer	4
	public static final String DB_COL_VOCHIST_EMPSPECS = "EmpSpecification";				//	Text	120
	public static final String DB_COL_VOCHIST_FAMDEBTSPECS = "FamilyDebtsSpecification";	//	Text	120
	public static final String DB_COL_VOCHIST_WORKSPECS = "WorkInterestSpecification";		//	Text	120
	public static final String DB_COL_VOCHIST_LANGCOMM = "LangFluent";						// 	Long Integer	4
	public static final String DB_COL_VOCHIST_LANGREAD = "LangRead";						// 	Long Integer	4
	public static final String DB_COL_VOCHIST_LANGWRITE = "LangWrite";						// 	Long Integer	4
	public static final String DB_COL_VOCHIST_LANGSPEAK = "LangSpeak";						//	Long Integer	4
	// public static final String DB_COL_VOCHIST_VOCENTRY_DT = "VocEntry_Dt";					//	Date/Time	8			Not being used
	public static final String DB_COL_VOCHIST_VOCCOMMENTS = "VocComments";					//	Memo	-

	// tblIndivLegal table columns											ND added on 18th Dec.
	// public static final String DB_COL_LEGAL_LEGAL_ID = "Legal_ID";							// INT(11)							Not being used
	public static final String DB_COL_LEGAL_INDIV_ID = "Indiv_ID";							// VARCHAR(15)
	public static final String DB_COL_LEGAL_OFFENCES = "LegalOffences";						// VARCHAR(30)
	public static final String DB_COL_LEGAL_LEGALCOMMENTS = "LegalComments";				// MEDIUMTEXT
	
	//TSC54 Table columns
	public static final String DB_COL_TSC54_ID  = "TSC54ChkLstId";
	public static final String DB_COL_TSC54_CASE_ID = "CaseId";
	public static final String DB_COL_TSC54_DATE 		= "AssessmentDate";
	public static final String DB_COL_TSC54_DESC 		= "AssessmentDesc";
	public static final String DB_COL_TSC54_CNSLR_ID 	= "CounsellorId";
	public static final String DB_COL_TSC54_TOTAL_SCORE = "TotalScore";
	public static final String DB_COL_TSC54_BAD_DREAMS = "BadDreams";
	public static final String DB_COL_TSC54_FEEL_AFRAID_OF_BAD = "AfraidBadMightHappen"; 
	public static final String DB_COL_TSC54_SCARY_IDEAS = "ScaryIdeas"; 
	public static final String DB_COL_TSC54_DIRTY_WORDS = "WantToSayDirtyWords";
	public static final String DB_COL_TSC54_PRETEND = "PretendSomeoneElse"; 
	public static final String DB_COL_TSC54_ARGUE = "Argue"; 
	public static final String DB_COL_TSC54_FEEL_LONELY = "FeelLonely"; 
	public static final String DB_COL_TSC54_TOUCH_PRIV_PARTS = "TouchPrivParts"; 
	public static final String DB_COL_TSC54_FEEL_SAD = "FeelSad";
	public static final String DB_COL_TSC54_REM_PAST_THINGS = "RemPastThings"; 
	public static final String DB_COL_TSC54_GOING_AWAY = "GoingAwayInMind"; 
	public static final String DB_COL_TSFC54_REM_SCARY_THINGS = "RemScaryThings"; 
	public static final String DB_COL_TSC54_YELL = "Yell"; 
	public static final String DB_COL_TSC54_CRYING = "Crying";
	public static final String DB_COL_TSC54_SUDDEN_FEAR = "SuddenFear"; 
	public static final String DB_COL_TSC54_GETTING_MAD = "GettingMad"; 
	public static final String DB_COL_TSC54_THINK_ABT_SX = "ThinkAbtSx"; 
	public static final String DB_COL_TSC54_FEEL_DIZZY = "FeelDizzy"; 
	public static final String DB_COL_TSC54_YELL_OTHERS = "YellOthers";
	public static final String DB_COL_TSC54_HURT_SELF = "HurtSelf"; 
	public static final String DB_COL_TSC54_HURT_OTHERS = "HurtOthers"; 
	public static final String DB_COL_TSC54_TOUCH_OTHERS_PRIV_PARTS = "TouchOtherPrivParts"; 
	public static final String DB_COL_TSC54_THINK_SX = "ThinkSx"; 
	public static final String DB_COL_TSC54_FEAR_MEN = "FearMen";
	public static final String DB_COL_TSC54_FEAR_WOMEN = "FearWomen"; 
	public static final String DB_COL_TSC54_WASH = "Wash"; 
	public static final String DB_COL_TSC54_FEEL_sTUPID = "FeelStupid"; 
	public static final String DB_COL_TSC54_FEEL_GUILT = "FeelGuilt"; 
	public static final String DB_COL_TSC54_FEEL_UNREAL = "FeelUnreal"; 
	public static final String DB_COL_TSC54_FORGET_THINGS = "ForgetThings";
	public static final String DB_COL_TSC54_FEEL_NOT_IN_BODY = "FeelNotInBody"; 
	public static final String DB_COL_TSC54_FEEL_NERVOUS = "FeelNervous"; 
	public static final String DB_COL_TSC54_FEEL_AFRAID = "FeelAfraid"; 
	public static final String DB_COL_TSC54_NOT_TRUST_PEOPLE = "NotTrustPeople"; 
	public static final String DB_COL_TSC54_THINK_BAD_PAST = "ThinkBadPast";
	public static final String DB_COL_TSC54_FIGHTS = "Fights"; 
	public static final String DB_COL_TSC54_FEEL_MEAN = "FeelMean"; 
	public static final String DB_COL_TSC54_PRETEND_SOMWHERE_ELSE = "PretendSomewhereElse"; 
	public static final String DB_COL_TSC54_FEAR_DARK = "FearDark"; 
	public static final String DB_COL_TSC54_UPSET_ABT_SX = "UpsetAbtSx";
	public static final String DB_COL_TSC54_WORRY = "Worry"; 
	public static final String DB_COL_TSC54_FEEL_NOONE_LIKES_ME = "FeelNooneLikesMe"; 
	public static final String DB_COL_TSC54_REM_THINGS = "RemThings"; 
	public static final String DB_COL_TSC54_FEEL_SX = "FeelSx"; 
	public static final String DB_COL_TSC54_MIND_EMPTY = "MindEmpty"; 
	public static final String DB_COL_TSC54_FEEL_HATE = "FeelHate"; 
	public static final String DB_COL_TSC54_CANT_STOP_THINK_ABT_SX = "CantStopThinkAbtSx"; 
	public static final String DB_COL_TSC54_TRY_NO_FEELINGS = "TryNoFeelings"; 
	public static final String DB_COL_TSC54_FEEL_MAD = "FeelMad"; 
	public static final String DB_COL_TSC54_FEEL_KILL = "FeelKill"; 
	public static final String DB_COL_TSC54_WISH_BAD_DIN_HAPPEN = "WishBadDinHappen"; 
	public static final String DB_COL_TSC54_WANT_TO_KILL_SELF = "WantToKillSelf"; 
	public static final String DB_COL_TSC54_DAY_DREAM = "DayDream"; 
	public static final String DB_COL_TSC54_UPSET_TALK_ABT_SX = "UpsetTalkAbtSx";
	
	
	//TSC40 Table columns
	public static final String DB_COL_TSC40_ID  		= "tsc40assessmentId";
	public static final String DB_COL_TSC40_CASE_ID 	= "CaseId";
	public static final String DB_COL_TSC40_DATE 		= "AssessmentDate";
	public static final String DB_COL_TSC40_DESC 		= "AssessmentDesc";
	public static final String DB_COL_TSC40_CNSLR_ID 	= "CounsellorId";
	public static final String DB_COL_TSC40_TOTAL_SCORE = "TotalScore";
	public static final String DB_COL_TSC40_HEAD_ACHE = "HeadAche";
	public static final String DB_COL_TSC40_INSOMNIA = "Insomnia";
	public static final String DB_COL_TSC40_WGHT_LOSS = "WghtLoss";
	public static final String DB_COL_TSC40_STOMACH_PROB = "StomachProb";
	public static final String DB_COL_TSC40_SX_PROB = "SexProb";
	public static final String DB_COL_TSC40_ISOLATION = "Isolation"; 	
	public static final String DB_COL_TSC40_FLASH_BACK = "FlashBack";
	public static final String DB_COL_TSC40_RESTLESS_SLEEP = "RestlssSleep";
	public static final String DB_COL_TSC40_LOW_SX_dRIVE = "LowSxDrive";
	public static final String DB_COL_TSC40_LONELINESS = "Loneliness";
	public static final String DB_COL_TSC40_NIGHTMARES = "Nightmares";
	public static final String DB_COL_TSC40_SPACE_OUT = "SpaceOut";	
	public static final String DB_COL_TSC40_ANX_ATTACK = "AnxAttck";
	public static final String DB_COL_TSC40_SADNESS = "Sadness";
	public static final String DB_COL_TSC40_DIZZINESS = "Dizziness";
	public static final String DB_COL_TSC40_DISSAT_SX_DRIVE = "DissatSxDrive";
	public static final String DB_COL_TSC40_CTRL_TEMPER = "CtrlTemper";
	public static final String DB_COL_TSC40_WAKE_EARLY = "WakeEarly";	
	public static final String DB_COL_TSC40_SX_OVERACT = "SxOveract";
	public static final String DB_COL_TSC40_UNCONTROL_cRY = "UncontrolCry";
	public static final String DB_COL_TSC40_FEAR_MEN = "FearMen";
	public static final String DB_COL_TSC40_NO_REST_MORN = "NotRestMorn";
	public static final String DB_COL_TSC40_SX_NO_ENJOY = "SxNoEnjoy";
	public static final String DB_COL_TSC40_TRBL_GETALONG = "TrblGetAlong";	
	public static final String DB_COL_TSC40_MEM_PROB = "MemProb";
	public static final String DB_COL_TSC40_PHYSIC_HURT = "PhysicHurt";
	public static final String DB_COL_TSC40_FEAR_WOMEN = "FearWomen";
	public static final String DB_COL_TSC40_WAKE_MIDNIGHT = "WakeMidnight";
	public static final String DB_COL_TSC40_BAD_THOUGHT_ABT_SX = "BadThoughtsSx";	
	public static final String DB_COL_TSC40_PASS_OUT = "PassOut";
	public static final String DB_COL_TSC40_UNREAL_FEEL = "UnrealFeel";
	public static final String DB_COL_TSC40_FREQ_WASH = "FreqWash";
	public static final String DB_COL_TSC40_INFERIORITY = "Inferiority";
	public static final String DB_COL_TSC40_TENSION = "Tension";
	public static final String DB_COL_TSC40_SX_CONFUSION = "SxConfusion";	
	public static final String DB_COL_TSC40_HURT_OTHERS = "HurtOthers";
	public static final String DB_COL_TSC40_GUILT = "Guilt";
	public static final String DB_COL_TSC40_FEEL_NOT_IN_BODY = "FeelNotInBody";
	public static final String DB_COL_TSC40_BREATHE_TROUBLE = "BreatheTrouble";
	public static final String DB_COL_TSC40_SX_FEEL_UNTIMELY = "SxFeelingUntimely";
	
	//TFCBT Stage Master table columns
	public static final String DB_COL_TFCBT_STAGE_NUMBER 	= "StageNumber";
	public static final String DB_COL_TFCBT_STAGE_NAME	 	= "StageName";
	public static final String DB_COL_TFCBT_STAGE_OBJECTIVE = "StageObjective";
	public static final String DB_COL_TFCBT_STAGE_DESC		= "StageDescription";
	
	//TFCBT Task Master table columns
	public static final String DB_COL_TFCBT_TASK_ID		 	= "TaskId";
	public static final String DB_COL_TFCBT_TASK_STAGE_NUM	= "StageNumber";  
	public static final String DB_COL_TFCBT_TASK_TYPE	 	= "TaskType";
	public static final String DB_COL_TFCBT_TASK_TITLE		= "TaskTitle";
	public static final String DB_COL_TFCBT_TASK_SUB_TITLE	= "TaskSubTitle";

	//TFCBT Counsellee Task table columns
	public static final String DB_COL_TFCBT_CNSLEE_TASK_ID		 		= "TaskId";
	public static final String DB_COL_TFCBT_CNSLEE_ID		 			= "CounselleeId";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM		= "StageNumber";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED	= "DateCompleted";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_TYPE			= "TaskType";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_NOTES			= "Notes";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_STATUS			= "Status";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID		= "CounsellorId";
	public static final String DB_COL_TFCBT_CNSLEE_TASK_CNSL_SESSION_ID	= "CounsellingSessionId";


	//Counselling Sessions table
	public static final String DB_COL_CNSLING_SESSION_ID 				= "CounsellingSessionId";
	public static final String DB_COL_CNSLING_SESSION_CASE_ID 			= "CaseId";
	public static final String DB_COL_CNSLING_SESSION_CNSLR_ID 			= "CounsellorId";
	public static final String DB_COL_CNSLING_SESSION_DATE 				= "SessionDate";
	public static final String DB_COL_CNSLING_SESSION_LOCATION 			= "Location";
	public static final String DB_COL_CNSLING_SESSION_START_TIME		= "StartTime";
	public static final String DB_COL_CNSLING_SESSION_DURATION			= "Duration";
	public static final String DB_COL_CNSLING_SESSION_SETTING 			= "SessionSetting";
	public static final String DB_COL_CNSLING_SESSION_OBJECTIVE 		= "SessionObjective";
	public static final String DB_COL_CNSLING_SESSION_CONTENTS_AREAS_CONCERN	= "AreasOfConcern";
	public static final String DB_COL_CNSLING_SESSION_CNSLR_COMMENTS 			= "CounselorComments";
	public static final String DB_COL_CNSLING_SESSION_FOLLOW_UP_PREP 			= "FollowUpSessionPrep";
	public static final String DB_COL_CNSLING_SESSION_THERAPY 					= "TherapyName";
	public static final String DB_COL_CNSLING_SESSION_STATUS 					= "Status";
	public static final String DB_COL_CNSLING_SESSION_CASE_MGMT 				= "CaseManagement";
	public static final String DB_COL_CNSLING_SESSION_OPEN_TO_CNSLING 			= "OpenToCounseling";
	public static final String DB_COL_CNSLING_SESSION_OPEN_TO_FUTURE_OUTSIDE 	= "OpenToFutureOutsideProst";
	public static final String DB_COL_CNSLING_SESSION_OPEN_TO_GRP_HOME		 	= "OpentToGrpHome";
	public static final String DB_COL_CNSLING_SESSION_APPETITE		 			= "Appetite";
	public static final String DB_COL_CNSLING_SESSION_SLEEP			 			= "Sleep";
	public static final String DB_COL_CNSLING_SESSION_RAPPORT		 			= "Rapport";
	public static final String DB_COL_CNSLING_SESSION_APPEAR		 			= "Appearance";
	public static final String DB_COL_CNSLING_SESSION_MOOD		 				= "Mood";
	public static final String DB_COL_CNSLING_SESSION_AFFECT		 			= "Affect";
	public static final String DB_COL_CNSLING_SESSION_THOUGHT		 			= "Thought";
	public static final String DB_COL_CNSLING_SESSION_DISTURBANCES		 		= "Disturbances";
	public static final String DB_COL_CNSLING_SESSION_ORIENTATION		 		= "Orientation";
	public static final String DB_COL_CNSLING_SESSION_INSIGHT		 			= "Insight";
	public static final String DB_COL_CNSLING_SESSION_JUDGEMENT		 			= "Judgement";
	public static final String DB_COL_CNSLING_SESSION_APPETITE_COMMENTS		 			= "AppetiteComments";
	public static final String DB_COL_CNSLING_SESSION_SLEEP_COMMENTS			 		= "SleepComments";
	public static final String DB_COL_CNSLING_SESSION_RAPPORT_COMMENTS		 			= "RapportComments";
	public static final String DB_COL_CNSLING_SESSION_APPEAR_COMMENTS		 			= "AppearanceComments";
	public static final String DB_COL_CNSLING_SESSION_MOOD_COMMENTS		 				= "MoodComments";
	public static final String DB_COL_CNSLING_SESSION_AFFECT_COMMENTS		 			= "AffectComments";
	public static final String DB_COL_CNSLING_SESSION_THOUGHT_COMMENTS		 			= "ThoughtComments";
	public static final String DB_COL_CNSLING_SESSION_DISTURBANCES_COMMENTS		 		= "DisturbancesComments";
	public static final String DB_COL_CNSLING_SESSION_ORIENTATION_COMMENTS		 		= "OrientationComments";
	public static final String DB_COL_CNSLING_SESSION_INSIGHT_COMMENTS		 			= "InsightComments";
	public static final String DB_COL_CNSLING_SESSION_JUDGEMENT_COMMENTS		 		= "JudgementComments";

	
	/**
	 * Get the Database connection
	 * @return
	 */
	public static Connection getConnection(){
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "act2c1";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "geena1@#$";
		
		
//		String url = "jdbc:mysql://localhost:3306/";
//		String dbName = "ActAceTest1";
//		String driver = "com.mysql.jdbc.Driver";
//		String userName = "root"; 
//		String password = "Raja123@#$";
		
		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	public String likeClause(String col, String val){
		if (col == null || val == null || col.length() <1 || val.length() < 1)
			return "";
		
		StringBuffer sb = new StringBuffer();
		sb.append(col + " Like " );
		sb.append("'%" + val + "%'");
		
		return sb.toString();
		
	}
	public static void main (String args[]){
		
		String sql = "Select * from " +DB_TBL_INDIV;
		
		try{
			Connection con = getConnection();
			
			Statement st = con.createStatement();
			st.execute(sql);
			
			ResultSet rs = st.getResultSet();
			
			while (rs.next()){
				String s = rs.getString(DB_COL_INDIV_ID);
				String s2 = rs.getString(DB_COL_INDIV_NAME);
				System.out.println(s);
				System.out.println(s2);
				
			}
			con.close();
									
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static String joinVStrings(Vector vStringToJoin) 
	{
		StringBuffer joinedString = new StringBuffer() ;
		if (vStringToJoin != null) {									// ND edited 15th Dec
			for (int i = 0; i < vStringToJoin.size(); i++)
			{	
				if (i >0)
					joinedString.append(",");							// ND edited 6th Apr 16
			
				joinedString.append(vStringToJoin.elementAt(i));
				
			}
			}
			else {
				joinedString.append((""));
			}
		return joinedString.toString();
	}
	
	public static String createInsertQry(	String[] columnNames,					// String tableName,
											String[] values){						// ND edited on 11th Dec
		
		StringBuffer sbQuery = new StringBuffer();
		
//		sbQuery.append("Insert into ");
//		sbQuery.append(tableName);
		sbQuery.append(" (");
		
		for (int i = 0; i < columnNames.length; i++) {
			if (i > 0)
				sbQuery.append(",");
			sbQuery.append(columnNames[i]);
		}
		sbQuery.append(") ");
		sbQuery.append(" values (");
		
		for (int i = 0; i < values.length; i++) {
			if (i > 0)
				sbQuery.append(",");
			sbQuery.append("'");
			sbQuery.append(values[i]);
			sbQuery.append("'");
		}
		sbQuery.append(") ");
		return sbQuery.toString();
	}
	
	public String noNull(String val){
		if (val == null)
			return "";
		return val.trim();
	}
	
	public String makeInsertQueryValue(String value){
		if (value == null)
			return "''";
		
		return "'" + value.trim() + "'";
	}
	public static Vector stringToVector (String str_tobreak) {
		
		StringTokenizer st = new StringTokenizer(str_tobreak, ",");
		Vector vs = new Vector();

		if(str_tobreak != null) {
			int i = 0;
			while(st.hasMoreTokens())
			{
				vs.add(st.nextToken());
				System.out.println("transferred tokens to vector " +  vs.elementAt(i));
				i++;
			}
			}
		else {
			System.out.println("no string to break into tokens");
			
		}
		return vs;
	}

	public static Hashtable<String, String> transferLangToHash(String sRead, String sWrite, String sSpeak) {
		// TODO Auto-generated method stub
		Hashtable <String, String> htLangUse = new Hashtable<String, String>();
		Vector vRead, vWrite, vSpeak, vLangKnown;
		String sLangKnown = new String();
		
		vRead = DB.stringToVector(sRead);
		vWrite = DB.stringToVector(sWrite);
		vSpeak = DB.stringToVector(sSpeak);
		System.out.println("S: " + vSpeak + " R: " + vRead + " W: " + vWrite);				// ND added 28th Mar 16
		for (int l=0; l < 3; l++){
			switch (l) {
			case 0: vLangKnown = vSpeak;
					break;
			case 1: vLangKnown = vRead;
					break;
			case 2: vLangKnown = vWrite;
					break;
			default: vLangKnown = null;
			}
			if(vLangKnown != null) {
				String sLangAbil = new String();
				String langToEnter = new String();
				for (int i = 0; i < vLangKnown.size(); i++) {
				String sLangInVector = vLangKnown.elementAt(i).toString();
				System.out.println(sLangInVector + " Number of langs:" + vLangKnown.size() + " - " + l);
				switch ( l) {
				case  0: 
					langToEnter = "Speak";
					htLangUse.put(sLangInVector, langToEnter);			// all the languages in the Speak field will be added to the hash table
					break;															// one after the other : case 0
				case 1:
					if (htLangUse.containsKey(sLangInVector) == false){					// ND edited 28th Mar 16
						langToEnter = "Read";
						htLangUse.put(sLangInVector, langToEnter);
					}
					else {
						langToEnter = "Speak, Read";
						htLangUse.put(sLangInVector, langToEnter);
					}
					break;
				case 2:
					if (htLangUse.containsKey(sLangInVector) == false) {					// ND edited 28th Mar 16
						htLangUse.put(sLangInVector, "Write");
					}
					else {
						sLangAbil = htLangUse.get(sLangInVector);
						sLangAbil = sLangAbil + ", Write";
						htLangUse.put(sLangInVector, sLangAbil);
					}
					break;
				default:
					System.out.println("No languagess known and none transferred into the hash table");
					break;
				}
			}
		}
	}
	if (htLangUse != null){
		String sKeyLangs = new String();
		sKeyLangs = htLangUse.keys().toString();
		System.out.println("The diff languages known are : " + sKeyLangs);
		return htLangUse;
	} 
	else 
		htLangUse.put("Error", "No Languages used");
	
	return htLangUse;
	}

}
