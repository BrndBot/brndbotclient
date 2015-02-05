package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.style.Style;
import com.brndbot.client.style.Style.StyleType;
import com.brndbot.client.style.StyleSet;

/** A ModelField is (logically enough) a field of a Model. It 
 *  has a name and type. ModelField can be used on its own but
 *  also serves as a superclass for type-specific fields with 
 *  content.
 */
public abstract class ModelField {


	
	
	final static Logger logger = LoggerFactory.getLogger(ModelField.class);
	
	protected String name;
	protected StyleType styleType;
	
	public ModelField(String name, StyleType styleType) {
		this.name = name;
		this.styleType = styleType;
	}
	
	/** Copy constructor. Copies the elements common to all subclasses.
	 *  The subclass copy constructor must finish the job. */
	public ModelField (ModelField modelf) {
		this.name = modelf.name;
		this.styleType = modelf.styleType;
	}
	
	/** Replicate the field. This shies away from calling itself a 
	 *  clone method, since it works only on well-behaved objects,
	 *  and simply aggregates the subclasses' copy constructors.
	 */
	public ModelField replicate () {
		switch (styleType) {
		case TEXT:
			return new TextField ((TextField) this);
		case IMAGE:
			return new ImageField ((ImageField) this);
		case SVG:
			return new SVGField ((SVGField) this);
		case LOGO:
			return new LogoField ((LogoField) this);
		case BLOCK:
			return new BlockField ((BlockField) this);
		case BUTTON:
			return new ButtonField ((ButtonField) this);
		default:
			return null;
		}
	}
	
	public abstract void setStyle (Style s);
	
	public String getName () {
		return name;
	}
	
	public StyleType getStyleType () {
		return styleType;
	}
	
	
	/** Represent a ModelField as a JSON object. 
	 *  Style data is not included, so 
	 *  JavaScript needs to apply a style before drawing.
	 */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		val.put ("name", name);
		val.put ("styleType", styleType.toString ());
		
		return val;
	}
	
	/** toString for debugging convenience. toJSON is as good as anything. */
	public String toString () {
		String val;
		try {
			val = "ModelField: " + toJSON().toString();
		} catch (Exception e) {
			val = "ModelField, could not JSONize";
		}
		return val;
	}
}
