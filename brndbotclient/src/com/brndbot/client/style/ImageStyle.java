package com.brndbot.client.style;

public class ImageStyle extends Style {

	private static final long serialVersionUID = 1L;
	private String imagePath;
	
	public ImageStyle () {
	}
	
	
	public ImageStyle (ImageStyle s) {
		super (s);
		imagePath = s.imagePath;
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.IMAGE;
	}

	
	/** An ImageStyle may hold a default image path */
	public String getImagePath () {
		return imagePath;
	}
	
	public void setImagePath (String p) {
		imagePath = p;
	}


}
