package com.brndbot.client;

public class BlockField extends ModelField {

	private BlockStyle style;
	
	public BlockField(String name) {
		super(name, StyleType.BLOCK);
	}
	
	/** Copy constructor. This is used to generate a Promotion's field
	 *  from a Model's field. 
	 */
	public BlockField (BlockField modelf) {
		super (modelf.name, StyleType.BLOCK);
		style = modelf.style;
	}

	public BlockStyle getStyle () {
		return style;
	}
	
	public void setStyle (BlockStyle s) {
		style = s;
	}
}
