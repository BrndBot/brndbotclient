package com.brndbot.client.style;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogoStyle extends Style {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(LogoStyle.class);
	
	public LogoStyle () {
		logger.debug ("LogoStyle no-arg constructor");
	}
	
	public LogoStyle (LogoStyle s) {
		super (s);
		logger.debug ("LogoStyle copy constructor");
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.LOGO;
	}

	
	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		logger.debug ("LogoStyle toJSON");
		JSONObject val = new JSONObject ();
		// Only the standard style fields are necessary
		putStandardJSONFields (val);
		logger.debug (val.toString());
		return val;				
	}
}
