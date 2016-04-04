package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.act.common.Counsellee;
import com.act.common.PersonName;
import com.act.common.Religion;
import com.act.common.Relationship;		// ND added
import com.act.common.Addiction;		// ND added 23rd Sep
import com.act.common.CopingSkill;		// ND added 24th Sep
import com.act.common.Language;			// ND added 25th Sep
import com.act.common.NonFormalEducation;		// ND added on 28th Sep
import com.act.common.Recreation;		// ND added 12th Oct
import com.act.common.Organisation;		// ND added on 19th Oct
import com.act.common.CareHome;			// ND added on 24th Oct

public class GeneralMstMSDB extends GeneralMstDB {
	private static GeneralMstMSDB generalMstMSDB;
	
	//Singleton
	public static GeneralMstMSDB getInstance(){
		if (generalMstMSDB == null){
			generalMstMSDB = new GeneralMstMSDB();
		}
		
		return generalMstMSDB;
	}

// ND added 11th Sep
	public Vector getReligions() { 
		Connection con = null; 
		Vector<Religion> vReligion = new Vector<Religion>();
		
		String sql= "Select * from " +
					DB_TBL_RELIGION ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				Religion relig = new Religion();
				System.out.println("rs.getString(DB_COL_RELIGION_ID): " + rs.getString(DB_COL_RELIGION_ID));
				
				relig.setRelId(rs.getInt(DB_COL_RELIGION_ID));
				relig.setRelName(rs.getString(DB_COL_RELIGION));
				
				vReligion.add(relig);
				System.out.println("retrieved religion " + relig.getRelName());
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
		
		return vReligion;

	}
	// ND added on 23rd Sep
	public Vector getRelationship() { 
		Connection con = null; 
		Vector<Relationship> vRelationship = new Vector<Relationship>();
		
		String sql= "Select * from " +
					DB_TBL_RELATIONSHIP ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				Relationship relate = new Relationship();
				System.out.println("rs.getString(DB_COL_RELATIVETYPE_ID): " + rs.getString(DB_COL_RELATIVETYPE_ID));
				
				relate.setReltnId(rs.getInt(DB_COL_RELATIVETYPE_ID));
				relate.setReltnName(rs.getString(DB_COL_RELATIVETYPE));
				
				vRelationship.add(relate);
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
		
		return vRelationship;

	}
	// ND added 23rd Sep
	public Vector getAddiction() { 
		Connection con = null; 
		Vector<Addiction> vAddiction = new Vector<Addiction>();
		
		String sql= "Select * from " +
					DB_TBL_ADDICTION ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				Addiction addic = new Addiction();
				System.out.println("rs.getString(DB_COL_ADDICTION_ID): " + rs.getString(DB_COL_ADDICTION_ID));
				
				addic.setAddictId(rs.getInt(DB_COL_ADDICTION_ID));
				addic.setAddictName(rs.getString(DB_COL_ADDICTION));
				
				vAddiction.add(addic);
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
		
		return vAddiction;

	}
	
	//ND added on 24th Sep
	public Vector getCopingSkill() { 
		Connection con = null; 
		Vector<CopingSkill> vCopingSkill = new Vector<CopingSkill>();
		Vector<String> vStrCopingSkill = new Vector<String>();							// ND added 16th Mar 16
		
		String sql= "Select * from " +
					DB_TBL_COPINGSKILL ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				CopingSkill copsk = new CopingSkill();
				System.out.println("rs.getString(DB_COL_COPINGSKILL_ID): " + rs.getString(DB_COL_COPINGSKILL_ID));
				
				copsk.setCopSkID(rs.getInt(DB_COL_COPINGSKILL_ID));
				copsk.setCopSk(rs.getString(DB_COL_COPINGSKILL));
				
				vCopingSkill.add(copsk);
				vStrCopingSkill.add(rs.getString(DB_COL_COPINGSKILL));					// ND added 16th Mar 16
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
		
//		return vCopingSkill;
		return vStrCopingSkill;

	}
	// ND added on 25th Sept
	public Vector getLanguage() { 
		Connection con = null; 
		Vector<Language> vLanguage = new Vector<Language>();
		
		String sql= "Select * from " +
					DB_TBL_LANGUAGE ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				Language lang = new Language();
				System.out.println("rs.getString(DB_COL_LANGUAGE_ID): " + rs.getString(DB_COL_LANGUAGE_ID));
				
				lang.setLangId(rs.getInt(DB_COL_LANGUAGE_ID));
				lang.setLangName(rs.getString(DB_COL_LANGUAGE));
				
				vLanguage.add(lang);
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
		
		return vLanguage;

	}
	// ND added on 28th Sep
	public Vector getNonFormalEducation() { 
		Connection con = null; 
		Vector<NonFormalEducation> vNFEducation = new Vector<NonFormalEducation>();
		Vector<String> vStrNonFormEdu =  new Vector<String>();
		
		String sql= "Select * from " +
					DB_TBL_NONFORMALEDUCATION ;

//		String sqlStr= "Select " + DB_COL_NONFORMALEDUCATION + " * from " +
//		DB_TBL_NONFORMALEDUCATION ;

		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
//			ResultSet rs = st.executeQuery(sqlStr);
			String nonFEduList = new String();
			int i = 0;
			while (rs.next()){
				
				NonFormalEducation nfEducation = new NonFormalEducation();
				System.out.println("rs.getString(DB_COL_NONFORMALEDU_ID): " + rs.getString(DB_COL_NONFORMALEDU_ID));
				
				nfEducation.setNfEduId(rs.getInt(DB_COL_NONFORMALEDU_ID));
				nfEducation.setNfEduName(rs.getString(DB_COL_NONFORMALEDUCATION));
				vNFEducation.add(nfEducation);
				
				nonFEduList = rs.getString(DB_COL_NONFORMALEDUCATION);
				vStrNonFormEdu.add(nonFEduList);
				System.out.println(vStrNonFormEdu.elementAt(i));
				i++;
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
		
//		return vNFEducation;
		return vStrNonFormEdu;

	}

	
	// ND added on 12th Oct
	public Vector getRecreation() { 
		Connection con = null; 
		Vector<Recreation> vRecreation = new Vector<Recreation>();
		Vector<String> vStrRecFun = new Vector<String>();
		
		String sql= "Select * from " +
					DB_TBL_RECREATION ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				Recreation recfun = new Recreation();
				System.out.println("rs.getString(DB_COL_RECREATION_ID): " + rs.getString(DB_COL_RECREATION_ID));
				
				recfun.setRecId(rs.getInt(DB_COL_RECREATION_ID));
				recfun.setRecName(rs.getString(DB_COL_RECREATIONACTIVITY));
				
				vRecreation.add(recfun);
				
				vStrRecFun.add(rs.getString(DB_COL_RECREATIONACTIVITY));
				
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
		
//		return vRecreation;
		return vStrRecFun;

	}

	// ND added on 19th Oct
	public Vector getOrganisation() { 
		Connection con = null; 
		Vector<Organisation> vOrganisation = new Vector<Organisation>();
		
		String sql= "Select * from " +
					DB_TBL_ORGANISATION ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				Organisation orgn = new Organisation();
				System.out.println("rs.getString(DB_COL_ORGN_ID): " + rs.getString(DB_COL_ORGN_ID));
				
				orgn.setOrgnId(rs.getInt(DB_COL_ORGN_ID));
				orgn.setOrgnName(rs.getString(DB_COL_ORGN_NAME));
				
				vOrganisation.add(orgn);
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
		
		return vOrganisation;

	}
	// ND added on 24th Oct
	public Vector getCareHome() { 
		Connection con = null; 
		Vector<CareHome> vCareHome = new Vector<CareHome>();
		
		String sql= "Select * from " +
					DB_TBL_CAREHOME ;
		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				CareHome ch = new CareHome();
				System.out.println("rs.getString(DB_COL_CAREHOME_ID): " + rs.getString(DB_COL_CAREHOME_ID));
				
				ch.setCareHomeId(rs.getInt(DB_COL_CAREHOME_ID));
				ch.setCareHomeName(rs.getString(DB_COL_CAREHOME_NAME));
				
				vCareHome.add(ch);
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
		
		return vCareHome;

	}

	
	
}
