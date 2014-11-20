package com.brndbot.client;

import java.util.Map;

/**
 *  Each package must define a main entry class which implements ClientInterface.
 *  
 *  ...as soon as we figure out what it consists of. It should, at a minimum, define
 *  a way to request data and get it back. We have to enumerate what the requests
 *  will be. They will be things like a list of top-level content types and a
 *  request of all content objects of a given type.
 *  
 *  So we need some terminology.
 *  
 *  Client content objects, content types; can we get more specific than this?
 *  
 *  Should there be a Request and Response object class?
 */
public interface ClientInterface {
	public ClientResponse issueRequest(ClientRequest cr) ;
}
