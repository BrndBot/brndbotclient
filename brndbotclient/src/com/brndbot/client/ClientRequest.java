package com.brndbot.client;


/** The ClientResponse class encapsulates a request for a client's 
 *  data. It's intended to be very flexible.
 */
public abstract class ClientRequest {

	public abstract void setRequestType (String typ);
	
	public abstract void setParameter (String key, Object val);

}
