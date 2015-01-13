package com.brndbot.client.style;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.Anchor;

public class LogoStyleTest {
	
	private LogoStyle lsExample;

	@Before
	public void setUp() throws Exception {
		lsExample = createExample ();
	}

	@Test
	public void testCopy() {
		LogoStyle is = new LogoStyle (lsExample);
		assertEquals ("model1", is.getModel());
		assertEquals (is.getWidth(), 30);
		assertEquals (is.getHeight(), 40);
		assertEquals (is.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (is.getOffsetX(), 18);
		assertEquals (is.getOffsetY(), 23);
	}

	@Test
	public void testCopyIsDistinct() {
		LogoStyle ls = new LogoStyle (lsExample);
		ls.setModel ("changed");
		ls.setWidth (0);
		ls.setHeight (0);
		ls.setAnchor (Anchor.BOTTOM_LEFT);
		ls.setOffsetX(0);
		ls.setOffsetY(0);
		ls.setDropShadowH(0);
		ls.setDropShadowV(0);
		ls.setDropShadowBlur(0);
		
		// Make sure that didn't change the original
		assertEquals ("model1", lsExample.getModel ());
		assertEquals (lsExample.getWidth(), 30);
		assertEquals (lsExample.getHeight(), 40);
		assertEquals (lsExample.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (lsExample.getOffsetX(), 18);
		assertEquals (lsExample.getOffsetY(), 23);
	}

	private LogoStyle createExample () {
		LogoStyle is = new LogoStyle();
		is.setModel ("model1");
		is.setWidth (30);
		is.setHeight (40);
		is.setAnchor (Anchor.TOP_RIGHT);
		is.setOffsetX(18);
		is.setOffsetY(23);
		return is;
	}
}
