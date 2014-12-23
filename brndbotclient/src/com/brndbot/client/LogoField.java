package com.brndbot.client;

public class LogoField extends ModelField {

	private LogoStyle style;
	
	public LogoField(String name) {
		super(name, StyleType.LOGO);
	}

	public LogoStyle getStyle () {
		return style;
	}
	
	public void setStyle(LogoStyle s) {
		style = s;
	}
}
