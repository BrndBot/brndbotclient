package com.brndbot.client;

import java.util.Map;

/** The ClientResponse class encapsulates a response from a client's 
 *  data. It's intended to be very flexible. ClientResponse may be overridden,
 *  though it may turn out to be useful to provide a standard implementation that
 *  just manipulates a map of responses.
 */
public abstract class ClientResponse {

	public final static String OK = "ok";
	
	public abstract String getResponseType();
	
	/** All implementations should return OK for success. Any
	 *  other response indicates some kind of problem. 
	 */
	public abstract String getStatus();
	
	/** Returns a Map representing the response in a consistent way.
	 *  The value may be any data type, with the restriction that
	 *  if it's a Map, its keys should be Strings. */
	public abstract Map<String, Object> getResponseAsMap();
}
