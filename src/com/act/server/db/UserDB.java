package com.act.server.db;

import com.act.common.User;

public abstract class UserDB extends DB{
	
	public abstract boolean findUser(String userName, String passWord);
	

}