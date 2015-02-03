package com.brndbot.client.style;

import org.json.JSONException;
import org.json.JSONObject;

/* A TextStyle defines the presentation of a text field. */
public class TextStyle extends Style {

	
	private static final long serialVersionUID = 1L;

	public enum Alignment {
		LEFT,
		RIGHT,
		CENTER,
		JUSTIFIED
	}
	
	private String typeface;
	private int pointSize;	// Do we want fractional point sizes?
	//private int pointSizeTenths;	// If so, this is probably better than float
	private boolean italic;
	private boolean bold;
	private boolean dropShadow;
	private Alignment alignment;
	private String textColor;
	private String text;
	
	public TextStyle () {
		
	}
	
	/** Copy constructor. */
	public TextStyle (TextStyle s) {
		super (s);
		italic = s.italic;
		bold = s.bold;
		dropShadow = s.dropShadow;
		alignment = s.alignment;
		textColor = s.textColor;
		typeface = s.typeface;
		pointSize = s.pointSize;
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.TEXT;
	}
	
	/** Convert this Style to a JSON object */
	@Override
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject ();
		putStandardJSONFields (val);
		val.put ("text", text);
		val.put ("italic", italic);
		val.put ("bold", bold);
		val.put ("typeface", typeface);
		val.put ("pointSize", pointSize);
		val.put ("textColor", textColor);
		return val;				
	}
	
	public String getTypeface () {
		return typeface;
	}
	
	public void setTypeface (String f) {
		typeface = f;
	}
	
	/** TODO have to initialize this somehow. Do I want a big 
	 *  hairy constructor or a bunch of initializers? Latter is less
	 *  error prone, if more verbose. */
	
	/** Return the point size as an integer. If it turns
	 *  out that we want tenths of a point, change this to a double
	 *  and calculate it from pointsize and tenths. 
	 */
	public int getPointSize() {
		return pointSize;
	}
	
	public void setPointSize (int ptsize) {
		pointSize = ptsize;
	}
	
	public boolean isItalic () {
		return italic;
	}
	
	public void setItalic (boolean it) {
		italic = it;
	}
	
	public boolean isBold () {
		return bold;
	}
	
	public void setBold (boolean b) {
		bold = b;
	}
	
	public boolean isDropShadow () {
		return dropShadow;
	}
	
	public void setDropShadow (boolean b) {
		dropShadow = b;
	}
	
	public String getTextColor () {
		return textColor;
	}
	
	public void setTextColor (String c) {
		textColor = c;
	}
	
	public Alignment getAlignment () {
		return alignment;
	}
	
	public void setAlignment (Alignment algn) {
		alignment = algn;
	}


}
