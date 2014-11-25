package com.brndbot.client;

import java.util.HashMap;
import java.util.Map;

/** This is a straightforward implementation of ClientResponse. It may
 *  be used as is, bypassed, or further subclassed.
 */
public class BasicClientResponse extends ClientResponse {

	private String responseType;
	private String status;
	private Map<String, Object> parameters;
	
	public BasicClientResponse (String typ) {
		responseType = typ;
		parameters = new HashMap<>();
	}
	
	
	@Override
	public String getResponseType() {
		return responseType;
	}

	@Override
	public String getStatus() {
		return status;
	}
	
	@Override
	public Object getParameter(String name) {
		return parameters.get(name);
	}

	@Override
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	public void setParameter (String key, Object val) {
		parameters.put (key, val);
	}

}
