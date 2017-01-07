package com.act.common;

import java.io.Serializable;

public class ServiceType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3810574316497101740L;
	
	private int service_id;
	private String service_name;
	
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getService_name() {
		return service_name;
	}

}
