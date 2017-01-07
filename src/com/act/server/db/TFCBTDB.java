package com.act.server.db;

import java.util.Hashtable;
import java.util.Vector;

import com.act.common.CounselleeTFCBTStage;
import com.act.common.TFCBTStage;


public abstract class TFCBTDB  extends DB{
	
	public abstract Hashtable<String, TFCBTStage> getTFCBTStages();

}
