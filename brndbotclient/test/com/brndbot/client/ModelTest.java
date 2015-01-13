package com.brndbot.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.StyleType;

public class ModelTest {
	
	private Model modelExample;

	@Before
	public void setUp() throws Exception {
		modelExample = createExample ();
	}

	@Test
	public void testSimple() {
		assertEquals ("name", modelExample.getName ());
		assertEquals ("teachers", modelExample.getCategory ());
		assertEquals ("example organization", modelExample.getOrganization());
	}
	
	@Test
	public void testSorting() {
		Model m2 = new Model ("xyzzy", "description");
		assertTrue (modelExample.compareTo(m2) < 0);
		Model m3 = new Model ("aaaa", "description");
		assertFalse (modelExample.compareTo(m3) < 0);
	}
	
	@Test
	public void testFields () {
		List<ModelField> fields = modelExample.getFields ();
		assertEquals (fields.size(), 6);
		ImageField imf = (ImageField) fields.get(0);
		assertEquals ("image", imf.getName());
		TextField txf = (TextField) fields.get(1);
		assertEquals ("text", txf.getName ());
		SVGField svgf = (SVGField) fields.get(2);
		assertEquals ("svg", svgf.getName ());
		LogoField lgf = (LogoField) fields.get(3);
		assertEquals ("logo", lgf.getName ());
		ButtonField btf = (ButtonField) fields.get(4);
		assertEquals ("button", btf.getName ());
		BlockField bkf = (BlockField) fields.get(5);
		assertEquals ("block", bkf.getName ());
	}

	private Model createExample () {
		Model m  = new Model ("name", "description");
		m.setCategory ("teachers");
		m.setOrganization ("example organization");
		m.addField ("image", StyleType.IMAGE);
		m.addField ("text", StyleType.TEXT);
		m.addField ("svg", StyleType.SVG);
		m.addField ("logo", StyleType.LOGO);
		m.addField ("button", StyleType.BUTTON);
		m.addField ("block", StyleType.BLOCK);
		return m;
	}
}
