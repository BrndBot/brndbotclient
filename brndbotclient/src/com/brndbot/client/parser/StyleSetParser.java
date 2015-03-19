package com.brndbot.client.parser;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientException;
import com.brndbot.client.style.BlockStyle;
import com.brndbot.client.style.ImageStyle;
import com.brndbot.client.style.LogoStyle;
import com.brndbot.client.style.SVGStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.StyleSet;
import com.brndbot.client.style.TextStyle;

/**
 *  A StyleSetParser turns an XML styleset file into a StyleSet object.
 *  This implementation assumes that there is no namespace declaration in
 *  the XML file.
 */
public class StyleSetParser {

	final static Logger logger = LoggerFactory.getLogger(StyleSetParser.class);

	XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());

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
		
		@Override
		public String toString () {
			StringBuilder sb = new StringBuilder ("DropShadow: h = ");
			sb.append (Integer.toString(h));
			sb.append ("    v = ");
			sb.append (Integer.toString(v));
			sb.append ("    blur = ");
			sb.append (Integer.toString(blur));
			return sb.toString();
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
			e.printStackTrace();
			if (e instanceof ClientException)
				throw (ClientException) e;
			throw new ClientException (e);
		}
	}
	
	private void parseStyles (List<Element> styles, StyleSet ss) throws ClientException {
		for (Element styleElem : styles) {
			// Each "style" element will have a single child, which is named
			// for one of the style types.
			String fieldName = styleElem.getAttributeValue("field");
			Element styleChild = styleElem.getChildren().get(0);
			String styleName = styleChild.getName();
			switch (styleName) {
			case "text":
				addTextStyle (styleChild, ss, fieldName);
				break;
			case "image":
				addImageStyle (styleChild, ss, fieldName);
				break;
			case "svgdata":
				addSVGStyle (styleChild, ss, fieldName);
				break;
			case "block":
				addBlockStyle (styleChild, ss, fieldName);
				break;
			case "logo":
				addLogoStyle (styleChild, ss, fieldName);
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
		if (anchor == null)
			logger.warn ("Invalid anchor");
		s.setAnchor(anchor);
		
		Dimension offset = parseDimensions("offset", styleElem);
		s.setOffsetX (offset.getX());
		s.setOffsetY (offset.getY());
		
		Element hCenter = styleElem.getChild("hCenter");
		s.setHCenter (hCenter != null);
	}
	
	private void addTextStyle (Element textElem, StyleSet ss, String fieldName) throws ClientException {
		logger.debug ("addTextStyle");
		TextStyle ts = new TextStyle();
		ts.setFieldName (fieldName);
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
		ts.setItalic (ital != null);
		Element bold = textElem.getChild("bold");
		ts.setBold (bold != null);
		Element size = textElem.getChild("size");
		int ptSize = 12;		// default if somehow missing or bad
		try {
			ptSize = Integer.parseInt(size.getText());
		} catch (Exception e) {
			logger.warn ("Bad point size, defaulting to 12");
		}
		ts.setPointSize(ptSize);

		String color = textElem.getChildText("textcolor");
		if (color != null)
			ts.setColor (color);
		
		String typeface = textElem.getChildText("font");
		if (typeface != null)
			ts.setTypeface(typeface);
		
		DropShadow ds = parseDropShadow ("dropshadow", textElem);
		if (ds != null) {
			ts.setDropShadowH(ds.h);
			ts.setDropShadowV(ds.v);
			ts.setDropShadowBlur(ds.blur);
		}
		String defaultText = textElem.getChildText ("default");
		ts.setDefaultText (defaultText);

		ss.addStyle (ts);
	}

	private void addImageStyle (Element imageElem, StyleSet ss, String fieldName) throws ClientException{
		ImageStyle is = new ImageStyle();
		is.setFieldName (fieldName);
		getCommonElements (imageElem, is);
		is.setImagePath(imageElem.getChildText("imagepath"));
		is.setOpacity (Integer.parseInt(imageElem.getChildText("opacity")));
		Element multiply = imageElem.getChild("multiply");
		is.setMultiply (multiply != null);
		ss.addStyle (is);
	}

	/* This one could be tricky. */
	private void addSVGStyle (Element svgElem, StyleSet ss, String fieldName) throws ClientException {
		logger.debug ("addSVGStyle, fieldName = {}", fieldName);
		SVGStyle svgs = new SVGStyle();
		svgs.setFieldName (fieldName);
		getCommonElements (svgElem, svgs);
		// Are there namespace issues here? Accept either and SVG namespace
		// element or a default namespace element.
		Element svg = svgElem.getChild ("svg", svgNamespace);
		if (svg == null)
			svg = svgElem.getChild ("svg");
		if (svg != null)
			svgs.setSVG(elementToString(svg));
		ss.addStyle (svgs);
	}

	private void addBlockStyle (Element blockElem, StyleSet ss, String fieldName) throws ClientException {
		BlockStyle bs = new BlockStyle();
		bs.setFieldName (fieldName);
		getCommonElements (blockElem, bs);
		String opacityString = blockElem.getChildText("opacity");
		if (opacityString != null) {
			try {
				bs.setOpacity (Integer.parseInt(opacityString));
			} catch (Exception e) {}
		}
		String pal = blockElem.getChildText ("palette");
		int palidx = paletteSelToIndex (pal);
		if (palidx < 0) {
			String color = blockElem.getChildText ("blockcolor");
			logger.debug ("Color {}", color);
			if (color != null)
				bs.setColor (color);
		}
		else {
			bs.setPaletteSelection (palidx);
		}
		Element multiply = blockElem.getChild("multiply");
		logger.debug ("multiply for block: {}", multiply != null);
		bs.setMultiply (multiply != null);
		DropShadow ds = parseDropShadow ("dropshadow", blockElem);
		if (ds != null) {
			bs.setDropShadowH(ds.h);
			bs.setDropShadowV(ds.v);
			bs.setDropShadowBlur(ds.blur);
		}
		ss.addStyle (bs);
	}

	private void addLogoStyle (Element logoElem, StyleSet ss, String fieldName) throws ClientException {
		LogoStyle ls = new LogoStyle();
		ls.setFieldName (fieldName);
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
			int h = 0;
			try {
				h = Integer.parseInt(hstr);
			} catch (Exception e) {}
			int v = 0;
			try {
				v = Integer.parseInt(vstr);
			} catch (Exception e) {}
				
			int blur = 0;
			try {
				blur = Integer.parseInt(bstr);
			} catch (Exception e) {}
			return new DropShadow (h, v, blur);
		} catch (Exception e) {
			throw new ClientException (e);
		}

	}
	
	/* Convert a palette selector to a number. We use 1-based indexing.
	 * -1 means it's customcolor or something invalid. */
	private int paletteSelToIndex (String sel) {
		if (sel == null)
			return -1;
		switch (sel) {
		case "paletteone":
			return 1;
		case "palettetwo":
			return 2;
		case "palettethree":
			return 3;
		case "palettefour":
			return 4;
		default:
			return -1;
		}
	}
	
	/* Convert an svg (or other) element to a compact formatted string. */
	private String elementToString (Element elem) {
		return outputter.outputString (elem);
	}
}
