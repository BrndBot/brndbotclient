package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.style.ButtonStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.Style.StyleType;

public class ButtonField extends ModelField {
	
	private ButtonStyle style;

	public ButtonField(String name) {
		super(name, StyleType.BUTTON);
	}
	
	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */	public ButtonField (ButtonField modelf) {
		 super (modelf);
		 style = modelf.style;
	 }
	 
	public ButtonStyle getStyle () {
		return style;
	}
	 
	public void setStyle(Style s) {
		style = (ButtonStyle) s;
	}
		

	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
		// TODO add button content
	}
}
