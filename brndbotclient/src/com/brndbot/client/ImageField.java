package com.brndbot.client;

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
		super (modelf.name, StyleType.IMAGE);
		imagePath = modelf.imagePath;
		style = modelf.style;
	}
	
	public String getImagePath () {
		return imagePath;
	}
	
	public void setImagePath (String t) {
		imagePath = t;
	}
	
	public ImageStyle getStyle () {
		return style;
	}
	
	public void setStyle(ImageStyle s) {
		style = s;
	}
}
