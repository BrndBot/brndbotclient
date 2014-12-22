package com.brndbot.client;

/* A Style defines the presentation of a field. Each kind of field 
 * is associated with a subclass of Style. */
public abstract class Style {

	public enum Anchor {
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}

	
	private int width;
	private int height;
	private String model;
	private Anchor anchor;
	private int offsetX;
	private int offsetY;
	
	/* Some styles but not all support drop shadow. We make it available to all
	 * just because it's easier.
	 */
	private int dropShadowH;
	private int dropShadowV;
	private int dropShadowBlur;
	
	/* Likewise for opacity and multiply. */
	private int opacity;
	private boolean multiply;

	
	/** Returns the name of the model field associated with this style.
	 *  Will be null if there is no model associated with the style. 
	 */
	public String getModel () {
		return model;
	}
	
	public void setModel(String m) {
		model = m;
	}
	
	public int getWidth () {
		return width;
	}
	
	public void setWidth (int w) {
		width = w;
	}
	
	public int getHeight () {
		return height;
	}
	
	public void setHeight (int h) {
		height = h;
	}
	
	public Anchor getAnchor () {
		return anchor;
	}
	
	public void setAnchor (Anchor a) {
		anchor = a;
	}
	
	public int getOffsetX () {
		return offsetX;
	}
	
	public void setOffsetX (int x) {
		offsetX = x;
	}

	public int getOffsetY () {
		return offsetY;
	}
	
	public void setOffsetY (int y) {
		offsetY = y;
	}

	/** Drop shadow isn't defined for all styles, but we put it here since
	 *  this isn't public code and it's easier. 
	 */
	public int getDropShadowH () {
		return dropShadowH;
	}
	
	public void setDropShadowH (int h) {
		dropShadowH = h;
	}

	public int getDropShadowV () {
		return dropShadowV;
	}
	
	public void setDropShadowV (int v) {
		dropShadowV = v;
	}

	public int getDropShadowBlur () {
		return dropShadowBlur;
	}

	public void setDropShadowBlur (int b) {
		dropShadowBlur = b;
	}

	
	/** Opacity isn't defined for all styles, but we put it here since
	 *  this isn't public code and it's easier. 
	 */	public int getOpacity () {
		return opacity;
	}
	
	public void setOpacity (int op) {
		opacity = op;
	}
	
	/** Multiply isn't defined for all styles, but we put it here since
	 *  this isn't public code and it's easier. 
	 */	public boolean getMultiply () {
		return multiply;
	}
	
	public void setMultiply (boolean m) {
		multiply = m;
	}
}
