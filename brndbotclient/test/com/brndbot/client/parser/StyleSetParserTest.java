package com.brndbot.client.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.jdom2.Element;
import org.junit.Test;

import com.brndbot.client.style.BlockStyle;
import com.brndbot.client.style.ImageStyle;
import com.brndbot.client.style.LogoStyle;
import com.brndbot.client.style.SVGStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.StyleSet;
import com.brndbot.client.style.TextStyle;

public class StyleSetParserTest {

	/** Minimal test that parser isn't completely broken */
	@Test
	public void testStyleSetParserMin() throws Exception {
		File testFile = new File ("testfiles/teststyleset1.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		assertNotNull(ss);
	}

	/** More thorough test on same file, not including styles */
	@Test
	public void testStyleSetParserBasic() throws Exception {
		File testFile = new File ("testfiles/teststyleset1.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		assertEquals ((Integer)ss.getWidth(), (Integer)120);
		assertEquals ((Integer)ss.getHeight(), (Integer)240);
		assertEquals (ss.getBrand(), "Shiny");
		assertEquals (ss.getPromotion(), "Workshop");
		assertEquals (ss.getOrganization(), "MiskatonicU");
	}

	/** Test on text style */
	@Test
	public void testStyleSetParserText () throws Exception {
		File testFile = new File ("testfiles/teststyleset2.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		List<Style> styles = ss.getStyles();
		Style s0 = styles.get(0);
//		Style s1 = styles.get(1);
//		Style s2 = styles.get(2);
//		Style s3 = styles.get(3);
//		Style s4 = styles.get(4);
		assertTrue(s0 instanceof TextStyle);
		TextStyle ts = (TextStyle) s0;
//		assertTrue(s1 instanceof BlockStyle);
//		assertTrue(s2 instanceof SVGStyle);
//		assertTrue(s3 instanceof ImageStyle);
//		assertTrue(s4 instanceof LogoStyle);
		assertEquals (ts.getWidth(), 120);
		assertEquals (ts.getHeight(), 30);
		assertEquals (ts.getAnchor(), Style.Anchor.TOP_LEFT);
		assertEquals (ts.getOffsetX(), 0);
		assertEquals (ts.getOffsetY(), 4);
		assertEquals (ts.getAlignment(), TextStyle.Alignment.LEFT);
		// Drop shadow is unspecified, so values are 0 by default
		assertEquals (ts.getDropShadowH(), 0);
		assertEquals (ts.getDropShadowV(), 0);
		assertEquals (ts.getDropShadowBlur(), 0);
		assertFalse (ts.isItalic());
		assertFalse (ts.isBold());
	}


	/** Test on block style */
	@Test
	public void testStyleSetParserBlock () throws Exception {
		File testFile = new File ("testfiles/teststyleset2.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		List<Style> styles = ss.getStyles();
		Style s1 = styles.get(1);
		assertTrue(s1 instanceof BlockStyle);
		BlockStyle bs = (BlockStyle) s1;
		assertEquals (bs.getWidth(), 120);
		assertEquals (bs.getHeight(), 30);
		assertEquals (bs.getAnchor(), Style.Anchor.TOP_LEFT);
		assertEquals (bs.getOffsetX(), 0);
		assertEquals (bs.getOffsetY(), 4);
		assertEquals (bs.getOpacity(), 24);
		assertTrue (bs.getMultiply());
	}
	
	/** Test on SVG style */
	@Test
	public void testStyleSetParserSVG () throws Exception {
		File testFile = new File ("testfiles/teststyleset2.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		List<Style> styles = ss.getStyles();
		Style s2 = styles.get(2);
		assertTrue(s2 instanceof SVGStyle);
		SVGStyle svgs = (SVGStyle) s2;
		assertEquals (svgs.getWidth(), 220);
		assertEquals (svgs.getHeight(), 40);
		assertEquals (svgs.getAnchor(), Style.Anchor.BOTTOM_RIGHT);
		assertEquals (svgs.getOffsetX(), 40);
		assertEquals (svgs.getOffsetY(), 30);
		String svg = svgs.getSVG();
		assertNotNull (svg);
	}

	/** Test on image style */
	@Test
	public void testStyleSetParserImage () throws Exception {
		File testFile = new File ("testfiles/teststyleset2.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		List<Style> styles = ss.getStyles();
		Style s3 = styles.get(3);
		assertTrue(s3 instanceof ImageStyle);
		ImageStyle is = (ImageStyle) s3;
		assertEquals (is.getWidth(), 100);
		assertEquals (is.getHeight(), 0);
		assertEquals (is.getAnchor(), Style.Anchor.BOTTOM_LEFT);
		assertEquals (is.getOffsetX(), 0);
		assertEquals (is.getOffsetY(), 0);
		assertEquals (is.getImagePath(), "/var/brndbot/images/abcd.jpg");
		assertEquals (is.getOpacity(), 90);
		assertFalse (is.getMultiply());
	}

	/** Test on logo style */
	@Test
	public void testStyleSetParserLogo () throws Exception {
		File testFile = new File ("testfiles/teststyleset2.xml");
		StyleSetParser sparser = new StyleSetParser(testFile);
		StyleSet ss = sparser.parse();
		List<Style> styles = ss.getStyles();
		Style s4 = styles.get(4);
		assertTrue(s4 instanceof LogoStyle);
		LogoStyle ls = (LogoStyle) s4;
		assertEquals (ls.getWidth(), 90);
		assertEquals (ls.getHeight(), 35);
		assertEquals (ls.getAnchor(), Style.Anchor.TOP_RIGHT);
		assertEquals (ls.getOffsetX(), 2);
		assertEquals (ls.getOffsetY(), 3);
	}
}
