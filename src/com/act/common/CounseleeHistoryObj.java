package com.act.common;

import java.io.Serializable;
import java.util.Vector;

import com.act.common.CounseleeRelativeIndivObj;
import com.act.common.FamilyEnvIndivObj;
import com.act.common.EducationHistIndivObj;
import com.act.common.VocationFinIndivObj;
import com.act.common.SocialHistIndivObj;
import com.act.common.PhysicalHistIndivObj;
import com.act.common.MentalStatusIndivObj;
import com.act.common.AbuseIndivObj;
import com.act.common.StrenghtIndivObj;

import com.act.common.SummaryIndivObj;
import com.act.common.LegalHistIndivObj;


public class CounseleeHistoryObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7777062470587244156L;
	
	private String counseleeID;
	
	private AbuseIndivObj abuseIndivObj;
	private EducationHistIndivObj eduIndivObj;
	private FamilyEnvIndivObj famEnvIndivObj ;
	private Vector vCnslRelnObj ;
	private PhysicalHistIndivObj phyIndivObj;
	private MentalStatusIndivObj menStatIndivObj;
	private SocialHistIndivObj socIndivObj;
	private StrenghtIndivObj strengthIndivObj;

	private VocationFinIndivObj vocIndivObj;
	
	private SummaryIndivObj summIndivObj;
	private LegalHistIndivObj legalHistIndivObj;

	
	public void setabuseIndivObj(AbuseIndivObj abuseIndivObj) {
		this.abuseIndivObj = abuseIndivObj;
	}

	public AbuseIndivObj getabuseIndivObj() {
		return abuseIndivObj;
	}

	public void seteduIndivObj(EducationHistIndivObj eduIndivObj) {
		this.eduIndivObj = eduIndivObj;
	}

	public EducationHistIndivObj geteduIndivObj() {
		return eduIndivObj;
	}

	public void setfamEnvIndivObj(FamilyEnvIndivObj famEnvIndivObj) {
		this.famEnvIndivObj = famEnvIndivObj;
	}

	public FamilyEnvIndivObj getfamEnvIndivObj() {
		return famEnvIndivObj;
	}
	
	public void setvCnslRelnObj(Vector vCnslRelnObj) {
		this.vCnslRelnObj = vCnslRelnObj;
	}

	public Vector getvCnslRelnObj() {
		return vCnslRelnObj;
	}

	public void setphyIndivObj(PhysicalHistIndivObj phyIndivObj) {
		this.phyIndivObj = phyIndivObj;
	}

	public PhysicalHistIndivObj getphyIndivObj() {
		return phyIndivObj;
	}

	public void setmenStatIndivObj(MentalStatusIndivObj menStatIndivObj) {
		this.menStatIndivObj = menStatIndivObj;
	}

	public MentalStatusIndivObj getmenStatIndivObj() {
		return menStatIndivObj;
	}

	public void setsocIndivObj(SocialHistIndivObj socIndivObj) {
		this.socIndivObj = socIndivObj;
	}

	public SocialHistIndivObj getsocIndivObj() {
		return socIndivObj;
	}

	public void setstrengthIndivObj(StrenghtIndivObj strengthIndivObj) {
		this.strengthIndivObj = strengthIndivObj;
	}

	public StrenghtIndivObj getstrengthIndivObj() {
		return strengthIndivObj;
	}

	public void setvocIndivObj(VocationFinIndivObj vocIndivObj) {
		this.vocIndivObj = vocIndivObj;
	}

	public VocationFinIndivObj getvocIndivObj() {
		return vocIndivObj;
	}



	public void setCounseleeID(String counseleeID) {
		this.counseleeID = counseleeID;
	}

	public String getCounseleeID() {
		return counseleeID;
	}

	public void setsummIndivObj(SummaryIndivObj summIndivObj) {
		this.summIndivObj = summIndivObj;
	}

	public SummaryIndivObj getsummIndivObj() {
		return summIndivObj;
	}

	public void setLegalHistIndivObj(LegalHistIndivObj legalHistIndivObj) {
		this.legalHistIndivObj = legalHistIndivObj;
	}

	public LegalHistIndivObj getLegalHistIndivObj() {
		return legalHistIndivObj;
	}




	
	
	
}
