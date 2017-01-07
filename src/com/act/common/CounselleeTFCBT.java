package com.act.common;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class CounselleeTFCBT extends CounsellingTherapy implements Serializable{

	private static final long serialVersionUID = 7169964962587516326L;
	private Hashtable<String, CnsleeTFCBTTask> htTasks;

	public Hashtable<String, CnsleeTFCBTTask> getTasks() {
		return htTasks;
	}

	public void setTasks(Hashtable<String, CnsleeTFCBTTask> htTasks) {
		this.htTasks = htTasks;
	}
	
	//get tasks for a stage
	
	//get milestones for a stage
	
	//getLatestStage of TFCBT
}
