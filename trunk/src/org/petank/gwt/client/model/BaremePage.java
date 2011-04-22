package org.petank.gwt.client.model;

import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class BaremePage implements IPage {

	private ArrayList<NamedValue> mBaremes = new ArrayList<NamedValue>();
	private String mName;

	private BaremePage() {

	}

	public static BaremePage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);

		BaremePage result = new BaremePage();
		
		Element topGroup = (Element) messageDom.getElementsByTagName("baremes").item(0);
		result.mName = "Bareme";

		NodeList baremeList = topGroup.getChildNodes();
		String value;
		String name;
		for (int i = 0; i < baremeList.getLength(); i++) {
			Node node = baremeList.item(i);
			if (node.getNodeName().equals("bareme")) {
				Element baremeElement = (Element) node;
				name = baremeElement.getAttribute("minimum") + " - " + baremeElement.getAttribute("maximum");
				value =  result.concatElementForBareme(
						baremeElement.getAttribute("victoireNormale"),
						baremeElement.getAttribute("defaiteNormale"),
						baremeElement.getAttribute("victoireAnormale"),
						baremeElement.getAttribute("defaiteAnormale"));
				result.mBaremes.add(new NamedValue(name,value));
			}
		}

		return result;

	}
	
	public String concatElementForBareme(String victNorm, String defNorm, String victAnorm, String defAnorm) {
		String value = " [" + victNorm + "," + defNorm + "] ["+ victAnorm + "," + defAnorm + "]";
		return value;
	}

	@Override
	public String getBackButtonText() {
		return MainPage.TITLE;
	}

	@Override
	public String getTitle() {
		return mName;
	}

	public Iterable<NamedValue> getNamedValues() {
		return mBaremes;
	}

}
