package com.brndbot.client.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.brndbot.client.Model;
import com.brndbot.client.ModelField;
import com.brndbot.client.ModelField.StyleType;

/** This test relies on the files testfiles/testmodel1.xml
 *  and testfiles/testmodel2.xml */
public class ModelParserTest {

	@Test
	public void testModelParserMin() throws Exception {
		File testFile = new File ("testfiles/testmodel1.xml");
		ModelParser mparser = new ModelParser(testFile);
		Model mdl = mparser.parse();
		assertNotNull(mdl);
		assertNotNull(mdl.getDescription());
		assertEquals(mdl.getOrganization(), "MiskatonicU");
		assertEquals(mdl.getCategory(), "class");
	}

	@Test
	public void testModelFields() throws Exception {
		File testFile = new File ("testfiles/testmodel2.xml");
		ModelParser mparser = new ModelParser(testFile);
		Model mdl = mparser.parse();
		List<ModelField> fields = mdl.getFields();
		assertEquals(fields.size(), 5);
		ModelField field = fields.get(0);
		assertEquals (field.getName(), "textField");
		assertEquals (field.getStyleType(), StyleType.TEXT);

		field = fields.get(1);
		assertEquals (field.getName(), "svgField");
		assertEquals (field.getStyleType(), StyleType.SVG);
		
		field = fields.get(2);
		assertEquals (field.getName(), "logoField");
		assertEquals (field.getStyleType(), StyleType.LOGO);

		field = fields.get(3);
		assertEquals (field.getName(), "imageField");
		assertEquals (field.getStyleType(), StyleType.IMAGE);

		field = fields.get(4);
		assertEquals (field.getName(), "blockField");
		assertEquals (field.getStyleType(), StyleType.BLOCK);
}
}
