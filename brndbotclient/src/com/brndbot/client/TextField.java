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
