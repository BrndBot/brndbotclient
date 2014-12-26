package com.brndbot.client;

/** A PromotionPrototype defines the fields of a promotion using a
 *  Model, and assigns a default styleset and content to them.
 *  
 *  Should there be a subclass called Promotion? Maybe it should be in
 *  bbproject rather than here. 
 */
public class PromotionPrototype {

	protected String name;
	protected Model model;
	protected StyleSet styleSet;
	
	public PromotionPrototype (String n, Model m, StyleSet ss) {
		name = n;
		model = m;
		styleSet = ss;
	}
	
	public String getName () {
		return name;
	}
	
	public Model getModel () {
		return model;
	}
	
	public StyleSet getStyleSet () {
		return styleSet;
	}
	
	/** We'll need to convert a promotion prototype to JSON in order
	 *  to feed it to JavaScript. */
	public String getJson () {
		return null;		// TODO stub
	}
}
