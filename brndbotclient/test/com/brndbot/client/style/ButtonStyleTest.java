package com.brndbot.client.style;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.Anchor;

public class ButtonStyleTest {

	private ButtonStyle bsExample;
	
	@Before
	public void setUp() throws Exception {
		bsExample = createExample();
	}

	@Test
	public void testCopy() {
		ButtonStyle bs = new ButtonStyle (bsExample);
		assertEquals ("Click me", bs.getText());
		assertEquals ("http://www.example.com/123", bs.getUrl());
		assertEquals (bs.getWidth(), 30);
		assertEquals (bs.getHeight(), 40);
		assertEquals (bs.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (bs.getOffsetX(), 18);
		assertEquals (bs.getOffsetY(), 23);
		assertEquals (bs.getDropShadowH(), 7);
		assertEquals (bs.getDropShadowV(), 17);
		assertEquals (bs.getDropShadowBlur(), 27);
	}

	@Test
	public void testCopyIsDistinct() {
		ButtonStyle bs = new ButtonStyle (bsExample);
		bs.setText ("Ignore me");
		bs.setUrl ("http://www.example.com/different");
		bs.setWidth (0);
		bs.setHeight (0);
		bs.setAnchor (Anchor.BOTTOM_LEFT);
		bs.setOffsetX(0);
		bs.setOffsetY(0);
		bs.setDropShadowH(0);
		bs.setDropShadowV(0);
		bs.setDropShadowBlur(0);
		
		// Make sure original is unchanged
		assertEquals ("Click me", bsExample.getText());
		assertEquals ("http://www.example.com/123", bsExample.getUrl());
		assertEquals (bsExample.getWidth(), 30);
		assertEquals (bsExample.getHeight(), 40);
		assertEquals (bsExample.getAnchor (), Anchor.TOP_RIGHT);
		assertEquals (bsExample.getOffsetX(), 18);
		assertEquals (bsExample.getOffsetY(), 23);
		assertEquals (bsExample.getDropShadowH(), 7);
		assertEquals (bsExample.getDropShadowV(), 17);
		assertEquals (bsExample.getDropShadowBlur(), 27);
	}

	private ButtonStyle createExample () {
		ButtonStyle bs = new ButtonStyle ();
		bs.setText ("Click me");
		bs.setUrl("http://www.example.com/123");
		bs.setWidth (30);
		bs.setHeight (40);
		bs.setAnchor (Anchor.TOP_RIGHT);
		bs.setOffsetX(18);
		bs.setOffsetY(23);
		bs.setDropShadowH(7);
		bs.setDropShadowV(17);
		bs.setDropShadowBlur(27);
		return bs;	}
}
