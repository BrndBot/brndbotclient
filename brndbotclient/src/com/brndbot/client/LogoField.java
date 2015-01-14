package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.style.LogoStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.Style.StyleType;

public class LogoField extends ModelField {

	private LogoStyle style;
	
	public LogoField(String name) {
		super(name, StyleType.LOGO);
	}

	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public LogoField (LogoField modelf) {
		super(modelf);
		if (modelf.style != null) {
			style = modelf.style;
		}
	}
	
	public LogoStyle getStyle () {
		return style;
	}
	
	public void setStyle(Style s) {
		style = (LogoStyle) s;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
		// TODO add logo content
	}
}
