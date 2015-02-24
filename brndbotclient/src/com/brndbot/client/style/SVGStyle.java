package com.brndbot.client.style;

import org.jdom2.Element;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *  Tricky question here: How do we store the SVG? 
 *  Storing it as a JDOM element may be best, though it commits
 *  us to using JDOM all through the stack. 
 */
public class SVGStyle extends Style {

	private static final long serialVersionUID = 1L;

	/** The SVG content, as a string */
	private String svg;
	
	public SVGStyle () {
	}
	
	public SVGStyle(SVGStyle s) {
		super (s);
		svg = s.svg;
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.SVG;
	}

	/** Get the SVG element. */
	public String getSVG () {
		return svg;
	}
	
	/** Set the SVG element, which is a formatted XML string. */
	public void setSVG (String svg) {
		this.svg = svg;
	}

	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		logger.debug ("SVGStyle.toJSON");
		JSONObject val = new JSONObject ();
		putStandardJSONFields (val);
		val.put ("svg", svg);
		return val;				
	}
}
