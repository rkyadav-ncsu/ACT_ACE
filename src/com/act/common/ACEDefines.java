package com.act.common;

public interface ACEDefines {
	
	//HTTP Request Definitions
	public static String ACE_REQ_MH_GET_COUNSELEE = "GetCounseleeList";
	public static String ACE_REQ_MH_DEL_COUNSELEE = "DelCounsellee";
	public static String ACE_REQ_MH_SAVE_COUNSELEE = "SaveCounsellee";
	public static String ACE_REQ_MH_GET_RELIGION = "GetReligionList";		
	public static String ACE_REQ_MH_GET_RELATIONSHIP = "GetRelationshipList";
	public static String ACE_REQ_MH_GET_ADDICTION = "GetAddictionList";		
	public static String ACE_REQ_MH_GET_COPINGSKILL = "GetCopingSkillList";	
	public static String ACE_REQ_MH_GET_LANGUAGE = "GetLanguage";			
	public static String ACE_REQ_MH_GET_NONFORMALEDUCATION = "GetNonFormalEducation";
	public static String ACE_REQ_MH_GET_RECREATION = "GetRecreation";		
	public static String ACE_REQ_MH_GET_ORGANISATION = "GetOrganisation";		// ND added on 19th Oct
	public static String ACE_REQ_MH_GET_CAREHOME = "GetCareHome";				// ND added on 24th Oct ////////////////
	public static String ACE_REQ_MH_GET_CHKLST_TSC = "GetCheckListTSC";
	public static String ACE_REQ_MH_GET_SESSION = "GetSessionList";
	
	public static String ACE_REQ_MH_GET_CNSLEE_HISTORY = "Get Counselee history";			// ND added on 12th Feb 16

	public static String ACE_REQ_MH_SAVE_COUNSELEEHISTOBJ = "SaveCounseleeHistObj"; 	// ND added on 26th Sep /////////////////////////
	public static String ACE_REQ_MH_SAVE_CHKLST_TSC54 = "SaveCheckListTSC54";
	public static String ACE_REQ_MH_SAVE_CHKLST_TSC40 = "SaveCheckListTSC40";
	
	
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
	
	public static String ACE_REQ_MH_DEL_CHKLST_TSC54 = "DeleteCheckListTSC54";
	public static String ACE_REQ_MH_DEL_CHKLST_TSC40 = "DeleteCheckListTSC40";
	
	//MENTAL HEALTH
	public static String COUNSELLE_NAME 			= "Cnslee_Name";
	public static String COUNSELLOR_NAME 			= "Cnslr_Name";
	public static String COUNSELLE_PARENT_ORG 		= "Cnslee_ParntOrg";
	public static String COUNSELLE_CASE_ID			= "Cnslee_CaseId";
	public static String COUNSELLE_OTHER_NAME		= "Cnslee_OtherName";
	
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
	
//	public static String[] RECREAT_ACTIVITIES = {"Friends", "Crafts", "Music", "Art",
//												 "Dance", "Gardening", "Mehendhi",
//												 "Movies", "Shopping", "Jewellery",
//												 "Other"};

	
	
	
	
}


