package com.brndbot.client;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import com.brndbot.client.style.ImageStyle;

public class ImageFieldTest {

	@Test
	public void testConstruction() {
		ImageField imf = new ImageField ("name");
		assertEquals ("name", imf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		ImageField tf = new ImageField ("name");
		tf.setImagePath ("example.png");
		ImageStyle style = new ImageStyle ();
		style.setImagePath ("default.png");
		tf.setStyle (style);
		ImageField cloneField = new ImageField (tf);
		assertEquals ("name", cloneField.getName());
		ImageStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
		assertEquals (cloneStyle.getImagePath(), "default.png");
		assertEquals ("example.png", cloneField.getImagePath());
	}

	@Test
	public void testJSON () throws Exception {
		ImageField tf = new ImageField ("name1");
		ImageStyle style = new ImageStyle ();
		assertNotNull (style);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("image", json.get("styleType"));
	}
}
