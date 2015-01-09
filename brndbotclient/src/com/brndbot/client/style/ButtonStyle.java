package com.brndbot.client.style;

public class ButtonStyle extends Style {

	private static final long serialVersionUID = 1L;

	private String text;
	private String url;

	public ButtonStyle () {
	}
	
	public ButtonStyle (ButtonStyle s) {
		super (s);
		text = s.text;
		url = s.url;
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.BUTTON;
	}

	/** The visible button text */
	public String getText () {
		return text;
	}
	
	public void setText (String t) {
		text = t;
	}
	
	/** The URL the button links to */
	public String getUrl () {
		return url;
	}
	
	public void setUrl (String u) {
		url = u;
	}
}
