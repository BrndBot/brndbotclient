/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client;

import java.util.List;


/**
 *  Each client module must define an entry class which implements ClientInterface.
 *  The fully qualified name of this class will be in the database, or in
 *  a configuration file pointed to from the database.
 *  
 */
public interface ClientInterface {
	// Is issueRequest really needed for anything?
	//public ClientResponse issueRequest(ClientRequest cr) ;
	
	public ModelCollection getModels ();
	public List<PromotionPrototype> getPromotionPrototypes ();
}
