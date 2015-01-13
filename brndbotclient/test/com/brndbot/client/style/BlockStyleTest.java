package com.brndbot.client.style;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.style.Style.Anchor;

public class BlockStyleTest {
	
	private BlockStyle bsExample;

	@Before
	public void setUp() throws Exception {
		bsExample = createExample();
	}



	@Test
	public void testCopy() {
		BlockStyle bs = new BlockStyle (bsExample);
		assertEquals ("#001122", bs.getColor());
		assertEquals (bs.getWidth(), 30);
		assertEquals (bs.getHeight(), 40);
		assertEquals (bs.getAnchor (), Anchor.TOP_LEFT);
		assertEquals (bs.getOffsetX(), 18);
		assertEquals (bs.getOffsetY(), 23);
		assertEquals (bs.getDropShadowH(), 7);
		assertEquals (bs.getDropShadowV(), 17);
		assertEquals (bs.getDropShadowBlur(), 27);
	}

	@Test
	public void testCopyIsDistinct() {
		BlockStyle bs = new BlockStyle (bsExample);
		bs.setColor ("#000000");
		bs.setWidth (0);
		bs.setHeight (0);
		bs.setAnchor (Anchor.BOTTOM_LEFT);
		bs.setOffsetX(0);
		bs.setOffsetY(0);
		bs.setDropShadowH(0);
		bs.setDropShadowV(0);
		bs.setDropShadowBlur(0);
		
		// Make sure that didn't change the original
		assertEquals ("#001122", bsExample.getColor());
		assertEquals (bsExample.getWidth(), 30);
		assertEquals (bsExample.getHeight(), 40);
		assertEquals (bsExample.getAnchor (), Anchor.TOP_LEFT);
		assertEquals (bsExample.getOffsetX(), 18);
		assertEquals (bsExample.getOffsetY(), 23);
		assertEquals (bsExample.getDropShadowH(), 7);
		assertEquals (bsExample.getDropShadowV(), 17);
		assertEquals (bsExample.getDropShadowBlur(), 27);
	}
	
	private BlockStyle createExample () {
		BlockStyle bs = new BlockStyle();
		bs.setColor("#001122");
		bs.setWidth (30);
		bs.setHeight (40);
		bs.setAnchor (Anchor.TOP_LEFT);
		bs.setOffsetX(18);
		bs.setOffsetY(23);
		bs.setDropShadowH(7);
		bs.setDropShadowV(17);
		bs.setDropShadowBlur(27);
		return bs;
	}
}
