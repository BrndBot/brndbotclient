package com.brndbot.client;

public class ButtonField extends ModelField {
	
	private ButtonStyle style;

	public ButtonField(String name) {
		super(name, StyleType.BUTTON);
	}
	
	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */	public ButtonField (ButtonField modelf) {
		 super (modelf.name, StyleType.BUTTON);
		 style = modelf.style;
	 }
	 
	 public ButtonStyle getStyle () {
		return style;
	 }
}
