package com.brndbot.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** A BrandIdentity is a set of Promotion prototypes. One organization
 *  can have multiple brand identities. It isn't clear whether a user
 *  can have access to more than one brand identity.
 */
public class BrandIdentity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Map<String, Promotion> promotions;
	
	public BrandIdentity() {
		// TODO Auto-generated constructor stub
		promotions = new HashMap<>();
	}

	public void addPromotion (Promotion p) {
		String name = p.getName();
		promotions.put (name, p);
	}
	
	/** Returns a Promotion with a specified name. Returns
	 *  null if there's no matching promotion.
	 */
	public Promotion getPromotion (String name) {
		return promotions.get (name);
	}
}
