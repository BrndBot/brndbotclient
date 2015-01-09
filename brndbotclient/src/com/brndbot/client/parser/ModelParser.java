/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.client.parser;

import java.io.File;
import java.util.List;

import com.brndbot.client.ClientException;
import com.brndbot.client.Model;
import com.brndbot.client.style.Style.StyleType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *  A ModelParser turns an XML model file into a Model object.
 *  This implementation assumes that there is no namespace declaration in
 *  the XML file.
 */
public class ModelParser {

	File modelFile;
	
	public ModelParser(File file) {
		modelFile = file;
	}
	
	public Model parse() throws ClientException {
		SAXBuilder builder = new SAXBuilder ();
		try {
			Document doc = builder.build (modelFile);
			Element root = doc.getRootElement();
			if (!"model".equals (root.getName())) {
				throw new ClientException ("Not a valid model file");
			}
			String name = root.getAttributeValue ("name");
			if (name == null) {
				throw new ClientException ("model element has no name attribute");
			}
			String desc = root.getChildTextTrim("description");
			if (desc == null || desc.length() == 0) {
				throw new ClientException ("model element has no description");
			}
			Model mdl = new Model(name, desc);
			String category = root.getChildTextTrim("category");
			mdl.setCategory (category);
			String org = root.getChildTextTrim("org");
			mdl.setOrganization(org);
			
			// Now to get the fields 
			List<Element> fields = root.getChildren("field");
			parseFields (fields, mdl);
			return mdl;
		} catch (Exception e) {
			throw new ClientException (e);
		}
	}
	
	/* Parse each of the fields */
	private void parseFields (List<Element> fields, Model mdl) throws ClientException {
		for (Element field : fields) {
			String name = field.getAttributeValue ("name");
			if (name == null) {
				throw new ClientException ("field element has no name attribute");
			}
			String typeText = field.getChildTextTrim ("type");
			StyleType type = null;
			switch (typeText) {
			case "text":
				type = StyleType.TEXT;
				break;
			case "image":
				type = StyleType.IMAGE;
				break;
			case "svg":
				type = StyleType.SVG;
				break;
			case "block":
				type = StyleType.BLOCK;
				break;
			case "logo":
				type = StyleType.LOGO;
				break;
			}
			mdl.addField(name, type);
		}
	}
}
