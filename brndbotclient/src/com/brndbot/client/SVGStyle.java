package com.brndbot.client;

import org.jdom2.Element;

/**
 *  Tricky question here: How do we store the SVG? 
 *  Storing it as a JDOM element may be best, though it commits
 *  us to using JDOM all through the stack. 
 */
public class SVGStyle extends Style {

	/** The SVG content, in JDOM form */
	private Element svg;
	
	public SVGStyle() {
	}
	
	public Element getSVG () {
		return svg;
	}
	
	public void setSVG (Element svg) {
		this.svg = svg;
	}

}
