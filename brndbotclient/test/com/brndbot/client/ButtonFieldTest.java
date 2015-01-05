package com.brndbot.client;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class ButtonFieldTest {

	@Test
	public void testConstruction() {
		ButtonField imf = new ButtonField ("name");
		assertEquals ("name", imf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		ButtonField tf = new ButtonField ("name");
		ButtonStyle style = new ButtonStyle ();
		tf.setStyle (style);
		ButtonField cloneField = new ButtonField (tf);
		assertEquals ("name", cloneField.getName());
		ButtonStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
	}

	@Test
	public void testJSON () throws Exception {
		ButtonField tf = new ButtonField ("name1");
		ButtonStyle style = new ButtonStyle ();
		assertNotNull (style);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("button", json.get("styleType"));
	}

}
