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

	/** Map from category name to a Map of models indexed by name. */
	private Map<String, Map<String, Model>> categories;
	private Map<String, Model> models;
	
	public ModelCollection () {
		categories = new HashMap<>();
		models = new HashMap<>();
	}
	
	/** Add a Model. It will be stored in its category. */
	public void addModel (Model m) {
		models.put (m.getName(), m);
		String cat = m.getCategory();
		Map<String,Model> catMap = categories.get(cat);
		if (catMap == null) {
			// Category is not already in map, add it
			catMap = new HashMap<>();
			categories.put(cat, catMap);
		}
		// Add the model to the category
		catMap.put (m.getName(), m);
	}
	
	/** Create and initialize a new category. */
	public void addCategory (String cat) {
		Map<String, Model> catModels = new HashMap<>();
		categories.put (cat, catModels);
	}
	
	/** Get all Models in a category.
	 *  Will return null if the category has no models. */
	public Map<String,Model> getModelsForCategory (String cat) {
		return categories.get(cat);
	}
	
	public Model getModelByName (String name) {
		return models.get(name);
	}
	
	/** Get all categories as an alphabetical list. */
	public List<String> getCategories () {
		Set<String> catSet = categories.keySet();
		List<String> catList = new ArrayList<String>(catSet);
		java.util.Collections.sort(catList);
		return catList;
	}
	
	/** Get all models as an alphabetic list. */
	public List<Model> getModels () {
		List<Model> modelList = new ArrayList<>(models.values());
		java.util.Collections.sort(modelList);
		return modelList;
	}
}
