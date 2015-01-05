package com.brndbot.client;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class LogoFieldTest {

	@Test
	public void testConstruction() {
		LogoField imf = new LogoField ("name");
		assertEquals ("name", imf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		LogoField tf = new LogoField ("name");
		LogoStyle style = new LogoStyle ();
		tf.setStyle (style);
		LogoField cloneField = new LogoField (tf);
		assertEquals ("name", cloneField.getName());
		LogoStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
	}

	@Test
	public void testJSON () throws Exception {
		LogoField tf = new LogoField ("name1");
		LogoStyle style = new LogoStyle ();
		assertNotNull (style);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("logo", json.get("styleType"));
	}

}
