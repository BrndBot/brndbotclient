package com.brndbot.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		logger.debug("Creating BrandPersonality with id = {}", id);
		this.id = id;
		styleSetMap = new HashMap<>();
	}
	
	/** No-argument constructor. We use this to create a dummy
	 *  BrandPersonality if we can't get a real one. */
	public BrandPersonality () {
		logger.debug("Creating dummy BrandPersonality");
		this.id = 0;
		this.name = "empty";
		styleSetMap = new HashMap<>();
	}
	
	/** Return true if this was set up as a dummy brand personality */
	public boolean isDummy() {
		return (id == 0);
	}
	
	public String getName () {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, StyleSet> getStyleSetsForModel (String modelName) {
		logger.debug ("getStyleSetsForModel {}", modelName);
//		dumpStyleSetMap();
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
			styleSetMap.put (modelName, smap);
		}
//		dumpStyleSetMap();
		smap.put (styleSet.getName(), styleSet);
	}

	/** toString function as a debugging aid */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ("BrandPersonality: ");
		sb.append ("name = ");
		sb.append (name);
		sb.append ("    id = ");
		sb.append (id);
		if (styleSetMap != null) {
			sb.append ("styleSetMap size = ");
			sb.append (Integer.toString (styleSetMap.size()));
		}
		return sb.toString();
	}
	
	/* For debugging */
	@SuppressWarnings("unused")
	private void dumpStyleSetMap () {
		logger.debug ("Dumping styleSetMap");
		if (styleSetMap == null) {
			logger.debug ("StyleSetMap is null");
			return;
		}
		Set<String> keys = styleSetMap.keySet();
		for (String key : keys) {
			logger.debug ("key = {}", key);
			Map<String, StyleSet> val = styleSetMap.get(key);
			Set<String> sskeys = val.keySet();
			for (String sskey : sskeys) {
				logger.debug ("Style set key = {}", sskey);
			}
		}
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
