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
		JUSTIFIED;
		
		public String toString () {
			switch (this) {
			case LEFT:
			default:
				return "left";
			case RIGHT:
				return "right";
			case CENTER:
				return "center";
			case JUSTIFIED:
				return "justified";
			}
		}
	}
	
	private String typeface;
	private int pointSize;	// Do we want fractional point sizes?
	//private int pointSizeTenths;	// If so, this is probably better than float
	private boolean italic;
	private boolean bold;
	private boolean dropShadow;
	private Alignment alignment;
	private String color;
	private String text;
	private String defaultText;
	
	public TextStyle () {
		
	}
	
	/** Copy constructor. */
	public TextStyle (TextStyle s) {
		super (s);
		italic = s.italic;
		bold = s.bold;
		dropShadow = s.dropShadow;
		alignment = s.alignment;
		color = s.color;
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
		if (defaultText != null)
			val.put ("defaultText", defaultText);
		val.put ("italic", italic);
		val.put ("bold", bold);
		val.put ("typeface", typeface);
		val.put ("pointSize", pointSize);
		val.put ("color", color);
		val.put ("alignment", alignment.toString());
		return val;				
	}
	
	public String getTypeface () {
		return typeface;
	}
	
	public void setTypeface (String f) {
		typeface = f;
	}
	
	public String getDefaultText () {
		return defaultText;
	}
	
	public void setDefaultText (String s) {
		defaultText = s;
	}
	
	
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
	
	public String getColor () {
		return color;
	}
	
	public void setColor (String c) {
		color = c;
	}
	
	public Alignment getAlignment () {
		return alignment;
	}
	
	public void setAlignment (Alignment algn) {
		alignment = algn;
	}


}
