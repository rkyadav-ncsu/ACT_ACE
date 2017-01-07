package com.act.common;

public interface  ACEDefines {
	
	//HTTP Request Definitions
	public static String ACE_REQ_MH_GET_COUNSELEE 				= "GetCounseleeList";
	public static String ACE_REQ_MH_DEL_COUNSELEE 				= "DelCounsellee";
	public static String ACE_REQ_MH_SAVE_COUNSELEE 				= "SaveCounsellee";
	public static String ACE_REQ_MH_GET_RELIGION 				= "GetReligionList";		
	public static String ACE_REQ_MH_GET_RELATIONSHIP 			= "GetRelationshipList";
	public static String ACE_REQ_MH_GET_ADDICTION 				= "GetAddictionList";		
	public static String ACE_REQ_MH_GET_COPINGSKILL 			= "GetCopingSkillList";	
	public static String ACE_REQ_MH_GET_LANGUAGE 				= "GetLanguage";			
	public static String ACE_REQ_MH_GET_NONFORMALEDUCATION 		= "GetNonFormalEducation";
	public static String ACE_REQ_MH_GET_RECREATION 				= "GetRecreation";		
	public static String ACE_REQ_MH_GET_ORGANISATION 			= "GetOrganisation";		
	public static String ACE_REQ_MH_GET_ORGN_ID 				= "GetOrganisationID";		
	public static String ACE_REQ_MH_GET_ORGN_NAME 				= "GetOrganisationName";		
	public static String ACE_REQ_MH_GET_COUNSELLING_SESSION 	= "GetCounsellingSessionList";
	public static String ACE_REQ_MH_GET_CNSLEE_REPORTS			= "GetCounselleeReports";

	public static String ACE_REQ_MH_GET_CAREHOME 				= "GetCareHome";				
	public static String ACE_REQ_MH_GET_CHKLST_TSC 				= "GetCheckListTSC";
	public static String ACE_REQ_MH_GET_SESSION 				= "GetSessionList";
	public static String ACE_REQ_MH_GET_CNLSEE_TFCBT_STAGES 	= "GetCounselleeTFCBTStages";
	public static String ACE_REQ_MH_GET_TFCBT_STAGES 			= "GetTFCBTStages";
	public static String ACE_REQ_MH_GET_TFCBT_STAGE_TASKS 		= "GetStageTasks";
	public static String ACE_REQ_MH_GET_TFCBT_STAGE_MILESTONES 	= "GetStageMilestones";
	public static String ACE_REQ_MH_GET_PININFO 				= "GetPinInfo";					// ND added on 25th Aug 16

	public static String ACE_REQ_MH_SAVE_CNSLEE_TFCBT_TASK 		= "SaveCnsleeTFCBTTask";
	public static String ACE_REQ_MH_SAVE_CNSLEE_TFCBT_MILESTONE = "SaveCnsleeTFCBTMilestone";

	public static String ACE_REQ_MH_GET_CNSLEE_HISTORY 			= "Get Counselee history";			// ND added on 12th Feb 16

	public static String ACE_REQ_MH_SAVE_COUNSELEEHISTOBJ 		= "SaveCounseleeHistObj"; 	// ND added on 26th Sep /////////////////////////
	public static String ACE_REQ_MH_SAVE_CHKLST_TSC54 			= "SaveCheckListTSC54";
	public static String ACE_REQ_MH_SAVE_CHKLST_TSC40 			= "SaveCheckListTSC40";
	
	public static String ACE_REQ_MH_SAVE_COUNSELLING_SESSION 	= "SaveCounsellingSession";
	
	public static String ACE_REQ_MH_UPDATE_CNSLEE_DETAILS 	= "Update Counselee details";			// ND added on 19th Jan 16
	public static String ACE_REQ_MH_UPDATE_CNSLEE_ABUSEHIST = "Update Counselee Abuse history";		// ND added on 19th Jan 16
	public static String ACE_REQ_MH_UPDATE_CNSLEE_EDUHIST 	= "Update Counselee Education history";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_FAMENV 	= "Update Counselee Family Environment";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_CNSLRELN 	= "Update Counselee Family Relationships";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_PHYSICALHIST = "Update Counselee Physical history";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_MENTALSTAT = "Update Counselee Mental Status";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_SOCIALHIST = "Update Counselee Social history";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_CNSLSTRENGTH = "Update Counselee Stengrhs";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_CNSLSUMM = "Update Counselee Summary";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_VOCHIST = "Update Counselee Vocational and Financial history";
	public static String ACE_REQ_MH_UPDATE_CNSLEE_LEGAL = "Update Counselee Legal history";
	public static String ACE_REQ_MH_UPDATE_CHKLST_TSC54 = "UpdateCheckListTSC54";
	public static String ACE_REQ_MH_UPDATE_CHKLST_TSC40 = "UpdateCheckListTSC40";
	public static String ACE_REQ_MH_UPDATE_COUNSELLING_SESSION 	= "UpdateCounsellingSession";
	
	public static String ACE_REQ_MH_DEL_CHKLST_TSC54 = "DeleteCheckListTSC54";
	public static String ACE_REQ_MH_DEL_CHKLST_TSC40 = "DeleteCheckListTSC40";
	
	public static String TFCBT_REQUIRED_TASK = "REQUIRED_TASK";
	public static String TFCBT_MILESTONE = "MILESTONE";
	
	public static String ACE_REQ_MH_SAVE_LANGUAGE = "SaveLanguage";				
	public static String ACE_REQ_MH_GET_SERVICETYPE = "GetServiceType";			
	public static String ACE_REQ_MH_SAVE_SERVICETYPE = "SaveServiceType";		
	public static String ACE_REQ_MH_SAVE_CAREHOME = "SaveCareHome";				
	public static String ACE_REQ_MY_SAVE_ORGN = "SaveOrganisations";			
	public static String ACE_REQ_MH_GET_ORGN_ALLIDNAME = "GetOrgnAllIDName";	
	public static String ACE_REQ_MH_GET_ORGN_DETAILS = "GetOrganisationDetails";
	public static String ACE_REQ_MH_UPDATE_ORGN =  "Update Organisation Information";
	public static String ACE_REQ_MH_SAVE_ADDICT = "SaveAddiction";						// ND added on 1st Aug 16
	public static String ACE_REQ_MH_SAVE_COPSKILL = "SaveCopingSkill";					// ND added on 1st Aug 16
	public static String ACE_REQ_MH_SAVE_HOBBY = "SaveHobby";							// ND added on 26th Jul 16
	public static String ACE_REQ_MH_SAVE_NFEDU = "SaveNFEdu";							// ND added on 26th Jul 16
	public static String ACE_REQ_MH_SAVE_RELIGION = "SaveReligion";						// ND added on 1st Aug 16;
	public static String ACE_REQ_MH_SAVE_RELATE = "SaveRelationship";					// ND added on 1st Aug 16;
	public static String ACE_REQ_MH_SAVE_ORGN = "SaveOrganisations";					// ND added on 21st Aug 16;

	public static String ACE_REQ_MH_DEL_ORGANISATIONS 			= "DeleteOrganisations";
	public static String ACE_REQ_MH_DEL_COUNSELLING_SESSION 	= "DelCounsellingSession";

	public static String ACE_REQ_MH_GET_COUNSELINGTHERAPY = "GetCounselingTherapy"	;
	public static String ACE_REQ_MH_SAVE_COUNSELINGTHERAPY = "SaveCounselingTherapy";	
	public static String ACE_REQ_MH_GET_USERPROFILE = "GetUserProfile";				// ND added on 05th Oct 16
	public static String ACE_REQ_MH_SAVE_USERINFO = "SaveUserInformation";	
	public static String ACE_REQ_MH_GET_USERSALL = "GetAllUsers";					// ND added on 10th Oct 16
	public static String ACE_REQ_MH_SAVE_NEWUSER = "SaveNewUser";					// ND added on 12th Oct 16
	public static String ACE_REQ_MH_DEL_USER = "DelUser";	
	public static String ACE_REQ_MH_GET_GRPSALL = "GetAllGroups";						// ND added on 14th Oct 16
	public static String ACE_REQ_MH_UPDATE_GROUP = "UpdateMstUserGroup";				// ND added on 25th Oct 16
//	public static String ACE_REQ_MH_GET_THEUSERSGRP = "GetTheUsersGroups";				// ND added on 25th Oct 16
	public static String ACE_REQ_MH_DEL_GROUPS = "DelGroups";							// ND added on 3rd Nov 16
	public static String ACE_REQ_MH_GET_USERGRPSALL = "GetAllUserGroups";
	public static String ACE_REQ_MH_GET_GROUPINFO = "GetUsersGroups";						// ND added on 14th Oct 16
	public static String ACE_REQ_MH_SAVE_USERGRP = "SaveUserGroup";						// ND added on 15th Oct 16
	public static String ACE_REQ_MB_UPDATE_USERGRP = "UpdateMstUserGroup";	
	

	public static String CAREHOME_MG = "Care Home Minor Girls";				
	
	//MENTAL HEALTH
	public static String COUNSELLE_NAME 			= "Client Name";
	public static String COUNSELLOR_NAME 			= "Counsellor Name";
	public static String COUNSELLE_CASE_ID			= "Case Id";
	public static String COUNSELLE_OTHER_NAME		= "OtherName";
	public static String COUNSELLOR_ID				= "Counsellor Id";
	public static String COUNSELLE_AGE 				= "Age";
	public static String COUNSELLE_ORGANIZATION		= "Organization";
	public static String COUNSELLE_LOCATION			= "Location";
	public static String COUNSELLE_THERAPY			= "Therapy";
	
	//USER
	public static String USER_ID					= "User Id";
	
	//COUNSELLING SESSION
	public static String CNSLING_SESSION_DATE		= "Session Date";
	public static String CNSLING_SESSION_DATE_FROM	= "Session Date From";
	public static String CNSLING_SESSION_DATE_TO	= "Session Date To";
	public static String CNSLING_SESSION_DURATION	= "Duration";
	public static String CNSLING_SESSION_SUMMARY	= "Session Summary";
	public static String CNSLING_SESSION_NEXT_OBJ	= "Next Session Objectives";
	public static String CNSLING_SESSION_CASE_MGMT	= "Case Management";
	
	//TFCBT
	public static String TFCBT_STAGE			= "TFCBTStage";
	public static String TFCBT_STAGE_FROM		= "TFCBTStageFrom";
	public static String TFCBT_STAGE_TO			= "TFCBTStageTo";
	public static String TFCBT_ACTIVITIES		= "TFCBTActivities";
	public static String TFCBT_COMMENTS			= "TFCBTComments";
	
	//General
	public static String NA = "N/A"; 
	public static String NEVER = "Never";
	public static String SOMETIMES = "Sometimes";
	public static String USUALLY = "Usually";
	public static String ALWAYS = "Always";
	public static String[] arrGradeNeSoUsAl = {NA, NEVER, SOMETIMES, USUALLY, ALWAYS};
	
	public static String GOOD = "Good";
	public static String FAIR = "Fair";
	public static String POOR = "Poor";
	
	public static String[] arrGradeGFP = {NA, GOOD, FAIR, POOR};
	
	//Physical health assessment
	public static String ADDICT_CIGARETTES = "Cigarettes";
	public static String ADDICT_ALCOHOL = "Alcohol";
	public static String ADDICT_TOBACCO = "Tobacco";
	public static String ADDICT_STEROIDS = "Steroids";
	public static String ADDICT_OTHER = "Other";
	
	public static String WSYMPT_RESTLESS = "Restlessness";
	public static String WSYMPT_BSHOT_EYES = "Bloodshot Eyes";
	public static String WSYMPT_NEEDLE_TRACK = "Needle Tracks";
	public static String WSYMPT_POOR_CONC = "Poor Concentration";
	public static String WSYMPT_TREMOR = "Tremors";
	public static String WSYMPT_OTHER = "Other";
	
	
	//Mental status Assessment
	public static String WELL_GRROMED= "Well Groomed";
	public static String UNTIDY = "Untidy";
	public static String INAPPROPRIATE = "Inappropriate";
	
	public static String CALM = "Calm";
	public static String HYPER = "Hyperactive";
	public static String AGITATED = "Agitated";
	public static String SLOW = "Slow";
	public static String TREMOR = "Tics/Tremors";
	
	public static String CONGRUENT = "Congruent";
	public static String INCONGRUENT = "Incongruent";
	public static String RESTRICTED = "Restricted";
	public static String FLAT = "Flat";
	
	public static String APPROPRIATE = "Appropriate";
	public static String DEPRESSED = "Depressed";
	public static String ANXIOUS = "Anxious";
	public static String EUPHORIC = "Euphoric";
	public static String ANGRY = "Angry";
	
	public static String NORMAL = "Normal";
	public static String SLURRED = "Slurred";
	public static String FAST = "Fast";
	public static String LOUD = "Loud";
	public static String SOFT = "Soft";
	
	public static String FRIENDLY = "Friendly";
	public static String HOSTILE = "Hostile";
	public static String SHY = "Shy";
	public static String WITHDRAWN = "Withdrawn";
	
	public static String ABOVE_AVG = "Above Average";
	public static String AVG = "Average";
	public static String BELOW_AVG = "Below Average";
	public static String DIFF_ASSESS = "Difficult to Assess";
	
	public static String LIMITED = "Limited";
	public static String IMPAIRED = "Impaired";
	
	public static String ABSENT = "Absent";
	public static String NA_AGE = "N/A Age";
	public static String ALL_SPHERES = "All Spheres";
	public static String PERSON = "Person";
	public static String PLACE = "Place";
	public static String TIME = "Time";
	public static String PURPOSE = "Purpose";
	
	public static String INTACT = "Intact";
	public static String IMMEDIATE = "Immediate";
	public static String RECENT = "Recent";
	public static String DISTANT = "Distant";
	public static String HIGH_DISTRACTED = "Highly Distracted";
	
	public static String NONE = "None";
	public static String INTENT = "Intent";
	public static String PLAN = "Plan";
	public static String MEANS = "Means";
	public static String GOAL_DIR = "Goal Directed";
	public static String RANDOM = "Random";
	public static String CONFUSED = "Confused";
	public static String INTRUSIVE = "Intrusive";
	public static String SOUND = "Sound";
	public static String SMELL = "Smell";
	public static String SIGHT = "Sight";
	
	//Languages
	public static String LANG_HINDI = "Hindi";
	public static String LANG_BENG = "Bengali";
	public static String LANG_MARATHI = "Marathi";
	public static String LANG_TAM = "Tamil";
	public static String LANG_KANN = "Kannada";
	public static String LANG_ENG = "English";
	public static String LANG_OTH = "Other";
	
	//Symptoms_Rapport
	public static String SYMPT_RAPPORT_APPROPRIATE = "Appropriate";
	public static String SYMPT_RAPPORT_HOSTILE = "Hostile";
	public static String SYMPT_RAPPORT_EVASIVE	 = "Evasive";
	public static String SYMPT_RAPPORT_DISTANT = "Distant";
	public static String SYMPT_RAPPORT_INATTENTIVE = "Inattentive";
	public static String SYMPT_RAPPORT_POOR_EYE_CONT = "Poor Eye Contact";
	
	//Symptoms_Appearence	
	public static String SYMPT_APPEAR_APPROP_DRESSED = "Appropriately Dressed";
	public static String SYMPT_APPEAR_POORLY_DRESSED = "Poorly Dressed";
	public static String SYMPT_APPEAR_UNKEMPT 			= "Unkempt";
	
	//Symptoms_Mood	
	public static String SYMPT_MOOD_ANXIOUS = "Anxious";
	public static String SYMPT_MOOD_DEPRESSED = "Depressed";
	public static String SYMPT_MOOD_ANGRY	 = "Angry";
	public static String SYMPT_MOOD_ELATED = "Elated";
	public static String SYMPT_MOOD_IRRITABLE = "Irritable";
	
	//Symptoms_Affect	
	public static String SYMPT_AFFECT_APPRO = "Appropriate";
	public static String SYMPT_Affect_Depressed = "Depressed";
	public static String SYMPT_Affect_Blunted = "Blunted";
	public static String SYMPT_Affect_Flat = "Flat";
	public static String SYMPT_Affect_Labile = "Labile";
	
	//Thought Content
	public static String SYMPT_THOUGHT_CLEAR = "Clear and Coherent";
	public static String SYMPT_THOUGHT_IMPOVERISHED = "Impoverished";
	public static String SYMPT_THOUGHT_RAPID = "Rapid";
	public static String SYMPT_THOUGHT_FLIGHT_OF_IDEAS = "Flight of Ideas";
	public static String SYMPT_THOUGHT_INCOHERENT = "Incoherent";
	public static String SYMPT_THOUGHT_LOOSE = "Loose";
	public static String SYMPT_THOUGHT_TANGENTIAL = "Tangential";
	
	//Sympt Perceptual Disturbances: //None
	public static String SYMPT_DISTURB_HALLUCINATIONS = "Hallucinations(Auditory/Visual/Tactile)";
	public static String SYMPT_DISTURB_DELUSIONS 	= "Delusions";
	public static String SYMPT_DISTURB_NONE 		= "None";
	
	//Sympt Orientation
	public static String SYMPT_ORIENTED = "Oriented (time/place/person)";
	
	//Sympt Judgement: //Fair/Poor/Grossly impaired
	public static String SYMPT_EXCELLENT = "Excellent";
	public static String SYMPT_GOOD = "Good";
	public static String SYMPT_FAIR = "Fair";
	public static String SYMPT_POOR = "Poor";
	public static String SYMPT_GROSSLY_IMPAIRED = "Grossly Impaired";
	
	
//	public static Enum[] CounsellingSessionStatus [];
	
//	public static String[] RECREAT_ACTIVITIES = {"Friends", "Crafts", "Music", "Art",
//												 "Dance", "Gardening", "Mehendhi",
//												 "Movies", "Shopping", "Jewellery",
//												 "Other"};

	
	
	
	
}


