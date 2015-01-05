package com.brndbot.client;

import org.jdom2.Element;
import org.json.JSONException;
import org.json.JSONObject;

public class SVGField extends ModelField {

	private Element svg;
	private SVGStyle style;
	
	public SVGField(String name) {
		super(name, StyleType.SVG);
	}

	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public SVGField (SVGField modelf) {
		super (modelf.name, StyleType.SVG);
		svg = modelf.svg;		// safe only if the SVG is immutable
		style = modelf.style;
	}
	
	public Element getSVG () {
		return svg;
	}
	
	public void setSVG(Element s) {
		svg = s;
	}
	
	public SVGStyle getStyle () {
		return style;
	}
	
	public void setStyle (SVGStyle s) {
		style = s;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
		// TODO add local fields
	}
}
