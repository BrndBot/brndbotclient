package com.brndbot.client;

import org.jdom2.Element;

public class SVGField extends ModelField {

	private Element svg;
	private SVGStyle style;
	
	public SVGField(String name) {
		super(name, StyleType.SVG);
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
}
