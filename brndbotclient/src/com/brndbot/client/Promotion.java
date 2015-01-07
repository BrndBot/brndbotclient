package com.brndbot.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A Promotion defines the fields of a promotion using a
 *  Model, and assigns a default styleset and content to them.
 *  The same class is used for prototypes which are available to the
 *  user and content-specific promotions created by the user.
 *  
 */
public class Promotion implements Serializable {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(Promotion.class);
	
	protected String name;
	protected Model model;
	protected StyleSet styleSet;
	private List<ModelField> content;

	/** Constructor. The StyleSet may be null. */
	public Promotion (String n, Model m, StyleSet ss) {
		name = n;
		model = m;
		styleSet = ss;
		// Clone model content into the Promotion, doing a deep copy
		content = new ArrayList<>();
		for (ModelField f : m.getFields()) {
			content.add (f.replicate());
		}
	}
	
	/** Copy constructor. This generates a working Promotion from a 
	 *  prototype.
	 */
	public Promotion (Promotion proto) {
		name = proto.name;
		model = proto.model;
		styleSet = proto.styleSet;
		content = new ArrayList<>(proto.content.size());
		for (ModelField f : proto.content) {
			content.add (f.replicate());
		}
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
	
	public void setStyleSet (StyleSet ss) {
		styleSet = ss;
	}

	public List<ModelField> getContent () {
		return content;
	}
	
	/** Locate a field with a given name */
	public ModelField getNamedField (String name) {
		logger.debug ("getNamedField: {}", name);
		for (ModelField f : content) {
			if (name.equals (f.getName())) {
				logger.debug ("Found a field");
				return f;
			}
		}
		return null;
	}
	
	/** We'll need to convert a promotion prototype to JSON in order
	 *  to feed it to JavaScript. */
	/** Convert a Promotion to JSON so that JavaScript can use it.
	 *  Return it as a JSONObject, since this might be just part of a bigger
	 *  collection 
	 */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		if (model != null) {
			val.put ("modelName", model.getName());
		}
		if (name != null) {
			val.put ("name", name);
		}
		if (styleSet != null) {
			val.put ("styleSetName", styleSet.getName());
		}
		if (model == null) {
			logger.error ("PromotionPrototype has no model");
			return null;
		}
		// Use content by preference, or else the model
		List<ModelField> fields = content;
		if (content == null)
			fields = model.getFields();
		if (fields == null) {
			logger.error ("Uninitialized model");
			return null;
		}
		// Each field gets a JSON value which is its name prefixed by "field"
		// This will get messy in the Kendo template. We'll need to use the
		// model to build the template! We may need to set up some conventions.
		for (ModelField field : fields) {
			val.put ("field" + field.getName(), field.toJSON());
		}
		return val;
	}}
