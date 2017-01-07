package com.act.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import com.act.common.ACEDefines;
import com.act.common.TSC40Obj;
import com.act.common.TSC54Obj;

public class TSCChkLstMSSDB extends TSCChkLstDB{
	
	private static TSCChkLstMSSDB chkLstMSDB;
	
	//Singleton
	public static TSCChkLstMSSDB getInstance(){
		if (chkLstMSDB == null){
			chkLstMSDB = new TSCChkLstMSSDB();
		}
		
		return chkLstMSDB;
	}
	
	public Vector<TSC54Obj> getTSC54List(Hashtable htOptions){
		
		Connection con = null; 
		Vector<TSC54Obj> vChkLst = new Vector<TSC54Obj>();
		
		StringBuffer sql= new StringBuffer("Select * from " +
				DB_TBL_CHK_LST_TSC54 );
		
		//where clause
		if (htOptions != null && !htOptions.isEmpty()){
			sql.append(" Where ");
			
			if (htOptions.containsKey(ACEDefines.COUNSELLE_CASE_ID)){
				String sCaseId = (String)htOptions.get(ACEDefines.COUNSELLE_CASE_ID);
				sql.append(DB_COL_TSC54_CASE_ID + " = '" + sCaseId + "'");
				System.out.println("get tsc chk list 1 "+ sCaseId);	}
		}
		
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			System.out.println("tsc54 get "+ sql.toString());
			ResultSet rs = st.executeQuery(sql.toString());
			System.out.println("get tsc chk list 2");	
			while (rs.next()){
				System.out.println("get tsc chk list 3");
				TSC54Obj tsc54Obj = new TSC54Obj();
				tsc54Obj.setChkListId(String.valueOf(rs.getInt(DB_COL_TSC54_ID)));
				tsc54Obj.setCaseId(rs.getString(DB_COL_TSC54_CASE_ID));
				tsc54Obj.setChkListDate(rs.getString(DB_COL_TSC54_DATE));
				tsc54Obj.setChkListDesc(rs.getString(DB_COL_TSC54_DESC));
				tsc54Obj.setChkListCounsellor(rs.getString(DB_COL_TSC54_CNSLR_ID));
				
				
				tsc54Obj.setBadDreams(rs.getInt(DB_COL_TSC54_BAD_DREAMS));
				tsc54Obj.setFeelAfraidOfBad(rs.getInt(DB_COL_TSC54_FEEL_AFRAID_OF_BAD)); 
				tsc54Obj.setScaryIdeas(rs.getInt(DB_COL_TSC54_SCARY_IDEAS)); 
				tsc54Obj.setDirtyWords(rs.getInt(DB_COL_TSC54_DIRTY_WORDS));
				tsc54Obj.setPretendSomewhereElse(rs.getInt(DB_COL_TSC54_PRETEND)); 
				tsc54Obj.setArgue(rs.getInt(DB_COL_TSC54_ARGUE)); 
				tsc54Obj.setFeelLonely(rs.getInt(DB_COL_TSC54_FEEL_LONELY)); 
				tsc54Obj.setTouchPrivParts(rs.getInt(DB_COL_TSC54_TOUCH_PRIV_PARTS)); 
				tsc54Obj.setFeelSad(rs.getInt(DB_COL_TSC54_FEEL_SAD));
				tsc54Obj.setRemPastThings(rs.getInt(DB_COL_TSC54_REM_PAST_THINGS)); 
				tsc54Obj.setGoingAway(rs.getInt(DB_COL_TSC54_GOING_AWAY)); 
				tsc54Obj.setRemScaryThings(rs.getInt(DB_COL_TSFC54_REM_SCARY_THINGS)); 
				tsc54Obj.setYell(rs.getInt(DB_COL_TSC54_YELL)); 
				tsc54Obj.setCrying(rs.getInt(DB_COL_TSC54_CRYING));
				tsc54Obj.setSuddenFear(rs.getInt(DB_COL_TSC54_SUDDEN_FEAR)); 
				tsc54Obj.setGettingMad(rs.getInt(DB_COL_TSC54_GETTING_MAD)); 
				tsc54Obj.setThinkAbtSx(rs.getInt(DB_COL_TSC54_THINK_ABT_SX)); 
				tsc54Obj.setFeelDizzy(rs.getInt(DB_COL_TSC54_FEEL_DIZZY)); 
				tsc54Obj.setYellOthers(rs.getInt(DB_COL_TSC54_YELL_OTHERS));
				tsc54Obj.setHurtSelf(rs.getInt(DB_COL_TSC54_HURT_SELF)); 
				tsc54Obj.setHurtOthers(rs.getInt(DB_COL_TSC54_HURT_OTHERS)); 
				tsc54Obj.setTouchOtherPrivParts(rs.getInt(DB_COL_TSC54_TOUCH_OTHERS_PRIV_PARTS)); 
				tsc54Obj.setThinkSx(rs.getInt(DB_COL_TSC54_THINK_SX)); 
				tsc54Obj.setFearMen(rs.getInt(DB_COL_TSC54_FEAR_MEN));
				tsc54Obj.setFearWomen(rs.getInt(DB_COL_TSC54_FEAR_WOMEN)); 
				tsc54Obj.setWash(rs.getInt(DB_COL_TSC54_WASH)); 
				tsc54Obj.setFeelStupid(rs.getInt(DB_COL_TSC54_FEEL_sTUPID)); 
				tsc54Obj.setFeelGuilt(rs.getInt(DB_COL_TSC54_FEEL_GUILT)); 
				tsc54Obj.setFeelUnreal(rs.getInt(DB_COL_TSC54_FEEL_UNREAL)); 
				tsc54Obj.setForgetThings(rs.getInt(DB_COL_TSC54_FORGET_THINGS));
				tsc54Obj.setFeelNotInBody(rs.getInt(DB_COL_TSC54_FEEL_NOT_IN_BODY)); 
				tsc54Obj.setFeelNervous(rs.getInt(DB_COL_TSC54_FEEL_NERVOUS)); 
				tsc54Obj.setFeelAfraid(rs.getInt(DB_COL_TSC54_FEEL_AFRAID)); 
				tsc54Obj.setNotTrustPeople(rs.getInt(DB_COL_TSC54_NOT_TRUST_PEOPLE)); 
				tsc54Obj.setThinkBadPast(rs.getInt(DB_COL_TSC54_THINK_BAD_PAST));
				tsc54Obj.setFights(rs.getInt(DB_COL_TSC54_FIGHTS)); 
				tsc54Obj.setFeelMean(rs.getInt(DB_COL_TSC54_FEEL_MEAN)); 
				tsc54Obj.setPretendSomewhereElse(rs.getInt(DB_COL_TSC54_PRETEND_SOMWHERE_ELSE)); 
				tsc54Obj.setFearDark(rs.getInt(DB_COL_TSC54_FEAR_DARK)); 
				tsc54Obj.setUpsetAbtSx(rs.getInt(DB_COL_TSC54_UPSET_ABT_SX));
				tsc54Obj.setWorry(rs.getInt(DB_COL_TSC54_WORRY)); 
				tsc54Obj.setFeelNooneLikesMe(rs.getInt(DB_COL_TSC54_FEEL_NOONE_LIKES_ME)); 
				tsc54Obj.setRemThings(rs.getInt(DB_COL_TSC54_REM_THINGS)); 
				tsc54Obj.setFeelSx(rs.getInt(DB_COL_TSC54_FEEL_SX)); 
				tsc54Obj.setMindEmpty(rs.getInt(DB_COL_TSC54_MIND_EMPTY)); 
				tsc54Obj.setFeelHate(rs.getInt(DB_COL_TSC54_FEEL_HATE)); 
				tsc54Obj.setCantStopThinkAbtSx(rs.getInt(DB_COL_TSC54_CANT_STOP_THINK_ABT_SX)); 
				tsc54Obj.setTryNoFeelings(rs.getInt(DB_COL_TSC54_TRY_NO_FEELINGS)); 
				tsc54Obj.setFeelMad(rs.getInt(DB_COL_TSC54_FEEL_MAD)); 
				tsc54Obj.setFeelKill(rs.getInt(DB_COL_TSC54_FEEL_KILL)); 
				tsc54Obj.setWishBadDinHappen(rs.getInt(DB_COL_TSC54_WISH_BAD_DIN_HAPPEN)); 
				tsc54Obj.setWantToKillSelf(rs.getInt(DB_COL_TSC54_WANT_TO_KILL_SELF)); 
				tsc54Obj.setDayDream(rs.getInt(DB_COL_TSC54_DAY_DREAM)); 
				tsc54Obj.setUpsetTalkAbtSx(rs.getInt(DB_COL_TSC54_UPSET_TALK_ABT_SX));

				tsc54Obj.setChkListTotalScore(String.valueOf(rs.getFloat(DB_COL_TSC54_TOTAL_SCORE)));
				
				vChkLst.add(tsc54Obj);
			}
				
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		System.out.println("tsc check list 6" + vChkLst.size());
		return vChkLst;
	}

	public Vector<TSC40Obj> getTSC40List(Hashtable htOptions){
		
		Connection con = null; 
		Vector<TSC40Obj> vChkLst = new Vector<TSC40Obj>();
		
		StringBuffer sql= new StringBuffer("Select * from " +
				DB_TBL_CHK_LST_TSC40 );
		
		//where clause
		if (htOptions != null && !htOptions.isEmpty()){
			sql.append(" Where ");
			
			if (htOptions.containsKey(ACEDefines.COUNSELLE_CASE_ID)){
				String sCaseId = (String)htOptions.get(ACEDefines.COUNSELLE_CASE_ID);
				sql.append(DB_COL_TSC40_CASE_ID + " = '" + sCaseId + "'");
			}
		}
		
		//write database queries to retrieve the user 
		try{
			con = getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			
			while (rs.next()){
				
				TSC40Obj tsc40Obj = new TSC40Obj();
				
				tsc40Obj.setCaseId(rs.getString(DB_COL_TSC40_CASE_ID));
				tsc40Obj.setChkListId(String.valueOf(rs.getInt(DB_COL_TSC40_ID)));
				tsc40Obj.setCaseId(rs.getString(DB_COL_TSC40_CASE_ID));
				tsc40Obj.setChkListDate(rs.getString(DB_COL_TSC40_DATE));
				tsc40Obj.setChkListDesc(rs.getString(DB_COL_TSC40_DESC));
				tsc40Obj.setChkListCounsellor(rs.getString(DB_COL_TSC40_CNSLR_ID));

				tsc40Obj.setHeadAche(rs.getInt(DB_COL_TSC40_HEAD_ACHE));
				tsc40Obj.setInsomnia(rs.getInt(DB_COL_TSC40_INSOMNIA));
				tsc40Obj.setWghtLoss(rs.getInt(DB_COL_TSC40_WGHT_LOSS));
				tsc40Obj.setStomachProb(rs.getInt(DB_COL_TSC40_STOMACH_PROB));
				tsc40Obj.setSexProb(rs.getInt(DB_COL_TSC40_SX_PROB));
				tsc40Obj.setIsolation(rs.getInt(DB_COL_TSC40_ISOLATION)); 	
				tsc40Obj.setFlashBack(rs.getInt(DB_COL_TSC40_FLASH_BACK));
				tsc40Obj.setRestlssSleep(rs.getInt(DB_COL_TSC40_RESTLESS_SLEEP));
				tsc40Obj.setLowSxDrive(rs.getInt(DB_COL_TSC40_LOW_SX_dRIVE));
				tsc40Obj.setLoneliness(rs.getInt(DB_COL_TSC40_LONELINESS));
				tsc40Obj.setNightmares(rs.getInt(DB_COL_TSC40_NIGHTMARES));
				tsc40Obj.setSpaceOut(rs.getInt(DB_COL_TSC40_SPACE_OUT));	
				tsc40Obj.setAnxAttck(rs.getInt(DB_COL_TSC40_ANX_ATTACK));
				tsc40Obj.setSadness(rs.getInt(DB_COL_TSC40_SADNESS));
				tsc40Obj.setDizziness(rs.getInt(DB_COL_TSC40_DIZZINESS));
				tsc40Obj.setDissatSxDrive(rs.getInt(DB_COL_TSC40_DISSAT_SX_DRIVE));
				tsc40Obj.setCtrlTemper(rs.getInt(DB_COL_TSC40_CTRL_TEMPER));
				tsc40Obj.setWakeEarly(rs.getInt(DB_COL_TSC40_WAKE_EARLY));	
				tsc40Obj.setSxOveract(rs.getInt(DB_COL_TSC40_SX_OVERACT));
				tsc40Obj.setUncontrolCry(rs.getInt(DB_COL_TSC40_UNCONTROL_cRY));
				tsc40Obj.setFearMen(rs.getInt(DB_COL_TSC40_FEAR_MEN));
				tsc40Obj.setNotRestMorn(rs.getInt(DB_COL_TSC40_NO_REST_MORN));
				tsc40Obj.setSxNoEnjoy(rs.getInt(DB_COL_TSC40_SX_NO_ENJOY));
				tsc40Obj.setTrblGetAlong(rs.getInt(DB_COL_TSC40_TRBL_GETALONG));	
				tsc40Obj.setMemProb(rs.getInt(DB_COL_TSC40_MEM_PROB));
				tsc40Obj.setPhysicHurt(rs.getInt(DB_COL_TSC40_PHYSIC_HURT));
				tsc40Obj.setFearWomen(rs.getInt(DB_COL_TSC40_FEAR_WOMEN));
				tsc40Obj.setWakeMidnight(rs.getInt(DB_COL_TSC40_WAKE_MIDNIGHT));
				tsc40Obj.setBadThoughtsSx(rs.getInt(DB_COL_TSC40_BAD_THOUGHT_ABT_SX));	
				tsc40Obj.setPassOut(rs.getInt(DB_COL_TSC40_PASS_OUT));
				tsc40Obj.setUnrealFeel(rs.getInt(DB_COL_TSC40_UNREAL_FEEL));
				tsc40Obj.setFreqWash(rs.getInt(DB_COL_TSC40_FREQ_WASH));
				tsc40Obj.setInferiority(rs.getInt(DB_COL_TSC40_INFERIORITY));
				tsc40Obj.setTension(rs.getInt(DB_COL_TSC40_TENSION));
				tsc40Obj.setSxConfusion(rs.getInt(DB_COL_TSC40_SX_CONFUSION));	
				tsc40Obj.setHurtOthers(rs.getInt(DB_COL_TSC40_HURT_OTHERS));
				tsc40Obj.setGuilt(rs.getInt(DB_COL_TSC40_GUILT));
				tsc40Obj.setFeelNotInBody(rs.getInt(DB_COL_TSC40_FEEL_NOT_IN_BODY));
				tsc40Obj.setBreatheTrouble(rs.getInt(DB_COL_TSC40_BREATHE_TROUBLE));
				tsc40Obj.setSxFeelingUntimely(rs.getInt(DB_COL_TSC40_SX_FEEL_UNTIMELY));

				tsc40Obj.setChkListTotalScore(String.valueOf(rs.getFloat(DB_COL_TSC40_TOTAL_SCORE)));

				
				vChkLst.add(tsc40Obj);
			}
				
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return vChkLst;
	}
	
	public String saveTSC54List(TSC54Obj tsc54Obj){
		
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Insert into " +
				DB_TBL_CHK_LST_TSC54 + " values ( ");
		
		
		//write database queries to retrieve the user 
		try{
			sql.append("0,");
			sql.append("'" + noNull(tsc54Obj.getCaseId()) + "'");					
			sql.append(",");
			sql.append("'" + tsc54Obj.getChkListDate() + "'");					
			sql.append(",");
			sql.append("'" + noNull(tsc54Obj.getChkListCounsellor()) + "'");					
			sql.append(",");
			sql.append("'" + noNull(tsc54Obj.getChkListDesc()) + "'");					
			sql.append(",");

			sql.append(tsc54Obj.getBadDreams());
			sql.append(",");
			sql.append(tsc54Obj.getFeelAfraidOfBad());
			sql.append(",");
			sql.append(tsc54Obj.getScaryIdeas());
			sql.append(",");
			sql.append(tsc54Obj.getDirtyWords());
			sql.append(",");
			sql.append(tsc54Obj.getPretend());
			sql.append(",");
			sql.append(tsc54Obj.getArgue());
			sql.append(",");
			sql.append(tsc54Obj.getFeelLonely());
			sql.append(",");
			sql.append(tsc54Obj.getTouchPrivParts());
			sql.append(",");
			sql.append(tsc54Obj.getFeelSad());
			sql.append(",");
			sql.append(tsc54Obj.getRemPastThings());
			sql.append(",");
			sql.append(tsc54Obj.getGoingAway());
			sql.append(",");
			sql.append(tsc54Obj.getRemScaryThings());
			sql.append(",");
			sql.append(tsc54Obj.getYell());
			sql.append(",");
			sql.append(tsc54Obj.getCrying());
			sql.append(",");
			sql.append(tsc54Obj.getSuddenFear());
			sql.append(",");
			sql.append(tsc54Obj.getGettingMad());
			sql.append(",");
			sql.append(tsc54Obj.getThinkAbtSx());
			sql.append(",");
			sql.append(tsc54Obj.getFeelDizzy());
			sql.append(",");
			sql.append(tsc54Obj.getYellOthers());
			sql.append(",");
			sql.append(tsc54Obj.getHurtSelf());
			sql.append(",");
			sql.append(tsc54Obj.getHurtOthers());
			sql.append(",");
			sql.append(tsc54Obj.getTouchOtherPrivParts());
			sql.append(",");
			sql.append(tsc54Obj.getThinkSx());
			sql.append(",");
			sql.append(tsc54Obj.getFearMen());
			sql.append(",");
			sql.append(tsc54Obj.getFearWomen());
			sql.append(",");
			sql.append(tsc54Obj.getWash());
			sql.append(",");
			sql.append(tsc54Obj.getFeelStupid());
			sql.append(",");
			sql.append(tsc54Obj.getFeelGuilt());
			sql.append(",");
			sql.append(tsc54Obj.getFeelUnreal());
			sql.append(",");
			sql.append(tsc54Obj.getForgetThings());
			sql.append(",");
			sql.append(tsc54Obj.getFeelNotInBody());
			sql.append(",");
			sql.append(tsc54Obj.getFeelNervous());
			sql.append(",");
			sql.append(tsc54Obj.getFeelAfraid());
			sql.append(",");
			sql.append(tsc54Obj.getNotTrustPeople());
			sql.append(",");
			sql.append(tsc54Obj.getThinkBadPast());
			sql.append(",");
			sql.append(tsc54Obj.getFights());
			sql.append(",");
			sql.append(tsc54Obj.getFeelMean());
			sql.append(",");
			sql.append(tsc54Obj.getPretendSomewhereElse());
			sql.append(",");
			sql.append(tsc54Obj.getFearDark());
			sql.append(",");
			sql.append(tsc54Obj.getUpsetAbtSx());
			sql.append(",");
			sql.append(tsc54Obj.getWorry());
			sql.append(",");
			sql.append(tsc54Obj.getFeelNooneLikesMe());
			sql.append(",");
			sql.append(tsc54Obj.getRemThings());
			sql.append(",");
			sql.append(tsc54Obj.getFeelSx());
			sql.append(",");
			sql.append(tsc54Obj.getMindEmpty());
			sql.append(",");
			sql.append(tsc54Obj.getFeelHate());
			sql.append(",");
			sql.append(tsc54Obj.getCantStopThinkAbtSx());
			sql.append(",");
			sql.append(tsc54Obj.getTryNoFeelings());
			sql.append(",");
			sql.append(tsc54Obj.getFeelMad());
			sql.append(",");
			sql.append(tsc54Obj.getFeelKill());
			sql.append(",");
			sql.append(tsc54Obj.getWishBadDinHappen());
			sql.append(",");
			sql.append(tsc54Obj.getWantToKillSelf());
			sql.append(",");
			sql.append(tsc54Obj.getDayDream());
			sql.append(",");
			sql.append(tsc54Obj.getUpsetTalkAbtSx());
			sql.append(",");
			try{
			sql.append(Float.parseFloat(tsc54Obj.getChkListTotalScore()));
			}catch(Exception ex){
				System.out.println("float prob..............");
				ex.printStackTrace();
			}
			sql.append(" )");

			con = getConnection();
			Statement st = con.createStatement();
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS";
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return "";
	}
	
	
	public String saveTSC40List(TSC40Obj tsc40Obj){
		
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Insert into " +
				DB_TBL_CHK_LST_TSC40 + " values ( ");
		
		
		//write database queries to retrieve the user 
		try{
			
			sql.append("0,");
			sql.append("'" + tsc40Obj.getCaseId() + "'");					
			sql.append(",");
			sql.append("'" + tsc40Obj.getChkListDate() + "'");					
			sql.append(",");
			sql.append("'" + tsc40Obj.getChkListCounsellor() + "'");					
			sql.append(",");
			sql.append("'" + noNull(tsc40Obj.getChkListDesc()) + "'");					
			sql.append(",");

			sql.append(tsc40Obj.getHeadAche()+ ",");
			sql.append(tsc40Obj.getInsomnia()+ ",");
			sql.append(tsc40Obj.getWghtLoss()+ ",");
			sql.append(tsc40Obj.getStomachProb()+ ",");
			sql.append(tsc40Obj.getSexProb()+ ",");
			sql.append(tsc40Obj.getIsolation()+ ","); 	
			sql.append(tsc40Obj.getFlashBack()+ ",");
			sql.append(tsc40Obj.getRestlssSleep()+ ",");
			sql.append(tsc40Obj.getLowSxDrive()+ ",");
			sql.append(tsc40Obj.getLoneliness()+ ",");
			sql.append(tsc40Obj.getNightmares()+ ",");
			sql.append(tsc40Obj.getSpaceOut()+ ",");	
			sql.append(tsc40Obj.getAnxAttck()+ ",");
			sql.append(tsc40Obj.getSadness()+ ",");
			sql.append(tsc40Obj.getDizziness()+ ",");
			sql.append(tsc40Obj.getDissatSxDrive()+ ",");
			sql.append(tsc40Obj.getCtrlTemper()+ ",");
			sql.append(tsc40Obj.getWakeEarly()+ ",");	
			sql.append(tsc40Obj.getSxOveract()+ ",");
			sql.append(tsc40Obj.getUncontrolCry()+ ",");
			sql.append(tsc40Obj.getFearMen()+ ",");
			sql.append(tsc40Obj.getNotRestMorn()+ ",");
			sql.append(tsc40Obj.getSxNoEnjoy()+ ",");
			sql.append(tsc40Obj.getTrblGetAlong()+ ",");	
			sql.append(tsc40Obj.getMemProb()+ ",");
			sql.append(tsc40Obj.getPhysicHurt()+ ",");
			sql.append(tsc40Obj.getFearWomen()+ ",");
			sql.append(tsc40Obj.getWakeMidnight()+ ",");
			sql.append(tsc40Obj.getBadThoughtsSx()+ ",");	
			sql.append(tsc40Obj.getPassOut()+ ",");
			sql.append(tsc40Obj.getUnrealFeel()+ ",");
			sql.append(tsc40Obj.getFreqWash()+ ",");
			sql.append(tsc40Obj.getInferiority()+ ",");
			sql.append(tsc40Obj.getTension()+ ",");
			sql.append(tsc40Obj.getSxConfusion()+ ",");	
			sql.append(tsc40Obj.getHurtOthers()+ ",");
			sql.append(tsc40Obj.getGuilt()+ ",");
			sql.append(tsc40Obj.getFeelNotInBody()+ ",");
			sql.append(tsc40Obj.getBreatheTrouble()+ ",");
			sql.append(tsc40Obj.getSxFeelingUntimely() + ",");
			sql.append(Float.parseFloat(tsc40Obj.getChkListTotalScore()));
			sql.append(" )");

			con = getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(sql.toString());
			
			con.close();
			return "SUCCESS";
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return "";
	}

	public String updateTSC40List(TSC40Obj tsc40Obj){
		
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Update " +
				DB_TBL_CHK_LST_TSC40 + " Set ");
		
		
		//write database queries to retrieve the user 
		try{
			sql.append(DB_COL_TSC40_DATE + " = '" + tsc40Obj.getChkListDate() +"'");
			sql.append(",");
			sql.append(DB_COL_TSC40_DESC + " = '" + tsc40Obj.getChkListDesc() +"'");
			sql.append(",");
			sql.append(DB_COL_TSC40_CNSLR_ID+ "= '" + tsc40Obj.getChkListCounsellor() +"'");
			sql.append(",");

			sql.append(DB_COL_TSC40_HEAD_ACHE + " = " + tsc40Obj.getHeadAche());
			sql.append(",");
			sql.append(DB_COL_TSC40_INSOMNIA + " = " + tsc40Obj.getInsomnia());
			sql.append(",");
			sql.append(DB_COL_TSC40_WGHT_LOSS+ " = " +  tsc40Obj.getWghtLoss());
			sql.append(",");
			sql.append(DB_COL_TSC40_STOMACH_PROB + " = " +  tsc40Obj.getStomachProb());
			sql.append(",");
			sql.append(DB_COL_TSC40_SX_PROB+ " = " +  tsc40Obj.getSexProb());
			sql.append(",");
			sql.append(DB_COL_TSC40_ISOLATION+ " = " +  tsc40Obj.getIsolation()); 	
			sql.append(",");
			sql.append(DB_COL_TSC40_FLASH_BACK+ " = " +  tsc40Obj.getFlashBack());
			sql.append(",");
			sql.append(DB_COL_TSC40_RESTLESS_SLEEP+ " = " +  tsc40Obj.getRestlssSleep());
			sql.append(",");
			sql.append(DB_COL_TSC40_LOW_SX_dRIVE+ " = " +  tsc40Obj.getLowSxDrive());
			sql.append(",");
			sql.append(DB_COL_TSC40_LONELINESS+ " = " +  tsc40Obj.getLoneliness());
			sql.append(",");
			sql.append(DB_COL_TSC40_NIGHTMARES+ " = " +  tsc40Obj.getNightmares());
			sql.append(",");
			sql.append(DB_COL_TSC40_SPACE_OUT+ " = " +  tsc40Obj.getSpaceOut());	
			sql.append(",");
			sql.append(DB_COL_TSC40_ANX_ATTACK+ " = " +  tsc40Obj.getAnxAttck());
			sql.append(",");
			sql.append(DB_COL_TSC40_SADNESS+ " = " +  tsc40Obj.getSadness());
			sql.append(",");
			sql.append(DB_COL_TSC40_DIZZINESS+ " = " +  tsc40Obj.getDizziness());
			sql.append(",");
			sql.append(DB_COL_TSC40_DISSAT_SX_DRIVE+ " = " +  tsc40Obj.getDissatSxDrive());
			sql.append(",");
			sql.append(DB_COL_TSC40_CTRL_TEMPER+ " = " +  tsc40Obj.getCtrlTemper());
			sql.append(",");
			sql.append(DB_COL_TSC40_WAKE_EARLY+ " = " +  tsc40Obj.getWakeEarly());	
			sql.append(",");
			sql.append(DB_COL_TSC40_SX_OVERACT+ " = " +  tsc40Obj.getSxOveract());
			sql.append(",");
			sql.append(DB_COL_TSC40_UNCONTROL_cRY+ " = " +  tsc40Obj.getUncontrolCry());
			sql.append(",");
			sql.append(DB_COL_TSC40_FEAR_MEN+ " = " +  tsc40Obj.getFearMen());
			sql.append(",");
			sql.append(DB_COL_TSC40_NO_REST_MORN+ " = " +  tsc40Obj.getNotRestMorn());
			sql.append(",");
			sql.append(DB_COL_TSC40_SX_NO_ENJOY+ " = " +  tsc40Obj.getSxNoEnjoy());
			sql.append(",");
			sql.append(DB_COL_TSC40_TRBL_GETALONG+ " = " +  tsc40Obj.getTrblGetAlong());	
			sql.append(",");
			sql.append(DB_COL_TSC40_MEM_PROB+ " = " +  tsc40Obj.getMemProb());
			sql.append(",");
			sql.append(DB_COL_TSC40_PHYSIC_HURT+ " = " +  tsc40Obj.getPhysicHurt());
			sql.append(",");
			sql.append(DB_COL_TSC40_FEAR_WOMEN+ " = " +  tsc40Obj.getFearWomen());
			sql.append(",");
			sql.append(DB_COL_TSC40_WAKE_MIDNIGHT+ " = " +  tsc40Obj.getWakeMidnight());
			sql.append(",");
			sql.append(DB_COL_TSC40_BAD_THOUGHT_ABT_SX+ " = " +  tsc40Obj.getBadThoughtsSx());	
			sql.append(",");
			sql.append(DB_COL_TSC40_PASS_OUT+ " = " +  tsc40Obj.getPassOut());
			sql.append(",");
			sql.append(DB_COL_TSC40_UNREAL_FEEL+ " = " +  tsc40Obj.getUnrealFeel());
			sql.append(",");
			sql.append(DB_COL_TSC40_FREQ_WASH+ " = " +  tsc40Obj.getFreqWash());
			sql.append(",");
			sql.append(DB_COL_TSC40_INFERIORITY+ " = " +  tsc40Obj.getInferiority());
			sql.append(",");
			sql.append(DB_COL_TSC40_TENSION+ " = " +  tsc40Obj.getTension());
			sql.append(",");
			sql.append(DB_COL_TSC40_SX_CONFUSION+ " = " +  tsc40Obj.getSxConfusion());	
			sql.append(",");
			sql.append(DB_COL_TSC40_HURT_OTHERS+ " = " +  tsc40Obj.getHurtOthers());
			sql.append(",");
			sql.append(DB_COL_TSC40_GUILT+ " = " +  tsc40Obj.getGuilt());
			sql.append(",");
			sql.append(DB_COL_TSC40_FEEL_NOT_IN_BODY+ " = " +  tsc40Obj.getFeelNotInBody());
			sql.append(",");
			sql.append(DB_COL_TSC40_BREATHE_TROUBLE+ " = " +  tsc40Obj.getBreatheTrouble());
			sql.append(",");
			sql.append(DB_COL_TSC40_SX_FEEL_UNTIMELY+ " = " +  tsc40Obj.getSxFeelingUntimely());
			sql.append(",");
			sql.append(DB_COL_TSC40_TOTAL_SCORE+ " = " +  tsc40Obj.getChkListTotalScore());

//			sql.append(" )");

			con = getConnection();
			Statement st = con.createStatement();
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS";
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return "";
	}

	public String updateTSC54List(TSC54Obj tsc54Obj){
		
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Update " +
				DB_TBL_CHK_LST_TSC54 + " Set ");
		
		
		//write database queries to retrieve the user 
		try{
			sql.append(DB_COL_TSC54_DATE + " = '" + tsc54Obj.getChkListDate() +"'");
			sql.append(",");
			sql.append(DB_COL_TSC54_DESC + " = '" + tsc54Obj.getChkListDesc() +"'");
			sql.append(",");
			sql.append(DB_COL_TSC54_CNSLR_ID+ "= '" + tsc54Obj.getChkListCounsellor() +"'");
			sql.append(",");

			sql.append(DB_COL_TSC54_BAD_DREAMS+ " = " + tsc54Obj.getBadDreams());
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_AFRAID_OF_BAD+ " = " + tsc54Obj.getFeelAfraidOfBad()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_SCARY_IDEAS+ " = " + tsc54Obj.getScaryIdeas()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_DIRTY_WORDS+ " = " + tsc54Obj.getDirtyWords());
			sql.append(",");
			sql.append(DB_COL_TSC54_PRETEND+ " = " + tsc54Obj.getPretendSomewhereElse()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_ARGUE+ " = " + tsc54Obj.getArgue()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_LONELY+ " = " + tsc54Obj.getFeelLonely()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_TOUCH_PRIV_PARTS+ " = " + tsc54Obj.getTouchPrivParts()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_SAD+ " = " + tsc54Obj.getFeelSad());
			sql.append(",");
			sql.append(DB_COL_TSC54_REM_PAST_THINGS+ " = " + tsc54Obj.getRemPastThings()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_GOING_AWAY+ " = " + tsc54Obj.getGoingAway()); 
			sql.append(",");
			sql.append(DB_COL_TSFC54_REM_SCARY_THINGS+ " = " + tsc54Obj.getRemScaryThings()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_YELL+ " = " + tsc54Obj.getYell()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_CRYING+ " = " + tsc54Obj.getCrying());
			sql.append(",");
			sql.append(DB_COL_TSC54_SUDDEN_FEAR+ " = " + tsc54Obj.getSuddenFear()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_GETTING_MAD+ " = " + tsc54Obj.getGettingMad()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_THINK_ABT_SX+ " = " + tsc54Obj.getThinkAbtSx()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_DIZZY+ " = " + tsc54Obj.getFeelDizzy()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_YELL_OTHERS+ " = " + tsc54Obj.getYellOthers());
			sql.append(",");
			sql.append(DB_COL_TSC54_HURT_SELF+ " = " + tsc54Obj.getHurtSelf()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_HURT_OTHERS+ " = " + tsc54Obj.getHurtOthers()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_TOUCH_OTHERS_PRIV_PARTS+ " = " + tsc54Obj.getTouchOtherPrivParts()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_THINK_SX+ " = " + tsc54Obj.getThinkSx()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEAR_MEN+ " = " + tsc54Obj.getFearMen());
			sql.append(",");
			sql.append(DB_COL_TSC54_FEAR_WOMEN+ " = " + tsc54Obj.getFearWomen()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_WASH+ " = " + tsc54Obj.getWash()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_sTUPID+ " = " + tsc54Obj.getFeelStupid()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_GUILT+ " = " + tsc54Obj.getFeelGuilt()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_UNREAL+ " = " + tsc54Obj.getFeelUnreal()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FORGET_THINGS+ " = " + tsc54Obj.getForgetThings());
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_NOT_IN_BODY+ " = " + tsc54Obj.getFeelNotInBody()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_NERVOUS+ " = " + tsc54Obj.getFeelNervous()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_AFRAID+ " = " + tsc54Obj.getFeelAfraid()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_NOT_TRUST_PEOPLE+ " = " + tsc54Obj.getNotTrustPeople()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_THINK_BAD_PAST+ " = " + tsc54Obj.getThinkBadPast());
			sql.append(",");
			sql.append(DB_COL_TSC54_FIGHTS+ " = " + tsc54Obj.getFights()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_MEAN+ " = " + tsc54Obj.getFeelMean()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_PRETEND_SOMWHERE_ELSE+ " = " + tsc54Obj.getPretendSomewhereElse()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEAR_DARK+ " = " + tsc54Obj.getFearDark()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_UPSET_ABT_SX+ " = " + tsc54Obj.getUpsetAbtSx());
			sql.append(",");
			sql.append(DB_COL_TSC54_WORRY+ " = " + tsc54Obj.getWorry()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_NOONE_LIKES_ME+ " = " + tsc54Obj.getFeelNooneLikesMe()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_REM_THINGS+ " = " + tsc54Obj.getRemThings()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_SX+ " = " + tsc54Obj.getFeelSx()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_MIND_EMPTY+ " = " + tsc54Obj.getMindEmpty()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_HATE+ " = " + tsc54Obj.getFeelHate()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_CANT_STOP_THINK_ABT_SX+ " = " + tsc54Obj.getCantStopThinkAbtSx()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_TRY_NO_FEELINGS+ " = " + tsc54Obj.getTryNoFeelings()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_MAD+ " = " + tsc54Obj.getFeelMad()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_FEEL_KILL+ " = " + tsc54Obj.getFeelKill()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_WISH_BAD_DIN_HAPPEN+ " = " + tsc54Obj.getWishBadDinHappen()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_WANT_TO_KILL_SELF+ " = " + tsc54Obj.getWantToKillSelf()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_DAY_DREAM+ " = " + tsc54Obj.getDayDream()); 
			sql.append(",");
			sql.append(DB_COL_TSC54_UPSET_TALK_ABT_SX+ " = " + tsc54Obj.getUpsetTalkAbtSx());
			sql.append(",");
			sql.append(DB_COL_TSC54_TOTAL_SCORE+ " = " + tsc54Obj.getChkListTotalScore());

//			sql.append(" ");

			con = getConnection();
			Statement st = con.createStatement();
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS";
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return "";
	}

	public String deleteTSC54List(TSC54Obj tsc54Obj){
		
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Delete from " +
				DB_TBL_CHK_LST_TSC54 + " Where (");
		
		
		//write database queries to retrieve the user 
		try{
			sql.append(DB_COL_TSC54_ID + " = " + tsc54Obj.getChkListId());

			sql.append(" )");

			con = getConnection();
			Statement st = con.createStatement();
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS";
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return "";
	}
	public String deleteTSC40List(TSC40Obj tsc40Obj){
		
		Connection con = null; 
		
		StringBuffer sql= new StringBuffer("Delete from " +
				DB_TBL_CHK_LST_TSC40 + " Where (");
		
		
		//write database queries to retrieve the user 
		try{
			sql.append(DB_COL_TSC40_ID + " = " + tsc40Obj.getChkListId() );

			sql.append(" )");

			con = getConnection();
			Statement st = con.createStatement();
			System.out.println(sql.toString());
			st.executeUpdate(sql.toString());
			
			con.close();
			
			return "SUCCESS";
			
		}catch(SQLException e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}catch (Exception e){
			e.printStackTrace();
			if (con != null)
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		return "";
	}
}
