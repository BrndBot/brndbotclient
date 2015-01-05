/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client;

import java.io.Serializable;
import java.util.List;


/**
 *  Each client module must define an entry class which implements ClientInterface.
 *  The fully qualified name of this class will be in the database, or in
 *  a configuration file pointed to from the database.
 *  
 *  Implementations of ClientInterface should take care to be really Serializable.
 */
public interface ClientInterface extends Serializable {
	// Is issueRequest really needed for anything?
	//public ClientResponse issueRequest(ClientRequest cr) ;
	
	/** Returns a ModelCollection hierarchically describing all the client's
	 *  models 
	 */
	public ModelCollection getModels ();
	
	/** Returns a List of all the PromotionPrototypes for a given Model */
	public List<Promotion> getPromotionPrototypes (String modelName);
}
