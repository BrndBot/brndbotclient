package com.brndbot.client;

/** A PromotionPrototype defines the fields of a promotion using a
 *  Model, and assigns a default styleset and content to them.
 *  
 *  Should there be a subclass called Promotion? Maybe it should be in
 *  bbproject rather than here. 
 */
public class PromotionPrototype {

	protected Model model;
	protected StyleSet styleSet;
	
	public PromotionPrototype (Model m, StyleSet ss) {
		model = m;
		styleSet = ss;
	}
	
	public Model getModel () {
		return model;
	}
	
	public StyleSet getStyleSet () {
		return styleSet;
	}
}
