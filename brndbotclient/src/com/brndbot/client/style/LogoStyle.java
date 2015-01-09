package com.brndbot.client.style;


public class LogoStyle extends Style {

	private static final long serialVersionUID = 1L;

	public LogoStyle () {
	}
	
	public LogoStyle (LogoStyle s) {
		super (s);
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.LOGO;
	}

}
