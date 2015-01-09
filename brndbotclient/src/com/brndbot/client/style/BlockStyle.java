package com.brndbot.client.style;

public class BlockStyle extends Style {


	private static final long serialVersionUID = 1L;

	private String color;
	private int paletteSelection = -1;
	
	public BlockStyle () {
	}
	
	public BlockStyle (BlockStyle s) {
		super (s);
		color = s.color;
		paletteSelection = s.paletteSelection;
	}
	
	@Override
	public StyleType getStyleType () {
		return StyleType.BLOCK;
	}

	public String getColor () {
		return color;
	}
	
	/** We don't do any checking when setting the color. It should
	 *  be a valid CSS color expression. */
	public void setColor(String c) {
		color = c;
	}
	
	/** Get the 1-based palette selection. Use getColor only if
	 *  this returns null.
	 */
	public int getPaletteSelection () {
		return paletteSelection;
	}
	
	/** Set the 1-based palette selection. Color and palette selection
	 *  shouldn't both be set.
	 */
	public void setPaletteSelection (int n) {
		paletteSelection = n;
	}
}
