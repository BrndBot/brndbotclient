package com.brndbot.client;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.json.JSONObject;
import org.junit.Test;

public class SVGFieldTest {

	@Test
	public void testConstruction() {
		SVGField imf = new SVGField ("name");
		assertEquals ("name", imf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		SVGField tf = new SVGField ("name");
		Element svgElement = new Element ("svg");
		svgElement.addContent ("<svg/>");
		tf.setSVG(svgElement);
		SVGStyle style = new SVGStyle ();
		tf.setStyle (style);
		SVGField cloneField = new SVGField (tf);
		assertEquals ("name", cloneField.getName());
		SVGStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
		Element newSVGElement = tf.getSVG();
		assertEquals ("svg", newSVGElement.getName());
	}

	@Test
	public void testJSON () throws Exception {
		SVGField tf = new SVGField ("name1");
		SVGStyle style = new SVGStyle ();
		assertNotNull (style);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("svg", json.get("styleType"));
	}

}
