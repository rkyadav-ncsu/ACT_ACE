package com.act.server.db;

import java.util.Hashtable;
import java.util.Vector;

import com.act.common.CounsellingSessionObj;

public abstract class CounsellingSessionDB extends DB {
	
	abstract public String saveCounsellingSession(CounsellingSessionObj sessionObj);
	abstract public String updateCounsellingSession(CounsellingSessionObj sessionObj);
	abstract public Vector<CounsellingSessionObj> getCounsellingSessionObjs(Hashtable<String, String> htOptions);

}
