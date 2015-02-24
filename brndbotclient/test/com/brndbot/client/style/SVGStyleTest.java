package com.brndbot.client.style;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.Anchor;

public class SVGStyleTest {
	
	private SVGStyle svgExample;
	
	XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());


	@Before
	public void setUp() throws Exception {
		svgExample = createExample ();
	}

	@Test
	public void testCopy() {
		SVGStyle ss = new SVGStyle (svgExample);
		assertEquals ("model1", ss.getModel());
		assertTrue (ss.getSVG().indexOf("<svg") >= 0);
		assertEquals (ss.getWidth(), 30);
		assertEquals (ss.getHeight(), 40);
		assertEquals (ss.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (ss.getOffsetX(), 18);
		assertEquals (ss.getOffsetY(), 23);
	}

	@Test
	public void testCopyIsDistinct() {
		SVGStyle ss = new SVGStyle (svgExample);
		ss.setModel ("changed");
		ss.setWidth (0);
		ss.setHeight (0);
		ss.setAnchor (Anchor.BOTTOM_LEFT);
		ss.setOffsetX(0);
		ss.setOffsetY(0);
		ss.setDropShadowH(0);
		ss.setDropShadowV(0);
		ss.setDropShadowBlur(0);
		
		// Make sure that didn't change the original
		assertEquals ("model1", svgExample.getModel ());
		assertEquals (svgExample.getWidth(), 30);
		assertEquals (svgExample.getHeight(), 40);
		assertEquals (svgExample.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (svgExample.getOffsetX(), 18);
		assertEquals (svgExample.getOffsetY(), 23);
		
		// SVG element is NOT cloned
		assertNotNull (svgExample.getSVG());
		assertNotNull (ss.getSVG());
	}
	
	private SVGStyle createExample () {
		SVGStyle ss = new SVGStyle();
		ss.setModel ("model1");
		Element svg = new Element ("svg");
		ss.setSVG(outputter.outputString(svg));
		ss.setWidth (30);
		ss.setHeight (40);
		ss.setAnchor (Anchor.TOP_RIGHT);
		ss.setOffsetX(18);
		ss.setOffsetY(23);
		return ss;
	}
}
