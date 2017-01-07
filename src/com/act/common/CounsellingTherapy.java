package com.act.common;

import java.io.Serializable;

public class CounsellingTherapy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1479984158326867897L;

	//client details
//	private String caseId,
//				   counsellorId,
	private String therapyObjId,
					therapyDesc;					;
	
	public static String THERAPY_TFCBT = "TFCBT Therapy";
	
	private Counsellee cnslee;


	public String getTherapyObjId() {
		return therapyObjId;
	}

	public void setTherapyObjId(String therapyObjId) {
		this.therapyObjId = therapyObjId;
	}

	public void setTherapyDesc(String therapyDesc) {
		this.therapyDesc = therapyDesc;
	}

	public String getTherapyDesc() {
		return therapyDesc;
	}
	
	public String toString(){
		return therapyObjId;
	}
}
