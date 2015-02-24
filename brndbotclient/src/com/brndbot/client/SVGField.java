package com.brndbot.client;

import org.jdom2.Element;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.style.SVGStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.Style.StyleType;

public class SVGField extends ModelField {

	private String svg;
	private SVGStyle style;
	
	public SVGField(String name) {
		super(name, StyleType.SVG);
	}

	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public SVGField (SVGField modelf) {
		super (modelf);
		svg = modelf.svg;		// safe only if the SVG is immutable
		if (modelf.style != null) {
			style = new SVGStyle(modelf.style);
		}
	}
	
	/** Returns the SVG which has been set for this field, if any.
	 *  If it's null, returns the style's SVG. */
	public String getSVG () {
		if (svg != null) {
			return svg;
		}
		else return style.getSVG();
	}
	
	public void setSVG(String s) {
		svg = s;
	}
	
	public SVGStyle getStyle () {
		return style;
	}
	
	public void setStyle (Style s) {
		style = (SVGStyle) s;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
		// TODO add local fields
	}
}
