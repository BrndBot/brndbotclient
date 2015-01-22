package com.brndbot.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.style.StyleSet;

/** A BrandIdentity is a set of Promotion prototypes. One organization
 *  can have multiple brand identities. 
 *  
 *  Styles are not part of a model, but rather are part of a brand
 *  identity and associated with a model. They're stored here 
 *  grouped by model name.
 */
public class BrandIdentity implements Serializable {

	final static Logger logger = LoggerFactory.getLogger(BrandIdentity.class);
	
	private static final long serialVersionUID = 1L;
	
	/* Map keyed by model name to map keyed by styleset name of stylesets */
	private Map<String, Map<String, StyleSet>> styleSetMap;
//	private Map<String, Promotion> promotions;
	private String name;
	
	public BrandIdentity(String name) {
		this.name = name;
//		promotions = new HashMap<>();
		styleSetMap = new HashMap<>();
	}
	
	public String getName () {
		return name;
	}
	
	public Map<String, StyleSet> getStyleSetsForModel (String modelName) {
		logger.debug ("getStyleSetsForModel {}", modelName);
		return styleSetMap.get(modelName);
	}
	
	public void addStyleSet (String modelName, StyleSet styleSet) {
		logger.debug ("addStyleSet for model {}", modelName);
		if (styleSet == null)
			logger.error ("Adding null styleSet!");
		// Get the map for the model, creating it if necessary.
		Map<String, StyleSet> smap = styleSetMap.get(modelName);
		if (smap == null) {
			smap = new HashMap<>();
			logger.debug ("Adding hashmap for model {}", modelName);
			styleSetMap.put (modelName, smap);
		}
		logger.debug ("Adding styleset {}", styleSet);
		smap.put (styleSet.getName(), styleSet);
	}

//	public void addPromotion (Promotion p) {
//		String name = p.getName();
//		promotions.put (name, p);
//	}
//	
//	/** Returns a Promotion with a specified name. Returns
//	 *  null if there's no matching promotion.
//	 */
//	public Promotion getPromotion (String name) {
//		return promotions.get (name);
//	}
}
