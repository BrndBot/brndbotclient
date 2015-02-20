package com.brndbot.client.style;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageStyle extends Style {

	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(ImageStyle.class);
	
	private String imagePath;		// TODO should probably eliminate
	private int imageID;
	
	public ImageStyle () {
		imageID = 1;		// **** TODO placeholder
	}
	
	
	public ImageStyle (ImageStyle s) {
		super (s);
		imageID = 1;		// **** TODO placeholder
		imagePath = s.imagePath;
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.IMAGE;
	}

	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		logger.debug ("toJSON");
		JSONObject val = new JSONObject ();
		putStandardJSONFields (val);
		val.put ("imagePath", imagePath);
		val.put ("imageID", imageID);
		logger.debug (val.toString());
		return val;				
	}
	
	/** An ImageStyle may hold a default image path */
	public String getImagePath () {
		return imagePath;
	}
	
	public void setImagePath (String p) {
		imagePath = p;
	}


}
