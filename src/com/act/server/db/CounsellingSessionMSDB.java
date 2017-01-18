package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import com.act.common.ACEDefines;
import com.act.common.CounsellingSessionObj;


public class CounsellingSessionMSDB extends CounsellingSessionDB {

	private Random ran = new Random();
	private static CounsellingSessionMSDB counsellingSessionDB;
	
	public static CounsellingSessionMSDB getInstance(){
		if (counsellingSessionDB == null){
			counsellingSessionDB = new CounsellingSessionMSDB();
		}
		return counsellingSessionDB;
	}
	
	@Override
	public String saveCounsellingSession(CounsellingSessionObj sessionObj) {
		Connection con = null; 

		StringBuffer sql= new StringBuffer("Insert into " +
					DB_TBL_COUNSELLING_SESSION);
		
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			
			String counsellingSessionId = generateId();

			System.out.println("counselling session id generated************************** " + counsellingSessionId);
			//create the insert query statement values
			sql.append(" values (");
			sql.append(counsellingSessionId);					
			sql.append(",");
			sql.append("'" +sessionObj.getCaseNumber() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getCnslrId()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSessionDate()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getLocation() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getStartTime() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getDuration() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSessionSetting() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSesionObjective() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSessionContents()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSessionFollowupPrep() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getCnslrComments() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getTherapyName() +"'");
			sql.append(",");
			sql.append("'" +sessionObj.getStatus()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getCaseManagement()+"'");
			sql.append(",");
			sql.append(sessionObj.getOpenToCnsling());
			sql.append(",");
			sql.append(sessionObj.getOpentTofutureOutsidePros());
			sql.append(",");
			sql.append(sessionObj.getOpenTogrpHome());
			sql.append(",");
			sql.append("'" +sessionObj.getAppetite()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSleep()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getRapport()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getAppearance()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getMood()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getAffect()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getThought()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getPerceptDisturb()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getOrientation()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getInsight()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getJudgement()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getAppetiteComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getSleepComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getRapportComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getAppearanceComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getMoodComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getAffectComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getThoughtComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getPerceptDisturbComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getOrientationComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getInsightComments()+"'");
			sql.append(",");
			sql.append("'" +sessionObj.getJudgementComments()+"'");
			sql.append(" )");
			
			System.out.println("insert query string : " + sql.toString());
			
			Statement st = con.createStatement();
			st.executeUpdate(sql.toString());
			
			con.close();
			
			//returning the auto generated ID
			return counsellingSessionId;
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		return "ERROR";
	}

	private String generateId() {
		String counsellingSessionId;
		
		Date date = new Date();
		DateFormat dtformat = new SimpleDateFormat("hhmmss");								// ND edited on 1st Dec
		counsellingSessionId = dtformat.format(date) +ran.nextInt(12);
		return counsellingSessionId;
	}

	@Override
	public String updateCounsellingSession(CounsellingSessionObj sessionObj) {
		Connection con = null; 
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_COUNSELLING_SESSION + " SET ");

		try{
			con = getConnection();

			sql.append(DB_COL_CNSLING_SESSION_DATE);
			sql.append(" ='" + sessionObj.getSessionDate() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_LOCATION);
			sql.append(" ='" + sessionObj.getLocation() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_START_TIME);
			sql.append(" ='" + sessionObj.getStartTime() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_DURATION);
			sql.append(" ='" + sessionObj.getDuration() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_SETTING);
			sql.append(" ='" + sessionObj.getSessionSetting() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_OBJECTIVE);
			sql.append(" ='" + sessionObj.getSesionObjective()+"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_CONTENTS_AREAS_CONCERN);
			sql.append(" ='" + sessionObj.getSessionContents()+"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_FOLLOW_UP_PREP);
			sql.append(" ='" + sessionObj.getSessionFollowupPrep()+"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_THERAPY);
			sql.append(" ='" + sessionObj.getTherapyName() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_STATUS);
			sql.append(" ='" + sessionObj.getStatus() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_CASE_MGMT);
			sql.append(" ='" + sessionObj.getCaseManagement() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_CNSLR_COMMENTS);
			sql.append(" ='" + sessionObj.getCnslrComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_OPEN_TO_CNSLING);
			sql.append(" =" + sessionObj.getOpenToCnsling() );
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_OPEN_TO_FUTURE_OUTSIDE);
			sql.append(" =" + sessionObj.getOpentTofutureOutsidePros() );
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_OPEN_TO_GRP_HOME);
			sql.append(" =" + sessionObj.getOpenTogrpHome() );
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_APPETITE);
			sql.append(" ='" + sessionObj.getAppetite() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_SLEEP);
			sql.append(" ='" + sessionObj.getSleep() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_RAPPORT);
			sql.append(" ='" + sessionObj.getRapport() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_APPEAR);
			sql.append(" ='" + sessionObj.getAppearance() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_MOOD);
			sql.append(" ='" + sessionObj.getMood() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_AFFECT);
			sql.append(" ='" + sessionObj.getAffect() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_THOUGHT);
			sql.append(" ='" + sessionObj.getThought() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_DISTURBANCES);
			sql.append(" ='" + sessionObj.getPerceptDisturb() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_ORIENTATION);
			sql.append(" ='" + sessionObj.getOrientation() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_INSIGHT);
			sql.append(" ='" + sessionObj.getInsight() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_JUDGEMENT);
			sql.append(" ='" + sessionObj.getJudgement() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_APPETITE_COMMENTS);
			sql.append(" ='" + sessionObj.getAppetiteComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_SLEEP_COMMENTS);
			sql.append(" ='" + sessionObj.getSleepComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_RAPPORT_COMMENTS);
			sql.append(" ='" + sessionObj.getRapportComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_APPEAR_COMMENTS);
			sql.append(" ='" + sessionObj.getAppearanceComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_MOOD_COMMENTS);
			sql.append(" ='" + sessionObj.getMoodComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_AFFECT_COMMENTS);
			sql.append(" ='" + sessionObj.getAffectComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_THOUGHT_COMMENTS);
			sql.append(" ='" + sessionObj.getThoughtComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_DISTURBANCES_COMMENTS);
			sql.append(" ='" + sessionObj.getPerceptDisturbComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_ORIENTATION_COMMENTS);
			sql.append(" ='" + sessionObj.getOrientationComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_INSIGHT_COMMENTS);
			sql.append(" ='" + sessionObj.getInsightComments() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLING_SESSION_JUDGEMENT_COMMENTS);
			sql.append(" ='" + sessionObj.getJudgementComments() +"'");
			sql.append(" WHERE ");
			sql.append(DB_COL_CNSLING_SESSION_CASE_ID);
			sql.append(" = '");
			sql.append(sessionObj.getCaseNumber() + "'");
			
			System.out.println("update counselling session query string : " + sql.toString());
			
			
			Statement st = con.createStatement();
			int n = st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS"; //TODO CONVERT TOdefines
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		return "ERROR";
	}

	@Override
	public Vector<CounsellingSessionObj> getCounsellingSessionObjs(
			Hashtable<String, String> htOptions) {
		
		Connection con = null;
		
		Vector<CounsellingSessionObj> vSessionObjs = new Vector<CounsellingSessionObj>();
		String sql= "Select * from " +
				DB_TBL_COUNSELLING_SESSION;

		//form the where condition
		StringBuffer sbWhere = new StringBuffer();
		if (htOptions != null){
			if (htOptions.containsKey(ACEDefines.COUNSELLE_CASE_ID)){
				String val = htOptions.get(ACEDefines.COUNSELLE_CASE_ID);
				
				if (sbWhere.length() <1)
					sbWhere.append(" Where ");
				else
					sbWhere.append(" And ");
				
				sbWhere.append(likeClause(DB_COL_CNSLING_SESSION_CASE_ID, val));
			}
			if (htOptions.containsKey(ACEDefines.COUNSELLOR_ID)){
				String val = htOptions.get(ACEDefines.COUNSELLOR_ID);
				
				if (sbWhere.length() <1)
					sbWhere.append(" Where ");
				else
					sbWhere.append(" And ");
				
				sbWhere.append(likeClause(DB_COL_CNSLING_SESSION_CNSLR_ID, val));
			}
		}

		if (sbWhere.length() > 0){
			sql +=sbWhere.toString();
			
		}
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				CounsellingSessionObj sessionObj = new CounsellingSessionObj();
				
				sessionObj.setCnslingSessionId(rs.getString(DB_COL_CNSLING_SESSION_ID));
				sessionObj.setCaseNumber(rs.getString(DB_COL_CNSLING_SESSION_CASE_ID));
				sessionObj.setCnslrId(rs.getString(DB_COL_CNSLING_SESSION_CNSLR_ID));
				sessionObj.setSessionDate(rs.getString(DB_COL_CNSLING_SESSION_DATE));
				sessionObj.setLocation(rs.getString(DB_COL_CNSLING_SESSION_LOCATION));
				sessionObj.setStartTime(rs.getString(DB_COL_CNSLING_SESSION_START_TIME));
				sessionObj.setDuration(rs.getString(DB_COL_CNSLING_SESSION_DURATION));
				sessionObj.setSessionSetting(rs.getString(DB_COL_CNSLING_SESSION_SETTING));
				sessionObj.setSesionObjective(rs.getString(DB_COL_CNSLING_SESSION_OBJECTIVE));
				sessionObj.setSessionContents(rs.getString(DB_COL_CNSLING_SESSION_CONTENTS_AREAS_CONCERN));
				sessionObj.setSessionFollowupPrep(rs.getString(DB_COL_CNSLING_SESSION_FOLLOW_UP_PREP));
				sessionObj.setTherapy(rs.getString(DB_COL_CNSLING_SESSION_THERAPY));
				sessionObj.setStatus(rs.getString(DB_COL_CNSLING_SESSION_STATUS));
				sessionObj.setCaseManagement(rs.getString(DB_COL_CNSLING_SESSION_CASE_MGMT));
				
				sessionObj.setCnslrComments(rs.getString(DB_COL_CNSLING_SESSION_CNSLR_COMMENTS));
				sessionObj.setOpenToCnsling(rs.getString(DB_COL_CNSLING_SESSION_OPEN_TO_CNSLING));
				sessionObj.setOpentTofutureOutsidePros(rs.getString(DB_COL_CNSLING_SESSION_OPEN_TO_FUTURE_OUTSIDE));
				sessionObj.setOpenTogrpHome(rs.getString(DB_COL_CNSLING_SESSION_OPEN_TO_GRP_HOME));
				sessionObj.setAppetite(rs.getString(DB_COL_CNSLING_SESSION_APPETITE));
				sessionObj.setSleep(rs.getString(DB_COL_CNSLING_SESSION_SLEEP));
				sessionObj.setRapport(rs.getString(DB_COL_CNSLING_SESSION_RAPPORT));
				sessionObj.setAppearance(rs.getString(DB_COL_CNSLING_SESSION_APPEAR));
				sessionObj.setMood(rs.getString(DB_COL_CNSLING_SESSION_MOOD));
				sessionObj.setAffect(rs.getString(DB_COL_CNSLING_SESSION_AFFECT));
				sessionObj.setThought(rs.getString(DB_COL_CNSLING_SESSION_THOUGHT));
				sessionObj.setPerceptDisturb(rs.getString(DB_COL_CNSLING_SESSION_DISTURBANCES));
				sessionObj.setOrientation(rs.getString(DB_COL_CNSLING_SESSION_ORIENTATION));
				sessionObj.setInsight(rs.getString(DB_COL_CNSLING_SESSION_INSIGHT));
				sessionObj.setJudgement(rs.getString(DB_COL_CNSLING_SESSION_JUDGEMENT));
				sessionObj.setAppetiteComments(rs.getString(DB_COL_CNSLING_SESSION_APPETITE_COMMENTS));
				sessionObj.setSleepComments(rs.getString(DB_COL_CNSLING_SESSION_SLEEP_COMMENTS));
				sessionObj.setRapportComments(rs.getString(DB_COL_CNSLING_SESSION_RAPPORT_COMMENTS));
				sessionObj.setAppearanceComments(rs.getString(DB_COL_CNSLING_SESSION_APPEAR_COMMENTS));
				sessionObj.setMoodComments(rs.getString(DB_COL_CNSLING_SESSION_MOOD_COMMENTS));
				sessionObj.setAffectComments(rs.getString(DB_COL_CNSLING_SESSION_AFFECT_COMMENTS));
				sessionObj.setThoughtComments(rs.getString(DB_COL_CNSLING_SESSION_THOUGHT_COMMENTS));
				sessionObj.setPerceptDisturbComments(rs.getString(DB_COL_CNSLING_SESSION_DISTURBANCES_COMMENTS));
				sessionObj.setOrientationComments(rs.getString(DB_COL_CNSLING_SESSION_ORIENTATION_COMMENTS));
				sessionObj.setInsightComments(rs.getString(DB_COL_CNSLING_SESSION_INSIGHT_COMMENTS));
				sessionObj.setJudgementComments(rs.getString(DB_COL_CNSLING_SESSION_JUDGEMENT_COMMENTS));
				
				vSessionObjs.addElement(sessionObj);
			}
			
			con.close();
			
			return vSessionObjs;
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}

		
		// TODO Auto-generated method stub
		return null;
	}

	public String delCounsellingSession(CounsellingSessionObj sessionObj) {
		Connection con = null; 
		StringBuffer sql= new StringBuffer("Delete from " +
					DB_TBL_COUNSELLING_SESSION + " Where ");

		try{
			con = getConnection();

			sql.append(DB_COL_CNSLING_SESSION_ID);
			sql.append(" ='" + sessionObj.getCnslingSessionId() +"'");
			
			System.out.println("DELETE counselling session query string : " + sql.toString());
			
			Statement st = con.createStatement();
			st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS"; //TODO CONVERT TOdefines
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		return "ERROR";
	}
}
