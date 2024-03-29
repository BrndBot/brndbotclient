/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client.style;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* A StyleSet is an ordered collection of Styles, plus some information
 * defining the appearance of the promotion as a whole.
 */
public class StyleSet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(StyleSet.class);

	private String name;
	private String model;
	private String organization;
	private String brand;
	private Set<String> channels;
	private String promotion;
	private int width;
	private int height;
	private List<Style> styles;
	
	public StyleSet (String name) {
		this.name = name;
		logger.debug ("Constructing styleset {}", name);
		styles = new ArrayList<>();
		logger.debug ("StyleSet constructor for {} creating channels", name);
		channels = new HashSet<>();
	}
	
	/** Copy constructor. Copies the individual styles, so they can
	 *  be modified. */
	public StyleSet (StyleSet orig) {
		name = orig.name;
		model = orig.model;
		organization = orig.organization;
		brand = orig.brand;
		logger.debug ("StyleSet copy constructor for {} creating channels", name);
		channels = new HashSet<> (orig.channels);
		promotion = orig.promotion;
		width = orig.width;
		height = orig.height;
		styles = new ArrayList<>(orig.styles.size());
		for (Style styl : orig.styles) {
			styles.add (copyStyle (styl));
		}
	}
	
	/** Check if the StyleSet has enough information to be usable. */
	public boolean isValid () {
		if (!(name != null &&
				organization != null &&
				width > 0 &&
				height > 0 &&
				styles != null &&
				styles.size() > 0))
			return false;				// lacks minimal fields
		
		// Make sure there's at least one valid style.
		int validStyles = 0;
		for (Style s : styles) {
			if (s.isValid ())
				++validStyles;
		}
		return (validStyles > 0);
	}
	
	
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		val.put ("name", name);
		val.put ("model", model);
		val.put ("organization", organization);
		val.put ("brand", brand);
		// ****TEMPORARY If no channel, set a default
		if (channels.isEmpty())
			val.put ("channel", "Twitter");		// TODO remove when styles have real channel elements
		else {
			for (String channel : channels)
				val.put ("channel", channel);
		}
		val.put ("width", width);
		val.put ("height", height);
		JSONArray jStyleArray = new JSONArray ();
		for (Style style : styles) {
			JSONObject jStyle = style.toJSON();
			jStyleArray.put (jStyle);
		}
		val.put ("styles", jStyleArray);
		return val;		
	}
	
	public String getName () {
		return name;
	}
	
	public String getModel () {
		return model;
	}
	
	public void setModel (String mdl) {
		model = mdl;
	}	
	
	public String getOrganization () {
		return organization;
	}
	
	public void setOrganization (String org) {
		organization = org;
	}
	
	public String getBrand () {
		return brand;
	}
	
	public void setBrand (String brnd) {
		brand = brnd;
	}
	
	public Set<String> getChannels () {
		return channels;
	}
	
	public void addChannel (String ch) {
		logger.debug ("Adding channel {}", ch);
		channels.add(ch);
	}
	
	public String getPromotion () {
		return promotion;
	}
	
	public void setPromotion (String promo) {
		promotion = promo;
	}
	
	public int getWidth () {
		return width;
	}
	
	public void setWidth(int w) {
		width = w;
	}

	public int getHeight () {
		return height;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public List<Style> getStyles () {
		return styles;
	}
	
	public void addStyle (Style s) {
		styles.add (s);
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ("StyleSet: ");
		sb.append("name = ");
		sb.append(name);
		sb.append ("  model = ");
		sb.append(model);
		sb.append("  organization = ");
		sb.append(organization);
		sb.append("  brand = ");
		sb.append(brand);
		if (channels != null) {
			sb.append("  no. of channels = ");
			sb.append(channels.size());
		}
		sb.append("  width = ");
		sb.append(Integer.toString(width));
		sb.append("  height = ");
		sb.append(Integer.toString(height));
		
		return sb.toString();
	}
	
	
	/* Copy a Style as part of the copy constructor's action */
	private Style copyStyle (Style s) {
		switch (s.getStyleType()) {
		case TEXT:
			return new TextStyle ((TextStyle) s);
		case IMAGE:
			return new ImageStyle ((ImageStyle) s);
		case SVG:
			return new SVGStyle ((SVGStyle) s);
		case LOGO:
			return new LogoStyle ((LogoStyle) s);
		case BLOCK:
			return new BlockStyle ((BlockStyle) s);
		case BUTTON:
			return new ButtonStyle ((ButtonStyle) s);
		default:
			logger.error ("Unknown style type");
			return null;
		}
	}
	
	
}
