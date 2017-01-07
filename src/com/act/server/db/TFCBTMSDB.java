package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import com.act.common.ACEDefines;
import com.act.common.Counsellee;
import com.act.common.CnsleeTFCBTMileStone;
import com.act.common.CounselleeTFCBTStage;
import com.act.common.CnsleeTFCBTTask;
import com.act.common.PersonName;
import com.act.common.TFCBTStage;
import com.act.common.TFCBTStageMilestone;
import com.act.common.TFCBTStageTask;

public class TFCBTMSDB extends TFCBTDB{

	private static TFCBTMSDB tfcbtMSDB;
	
	private TFCBTMSDB(){
	}
	
	public static TFCBTMSDB getInstance(){
		if (tfcbtMSDB == null){
			tfcbtMSDB = new TFCBTMSDB();
		}
		return tfcbtMSDB;
		
	}
	/**
	 * Retrieves all the TFCBT tasks and milestones values for a particular 
	 * Counsellee
	 * 
	 * @param cnsleeId
	 * @return The entire TFCBT data for a counsellee
	 */
	public Hashtable<String, CounselleeTFCBTStage> getCnsleeTFCBTStages(String cnsleeId){
		
		Hashtable <String, CounselleeTFCBTStage> htCnsleeTFCBTStages = new Hashtable<String, CounselleeTFCBTStage>();
		
		
		Connection con = null; 

		for (int i = 1; i < 11; i++) {
			CounselleeTFCBTStage cnsleeStage = new CounselleeTFCBTStage();
			cnsleeStage.setStageNumber(i);
			htCnsleeTFCBTStages.put(String.valueOf(i), cnsleeStage);
			
		}
		
		String sql= "Select * from " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" where " + 
					DB_COL_TFCBT_CNSLEE_ID + " = '"+cnsleeId + "'";
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				//check if it is a task/milestone
				String taskType = rs.getString(DB_COL_TFCBT_CNSLEE_TASK_TYPE);
				
				if(taskType.equals(ACEDefines.TFCBT_REQUIRED_TASK)){
					CnsleeTFCBTTask cnsleeTFCBTTask = new CnsleeTFCBTTask();
	 
					cnsleeTFCBTTask.setTaskId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_ID));
					cnsleeTFCBTTask.setCnsleeId(rs.getString(DB_COL_TFCBT_CNSLEE_ID));
					cnsleeTFCBTTask.setCnslrId(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID));
					cnsleeTFCBTTask.setTaskNotes(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_NOTES));
					cnsleeTFCBTTask.setDateCompleted(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
					cnsleeTFCBTTask.setTaskDate(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
					cnsleeTFCBTTask.setTaskStatus(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_STATUS));
					cnsleeTFCBTTask.setTfcbtStageId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM));
					
					int stageNum = cnsleeTFCBTTask.getTfcbtStageId();
					
					CounselleeTFCBTStage cnsleeTFCBTStage = htCnsleeTFCBTStages.get(String.valueOf(stageNum));
					cnsleeTFCBTStage.addCnsleeTFCBTTask(cnsleeTFCBTTask);
					
				}else if (taskType.equals(ACEDefines.TFCBT_MILESTONE)){
					
					CnsleeTFCBTMileStone cnsleeTFCBTMileStone = new CnsleeTFCBTMileStone();
					 
					cnsleeTFCBTMileStone.setMilestoneId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_ID));
					cnsleeTFCBTMileStone.setCnsleeId(rs.getString(DB_COL_TFCBT_CNSLEE_ID));
					cnsleeTFCBTMileStone.setCnslrId(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID));
					cnsleeTFCBTMileStone.setMilestoneNotes(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_NOTES));
					cnsleeTFCBTMileStone.setDateCompleted(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
					cnsleeTFCBTMileStone.setMilestoneDate(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
					cnsleeTFCBTMileStone.setTfcbtStageId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM));
					
					int stageNum = cnsleeTFCBTMileStone.getTfcbtStageId();
					
					CounselleeTFCBTStage cnsleeTFCBTStage = htCnsleeTFCBTStages.get(String.valueOf(stageNum));
					cnsleeTFCBTStage.addCnsleeTFCBTMilestone(cnsleeTFCBTMileStone);

				}
			}
				
			con.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
				
		return htCnsleeTFCBTStages;
		
	}
	
	@Override
	public Hashtable<String, TFCBTStage> getTFCBTStages() {

		Connection con = null; 
		Hashtable<String, TFCBTStage> htStages = new Hashtable<String, TFCBTStage>();
		
		String sql= "Select * from " +
					DB_TBL_TFCBT_STAGE_MASTER;
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				TFCBTStage tfcbtStage = new TFCBTStage();
 
				tfcbtStage.setStageNumber(rs.getInt(DB_COL_TFCBT_STAGE_NUMBER));
				tfcbtStage.setObjective(rs.getString(DB_COL_TFCBT_STAGE_OBJECTIVE));
				tfcbtStage.setDescription(rs.getString(DB_COL_TFCBT_STAGE_DESC));
				tfcbtStage.setTitle(rs.getString(DB_COL_TFCBT_STAGE_NAME));
				
				htStages.put(String.valueOf(tfcbtStage.getStageNumber()), tfcbtStage);
			
			}
				
			con.close();
			
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
				
		return htStages;
	}


	/**
	 * Get all the TFCBT required tasks for the given stage
	 * @param stageNumber
	 * @return
	 */
	public Vector<TFCBTStageTask> getTFCBTStageTask(String stageNumber) {

		Connection con = null; 
		Vector<TFCBTStageTask> vStageTasks = new Vector<TFCBTStageTask>();
		
		String sql= "Select * from " +
					DB_TBL_TFCBT_TASK_MASTER +
					" where " + DB_COL_TFCBT_TASK_TYPE +" = "+
					"'REQUIRED_TASK' AND "+
					DB_COL_TFCBT_TASK_STAGE_NUM + " = "+stageNumber;
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()){
				
				TFCBTStageTask tfcbtStageTask = new TFCBTStageTask();
 
				tfcbtStageTask.setTaskId(rs.getInt(DB_COL_TFCBT_TASK_ID));
				tfcbtStageTask.setStageId(rs.getInt(DB_COL_TFCBT_TASK_STAGE_NUM));
				tfcbtStageTask.setTitle(rs.getString(DB_COL_TFCBT_TASK_TITLE));
				tfcbtStageTask.setSubTitles(rs.getString(DB_COL_TFCBT_TASK_SUB_TITLE));

				vStageTasks.add(tfcbtStageTask);
			
			}
				
			con.close();
			
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
				
		return vStageTasks;
	}

	public Vector<TFCBTStageMilestone> getTFCBTStageMilestones(String stageNumber) {

		Connection con = null; 
		Vector<TFCBTStageMilestone> vStageMilestones = new Vector<TFCBTStageMilestone>();
		
		String sql= "Select * from " +
					DB_TBL_TFCBT_TASK_MASTER +
					" where " + DB_COL_TFCBT_TASK_TYPE +" = "+
					"'MILESTONE' AND "+
					DB_COL_TFCBT_TASK_STAGE_NUM + " = "+stageNumber;
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				TFCBTStageMilestone tfcbtStageMilestone = new TFCBTStageMilestone();
 
				tfcbtStageMilestone.setMilestoneId(rs.getInt(DB_COL_TFCBT_TASK_ID));
				tfcbtStageMilestone.setStageId(rs.getInt(DB_COL_TFCBT_TASK_STAGE_NUM));
				tfcbtStageMilestone.setTitle(rs.getString(DB_COL_TFCBT_TASK_TITLE));
				tfcbtStageMilestone.setSubTitles(rs.getString(DB_COL_TFCBT_TASK_SUB_TITLE));
				
				vStageMilestones.add(tfcbtStageMilestone);
			
			}
				
			con.close();
			
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
				
		return vStageMilestones;
	}

	public Vector<CnsleeTFCBTTask> getCounselleeTFCBTTasks(int stageNumber) {

		Connection con = null; 
		Vector<CnsleeTFCBTTask> vCounselleeTFCBTTasks = new Vector<CnsleeTFCBTTask>();
		
		String sql= "Select * from " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" where " + DB_COL_TFCBT_CNSLEE_TASK_TYPE +" = "+
					"'REQUIRED_TASK' AND "+
					DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM + " = "+stageNumber;
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				CnsleeTFCBTTask cnsleeTFCBTTask = new CnsleeTFCBTTask();
 
				cnsleeTFCBTTask.setTaskId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_ID));
				cnsleeTFCBTTask.setCnsleeId(rs.getString(DB_COL_TFCBT_CNSLEE_ID));
				cnsleeTFCBTTask.setCnslrId(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID));
				cnsleeTFCBTTask.setTaskNotes(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_NOTES));
				cnsleeTFCBTTask.setDateCompleted(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
				cnsleeTFCBTTask.setTaskDate(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
				cnsleeTFCBTTask.setTaskStatus(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_STATUS));
				cnsleeTFCBTTask.setTfcbtStageId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM));
				
				vCounselleeTFCBTTasks.add(cnsleeTFCBTTask);
			 
			}
				
			con.close();
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
				
		return vCounselleeTFCBTTasks;
	}

	/**
	 * 
	 * @param stageNumber
	 * @return
	 */
	public Vector<CnsleeTFCBTMileStone> getCounselleeTFCBTMilestones(int stageNumber) {

		Connection con = null; 
		Vector<CnsleeTFCBTMileStone> vCounselleeTFCBTMilestones = new Vector<CnsleeTFCBTMileStone>();
		
		String sql= "Select * from " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" where " + DB_COL_TFCBT_CNSLEE_TASK_TYPE +" = "+
					"'REQUIRED_TASK' AND "+
					DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM + " = "+stageNumber;
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				CnsleeTFCBTMileStone cnsleeTFCBTMilestone = new CnsleeTFCBTMileStone();
 
				cnsleeTFCBTMilestone.setMilestoneId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_ID));
				cnsleeTFCBTMilestone.setCnsleeId(rs.getString(DB_COL_TFCBT_CNSLEE_ID));
				cnsleeTFCBTMilestone.setCnslrId(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID));
				cnsleeTFCBTMilestone.setMilestoneNotes(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_NOTES));
				cnsleeTFCBTMilestone.setDateCompleted(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
				cnsleeTFCBTMilestone.setMilestoneDate(rs.getString(DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED));
				cnsleeTFCBTMilestone.setTfcbtStageId(rs.getInt(DB_COL_TFCBT_CNSLEE_TASK_STAGE_NUM));
				
				vCounselleeTFCBTMilestones.add(cnsleeTFCBTMilestone);
			
			}
				
			con.close();
			
			return vCounselleeTFCBTMilestones;
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
				
		return null;
	}
	
	public String saveCnsleeTFCBTTask(CnsleeTFCBTTask task){
		
		Connection con = null; 

		//First check if a record exists for this task
		//for this counsellee. If so, update it, else create a new record
		int taskId = task.getTaskId();
		
		boolean bExists = doesTaskExist(task.getCnsleeId(), taskId, task.getCounsellingSessionId());
		
		String sql = "";
		
		if (bExists){
			sql = "Update " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" set " +  
					DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID + "= '" + task.getCnslrId() +"'," +
					DB_COL_TFCBT_CNSLEE_TASK_NOTES + "= '" + task.getTaskNotes() +"'," +
					DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED + "= '" + task.getTaskDate() +"'," +
					DB_COL_TFCBT_CNSLEE_TASK_STATUS + "= '" + task.getTaskStatus()  + "' "+
					" where " +
					DB_COL_TFCBT_CNSLEE_ID + " = '"+task.getCnsleeId() + "'" +
					" and " +
					DB_COL_TFCBT_CNSLEE_TASK_ID + " = " + taskId +
					" and " +
					DB_COL_TFCBT_CNSLEE_TASK_CNSL_SESSION_ID + " = " + task.getCounsellingSessionId();
			
		}else{
			sql = "Insert into " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" values (" +
					"'" + task.getCnsleeId() + "', " +
					"'" + task.getTaskId()  + "', " + 
					"'" + task.getTfcbtStageId()  + "', " +
					"'" + task.getDateCompleted()  + "', " +
					"'" + ACEDefines.TFCBT_REQUIRED_TASK + "', " +
					"'" + task.getTaskNotes()  + "', " +
					"'" + CnsleeTFCBTTask.TFCBT_STAGE_TASK_STATUS_NEW + "', " +
					"'" + task.getCnslrId()  + "', " +
					"'" + task.getCounsellingSessionId()  + "' )" ;
		}
					
		try{
			con = getConnection();
			Statement st = con.createStatement();
			
			System.out.println("save query String: " + sql.toString());
			st.executeUpdate(sql);

			con.close();
			
			return "SUCCESS";
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return "ERROR";
	}
	
	public String saveCnsleeTFCBTMilestone(CnsleeTFCBTMileStone milestone){
		
		Connection con = null; 

		//First check if a record exists for this milestone
		//for this counsellee. If so, update it, else create a new record
		int milestoneId = milestone.getMilestoneId();
		
		boolean bExists = doesTaskExist(milestone.getCnsleeId(), milestoneId, milestone.getCounsellingSessionId());
		
		String sql = "";
		
		if (bExists){
			sql = "Update " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" set " +  
					DB_COL_TFCBT_CNSLEE_TASK_CNSLR_ID + "= '" + milestone.getCnslrId() +"'," +
					DB_COL_TFCBT_CNSLEE_TASK_NOTES + "= '" + milestone.getMilestoneNotes() +"'," +
					DB_COL_TFCBT_CNSLEE_TASK_DATE_COMPLETED + "= '" + milestone.getMilestoneDate() +"' " +
					" where " +
					DB_COL_TFCBT_CNSLEE_ID + " = '"+milestone.getCnsleeId() + "'" +
					" and " +
					DB_COL_TFCBT_CNSLEE_TASK_ID + " = " + milestoneId +
					" and " +
					DB_COL_TFCBT_CNSLEE_TASK_CNSL_SESSION_ID + " = " + milestone.getCounsellingSessionId();
			
			
		}else{
			sql = "Insert into " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" values (" +
					"'" + milestone.getCnsleeId() + "', " +
					"'" + milestone.getMilestoneId()  + "', " + 
					"'" + milestone.getTfcbtStageId()  + "', " +
					"'" + milestone.getDateCompleted()  + "', " +
					"'" + ACEDefines.TFCBT_MILESTONE  + "', " +
					"'" + milestone.getMilestoneNotes()  + "', " +
					"'', " +
					"'" + milestone.getCnslrId()  + "', " +
					"'" + milestone.getCounsellingSessionId()  + "' )" ;
			
		}
					
		try{
			con = getConnection();
			Statement st = con.createStatement();
			
			System.out.println("save query : " + sql.toString());
			st.executeUpdate(sql);

			con.close();
			
			return "SUCCESS";
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return "ERROR";
	}
	
	
	public boolean doesTaskExist(String cnsleeId, int taskId, String cnslingSessionId){
		
		boolean bExists = false;
		Connection con = null; 

		String sql = "Select * from " +
					DB_TBL_TFCBT_CNSLEE_TASK +
					" where " +  
					DB_COL_TFCBT_CNSLEE_ID + " = '"+cnsleeId + "'" +
					" and "+
					DB_COL_TFCBT_CNSLEE_TASK_ID + " = " + taskId +
					" and "+
					DB_COL_TFCBT_CNSLEE_TASK_CNSL_SESSION_ID + " = " + cnslingSessionId;
					
	
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				bExists = true;
			}
			
			con.close();
			
		}catch(SQLException e){
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}catch (Exception e){
			if (con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
				
		
		return bExists;
		
	}
}
