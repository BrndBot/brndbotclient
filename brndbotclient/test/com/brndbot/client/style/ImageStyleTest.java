package com.brndbot.client.style;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.Anchor;

public class ImageStyleTest {

	private ImageStyle isExample;
	
	@Before
	public void setUp() throws Exception {
		isExample = createExample ();
	}

	@Test
	public void testCopy() {
		ImageStyle is = new ImageStyle (isExample);
		assertEquals ("model1", is.getModel());
		assertEquals ("images/example.jpg", is.getImagePath());
		assertEquals (is.getWidth(), 30);
		assertEquals (is.getHeight(), 40);
		assertEquals (is.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (is.getOffsetX(), 18);
		assertEquals (is.getOffsetY(), 23);
	}

	@Test
	public void testCopyIsDistinct() {
		ImageStyle is = new ImageStyle (isExample);
		is.setModel ("changed");
		is.setImagePath ("images/another.png");
		is.setWidth (0);
		is.setHeight (0);
		is.setAnchor (Anchor.BOTTOM_LEFT);
		is.setOffsetX(0);
		is.setOffsetY(0);
		is.setDropShadowH(0);
		is.setDropShadowV(0);
		is.setDropShadowBlur(0);
		
		// Make sure that didn't change the original
		assertEquals ("model1", isExample.getModel ());
		assertEquals ("images/example.jpg", isExample.getImagePath ());
		assertEquals (isExample.getWidth(), 30);
		assertEquals (isExample.getHeight(), 40);
		assertEquals (isExample.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (isExample.getOffsetX(), 18);
		assertEquals (isExample.getOffsetY(), 23);
	}
	
	private ImageStyle createExample () {
		ImageStyle is = new ImageStyle();
		is.setImagePath("images/example.jpg");
		is.setModel ("model1");
		is.setWidth (30);
		is.setHeight (40);
		is.setAnchor (Anchor.TOP_RIGHT);
		is.setOffsetX(18);
		is.setOffsetY(23);
		return is;
	}
}
