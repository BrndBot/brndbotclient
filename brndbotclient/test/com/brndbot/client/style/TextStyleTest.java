package com.brndbot.client.style;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.Anchor;
import com.brndbot.client.style.TextStyle.Alignment;

public class TextStyleTest {
	
	private TextStyle txExample;

	@Before
	public void setUp() throws Exception {
		txExample = createExample();
	}

	@Test
	public void testCopy() {
		TextStyle ts = new TextStyle (txExample);
		assertEquals ("model1", ts.getModel());
		assertEquals (ts.getWidth(), 30);
		assertEquals (ts.getHeight(), 40);
		assertEquals (ts.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (ts.getOffsetX(), 18);
		assertEquals (ts.getOffsetY(), 23);
		
		assertEquals ("Comic Avec", ts.getTypeface());
		assertEquals (ts.getPointSize (), 40);
		assertTrue (ts.isItalic ());
		assertTrue (ts.isBold());
		assertTrue (ts.isDropShadow());
		assertEquals ("#AA00BB", ts.getTextColor());
		assertEquals (Alignment.RIGHT, ts.getAlignment());
	}

	@Test
	public void testCopyIsDistinct() {
		TextStyle ts = new TextStyle (txExample);
		ts.setModel ("changed");
		ts.setWidth (0);
		ts.setHeight (0);
		ts.setAnchor (Anchor.BOTTOM_LEFT);
		ts.setOffsetX(0);
		ts.setOffsetY(0);
		ts.setDropShadowH(0);
		ts.setDropShadowV(0);
		ts.setDropShadowBlur(0);
		ts.setTypeface("Times Bold");
		ts.setPointSize(72);
		ts.setItalic(false);
		ts.setBold(false);
		ts.setDropShadow(false);
		ts.setTextColor ("black");
		ts.setAlignment(Alignment.JUSTIFIED);
		
		// Make sure that didn't change the original
		assertEquals ("model1", txExample.getModel ());
		assertEquals (txExample.getWidth(), 30);
		assertEquals (txExample.getHeight(), 40);
		assertEquals (txExample.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (txExample.getOffsetX(), 18);
		assertEquals (txExample.getOffsetY(), 23);
		assertEquals ("Comic Avec", txExample.getTypeface());
		assertEquals (txExample.getPointSize (), 40);
		assertTrue (txExample.isItalic ());
		assertTrue (txExample.isBold());
		assertTrue (txExample.isDropShadow());
		assertEquals ("#AA00BB", txExample.getTextColor());
		assertEquals (Alignment.RIGHT, txExample.getAlignment());

	}
	
	

	
	private TextStyle createExample () {
		TextStyle ts = new TextStyle();
		ts.setModel ("model1");
		ts.setWidth (30);
		ts.setHeight (40);
		ts.setAnchor (Anchor.TOP_RIGHT);
		ts.setOffsetX(18);
		ts.setOffsetY(23);
		ts.setTypeface("Comic Avec");
		ts.setPointSize(40);
		ts.setItalic (true);
		ts.setBold (true);
		ts.setDropShadow (true);
		ts.setTextColor ("#AA00BB");
		ts.setAlignment (Alignment.RIGHT);
		return ts;
	}
}
