package com.act.server.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.act.client.CnsleeSymptChkListsPanel;
import com.act.common.ACEDefines;
import com.act.common.AbuseIndivObj;
import com.act.common.CounseleeHistoryObj;
import com.act.common.CounseleeRelativeIndivObj;
import com.act.common.Counsellee;
import com.act.common.EducationHistIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.LangFluency;
import com.act.common.LegalHistIndivObj;
import com.act.common.MentalStatusIndivObj;
import com.act.common.PersonName;
import com.act.common.PhysicalHistIndivObj;
import com.act.common.SocialHistIndivObj;
import com.act.common.StrenghtIndivObj;
import com.act.common.SummaryIndivObj;
import com.act.common.VocationFinIndivObj;

public class CounselleeMSDB extends CounselleeDB{

	private static final String[] StringBuffer = null;

	private static CounselleeMSDB counselleeMSDB;

	static Random ran = new Random(); //this logic needs to change for creating indiv ID
	private CounselleeMSDB(){
		
	}
	
	//Singleton
	public static CounselleeMSDB getInstance(){
		if (counselleeMSDB == null){
			counselleeMSDB = new CounselleeMSDB();
		}
		
		return counselleeMSDB;
	}


	public Vector<Counsellee> getCounsleeList(String CnslrId, Hashtable<String, String> htOptions){
		Connection con = null;
		
		Vector<Counsellee> vCounsellee = new Vector<Counsellee>();
		
		String sql= "Select * from " +
					DB_TBL_INDIV ;
	
		//form the where condition
		StringBuffer sbWhere = new StringBuffer();
		if (htOptions != null){
			
			if (htOptions.containsKey(ACEDefines.COUNSELLE_NAME)){
				String val = htOptions.get(ACEDefines.COUNSELLE_NAME);
				
				sbWhere.append(" Where ");
				
				sbWhere.append(likeClause(DB_COL_INDIV_NAME , val));
			}
			if (htOptions.containsKey(ACEDefines.COUNSELLOR_NAME)){
				String val = htOptions.get(ACEDefines.COUNSELLOR_NAME);
				
				if (sbWhere.length() <1)
					sbWhere.append(" Where ");
				else
					sbWhere.append(" And ");
				
				sbWhere.append(likeClause(DB_COL_INDIV_CNSLR_NAME, val));
			}
			if (htOptions.containsKey(ACEDefines.COUNSELLE_ORGANIZATION)){
				String val = htOptions.get(ACEDefines.COUNSELLE_ORGANIZATION);
				
				if (sbWhere.length() <1)
					sbWhere.append(" Where ");
				else
					sbWhere.append(" And ");
				
				sbWhere.append(likeClause(DB_COL_INDIV_PARTNER_ORGN, val));
			}
			if (htOptions.containsKey(ACEDefines.COUNSELLE_OTHER_NAME)){
				String val = htOptions.get(ACEDefines.COUNSELLE_OTHER_NAME);
				
				if (sbWhere.length() <1)
					sbWhere.append(" Where ");
				else
					sbWhere.append(" And ");
				
				sbWhere.append(likeClause(DB_COL_INDIV_OTHER_NAME,  val));
			}
		}

		//Authenticate the user   //TODO
		//1. if cnslrId is ADMIN, no need of condition,
		//2. else show only that user's counsellee details
		if (!UserMSDB.getInstance().isAdmin(CnslrId)){
			if (sbWhere.length() <1)
				sbWhere.append(" Where ");
			else
				sbWhere.append(" And ");
			
			sbWhere.append(DB_COL_INDIV_CNSLR_NAME + " = '"+  CnslrId +"'");
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
				
				Counsellee counselee = new Counsellee();
				System.out.println("rs.getString(DB_COL_INDIV_ID): " + rs.getString(DB_COL_INDIV_ID));
				
				counselee.setCaseNumber(rs.getString(DB_COL_INDIV_ID));
				counselee.setName(new PersonName(rs.getString(DB_COL_INDIV_NAME)));
				counselee.setParentOrg(rs.getString(DB_COL_INDIV_PARTNER_ORGN));
				counselee.setDate(rs.getString(DB_COL_INDIV_DATE_ASSESSMENT));
				counselee.setDob(rs.getString(DB_COL_INDIV_DOB));
				counselee.setDtTerm(rs.getString(DB_COL_INDIV_DTTERM));
				counselee.setGender(rs.getString(DB_COL_INDIV_GENDER));
				counselee.setLocation(rs.getString(DB_COL_INDIV_HOME));
				counselee.setOtherName(rs.getString(DB_COL_INDIV_OTHER_NAME));

				vCounsellee.add(counselee);
				
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
		
		return vCounsellee;
	}
	
	public boolean deleteCounsellee(Vector vCaseNumber){
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Delete from " +
					DB_TBL_INDIV +" where " + DB_COL_INDIV_ID +
					" in (" );
// start ND added 12th Mar 16
		StringBuffer sql_abu = new StringBuffer("Delete from " + DB_TBL_ABUSEHIST + 
				" where " + DB_COL_ABUSEHIST_INDIV_ID + " in (" );
		
		StringBuffer sql_edu = new StringBuffer("Delete from " + DB_TBL_EDUHIST + 
				" where " + DB_COL_EDUHIST_INDIV_ID + " in (" );
		
		StringBuffer sql_fEnv = new StringBuffer("Delete from " + DB_TBL_FAMENV + 
				" where " + DB_COL_FAMENV_INDIV_ID + " in (" );
		
		StringBuffer sql_fReln = new StringBuffer("Delete from " + DB_TBL_CNSLRELN + 
				" where " + DB_COL_CNSLRELN_INDIV_ID + " in (" );
		
		StringBuffer sql_phy = new StringBuffer("Delete from " + DB_TBL_PHYSICALHIST + 
				" where " + DB_COL_PHYSICALHIST_INDIV_ID + " in (" );
		
		StringBuffer sql_psy = new StringBuffer("Delete from " + DB_TBL_MENTALSTAT + 
				" where " + DB_COL_MENTALSTAT_INDIV_ID + " in (" );
		
		StringBuffer sql_soc = new StringBuffer("Delete from " + DB_TBL_SOCIALHIST + 
				" where " + DB_COL_SOCHIST_INDIV_ID + " in (" );
		
		StringBuffer sql_stg = new StringBuffer("Delete from " + DB_TBL_CNSLSTRENGTH + 
				" where " + DB_COL_CNSLSTRENGTH_INDIV_ID + " in (" );
		
		StringBuffer sql_sum = new StringBuffer("Delete from " + DB_TBL_CNSLSUMM + 
				" where " + DB_COL_CNSLSUMM_INDIV_ID + " in (" );
		
		StringBuffer sql_voc = new StringBuffer("Delete from " + DB_TBL_VOCHIST + 
				" where " + DB_COL_VOCHIST_INDIV_ID + " in (" );
		
		StringBuffer sql_leg = new StringBuffer("Delete from " + DB_TBL_LEGAL + 
				" where " + DB_COL_LEGAL_INDIV_ID + " in (" );
		
// end ND added 12th Mar 16
		

// start ND added 25th Mar 16		
		//write database queries to delete the user 
		try{
			String sSql = new String();
			String sPunc = new String();
			for (int i = 0; i < vCaseNumber.size(); i++) {
				if (i>0)
//					sql.append(", '"); 
					sPunc = ", '";
				else
					sPunc = "'";
//				sql.append("'" + vCaseNumber.elementAt(i) + "'");
				sql.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_abu.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_edu.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_fReln.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_fEnv.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_phy.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_psy.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_soc.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_stg.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_sum.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_voc.append(sPunc + vCaseNumber.elementAt(i) + "'");
				sql_leg.append(sPunc + vCaseNumber.elementAt(i) + "'");
				
			}
			con = getConnection();
			Statement st = con.createStatement();
			
			for (int s = 0; s <= 11; s++) {
				switch (s) {
				case 0:
					sSql = sql.toString();
					break;
				case 1:
					sSql = sql_abu.toString();
					System.out.println("Abuse table sql: " + sSql);
					break;
				case 2:
					sSql = sql_edu.toString();
					break;
				case 3:
					sSql = sql_fReln.toString();
					break;
				case 4:
					sSql = sql_fEnv.toString();
					break;
				case 5:
					sSql = sql_phy.toString();
					break;
				case 6:
					sSql = sql_psy.toString();
					break;
				case 7:
					sSql = sql_soc.toString();
					break;
				case 8:
					sSql = sql_stg.toString();
					break;
				case 9:
					sSql = sql_sum.toString();
					break;
				case 10:
					sSql = sql_voc.toString();
					break;
				case 11:
					sSql = sql_leg.toString();
					break;
				default:
					System.out.println("Computer error");
					break;
				}
				sSql = sSql + ")";
				st.addBatch(sSql);
				System.out.println(sSql);
			}
			
//			sql.append(")");
//			sql_abu.append(")");
//			sql_edu.append(")");
//			sql_fReln.append(")");
//			sql_fEnv.append(")");
//			sql_phy.append(")");
//			sql_soc.append(")");
//			sql_stg.append(")");
//			sql_sum.append(")");
//			sql_voc.append(")");
//			sql_leg.append(")");
//			sql_psy.append(")");
			
//			System.out.println(sql.toString());
//			st.executeUpdate(sql.toString());
			
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
	// end ND added 25th Mar 16
	public String  saveCounselee(Counsellee cnslee, CounseleeHistoryObj cnslHist){				// ND edited on 1st Dec
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("Insert into " +
					DB_TBL_INDIV );
		
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			
			//generate counsellee ID
			strCnsl_ID = generateCounselleeId(cnslee);
			
			//create the insert query statement values
			sql.append(" values (");
			cnslee.setCaseNumber(strCnsl_ID);													// ND edited on 1st Dec 
			cnslHist.setCounseleeID(strCnsl_ID);
			sql.append("'" + strCnsl_ID + "'");					
			sql.append(",");
			sql.append("'" +cnslee.getParentOrg() +"'");
			sql.append(",");
			sql.append("'"  +cnslee.getOtherName() +"'");
			sql.append(",");
			sql.append("'" +cnslee.getDate() +"'");
			sql.append(",");
			sql.append("'" + cnslee.getAge() + "'");
			sql.append(",");
			sql.append("'" +cnslee.getName().toString() +"'");
			sql.append(",");
			sql.append("'Marie DSouza'"); //TODO change to actual value
			sql.append(",");
			sql.append("'" + cnslee.getLocation()+ "'");								// ND added on 2nd Apr 16
			sql.append(",");
			sql.append("'" + cnslee.getDob() + "'");											//  ND added on 2nd Apr 16
			sql.append(",");
			sql.append("'" + cnslee.getDtTerm() + "'");											//  ND added on 2nd Apr 16
			sql.append(",");
			sql.append("'" + cnslee.getGender() + "'");											// ND added 02nd Apr 16
			sql.append(" )");
			
			System.out.println("insert query string : " + sql.toString());
			
			
			Statement st = con.createStatement();
			int n = st.executeUpdate(sql.toString());
			
			con.close();
			saveCounseleeHistory(cnslHist);
			
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

	private String generateCounselleeId(Counsellee cnslee) {
		String strCnsl_ID;
		Date date = new Date();
		DateFormat dtformat = new SimpleDateFormat("yyyymmdd");								// ND edited on 1st Dec
		strCnsl_ID =cnslee.getParentOrg().substring(0,3) +									// ND edited on 1st Dec
						dtformat.format(date) +ran.nextInt(3);
		return strCnsl_ID;
	}
	
	// ND added on 27th Oct
	public String  saveCounseleeHistory(CounseleeHistoryObj cnslHist){
		Connection con = null; 
		
		
		AbuseIndivObj abuObj = cnslHist.getabuseIndivObj();
		EducationHistIndivObj eduObj = cnslHist.geteduIndivObj();
		Vector vCnslRelnObj = cnslHist.getvCnslRelnObj();	
		FamilyEnvIndivObj fEnvObj = cnslHist.getfamEnvIndivObj();
		PhysicalHistIndivObj phyHistObj = cnslHist.getphyIndivObj();
		MentalStatusIndivObj menStatObj = cnslHist.getmenStatIndivObj();
		SocialHistIndivObj socHistObj = cnslHist.getsocIndivObj();
		StrenghtIndivObj cnslStrObj = cnslHist.getstrengthIndivObj();
		SummaryIndivObj cnslSumObj = cnslHist.getsummIndivObj();
		VocationFinIndivObj vocObj = cnslHist.getvocIndivObj();
		LegalHistIndivObj legalHistObj = cnslHist.getLegalHistIndivObj();				// ND added on 18th Dec

		try{
			String cnsl_ID = cnslHist.getCounseleeID();											// ND edited on 1st Dec
	
			String[] colNamesAbuseHist = {DB_COL_ABUSEHIST_INDIV_ID, DB_COL_ABUSEHIST_VERBAL, DB_COL_ABUSEHIST_PHYSICAL,
										  DB_COL_ABUSEHIST_SEXUAL, DB_COL_ABUSEHIST_NEGLECT, DB_COL_ABUSEHIST_ABUSECOMMENTS
										 };
			String[] colValAbuseHist = {cnsl_ID, noNull(abuObj.getAbuseVerbal()), noNull(abuObj.getAbusePhys()),
										noNull(abuObj.getAbuseSex()), noNull(abuObj.getAbuseNeglect()), noNull(abuObj.getAbuseComments())
									   };
			String sql_abuHist = createInsertQry( colNamesAbuseHist, colValAbuseHist) ;
			sql_abuHist = "Insert into " + DB_TBL_ABUSEHIST + sql_abuHist;
	//		StringBuffer sql_abu = createInsertQry(DB_TBL_ABUSEHIST, 
					//new StringBuffer("Insert into " +
						//DB_TBL_ABUSEHIST);
			
			String[] colNamesEduHist = {DB_COL_EDUHIST_INDIV_ID, DB_COL_EDUHIST_ATTENDEDSCHOOL, DB_COL_EDUHIST_WHEREED,
										DB_COL_EDUHIST_HIGHSTD, DB_COL_EDUHIST_LITERACYCLASSES, DB_COL_EDUHIST_SCHOOLEXPERIENCE,
										DB_COL_EDUHIST_CONTINUESTUDIES, DB_COL_EDUHIST_NON_FORMALED, DB_COL_EDUHIST_EDCOMMENTS
									   };
			String[] colValEduHist = {cnsl_ID, Boolean.toString(eduObj.isEduAttend()), noNull(eduObj.getEduWhere()), 			// ND edited on 4th Dec
									  noNull(eduObj.getEduStd()), Boolean.toString(eduObj.isEduLitClass()), noNull(eduObj.getEduSchExp()),		
									  Boolean.toString(eduObj.isEduCont()), noNull(joinVStrings(eduObj.getEduNonForm())), noNull(eduObj.getEduComm())		
									 };													// ND edited 12th Mar 16
			String sql_eduHist = createInsertQry(colNamesEduHist, colValEduHist);
			sql_eduHist = "Insert into " + DB_TBL_EDUHIST + sql_eduHist;
	////		StringBuffer sql_edu = new StringBuffer("Insert into " + 
	////					DB_TBL_EDUHIST);
	//		
			String[] colNamesCnslReln = { DB_COL_CNSLRELN_INDIV_ID, DB_COL_CNSLRELN_RELNNAME, DB_COL_CNSLRELN_RELNSTRENGTH,
										  DB_COL_CNSLRELN_RELNAGE, DB_COL_CNSLRELN_RELNAWARE, 
										  DB_COL_CNSLRELN_RELNCOMMENTS, DB_COL_CNSLRELN_RELATIONSHIP, DB_COL_CNSLRELN_RELNPROFESSION
										};
			String[] sql_cnslReln = new String[5];
			if (vCnslRelnObj != null){
				for (int i = 0; i < vCnslRelnObj.size(); i++)
				{	
					CounseleeRelativeIndivObj relObj = (CounseleeRelativeIndivObj) vCnslRelnObj.elementAt(i);
					String[] colValCnslReln = {cnsl_ID, noNull(relObj.getRelName()), noNull(relObj.getRelStrength()),
											   noNull(relObj.getRelAge()), Boolean.toString(relObj.isRelAwareOfVictimsSituation()),
											   noNull(relObj.getRelComments()), noNull(relObj.getRelationship()), noNull(relObj.getRelProfession())};
					sql_cnslReln[i] = createInsertQry( colNamesCnslReln, colValCnslReln);
					sql_cnslReln[i] = "Insert into " + DB_TBL_CNSLRELN + sql_cnslReln[i];
				}
			}
			
	////		StringBuffer sql_vCrel = new StringBuffer("Insert into " +
	////				DB_TBL_CNSLRELN);											vector so number of inserts
	//		
	
			String[] colNamesFamEnv = {DB_COL_FAMENV_INDIV_ID, DB_COL_FAMENV_GETSALONG,
					 					DB_COL_FAMENV_PHYABUSE, DB_COL_FAMENV_VERBALABUSE, DB_COL_FAMENV_SEXUALABUSE,
					 					DB_COL_FAMENV_SUBSTANCEABUSE, DB_COL_FAMENV_NEGLECT, DB_COL_FAMENV_PROSTITUTION,
					 					DB_COL_FAMENV_FAMCOMMENTS};
			String[] colValFamEnv = { cnsl_ID, noNull(fEnvObj.getFamEnvGetsAlong()), noNull(fEnvObj.getFamEnvPhyAb()),				// ND edited on 4th Dec
									  noNull(fEnvObj.getFamEnvVerAb()), noNull(fEnvObj.getFamEnvSexAb()), noNull(fEnvObj.getFamEnvSubAb()),
									  noNull(fEnvObj.getFamEnvNeg()), noNull(fEnvObj.getFamEnvPros()), noNull(fEnvObj.getFamEnvComm())		
									};
			String sql_famEnv = createInsertQry( colNamesFamEnv, colValFamEnv);			// ND edited on 11th Dec.
			sql_famEnv = "Insert into " + DB_TBL_FAMENV + sql_famEnv;
	////		StringBuffer sql_fenv= new StringBuffer("Insert into " +
	////					DB_TBL_FAMENV);
	//
	//
	//		// DB_COL_PHYSICALHIST_CURRHEALTHSPECS_CNSR and DB_COL_PHYSICALHIST_CURRHEALTHSPECS_CNSL could be entered in the Comments field
			String[] colNamesPhysicalHist = {DB_COL_PHYSICALHIST_INDIV_ID, DB_COL_PHYSICALHIST_CURRHEALTH_CNSR,
											 DB_COL_PHYSICALHIST_CURRHEALTH_CNSL, 
											 DB_COL_PHYSICALHIST_SERIOUSILLINJDISABLE,
											DB_COL_PHYSICALHIST_ADDICTIONS, DB_COL_PHYSICALHIST_BIRTHCONTROLHIST, 
											DB_COL_PHYSICALHIST_PREGNANCYHIST, DB_COL_PHYSICALHIST_ABORTIONHIST,
											DB_COL_PHYSICALHIST_STTHIST, DB_COL_PHYSICALHIST_STITREATMENT, 
											DB_COL_PHYSICALHIST_WITHDRAWAL,
											DB_COL_PHYSICALHIST_LEARNDISABLE, DB_COL_PHYSICALHIST_ADHDISORDER, DB_COL_PHYSICALHIST_ADDISORDER, 
											DB_COL_PHYSICALHIST_AUTISM, DB_COL_PHYSICALHIST_OTHERDISORDER, DB_COL_PHYSICALHIST_BEENCOUNSELED,
											DB_COL_PHYSICALHIST_PSYCHMEDSCURR,									
											DB_COL_PHYSICALHIST_PHYSICCOMMENTS
											};
			// phyHistObj.getPhyHistCurrSpecsYou() and phyHistObj.getPhyHistCurrSpecs() could be entered in the Comments field
			String[] colValPhysicalHist = {cnsl_ID, noNull(phyHistObj.getPhyHistCurrYou()), 
										   noNull(phyHistObj.getPhyHistCurr()),
										   noNull(phyHistObj.getPhysicalHistSerious()),
										   noNull(joinVStrings(phyHistObj.getPhyHistAddict())), Boolean.toString(phyHistObj.isPhyHistBirthCtrl()),
										   Boolean.toString(phyHistObj.isPhyHistPreg()), Boolean.toString(phyHistObj.isPhyHistAbort()),
										   Boolean.toString(phyHistObj.isPhyHistSTI()), Boolean.toString(phyHistObj.isPhyHistSTITreat()),  
										   noNull(phyHistObj.getWithdrawalSymptoms()),
										   Boolean.toString(phyHistObj.isLearnDisable()), Boolean.toString(phyHistObj.isADHDisorder()), Boolean.toString(phyHistObj.isADDisorder()),
										   Boolean.toString(phyHistObj.isAutism()), Boolean.toString(phyHistObj.isOtherDisorder()), Boolean.toString(phyHistObj.isBeenCounseled()),
										   Boolean.toString(phyHistObj.isPsychMedsCurr()),
										   noNull(phyHistObj.getPhyHistComments())
										  };
			String sql_phyHist = createInsertQry( colNamesPhysicalHist, colValPhysicalHist);		// ND edited on 14 Dec
			sql_phyHist = "Insert into " + DB_TBL_PHYSICALHIST + sql_phyHist;
	////		StringBuffer sql_phy  = new StringBuffer("Insert into " +
	////					DB_TBL_PHYSICALHIST);
	//		
			String[] colNamesMentalStat = {DB_COL_MENTALSTAT_INDIV_ID, DB_COL_MENTALSTAT_APPHYGIENE, DB_COL_MENTALSTAT_MOVEMENT,
										   DB_COL_MENTALSTAT_AFFECT, DB_COL_MENTALSTAT_MOOD, DB_COL_MENTALSTAT_SPEECH,
										   DB_COL_MENTALSTAT_ATTITUDE, DB_COL_MENTALSTAT_INTELLECTUALLEVEL, DB_COL_MENTALSTAT_IMPULSECONTROL,
										   DB_COL_MENTALSTAT_UNDERSTANDING, DB_COL_MENTALSTAT_ORIENTATION, DB_COL_MENTALSTAT_MEMORY,
										   DB_COL_MENTALSTAT_ATTENTION, DB_COL_MENTALSTAT_SUICIDALIDEATION, DB_COL_MENTALSTAT_HOMICIDALIDEATION,
										   DB_COL_MENTALSTAT_THOUGHTPROCESS, DB_COL_MENTALSTAT_HALLUCINATION, DB_COL_MENTALSTAT_PSYCHOCOMMENTS
										   };
			String[] colValMentalStat = {cnsl_ID,  noNull(menStatObj.getMentalStAppearHyg()),  noNull(menStatObj.getMentalStMovement()),
										 noNull(menStatObj.getMentalStAffect()),  noNull(menStatObj.getMentalStMood()),  noNull(menStatObj.getMentalStSpeech()),
										 noNull(menStatObj.getMentalStAttitude()),  noNull(menStatObj.getMentalStIntellect()),  noNull(menStatObj.getMentalStImpulse()),
										 noNull(menStatObj.getMentalStUnderstanding()), noNull(joinVStrings(menStatObj.getMentalStOrientation())), 
										 noNull(menStatObj.getMentalStMemory()),
										 noNull(menStatObj.getMentalStAttention()), noNull(menStatObj.getMentalStSuicidal()), noNull(menStatObj.getMentalStHomicidal()),
										 noNull(menStatObj.getMentalStThoughtProc()), noNull(joinVStrings(menStatObj.getMentalStHallucination())), 
								//		 noNull(menStatObj.getMentalStComments()) 	has been removed from object on 22nd Feb 16
										};
			String sql_menStat = createInsertQry(colNamesMentalStat, colValMentalStat);				// ND edited on 14 Dec
			sql_menStat = "Insert into " + DB_TBL_MENTALSTAT + sql_menStat;
	//	//	StringBuffer sql_psy = new StringBuffer("Insert into " +
	//	//				DB_TBL_MENTALSTAT);
	//	
			String[] colNamesSocialHist = {DB_COL_SOCHIST_INDIV_ID, DB_COL_SOCHIST_LIVEDAT, DB_COL_SOCHIST_RELWITHCOMM,
										   DB_COL_SOCHIST_HERFRIENDS, DB_COL_SOCHIST_RELWITHFRIENDS, DB_COL_SOCHIST_INVOLVEMENT,
										   DB_COL_SOCHIST_SOCCOMMENTS, DB_COL_SOCHIST_RECREATIONANDFUN, DB_COL_SOCHIST_RELIGIONBELIEF
										  };
			String[] colVValSocialHist = {cnsl_ID, noNull(socHistObj.getSocLivedAt()), noNull(socHistObj.getSocRelWithComm()),
										  noNull(socHistObj.getSocHerFriends()), noNull(socHistObj.getSocRelWithFriends()), noNull(socHistObj.getSocInvolvement()),
										  noNull(socHistObj.getSocComments()), noNull(joinVStrings(socHistObj.getSocRecreationandFun())), 
										  noNull(socHistObj.getSocReligionBelief())
										 };
			String sql_socHist = createInsertQry( colNamesSocialHist, colVValSocialHist);		// ND edited on 14 Dec
			sql_socHist = "Insert into " + DB_TBL_SOCIALHIST + sql_socHist;
	////		StringBuffer sql_soc  = new StringBuffer("Insert into " +
	////					DB_TBL_SOCIALHIST);
	//		
	//		// ND added this on 12th of Nov 15
			String[] colNamesCnslStrength = {DB_COL_CNSLSTRENGTH_INDIV_ID, DB_COL_CNSLSTRENGTH_COPINGSKILL, DB_COL_CNSLSTRENGTH_STRENGTHS,
											 DB_COL_CNSLSTRENGTH_FLYGOALS, DB_COL_CNSLSTRENGTH_EDUGOALS, DB_COL_CNSLSTRENGTH_SOCIALGOALS,
											 DB_COL_CNSLSTRENGTH_PSYCHOHEALTHGOALS, DB_COL_CNSLSTRENGTH_VOCATIONALGOALS, DB_COL_CNSLSTRENGTH_LEGALGOALS,
											 DB_COL_CNSLSTRENGTH_PHYSICHEALTHGOALS, DB_COL_CNSLSTRENGTH_STRENGTHCOMMENTS
											};
			String[] colValCnslStrength = {cnsl_ID, noNull(joinVStrings(cnslStrObj.getvStrengthCopeSkills())), noNull(cnslStrObj.getStrengthStrengths()),
										   noNull(cnslStrObj.getStengthFlyGoals()), noNull(cnslStrObj.getStrengthEduGoals()), noNull(cnslStrObj.getStrengthSocialGoals()),
										   noNull(cnslStrObj.getStrengthPsychoHealthGoals()), noNull(cnslStrObj.getStrengthVocGoals()), noNull(cnslStrObj.getStrengthLegalGoals()),
										   noNull(cnslStrObj.getStrengthPhysicHealthGoals()), noNull(cnslStrObj.getStrengthComments())
										   };
			String sql_cnslStrength = createInsertQry( colNamesCnslStrength, colValCnslStrength);
			sql_cnslStrength = "Insert into " + DB_TBL_CNSLSTRENGTH + sql_cnslStrength;
	////		StringBuffer sql_cstr = new StringBuffer("Insert into " +
	////					DB_TBL_CNSLSTRENGTH);
			
			
	//		// ND edited on 14th Nov  also edited all the names of the sql strings for createInsertQry 
			String[] colNamesCnslSumm = {DB_COL_CNSLSUMM_INDIV_ID, DB_COL_CNSLSUMM_SUMMCLINICFORM, DB_COL_CNSLSUMM_INTERVENTIONPLAN, 
										 DB_COL_CNSLSUMM_PSYCHONEEDSCNSR, DB_COL_CNSLSUMM_SUMMCOMMENTS };
			//ND edited on 19th Dec
			String[] colValCnslSumm = {cnsl_ID, cnslSumObj.getPsychoClinicFormulation() ,cnslSumObj.getPsychoIntervenPlan(),
									   cnslSumObj.getPsychoNeedsCnsr(), cnslSumObj.getPsychoComments()};			
			// The new fields do not match the table (table changed on Dec 7th) and IntakeFormDlg.
			String sql_cnslSumm = createInsertQry(colNamesCnslSumm, colValCnslSumm);
			sql_cnslSumm = "Insert into " + DB_TBL_CNSLSUMM + sql_cnslSumm;
	////		StringBuffer sql_csum = new StringBuffer("Insert into " +
	////					DB_TBL_CNSLSUMM);
	//		
			String[] colNamesVocHist = { DB_COL_VOCHIST_INDIV_ID, DB_COL_VOCHIST_EMPSPECS, DB_COL_VOCHIST_FAMDEBTSPECS,
										 DB_COL_VOCHIST_WORKSPECS, 			// DB_COL_VOCHIST_LANGCOMM, 
										 DB_COL_VOCHIST_LANGREAD,
										 DB_COL_VOCHIST_LANGWRITE, DB_COL_VOCHIST_LANGSPEAK, DB_COL_VOCHIST_VOCCOMMENTS,
									   };
			String[] colValVocHist = { cnsl_ID, noNull(vocObj.getVocPriorEmp()), noNull(vocObj.getVocFamDebt()),
									   noNull(vocObj.getVocWork()),			// noNull(joinVStrings(vocObj.getVocLangComn())), 
									   noNull(vocObj.getVocLangRead()),
									   noNull(vocObj.getVocLangWrite()), noNull(vocObj.getVocLangSpeak()), noNull(vocObj.getVocComm())
									};
			String sql_vocHist = createInsertQry( colNamesVocHist, colValVocHist);
			sql_vocHist = "Insert into " + DB_TBL_VOCHIST + sql_vocHist;
			
			// ND end edition of 14th Nov
	//		StringBuffer sql_voc  = new StringBuffer("Insert into " +
	//					DB_TBL_VOCHIST);
			
			//ND added on 18th Dec.
			String[] colNamesLegalHist = { DB_COL_LEGAL_INDIV_ID, DB_COL_LEGAL_OFFENCES, DB_COL_LEGAL_LEGALCOMMENTS
										};
			String[] colValLegalHist = { cnsl_ID, noNull(legalHistObj.getLegalOffences()), noNull(legalHistObj.getLegalComments())
										};
			String sql_legalHist = createInsertQry(colNamesLegalHist, colValLegalHist);
			sql_legalHist = "Insert into " + DB_TBL_LEGAL + sql_legalHist;
				
			
			//write database queries to save the counselee intake form info 
			try{
				con = getConnection();
				
		
		/*		Connection connection = new getConnection();
				Statement statement = connection.createStatement();
	
				for (String query : queries) {
					statement.addBatch(query);
				}
				statement.executeBatch();
				statement.close();
				connection.close();
		*/		
				
				Statement st = con.createStatement();
		//		int n = st.executeUpdate(sql_fenv.toString());
				st.addBatch(sql_abuHist);
				st.addBatch(sql_eduHist);
				st.addBatch(sql_famEnv);
				for (int i=0; i<sql_cnslReln.length; i++)
					st.addBatch(sql_cnslReln[i]);
				st.addBatch(sql_phyHist);
				st.addBatch(sql_menStat);
				st.addBatch(sql_socHist);
				st.addBatch(sql_cnslStrength);
				st.addBatch(sql_cnslSumm);
				st.addBatch(sql_vocHist);							// ND edited on 11th Feb 16 (removed as comment)
				st.addBatch(sql_legalHist);							// ND added on 18th Dec
		
				st.executeBatch();
				st.close();
				con.close();
//				return "SUCCESS in SQL batch updating details of counselee"; //TODO CONVERT TOdefines
				return "SUCCESS in SQL batch inserting new records for a new counselee"; //TODO CONVERT TOdefines				// ND added 5th Apr 16

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
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ERROR";
	}

// End of Saving (inserting) procedure Beginning of Update procedure
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //

	public String  updateCounselee(Counsellee cnslee){				
		Connection con = null; 
		String strCnsl_ID, strOld_ID, strOld_parent;
		// UPDATE tablename SET Col1 = val1, Col2 = val2, .... WHERE INDIV_ID =  strCnsl_ID
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_INDIV + " SET ");

		try{
			con = getConnection();
			Statement st = con.createStatement();																// ND added 11th Apr 16
			strOld_ID = cnslee.getCaseNumber();
			
			System.out.println(DB_COL_INDIV_ID + " " + strOld_ID + " * * * * * * * * * * * * * * * * * *");
			String sql_old = getSelectQuery(DB_TBL_INDIV, DB_COL_INDIV_ID, strOld_ID);							// ND added 06th Apr 16
			System.out.println(sql_old);
			ResultSet rs_cnslee ;
			strOld_parent = null;
			rs_cnslee = st.executeQuery(sql_old);																// ND added 06th Apr 16
			while (rs_cnslee.next()){
				strOld_parent = rs_cnslee.getString(DB_COL_INDIV_PARTNER_ORGN) ;										// ND added 06th Apr 16
				System.out.println("Parent orgn old: " + rs_cnslee.getString(DB_COL_INDIV_NAME) + " ------------------------------");
			}
				if (strOld_parent != cnslee.getParentOrg()) {											// ND added 05th Apr 16
					strCnsl_ID = generateCounselleeId(cnslee);		
					System.out.println("Parent orgn has been changed " + strCnsl_ID);
				}
//				else
					strCnsl_ID = cnslee.getCaseNumber();
				
	//			cnslee.setCaseNumber(strCnsl_ID);													// ND added 05th Apr 16
	
				
				// "= '" ND added for all the columns on 5th Apr 16
				sql.append(DB_COL_INDIV_ID);
				sql.append("= '" + strCnsl_ID + "'");					
				sql.append(",");
				sql.append(DB_COL_INDIV_PARTNER_ORGN);
				sql.append("= '" +cnslee.getParentOrg() +"'");
				sql.append(",");
				sql.append(DB_COL_INDIV_HOME);											//ND added 05th Apr 16
				sql.append("= '" + cnslee.getLocation() + "'");
				sql.append(",");
				sql.append(DB_COL_INDIV_OTHER_NAME);
				sql.append("= '"  +cnslee.getOtherName() +"'");
				sql.append(",");
				sql.append(DB_COL_INDIV_DATE_ASSESSMENT);
				sql.append("= '" +cnslee.getDate() +"'");
				sql.append(",");
				sql.append(DB_COL_INDIV_DOB);											// ND added 14th Apr 16
				sql.append("= '" +cnslee.getDob() +"'");
				sql.append(",");
				sql.append(DB_COL_INDIV_AGE);											// ND added 05th Apr 16
				sql.append("= '" + cnslee.getAge() + "'");
				sql.append(",");
				sql.append(DB_COL_INDIV_NAME);
				sql.append("= '" +cnslee.getName().toString() +"'");
				sql.append(",");
				sql.append(DB_COL_INDIV_CNSLR_NAME);
				sql.append("= 'Marie DSouza'"); //TODO change to actual value1
				sql.append(",");
				sql.append(DB_COL_INDIV_DTTERM);										// ND added 05th Apr 16
				sql.append("= '" + cnslee.getDtTerm() + "'");
				sql.append(",");
				sql.append(DB_COL_INDIV_GENDER);										// ND added 05th Apr 16
				sql.append("= '" + cnslee.getGender() + "'");
				sql.append(" WHERE ");
				sql.append(DB_COL_INDIV_ID);
				sql.append(" = '");
				sql.append(strOld_ID + "'");											// ND edited 05th Apr 16
				
				System.out.println("update Counselee info query string : " + sql.toString());
				if (strOld_ID != strCnsl_ID){											// ND added 09th Apr 16
					changeIndivID_all(strOld_ID, strCnsl_ID);
				}
				
	//			Statement st = con.createStatement();								// ND commented 11th Apr 16
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

	// start ND added on 19th Jan 16
	// start ND edited on 4th Feb 16
	public String  updateAbuseHist(AbuseIndivObj abuseHist){				
		Connection con = null; 
		String strCnsl_ID;
		// UPDATE tablename SET Col1 = val1, Col2 = val2, .... WHERE INDIV_ID =  strCnsl_ID
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_ABUSEHIST + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = abuseHist.getCaseNumber();
			
			sql.append(DB_COL_ABUSEHIST_VERBAL);
			sql.append(" ='" + abuseHist.getAbuseVerbal() +"'");
			sql.append(",");
			sql.append(DB_COL_ABUSEHIST_PHYSICAL);
			sql.append(" ='"  + abuseHist.getAbusePhys() +"'");
			sql.append(",");
			sql.append(DB_COL_ABUSEHIST_SEXUAL);
			sql.append(" ='" + abuseHist.getAbuseSex() +"'");
			sql.append(",");
			sql.append(DB_COL_ABUSEHIST_NEGLECT);
			sql.append(" ='" + abuseHist.getAbuseNeglect() +"'");
			sql.append(",");
			sql.append(DB_COL_ABUSEHIST_ABUSECOMMENTS);
			sql.append(" ='" + abuseHist.getAbuseComments() + "'");
			sql.append(" WHERE ");
			sql.append(DB_COL_ABUSEHIST_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID + "'");
			
			System.out.println("update Abuse history query string : " + sql.toString());
			
			
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
// end ND added 19th Jan 16		

// start ND added 25th Jan 16
	public String  updateEduHist(EducationHistIndivObj eduHist){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_EDUHIST + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = eduHist.getCaseNumber();
			
			sql.append(DB_COL_EDUHIST_ATTENDEDSCHOOL);
			sql.append(" = '" + Boolean.toString(eduHist.isEduAttend()) +"'");
			sql.append(",");
			sql.append(DB_COL_EDUHIST_WHEREED);
			sql.append(" = '"  + eduHist.getEduWhere() +"'");
			sql.append(",");
			sql.append(DB_COL_EDUHIST_HIGHSTD);
			sql.append(" = '" + eduHist.getEduStd() +"'");
			sql.append(",");
	
			sql.append(DB_COL_EDUHIST_LITERACYCLASSES);
			sql.append(" = '" + Boolean.toString(eduHist.isEduLitClass()) +"'");
			sql.append(",");
			sql.append(DB_COL_EDUHIST_SCHOOLEXPERIENCE);
			sql.append(" = '"  + eduHist.getEduSchExp() +"'");
			sql.append(",");
			sql.append(DB_COL_EDUHIST_CONTINUESTUDIES);
			sql.append(" = '" + Boolean.toString(eduHist.isEduCont()) +"'");
			sql.append(",");
			sql.append(DB_COL_EDUHIST_NON_FORMALED);
			sql.append(" = '" + joinVStrings(eduHist.getEduNonForm()) +"'");			// ND edited 12th Mar 16
			sql.append(",");
			sql.append(DB_COL_EDUHIST_EDCOMMENTS);
			sql.append(" = '" + eduHist.getEduComm() + "'");
			sql.append(" WHERE ");
			sql.append(DB_COL_EDUHIST_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID + "'");
			
			System.out.println("update Education history query string : " + sql.toString());
			System.out.println("*********" + joinVStrings(eduHist.getEduNonForm()) + " ********");
			
			
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
	
	public String  updateFamEnv(FamilyEnvIndivObj famEnv){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_FAMENV + " SET ");

		try{
			con = getConnection();

			strCnsl_ID =  famEnv.getCaseNumber();
			
			sql.append(DB_COL_FAMENV_GETSALONG);
			sql.append(" = '" + famEnv.getFamEnvGetsAlong() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_PHYABUSE);
			sql.append(" = '"  + famEnv.getFamEnvPhyAb() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_VERBALABUSE);
			sql.append(" = '" + famEnv.getFamEnvSexAb() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_SEXUALABUSE);
			sql.append(" = '" + famEnv.getFamEnvNeg() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_SUBSTANCEABUSE);
			sql.append(" = '"  + famEnv.getFamEnvSubAb() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_NEGLECT);
			sql.append(" = '" + famEnv.getFamEnvNeg() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_PROSTITUTION);
			sql.append(" = '" + famEnv.getFamEnvPros() +"'");
			sql.append(",");
			sql.append(DB_COL_FAMENV_FAMCOMMENTS);
			sql.append(" = '" + famEnv.getFamEnvComm() + "'");
			sql.append(" WHERE ");
			sql.append(DB_COL_FAMENV_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID  + "'");
			
			System.out.println("update Family environment query string : " + sql.toString());
			
			
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
// start ND added 12th Mar 16
	// Delete the old records of the relatives (after having traferred data 
	//  to the Intake form and then save the new data from the form	
	public String  updateCnslReln(Vector<CounseleeRelativeIndivObj> vCnslReln, String caseNum){				// ND edited 02nd May 16
		Connection con = null; 
		String strCnsl_ID = null;
		CounseleeRelativeIndivObj relObjF = null;											// ND added 02nd May 16
			
//		StringBuffer sql= new StringBuffer("UPDATE " +
//					DB_TBL_CNSLRELN + " SET ");

		String[] sql_cnslReln = new String[10];
		try{
			con = getConnection();
			Statement stdel = con.createStatement();
			Statement st = con.createStatement();
			
			if (vCnslReln != null){																// ND added 02nd May 16
				 relObjF = (CounseleeRelativeIndivObj) vCnslReln.elementAt(0);					// 	ND edited 02nd May 16
				 strCnsl_ID = relObjF.getCaseNumber();
			}
			else																				// ND added 02nd May 16
				strCnsl_ID = caseNum;															// ND added 02nd May 16 
			StringBuffer sql_del_fReln = new StringBuffer("Delete from " + DB_TBL_CNSLRELN + 
					" where " + DB_COL_CNSLRELN_INDIV_ID + " = '" + strCnsl_ID + "'" );				// ND edited 19th Apr 16

			System.out.println( strCnsl_ID + " ****************** " + sql_del_fReln.toString() );
			if (strCnsl_ID != null) {
				int nd = stdel.executeUpdate(sql_del_fReln.toString());
				System.out.println(nd + " relative records deleted ");
			}
			stdel.close();

			String[] colNamesCnslReln = { DB_COL_CNSLRELN_INDIV_ID, DB_COL_CNSLRELN_RELNNAME, DB_COL_CNSLRELN_RELNSTRENGTH,
					  DB_COL_CNSLRELN_RELNAGE, DB_COL_CNSLRELN_RELNAWARE, 
					  DB_COL_CNSLRELN_RELNCOMMENTS, DB_COL_CNSLRELN_RELATIONSHIP, DB_COL_CNSLRELN_RELNPROFESSION
					};
			if (vCnslReln != null){
				for (int i = 0; i < vCnslReln.size(); i++)
				{	
					CounseleeRelativeIndivObj relObj = (CounseleeRelativeIndivObj) vCnslReln.elementAt(i);
					String[] colValCnslReln = {strCnsl_ID, noNull(relObj.getRelName()), noNull(relObj.getRelStrength()),
											   noNull(relObj.getRelAge()), Boolean.toString(relObj.isRelAwareOfVictimsSituation()),
											   noNull(relObj.getRelComments()), noNull(relObj.getRelationship()), noNull(relObj.getRelProfession())};
					sql_cnslReln[i] = createInsertQry( colNamesCnslReln, colValCnslReln);
					sql_cnslReln[i] = "Insert into " + DB_TBL_CNSLRELN + sql_cnslReln[i];
				}
				for (int i=0; i<sql_cnslReln.length; i++)							// ND added 26th Apr 16
					st.addBatch(sql_cnslReln[i]);
				st.executeBatch();

			}

//			for (int i=0; i<sql_cnslReln.length; i++)								// ND commented 26th Apr 16
//				st.addBatch(sql_cnslReln[i]);
//			st.executeBatch();
			
			st.close();
// end ND added 12th Mar 16	
			
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

	public String  updatePhysicalHist(PhysicalHistIndivObj physicalHist){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_PHYSICALHIST + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = physicalHist.getCaseNumber();
			sql.append(DB_COL_PHYSICALHIST_CURRHEALTH_CNSR);
			sql.append(" = '" + physicalHist.getPhyHistCurrYou() +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_CURRHEALTH_CNSL);
			sql.append(" = '"  + physicalHist.getPhyHistCurr() +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_SERIOUSILLINJDISABLE);
			sql.append(" = '" + physicalHist.getPhysicalHistSerious() +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_ADDICTIONS);
			sql.append(" = '" + joinVStrings(physicalHist.getPhyHistAddict()) +"'");
			sql.append(",");

			sql.append(DB_COL_PHYSICALHIST_BIRTHCONTROLHIST);
			sql.append(" = '" + Boolean.toString(physicalHist.isPhyHistBirthCtrl()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_PREGNANCYHIST);
			sql.append(" = '"  + Boolean.toString(physicalHist.isPhyHistPreg()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_ABORTIONHIST);
			sql.append(" = '" + Boolean.toString(physicalHist.isPhyHistAbort()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_STTHIST);
			sql.append(" = '" + Boolean.toString(physicalHist.isPhyHistSTI()) +"'");
			sql.append(",");

			sql.append(DB_COL_PHYSICALHIST_STITREATMENT);
			sql.append(" = '"  + Boolean.toString(physicalHist.isPhyHistSTITreat()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_WITHDRAWAL);
			sql.append(" = '" + physicalHist.getPhyHistWithdrawSympt() +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_LEARNDISABLE);
			sql.append(" = '" + Boolean.toString(physicalHist.isLearnDisable()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_ADHDISORDER);
			sql.append(" = '" + Boolean.toString(physicalHist.isADHDisorder()) + "'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_ADDISORDER);
			sql.append(" = '" + Boolean.toString(physicalHist.isADDisorder()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_AUTISM);
			sql.append(" = '" + Boolean.toString(physicalHist.isAutism()) +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_OTHERDISORDER);
			sql.append(" = '" + Boolean.toString(physicalHist.isOtherDisorder()) + "'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_BEENCOUNSELED);
			sql.append(" = '" + physicalHist.isBeenCounseled() +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_PSYCHMEDSCURR);
			sql.append(" = '" + physicalHist.isPsychMedsCurr() +"'");
			sql.append(",");
			sql.append(DB_COL_PHYSICALHIST_PHYSICCOMMENTS);
			sql.append(" = '" + physicalHist.getPhyHistComments() + "'");

			sql.append(" WHERE ");
			sql.append(DB_COL_PHYSICALHIST_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Physical history query string : " + sql.toString());
			
			
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

	public String  updateMentalStat(MentalStatusIndivObj mentalStat){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_MENTALSTAT + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = mentalStat.getCaseNumber();
			
			sql.append(DB_COL_MENTALSTAT_APPHYGIENE);
			sql.append(" = '" + mentalStat.getMentalStAppearHyg() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_MOVEMENT);
			sql.append(" = '"  + mentalStat.getMentalStMovement() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_AFFECT);
			sql.append(" = '" + mentalStat.getMentalStAffect() +"'");
			sql.append(",");
			
			sql.append(DB_COL_MENTALSTAT_MOOD);
			sql.append(" = '" + mentalStat.getMentalStMood() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_SPEECH);
			sql.append(" = '"  + mentalStat.getMentalStSpeech() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_ATTITUDE);
			sql.append(" = '" + mentalStat.getMentalStAttitude() +"'");
			sql.append(",");
			
			sql.append(DB_COL_MENTALSTAT_INTELLECTUALLEVEL);
			sql.append(" = '" + mentalStat.getMentalStIntellect() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_IMPULSECONTROL);
			sql.append(" = '"  + mentalStat.getMentalStImpulse() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_UNDERSTANDING);
			sql.append(" = '" + mentalStat.getMentalStUnderstanding() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_ORIENTATION);
			sql.append(" = '" + joinVStrings(mentalStat.getMentalStOrientation()) +"'");
			sql.append(",");
			
			sql.append(DB_COL_MENTALSTAT_MEMORY);
			sql.append(" = '" + mentalStat.getMentalStMemory() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_ATTENTION);
			sql.append(" = '"  + mentalStat.getMentalStAttention() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_SUICIDALIDEATION);
			sql.append(" = '" + mentalStat.getMentalStSuicidal() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_HOMICIDALIDEATION);
			sql.append(" = '" + mentalStat.getMentalStHomicidal() +"'");
			sql.append(",");
			
			sql.append(DB_COL_MENTALSTAT_THOUGHTPROCESS);
			sql.append(" = '" + mentalStat.getMentalStThoughtProc() +"'");
			sql.append(",");
			sql.append(DB_COL_MENTALSTAT_HALLUCINATION);
			sql.append(" = '"  + joinVStrings(mentalStat.getMentalStHallucination()) +"'");
//			Has been removed from object on 22nd Feb 16
//			sql.append(",");
//			sql.append(DB_COL_MENTALSTAT_PSYCHOCOMMENTS);
//			sql.append(" = '" + mentalStat.getMentalStComments() +"'");
			sql.append(" WHERE ");
			sql.append(DB_COL_MENTALSTAT_INDIV_ID);
			sql.append(" = '");							// ND added 09 Feb 2016
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Psychological history query string : " + sql.toString());
			
			
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

	public String  updateSocialHist(SocialHistIndivObj socialHist){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_SOCIALHIST + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = socialHist.getCaseNumber();
			
			sql.append(DB_COL_SOCHIST_LIVEDAT);
			sql.append(" = '" + socialHist.getSocLivedAt() +"'");
			sql.append(",");
			sql.append(DB_COL_SOCHIST_RELWITHCOMM);
			sql.append(" = '"  + socialHist.getSocRelWithComm() +"'");
			sql.append(",");
			sql.append(DB_COL_SOCHIST_HERFRIENDS);
			sql.append(" = '" + socialHist.getSocHerFriends() +"'");
			sql.append(",");
			sql.append(DB_COL_SOCHIST_INVOLVEMENT);
			sql.append(" = '" + socialHist.getSocInvolvement() +"'");
			sql.append(",");
			sql.append(DB_COL_SOCHIST_SOCCOMMENTS);
			sql.append(" = '"  + socialHist.getSocComments() +"'");
			sql.append(",");
			sql.append(DB_COL_SOCHIST_RECREATIONANDFUN);
			sql.append(" = '" + joinVStrings(socialHist.getSocRecreationandFun()) + "'");
			sql.append(",");
			sql.append(DB_COL_SOCHIST_RELIGIONBELIEF);
			sql.append(" = '" + socialHist.getSocReligionBelief() +"'");					// ND edited 09 Feb 16
			sql.append(" WHERE ");
			sql.append(DB_COL_SOCHIST_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Social history query string : " + sql.toString());
			
			
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

	public String  updateCnslStrength(StrenghtIndivObj cnslStrength){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_CNSLSTRENGTH + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = cnslStrength.getCaseNumber();
			
			sql.append(DB_COL_CNSLSTRENGTH_COPINGSKILL);
			sql.append(" = '" + joinVStrings(cnslStrength.getvStrengthCopeSkills())  +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_STRENGTHS);
			sql.append(" = '"  + cnslStrength.getStrengthStrengths() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_FLYGOALS);
			sql.append(" = '" + cnslStrength.getStengthFlyGoals() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_EDUGOALS);
			sql.append(" = '" + cnslStrength.getStrengthEduGoals() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_SOCIALGOALS);
			sql.append(" = '" + cnslStrength.getStrengthSocialGoals()  +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_PSYCHOHEALTHGOALS);
			sql.append(" = '"  + cnslStrength.getStrengthPsychoHealthGoals() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_VOCATIONALGOALS);
			sql.append(" = '" + cnslStrength.getStrengthVocGoals() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_LEGALGOALS);
			sql.append(" = '" + cnslStrength.getStrengthLegalGoals() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSTRENGTH_PHYSICHEALTHGOALS);
			sql.append(" = '" + cnslStrength.getStrengthPhysicHealthGoals() + "'");
			sql.append(" WHERE ");
			sql.append(DB_COL_CNSLSTRENGTH_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Counselee strength query string : " + sql.toString());
			
			
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

	public String  updateCnslSumm(SummaryIndivObj cnslSumm){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_CNSLSUMM + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = cnslSumm.getCaseNumber();
			
			sql.append(DB_COL_CNSLSUMM_SUMMCLINICFORM);
			sql.append(" = '" + cnslSumm.getPsychoClinicFormulation() + "'");
			sql.append(", ");
			sql.append(DB_COL_CNSLSUMM_INTERVENTIONPLAN);
			sql.append(" = '"  + cnslSumm.getPsychoIntervenPlan() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSUMM_PSYCHONEEDSCNSR);
			sql.append(" = '" + cnslSumm.getPsychoNeedsCnsr() +"'");
			sql.append(",");
			sql.append(DB_COL_CNSLSUMM_SUMMCOMMENTS);
			sql.append(" = '" + cnslSumm.getPsychoComments() + "'");
			sql.append(" WHERE ");
			sql.append(DB_COL_CNSLSUMM_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Counselee summary query string : " + sql.toString());
			
			
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
	public String  updateVocHist(VocationFinIndivObj vocFinHist){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_VOCHIST + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = vocFinHist.getCaseNumber();
			
			sql.append(DB_COL_VOCHIST_EMPSPECS);
			sql.append(" = '" + vocFinHist.getVocPriorEmp() +"'");
			sql.append(",");
			sql.append(DB_COL_VOCHIST_WORKSPECS);
			sql.append(" = '"  + vocFinHist.getVocWork() +"'");
			sql.append(",");
			sql.append(DB_COL_VOCHIST_LANGREAD);
			sql.append(" = '" + vocFinHist.getVocLangRead() +"'");
			sql.append(",");
			sql.append(DB_COL_VOCHIST_LANGWRITE);
			sql.append(" = '" + vocFinHist.getVocLangWrite() +"'");
			sql.append(",");					// ND added 09 Feb 16
			sql.append(DB_COL_VOCHIST_LANGSPEAK);	
			sql.append(" = '" + vocFinHist.getVocLangSpeak() + "'");
			sql.append(",");
			sql.append(DB_COL_VOCHIST_FAMDEBTSPECS);									// ND added 28th Mar 16
			sql.append(" = '" + vocFinHist.getVocFamDebt() + "'");
			sql.append(",");
			sql.append(DB_COL_VOCHIST_VOCCOMMENTS);
			sql.append(" = '" + vocFinHist.getVocComm() + "'");

			sql.append(" WHERE ");
			sql.append(DB_COL_VOCHIST_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Vocational Financial history query string : " + sql.toString());
			
			
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

	public String  updateLegal(LegalHistIndivObj legalHist){				
		Connection con = null; 
		String strCnsl_ID;
		StringBuffer sql= new StringBuffer("UPDATE " +
					DB_TBL_LEGAL + " SET ");

		try{
			con = getConnection();

			strCnsl_ID = legalHist.getCaseNumber();
			
			sql.append(DB_COL_LEGAL_OFFENCES);
			sql.append(" = '" + legalHist.getLegalOffences() +"'");
			sql.append(",");
			sql.append(DB_COL_LEGAL_LEGALCOMMENTS);
			sql.append(" = '"  + legalHist.getLegalComments() +"'");
			sql.append(" WHERE ");
			sql.append(DB_COL_LEGAL_INDIV_ID);
			sql.append(" = '");
			sql.append(strCnsl_ID);
			sql.append("'");
			
			System.out.println("update Legal history query string : " + sql.toString());
			
			
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
	

// end ND edited on 4th Feb 16
// end ND added 25th Jan 16
	

	// End of Update the tables procedure Beginning of reading info from the tables for Update procedure					// ND added 5th Apr 16
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ //

// start ND added 15th Feb 16 till Mar 16
	// to read the contents of the database into the Intake form.
	public CounseleeHistoryObj getCnslHist(String caseNum){
		Connection con = null;
		CounseleeHistoryObj cnslHist = new CounseleeHistoryObj();
		String strCnsl_ID = caseNum;
		String col_name = new String();
		
//		Counsellee cnsleeObj = new Counsellee();									// ND added 5th Apr 16
		AbuseIndivObj abuHistObj = new AbuseIndivObj();
		EducationHistIndivObj eduObj = new EducationHistIndivObj();
		Vector vCnslRelnObj = new Vector<CounseleeRelativeIndivObj>();	
		FamilyEnvIndivObj fEnvObj = new FamilyEnvIndivObj();
		PhysicalHistIndivObj phyHistObj = new PhysicalHistIndivObj();
		MentalStatusIndivObj menStatObj = new MentalStatusIndivObj();
		SocialHistIndivObj socialHistObj = new SocialHistIndivObj();
		StrenghtIndivObj cnslStrObj = new StrenghtIndivObj();
		SummaryIndivObj cnslSumObj = new SummaryIndivObj();
		VocationFinIndivObj vocObj = new VocationFinIndivObj();
//		LangFluency vocLangFl = new LangFluency();
		LegalHistIndivObj legalHistObj = new LegalHistIndivObj();			

//		String sql_cnslee = getSelectQuery(DB_TBL_INDIV, DB_COL_INDIV_ID, strCnsl_ID);							// ND added 5th Apr 16
		String sql_abuHist =  getSelectQuery(DB_TBL_ABUSEHIST, DB_COL_ABUSEHIST_INDIV_ID, strCnsl_ID);
		String sql_eduHist = getSelectQuery( DB_TBL_EDUHIST, DB_COL_EDUHIST_INDIV_ID , strCnsl_ID);
		String sql_fEnv = getSelectQuery( DB_TBL_FAMENV, DB_COL_FAMENV_INDIV_ID , strCnsl_ID);
		String sql_cnslReln = getSelectQuery( DB_TBL_CNSLRELN, DB_COL_CNSLRELN_INDIV_ID , strCnsl_ID);
		String sql_phyHist = getSelectQuery( DB_TBL_PHYSICALHIST, DB_COL_PHYSICALHIST_INDIV_ID , strCnsl_ID);
		String sql_menStat = getSelectQuery( DB_TBL_MENTALSTAT, DB_COL_MENTALSTAT_INDIV_ID , strCnsl_ID);
		String sql_socHist = getSelectQuery( DB_TBL_SOCIALHIST, DB_COL_SOCHIST_INDIV_ID , strCnsl_ID);
		String sql_cnslStrength = getSelectQuery( DB_TBL_CNSLSTRENGTH, DB_COL_CNSLSTRENGTH_INDIV_ID , strCnsl_ID);
		String sql_cnslSumm = getSelectQuery( DB_TBL_CNSLSUMM, DB_COL_CNSLSUMM_INDIV_ID , strCnsl_ID);
		String sql_vocHist = getSelectQuery( DB_TBL_VOCHIST, DB_COL_VOCHIST_INDIV_ID , strCnsl_ID);						 
		String sql_legalHist = getSelectQuery( DB_TBL_LEGAL, DB_COL_LEGAL_INDIV_ID , strCnsl_ID);						
		
		
		
		try{
			con = getConnection();
			Statement st = con.createStatement();

// start ND added 5th Apr 16
//			ResultSet rsCnslee = st.executeQuery(sql_cnslee);
//			while (rsCnslee.next()){
//				System.out.println("Counslee's main info being read");
//				cnsleeObj.setAge(rsCnslee.getInt(DB_COL_INDIV_AGE));
////				cnsleeObj.setCaseNumber(rsCnslee.getString(DB_COL_INDIV_ID));
//				cnsleeObj.setDate(rsCnslee.getString(DB_COL_INDIV_DATE_ASSESSMENT));
//				cnsleeObj.setDob(rsCnslee.getString(DB_COL_INDIV_DOB));
//				cnsleeObj.setDtTerm(rsCnslee.getString(DB_COL_INDIV_DTTERM));
//				cnsleeObj.setGender(rsCnslee.getString(DB_COL_INDIV_GENDER));
//				cnsleeObj.setLocation(rsCnslee.getString(DB_COL_INDIV_HOME));
//// 				cnsleeObj.setName(rsCnslee.getString(DB_COL_INDIV_NAME));
//				cnsleeObj.setOtherName(rsCnslee.getString(DB_COL_INDIV_OTHER_NAME));
//				cnsleeObj.setParentOrg(rsCnslee.getString(DB_COL_INDIV_PARTNER_ORGN));
//				System.out.println("Termination, Partner Orgn, Home, Age: " + rsCnslee.getString(DB_COL_INDIV_DTTERM)
//						+ ", " + rsCnslee.getString(DB_COL_INDIV_PARTNER_ORGN) + ", " + rsCnslee.getString(DB_COL_INDIV_HOME)
//						+ ", " + rsCnslee.getString(DB_COL_INDIV_AGE));
//				cnslHist.setCounseleeObj(cnsleeObj);
//			}
//// end ND added 5th Apr 16
			ResultSet rsAbu = st.executeQuery(sql_abuHist); 
			while (rsAbu.next()){											
				System.out.println("Abuse info being read");
				abuHistObj.setAbuseComments(rsAbu.getString(DB_COL_ABUSEHIST_ABUSECOMMENTS));
				abuHistObj.setAbuseNeglect(rsAbu.getString(DB_COL_ABUSEHIST_NEGLECT));
				abuHistObj.setAbusePhys(rsAbu.getString(DB_COL_ABUSEHIST_PHYSICAL));
				abuHistObj.setAbuseSex(rsAbu.getString(DB_COL_ABUSEHIST_SEXUAL));
				abuHistObj.setAbuseVerbal(rsAbu.getString(DB_COL_ABUSEHIST_VERBAL));
				System.out.println(rsAbu.getString(DB_COL_ABUSEHIST_VERBAL));
				cnslHist.setabuseIndivObj(abuHistObj);
			}
			ResultSet rsEdu = st.executeQuery(sql_eduHist);
			while (rsEdu.next()){
				System.out.println("&&&&&&&&&&&&&&&&&& Result set Education: " + rsEdu.getString(DB_COL_EDUHIST_EDCOMMENTS) + " ***************** ");
				eduObj.setEduAttend(rsEdu.getBoolean(DB_COL_EDUHIST_ATTENDEDSCHOOL));
				eduObj.setEduComm(rsEdu.getString(DB_COL_EDUHIST_EDCOMMENTS));
				eduObj.setEduCont(rsEdu.getBoolean(DB_COL_EDUHIST_CONTINUESTUDIES));
				eduObj.setEduLitClass(rsEdu.getBoolean(DB_COL_EDUHIST_LITERACYCLASSES));
				eduObj.setEduSchExp(rsEdu.getString(DB_COL_EDUHIST_SCHOOLEXPERIENCE));
				eduObj.setEduStd(rsEdu.getString(DB_COL_EDUHIST_HIGHSTD));
				eduObj.setEduWhere(rsEdu.getString(DB_COL_EDUHIST_WHEREED));
				System.out.println("Educated? " + rsEdu.getBoolean(DB_COL_EDUHIST_ATTENDEDSCHOOL));
				String sNFEd = null;
				sNFEd = rsEdu.getString(DB_COL_EDUHIST_NON_FORMALED);
				if (sNFEd != null){
					System.out.println("reading Non-Formal edu for editing " + sNFEd);
					eduObj.setEduNonForm(stringToVector(sNFEd));					// edited 12th Mar 16
				}
				else
					System.out.println("sNFEd is null - did not read non-formal education from table for editing");
					
	//			eduObj.setLstEduNonForm(rsEdu.getString(DB_COL_EDUHIST_NON_FORMALED));
	
				cnslHist.seteduIndivObj(eduObj);
			}
			
			ResultSet rsFEnv = st.executeQuery(sql_fEnv);
			System.out.println("Just read Fam Environ");
			while (rsFEnv.next()) {
				System.out.println("Just transferring Fam Environ");
				fEnvObj.setFamEnvComm(rsFEnv.getString(DB_COL_FAMENV_FAMCOMMENTS));
				System.out.println(fEnvObj.getFamEnvComm());
				fEnvObj.setFamEnvGetsAlong(rsFEnv.getString(DB_COL_FAMENV_GETSALONG));
				fEnvObj.setFamEnvNeg(rsFEnv.getString(DB_COL_FAMENV_NEGLECT));
				fEnvObj.setFamEnvPhyAb(rsFEnv.getString(DB_COL_FAMENV_PHYABUSE));
				fEnvObj.setFamEnvPros(rsFEnv.getString(DB_COL_FAMENV_PROSTITUTION));
				fEnvObj.setFamEnvSexAb(rsFEnv.getString(DB_COL_FAMENV_SEXUALABUSE));
				fEnvObj.setFamEnvSubAb(rsFEnv.getString(DB_COL_FAMENV_SUBSTANCEABUSE));
				fEnvObj.setFamEnvVerAb(rsFEnv.getString(DB_COL_FAMENV_VERBALABUSE));
				cnslHist.setfamEnvIndivObj(fEnvObj);
			}
//			System.out.println("did not read Family Env from table for editing");
			
			// ND added 12th Mar 16
			// ND uncommented on 11th Apr 16
			ResultSet rsReln = st.executeQuery(sql_cnslReln);
			int r = 0;
//			CounseleeRelativeIndivObj reln = (CounseleeRelativeIndivObj) vCnslRelnObj.elementAt(r);
			CounseleeRelativeIndivObj reln =  new CounseleeRelativeIndivObj();
			System.out.println("Just read Relative History");
			while (rsReln.next()) {
				reln = new CounseleeRelativeIndivObj();											// ND added 11th Apr 16
				reln.setRelAge(rsReln.getString(DB_COL_CNSLRELN_RELNAGE));
				reln.setRelationship(rsReln.getString(DB_COL_CNSLRELN_RELATIONSHIP));
				reln.setCaseNumber(rsReln.getString(DB_COL_CNSLRELN_INDIV_ID));
				reln.setRelAwareOfVictimsSituation(rsReln.getBoolean(DB_COL_CNSLRELN_RELNAWARE));
				reln.setRelComments(rsReln.getString(DB_COL_CNSLRELN_RELNCOMMENTS));
				reln.setRelName(rsReln.getString(DB_COL_CNSLRELN_RELNNAME));
				System.out.println(reln.getRelName());
				reln.setRelProfession(rsReln.getString(DB_COL_CNSLRELN_RELNPROFESSION));
				reln.setRelStrength(rsReln.getString(DB_COL_CNSLRELN_RELNSTRENGTH));
				vCnslRelnObj.add(reln);
				System.out.println("Just transferring Relative History");
			}
			cnslHist.setvCnslRelnObj(vCnslRelnObj);												// ND added on 11th Apr 16
			
			ResultSet rsPhy = st.executeQuery(sql_phyHist);
			System.out.println("Just read Physical History");
			while (rsPhy.next()) {
				System.out.println("Just transferring Physical History");
				phyHistObj.setADDisorder(rsPhy.getBoolean(DB_COL_PHYSICALHIST_ADDISORDER));
				phyHistObj.setADHDisorder(rsPhy.getBoolean(DB_COL_PHYSICALHIST_ADHDISORDER));
				phyHistObj.setAutism(rsPhy.getBoolean(DB_COL_PHYSICALHIST_AUTISM));
				phyHistObj.setBeenCounseled(rsPhy.getBoolean(DB_COL_PHYSICALHIST_BEENCOUNSELED));
				phyHistObj.setLearnDisable(rsPhy.getBoolean(DB_COL_PHYSICALHIST_LEARNDISABLE));
				phyHistObj.setOtherDisorder(rsPhy.getBoolean(DB_COL_PHYSICALHIST_OTHERDISORDER));
				phyHistObj.setPhyHistAbort(rsPhy.getBoolean(DB_COL_PHYSICALHIST_ABORTIONHIST));
				String sAddic = rsPhy.getString(DB_COL_PHYSICALHIST_ADDICTIONS);
				if (sAddic != null) {
					System.out.println("Reading addictions for edition " + sAddic);
					phyHistObj.setPhyHistAddict(stringToVector(sAddic));
				}
				else  {System.out.println("sAddic is null - did not read Addictions from table");}
				
				phyHistObj.setPhyHistBirthCtrl(rsPhy.getBoolean(DB_COL_PHYSICALHIST_BIRTHCONTROLHIST));
				phyHistObj.setPhyHistComments(rsPhy.getString(DB_COL_PHYSICALHIST_PHYSICCOMMENTS));
				System.out.println(phyHistObj.getPhyHistComments());
				phyHistObj.setPhyHistCurr(rsPhy.getString(DB_COL_PHYSICALHIST_CURRHEALTH_CNSL));
				phyHistObj.setPhyHistCurrYou(rsPhy.getString(DB_COL_PHYSICALHIST_CURRHEALTH_CNSR));
				phyHistObj.setPhyHistPreg(rsPhy.getBoolean(DB_COL_PHYSICALHIST_PREGNANCYHIST));
				phyHistObj.setPhyHistSTI(rsPhy.getBoolean(DB_COL_PHYSICALHIST_STITREATMENT));
				phyHistObj.setPhyHistSTITreat(rsPhy.getBoolean(DB_COL_PHYSICALHIST_STTHIST));
				phyHistObj.setPhyHistWithdrawSympt(rsPhy.getString(DB_COL_PHYSICALHIST_WITHDRAWAL));
				phyHistObj.setPhysicalHistSerious(rsPhy.getString(DB_COL_PHYSICALHIST_SERIOUSILLINJDISABLE));		// ND edited 16th Mar 16
				phyHistObj.setPsychMedsCurr(rsPhy.getBoolean(DB_COL_PHYSICALHIST_PSYCHMEDSCURR));
				cnslHist.setphyIndivObj(phyHistObj);
			} 
			
			ResultSet rsMen = st.executeQuery(sql_menStat);
			System.out.println("Just read Psychological history");
			while (rsMen.next()) {
				System.out.println("Just transferring Psychological History");
				menStatObj.setMentalStAffect(rsMen.getString(DB_COL_MENTALSTAT_AFFECT));
				menStatObj.setMentalStAppearHyg(rsMen.getString(DB_COL_MENTALSTAT_APPHYGIENE));
				menStatObj.setMentalStAttention(rsMen.getString(DB_COL_MENTALSTAT_ATTENTION));
				menStatObj.setMentalStAttitude(rsMen.getString(DB_COL_MENTALSTAT_ATTITUDE));
				String sHallu = rsMen.getString(DB_COL_MENTALSTAT_HALLUCINATION);
				if (sHallu != null) {
					System.out.println("Reading Hallucinations for edition");
					menStatObj.setMentalStHallucination(stringToVector(sHallu));
				} else System.out.println("sBallu is null. Did not read Halluncinations from table");
				menStatObj.setMentalStHomicidal(rsMen.getString(DB_COL_MENTALSTAT_HOMICIDALIDEATION));
				menStatObj.setMentalStImpulse(rsMen.getString(DB_COL_MENTALSTAT_IMPULSECONTROL));
				menStatObj.setMentalStIntellect(rsMen.getString(DB_COL_MENTALSTAT_INTELLECTUALLEVEL));
				menStatObj.setMentalStMemory(rsMen.getString(DB_COL_MENTALSTAT_MEMORY));
				menStatObj.setMentalStMood(rsMen.getString(DB_COL_MENTALSTAT_MOOD));
				menStatObj.setMentalStMovement(rsMen.getString(DB_COL_MENTALSTAT_MOVEMENT));
				String sOrien = rsMen.getString(DB_COL_MENTALSTAT_ORIENTATION);
				if (sOrien != null) {
					System.out.println("Reading Orientation for edition");
					menStatObj.setMentalStOrientation(stringToVector(sOrien));
				} else System.out.println("sOrien is null. Did not read Orientation from table");
				menStatObj.setMentalStSpeech(rsMen.getString(DB_COL_MENTALSTAT_SPEECH));
				menStatObj.setMentalStSuicidal(rsMen.getString(DB_COL_MENTALSTAT_SUICIDALIDEATION));
				menStatObj.setMentalStThoughtProc(rsMen.getString(DB_COL_MENTALSTAT_THOUGHTPROCESS));
				menStatObj.setMentalStUnderstanding(rsMen.getString(DB_COL_MENTALSTAT_UNDERSTANDING));
				System.out.println(menStatObj.getMentalStUnderstanding());
				cnslHist.setmenStatIndivObj(menStatObj);
			}
			
			ResultSet rsSoc = st.executeQuery(sql_socHist);
			while (rsSoc.next()) {
				System.out.println("Just transferring Social History");
				socialHistObj.setSocComments(rsSoc.getString(DB_COL_SOCHIST_SOCCOMMENTS));
				System.out.println(rsSoc.getString(DB_COL_SOCHIST_SOCCOMMENTS));
				socialHistObj.setSocHerFriends(rsSoc.getString(DB_COL_SOCHIST_HERFRIENDS));
				socialHistObj.setSocInvolvement(rsSoc.getString(DB_COL_SOCHIST_INVOLVEMENT));
				socialHistObj.setSocLivedAt(rsSoc.getString(DB_COL_SOCHIST_LIVEDAT));
//				System.out.println(socialHistObj.getSocLivedAt());
				String sRecre = rsSoc.getString(DB_COL_SOCHIST_RECREATIONANDFUN);
				if (sRecre != null){
					System.out.println("Reading Social Recreation for editing " + sRecre);
					socialHistObj.setSocRecreationandFun(stringToVector(sRecre));
				} else 
					System.out.println("sRecre is null. Did not read Recreation from table ");

//				String sNFEd = null;
//				sNFEd = rsEdu.getString(DB_COL_EDUHIST_NON_FORMALED);
//				if (sNFEd != null){
//					System.out.println("reading Non-Formal edu for editing " + sNFEd);
//					eduObj.setEduNonForm(stringToVector(sNFEd));					// edited 12th Mar 16
//				}
//				else
//					System.out.println("sNFEd is null - did not read non-formal education from table for editing");

				
				socialHistObj.setSocReligionBelief(rsSoc.getString(DB_COL_SOCHIST_RELIGIONBELIEF));
				socialHistObj.setSocRelWithComm(rsSoc.getString(DB_COL_SOCHIST_RELWITHCOMM));
				socialHistObj.setSocRelWithFriends(rsSoc.getString(DB_COL_SOCHIST_RELWITHFRIENDS));
				System.out.println(socialHistObj.getSocReligionBelief());
				cnslHist.setsocIndivObj(socialHistObj);
			}
			
			ResultSet rsStr = st.executeQuery(sql_cnslStrength);
			while (rsStr.next()) {
				System.out.println("Just transferring Conselee Strength");
				cnslStrObj.setvStrengthCopeSkills(stringToVector(rsStr.getString(DB_COL_CNSLSTRENGTH_COPINGSKILL)));
				cnslStrObj.setStrengthStrengths(rsStr.getString(DB_COL_CNSLSTRENGTH_STRENGTHS)); 
				cnslStrObj.setStengthFlyGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_FLYGOALS));
				cnslStrObj.setStrengthEduGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_EDUGOALS));
				cnslStrObj.setStrengthSocialGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_SOCIALGOALS));
				cnslStrObj.setStrengthPsychoHealthGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_PSYCHOHEALTHGOALS)); 
				cnslStrObj.setStrengthVocGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_VOCATIONALGOALS));
				cnslStrObj.setStrengthLegalGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_LEGALGOALS));
				cnslStrObj.setStrengthPhysicHealthGoals(rsStr.getString(DB_COL_CNSLSTRENGTH_PHYSICHEALTHGOALS));
				cnslStrObj.setStrengthComments(rsStr.getString(DB_COL_CNSLSTRENGTH_STRENGTHCOMMENTS));
				System.out.println(cnslStrObj.getStrengthComments());
				cnslHist.setstrengthIndivObj(cnslStrObj);
			}
			
			ResultSet rsSumry = st.executeQuery(sql_cnslSumm);
			while (rsSumry.next()) {
				System.out.println("Just transferring Summary info");
//				System.out.println(rsSumry.getString(DB_COL_CNSLSUMM_SUMMCLINICFORM));
				cnslSumObj.setPsychoClinicFormulation(rsSumry.getString(DB_COL_CNSLSUMM_SUMMCLINICFORM));
				cnslSumObj.setPsychoComments(rsSumry.getString(DB_COL_CNSLSUMM_SUMMCOMMENTS));
				System.out.println(cnslSumObj.getPsychoComments());
				cnslSumObj.setPsychoIntervenPlan(rsSumry.getString(DB_COL_CNSLSUMM_INTERVENTIONPLAN));
				cnslSumObj.setPsychoNeedsCnsr(rsSumry.getString(DB_COL_CNSLSUMM_PSYCHONEEDSCNSR));
				cnslHist.setsummIndivObj(cnslSumObj);
			} 
			
			ResultSet rsVoc = st.executeQuery(sql_vocHist);		
			Hashtable <String, String> langHash;									// ND added 26th Mar 16
			Enumeration enLangNames;													// ND added 26th Mar 16
			String sLangName;														// ND added 26th Mar 16
			Vector <LangFluency> vlangFl = new Vector<LangFluency>();				// ND added 26th Mar 16
			int lr, lw, ls;															// ND added 26th Mar 16
			while (rsVoc.next()) {
				System.out.println("Just transferring Vocational info");
				vocObj.setVocComm(rsVoc.getString(DB_COL_VOCHIST_VOCCOMMENTS));
				System.out.println(vocObj.getVocComm());
				vocObj.setVocLangR(rsVoc.getString(DB_COL_VOCHIST_LANGREAD));
				vocObj.setVocLangS(rsVoc.getString(DB_COL_VOCHIST_LANGSPEAK));
				vocObj.setVocLangW(rsVoc.getString(DB_COL_VOCHIST_LANGWRITE));
				
// start ND added 26th Mar 16
				langHash = transferLangToHash(rsVoc.getString(DB_COL_VOCHIST_LANGREAD), 				// ND added 26th Mar 16
						rsVoc.getString(DB_COL_VOCHIST_LANGWRITE),
						rsVoc.getString(DB_COL_VOCHIST_LANGSPEAK));
				enLangNames = langHash.keys();								
				while(enLangNames.hasMoreElements()){	
					LangFluency vocLangFl = new LangFluency();
					sLangName = enLangNames.nextElement().toString();
					System.out.println(sLangName + "transferring to fluency object"); 
					vocLangFl.setLangName(sLangName);
					lr = langHash.get(sLangName).toString().indexOf("Read");		// returns -1 if string not found
					lw = langHash.get(sLangName).toString().indexOf("Write");
					ls = langHash.get(sLangName).toString().indexOf("Speak");
					System.out.println("S: " + ls + " R: " + lr + " W: " + lw + " after hash table");		// ND added 28th Mar 16

					if (lr >= 0){
						vocLangFl.setCanRead(true) ;						// ND edited 28th Mar 16
						System.out.println(vocLangFl.getLangName() + " can read");
					}	else vocLangFl.setCanRead(false);
					
					if (ls >= 0) {
						vocLangFl.setCanSpeak(true);						// ND edited 28th Mar 16
						System.out.println(vocLangFl.getLangName() + " can speak"); 	
					}	else vocLangFl.setCanSpeak(false);
					
					if (lw >= 0) {
						vocLangFl.setCanWrite(true);						// ND edited 28th Mar 16
						System.out.println(vocLangFl.getLangName() + " can write");
					}	else vocLangFl.setCanWrite(false);
					vlangFl.add(vocLangFl);
					System.out.println(vocLangFl.getLangName() + " stored in LanguageFluency object"
							+ " R:" + vocLangFl.isCanRead() + " W:" + vocLangFl.isCanWrite() + " S:" + vocLangFl.isCanSpeak());
				}
				for (int i = 0; i < vlangFl.size(); i ++){
					System.out.println(vlangFl.elementAt(i).getLangName() + " R:" + vlangFl.elementAt(i).isCanRead()
						+ " S:" + vlangFl.elementAt(i).isCanSpeak() + " W:"  + vlangFl.elementAt(i).isCanWrite());
				}
				vocObj.setvLanguages(vlangFl);
// end ND added 26th Mar 16
				vocObj.setVocFamDebt(rsVoc.getString(DB_COL_VOCHIST_FAMDEBTSPECS));
				vocObj.setVocPriorEmp(rsVoc.getString(DB_COL_VOCHIST_EMPSPECS));
				vocObj.setVocWork(rsVoc.getString(DB_COL_VOCHIST_WORKSPECS));
				cnslHist.setvocIndivObj(vocObj);
			}
			ResultSet rsLeg = st.executeQuery(sql_legalHist);			
			while (rsLeg.next()) {
				System.out.println("Just transferrring Legal info");
				legalHistObj.setLegalComments(rsLeg.getString(DB_COL_LEGAL_LEGALCOMMENTS));
				legalHistObj.setLegalOffences(rsLeg.getString(DB_COL_LEGAL_OFFENCES));
				cnslHist.setLegalHistIndivObj(legalHistObj);
			} 
			
			st.close();
			con.close();
		} catch(Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		return cnslHist;
	}
	
	public String getSelectQuery(String tbl_name, String col_name, String indiv_ID) {

		String sql = new String("Select * from " + tbl_name);
		String sql_cond = " Where " + col_name + " = '" + indiv_ID + "'";

		sql = sql + sql_cond;
	    return sql;
	}
// end ND added 15th Feb 16

	
// start ND added 09th Apr 16
	public String changeIndivID_all(String old_ID, String new_ID){
//	StringBuffer sql= new StringBuffer("UPDATE " +	DB_TBL_INDIV + " SET ");
	Connection con = getConnection();

	try{
		Statement st = con.createStatement();
	
		st.addBatch("UPDATE " + DB_TBL_INDIV + " SET " + DB_COL_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_INDIV_ID + " = '" + old_ID + "'" );
		st.addBatch("UPDATE " + DB_TBL_ABUSEHIST + " SET " + DB_COL_ABUSEHIST_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_ABUSEHIST_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_CNSLRELN + " SET " + DB_COL_CNSLRELN_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_CNSLRELN_INDIV_ID + " = '" + old_ID + "'" );
	
		st.addBatch("UPDATE " + DB_TBL_FAMENV + " SET " + DB_COL_FAMENV_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_FAMENV_INDIV_ID + " = '" + old_ID + "'" );
	
		st.addBatch("UPDATE " + DB_TBL_EDUHIST + " SET " + DB_COL_EDUHIST_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_EDUHIST_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_PHYSICALHIST + " SET " + DB_COL_PHYSICALHIST_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_PHYSICALHIST_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_MENTALSTAT + " SET " + DB_COL_MENTALSTAT_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_MENTALSTAT_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_VOCHIST + " SET " + DB_COL_VOCHIST_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_VOCHIST_INDIV_ID + " = '" + old_ID + "'" );
	
		st.addBatch("UPDATE " + DB_TBL_SOCIALHIST + " SET " + DB_COL_SOCHIST_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_SOCHIST_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_CNSLSTRENGTH + " SET " + DB_COL_CNSLSTRENGTH_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_CNSLSTRENGTH_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_CNSLSUMM + " SET " + DB_COL_CNSLSUMM_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_CNSLSTRENGTH_INDIV_ID + " = '" + old_ID + "'" );
		
		st.addBatch("UPDATE " + DB_TBL_LEGAL + " SET " + DB_COL_LEGAL_INDIV_ID + "= '" + new_ID + "' WHERE" 
				+ DB_COL_LEGAL_INDIV_ID + " = '" + old_ID + "'" );
		
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
		}
		return "Error in updating all Indiv_IDs";
	}

	
	
}