package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.act.common.User;
import com.act.common.GroupsObj;
import com.act.common.UsersandGroupsObj;

public class UserMSDB extends UserDB {
	
	private static UserMSDB userMSDB;
	
	private UserMSDB(){
		
	}
	//Singleton
	public static UserMSDB getInstance(){
		if (userMSDB == null){
			userMSDB = new UserMSDB();
		}
		
		return userMSDB;
	}
	
	public boolean findUser(String userID, String passWord) {
		Connection con = null; 
		
		
		String sql= "Select * from " +
					DB_TBL_USER + " where " + DB_COL_USER_ID +" = '" + 
					userID + "' and " +DB_COL_USER_PWD + " =" +"'" + passWord +"'";
		
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				return true;
			}
				
			con.close();
		}catch(SQLException e){
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}catch (Exception e){
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		return false;
	}

	// ND added on 06th Oct 16
	public Vector<User> getUserInfo(String user_id){
		
		Connection con = null;
		String sql = "Select * from " + DB_TBL_USER +
				" Where " + DB_COL_USER_ID + " = '" + user_id + "'";
		String sql_ung = "Select * from " + DB_TBL_USERSANDGROUPS + 
				" Where " + DB_COL_USER_GRP_USRID + " = '" + user_id + "'";
		UsersandGroupsObj ungObj;
		Vector<User> v = new Vector<User>();
		Vector<String> vUnG = new Vector<String>();
		System.out.println("Searching for info on " + user_id);
		System.out.println("SQL query is " + sql);
		System.out.println("And for usersandgroups is " + sql_ung);
		try{
			con = getConnection();
			Statement st = con.createStatement();
			Statement st_ung = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				User user = new User();
				user.setUserID(user_id);
				user.setfName(rs.getString(DB_COL_USER_FNAME));
				user.setmName(rs.getString(DB_COL_USER_MNAME));
				user.setlName(rs.getString(DB_COL_USER_LNAME));
				user.setAddress(rs.getString(DB_COL_USER_ADDRESS));
				user.setDesig(rs.getString(DB_COL_USER_DESIG));
				user.setDob(rs.getString(DB_COL_USER_DOB));
				user.setEmail(rs.getString(DB_COL_USER_EMAIL));
				user.setGender(rs.getString(DB_COL_USER_GENDER));
				user.setMobile(rs.getString(DB_COL_USER_MOB));
				user.setPhone(rs.getString(DB_COL_USER_PHONE));
				user.setPincode(rs.getString(DB_COL_USER_PIN));
				user.setPassWord(rs.getString(DB_COL_USER_PWD));
				ResultSet rs_ung = st_ung.executeQuery(sql_ung);
				vUnG = new Vector<String>();
				while (rs_ung.next()) {
					ungObj = new UsersandGroupsObj();
					ungObj.setGrpName(rs_ung.getString(DB_COL_USER_GRP_GRPNAME));
					vUnG.add(ungObj.getGrpName());
				}
				user.setGroups(vUnG);
				v.add(user);
				System.out.println("Groups for " + user.getUserID() + " are " + DB.joinVStrings(vUnG)
									+ " or " + DB.joinVStrings(user.getGroups()));
			}
			st.close();
			st_ung.close();
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
		
		return v;
	}
	
	public String updateUserInfo(User userobj, boolean grpChanged){
		Connection con = null;
		
		try{
		con = getConnection();
		Statement st = con.createStatement();		
		String sUser_id = userobj.getUserID();
//		String sql_del = "Delete from " + DB_TBL_USER + " Where " +  DB_COL_USER_ID + " = '" + sUser_id + "'";
		StringBuffer sql = new StringBuffer( "Update " + DB_TBL_USER + " set ");
		
//		sql.append(DB_COL_USER_ID);
//		sql.append("= '" + sUser_id + "'");					
//		sql.append(",");
		sql.append(DB_COL_USER_ADDRESS);
		sql.append("= '" +userobj.getAddress() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_DESIG);
		sql.append("= '" +userobj.getDesig() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_DOB);
		sql.append("= '" +userobj.getDob() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_EMAIL);
		sql.append("= '" +userobj.getEmail() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_FNAME);
		sql.append("= '" +userobj.getfName() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_GENDER);
		sql.append("= '" +userobj.getGender() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_LNAME);
		sql.append("= '" +userobj.getlName() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_MNAME);
		sql.append("= '" +userobj.getmName() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_MOB);
		sql.append("= '" +userobj.getMobile() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_PHONE);
		sql.append("= '" +userobj.getPhone() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_PIN);
		sql.append("= '" +userobj.getPincode() +"'");
		sql.append(",");
		sql.append(DB_COL_USER_PWD);
		sql.append("= '" +userobj.getPassWord() +"'");
//		sql.append(DB_COL_USER_GROUPS);
//		sql.append("= '" + joinVStrings(userobj.getGroups()) +"'");

		sql.append(" WHERE ");
		sql.append(DB_COL_USER_ID);
		sql.append(" = '");
		sql.append(sUser_id + "'");											
		
		System.out.println("update Users info query string : " + sql.toString());
		int n = st.executeUpdate(sql.toString());
		System.out.println("Number of records updated " + DB_TBL_USER + " is " + n);
		if (grpChanged)
			updateUserAndGrpInfo(userobj, grpChanged);
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

	// ND added on 10th Oct 16
	public Vector<User> getUsersAll(){
		
		Connection con = null;
		String sql = "Select * from " + DB_TBL_USER ;
		Vector<User> v = new Vector<User>();
		System.out.println("SQL query is " + sql);
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				User user = new User();
				user.setUserID(rs.getString(DB_COL_USER_ID));
				user.setfName(rs.getString(DB_COL_USER_FNAME));
				user.setmName(rs.getString(DB_COL_USER_MNAME));
				user.setlName(rs.getString(DB_COL_USER_LNAME));
				user.setAddress(rs.getString(DB_COL_USER_ADDRESS));
				user.setDesig(rs.getString(DB_COL_USER_DESIG));
				user.setDob(rs.getString(DB_COL_USER_DOB));
				user.setEmail(rs.getString(DB_COL_USER_EMAIL));
				user.setGender(rs.getString(DB_COL_USER_GENDER));
				user.setMobile(rs.getString(DB_COL_USER_MOB));
				user.setPhone(rs.getString(DB_COL_USER_PHONE));
				user.setPincode(rs.getString(DB_COL_USER_PIN));
				user.setPassWord(rs.getString(DB_COL_USER_PWD));
				v.add(user);
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
		
		return v;
	}
	
	public String saveNewUser(User userobj){
		Connection con = null;
		try {
			con = getConnection();
			Statement st = con.createStatement();	
			Statement st_ung = con.createStatement();
			String[] sql_userCol = {DB_COL_USER_ID, DB_COL_USER_FNAME, DB_COL_USER_MNAME, DB_COL_USER_LNAME,
										DB_COL_USER_ADDRESS, DB_COL_USER_DESIG, DB_COL_USER_DOB, DB_COL_USER_EMAIL,
										DB_COL_USER_GENDER, DB_COL_USER_MOB, DB_COL_USER_PHONE, DB_COL_USER_PIN, DB_COL_USER_PWD};
			String[] sql_usersAndGroupsCol = {DB_COL_USER_GRP_USRID, DB_COL_USER_GRP_GRPNAME} ;
			String[] sql_userValues = {  noNull(userobj.getUserID()), noNull(userobj.getfName()), noNull(userobj.getmName()), noNull(userobj.getlName()),
					noNull(userobj.getAddress()), noNull(userobj.getDesig()), noNull(userobj.getDob()), noNull(userobj.getEmail()),
					noNull(userobj.getGender()), noNull(userobj.getMobile()), noNull(userobj.getPhone()), noNull(userobj.getPincode()),
					noNull(userobj.getPassWord())};
			String[] sql_usersAndGroupsValues = {noNull(userobj.getUserID()), noNull(joinVStrings(userobj.getGroups()))};
			
			String sql = new String();
			String sql_ung = new String();
			
			sql = createInsertQry(sql_userCol, sql_userValues);
			sql_ung = createInsertQry(sql_usersAndGroupsCol, sql_usersAndGroupsValues);
			sql = "Insert into " + DB_TBL_USER  + " " + sql;
			sql_ung = "Insert into " + DB_TBL_USERSANDGROUPS + " " + sql_ung;
			
			System.out.println("The query to add a new user :" + sql);
			System.out.println("The query to add new user with groups " + sql_ung);
			st.executeUpdate(sql);
			st.close();
			st_ung.executeUpdate(sql_ung);
			st_ung.close();
			con.close();
			return "SUCCESS";
		}catch (SQLException e){
			e.printStackTrace();
			if(con!= null)
				try {
					con.close();
				} catch (SQLException e1){
					e1.printStackTrace();
				}
		} catch (Exception e) {
				e.printStackTrace();
				if (con != null)
					try {
						con.close();
					} catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
		
		return "ERROR";
	}
	
//	public Boolean delUsers(Vector<String> vUserIDs){								// ND added on 19th Oct 16
	public Boolean delUser(String userID){							
		Connection con = null; 
		
		String sql= new String();
		String sql_ung = new String();
		try{
			con = getConnection();
			Statement st = con.createStatement();
			Statement st_ung = con.createStatement();
//			String sSql = new String();
//			String sPunc = new String();
			System.out.println("User being deleted: " + userID);
			String sSql = new String();
			String sPunc = new String();
			sql = "Delete from " + DB_TBL_USER + " where " + 
						DB_COL_USER_ID + " = '" + userID + "'";
			sql_ung = "Delete from " + DB_TBL_USERSANDGROUPS + " where " + DB_COL_USER_GRP_USRID +
					" = '" + userID + "'";
//			st.addBatch(sql);
//			st_ung.addBatch(sql_ung);
			System.out.println("Del user query : " + sql);
			System.out.println("Del user and grps query : " + sql_ung);
			
//			st.executeBatch();
//			st_ung.executeBatch();
			st_ung.executeUpdate(sql_ung);
			st.executeUpdate(sql);
			st.close();
			st_ung.close();
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
			return false;
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return false;
		}
		
		return true;
	}	

	// ND added on 14th Oct 16
	public Vector<GroupsObj> getGroups(){
		
		Connection con = null;
		String sql = "Select * from " + DB_TBL_GROUPSTBL ;
		Vector<GroupsObj> v = new Vector<GroupsObj>();
		System.out.println("To get all the user groups the SQL query is " + sql);
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				GroupsObj userGrps = new GroupsObj();
				userGrps.setGroupName(rs.getString(DB_COL_GROUP_NAME));
				userGrps.setGroupDesc(rs.getString(DB_COL_GROUP_DESC));
				v.add(userGrps);
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
		return v;
	}
	
	public Vector<GroupsObj> getGroupInfo(String grpName){
		
		Connection con = null;
		String sql = "Select * from " + DB_TBL_GROUPSTBL + " Where " +
							DB_COL_GROUP_NAME + " = '" + grpName + "'";
		Vector<GroupsObj> v = new Vector<GroupsObj>();
		System.out.println("SQL query is " + sql);
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int i = 0;
			while (rs.next()){
				i++;
				GroupsObj userGrps = new GroupsObj();
				System.out.println("THE GROUP ID READ FROM THE USERGROUPS TABLE IS " + userGrps.getGroupName());
				userGrps.setGroupName(rs.getString(DB_COL_GROUP_NAME));
				userGrps.setGroupDesc(rs.getString(DB_COL_GROUP_DESC));
				v.add(userGrps);
			}
			if (i > 1)
				System.out.println("More than one group info read with the same name.");
			st.close();
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
		
		return v;
	}

	public String saveNewGroup(GroupsObj userGrpobj){
		Connection con = null;
		try {
			con = getConnection();
			Statement st = con.createStatement();		
			String[] sql_userCol = { //DB_COL_USRGRP_ID,  
							DB_COL_GROUP_NAME, DB_COL_GROUP_DESC };
			String sql_groups = " ";
			String[] sql_userValues = {  //noNull(userGrpobj.getGroupID()), 
					noNull(userGrpobj.getGroupName()), noNull(userGrpobj.getGroupDesc()) };
			String sql = new String();
			sql = createInsertQry(sql_userCol, sql_userValues);
			sql = "Insert into " + DB_TBL_GROUPSTBL  + " " + sql;
			System.out.println("The query to add a new user group :" + sql);
			st.executeUpdate(sql);
			st.close();
			con.close();
			return "SUCCESS";
		}catch (SQLException e){
			e.printStackTrace();
			if(con!= null)
				try {
					con.close();
				} catch (SQLException e1){
					e1.printStackTrace();
				}
		} catch (Exception e) {
				e.printStackTrace();
				if (con != null)
					try {
						con.close();
					} catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
		
		return "ERROR";
	}
	
	
	public String updateGroupInfo(GroupsObj groupsobj, String oldGroupName){
		Connection con = null;
		
		try{
		con = getConnection();
		Statement st = con.createStatement();		
//		String grpName = groupsobj.getGroupName();
//		String sql_del = "Delete from " + DB_TBL_USER + " Where " +  DB_COL_USER_ID + " = '" + sUser_id + "'";
		StringBuffer sql = new StringBuffer( "Update " + DB_TBL_GROUPSTBL + " set ");
//		sql.append(DB_COL_USER_ID);
//		sql.append("= '" + sUser_id + "'");					
//		sql.append(",");
		sql.append(DB_COL_GROUP_NAME);
		sql.append("= '" +groupsobj.getGroupName() +"'");
		sql.append(",");
		sql.append(DB_COL_GROUP_DESC);
		sql.append("= '" +groupsobj.getGroupDesc() +"'");
		sql.append(" WHERE ");
		sql.append(DB_COL_GROUP_NAME);
		sql.append(" = '");
		sql.append(oldGroupName + "'");											

		System.out.println("update Group info query string : " + sql.toString());
		int n = st.executeUpdate(sql.toString());
		System.out.println("Number of records updated is " + n);
		
		if (groupsobj.getGroupName() != oldGroupName){
			Statement st_ung = con.createStatement();
			StringBuffer sql_ung = new StringBuffer("Update " + DB_TBL_USERSANDGROUPS + " set ");
			
			sql_ung.append(DB_COL_USER_GRP_GRPNAME);
			sql_ung.append("= '" + groupsobj.getGroupName() + "'");
			sql_ung.append(" WHERE ");
			sql_ung.append(DB_COL_USER_GRP_GRPNAME);
			sql_ung.append(" = '");
			sql_ung.append(oldGroupName + "'");			
			System.out.println("update group name in usersandgroup :" +  sql_ung.toString());
			int ung = st_ung.executeUpdate(sql_ung.toString());
			System.out.println("Number of records in usersandgroups edited :" + ung);
			st_ung.close();
		}
		st.close();
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

	
	// called from updateUserInfo() if grpChanged is true
	public String updateUserAndGrpInfo(User userobj, boolean grpChanged){
		Connection con = null;
		
		try{
		con = getConnection();
		Statement st = con.createStatement();
		Statement st_del = con.createStatement();
		String sUser_id = userobj.getUserID();
		Vector<String> vUserGrps = userobj.getGroups();
		
//		String sql_del = "Delete from " + DB_TBL_USER + " Where " +  DB_COL_USER_ID + " = '" + sUser_id + "'";
		String sql_del = "Delete from " + DB_TBL_USERSANDGROUPS + " Where " + 
											DB_COL_USER_GRP_USRID + " = '"  + sUser_id + "'";
		String[] ung_col = { DB_COL_USER_GRP_GRPNAME, DB_COL_USER_GRP_USRID};

		for ( int i = 0; i < vUserGrps.size(); i++) {
			String[] ung_val = {vUserGrps.elementAt(i), sUser_id};
			System.out.println("Into the table usersandgroupstbl inserting groupname " + vUserGrps.elementAt(i));
			StringBuffer sql = new StringBuffer( "Insert into " + DB_TBL_USERSANDGROUPS + " " );
			sql.append(createInsertQry(ung_col, ung_val));
			st.addBatch(sql.toString());
			System.out.println("update Users Grp info query string : " + sql.toString());
		}
		int d = st_del.executeUpdate(sql_del);
		System.out.println("Number of User " + sUser_id + " records deleted in " + DB_TBL_USERSANDGROUPS + " is " + d);
		int[] n = st.executeBatch();
		System.out.println("Number of records updated in " + DB_TBL_USERSANDGROUPS + " is " + n);
		st_del.close();
		st.close();
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
	
	public Boolean delGroups(String grpName){								// ND added on 03rd Nov 16
		
		JOptionPane optConfirmDel;
		Connection con = null; 
		
		String sql= new String();
		String sql_ung = new String();
		String sql_ungdel = new String();
		
		try{
			optConfirmDel = new JOptionPane();
			con = getConnection();
			Statement st = con.createStatement();
			Statement st_ung = con.createStatement();
			Statement st_ungdel = con.createStatement();
			
//			String sSql = new String();
//			String sPunc = new String();
			System.out.println("group being deleted: " + grpName);
			String sSql = new String();
			String sPunc = new String();
			String sGrp = grpName;
			sql_ung = "Select * From " + DB_TBL_USERSANDGROUPS + " where " + DB_COL_USER_GRP_GRPNAME +
					" = '" + grpName + "'";
			ResultSet rs_ung = st_ung.executeQuery(sql_ung);
			sql = "Delete from " + DB_TBL_GROUPSTBL + " where " + 
					DB_COL_USER_GRP_GRPNAME + " = '" + grpName + "'";
			
			if (rs_ung.next()){				// If no records are retrieved rs_ung.next will return false
				System.out.println("Users still part of group " + grpName );
				String sTtl = new String("Delete all users from the group " + grpName);
					int r = optConfirmDel.showConfirmDialog(null, "Yes?", sTtl, 2);
					if (r == JOptionPane.YES_OPTION){
						sql_ungdel = "Delete from " + DB_TBL_USERSANDGROUPS + " where " + 
							DB_COL_USER_GRP_GRPNAME + " = '" + grpName + "'";
						System.out.println("Deleting all users from the group " + grpName);
						int ungd = st_ungdel.executeUpdate(sql_ungdel);
						int grpdel = st.executeUpdate(sql);
					}
			} else {
				st.executeUpdate(sql);
				System.out.println("Del group query : " + sql);
			}
			st.close();
			st_ung.close();
			st_ungdel.close();
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
			return false;
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return false;
		}
		
		return true;
	}	
	
	public boolean isAdmin(String cnslrID){
		boolean isAdmin = false;
		
		Connection con = null;
		String sql = "Select * from " + DB_TBL_USERSANDGROUPS +
				" Where " + DB_COL_USER_GRP_USRID + " = '" + cnslrID + "'" 
				+" and " + DB_COL_USER_GRP_GRPNAME + " = '" + "Administrator'";

		
		
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				isAdmin = true;
			}
			st.close();
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
		
		return isAdmin;
		
	}
	
}