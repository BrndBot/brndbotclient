package com.brndbot.client.style;

import org.json.JSONException;
import org.json.JSONObject;


public class LogoStyle extends Style {

	private static final long serialVersionUID = 1L;

	public LogoStyle () {
	}
	
	public LogoStyle (LogoStyle s) {
		super (s);
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.LOGO;
	}

	
	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject ();
		// Only the standard style fields are necessary
		putStandardJSONFields (val);
		return val;				
	}
}
