package com.brndbot.client;

public class ImageField extends ModelField {

	private String imagePath;
	private ImageStyle style;
	
	public ImageField(String name) {
		super(name, StyleType.IMAGE);
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
