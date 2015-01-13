package com.brndbot.client.style;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StyleSetTest {
	
	private StyleSet exampleStyleSet;

	@Before
	public void setUp() throws Exception {
		exampleStyleSet = createExample ();
	}

	@Test
	public void testSimple() {
		assertEquals ("ss1", exampleStyleSet.getName());
		assertEquals ("modelT", exampleStyleSet.getModel());
		assertEquals ("Ford", exampleStyleSet.getOrganization());
		assertEquals ("Mercury", exampleStyleSet.getBrand());
		assertEquals ("promo1", exampleStyleSet.getPromotion());
		assertEquals (exampleStyleSet.getWidth(), 400);
		assertEquals (exampleStyleSet.getHeight(), 300);
	}
	
	@Test
	public void testStyleList () {
		List<Style> styles = exampleStyleSet.getStyles();
		assertEquals (styles.size(), 6);
		TextStyle ts = (TextStyle) styles.get(0);
		assertEquals (ts.getHeight (), 30);
		BlockStyle bks = (BlockStyle) styles.get(1);
		assertEquals (bks.getHeight (), 70);
		SVGStyle svs = (SVGStyle) styles.get(2);
		assertEquals (svs.getHeight (), 100);
		LogoStyle ls = (LogoStyle) styles.get(3);
		assertEquals (ls.getHeight (), 110);
		ImageStyle is = (ImageStyle) styles.get(4);
		assertEquals (is.getHeight (), 118);
		ButtonStyle bts = (ButtonStyle) styles.get(5);
		assertEquals (bts.getHeight (), 200);
	}
	

	private StyleSet createExample () {
		StyleSet ss = new StyleSet ("ss1");
		ss.setModel ("modelT");
		ss.setOrganization ("Ford");
		ss.setBrand ("Mercury");
		ss.setPromotion("promo1");
		ss.setWidth (400);
		ss.setHeight (300);
		TextStyle ts = new TextStyle ();
		ts.setHeight(30);
		ss.addStyle (ts);
		
		BlockStyle bks = new BlockStyle ();
		bks.setHeight (70);
		ss.addStyle (bks);
		
		SVGStyle svs = new SVGStyle ();
		svs.setHeight (100);
		ss.addStyle (svs);
		
		LogoStyle ls = new LogoStyle ();
		ls.setHeight (110);
		ss.addStyle (ls);
		
		ImageStyle is = new ImageStyle ();
		is.setHeight (118);
		ss.addStyle (is);
		
		ButtonStyle bts = new ButtonStyle ();
		bts.setHeight (200);
		ss.addStyle (bts);

		return ss;
	}
}
