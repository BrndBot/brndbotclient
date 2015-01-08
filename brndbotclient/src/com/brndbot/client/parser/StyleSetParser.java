package com.brndbot.client.parser;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientException;
import com.brndbot.client.Style;
import com.brndbot.client.StyleSet;
import com.brndbot.client.ImageStyle;
import com.brndbot.client.TextStyle;
import com.brndbot.client.SVGStyle;
import com.brndbot.client.BlockStyle;
import com.brndbot.client.LogoStyle;

/**
 *  A StyleSetParser turns an XML styleset file into a StyleSet object.
 *  This implementation assumes that there is no namespace declaration in
 *  the XML file.
 */
public class StyleSetParser {

	final static Logger logger = LoggerFactory.getLogger(StyleSetParser.class);
	
	private final static Namespace svgNamespace = 
			Namespace.getNamespace ("http://www.w3.org/2000/svg");
	
	/* The AWT Dimension class uses floating point, so we create a custom
	 * dimension class here for convenience. */
	private class Dimension {
		private int width;
		private int height;
		
		public Dimension (int w, int h) {
			width = w;
			height = h;
		}
		
		public int getWidth () {
			return width;
		}
		
		public int getHeight () {
			return height;
		}

		/** getX and getWidth are equivalent. User whichever is semantically appropriate.
		 *  (Yes, this is overkill.) */
		public int getX () {
			return width;
		}
		
		/** getY and getHeight are equivalent. User whichever is semantically appropriate. */
		public int getY () {
			return height;
		}

	}
	
	/* A class for handling the drop shadow element */
	private class DropShadow {
		private int h;
		private int v;
		private int blur;
		
		public DropShadow (int h, int v, int b) {
			this.h = h;
			this.v = v;
			this.blur = b;
		}
		
		public int getH () {
			return h;
		}
		
		public void setH (int h) {
			this.h = h;
		}

		public int getV () {
			return v;
		}
		
		public void setV (int v) {
			this.v = v;
		}

		public int getBlur () {
			return blur;
		}
		
		public void setBlur (int b) {
			this.blur = b;
		}
}
	
	private File styleSetFile;

	public StyleSetParser(File file) {
		styleSetFile = file;
	}

	public StyleSet parse() throws ClientException {
		SAXBuilder builder = new SAXBuilder ();
		try {
			Document doc = builder.build (styleSetFile);
			Element root = doc.getRootElement();
			if (!"styleSet".equals (root.getName())) {
				throw new ClientException ("Not a valid model file");
			}
			String name = root.getAttributeValue ("name");
			if (name == null) {
				throw new ClientException ("styleset element has no name attribute");
			}
			StyleSet ss = new StyleSet(name);
			Dimension dim = parseDimensions ("dimensions", root);
			ss.setWidth((int) dim.getWidth()); 
			ss.setHeight((int) dim.getHeight());
			ss.setOrganization (root.getChildTextTrim("org"));
			ss.setModel (root.getChildTextTrim("model"));
			ss.setBrand (root.getChildTextTrim("brand"));
			ss.setPromotion (root.getChildTextTrim("promo"));
			List<Element> styles = root.getChildren ("style");
			parseStyles (styles, ss);
			return ss;
		} catch (Exception e) {
			throw new ClientException (e);
		}
	}
	
	private void parseStyles (List<Element> styles, StyleSet ss) throws ClientException {
		for (Element styleElem : styles) {
			// Each "style" element will have a single child, which is named
			// for one of the style types.
			Element styleChild = styleElem.getChildren().get(0);
			String styleName = styleChild.getName();
			switch (styleName) {
			case "text":
				addTextStyle (styleChild, ss);
				break;
			case "image":
				addImageStyle (styleChild, ss);
				break;
			case "svgdata":
				addSVGStyle (styleChild, ss);
				break;
			case "block":
				addBlockStyle (styleChild, ss);
				break;
			case "logo":
				addLogoStyle (styleChild, ss);
				break;
			default:
				throw (new ClientException ("Unknown style type " + styleName));
			}
		}
	}
	
	/** This function handles elements which are common to all styles */
	private void getCommonElements (Element styleElem, Style s) 
					throws ClientException {
		Dimension dim = parseDimensions("dimensions", styleElem);
		s.setWidth((int) dim.getWidth());
		s.setHeight((int) dim.getHeight());
		
		String anchorText = styleElem.getChildText("anchor");
		Style.Anchor anchor = null;
		switch (anchorText) {
		case "tl":
			anchor = Style.Anchor.TOP_LEFT;
			break;
		case "tr":
			anchor = Style.Anchor.TOP_RIGHT;
			break;
		case "bl":
			anchor = Style.Anchor.BOTTOM_LEFT;
			break;
		case "br":
			anchor = Style.Anchor.BOTTOM_RIGHT;
			break;
		}
		s.setAnchor(anchor);
		
		Dimension offset = parseDimensions("offset", styleElem);
		s.setOffsetX (offset.getX());
		s.setOffsetY (offset.getY());
	}
	
	private void addTextStyle (Element textElem, StyleSet ss) throws ClientException {
		logger.debug ("addTextStyle");
		TextStyle ts = new TextStyle();
		getCommonElements (textElem, ts);
		
		String alignText = textElem.getChildText("alignment");
		TextStyle.Alignment align = null;
		switch (alignText) {
		case "left":
			align = TextStyle.Alignment.LEFT;
			break;
		case "right":
			align = TextStyle.Alignment.RIGHT;
			break;
		case "center":
			align = TextStyle.Alignment.CENTER;
			break;
		case "justified":
			align = TextStyle.Alignment.JUSTIFIED;
			break;
		}
		ts.setAlignment (align);
		
		Element ital = textElem.getChild("italic");
		logger.debug ("Italic is {}", (ital != null ? "specified" : "not specified"));
		ts.setItalic (ital != null);
		Element bold = textElem.getChild("bold");
		logger.debug ("Bold is {}", (bold != null ? "specified" : "not specified"));
		ts.setBold (bold != null);
		Element size = textElem.getChild("size");
		int ptSize = 12;		// default if somehow missing or bad
		try {
			ptSize = Integer.parseInt(size.getText());
		} catch (Exception e) {
			logger.warn ("Bad point size, defaulting to 12");
		}
		ts.setPointSize(ptSize);
		
		DropShadow ds = parseDropShadow ("dropShadow", textElem);
		if (ds != null) {
			ts.setDropShadowH(ds.h);
			ts.setDropShadowV(ds.v);
			ts.setDropShadowBlur(ds.blur);
		}

		ss.addStyle (ts);
	}

	private void addImageStyle (Element imageElem, StyleSet ss) throws ClientException{
		ImageStyle is = new ImageStyle();
		getCommonElements (imageElem, is);
		is.setImagePath(imageElem.getChildText("imagepath"));
		is.setOpacity (Integer.parseInt(imageElem.getChildText("opacity")));
		Element multiply = imageElem.getChild("multiply");
		is.setMultiply (multiply != null);
		ss.addStyle (is);
	}

	/* This one could be tricky. */
	private void addSVGStyle (Element svgElem, StyleSet ss) throws ClientException {
		SVGStyle svgs = new SVGStyle();
		getCommonElements (svgElem, svgs);
		// Are there namespace issues here?
		Element svg = svgElem.getChild ("svg", svgNamespace);
		svgs.setSVG(svg);
		ss.addStyle (svgs);
	}

	private void addBlockStyle (Element blockElem, StyleSet ss) throws ClientException {
		BlockStyle bs = new BlockStyle();
		getCommonElements (blockElem, bs);
		bs.setOpacity (Integer.parseInt(blockElem.getChildText("opacity")));
		Element multiply = blockElem.getChild("multiply");
		bs.setMultiply (multiply != null);
		ss.addStyle (bs);
	}

	private void addLogoStyle (Element logoElem, StyleSet ss) throws ClientException {
		LogoStyle ls = new LogoStyle();
		getCommonElements (logoElem, ls);
		ss.addStyle (ls);
	}

	private Dimension parseDimensions (String dimElemName, Element elem) throws ClientException {
		Element dim = elem.getChild (dimElemName);
		if (dim == null) {
			return null;
		}
		try {
			String xstr = dim.getChildTextTrim("x");
			String ystr = dim.getChildTextTrim("y");
			int x = Integer.parseInt(xstr);
			int y = Integer.parseInt(ystr);
			return new Dimension (x, y);
		} catch (Exception e) {
			throw new ClientException (e);
		}
	}

	private DropShadow parseDropShadow (String dsElemName, Element elem) throws ClientException {
		Element ds = elem.getChild(dsElemName);
		if (ds == null) {
			return null;
		}
		try {
			String hstr = ds.getChildTextTrim("h");
			String vstr = ds.getChildTextTrim("v");
			String bstr = ds.getChildTextTrim("blur");
			int h = Integer.parseInt(hstr);
			int v = Integer.parseInt(vstr);
			int blur = Integer.parseInt(bstr);
			return new DropShadow (h, v, blur);
		} catch (Exception e) {
			throw new ClientException (e);
		}

	}
}
