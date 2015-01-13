package com.brndbot.client;

import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.style.BlockStyle;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.Style.StyleType;

public class BlockField extends ModelField {

	private String color;
	private BlockStyle style;
	
	public BlockField(String name) {
		super(name, StyleType.BLOCK);
	}
	
	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public BlockField (BlockField modelf) {
		super (modelf);
		style = new BlockStyle(modelf.style);
	}

	public BlockStyle getStyle () {
		return style;
	}
	
	public void setStyle (Style s) {
		style = (BlockStyle) s;
	}
	
	public String getColor () {
		return color;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException {
		return super.toJSON();
	}
}
