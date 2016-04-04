package com.act.common;

import java.io.Serializable;

public class FamilyEnvIndivObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8889663838193826610L;
	
	private String famEnvComm;
	private String famEnvGetsAlong, famEnvVerAb, famEnvSubAb, famEnvPhyAb, famEnvSexAb;
	private String famEnvNeg, famEnvPros;
	private String caseNumber;
	
	public void setFamEnvComm(String famEnvComm) {
		this.famEnvComm = famEnvComm;
	}
	public String getFamEnvComm() {
		return famEnvComm;
	}
	public void setFamEnvGetsAlong(String famEnvGetsAlong) {
		this.famEnvGetsAlong = famEnvGetsAlong;
	}
	public String getFamEnvGetsAlong() {
		return famEnvGetsAlong;
	}
	public void setFamEnvVerAb(String famEnvVerAb) {
		this.famEnvVerAb = famEnvVerAb;
	}
	public String getFamEnvVerAb() {
		return famEnvVerAb;
	}
	public void setFamEnvSubAb(String famEnvSubAb) {
		this.famEnvSubAb = famEnvSubAb;
	}
	public String getFamEnvSubAb() {
		return famEnvSubAb;
	}
	public void setFamEnvPhyAb(String famEnvPhyAb) {
		this.famEnvPhyAb = famEnvPhyAb;
	}
	public String getFamEnvPhyAb() {
		return famEnvPhyAb;
	}
	public void setFamEnvSexAb(String famEnvSexAb) {
		this.famEnvSexAb = famEnvSexAb;
	}
	public String getFamEnvSexAb() {
		return famEnvSexAb;
	}
	public void setFamEnvNeg(String famEnvNeg) {
		this.famEnvNeg = famEnvNeg;
	}
	public String getFamEnvNeg() {
		return famEnvNeg;
	}
	public void setFamEnvPros(String famEnvPros) {
		this.famEnvPros = famEnvPros;
	}
	public String getFamEnvPros() {
		return famEnvPros;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	
	

}
