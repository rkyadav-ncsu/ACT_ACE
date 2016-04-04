package com.act.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;

import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

import com.act.common.ACEDefines;
import com.act.common.AbuseIndivObj;
import com.act.common.CounseleeHistoryObj;
import com.act.common.CounseleeRelativeIndivObj;
import com.act.common.Counsellee;
import com.act.common.EducationHistIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.LegalHistIndivObj;
import com.act.common.MHSymptChkListObj;
import com.act.common.MentalStatusIndivObj;
import com.act.common.PersonName;
import com.act.common.PhysicalHistIndivObj;
import com.act.common.Relationship;
import com.act.common.Religion;
import com.act.common.Addiction;			
import com.act.common.CopingSkill;			
import com.act.common.Language;				
import com.act.common.NonFormalEducation;	
import com.act.common.Recreation;	
import com.act.common.Organisation;
import com.act.common.CareHome;
import com.act.common.SessionObj;
import com.act.common.SocialHistIndivObj;
import com.act.common.StrenghtIndivObj;
import com.act.common.SummaryIndivObj;
import com.act.common.TSC40Obj;
import com.act.common.TSC54Obj;
import com.act.common.VocationFinIndivObj;



public class ACEConnector implements ACEDefines{
	
	private String servletContext = "MHS";
	public static URL codeBase;
	private static ACEConnector connector = null;
	
	private ACEConnector(){
	}
	
	public static ACEConnector getInstance(URL codeBase){//TODO Refactor this to remove codebase
		if (connector ==null){
			connector = new ACEConnector();
		}
		return connector;
	}

	public static ACEConnector getInstance(){
		if (connector ==null){
			connector = new ACEConnector();
		}
		return connector;
	}
	
	private URLConnection getConnection() throws MalformedURLException, IOException {
		URL urlServlet = new URL(codeBase, servletContext);
		URLConnection con = urlServlet.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
//		con.setRe
		con.setRequestProperty("Content-Type",
		 "application/x-java-serialized-object");
		
		return con;
	}
	
	//Get counselee list
	
	public Vector<Counsellee> getCounseleeList(Hashtable htCriteria){
		Vector<Counsellee> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_COUNSELEE);
			out.writeObject(htCriteria);
			out.flush();
			out.close();
			System.out.println("connection open ... object written");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<Counsellee>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back");
			
			//test start

// 			v = testData();		
//
//	
// 			Integer integer;
//			integer = new Integer(1234);
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return v;
	}

	private Vector<Counsellee> testData() {
		Vector<Counsellee> v;
		v = new Vector<Counsellee>();
		Counsellee c = new Counsellee();
		c.setCaseNumber("IJM20120909-1234");
		c.setParentOrg("IJM");
		c.setName(new PersonName("Mary"));
		
		v.add(c);
		return v;
	}
	
	private Vector<SessionObj> testDataSessions() {
		Vector<SessionObj> v;
		v = new Vector<SessionObj>();
		SessionObj c = new SessionObj();
		c.setSessionDate("12/06/2008");
		c.setStatus("OPEN");
		c.setTherapyName("TFCBT");
		c.setComments("The person is in severe trauma");
		
		v.add(c);
		return v;
	}
	public boolean deleteCounseleeList(Vector vCaseIds){
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_COUNSELEE);
			out.writeObject(vCaseIds);
			out.flush();
			out.close();
			System.out.println("connection open ... object written delete");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			if(sResult.equals("SUCCESS"))
				return true;
			System.out.println("connection open ... object read back delete");
			
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
			
		}
		
		return false;
	}
	
	public boolean saveCounsellee(Counsellee cnslee, CounseleeHistoryObj cnslHist){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_COUNSELEE);
			out.writeObject(cnslee);
			out.writeObject(cnslHist);
			out.flush();
			out.close();
			System.out.println("connection open ... object written save");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... object read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return false;
	}

	// ND commented this on 8th Dec
//	public boolean saveCounseleeHist(CounseleeHistoryObj cnslHist){
//		URLConnection con;
//		
//		try{
//			con  = getConnection();
//			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
//			out.writeObject(ACE_REQ_MH_SAVE_COUNSELEEHISTOBJ);
//			out.writeObject(cnslHist);
//			out.flush();
//			out.close();
//			System.out.println("connection open ... Counselee details written save");
//			//Read results
//			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
//			String sResult = (String)in.readObject();
//			in.close();
//			System.out.println("connection open ... Counselee details read back save"  +sResult);
//			if(sResult.equals("SUCCESS"))
//				return true;
//			
//		}catch (Exception e){
//			e.printStackTrace();
//			
//		}
//		
//		return false;
//	}
	
	
	
	// ND added on 11th Sep get Religion list
	
	public Vector<Religion> getReligionList(){
		Vector<Religion> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_RELIGION);
			out.flush();
			out.close();
			System.out.println("connection open ... object written religion");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<Religion>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back Religion");
			
			//test start
	
	
	
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return v;
	}
	// ND added on 23rd Sep
	public Vector<Relationship> getRelationshipList(){
		Vector<Relationship> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_RELATIONSHIP);
			out.flush();
			out.close();
			System.out.println("connection open ... object written Relationship");		//ND edited on 12th Oct
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<Relationship>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back Relationship");	//ND edited on 12th Oct
			
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return v;
	}
	// ND added on 23rd Sep get Addiction list
	
	public Vector<Addiction> getAddictionList(){
		Vector<Addiction> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_ADDICTION);
			out.flush();
			out.close();
			System.out.println("connection open ... object written Addiction");			//ND edited on 12th Oct
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<Addiction>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back Addiction");		//ND edited on 12th Oct
			
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return v;
	}

	// ND added on 24th Sep get Coping Skill list
	
	public Vector<CopingSkill> getCopingSkillList(){
		Vector<CopingSkill> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_COPINGSKILL);
			out.flush();
			out.close();
			System.out.println("connection open ... object written Coping Skill");		//ND edited on 12th Oct
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<CopingSkill>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back Coping Skill");		//ND edited on 12th Oct
			
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return v;
	}

	
	// ND added on 25th Sep
	public Vector<Language> getLanguageList(){
		Vector<Language> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_LANGUAGE);
			out.flush();
			out.close();
			System.out.println("connection open ... object written Lanugage");			//ND edited on 12th Oct
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<Language>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back Language");		//ND edited on 12th Oct
			
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return v;
	}

	// ND added on 28th Sep
	public Vector<String> getNonFormalEducationList(){
//		Vector<NonFormalEducation> v = null;					// ND edited 08th Mar 16
		URLConnection con;
		Vector <String> vstr = new Vector<String>();
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_NONFORMALEDUCATION);
			out.flush();
			out.close();
			System.out.println("connection open ... object written non formal education");		// ND edited 12th Oct
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
//			v = (Vector<NonFormalEducation>)in.readObject();
			vstr = (Vector<String>) in.readObject();
			
			in.close();
			System.out.println("connection open ... size of object read back non formal education " + vstr.size() );	// ND edited 12th Oct
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
//		return v;
		return vstr;
	}
	
	// ND added on 12th Oct
	public Vector<String> getRecreationList() {
//		Vector<Recreation> v = null;
		Vector<String> vstr = new Vector<String>();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_RECREATION);
			out.flush();
			out.close();
			System.out.println("connection open ... object written recreation");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			//v = (Vector<Recreation>)in.readObject();
			vstr = (Vector<String>) in.readObject();
			in.close();
			System.out.println("connection open ... object read back recreation");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
//		return v;
		return vstr;
	}
	// ND added on 10th Jan 16
	
	public Vector<Organisation> getOrganisationList(){
		Vector<Organisation> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_ORGANISATION);
			out.flush();
			out.close();
			System.out.println("connection open ... object written organisation");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<Organisation>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back organisation");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return v;
	}
	
	// ND added on 11th Jan 16
	public Vector<CareHome> getCareHomeList(){
		Vector<CareHome> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_CAREHOME);
			out.flush();
			out.close();
			System.out.println("connection open ... object written care home");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<CareHome>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back care home");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return v;
	}

// start ND added on 19th Jan 16	
	public boolean updateCounselee(Counsellee cnslee){
	URLConnection con;
	
	try{
		con  = getConnection();
		ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
		out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_DETAILS);
		out.writeObject(cnslee);
		out.flush();
		out.close();
		System.out.println("connection open ... Counselee details updated");
		//Read results
		ObjectInputStream in = new ObjectInputStream(con.getInputStream());
		String sResult = (String)in.readObject();
		in.close();
		System.out.println("connection open ... Counselee details read back save"  +sResult);
		if(sResult.equals("SUCCESS"))
			return true;
		
	}catch (Exception e){
		e.printStackTrace();
		
	}
	
	return false;
	}
	
	public boolean updateAbuHist(AbuseIndivObj abuHist){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_ABUSEHIST);
			out.writeObject(abuHist);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Abuse details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Abuse details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateEduHist(EducationHistIndivObj eduHist){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_EDUHIST);
			out.writeObject(eduHist);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Educational details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateFamEnv(FamilyEnvIndivObj famEnv){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_FAMENV);
			out.writeObject(famEnv);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Family Environment details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Family Environment details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateCnslReln(CounseleeRelativeIndivObj cnslReln){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_CNSLRELN);
			out.writeObject(cnslReln);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	
	public boolean updatePhyHist(PhysicalHistIndivObj phyHist){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_PHYSICALHIST);
			out.writeObject(phyHist);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Physical history details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Physical history details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateMenStat(MentalStatusIndivObj menStat){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_MENTALSTAT);
			out.writeObject(menStat);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Mental Status details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Mental Status details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	
	public boolean updateSocHist(SocialHistIndivObj socHist){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_SOCIALHIST);
			out.writeObject(socHist);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Social History details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Social History details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateCnslStrength(StrenghtIndivObj cnslStrength){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_CNSLSTRENGTH);
			out.writeObject(cnslStrength);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Strength details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Strength details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateCnslSumm(SummaryIndivObj cnslSumm){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_CNSLSUMM);
			out.writeObject(cnslSumm);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Summary details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Summary details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	public boolean updateVocHist(VocationFinIndivObj vocHist){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_VOCHIST);
			out.writeObject(vocHist);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Vocational and Financial details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Vocational and Financial details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
	
	
	public boolean updateLegalHist(LegalHistIndivObj legalHist){
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_LEGAL);
			out.writeObject(legalHist);
			out.flush();
			out.close();
			System.out.println("connection open ... Counselee Legal details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Counselee Legal details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
	
		return false;
		}
// end ND added 19th Jan 16

	public boolean saveTSC40CheckLst(TSC40Obj tsc40Obj){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_CHKLST_TSC40);
			out.writeObject(tsc40Obj);
			out.flush();
			out.close();
			System.out.println("connection open ... object written save TSC40OBJ");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... object read back save tsc40"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public boolean saveTSC54CheckLst(TSC54Obj tsc54Obj){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_CHKLST_TSC54);
			out.writeObject(tsc54Obj);
			out.flush();
			out.close();
			System.out.println("connection open ... object written save TSC54OBJ");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... object read back save tsc54"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public Vector<MHSymptChkListObj> getTSCCheckLists(Hashtable htCriteria){
		Vector<MHSymptChkListObj> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_CHKLST_TSC);
			out.writeObject(htCriteria);
			out.flush();
			out.close();
			System.out.println("connection open ... object written gettscchecklists");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<MHSymptChkListObj>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back getchecklists");
			
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return v;
	}
	
	public Vector<SessionObj> getSessionList(Hashtable htCriteria){
		Vector<SessionObj> v = null;
		URLConnection con;
		
		try{
//			con  = getConnection();
//			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
//			out.writeObject(ACE_REQ_MH_GET_SESSION);
//			out.writeObject(htCriteria);
//			out.flush();
//			out.close();
//			System.out.println("connection open get session list... object written");
//			//Read results
//			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
//			v = (Vector<SessionObj>)in.readObject();
//			in.close();

 			v = testDataSessions();		
			System.out.println("connection open ... get session list object read back");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return v;
	}
	
	// start ND added 12th Jan 16
	public CounseleeHistoryObj getCounseleeHist(String caseNum) {
		URLConnection con;
		CounseleeHistoryObj cnslHist = new CounseleeHistoryObj();
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_CNSLEE_HISTORY);
			out.writeObject(caseNum);

			out.flush();
			out.close();
			System.out.println("connection open ... object written counselee caseNum ACE_REQ_MH_GET_CNSLEE_HISTORY");	// ND edited 16th Feb 16
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			cnslHist = (CounseleeHistoryObj)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back ACE_REQ_MH_GET_CNSLEE_HISTORY");		// ND edited 16th Feb 16
			
		}catch (Exception e){
			e.printStackTrace();
		}	
		return cnslHist;
	
	}



}
