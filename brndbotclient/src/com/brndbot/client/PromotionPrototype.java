package com.brndbot.client;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** A PromotionPrototype defines the fields of a promotion using a
 *  Model, and assigns a default styleset and content to them.
 *  
 *  Should there be a subclass called Promotion? Maybe it should be in
 *  bbproject rather than here. 
 *  
 *  Waitwaitwait!! The point of a Promotion Prototype is that it holds
 *  specific content. I'm starting to think a PromotionPrototype and
 *  a Promotion should be the same thing, except that a Promotion is
 *  customized by the user.
 */
public class PromotionPrototype {

	protected String name;
	protected Model model;
	protected StyleSet styleSet;
	private List<ModelField> content;

	/** Constructor. The StyleSet may be null. */
	public PromotionPrototype (String n, Model m, StyleSet ss) {
		name = n;
		model = m;
		styleSet = ss;
	}
	
	public String getName () {
		return name;
	}
	
	public Model getModel () {
		return model;
	}
	
	public StyleSet getStyleSet () {
		return styleSet;
	}

	public List<ModelField> getContent () {
		return content;
	}
	
	/** We'll need to convert a promotion prototype to JSON in order
	 *  to feed it to JavaScript. */
	/** Convert a Promotion to JSON so that JavaScript can use it.
	 *  Return it as a JSONObject, since this might be just part of a bigger
	 *  collection 
	 */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		val.put ("modelName", model.getName());
		val.put ("styleSetName", styleSet.getName());
		List<ModelField> fields = model.getFields();
		JSONArray fieldArray = new JSONArray();
		for (ModelField field : fields) {
			fieldArray.put (field.toJSON());
		}
		return val;
	}}
