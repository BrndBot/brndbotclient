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
		BlockField tf = new BlockField ("name");
		BlockStyle style = new BlockStyle ();
		tf.setStyle (style);
		BlockField cloneField = new BlockField (tf);
		assertEquals ("name", cloneField.getName());
		BlockStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
	}
	
	@Test
	public void testJSON () throws Exception {
		BlockField tf = new BlockField ("name1");
		BlockStyle style = new BlockStyle ();
		assertNotNull (style);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("block", json.get("styleType"));
	}

}
