package com.brndbot.client;

public class BlockStyle extends Style {


	private static final long serialVersionUID = 1L;

	private String color;
	
	public String getColor () {
		return color;
	}
	
	/** We don't do any checking when setting the color. It should
	 *  be a valid CSS color expression. */
	public void setColor(String c) {
		color = c;
	}
}
