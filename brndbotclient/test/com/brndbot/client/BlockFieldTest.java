package com.brndbot.client;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import com.brndbot.client.style.BlockStyle;

public class BlockFieldTest {

	@Test
	public void testConstruction() {
		BlockField tf = new BlockField ("name");
		assertEquals ("name", tf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		BlockField bf = new BlockField ("name");
		BlockStyle style = new BlockStyle ();
		style.setColor ("#FFDD00");
		bf.setStyle (style);
		BlockField cloneField = new BlockField (bf);
		assertEquals ("name", cloneField.getName());
		BlockStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
		assertEquals ("#FFDD00", cloneStyle.getColor());
		
		//Check that the clone style can be changed without changing original
		cloneStyle.setColor ("#001122");
		assertEquals ("#FFDD00", style.getColor());
	}
	
	@Test
	public void testCopyOfStylelessField () {
		BlockField bf = new BlockField ("name");
		BlockField cloneField = new BlockField (bf);
		assertEquals ("name", cloneField.getName ());
		assertNull (cloneField.getStyle());
	}
	
	@Test
	public void testJSON () throws Exception {
		BlockField tf = new BlockField ("name1");
//		BlockStyle style = new BlockStyle ();
//		assertNotNull (style);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("block", json.get("styleType"));
	}

}
