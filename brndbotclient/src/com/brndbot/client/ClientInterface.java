/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client;

import java.io.Serializable;
import java.util.Map;


/**
 *  Each client module must define an entry class which implements ClientInterface.
 *  The fully qualified name of this class will be in the database, or in
 *  a configuration file pointed to from the database.
 *  
 *  Implementations of ClientInterface should take care to be really Serializable.
 */
public interface ClientInterface extends Serializable {

	/** Returns the name of the ClientInterface. Every implementation of
	 *  ClientInterface must have a unique name. */
	public String getName();
	
	/** Returns a ModelCollection hierarchically describing all the client's
	 *  models. The implementation should avoid making a large collection of models
	 *  into sesson data.
	 */
	public ModelCollection getModels ();
	
	/** Returns a Map of names to PromotionPrototypes for a given Model */
	public Map<String,Promotion> getPromotionPrototypes (String modelName);
}
