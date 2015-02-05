package com.brndbot.client.style;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* A Style defines the presentation of a field. Each kind of field 
 * is associated with a subclass of Style. */
public abstract class Style implements Serializable {

	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(Style.class);

	public enum Anchor {
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT;
		
		public String toString () {
			switch (this) {
			case TOP_LEFT:
				return "TL";
			case TOP_RIGHT:
				return "TR";
			case BOTTOM_LEFT:
				return "BL";
			case BOTTOM_RIGHT:
				return "BR";
			}
			return null;		// TODO
		}
	}

	public enum StyleType {
		TEXT ("text"),
		IMAGE ("image"),
		SVG ("svg"),
		LOGO ("logo"),
		BLOCK ("block"),
		BUTTON ("button");
		
		private String stringRep;
		
		private StyleType (String s) {
			stringRep = s;
		}
		
		public String toString () {
			return stringRep;
		}
	}
	
	private String fieldName;	// the field the style affects, if any
	
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
	
	/* Likewise for opacity and multiply. Opacity is stored as an integer
	 * from 0 to 100. */
	private int opacity;
	private boolean multiply;

	public Style () {
		// Nothing to do; just needs to be explicit to be a super constructor.
	}
	
	
	/** Copy constructor. This needs to be invoked as a super() constructor
	 *  from subclasses. */
	public Style (Style s) {
		width = s.width;
		height = s.height;
		model = s.model;
		anchor = s.anchor;
		offsetX = s.offsetX;
		offsetY = s.offsetY;
		dropShadowH = s.dropShadowH;
		dropShadowV = s.dropShadowV;
		dropShadowBlur = s.dropShadowBlur;
		opacity = s.opacity;
		multiply = s.multiply;
	}
	
	/** Convert this Style to a JSON object. Override this to do anything. */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject ();
		return val;				// TODO stub
	}
	
	/** Returns the kind of Style this is */
	public abstract StyleType getStyleType ();
	
	/** Returns the name of the model field associated with this style.
	 *  Will be null if there is no model associated with the style. 
	 */
	public String getModel () {
		return model;
	}
	
	public void setModel(String m) {
		model = m;
	}
	
	/** Returns the field name associated with the style. May be null. */
	public String getFieldName () {
		return fieldName;
	}
	
	public void setFieldName (String s) {
		fieldName = s;
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
		//logger.debug ("getAnchor returning {}", (anchor == null? "null" : anchor.toString ()));
		return anchor;
	}
	
	public void setAnchor (Anchor a) {
		logger.debug ("setAnchor {}", a.toString());
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
	 *  this isn't public code and it's easier. The value will range
	 *  from 0 to 100.
	 */	
	public int getOpacity () {
		return opacity;
	}
	
	/** Set the opacity as a number from 0 to 100. Not applicable
	 *  to all subclasses.
	 */
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
	
	/** Fill out standard fields in a JSON object */
	public void putStandardJSONFields (JSONObject obj) throws JSONException {
		if (fieldName != null)
			obj.put ("fieldName", fieldName);
		obj.put ("styleType", getStyleType().toString ());
		obj.put ("model", model);
		obj.put ("width", width);
		obj.put ("height", height);
		obj.put ("offsetX", offsetX);
		obj.put ("offsetY", offsetY);
		obj.put ("anchor", anchor.toString());
	}
}
