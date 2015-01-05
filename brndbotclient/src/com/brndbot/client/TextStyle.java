package com.brndbot.client;

/* A TextStyle defines the presentation of a text field. */
public class TextStyle extends Style {

	
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
	private int dropShadowXOffset;
	private int dropShadowYOffset;
	private int dropShadowBlur;
	private Alignment alignment;
	
	public String getTypeface () {
		return typeface;
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
	
	public int getDropShadowXOffset () {
		return dropShadowXOffset;
	}

	public int getDropShadowYOffset () {
		return dropShadowYOffset;
	}
	
	public int getDropShadowBlur () {
		return dropShadowBlur;
	}
	
	public Alignment getAlignment () {
		return alignment;
	}
	
	public void setAlignment (Alignment algn) {
		alignment = algn;
	}


}
