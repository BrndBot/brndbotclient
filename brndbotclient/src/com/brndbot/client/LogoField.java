package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

public class LogoField extends ModelField {

	private LogoStyle style;
	
	public LogoField(String name) {
		super(name, StyleType.LOGO);
	}

	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public LogoField (LogoField modelf) {
		super(modelf.name, StyleType.LOGO);
		style = modelf.style;
	}
	
	public LogoStyle getStyle () {
		return style;
	}
	
	public void setStyle(LogoStyle s) {
		style = s;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
		// TODO add logo content
	}
}
