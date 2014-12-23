package com.brndbot.client;

public class BlockField extends ModelField {

	private BlockStyle style;
	
	public BlockField(String name) {
		super(name, StyleType.BLOCK);
	}

	public BlockStyle getStyle () {
		return style;
	}
	
	public void setStyle (BlockStyle s) {
		style = s;
	}
}
