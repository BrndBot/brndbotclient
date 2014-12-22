/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client;

import java.util.ArrayList;
import java.util.List;

/* A StyleSet is an ordered collection of Styles, plus some information
 * defining the appearance of the promotion as a whole.
 */
public class StyleSet {
	private String name;
	private String model;
	private String organization;
	private String brand;
	private String promotion;
	private int width;
	private int height;
	private List<Style> styles;
	
	public StyleSet (String name) {
		this.name = name;
		styles = new ArrayList<>();
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
	
	
}
