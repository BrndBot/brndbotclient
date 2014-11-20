package com.brndbot.client;

import java.util.Map;

/** The ClientResponse class encapsulates a response from a client's 
 *  data. It's intended to be very flexible.
 */
public abstract class ClientResponse {

	public abstract String getResponseType();
	
	/** Returns a Map representing the response in a consistent way.
	 *  The value may be any data type, with the restriction that
	 *  if it's a Map, its keys should be Strings. */
	public abstract Map<String, Object> getResponseAsMap();
}
