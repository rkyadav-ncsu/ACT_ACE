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
import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CounseleeHistoryObj;
import com.act.common.Counsellee;
import com.act.common.CounselleeReportDataObject;
import com.act.common.CounselleeTFCBTStage;
import com.act.common.CnsleeTFCBTTask;
import com.act.common.CounsellingTherapy;
import com.act.common.EducationHistIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.GroupsObj;
import com.act.common.LegalHistIndivObj;
import com.act.common.MHSymptChkListObj;
import com.act.common.MentalStatusIndivObj;
import com.act.common.PersonName;
import com.act.common.PhysicalHistIndivObj;
import com.act.common.Pincode;
import com.act.common.Relationship;
import com.act.common.Religion;
import com.act.common.Addiction;			
import com.act.common.CopingSkill;			
import com.act.common.Language;				
import com.act.common.NonFormalEducation;	
import com.act.common.Recreation;	
import com.act.common.Organisation;
import com.act.common.CareHome;
import com.act.common.CounsellingSessionObj;
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



public class ACEConnector implements ACEDefines{
	
	private String servletContext = "MHS"; //JUST TESTING
	public static URL codeBase;
	private static ACEConnector connector = null;
	
	//THE TFCBT master data are retrieved once in the application
	//and stored here
	private static Hashtable<String, TFCBTStage> htStages = null;
	private static Hashtable<String, Vector> htStageTasks = new Hashtable<String, Vector>();
	private static Hashtable<String, Vector> htStageMilestone = new Hashtable<String, Vector>();
	
	private String currUsrName, currUsrPasswrd;
	
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
	
	public String getCurrUsrName() {
		return currUsrName;
	}

	public void setCurrUsrName(String currUsrName) {
		this.currUsrName = currUsrName;
	}

	public String getCurrUsrPasswrd() {
		return currUsrPasswrd;
	}

	public void setCurrUsrPasswrd(String currUsrPasswrd) {
		this.currUsrPasswrd = currUsrPasswrd;
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
	private void sendObjects(ObjectOutputStream out, Object[] objs) throws IOException{
//		out.writeObject(currUsrName);
//		out.writeObject(currUsrPasswrd);
		
		for (int i = 0; i < objs.length; i++) {
			out.writeObject(objs[i]);
		}

		out.flush();
		out.close();
	}
	
	public Vector<Counsellee> getCounseleeList(Hashtable htCriteria){
		Vector<Counsellee> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			Object[] objs = {ACE_REQ_MH_GET_COUNSELEE, htCriteria};
			sendObjects(out, objs);
			
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
	
//	private Vector<CnsleeTFCBTMileStone> testCnsleeStageMilestoneData(int stageId) {
//		Vector<CnsleeTFCBTMileStone> v;
//		v = new Vector<CnsleeTFCBTMileStone>();
//		
//		if (stageId == 1){
//			CnsleeTFCBTMileStone c = new CnsleeTFCBTMileStone();
//			c.setCnsleeId("IJM20120909-1234");
//			c.setMilestoneNotes("test notes for stage Milestone1");
////			c.setMilestoneCompleted(true);
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageMilestone Milestone = new TFCBTStageMilestone();
//			Milestone.setStageId(stageId);
//			Milestone.setTitle("Client is able to share a positive memory / tell a story");
//			c.setMilestoneObj(Milestone);
//			
//			v.add(c);
//			
//			CnsleeTFCBTMileStone c1 = new CnsleeTFCBTMileStone();
//			c1.setCnsleeId("IJM20120909-1234");
//			c1.setMilestoneNotes("test notes for stage Milestone1");
////			c1.setMilestoneCompleted(true);
//			c1.setDateCompleted("20100902");
//		
//			TFCBTStageMilestone Milestone1 = new TFCBTStageMilestone();
//			Milestone1.setStageId(stageId);
//			Milestone1.setTitle("Client agrees to continue treatment");
//			c1.setMilestoneObj(Milestone1);
//			
//			v.add(c1);
//		}else if (stageId == 2){
//			
//		}
//		return v;
//	}
	
//	private Vector<CounselleeTFCBTTask> testCnsleeStageTaskData(int stageId) {
//		Vector<CounselleeTFCBTTask> v;
//		v = new Vector<CounselleeTFCBTTask>();
//		
//		
//		
//		if (stageId == 1){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
//			c.setTaskNotes("test notes for stage Task1");
//			c.setTaskCompleted(true);
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			task.setSubTitles("* Client likes\\dislikes \n" +
//							"* Client Motivations \n"+
//							"* All About Me Worksheet \n"+
//							"* Lifeline \n"+
//							"* House/Tree/Person/Family");
//			c.setTaskObj(task);
//			
//			v.add(c);
//
//			CounselleeTFCBTTask c1 = new CounselleeTFCBTTask();
//			c1.setCnsleeId("IJM20120909-1234");
//			c1.setTaskNotes("test notes for stage Task1");
////			c1.setTaskCompleted(true);
//			c1.setDateCompleted("20100902");
//		
//			TFCBTStageTask task2 = new TFCBTStageTask();
//			task2.setStageId(stageId);
//			task2.setTitle("Discuss the purpose of counselling and steps of TF-CBT");
//			c1.setTaskObj(task2);
//			
//			v.add(c1);
//
//			CounselleeTFCBTTask c2 = new CounselleeTFCBTTask();
//			c2.setCnsleeId("IJM20120909-1234");
//			c2.setTaskNotes("test notes for stage Task1");
////			c2.setTaskCompleted(true);
//			c2.setDateCompleted("20100902");
//		
//			TFCBTStageTask task3= new TFCBTStageTask();
//			task3.setStageId(stageId);
//			task3.setTitle("Discuss confidentiality policy");
//			c2.setTaskObj(task3);
//			
//			v.add(c2);
//			
//			CounselleeTFCBTTask c3 = new CounselleeTFCBTTask();
//			c3.setTaskNotes("test notes for stage Task1");
////			c3.setTaskCompleted(true);
//			c3.setDateCompleted("20100902");
//		
//			TFCBTStageTask task4= new TFCBTStageTask();
//			task4.setStageId(stageId);
//			task4.setTitle("Complete initial trauma symptom assessment");
//			c3.setTaskObj(task4);
//			
//			v.add(c3);
//		}else if (stageId == 2){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
//			c.setTaskNotes("test notes for stage Task");
////			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Provide Psycho education about abuse");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 3){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//
//		}else if (stageId == 4){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 5){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 6){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 7){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 8){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 9){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}else if (stageId == 10){
//			CounselleeTFCBTTask c = new CounselleeTFCBTTask();
//			c.setCnsleeId("IJM20120909-1234");
////			c.setTaskNotes("test notes for stage Task");
//			c.setDateCompleted("20100902");
//		
//			TFCBTStageTask task = new TFCBTStageTask();
//			task.setStageId(stageId);
//			task.setTitle("Establish Rapport");
//			c.setTaskObj(task);
//			
//			v.add(c);
//		}
//		return v;
//	}
	
	private Vector<CounsellingSessionObj> testDataSessions() {
		Vector<CounsellingSessionObj> v;
		v = new Vector<CounsellingSessionObj>();
		CounsellingSessionObj c = new CounsellingSessionObj();
		c.setSessionDate("12/06/2008");
		c.setStatus("OPEN");
//		c.?("TFCBT");
		c.setSessionContents("The person is in severe trauma");
		
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
//	public Vector<Relationship> getRelationshipList(){
	public Vector<String> getRelationshipList(){

		Vector<String> v = null;
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
			v = (Vector<String>)in.readObject();
			
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
	
	public Vector<String> getOrganisationList(){
		Vector<String> v = null;
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
			v = (Vector<String>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back organisation");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return v;
	}
	
	// ND added on 11th Jan 16
	public Vector<String> getCareHomeList(){
		Vector<String> v = null;
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
			v = (Vector<String>)in.readObject();
			
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
	
	public boolean updateCnslReln(Vector cnslReln, String caseNum){											
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CNSLEE_CNSLRELN);
			out.writeObject(caseNum);														
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
	
	public boolean updateTSC40CheckLst(TSC40Obj tsc40Obj){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CHKLST_TSC40);
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
	
	public boolean deleteTSC40CheckLst(TSC40Obj tsc40Obj){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_CHKLST_TSC40);
			out.writeObject(tsc40Obj);
			out.flush();
			out.close();
			System.out.println("connection open ... object written del TSC40OBJ");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open del ... object read back tsc40"  +sResult);
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
	
	public boolean updateTSC54CheckLst(TSC54Obj tsc54Obj){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_CHKLST_TSC54);
			out.writeObject(tsc54Obj);
			out.flush();
			out.close();
			System.out.println("connection open update ... object written save TSC54OBJ");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open update ... object read back save tsc54"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public boolean deleteTSC54CheckLst(TSC54Obj tsc54Obj){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_CHKLST_TSC54);
			out.writeObject(tsc54Obj);
			out.flush();
			out.close();
			System.out.println("connection open update ... object written del TSC54OBJ");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open update ... object read back del tsc54"  +sResult);
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
	
//	public Vector<CounsellingSessionObj> getSessionList(Hashtable htCriteria){
//		Vector<CounsellingSessionObj> v = null;
//		URLConnection con;
//		
//		try{
////			con  = getConnection();
////			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
////			out.writeObject(ACE_REQ_MH_GET_SESSION);
////			out.writeObject(htCriteria);
////			out.flush();
////			out.close();
////			System.out.println("connection open get session list... object written");
////			//Read results
////			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
////			v = (Vector<SessionObj>)in.readObject();
////			in.close();
//
// 			v = testDataSessions();		
//			System.out.println("connection open ... get session list object read back");
//			
//		}catch (Exception e){
//			e.printStackTrace();
//			
//		}
//		
//		return v;
//	}
//	
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


//	public Vector<CounselleeTFCBTTask> getCnsleeTFCBTStageTasks(int stageNum){
//		Hashtable htStages = getTFCBTStages();
//		
//		Vector<CounselleeTFCBTTask> v = null;
//		URLConnection con;
//		
//		try{
////			con  = getConnection();
////			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
////			out.writeObject(ACE_REQ_MH_GET_CNLSEE_TFCBT_STAGE_TASK);
////			out.writeObject(htCriteria);
////			out.flush();
////			out.close();
////			//Read results
////			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
////			v = (Vector<CounselleeTFCBTTask>)in.readObject();
////			
////			in.close();
////			
//			//test start
//
// 			v = testCnsleeStageTaskData(1);		
//
//	
// 			Integer integer;
//			integer = new Integer(1234);
//			
//		}catch (Exception e){
//			e.printStackTrace();
//			
//			
//		}
//		
//		return v;
//	}
//
	public Hashtable<String, CounselleeTFCBTStage> getCounselleeTFCBTStages(String cnsleeId){
		Hashtable<String, CounselleeTFCBTStage> htStages = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_CNLSEE_TFCBT_STAGES);
			out.writeObject(cnsleeId);
			out.flush();
			out.close();
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			htStages = (Hashtable<String, CounselleeTFCBTStage>)in.readObject();
			
			in.close();
			
			System.out.println("get cnslee stages " + htStages);
			//Set the stageObject in each
			if(htStages != null){
				
				Hashtable<String, TFCBTStage> htTFCBTStages = getTFCBTStages();
				
				for (int i = 1; i < 11; i++) {
					
					CounselleeTFCBTStage cnsleeStage = htStages.get(String.valueOf(i));
					System.out.println("htTFCBTStages.get(String.valueOf(i)) " + htTFCBTStages.get(String.valueOf(i)));
					cnsleeStage.setTfcbtStageObj(htTFCBTStages.get(String.valueOf(i)));
				}
					
					
				
			}
			
			//Set the stageObject in each
			//test start

//			CounselleeTFCBTStage stage1 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(1), stage1);
//			stage1.setStageNumber(1);
//			stage1.setTitle("Gathering");
//			stage1.setObjective("To build relationship with the client, while completing an assignment.");
// 			Vector<CounselleeTFCBTTask> vTasks = testCnsleeStageTaskData(1);
// 			stage1.setStageTasks(vTasks);
// 			
// 			Vector<CnsleeTFCBTMileStone> vMilestones = testCnsleeStageMilestoneData(1);
// 			stage1.setStageMileStones(vMilestones);
//
//			CounselleeTFCBTStage stage2 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(2), stage2);
//			stage2.setStageNumber(2);
//			stage2.setTitle("Learning");
//			stage2.setObjective("To provide accurate information to the client that will normalize feelings and reactions to trauma.");
// 			Vector<CounselleeTFCBTTask> vTasks2 = testCnsleeStageTaskData(2);
// 			stage2.setStageTasks(vTasks2);
//
//			CounselleeTFCBTStage stage3 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(3), stage3);
//			stage3.setStageNumber(3);
//			stage3.setTitle("Helping");
//			stage3.setObjective("To teach caregivers how to interact with clients in a positive way.");
// 			Vector<CounselleeTFCBTTask> vTasks3 = testCnsleeStageTaskData(3);
// 			stage3.setStageTasks(vTasks3);
//
//			CounselleeTFCBTStage stage4 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(4), stage4);
//			stage4.setStageNumber(4);
//			stage4.setTitle("Relaxing");
//			stage4.setObjective("To teach client various coping skills and managing negative feelings and thoughts.");
// 			Vector<CounselleeTFCBTTask> vTasks4 = testCnsleeStageTaskData(4);
// 			stage4.setStageTasks(vTasks4);
//
//			CounselleeTFCBTStage stage5 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(5), stage5);
//			stage5.setStageNumber(5);
//			stage5.setTitle("Feeling");
//			stage5.setObjective("To empower the client to identify and appropriately express emotion and be aware of emotional triggers.");
// 			Vector<CounselleeTFCBTTask> vTasks5 = testCnsleeStageTaskData(5);
// 			stage5.setStageTasks(vTasks5);
//
//			CounselleeTFCBTStage stage6 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(6), stage6);
//			stage6.setStageNumber(6);
//			stage6.setTitle("Thinking");
//			stage6.setObjective("To Teach the relationship between thoughts, feelings and action.");
// 			Vector<CounselleeTFCBTTask> vTasks6 = testCnsleeStageTaskData(6);
// 			stage6.setStageTasks(vTasks6);
//
//			CounselleeTFCBTStage stage7 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(7), stage7);
//			stage7.setStageNumber(7);
//			stage7.setTitle("Sharing the Trauma Narrative");
//			stage7.setObjective("To integrate the client's trauma storyas part of her story rather than that her identity.");
// 			Vector<CounselleeTFCBTTask> vTasks7 = testCnsleeStageTaskData(7);
// 			stage7.setStageTasks(vTasks7);
//
//			CounselleeTFCBTStage stage8 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(8), stage8);
//			stage8.setStageNumber(8);
//			stage8.setTitle("Evaluating");
//			stage8.setObjective("To apply the life\\coping skills learned in previous sessionsto integrate the trauma as a part of the client's life story.");
// 			Vector<CounselleeTFCBTTask> vTasks8 = testCnsleeStageTaskData(8);
// 			stage8.setStageTasks(vTasks8);
//
//			CounselleeTFCBTStage stage9 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(9), stage9);
//			stage9.setStageNumber(9);
//			stage9.setTitle("Sharing the Trauma narrative - Part 2");
//			stage9.setObjective("To decrease shame and increase healing through sharing the trauma story with another person.");
// 			Vector<CounselleeTFCBTTask> vTasks9 = testCnsleeStageTaskData(9);
// 			stage9.setStageTasks(vTasks9);
//
//			CounselleeTFCBTStage stage10 = new CounselleeTFCBTStage();
//			htStages.put(new Integer(10), stage10);
//			stage10.setStageNumber(10);
//			stage10.setTitle("Living");
//			stage10.setObjective("To identify and minimize trauma-avoidance areas, increase personal safety and set future goals.");
// 			Vector<CounselleeTFCBTTask> vTasks10 = testCnsleeStageTaskData(10);
// 			stage10.setStageTasks(vTasks10);

			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return htStages;
	}

	public Hashtable<String, TFCBTStage> getTFCBTStages(){
		
		if (htStages != null){
			return htStages;
		}
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_TFCBT_STAGES);
			out.flush();
			out.close();
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			htStages = (Hashtable<String, TFCBTStage>)in.readObject();
			
			in.close();
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return htStages;
	}

	public Vector<TFCBTStageTask> getTFCBTStageTasks(int stageNum){
		Vector<TFCBTStageTask> vStageTasks = null;
		
		if (htStageTasks != null){
			vStageTasks = htStageTasks.get(String.valueOf(stageNum));
		}
		
		if (vStageTasks != null){
			return vStageTasks;
		}
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_TFCBT_STAGE_TASKS);
			out.writeObject(String.valueOf(stageNum));
			
			out.flush();
			out.close();
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			vStageTasks = (Vector<TFCBTStageTask>)in.readObject();
			
			//persist it in the app
			if (vStageTasks != null){
				System.out.println("vStageTasks.size()  " + vStageTasks.size());
				
				htStageTasks.put(String.valueOf(stageNum), vStageTasks);
			}
			
			in.close();
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return vStageTasks;
	}
	
	public TFCBTStageMilestone getTFCBTStageMilestone(int stageNum, int milestoneId){
		
		Vector <TFCBTStageMilestone> vMilestones = getTFCBTStageMilestones(stageNum);
		
		if (vMilestones == null) 
			return null;
		
		for (int i = 0; i < vMilestones.size(); i++) {
			TFCBTStageMilestone milestone = vMilestones.elementAt(i);
			
			if (milestone.getMilestoneId() == milestoneId)
				return milestone;
			
		}
		return null;
	}
	
	public TFCBTStageTask getTFCBTStageTask(int stageNum, int taskId){
		
		Vector <TFCBTStageTask> vTasks = getTFCBTStageTasks(stageNum);
		
		if (vTasks == null) 
			return null;
		
		for (int i = 0; i < vTasks.size(); i++) {
			TFCBTStageTask task = vTasks.elementAt(i);
			
			if (task.getTaskId() == taskId)
				return task;
			
		}
		return null;
	}
	
	public Vector<TFCBTStageMilestone> getTFCBTStageMilestones(int stageNum){
		Vector<TFCBTStageMilestone> vStageMilestones = null;
		
		if (htStageMilestone != null){
			vStageMilestones = htStageMilestone.get(String.valueOf(stageNum));
		}
		
		if (vStageMilestones != null){
			return vStageMilestones;
		}
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_TFCBT_STAGE_MILESTONES);
			out.writeObject(String.valueOf(stageNum));
			
			out.flush();
			out.close();
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			vStageMilestones = (Vector<TFCBTStageMilestone>)in.readObject();
			
			//persist it in the app
			if (vStageMilestones != null)
				htStageMilestone.put(String.valueOf(stageNum), vStageMilestones);
			
			in.close();
			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}
		
		return vStageMilestones;
	}

	public String saveCnsleeTFCBTTask(CnsleeTFCBTTask cnsleeTaskObj){
		
		String sResult = "Error";
		
		if (cnsleeTaskObj == null)
			return null;
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_CNSLEE_TFCBT_TASK);
			out.writeObject(cnsleeTaskObj);
			
			out.flush();
			out.close();
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			sResult = (String)in.readObject();
			
			in.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return sResult;
	}

	public String saveCnsleeTFCBTMilestone(CnsleeTFCBTMileStone cnsleeMilestoneObj){
		
		String sResult = "Error";
		
		if (cnsleeMilestoneObj == null)
			return null;
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_CNSLEE_TFCBT_MILESTONE);
			out.writeObject(cnsleeMilestoneObj);
			
			out.flush();
			out.close();
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			sResult = (String)in.readObject();
			
			in.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return sResult;
	}

	// start ND added 25th July 16
	public boolean saveAddiction(Vector vAddict) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_ADDICT);
			out.writeObject(vAddict);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save addiction ACE_REQ_MH_SAVE_ADDICTION");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save addiction read back SAVE_ADDICTION " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	
	public boolean saveCopingSkill(Vector vCopSkill) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_COPSKILL);
			out.writeObject(vCopSkill);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save coping skill ACE_REQ_MH_SAVE_COPSKILL");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save coping skill read back SAVE_COPSKILL " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 

	public boolean saveLanguage(Vector vLanguage) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_LANGUAGE);
			out.writeObject(vLanguage);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save lanugage ACE_REQ_MH_SAVE_LANGUAGE");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save language read back SAVE_LANGUAGE " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 

	public boolean saveReligion(Vector vReligion) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_RELIGION);
			out.writeObject(vReligion);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save religion ACE_REQ_MH_SAVE_RELIGION");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save religion read back SAVE_RELIGION " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 

	public boolean saveRelationship(Vector vRelate) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_RELATE);
			out.writeObject(vRelate);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save relationship ACE_REQ_MH_SAVE_RELATE");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save relationship read back SAVE_RELATE " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 

	public boolean saveOrgn(Organisation orgnObj){
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_ORGN);
			out.writeObject(orgnObj);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save service type ACE_REQ_MH_SAVE_ORGN");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save service type read back SAVE_ORGN " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
		
	}
	
	public boolean saveHobby(Vector vHobby) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_HOBBY);
			out.writeObject(vHobby);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save hobby ACE_REQ_MH_SAVE_HOBBY");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save language read back SAVE_HOBBY " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	public boolean saveNFEdu(Vector vNFEdu) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_NFEDU);
			out.writeObject(vNFEdu);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save hobby ACE_REQ_MH_SAVE_NFEDU");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save non formal education read back SAVE_NFEDU " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	// ND added on 05th Aug 16
	public Vector<String> getServiceTypeList() {
		Vector<String> vstr = new Vector<String>();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_SERVICETYPE);
			out.flush();
			out.close();
			System.out.println("connection open ... object written service type");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			//v = (Vector<Recreation>)in.readObject();
			vstr = (Vector<String>) in.readObject();
			in.close();
			System.out.println("connection open ... object read back service type");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		return vstr;
	}
	
	// start ND added 06th Aug 16
	public boolean saveServiceType(Vector vServType) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_SERVICETYPE);
			out.writeObject(vServType);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save service type ACE_REQ_MH_SAVE_SERVICETYPE");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save service type read back SAVE_SERVICETYPE " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	
	public boolean saveCareHome(Vector vKHome) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_CAREHOME);
			out.writeObject(vKHome);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save service type ACE_REQ_MH_SAVE_CAREHOME");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save service type read back SAVE_CAREHOME " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	// end ND added on 06th Aug 16
	
	// ND added on 2nd Sep 16
	public Organisation getOrgnDetails(Integer orgnID){								
		Organisation orgObj = new Organisation();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_ORGN_DETAILS);
			out.writeObject(orgnID);
			out.flush();
			out.close();
			System.out.println("connection open ... object written organisation details");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			orgObj  = (Organisation)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back organisation details");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		return orgObj;
	}
	
	public String delOrganisations(Vector vOrgnIDs){								// ND added on 12th Sep 16							
		String delOrgResult = new String();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_ORGANISATIONS);
			out.writeObject(vOrgnIDs);
			out.flush();
			out.close();
			System.out.println("connection open ... deleted organisations");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			delOrgResult  = (String)in.readObject();
			
			in.close();
			System.out.println("connection open ... delete organisations result");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		return delOrgResult;
	}

	public boolean updateOrgn(Organisation orgnObj){										// ND added on 09th Sep 16
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_ORGN);
			out.writeObject(orgnObj);
			out.flush();
			out.close();
			System.out.println("connection open ... Organisation details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... Organisation details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
		}
	
	//Get Organisation ID and Name list
	
	public Vector<Organisation> getOrgnIDName(){				// ND added on 12th Sep 16
//		Vector<OrgnIDNameObj> v = null;
		Vector<Organisation> v = new Vector<Organisation>();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_ORGN_ALLIDNAME);
			out.flush();
			out.close();
			System.out.println("connection open ... object written");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
//			v = (Vector<OrgnIDNameObj>)in.readObject();
			v = (Vector<Organisation>)in.readObject();
			
			in.close();
			System.out.println("connection open ... object read back");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		for (int x = 0; x < v.size(); x++){
			System.out.println("Organisation " + v.elementAt(x).getOrgnName());
		}
		return v;
	}

	// ND added on 25th Aug 16
	public Vector<Pincode> getPinInfo() {
		Vector<Pincode> vPin = new Vector<Pincode>();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_PININFO);
			out.flush();
			out.close();
			System.out.println("connection open ... object written pincode info");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			//v = (Vector<Recreation>)in.readObject();
			vPin = (Vector<Pincode>) in.readObject();
			in.close();
			System.out.println("connection open ... object read back pincode info");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		return vPin;
		
	}
	
	public String saveCounsellingSession(CounsellingSessionObj sessionObj){
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_COUNSELLING_SESSION);
			out.writeObject(sessionObj);
			out.flush();
			out.close();
			
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String result  = (String)in.readObject();
			
			in.close();
			
			return result;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return "ERROR";
	}
	
	public boolean updateCounsellingSession(CounsellingSessionObj sessionObj){
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_COUNSELLING_SESSION);
			out.writeObject(sessionObj);
			out.flush();
			out.close();
			
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String result  = (String)in.readObject();
			
			in.close();
			
			if(result.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
		
	}

	public boolean deleteCounsellingSession(CounsellingSessionObj sessionObj){
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_COUNSELLING_SESSION);
			out.writeObject(sessionObj);
			out.flush();
			out.close();
			
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String result  = (String)in.readObject();
			
			in.close();
			
			if(result.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	public Vector<CounsellingSessionObj> getCounsellingSession(Hashtable htOptions){
		
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_COUNSELLING_SESSION);
			out.writeObject(htOptions);
			out.flush();
			out.close();
	
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			Vector<CounsellingSessionObj> vSessionObjs = (Vector<CounsellingSessionObj>) in.readObject();
			in.close();
			
			return vSessionObjs;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

	public Vector<CounselleeReportDataObject> getCounsellingReports(Vector<String> vColNames, 
															Hashtable<String,String> htOptions){
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_CNSLEE_REPORTS);
			
			out.writeObject(vColNames);
			out.writeObject(htOptions);
			
			out.flush();
			out.close();
	
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			Vector<CounselleeReportDataObject> vReportData= (Vector<CounselleeReportDataObject>) in.readObject();
			
			in.close();
			
			return vReportData;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

	// start ND added on 26th Sep 16
	public boolean saveCounselMeth(Vector vCounslMeth) {
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_COUNSELINGTHERAPY);
			out.writeObject(vCounslMeth);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save addiction ACE_REQ_MH_SAVE_COUNSELINGTHERAPY");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save addiction read back SAVE_COUNSELINGTHERAPY " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	
	public Vector<CounsellingTherapy> getCounselingTherapy(){
		Vector<CounsellingTherapy> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_COUNSELINGTHERAPY);
			out.flush();
			out.close();
			System.out.println("connection open ... object written Counseling Therapy");		
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<CounsellingTherapy>)in.readObject();
			System.out.println("Counseling Therapy size: " + v.size());
			in.close();
			System.out.println("connection open ... object read back Counseling Therapy");	
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return v;
	}
	
	// end ND added on 26th Sep 16
	
	// ND added on 05th Oct 16
	public Vector<User> getUserInfo(String userID){	
		Vector<User> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_USERPROFILE);
			out.writeObject(userID);
			out.flush();
			out.close();
			System.out.println("connection open ... object written User Profile");		
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<User>)in.readObject();
			System.out.println("User Profile size: " + v.size());
			in.close();
			System.out.println("connection open ... object read back User Profile");	
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return v;
	}
	
	// ND added on 07th Oct 16
	public boolean saveUserInfo(User userObj, boolean grpChanged){
		
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_USERINFO);
			out.writeObject(userObj);
			out.writeBoolean(grpChanged);
			out.flush();
			out.close();
			System.out.println("connection open .... save user informatiion ACE_REQ_MH_SAVE_USERINFO");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save user information read back SAVE_USERINFO " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	
	// ND added on 10th Oct 16
	public Vector<User> getUsersAll(){	
		Vector<User> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_USERSALL);
			out.flush();
			out.close();
			System.out.println("connection open ... object written User Profile");		
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<User>)in.readObject();
			System.out.println("All users size: " + v.size());
			in.close();
			System.out.println("connection open ... object read back All users");	
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return v;
	}

	// ND added on 12th Oct 16
	public boolean saveNewUser(User userObj){
		
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_NEWUSER);
			out.writeObject(userObj);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save new user ACE_REQ_MH_SAVE_NEWUSER");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save user information read back SAVE_NEWUSER " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 
	
	// ND added on 16th Oct 16
	public String delUsers(String userID){										
		String delUserResult = new String();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_USER);
			System.out.println("User ID being sent to server for del: " + userID);
			out.writeObject(userID);
			out.flush();
			out.close();
			System.out.println("connection open ... deleted users");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			delUserResult  = (String)in.readObject();
			
			in.close();
			System.out.println("connection open ... delete users result");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		return delUserResult;
	}


	
	// ND added on 14th Oct 16
	public Vector<GroupsObj> getUserGrpsAll(){	
		Vector<GroupsObj> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_GRPSALL);
			out.flush();
			out.close();
			System.out.println("connection open ... object written User Group");		
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<GroupsObj>)in.readObject();
			System.out.println("All user groups size: " + v.size());
			in.close();
			System.out.println("connection open ... object read back All user groups");	
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return v;
	}

	public Vector<GroupsObj> getGroupInfo(String grpName){	
		Vector<GroupsObj> v = null;
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_GET_GROUPINFO);
			out.writeObject(grpName);
			System.out.println("Group ID being sent to server to get group name " + grpName);
			out.flush();
			out.close();
			System.out.println("connection open ... object written A Users Group");		
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			v = (Vector<GroupsObj>)in.readObject();
			System.out.println("All user groups size: " + v.size());
			in.close();
			System.out.println("connection open ... object read back A users groups");	
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return v;
	}

	
	// ND added on 15th Oct 16
	public boolean saveNewMstUserGroup(GroupsObj userGrpObj){
		
		URLConnection con;
		
		try {
			con = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_SAVE_USERGRP);
			out.writeObject(userGrpObj);
			
			out.flush();
			out.close();
			System.out.println("connection open .... save new user ACE_REQ_MH_SAVE_USERGRP");
			// Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String) in.readObject();
			in.close();
			System.out.println("connection opeen ...... save user group information read back SAVE_USERGRP " + sResult);
			if (sResult.equals("SUCCESS")) 
				return true;
		} catch(Exception e){
			e.printStackTrace();
		  }
		  return false;
	} 

	public boolean updateGroupInfo(GroupsObj usrGrpObj, String oldGroupName){										// ND added on 09th Sep 16
		URLConnection con;
	
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_UPDATE_GROUP);
			out.writeObject(usrGrpObj);
			out.writeObject(oldGroupName);
			out.flush();
			out.close();
			System.out.println("connection open ... User Group details updated");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			String sResult = (String)in.readObject();
			in.close();
			System.out.println("connection open ... User Group details read back save"  +sResult);
			if(sResult.equals("SUCCESS"))
				return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
		}
	
//	public Vector<UsersandGroupsObj> getTheUsersGrp(String userID){	
//		Vector<UsersandGroupsObj> v = null;
//		URLConnection con;
//		
//		try{
//			con  = getConnection();
//			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
//			out.writeObject(ACE_REQ_MH_GET_THEUSERSGRP);
//			out.writeObject(userID);
//			out.flush();
//			out.close();
//			System.out.println("connection open ... object written User Profile");		
//			//Read results
//			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
//			v = (Vector<UsersandGroupsObj>)in.readObject();
//			System.out.println("User Profile size: " + v.size());
//			in.close();
//			System.out.println("connection open ... object read back User Profile");	
//			
//			
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		
//		return v;
//	}
	
	
	public String delGroups(String grpName){										
		String delGroupsResult = new String();
		URLConnection con;
		
		try{
			con  = getConnection();
			ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
			out.writeObject(ACE_REQ_MH_DEL_GROUPS);
			System.out.println("Group being sent to server for del: " + grpName);
			out.writeObject(grpName);
			out.flush();
			out.close();
			System.out.println("connection open ... deleted group");
			//Read results
			ObjectInputStream in = new ObjectInputStream(con.getInputStream());
			delGroupsResult  = (String)in.readObject();
			
			in.close();
			System.out.println("connection open ... delete group result");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		return delGroupsResult;
	}

}

