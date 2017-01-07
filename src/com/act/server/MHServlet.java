package com.act.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.common.ACEDefines;
import com.act.common.AbuseIndivObj;
import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CounseleeHistoryObj;
import com.act.common.CounseleeRelativeIndivObj;
import com.act.common.Counsellee;
import com.act.common.CounselleeReportDataObject;
import com.act.common.CounselleeTFCBTStage;
import com.act.common.CnsleeTFCBTTask;
import com.act.common.CounsellingSessionObj;
import com.act.common.CounsellingTherapy;
import com.act.common.EducationHistIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.GroupsObj;
import com.act.common.LegalHistIndivObj;
import com.act.common.MentalStatusIndivObj;
import com.act.common.Organisation;
import com.act.common.PhysicalHistIndivObj;
import com.act.common.Pincode;
import com.act.common.Religion;
import com.act.common.Relationship;		// ND added
import com.act.common.Addiction;		// ND added 23rd Sep
import com.act.common.CopingSkill;		// ND added 24th Sep
import com.act.common.Language;			// ND added 25th Sep
import com.act.common.NonFormalEducation;		// ND added on 28th Sep
import com.act.common.Recreation;		// ND added on 12th Oct
import com.act.common.SocialHistIndivObj;
import com.act.common.StrenghtIndivObj;
import com.act.common.SummaryIndivObj;
import com.act.common.TFCBTStage;
import com.act.common.TFCBTStageMilestone;
import com.act.common.TFCBTStageTask;
import com.act.common.TSC40Obj;
import com.act.common.TSC54Obj;
import com.act.common.User;
import com.act.common.VocationFinIndivObj;

import com.act.server.db.CounselleeMSDB;
import com.act.server.db.CounsellingSessionMSDB;
import com.act.server.db.GeneralMstMSDB;
import com.act.server.db.ReportsMSDB;
import com.act.server.db.TFCBTMSDB;
import com.act.server.db.TSCChkLstMSSDB;
import com.act.server.db.UserMSDB;

public class MHServlet extends HttpServlet implements ACEDefines{
	

	HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	
    	session = request.getSession();
    
    	System.out.println("Hit the MHServlet !!!!!!!");
    	
        //Authentication
    	String currUser = session.getAttribute("LoginUser").toString();
    	System.out.println("######################### loggedin user" + session.getAttribute("LoginUser"));
        String currUsrPasswrd  = request.getParameter("Password");
        
        ObjectInputStream in = new ObjectInputStream(request.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream (response.getOutputStream());
        
        //First read the request Id
        String requestId = null;
		try {
			requestId = (String)in.readObject();
			System.out.println("MHSERVLET  requestid - " + requestId);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO Logging
			//Handle the error and return
		}
        
        
		try{
	        //Process the Requests
	        if (requestId.equals(ACE_REQ_MH_GET_COUNSELEE)){
	        	System.out.println("processing request get counselee list..");
	        	Hashtable options = (Hashtable)in.readObject();
	        	Vector<Counsellee> list = CounselleeMSDB.getInstance().getCounsleeList(currUser, options);
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("success");

	        }else if (requestId.equals(ACE_REQ_MH_SAVE_COUNSELEE)){
	        	System.out.println("processing request save counselee list..");
	        	Counsellee cnslee = (Counsellee)in.readObject();
	        	CounseleeHistoryObj cnslHist = (CounseleeHistoryObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().saveCounselee(cnslee, cnslHist);		// ND edited on 8th Dec
	        	System.out.println("DB OPERATION SUCCESS . saved cnslee");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success");

	        }else if (requestId.equals(ACE_REQ_MH_DEL_COUNSELEE)){								// copied this section when amalgamting on 10th Nov 15
	        	System.out.println("processing request delete counselee list..");
	        	Vector vCaseIds = (Vector)in.readObject();
	        	boolean bResult = CounselleeMSDB.getInstance().deleteCounsellee(vCaseIds);
	        	System.out.println("DB OPERATION SUCCESS . deleted cnslee");
	        	
	        	if (bResult)
	        		out.writeObject("SUCCESS");
	        	else
	        		out.writeObject("ERROR");
	        	out.flush();
	        	out.close();
	        	System.out.println("success");
	        }
	        // ND added on 27th Oct
	        else if (requestId.equals(ACE_REQ_MH_SAVE_COUNSELEEHISTOBJ)){						// this section not in the other MHServlet file
	        	System.out.println("processing request save counselee history ..");
	        	CounseleeHistoryObj cnslHist = (CounseleeHistoryObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().saveCounseleeHistory(cnslHist);
	        	System.out.println("DB OPERATION SUCCESS . saved counselee history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success");

	        }

	        //Process the Requests		
	        //ND added on 11th Sep
	        else if (requestId.equals(ACE_REQ_MH_GET_RELIGION)){
	        	System.out.println("processing request get religion list..");
	        	Vector<Religion> list = GeneralMstMSDB.getInstance().getReligions();
	        	System.out.println("DB OPERATION SUCCESS . Row Count religion- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("religion successfully retrieved");		//ND edited on 12th Oct
	        }
	        // ND added on 23rd Sep
	        else if (requestId.equals(ACE_REQ_MH_GET_RELATIONSHIP)){
	        	System.out.println("processing request get relative type list..");
	        	Vector<Relationship> list = GeneralMstMSDB.getInstance().getRelationship();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("relationships retrieved");				//ND edited on 12th Oct
	        }
	        //ND added on 23rd Sep
	        else if (requestId.equals(ACE_REQ_MH_GET_ADDICTION)){
	        	System.out.println("processing request get addiction list..");
	        	Vector<Addiction> list = GeneralMstMSDB.getInstance().getAddiction();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("addictions successfully retreived");	//ND edited on 12th Oct
	        }
	        // ND added on 24th Sep
	        else if (requestId.equals(ACE_REQ_MH_GET_COPINGSKILL)){
	        	System.out.println("processing request get coping skill list..");
	        	Vector<CopingSkill> list = GeneralMstMSDB.getInstance().getCopingSkill();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("coping skills successfully retreived");	//ND edited on 12th Oct
	        }
	        // ND added on 25th Sep
	        else if (requestId.equals(ACE_REQ_MH_GET_LANGUAGE)){
	        	System.out.println("processing request get language list..");				//ND edited on 12th Oct
	        	Vector<Language> list = GeneralMstMSDB.getInstance().getLanguage();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("languages successfully retreived");		//ND edited on 12th Oct
	        }
	        // ND added on 28th Sep
	        else if (requestId.equals(ACE_REQ_MH_GET_NONFORMALEDUCATION)){
	        	System.out.println("processing request get non formal education list..");		//ND edited on 12th Oct
	        	Vector<NonFormalEducation> list = GeneralMstMSDB.getInstance().getNonFormalEducation();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("non-formal education successfully retreived");			//ND edited on 12th Oct
	        }
	        // ND added on 12th Oct
	        else if (requestId.equals(ACE_REQ_MH_GET_RECREATION)){
	        	System.out.println("processing request get recreation list..");		
	        	Vector<Recreation> list = GeneralMstMSDB.getInstance().getRecreation();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("recreation successfully retreived");			//ND edited on 12th Oct
	        }
	        // ND added on 05th Aug 16
	        else if (requestId.equals(ACE_REQ_MH_GET_SERVICETYPE )){
	        	System.out.println("processing request get service type list..");		
	        	Vector<String> list = GeneralMstMSDB.getInstance().getServiceTypes();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("service type successfully retreived");			//ND edited on 12th Oct
	        }
	        // ND added on 25th Aug 16
	        else if (requestId.equals(ACE_REQ_MH_GET_PININFO)){
	        	System.out.println("processing request get service type list..");		
	        	Vector<Pincode> list = GeneralMstMSDB.getInstance().getPinInfo();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("service type successfully retreived");			//ND edited on 12th Oct
	        	
	        }

	        // ND added on 19th Oct
	        else if (requestId.equals(ACE_REQ_MH_GET_ORGANISATION)){						// This section not in the other MHServlet file
	        	System.out.println("processing request get Organisation list..");			
	        	Vector<String> list = GeneralMstMSDB.getInstance().getOrganisation();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("Organisation successfully retreived");		
	        }
	        // ND added on 11th Jan 16
	        else if (requestId.equals(ACE_REQ_MH_GET_CAREHOME)){						
	        	System.out.println("processing request get Care home list");			
	        	Vector<String> list = GeneralMstMSDB.getInstance().getCareHome();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("Care home successfully retreived");		
	        }
	        
// start ND added 19th Jan 16
	        else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_DETAILS)){
	        	System.out.println("processing request Update counselee details..");
	        	Counsellee cnslee = (Counsellee)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateCounselee(cnslee);	
	        	System.out.println("DB OPERATION SUCCESS . updated cnslee");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success");

	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_ABUSEHIST)) {
	         	System.out.println("processing request Update Abuse history details..");
	        	AbuseIndivObj abuseHist = (AbuseIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateAbuseHist(abuseHist);	
	        	System.out.println("DB OPERATION SUCCESS . updated Abuse history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success");

	        	
	        	
	        	// start ND added 5th Feb 16
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_EDUHIST)) {
	         	System.out.println("processing request Update Education history details..");
	        	EducationHistIndivObj eduHist = (EducationHistIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateEduHist(eduHist);	
	        	System.out.println("DB OPERATION SUCCESS . updated Education history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Edu hist update success");

	        
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_FAMENV)) {
	        	System.out.println("processing request Update Family environment details...");
	        	FamilyEnvIndivObj famEnv = (FamilyEnvIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateFamEnv(famEnv);	
	        	System.out.println("DB OPERATION SUCCESS . updated Famiily environment");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Fam Env update success");
	        	
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_CNSLRELN)) {
	        	System.out.println("processing request Update Counselee Family Relationships...");
	        	Vector cnslReln = new Vector<CounseleeRelativeIndivObj>();
	        	String caseNum = null;																// ND added 02nd May 16
	        	caseNum = (String)in.readObject();													// ND added 02nd May 16
	        	cnslReln = (Vector)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateCnslReln(cnslReln, caseNum);	// ND edited 02nd May 16
	        	out.writeObject(sResult);										
	        	out.flush();
	        	out.close();
	        	System.out.println("Fam Hist relaties update success");
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_PHYSICALHIST)) {
	        	System.out.println("processing request Update Physical history details...");
	        	PhysicalHistIndivObj phyHist = (PhysicalHistIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updatePhysicalHist(phyHist) ;	
	        	System.out.println("DB OPERATION SUCCESS . updated Physical history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Phy hist update success");

	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_MENTALSTAT)) {
	        	System.out.println("processing request Update Psychological history details...");
	        	MentalStatusIndivObj psychoHist = (MentalStatusIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateMentalStat(psychoHist);	
	        	System.out.println("DB OPERATION SUCCESS . updated Psychological history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Psycho hist update success");

	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_SOCIALHIST)) {
	        	System.out.println("processing request Update Social history details...");
	        	SocialHistIndivObj socHist = (SocialHistIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateSocialHist(socHist) ;	
	        	System.out.println("DB OPERATION SUCCESS . updated Social history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Soc hist update success");

	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_CNSLSTRENGTH)) {
	        	System.out.println("processing request Update Counselee strength details...");
	        	StrenghtIndivObj cnslStre = (StrenghtIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateCnslStrength(cnslStre);	
	        	System.out.println("DB OPERATION SUCCESS . updated Counselee strength");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Cnsl Strength update success");

//	        	public static String ACE_REQ_MH_UPDATE_CNSLEE_CNSLSUMM = "Update Counselee Summary";
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_CNSLSUMM)) {
	        	System.out.println("processing request Update Counselee summary details...");
	        	SummaryIndivObj cnslSumm = (SummaryIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateCnslSumm(cnslSumm);	
	        	System.out.println("DB OPERATION SUCCESS . updated Counselee summary info");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Cnsl summary info update success");

	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_VOCHIST)) {
	        	System.out.println("processing request Update Vocational history details...");
	        	VocationFinIndivObj vocFinHist = (VocationFinIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateVocHist(vocFinHist) ;	
	        	System.out.println("DB OPERATION SUCCESS . updated Vocational and Financial history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Voc Fin hist update success");

	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CNSLEE_LEGAL)) {
	        	System.out.println("processing request Update Legal history details...");
	        	LegalHistIndivObj legalHist = (LegalHistIndivObj)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateLegal(legalHist);	
	        	System.out.println("DB OPERATION SUCCESS . updated Legal history");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("Legal hist update success");
	
	        }
	        // end ND added 5 Feb 16
	        // start ND added 12 Feb 16
	        else if (requestId.equals(ACE_REQ_MH_GET_CNSLEE_HISTORY)) {
	        	System.out.println("processing request for editing Counselee history details...");
	        	String caseNum = (String)in.readObject();
	        	CounseleeHistoryObj cnslHist = CounselleeMSDB.getInstance().getCnslHist( caseNum);
	        	System.out.println("DB OPERATION SUCCESS . retrieve all Counselee History " );
	        	
	        	out.writeObject(cnslHist);
	        	out.flush();
	        	out.close();
	        	System.out.println("All history of Counselee retrieved");		
	        }else if (requestId.equals(ACE_REQ_MH_GET_CHKLST_TSC)) {
	         	System.out.println("processing request get TSC Chk List.");
	        	Hashtable<String, String> htOptions = (Hashtable<String, String>)in.readObject();
	        	Vector vCheckListsTSC54 = (Vector)TSCChkLstMSSDB.getInstance().getTSC54List(htOptions);
	        	Vector vCheckListsTSC40 = (Vector)TSCChkLstMSSDB.getInstance().getTSC40List(htOptions);
	        	
	        	System.out.println("get tsc chk list 5");
	        	//add everything to a single vector
	        	vCheckListsTSC54.addAll(vCheckListsTSC40);
	        	System.out.println("DB OPERATION SUCCESS . get TSC Chk List");
	        	
	        	out.writeObject(vCheckListsTSC54);
	        	out.flush();
	        	out.close();
	        	System.out.println("success get tsc checklist" + vCheckListsTSC54.size());
	        	
	        }else if (requestId.equals(ACE_REQ_MH_SAVE_CHKLST_TSC40)) {
	         	System.out.println("processing request gsave TSC40 Chk List.");
	         	TSC40Obj tsc40Obj = (TSC40Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().saveTSC40List(tsc40Obj);
	        	System.out.println("DB OPERATION SUCCESS . save TSC 40Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save tsc checklist");
	        }else if (requestId.equals(ACE_REQ_MH_SAVE_CHKLST_TSC54)) {
	         	System.out.println("processing request gsave TSC54 Chk List.");
	         	TSC54Obj tsc54Obj = (TSC54Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().saveTSC54List(tsc54Obj);
	        	System.out.println("DB OPERATION SUCCESS . save TSC 54Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save tsc checklist");
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CHKLST_TSC54)) {
	         	System.out.println("processing request UPDATE TSC54 Chk List.");
	         	TSC54Obj tsc54Obj = (TSC54Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().updateTSC54List(tsc54Obj);
	        	System.out.println("DB OPERATION SUCCESS . update TSC 54Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success update tsc 54 checklist");
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CHKLST_TSC40)) {
	         	System.out.println("processing request update TSC40 Chk List.");
	         	TSC40Obj tsc40Obj = (TSC40Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().updateTSC40List(tsc40Obj);
	        	System.out.println("DB OPERATION SUCCESS . update TSC 40Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success update tsc40 checklist");
	        	
	        }else if (requestId.equals(ACE_REQ_MH_DEL_CHKLST_TSC40)) {
	         	System.out.println("processing request DEL TSC40 Chk List.");
	         	TSC40Obj tsc40Obj = (TSC40Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().deleteTSC40List(tsc40Obj);
	        	System.out.println("DB OPERATION SUCCESS . delete TSC 40Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success delete tsc40 checklist");
	        }else if (requestId.equals(ACE_REQ_MH_DEL_CHKLST_TSC54)) {
	         	System.out.println("processing request DEL TSC54 Chk List.");
	         	TSC54Obj tsc54Obj = (TSC54Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().deleteTSC54List(tsc54Obj);
	        	System.out.println("DB OPERATION SUCCESS . del TSC 54Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success update tsc 54 checklist");
	        }else if (requestId.equals(ACE_REQ_MH_UPDATE_CHKLST_TSC54)) {
	         	System.out.println("processing request UPDATE TSC54 Chk List.");
	         	TSC54Obj tsc54Obj = (TSC54Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().updateTSC54List(tsc54Obj);
	        	System.out.println("DB OPERATION SUCCESS . update TSC 54Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success update tsc 54 checklist");
	        }else if (requestId.equals(ACE_REQ_MH_GET_CNLSEE_TFCBT_STAGES)) {
	        	String cnsleeId = (String)in.readObject();
	        	Hashtable<String, CounselleeTFCBTStage> ht = TFCBTMSDB.getInstance().getCnsleeTFCBTStages(cnsleeId);
	        	System.out.println("DB OPERATION SUCCESS . get cnslee TCBT Stages");
	        	 
	        	out.writeObject(ht);
	        	out.flush();
	        	out.close();
	        	System.out.println("success get TCBT Stages");
	        }else if (requestId.equals(ACE_REQ_MH_GET_TFCBT_STAGES)) {
	        	Hashtable<String, TFCBTStage> ht = TFCBTMSDB.getInstance().getTFCBTStages();
	        	System.out.println("DB OPERATION SUCCESS . get TCBT Stages");
	        	 
	        	out.writeObject(ht);
	        	out.flush();
	        	out.close();
	        	System.out.println("success get TCBT Stages");
	        }else if (requestId.equals(ACE_REQ_MH_GET_TFCBT_STAGE_TASKS)) {
	        	String stageNum = (String)in.readObject();
	        	Vector<TFCBTStageTask> v= TFCBTMSDB.getInstance().getTFCBTStageTask(stageNum);
	        	System.out.println("DB OPERATION SUCCESS . get TCBT Stage task");
	        	 
	        	out.writeObject(v);
	        	out.flush();
	        	out.close();
	        	System.out.println("success get TCBT Stage tasks");
	        }else if (requestId.equals(ACE_REQ_MH_GET_TFCBT_STAGE_MILESTONES)) {
	        	String stageNum = (String)in.readObject();
	        	Vector<TFCBTStageMilestone> v= TFCBTMSDB.getInstance().getTFCBTStageMilestones(stageNum);
	        	System.out.println("DB OPERATION SUCCESS . get TCBT Stage milestone");
	        	 
	        	out.writeObject(v);
	        	out.flush();
	        	out.close();
	        	System.out.println("success get TCBT Stage milestones");
        	}else if (requestId.equals(ACE_REQ_MH_SAVE_CNSLEE_TFCBT_MILESTONE)) {
        		CnsleeTFCBTMileStone milestone = (CnsleeTFCBTMileStone)in.readObject();
	        	String sResult = TFCBTMSDB.getInstance().saveCnsleeTFCBTMilestone(milestone);
	        	System.out.println("DB OPERATION SUCCESS . get TCBT Stage milestone");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save TCBT Stage milestones");
        	}else if (requestId.equals(ACE_REQ_MH_SAVE_CNSLEE_TFCBT_TASK)) {
	        	CnsleeTFCBTTask task = (CnsleeTFCBTTask)in.readObject();
	        	String sResult = TFCBTMSDB.getInstance().saveCnsleeTFCBTTask(task);
	        	System.out.println("DB OPERATION SUCCESS . get TCBT Stage Task");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save TCBT Stage Tasks");
        	}else if (requestId.equals(ACE_REQ_MH_SAVE_ADDICT)){
	        	System.out.println("process request SAVE_ADDICT");
	        	Vector vAddict = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveAddiction(vAddict);
	        	System.out.println("DB OPERATION SUCCESS - save addictions");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save addictions");
			}
	        else if (requestId.equals(ACE_REQ_MH_SAVE_COPSKILL)){
	        	System.out.println("process request SAVE_COPSKILL");
	        	Vector vCopSkill = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveCopingSkill(vCopSkill);
	        	System.out.println("DB OPERATION SUCCESS - save coping skills");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save coping skills");
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_HOBBY)){
	        	System.out.println("process request SAVE_HOBBY");
	        	Vector vHobby = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveHobby(vHobby);
	        	System.out.println("DB OPERATION SUCCESS - save hobbies");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save hobbies");
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_LANGUAGE)){
	        	System.out.println("process request SAVE_LANGUAGE");
	        	Vector vLanguage = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveLanugage(vLanguage);
	        	System.out.println("DB OPERATION SUCCESS - save languages");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save languages");
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_NFEDU)){
	        	System.out.println("process request SAVE_NFEDU");
	        	Vector vNFEdu = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveNFEdu(vNFEdu);
	        	System.out.println("DB OPERATION SUCCESS - save non-formal education");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save non-formal education");
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_RELIGION)){
	        	System.out.println("process request SAVE_RELIGION");
	        	Vector vReligion = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveReligion(vReligion);
	        	System.out.println("DB OPERATION SUCCESS - save religions");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save religions");
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_RELATE)){
	        	System.out.println("process request SAVE_RELATE");
	        	Vector vRelate = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveRelationship(vRelate);
	        	System.out.println("DB OPERATION SUCCESS - save relationship");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save relationship");
	        }
			// start ND added on 06th Aug 16	        
	        else if (requestId.equals(ACE_REQ_MH_SAVE_SERVICETYPE)){	
	        	System.out.println("process request SAVE_SERVICETYPE");
	        	Vector vServType = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveServiceTypes(vServType);
	        	System.out.println("DB OPERATION SUCCESS - save service type");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save service type");
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_CAREHOME)){	
	        	System.out.println("process request SAVE_CAREHOME");
	        	Vector vKHome = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveCareHome(vKHome);
	        	System.out.println("DB OPERATION SUCCESS - save care home");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save care home");
	        }
	        // end ND added on 06th Aug 16
	        else if (requestId.equals(ACE_REQ_MH_SAVE_ORGN )){								// ND added on 21st Aug 16
	        	System.out.println("process request SAVE_ORGN");
//		        	Vector vOrgn = (Vector) in.readObject();
	        	Organisation orgnObj = (Organisation) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveOrgnDetails(orgnObj);
	        	System.out.println("DB OPERATION SUCCESS - save organisations");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save organisations");
	        }
	        else if (requestId.equals(ACE_REQ_MH_UPDATE_ORGN)){
	        	System.out.println("process request UPDATE_ORGN");
//		        	Vector vOrgn = (Vector) in.readObject();
	        	Organisation orgnObj = (Organisation) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().updtOrgnDetails(orgnObj);
	        	System.out.println("DB OPERATION SUCCESS - update organisations");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success update organisations");
	        }
	        else if (requestId.equals(ACE_REQ_MH_DEL_ORGANISATIONS)){								// ND added on 12th Sep 16
	        	System.out.println("processing request delete organisations..");
	        	Vector vOrgnIds = (Vector)in.readObject();
	        	boolean bResult = GeneralMstMSDB.getInstance().delOrgn(vOrgnIds);
	        	System.out.println("DB OPERATION SUCCESS . deleted organisations");
	        	if (bResult)
	        		out.writeObject("SUCCESS");
	        	else
	        		out.writeObject("ERROR");
	        	out.flush();
	        	out.close();
	        	System.out.println("success");
	        	
	        }
	        else if (requestId.equals(ACE_REQ_MH_GET_ORGN_ID)){								// ND added on 18th May 16			
	        	System.out.println("processing request get Organisation ID..");		
	        	String sOrganisation = (String)in.readObject();
	        	Integer orgn_ID = GeneralMstMSDB.getInstance().getOrgn_ID(sOrganisation);
	        	System.out.println("DB OPERATION SUCCESS . ORGN Name ID - " + orgn_ID);
	        	
	        	out.writeObject(orgn_ID);
	        	out.flush();
	        	out.close();
	        	System.out.println("Organisation ID successfully retreived");		
	        }

	        else if (requestId.equals(ACE_REQ_MH_GET_ORGN_NAME)){								// ND added on 18th May 16			
	        	System.out.println("processing request get Organisation Name..");		
	        	Integer orgn_ID = (Integer)in.readObject();
	        	String orgnName = GeneralMstMSDB.getInstance().getOrgnName(orgn_ID);
	        	System.out.println("DB OPERATION SUCCESS . ORGN Name - " + orgnName);
	        	
	        	out.writeObject(orgnName);
	        	out.flush();
	        	out.close();
	        	System.out.println("Organisation Name successfully retreived");		
	        }
	        
	        else if (requestId.equals(ACE_REQ_MH_GET_ORGN_ALLIDNAME)){								// ND added on 12th Sep 16
	        	System.out.println("processing request get organisation ID and name..");
	        	Vector<Organisation> vOrgn = GeneralMstMSDB.getInstance().getOrgnIDName() ;
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + vOrgn.size());
	        	out.writeObject(vOrgn);
	        	out.flush();
	        	out.close();
	        	System.out.println("success");
	        }
	        else if (requestId.equals(ACE_REQ_MH_GET_ORGN_DETAILS)){								// ND added on 02nd Sep 16			
	        	System.out.println("processing request get Organisation details..");		
	        	Integer orgID = (Integer)in.readObject();
	        	Organisation orgnObj = GeneralMstMSDB.getInstance().getOrgnDetails(orgID);
	        	System.out.println("DB OPERATION SUCCESS . ORGN Name - " + orgnObj.getOrgnName());
	        	
	        	out.writeObject(orgnObj);
	        	out.flush();
	        	out.close();
	        	System.out.println("Organisation details successfully retreived");		
	        }
	        else if (requestId.equals(ACE_REQ_MH_GET_CAREHOME)){						
	        	System.out.println("processing request get Care home list");			
	        	Vector<String> list = GeneralMstMSDB.getInstance().getCareHome();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("Care home successfully retreived");		
	        } else if (requestId.equals(ACE_REQ_MH_SAVE_COUNSELLING_SESSION)){						
	        	System.out.println("processing request SAVE COUNSELLING");	
	        	CounsellingSessionObj sessionObj = (CounsellingSessionObj )in.readObject();
	        	String sResult = CounsellingSessionMSDB.getInstance().saveCounsellingSession(sessionObj);
	        	if (sResult.equals("SUCCESS"))
	        		System.out.println("SAVE COUNSELLING");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        } else if (requestId.equals(ACE_REQ_MH_UPDATE_COUNSELLING_SESSION)){						
	        	System.out.println("processing request UPDATE COUNSELLING");	
	        	CounsellingSessionObj sessionObj = (CounsellingSessionObj )in.readObject();
	        	String sResult = CounsellingSessionMSDB.getInstance().updateCounsellingSession(sessionObj);
	        	if (sResult.equals("SUCCESS"))
	        		System.out.println("UPDATE COUNSELLING");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        } else if (requestId.equals(ACE_REQ_MH_GET_COUNSELLING_SESSION)){						
	        	System.out.println("processing request GET COUNSELLING");	
	        	Hashtable options = (Hashtable)in.readObject();
	        	Vector<CounsellingSessionObj> list = CounsellingSessionMSDB.getInstance().getCounsellingSessionObjs(options);
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        } else if (requestId.equals(ACE_REQ_MH_DEL_COUNSELLING_SESSION)){						
	        	System.out.println("processing request DEL COUNSELLING");	
	        	CounsellingSessionObj sessionObj = (CounsellingSessionObj)in.readObject();
	        	String result = CounsellingSessionMSDB.getInstance().delCounsellingSession(sessionObj);
	        	
	        	out.writeObject(result);
	        	out.flush();
	        	out.close();
	        } else if (requestId.equals(ACE_REQ_MH_GET_CNSLEE_REPORTS)){						
	        	System.out.println("processing request GER reports");	
	        	Vector<String> colNames = (Vector<String>)in.readObject();
	        	Hashtable<String, String> htOptions = (Hashtable<String, String>)in.readObject(); 
	        	Vector <CounselleeReportDataObject> vReprotData = 
	        				ReportsMSDB.getInstance().getCounselleeReports(htOptions, colNames);
	        	
	        	out.writeObject(vReprotData);
	        	out.flush();
	        	out.close();
	        }
	        else if (requestId.equals(ACE_REQ_MH_SAVE_COUNSELINGTHERAPY)){								// ND added on 26th Sep 16
	        	System.out.println("process request SAVE_COUNSLMETH");
	        	Vector vCounslMeth = (Vector) in.readObject();
	        	String sResult = GeneralMstMSDB.getInstance().saveCounselingTherapy(vCounslMeth);
	        	System.out.println("DB OPERATION SUCCESS - save counseling methods");
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success save counseling methods");
	        }
	        else if (requestId.equals(ACE_REQ_MH_GET_COUNSELINGTHERAPY)){								// ND added on 26th Sep 16
	        	System.out.println("processing request get counseling methods..");
	        	Vector<CounsellingTherapy> list = GeneralMstMSDB.getInstance().getTherapy();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("counseling methdos successfully retreived");	
	        }

	        // ND added on 05th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_GET_USERPROFILE)){
	        	String user_id = (String)in.readObject();
	        	System.out.println("processing request get user profile.." + user_id);
	        	Vector<User> vProfile = new Vector<User>();
	        	vProfile = UserMSDB.getInstance().getUserInfo(user_id);
	        	System.out.println("DB OPERATION SUCCESS . Profiles read " + vProfile.size());
	        	
	        	out.writeObject(vProfile);
	        	out.flush();
	        	out.close();
	        	System.out.println("profile successfully retrieved");		
	        }
	        // ND added on 12th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_SAVE_USERINFO)){
	        	User userobj = (User)in.readObject();
	        	boolean grpChanged = in.readBoolean();
	        	System.out.println("processing request save user profile name.." + userobj.getUserID());
	        	String sResult = UserMSDB.getInstance().updateUserInfo(userobj, grpChanged);					// ND edited on 19th Oct 16
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("profile successfully saved");		
	        }
	        // ND added on 10th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_GET_USERSALL)){
	        	System.out.println("processing request get all users .." );
	        	Vector<User> vUsersAll = new Vector<User>();
	        	vUsersAll = UserMSDB.getInstance().getUsersAll();
	        	System.out.println("DB OPERATION SUCCESS . Profiles read " + vUsersAll.size());
	        	
	        	out.writeObject(vUsersAll);
	        	out.flush();
	        	out.close();
	        	System.out.println("profile successfully retrieved");		
	        }
	        // ND added on 12th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_SAVE_NEWUSER)){
	        	System.out.println("processing request save new users .." );
	        	User userNew = (User) in.readObject();
	        	String sResult = UserMSDB.getInstance().saveNewUser(userNew);
	        	System.out.println("DB OPERATION SUCCESS . User saved " + userNew.getUserID());
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("new user successfully SAVED");		
	        }
	        // ND added on 19th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_DEL_USER)){						
	        	String userID = (String)in.readObject();
	        	System.out.println("processing request delete user.. " +  userID);
	        	boolean bResult = UserMSDB.getInstance().delUser(userID);
	        	System.out.println("DB OPERATION SUCCESS . deleted user " + bResult);
	        	if (bResult)
	        		out.writeObject("SUCCESS");
	        	else
	        		out.writeObject("ERROR");
	        	out.flush();
	        	out.close();
	        	System.out.println("success");
	        	
	        }

	        // ND added on 14th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_GET_GRPSALL)){
	        	System.out.println("processing request get all user groups .." );
	        	Vector<GroupsObj> vUserGrpsAll = new Vector<GroupsObj>();
	        	vUserGrpsAll = UserMSDB.getInstance().getGroups();
	        	System.out.println("DB OPERATION SUCCESS . Profiles read " + vUserGrpsAll.size());
	        	
	        	out.writeObject(vUserGrpsAll);
	        	out.flush();
	        	out.close();
	        	System.out.println("user groups successfully retrieved");		
	        }
	        else if (requestId.equals(ACE_REQ_MH_GET_GROUPINFO)){
	        	System.out.println("processing request get all user groups .." );
	        	String grpName = (String) in.readObject();
	        	Vector<GroupsObj> vAUserGrps = new Vector<GroupsObj>();
	        	vAUserGrps = UserMSDB.getInstance().getGroupInfo(grpName);
	        	System.out.println("DB OPERATION SUCCESS . Profile read " + vAUserGrps.size());
	        	
	        	out.writeObject(vAUserGrps);
	        	out.flush();
	        	out.close();
	        	System.out.println("A user group successfully retrieved");		
	        }
	        // ND added on 15th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_SAVE_USERGRP)){
	        	System.out.println("processing request save new user group .." );
	        	GroupsObj userGrpNew = (GroupsObj) in.readObject();
	        	String sResult = UserMSDB.getInstance().saveNewGroup(userGrpNew);
	        	System.out.println("DB OPERATION SUCCESS . User Group saved " + userGrpNew.getGroupName() );
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("User Group successfully SAVED");		
	        }
	        // ND added on 25th Oct 16
	        else if (requestId.equals(ACE_REQ_MH_UPDATE_GROUP)){
	        	GroupsObj userGrpobj = (GroupsObj)in.readObject();
	        	String oldGroupName = (String)in.readObject();
	        	System.out.println("processing request save user profile name.." + userGrpobj.getGroupName());
	        	String sResult = UserMSDB.getInstance().updateGroupInfo(userGrpobj, oldGroupName );					
	        	
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("profile successfully saved");		
	        }
	        else if (requestId.equals(ACE_REQ_MH_DEL_GROUPS)){						
	        	String grpName = (String)in.readObject();
	        	System.out.println("processing request delete group.. " +  grpName);
	        	boolean bResult = UserMSDB.getInstance().delGroups(grpName);
	        	System.out.println("DB OPERATION SUCCESS . deleted groups " + bResult);
	        	if (bResult)
	        		out.writeObject("SUCCESS");
	        	else
	        		out.writeObject("ERROR");
	        	out.flush();
	        	out.close();
	        	System.out.println("success");
	        	
	        }
        	else{
	        	System.out.println("ooopssss..Unknown request");
	        }

		}catch (Exception e){
			e.printStackTrace();
			
		}
		
    }    	

	

}
