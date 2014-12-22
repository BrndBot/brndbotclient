/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** A ModelCollection defines all the models which are currently available,
 *  grouping them by category.
 */
public class ModelCollection {

	private Map<String, List<Model>> categories;
	
	public ModelCollection () {
		categories = new HashMap<>();
	}
	
	/** Add a Model. It will be stored in its category. */
	public void addModel (Model m) {
		String cat = m.getCategory();
		List<Model> catList = categories.get(cat);
		if (catList == null) {
			// Category is not already in map, add it
			catList = new ArrayList<>();
			categories.put(cat, catList);
		}
		// Add the model to the category
		catList.add (m);
	}
	
	/** Get all Models in a category.
	 *  Will return null if the category has no models. */
	public List<Model> getModelsForCategory (String cat) {
		return categories.get(cat);
	}
	
	/** Get all categories as an alphabetical list. */
	public List<String> getCategories () {
		Set<String> catSet = categories.keySet();
		List<String> catList = new ArrayList<String>(catSet);
		java.util.Collections.sort(catList);
		return catList;
	}
}
