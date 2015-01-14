package com.brndbot.client;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import com.brndbot.client.style.TextStyle;

public class TextFieldTest {

	@Test
	public void testConstruction() {
		TextField tf = new TextField ("name");
		assertEquals ("name", tf.getName());
	}
	
	@Test
	public void testCopyConstructor () {
		TextField tf = new TextField ("name");
		tf.setText ("Some text");
		TextStyle style = new TextStyle ();
		style.setPointSize (24);
		tf.setStyle (style);
		TextField cloneField = new TextField (tf);
		assertEquals ("name", cloneField.getName());
		TextStyle cloneStyle = cloneField.getStyle();
		assertNotNull (cloneStyle);
		assertEquals (cloneStyle.getPointSize(), 24);
		assertEquals ("Some text", cloneField.getText());
	}
	
	@Test
	public void testCopyOfStylelessField () {
		TextField bf = new TextField ("name");
		TextField cloneField = new TextField (bf);
		assertEquals ("name", cloneField.getName ());
		assertNull (cloneField.getStyle());
	}
	
	@Test
	public void testJSON () throws Exception {
		TextField tf = new TextField ("name1");
		tf.setText ("More text");
		TextStyle style = new TextStyle ();
		style.setPointSize (20);
		JSONObject json = tf.toJSON();
		assertEquals ("name1", json.get("name"));
		assertEquals ("text", json.get("styleType"));
	}

}
