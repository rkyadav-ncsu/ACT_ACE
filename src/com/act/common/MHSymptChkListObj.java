package com.act.common;

import java.io.Serializable;

public class MHSymptChkListObj implements Serializable{ 
	
	private static final long serialVersionUID = -1976549602973284550L;
	
	public static String SYMPT_CKLIST_TYPE_TSC40 = "TSC40 Major";
	public static String SYMPT_CKLIST_TYPE_TSC54 = "TSC54 Minor";
	
	private String chkListId,
					chkListType,
					chkListDate,
					chkListTotalScore,
					chkListCounsellor,
					chkListDesc;

	public String getChkListId() {
		return chkListId;
	}

	public void setChkListId(String chkListId) {
		this.chkListId = chkListId;
	}

	public String getChkListType() {
		return chkListType;
	}

	public void setChkListType(String chkListType) {
		this.chkListType = chkListType;
	}

	public String getChkListDate() {
		return chkListDate;
	}

	public void setChkListDate(String chkListDate) {
		this.chkListDate = chkListDate;
	}

	public String getChkListTotalScore() {
		return chkListTotalScore;
	}

	public void setChkListTotalScore(String chkListTotalScore) {
		this.chkListTotalScore = chkListTotalScore;
	}

	public String getChkListCounsellor() {
		return chkListCounsellor;
	}

	public void setChkListCounsellor(String chkListCounsellor) {
		this.chkListCounsellor = chkListCounsellor;
	}

	public String getChkListDesc() {
		return chkListDesc;
	}

	public void setChkListDesc(String chkListDesc) {
		this.chkListDesc = chkListDesc;
	}

}
