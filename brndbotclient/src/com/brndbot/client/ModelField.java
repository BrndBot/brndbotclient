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
		BLOCK ("block"),
		BUTTON ("button");
		
		private String stringRep;
		
		private StyleType (String s) {
			stringRep = s;
		}
		
		public String toString () {
			return stringRep;
		}
	}
	
	public enum AnchorType {
		TOP_LEFT ("tl"),
		TOP_RIGHT ("tr"),
		BOTTOM_LEFT ("bl"),
		BOTTOM_RIGHT ("br");
		
		private String xmlRep;
		
		private AnchorType (String s) {
			xmlRep = s;
		}
		
		public static AnchorType findByString (String s) {
			switch (s) {
			case "tl":
				return TOP_LEFT;
			case "tr":
				return TOP_RIGHT;
			case "bl":
				return BOTTOM_LEFT;
			case "br":
				return BOTTOM_RIGHT;
			default:
				return null;
			}
		}
		
		public String toString () {
			return xmlRep;
		}
	}
	
	protected String name;
	protected StyleType styleType;
	protected AnchorType anchorType;
	protected int xOffset;
	protected int yOffset;
	
	public ModelField(String name, StyleType styleType) {
		this.name = name;
		this.styleType = styleType;
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
	
	public String getName () {
		return name;
	}
	
	public StyleType getStyleType () {
		return styleType;
	}
	
	public int getXOffset () {
		return xOffset;
	}
	
	public void setXOffset (int x) {
		xOffset = x;
	}
	
	public int getYOffset () {
		return yOffset;
	}
	
	public void setYOffset (int y) {
		yOffset = y;
	}
	
	public AnchorType getAnchorType () {
		return anchorType;
	}
	
	public void setAnchorType (String typ) {
		anchorType = AnchorType.findByString (typ);
	}
	
	/** Represent a ModelField as a JSON object. */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		val.put ("name", name);
		val.put ("styleType", styleType.toString ());
		return val;
	}
}
