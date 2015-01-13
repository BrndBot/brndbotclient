package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.style.Style;
import com.brndbot.client.style.TextStyle;
import com.brndbot.client.style.Style.StyleType;

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
		super (modelf);
		text = modelf.text;
		style = new TextStyle(modelf.style);
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
	
	public void setStyle(Style s) {
		style = (TextStyle) s;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject json = super.toJSON();
		json.put ("text", text);
		return json;
	}
}
