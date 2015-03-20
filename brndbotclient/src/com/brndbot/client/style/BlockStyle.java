package com.brndbot.client.style;

import org.json.JSONException;
import org.json.JSONObject;

public class BlockStyle extends Style {


	private static final long serialVersionUID = 1L;

	public BlockStyle () {
	}
	
	public BlockStyle (BlockStyle s) {
		super (s);
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.BLOCK;
	}
	
	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject ();
		putStandardJSONFields (val);
		val.put ("opacity", getOpacity());
		val.put ("multiply", getMultiply());
		return val;				
	}

	

}
