package com.brndbot.client;

import java.util.ArrayList;
import java.util.List;

/*  A Model is a representation of a type of presentation. It defines
 *  fields to be included (text, image, etc.) but nothing about their
 *  content or visual characteristics.
 */
public class Model implements Comparable<Model> {

	private String name;
	private String description;
	private String category;
	private String organization;
	private String buttonImage;
	
	private List<ModelField> fields;
	
	public Model (String name, String desc) {
		this.name = name;
		this.description = desc;
		fields = new ArrayList<>();
	}
	
	/** Add a field to the model, defining it by name and type */
	public void addField (String name, ModelField.StyleType type) {
		switch (type) {
		case TEXT:
			TextField tf = new TextField (name);
			fields.add(tf);
			break;
		case IMAGE:
			ImageField imf = new ImageField (name);
			fields.add (imf);
			break;
		case SVG:
			SVGField sf = new SVGField (name);
			fields.add (sf);
			break;
		case BLOCK:
			BlockField bf = new BlockField (name);
			fields.add (bf);
			break;
		case LOGO:
			LogoField lf = new LogoField (name);
			fields.add (lf);
			break;
		case BUTTON:
			ButtonField btf = new ButtonField (name);
			fields.add (btf);
			break;
		}
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
	
	/** The path, relative to the local storage directory, for
	 *  the model's button image. */
	public String getButtonImage () {
		return buttonImage;
	}
	
	/** Set the path, relative to the local storage directory, of
	 *  the model's button image. */
	public void setButtonImage (String path) {
		buttonImage = path;
	}
	
	public List<ModelField> getFields () {
		return fields;
	}

	/** Implement a comparison method for purposes of sorting by name */
	@Override
	public int compareTo(Model m) {
		return this.name.compareTo(m.name);
	}
}
