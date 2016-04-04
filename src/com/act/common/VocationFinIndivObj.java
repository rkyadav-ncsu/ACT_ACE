package com.act.common;

import java.io.Serializable;
import java.util.Vector;


public class VocationFinIndivObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 70962481676248309L;
	
	private String caseNumber;
	private String   vocPriorEmp,  vocWork,   vocFamDebt;
	private String   vocComm;
//	private Vector vocLangComn ;  								// ND and G edited and added on 8th Jan 16
//	private Vector vocLangWrite, vocLangRead, vocLangSpeak;
	private Vector<LangFluency> vLanguages;						// ND and G edited and added on 8th Jan 16
	private String vocLangW, vocLangR, vocLangS;				// ND added 19th Mar 16
	
	public void setVocPriorEmp(String vocPriorEmp) {
		this.vocPriorEmp = vocPriorEmp;
	}
	public String getVocPriorEmp() {
		return vocPriorEmp;
	}
	public void setVocWork(String vocWork) {
		this.vocWork = vocWork;
	}
	public String getVocWork() {
		return vocWork;
	}
	public void setVocFamDebt(String vocFamDebt) {
		this.vocFamDebt = vocFamDebt;
	}
	public String getVocFamDebt() {
		return vocFamDebt;
	}
	public void setVocComm(String vocComm) {
		this.vocComm = vocComm;
	}
	public String getVocComm() {
		return vocComm;
	}

	// start ND and G edited and added on 8th Jan 16
	public String getVocLangWrite() {
		if (vLanguages != null){
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < vLanguages.size(); i++) {
				LangFluency langFl = vLanguages.elementAt(i);
				if (langFl.isCanWrite()){
					if (i >0) 
						sb.append(",");
					sb.append(langFl.getLangName());
																			// ND edited 28th Mar 16
				}
			}
			return sb.toString();
		}
		
		return null;
	}
	public String getVocLangRead() {
		if (vLanguages != null){
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < vLanguages.size(); i++) {
				LangFluency langFl = vLanguages.elementAt(i);
				if (langFl.isCanRead()){
					if (i > 0)
						sb.append(",");
					sb.append(langFl.getLangName());						// ND edited 28th Mar 16
				}
			}
			return sb.toString();
		}
		
		return null;
	}
	public String getVocLangSpeak() {										// ND edited 28th Mar 18
		if (vLanguages != null){
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < vLanguages.size(); i++) {
				LangFluency langFl = vLanguages.elementAt(i);
				if (langFl.isCanSpeak()){
					if (i > 0)
						sb.append(",");
					sb.append(langFl.getLangName());
				}
			}
			return sb.toString();
		}
		
		return null;
	}
// // ND and G edited and added on 8th Jan 16
	
	public void setvLanguages(Vector vLanguages) {
		this.vLanguages = vLanguages;
	}
	public Vector getvLanguages() {
		return vLanguages;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setVocLangW(String vocLangW) {
		this.vocLangW = vocLangW;
	}
	public String getVocLangW() {
		return vocLangW;
	}
	public void setVocLangR(String vocLangR) {
		this.vocLangR = vocLangR;
	}
	public String getVocLangR() {
		return vocLangR;
	}
	public void setVocLangS(String vocLangS) {
		this.vocLangS = vocLangS;
	}
	public String getVocLangS() {
		return vocLangS;
	}


}
