package com.act.server.db;

import java.util.Hashtable;
import java.util.Vector;

import com.act.common.Counsellee;



public abstract class CounselleeDB extends DB{
	
	public abstract Vector<Counsellee> getCounsleeList(String CnslrId,
													Hashtable<String, String> htOptions);

}
