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
import com.act.common.CounseleeHistoryObj;
import com.act.common.CounseleeRelativeIndivObj;
import com.act.common.Counsellee;
import com.act.common.EducationHistIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.LegalHistIndivObj;
import com.act.common.MentalStatusIndivObj;
import com.act.common.PhysicalHistIndivObj;
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
import com.act.common.TSC40Obj;
import com.act.common.TSC54Obj;
import com.act.common.VocationFinIndivObj;

import com.act.server.db.CounselleeMSDB;
import com.act.server.db.GeneralMstMSDB;
import com.act.server.db.TSCChkLstMSSDB;
import com.act.server.db.UserMSDB;

public class MHServlet extends HttpServlet implements ACEDefines{
	

	HttpSession s;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	
    	s = request.getSession();
    
    	System.out.println("Hit the MHServlet !!!!!!!");
    	
        //Authentication
        String userId = request.getParameter("username");
        System.out.println("userId" + userId);
        String password  = request.getParameter("password");
        String login = request.getParameter("Login");
        
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
	        	Vector<Counsellee> list = CounselleeMSDB.getInstance().getList(options);
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
	        // ND added on 19th Oct
	        else if (requestId.equals(ACE_REQ_MH_GET_ORGANISATION)){						// This section not in the other MHServlet file
	        	System.out.println("processing request get Organisation list..");			
	        	Vector<Language> list = GeneralMstMSDB.getInstance().getOrganisation();
	        	System.out.println("DB OPERATION SUCCESS . Row Count- " + list.size());
	        	
	        	out.writeObject(list);
	        	out.flush();
	        	out.close();
	        	System.out.println("Organisation successfully retreived");		
	        }
	        // ND added on 11th Jan 16
	        else if (requestId.equals(ACE_REQ_MH_GET_CAREHOME)){						
	        	System.out.println("processing request get Care home list");			
	        	Vector<Language> list = GeneralMstMSDB.getInstance().getCareHome();
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
	        	cnslReln = (Vector)in.readObject();
	        	String sResult = CounselleeMSDB.getInstance().updateCnslReln(cnslReln);
	        	
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
	        	System.out.println("success get tsc checklist");
	        }else if (requestId.equals(ACE_REQ_MH_SAVE_CHKLST_TSC54)) {
	         	System.out.println("processing request gsave TSC54 Chk List.");
	         	TSC54Obj tsc54Obj = (TSC54Obj)in.readObject();
	        	String sResult = TSCChkLstMSSDB.getInstance().saveTSC54List(tsc54Obj);
	        	System.out.println("DB OPERATION SUCCESS . save TSC 54Chk List");
	        	 
	        	out.writeObject(sResult);
	        	out.flush();
	        	out.close();
	        	System.out.println("success get tsc checklist");
	        }
// end ND added 19th Jan 16	        
	        else{
	        	System.out.println("ooopssss..Unknown request");
	        }

		}catch (Exception e){
			e.printStackTrace();
			
		}
		
    }    	

	

}
