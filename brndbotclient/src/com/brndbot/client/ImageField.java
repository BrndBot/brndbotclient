package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.style.ImageStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.Style.StyleType;

public class ImageField extends ModelField {

	private String imagePath;
	private ImageStyle style;
	
	public ImageField(String name) {
		super(name, StyleType.IMAGE);
	}

	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public ImageField (ImageField modelf) {
		super (modelf);
		imagePath = modelf.imagePath;
		style = modelf.style;
	}
	
	/** Returns the field's image path if not null; otherwise
	 *  returns the style's image path. */
	public String getImagePath () {
		if (imagePath == null)
			return style.getImagePath();
		return imagePath;
	}
	
	public void setImagePath (String t) {
		imagePath = t;
	}
	
	public ImageStyle getStyle () {
		return style;
	}
	
	public void setStyle(Style s) {
		style = (ImageStyle) s;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
		// TODO add image content
	}
}
