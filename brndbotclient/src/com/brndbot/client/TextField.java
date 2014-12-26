package com.brndbot.client;

/** A TextField is a field of a promotion with specified style
 *  and text content.
 */
public class TextField extends ModelField {

	private String text;
	private TextStyle style;
	
	public TextField(String name) {
		super(name, StyleType.TEXT);
	}
	
	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public TextField (TextField modelf) {
		super (modelf.name, StyleType.TEXT);
		text = modelf.text;
		style = modelf.style;
	}

	public String getText () {
		return text;
	}
	
	public void setText (String t) {
		text = t;
	}
	
	public TextStyle getStyle () {
		return style;
	}
	
	public void setStyle(TextStyle s) {
		style = s;
	}
}
