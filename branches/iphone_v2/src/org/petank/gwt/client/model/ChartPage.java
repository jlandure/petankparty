package org.petank.gwt.client.model;

import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

public class ChartPage implements IPage {

	private String imgUrl;
	private String name;

	private ChartPage() {

	}

	public static ChartPage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);
		ChartPage result = new ChartPage();
		Element topGroup = messageDom.getDocumentElement();
		result.imgUrl = topGroup.getAttribute("src");
		result.name = topGroup.getAttribute("alt");
		return result;

	}

	@Override
	public String getBackButtonText() {
		return name.substring(6, 9);
	}

	@Override
	public String getTitle() {
		return name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getName() {
		return name;
	}
	
}
