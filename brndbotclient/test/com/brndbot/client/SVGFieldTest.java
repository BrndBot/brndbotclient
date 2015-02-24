package com.brndbot.client;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.json.JSONObject;
import org.junit.Test;

import com.brndbot.client.style.SVGStyle;

public class SVGFieldTest {

	@Test
	public void testConstruction() {
		SVGField imf = new SVGField ("name");
		assertEquals ("name", imf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		SVGField tf = new SVGField ("name");
		String svgElement = "<svg/>";
		tf.setSVG(svgElement);
		SVGStyle style = new SVGStyle ();
		tf.setStyle (style);
		SVGField cloneField = new SVGField (tf);
		assertEquals ("name", cloneField.getName());
		SVGStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
		String newSVGElement = tf.getSVG();
		assertEquals ("<svg/>", newSVGElement);
	}

	@Test
	public void testCopyOfStylelessField () {
		SVGField bf = new SVGField ("name");
		SVGField cloneField = new SVGField (bf);
		assertEquals ("name", cloneField.getName ());
		assertNull (cloneField.getStyle());
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
