package com.brndbot.client;

public class ButtonStyle extends Style {

	private String text;
	private String url;

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
