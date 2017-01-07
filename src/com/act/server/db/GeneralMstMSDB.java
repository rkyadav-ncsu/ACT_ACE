package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import com.act.common.ACEDefines;
import com.act.common.Counsellee;
import com.act.common.CounsellingTherapy;
import com.act.common.OrgnIDNameObj;
import com.act.common.PersonName;
import com.act.common.Pincode;
import com.act.common.Religion;
import com.act.common.Relationship;		// ND added
import com.act.common.Addiction;		// ND added 23rd Sep
import com.act.common.CopingSkill;		// ND added 24th Sep
import com.act.common.Language;			// ND added 25th Sep
import com.act.common.NonFormalEducation;		// ND added on 28th Sep
import com.act.common.Recreation;		// ND added 12th Oct
import com.act.common.Organisation;		// ND added on 19th Oct
import com.act.common.CareHome;			// ND added on 24th Oct
import com.act.common.ServiceType;
import com.sun.istack.internal.NotNull;

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
					DB_TBL_RELIGION  +
					" ORDER BY " + DB_COL_RELIGION;
		System.out.println("The retreival query is ------------- " + sql);		
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
	
	public Vector getRelationship() { 
		Connection con = null; 

		Vector<String> vRelationship = new Vector<String>();
		String sql= "Select * from " +
					DB_TBL_RELATIONSHIP +
					" ORDER BY " + DB_COL_RELATIVETYPE;
		System.out.println("The retreival query is ------------- " + sql);		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				System.out.println("rs.getString(DB_COL_RELATIVETYPE_ID): " + rs.getString(DB_COL_RELATIVETYPE_ID));
				vRelationship.add(rs.getString(DB_COL_RELATIVETYPE));
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

	public Vector getAddiction() { 
		Connection con = null; 
		Vector<Addiction> vAddiction = new Vector<Addiction>();
		
		String sql= "Select * from " +
					DB_TBL_ADDICTION +
					"  ORDER BY " + DB_COL_ADDICTION;
		System.out.println("The retreival query is ------------- " + sql);		
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
			for (int i = 0; i < vAddiction.size(); i++)
				System.out.println("Addictions : " + i + " " + vAddiction.elementAt(i) );

				
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
					DB_TBL_COPINGSKILL +
					" ORDER BY " + DB_COL_COPINGSKILL;
		System.out.println("The retreival query is ------------- " + sql);		
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
					DB_TBL_LANGUAGE +
					" ORDER BY " + DB_COL_LANGUAGE;
		System.out.println("The retreival query is ------------- " + sql);		
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
					DB_TBL_NONFORMALEDUCATION + 
					" ORDER BY " + DB_COL_NONFORMALEDUCATION;
		
		System.out.println("The retreival query is ------------- " + sql);

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
					DB_TBL_RECREATION +
					" ORDER BY " + DB_COL_RECREATIONACTIVITY;
		System.out.println("The retreival query is ------------- " + sql);		
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

	public Vector getServiceTypes() { 
		Connection con = null; 
		Vector<ServiceType> vServiceType = new Vector<ServiceType>();
		Vector<String> vService = new Vector<String>();
		
		String sql= "Select * from " +
					DB_TBL_SERVICETYPES +
					" ORDER BY " + DB_COL_SERVICETYPES;
		System.out.println("The retreival query is ------------- " + sql);		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				ServiceType serveT = new ServiceType();
				System.out.println("rs.getString(DB_COL_SERVICETYPES_ID): " + rs.getString(DB_COL_SERVICETYPES_ID));
				
				serveT.setService_id(rs.getInt(DB_COL_SERVICETYPES_ID));
				serveT.setService_name(rs.getString(DB_COL_SERVICETYPES));
				
				vServiceType.add(serveT);
				
				vService.add(rs.getString(DB_COL_SERVICETYPES));
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
		return vService;

	}
	
	// ND added on 25th Aug 16
	public Vector getPinInfo() { 
		Connection con = null; 
		Vector <Pincode> vPinCodes = new Vector<Pincode>();
		Pincode pinCodeObj = new Pincode();

		String sql= "Select * from " +
					DB_TBL_PINCODE +
					" ORDER BY " + DB_COL_PINCODE;
		System.out.println("The retreival query is ------------- " + sql);		

		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				System.out.println("rs.getString(DB_COL_PO): " + rs.getString(DB_COL_PO));
				pinCodeObj.setPinCitDis(rs.getString(DB_COL_CITYDIST));
				pinCodeObj.setPinPO(rs.getString(DB_COL_PO));
				pinCodeObj.setPinCode(rs.getString(DB_COL_PINCODE));
				pinCodeObj.setPinState(rs.getString(DB_COL_SATE));
				vPinCodes.add(pinCodeObj);
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
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		return vPinCodes;

	}


	public Vector getOrganisation() { 
		Connection con = null; 
		Vector <String> vOrganisation = new Vector<String>();							// ND added 14th Mar 16
		
		String sql= "Select * from " +
					DB_TBL_ORGANISATION +
					" ORDER BY " + DB_COL_ORGN_NAME;
		System.out.println("The retreival query is ------------- " + sql);		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
	//			Organisation orgn = new Organisation();									// ND commented on 14th Mar 16
				System.out.println("rs.getString(DB_COL_ORGN_ID): " + rs.getString(DB_COL_ORGN_ID));
				vOrganisation.add(rs.getString(DB_COL_ORGN_NAME));						// ND added 14th Mar 16
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
	
	public Integer getOrgn_ID(String sOrganisation) { 									// ND added 18th Mar 16
		Connection con = null; 
		Integer iOrgn_ID = null;
		String sql= "Select " + DB_COL_ORGN_ID + ", " + DB_COL_ORGN_NAME + " from " +
					DB_TBL_ORGANISATION + " Where " + DB_COL_ORGN_NAME + " = '" + sOrganisation + "'";
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int rs_count = 0;
			
			while (rs.next()){
				rs_count ++;
				iOrgn_ID = rs.getInt(DB_COL_ORGN_ID);
				System.out.println(rs.getString(DB_COL_ORGN_ID) + " Name Orgn : " + rs.getString(DB_COL_ORGN_NAME));
			}
			System.out.println("Number of organisations with the same name : " + rs_count);	
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
		
		return iOrgn_ID;

	}

	public String getOrgnName(Integer orgn_ID) { 									// ND added 18th Mar 16
		Connection con = null; 
		String sOrgnName = null;
		String sql= "Select " + DB_COL_ORGN_ID + ", " + DB_COL_ORGN_NAME + " from " +
					DB_TBL_ORGANISATION + " Where " + DB_COL_ORGN_ID + " = " + orgn_ID ;
		
		//write database queries to retrieve the info 
		System.out.println("Get Orgn Name for " + orgn_ID);
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				sOrgnName = rs.getString(DB_COL_ORGN_NAME);
				System.out.println(rs.getString(DB_COL_ORGN_ID) + " Name Orgn : " + rs.getString(DB_COL_ORGN_NAME) + " retreived");
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
		
		return sOrgnName;
	}
	
	public Vector<Organisation> getOrgnIDName(){					// ND added on 12th Sep 16
		Connection con = null; 
//		OrgnIDNameObj orgIDNObj = new OrgnIDNameObj();
//		Vector <OrgnIDNameObj> vOrgIDN = new Vector<OrgnIDNameObj>();
		Vector <Organisation> vOrgIDN = new Vector<Organisation>();
		String sql= "Select " + DB_COL_ORGN_ID + ", " + DB_COL_ORGN_NAME + ", " + 
					DB_COL_ORGN_TYPE + ", " + DB_COL_ORGN_CITY + ", " +  DB_COL_ORGN_SERVICETYPES +
				" from " +	DB_TBL_ORGANISATION + 
					" ORDER BY " + DB_COL_ORGN_NAME;
		System.out.println("query is " + sql);
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int rs_count = 0;
			
			while (rs.next()){
//				rs_count ++;
				Organisation orgIDNObj = new Organisation();
				orgIDNObj.setOrgnId(rs.getInt(DB_COL_ORGN_ID));
				orgIDNObj.setOrgnName(rs.getString(DB_COL_ORGN_NAME));
				orgIDNObj.setOrgnType(rs.getInt(DB_COL_ORGN_TYPE));
				orgIDNObj.setAddCity(rs.getString(DB_COL_ORGN_CITY));
				
				String sServ = rs.getString(DB_COL_ORGN_SERVICETYPES);
				if (sServ != null) {
					System.out.println("Reading services " + sServ);
					orgIDNObj.setServiceTypes(stringToVector(sServ));
				}
				else  {System.out.println("sServ is null - did not read Services from table");}

				System.out.println(rs.getInt(DB_COL_ORGN_ID) + " Name Orgn : " + rs.getString(DB_COL_ORGN_NAME));
				vOrgIDN.add(orgIDNObj);
			}
//			for (int x = 0; x < vOrgIDN.size(); x++)
//				System.out.println("Organisation Names: " + vOrgIDN.elementAt(x).getOrgnName());
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
		
		return vOrgIDN;
	}
	
//	public Organisation getOrgnDetails(String sOrganisation) { 									// ND added on 02nd Sep 16
	public Organisation getOrgnDetails(Integer sOrganisationID) { 									// ND edited on 12th Sep 16
		Connection con = null; 
		Organisation orgnObj = new Organisation();
		Vector <Organisation> vOrgnDetails = new Vector<Organisation>();
		String sql= "Select * from " +
					DB_TBL_ORGANISATION + " Where " + DB_COL_ORGN_ID + " = " + sOrganisationID ;
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int rs_count = 0;
			
			while (rs.next()){
				rs_count ++;
				orgnObj.setOrgnId(rs.getInt(DB_COL_ORGN_ID));
				orgnObj.setOrgnName(rs.getString(DB_COL_ORGN_NAME));
				orgnObj.setAdd1(rs.getString(DB_COL_ORGN_ADD1));
				orgnObj.setAdd2(rs.getString(DB_COL_ORGN_ADD2));
				orgnObj.setAdd3(rs.getString(DB_COL_ORGN_ADD3));
				orgnObj.setAddCity(rs.getString(DB_COL_ORGN_CITY));
				orgnObj.setAddPin(rs.getString(DB_COL_ORGN_PIN));
				orgnObj.setOrgnType(rs.getInt(DB_COL_ORGN_TYPE));

				String sServ = rs.getString(DB_COL_ORGN_SERVICETYPES);
				if (sServ != null) {
					System.out.println("Reading services for saving " + sServ);
					orgnObj.setServiceTypes(stringToVector(sServ));
				}
				else  {System.out.println("sServ is null - did not read Services from table");}

				System.out.println(rs.getString(DB_COL_ORGN_ID) + " Name Orgn : " + rs.getString(DB_COL_ORGN_NAME));
				vOrgnDetails.add(orgnObj);
			}
			System.out.println("Number of organisations with the same name : " + rs_count);	
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
		
		return orgnObj;

	}

	public Vector getCareHome() { 
		Connection con = null; 
		Vector<String> vCareHome = new Vector<String>();					
		
		String sql= "Select * from " +
					DB_TBL_CAREHOME +
					" ORDER BY " + DB_COL_CAREHOME_NAME;
		System.out.println("The retreival query is ------------- " + sql);		
		//write database queries to retrieve the info 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				
				System.out.println("rs.getString(DB_COL_CAREHOME_ID): " + rs.getString(DB_COL_CAREHOME_ID));
				
				vCareHome.add(rs.getString(DB_COL_CAREHOME_NAME));
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
	

	// start ND added 22nd July 16	
	public String saveAddiction(Vector vAddiction) { 
	Connection con = null; 
	
	String sql_delAddict = new String("Truncate " + DB_TBL_ADDICTION ); 
	String sbSqlInsert = new String();
	
	try{
		sbSqlInsert = null;
		con = getConnection();
		Statement st_del = con.createStatement();
		Statement st_updt = con.createStatement();
		System.out.println(sql_delAddict);
		
		Integer rd = st_del.executeUpdate(sql_delAddict);
		System.out.println( rd + " records deleted");
		st_del.close();
		
		for (int i = 0; i< vAddiction.size(); i++) {
			sbSqlInsert = "Insert into " + DB_TBL_ADDICTION  + " (" +  DB_COL_ADDICTION  + ") " +
					"values ( '"  + vAddiction.elementAt(i) + "')";
			st_updt.addBatch(sbSqlInsert);
			System.out.println("record inserted " + i);
		}	
		st_updt.executeBatch();
		st_updt.close();
		con.close();
		return "SUCCESS";
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
	return "ERROR";
}

	public String saveCopingSkill(Vector vCopingSkill) { 
		Connection con = null; 
		
		String sql_delCopSkill = new String("Truncate " + DB_TBL_COPINGSKILL ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delCopSkill);
			
			Integer rd = st_del.executeUpdate(sql_delCopSkill);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vCopingSkill.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_COPINGSKILL  + " (" +  DB_COL_COPINGSKILL  + ") " +
						"values ( '"  + vCopingSkill.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}


	public String saveHobby(Vector vHobby) { 
		Connection con = null; 
		
//		String sql_delHobb = new String("Delete from " + DB_TBL_RECREATION ); 
		String sql_delHobb = new String("Truncate " + DB_TBL_RECREATION ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delHobb);
			st_del.executeUpdate(sql_delHobb);
			
//			Integer rd = st_del.executeUpdate(sql_delHobb);
//			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vHobby.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_RECREATION  + " (" +  DB_COL_RECREATIONACTIVITY  + ") " +
						"values ( '"  + vHobby.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}

	public String saveLanugage(Vector vLanguage) { 
		Connection con = null; 
		
		String sql_delLang = new String("Truncate " + DB_TBL_LANGUAGE ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delLang);
			
			Integer rd = st_del.executeUpdate(sql_delLang);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vLanguage.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_LANGUAGE  + " (" +  DB_COL_LANGUAGE  + ") " +
						"values ( '"  + vLanguage.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}

	public String saveNFEdu(Vector vNFEdu) { 
		Connection con = null; 
		
//		String sql_delNFEdu = new String("Delete from " + DB_TBL_NONFORMALEDUCATION ); 
		String sql_delNFEdu = new String("Truncate " + DB_TBL_NONFORMALEDUCATION ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delNFEdu);
			
			Integer rd = st_del.executeUpdate(sql_delNFEdu);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vNFEdu.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_NONFORMALEDUCATION  + " (" +  DB_COL_NONFORMALEDUCATION  + ") " +
						"values ( '"  + vNFEdu.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}
	
	public String saveReligion(Vector vReligion) { 
		Connection con = null; 
		
		String sql_delReligion = new String("Truncate " + DB_TBL_RELIGION ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delReligion);
			
			Integer rd = st_del.executeUpdate(sql_delReligion);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vReligion.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_RELIGION  + " (" +  DB_COL_RELIGION  + ") " +
						"values ( '"  + vReligion.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}

	public String saveRelationship(Vector vRelate) { 
		Connection con = null; 
		
		String sql_delRelate = new String("Truncate " + DB_TBL_RELATIONSHIP ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delRelate);
			
			Integer rd = st_del.executeUpdate(sql_delRelate);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vRelate.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_RELATIONSHIP  + " (" +  DB_COL_RELATIVETYPE  + ") " +
						"values ( '"  + vRelate.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}

	// end ND added 22nd July 16
	
	// start ND added on 06th Aug 16
	public String saveServiceTypes(Vector vServType) { 
		Connection con = null; 
		
		String sql_delServ = new String("Truncate " + DB_TBL_SERVICETYPES ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delServ);
			
			Integer rd = st_del.executeUpdate(sql_delServ);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vServType.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_SERVICETYPES  + " (" +  DB_COL_SERVICETYPES  + ") " +
						"values ( '"  + vServType.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
		return "ERROR";
	}

	public String saveCareHome(Vector vCareHome) { 
		Connection con = null; 
		
		String sql_delKHome = new String("Truncate " + DB_TBL_CAREHOME ); 
		String sbSqlInsert = new String();
		
		try{
			sbSqlInsert = null;
			con = getConnection();
			Statement st_del = con.createStatement();
			Statement st_updt = con.createStatement();
			System.out.println(sql_delKHome);
			
			Integer rd = st_del.executeUpdate(sql_delKHome);
			System.out.println( rd + " records deleted");
			st_del.close();
			
			for (int i = 0; i< vCareHome.size(); i++) {
				sbSqlInsert = "Insert into " + DB_TBL_CAREHOME  + " (" +  DB_COL_CAREHOME_NAME  + ") " +
						"values ( '"  + vCareHome.elementAt(i) + "')";
				st_updt.addBatch(sbSqlInsert);
				System.out.println("record inserted " + i);
			}	
			st_updt.executeBatch();
			st_updt.close();
			con.close();
			return "SUCCESS";
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
	
	
	// end ND added on 06th Aug 16

	public String saveOrgnDetails(Organisation orgnObj) { 													// ND added on 20th Aug 2016
		Connection con = null; 
		Integer orgnID ;
//		Vector <String> vOrganisation = new Vector<String>();	
//		String sServices = new String();
		
		StringBuffer sql_ins= new StringBuffer("Insert into " +
				DB_TBL_ORGANISATION );
		
		String[] colNamesOrgn = {DB_COL_ORGN_NAME, DB_COL_ORGN_ADD1, DB_COL_ORGN_ADD2, DB_COL_ORGN_ADD3, 
									DB_COL_ORGN_CITY, DB_COL_ORGN_PIN, DB_COL_ORGN_SERVICETYPES };
		String[] colValOrgn = {noNull(orgnObj.getOrgnName()), (orgnObj.getAdd1()), noNull(orgnObj.getAdd2()),
								noNull(orgnObj.getAdd3()), noNull(orgnObj.getAddCity()), 
								noNull(orgnObj.getAddPin()), noNull(joinVStrings(orgnObj.getServiceTypes()))};
		// yet to add vector service types.
		sql_ins.append(createInsertQry(colNamesOrgn, colValOrgn));		
		

		try{
			con = getConnection();

			Statement st = con.createStatement();
			
			System.out.println("The insert query is ------------- " + sql_ins);	
			int recsUpdt = st.executeUpdate(sql_ins.toString());
			
			st.close();	
			con.close();
			return "SUCCESS";
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

	public String updtOrgnDetails(Organisation orgnObj) { 	// ND added on 06th Sep 2016
	Connection con = null; 
	Integer orgnID ;
//	Vector <String> vOrganisation = new Vector<String>();	
//	String sServices = new String();
	
	
	StringBuffer sql = new StringBuffer("UPDATE " +
			DB_TBL_ORGANISATION + " SET ");

	try{
		con = getConnection();
		
		orgnID = orgnObj.getOrgnId();
		sql.append(DB_COL_ORGN_ADD1);
		sql.append(" = '" + orgnObj.getAdd1() +"'");
		sql.append(",");
		sql.append(DB_COL_ORGN_ADD2);
		sql.append(" = '"  + orgnObj.getAdd2() +"'");
		sql.append(",");
		sql.append(DB_COL_ORGN_ADD3);
		sql.append(" = '" + orgnObj.getAdd3() +"'");
		sql.append(",");
		sql.append(DB_COL_ORGN_CITY);
		sql.append(" = '" + orgnObj.getAddCity() +"'");
		sql.append(",");
		sql.append(DB_COL_ORGN_PIN);
		sql.append(" = '" + orgnObj.getAddPin() +"'");
		sql.append(",");

		sql.append(DB_COL_ORGN_SERVICETYPES);
		sql.append(" = '" + orgnObj.getServiceTypes() +"'");
		
		sql.append(" WHERE ");
		sql.append(DB_COL_ORGN_ID);
		sql.append(" = ");
		sql.append(orgnID);
		
		System.out.println("update Organisation details query string : " + sql.toString());

		Statement st = con.createStatement();
		int n = st.executeUpdate(sql.toString());
		
		st.close();	
		con.close();
		return "SUCCESS";
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
	
public Boolean delOrgn(Vector vOrgnID){								// ND added on 12th Sep 16
	
		Connection con = null; 
		
		String sql= new String("Delete from " +
					DB_TBL_ORGANISATION +" where " + DB_COL_ORGN_ID +
					" = " );
		try{
			con = getConnection();
			Statement st = con.createStatement();
			String sSql = new String();
			String sPunc = new String();
			for (int i = 0; i < vOrgnID.size(); i++) {
				sql = "Delete from " + DB_TBL_ORGANISATION + " where " + 
							DB_COL_ORGN_ID + " = " + ((Integer)vOrgnID.elementAt(i));
				st.addBatch(sql);
				System.out.println(sql);
			}
			
			st.executeBatch();
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



//start ND added on 24th Sep 16
public Vector getTherapy() { 
	Connection con = null; 
	Vector<CounsellingTherapy> vCounslTherapy = new Vector<CounsellingTherapy>();
	
	String sql= "Select * from " +
				DB_TBL_COUNSELINGTHERAPY  +
				" ORDER BY " + DB_COL_COUNSELINGTHERAPY_ID;
	System.out.println("The retreival query is ------------- " + sql);		
	//write database queries to retrieve the info 
	try{
		con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()){
			
			CounsellingTherapy cnslTherapy = new CounsellingTherapy();
			System.out.println("rs.getString(DB_COL_COUNSELINGTHERAPY_ID): " + rs.getString(DB_COL_COUNSELINGTHERAPY_ID));
			
			cnslTherapy.setTherapyObjId(rs.getString(DB_COL_COUNSELINGTHERAPY_ID));
			cnslTherapy.setTherapyDesc(rs.getString(DB_COL_COUNSELINGTHERAPYDESC));
			
			vCounslTherapy.add(cnslTherapy);
			System.out.println("retrieved counseling therapy " + cnslTherapy.getTherapyObjId());
			
		}
		for (int i = 0; i < vCounslTherapy.size(); i++)
			System.out.println("Counselling Therapy: " + i + " " + vCounslTherapy.elementAt(i) );
	
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
	
	return vCounslTherapy;

}

public String saveCounselingTherapy(Vector vCnslTherapy) { 
	Connection con = null; 
	
	String sql_delCnslTherapy = new String("Truncate " + DB_TBL_COUNSELINGTHERAPY ); 
	String sbSqlInsert = new String();
	
	try{
		sbSqlInsert = null;
		con = getConnection();
		Statement st_del = con.createStatement();
		Statement st_updt = con.createStatement();
		System.out.println(sql_delCnslTherapy);
		
		Integer rd = st_del.executeUpdate(sql_delCnslTherapy);
		System.out.println( rd + " records deleted");
		st_del.close();
		
		for (int i = 0; i< vCnslTherapy.size(); i++) {
			sbSqlInsert = "Insert into " + DB_TBL_COUNSELINGTHERAPY  + " (" +  DB_COL_COUNSELINGTHERAPY_ID  + ") " +
					"values ( '"  + vCnslTherapy.elementAt(i) + "')";
			st_updt.addBatch(sbSqlInsert);
			System.out.println("record inserted " + i);
		}	
		st_updt.executeBatch();
		st_updt.close();
		con.close();
		return "SUCCESS";
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
	return "ERROR";
}
// end ND added on 24th Sep 16
}
