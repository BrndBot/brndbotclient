package com.brndbot.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.style.StyleSet;

/** A BrandPersonality is a set of Promotion prototypes. One organization
 *  can have multiple brand identities. 
 *  
 *  Styles are not part of a model, but rather are part of a brand
 *  personality and associated with a model. They're stored here 
 *  grouped by model name.
 */
public class BrandPersonality implements Serializable {

	final static Logger logger = LoggerFactory.getLogger(BrandPersonality.class);
	
	private static final long serialVersionUID = 1L;
	
	int id;			// ID of the brand personality in the database
	/* Map keyed by model name to map keyed by styleset name of stylesets */
	private Map<String, Map<String, StyleSet>> styleSetMap;
	private String name;
	
	public BrandPersonality(int id) {
		this.id = id;
		styleSetMap = new HashMap<>();
	}
	
	/** No-argument constructor. We use this to create a dummy
	 *  BrandPersonality if we can't get a real one. */
	public BrandPersonality () {
		this.id = 0;
		this.name = "empty";
		styleSetMap = new HashMap<>();
	}
	
	public String getName () {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
