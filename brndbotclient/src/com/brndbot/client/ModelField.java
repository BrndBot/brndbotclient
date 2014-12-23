package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

/** A ModelField is (logically enough) a field of a Model. It 
 *  has a name and type. ModelField can be used on its own but
 *  also serves as a superclass for type-specific fields with 
 *  content.
 */
public class ModelField {

	public enum StyleType {
		TEXT ("text"),
		IMAGE ("image"),
		SVG ("svg"),
		LOGO ("logo"),
		BLOCK ("block");
		
		private String stringRep;
		
		private StyleType (String s) {
			stringRep = s;
		}
		
		public String toString () {
			return stringRep;
		}
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
	
	/** Represent a ModelField as a JSON object. */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		val.put ("name", name);
		val.put ("styleType", styleType.toString ());
		return val;
	}
}
