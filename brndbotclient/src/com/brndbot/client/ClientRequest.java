/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client;

import java.util.HashMap;
import java.util.Map;


/** The ClientRequest class encapsulates a request for a client's 
 *  data. It's intended to be very flexible.
 */
public class ClientRequest {

	private String requestType;
	private Map<String, Object> params;
	
	public ClientRequest(String typ) {
		requestType = typ;
		params = new HashMap<String,Object> ();
	}
	
	public void setParameter (String key, Object val) {
		params.put(key, val);
	}
	
	public Map<String, Object> getParameterMap () {
		return params;
	}
	
	public Object getParameter(String key) {
		return params.get(key);
	}

	public String getRequestType () {
		return requestType;
	}
}
