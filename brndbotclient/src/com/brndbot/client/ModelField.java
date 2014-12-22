package com.brndbot.client;

/** A ModelField is (logically enough) a field of a Model. It 
 *  has a name and type.
 */
public class ModelField {

	public enum StyleType {
		TEXT,
		IMAGE,
		SVG,
		LOGO,
		BLOCK
	}
	
	private String name;
	private StyleType styleType;
	
	public ModelField(String name, StyleType styleType) {
		this.name = name;
		this.styleType = styleType;
	}
	
	public String getName () {
		return name;
	}
	
	public StyleType getStyleType () {
		return styleType;
	}
}
