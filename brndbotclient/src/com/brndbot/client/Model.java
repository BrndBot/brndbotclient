package com.brndbot.client;

import java.util.ArrayList;
import java.util.List;

/*  A Model is a representation of a type of presentation. It defines
 *  fields to be included (text, image, etc.) but nothing about their
 *  content or visual characteristics.
 */
public class Model {

	private String name;
	private String description;
	private String category;
	private String organization;
	
	private List<ModelField> fields;
	
	public Model (String name, String desc) {
		this.name = name;
		this.description = desc;
		fields = new ArrayList<>();
	}
	
	/** Add a field to the model, defining it by name and type */
	public void addField (String name, ModelField.StyleType type) {
		ModelField mf = new ModelField (name, type);
		fields.add (mf);
	}
	
	public String getName () {
		return name;
	}
	
	public String getDescription () {
		return description;
	}
	
	public String getCategory () {
		return category;
	}
	
	public void setCategory(String c) {
		category = c;
	}
	
	public String getOrganization () {
		return organization;
	}
	
	public void setOrganization(String org) {
		organization = org;
	}
	
	public List<ModelField> getFields () {
		return fields;
	}
}
