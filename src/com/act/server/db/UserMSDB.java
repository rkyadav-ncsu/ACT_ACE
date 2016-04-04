package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.act.common.User;

public class UserMSDB extends UserDB {
	
	public boolean findUser(String userName, String passWord) {
		Connection con = null; 
		
		String sql= "Select * from " +
					DB_TBL_USER + " where " + DB_COL_USER_USERNAME +" = '" + 
					userName + "' and " +DB_COL_USER_PWD + " =" +"'" + passWord +"'";
		
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

}
