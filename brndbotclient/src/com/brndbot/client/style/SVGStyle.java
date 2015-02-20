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

	/** The SVG content, in JDOM form */
	private Element svg;
	
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
	public Element getSVG () {
		return svg;
	}
	
	/** Set the SVG element. This does not create a new element. */
	public void setSVG (Element svg) {
		this.svg = svg;
	}

	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject ();
		putStandardJSONFields (val);
		val.put ("svgdata", svg);
		return val;				
	}
}
