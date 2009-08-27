package org.petank.gwt.client.model;

import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

public class PlayerPage implements IPage {

	private ArrayList<NamedValue> values = new ArrayList<NamedValue>();
	private ListePage chart;
	private String mPlayerName;

	private PlayerPage() {

	}

	public static PlayerPage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);

		PlayerPage result = new PlayerPage();
		Element topGroup = messageDom.getDocumentElement();
		result.mPlayerName = topGroup.getAttribute("name");

		Element chartElement = (Element) topGroup.getElementsByTagName("page").item(0);
		result.chart = new ListePage(chartElement.getAttribute("name"), chartElement.getAttribute("uri"));
		
		result.values.add(new NamedValue("Points", topGroup.getAttribute("points")));
		result.values.add(new NamedValue("Jou\u00E9s", topGroup.getAttribute("joues")));
		result.values.add(new NamedValue("Gagn\u00E9s", topGroup.getAttribute("gagnes")));
		result.values.add(new NamedValue("Perdus", topGroup.getAttribute("perdus")));
		result.values.add(new NamedValue("Points par match", topGroup.getAttribute("pointsMoyen")));
		
		
		return result;

	}

	@Override
	public String getBackButtonText() {
		return "Classement";
	}

	@Override
	public String getTitle() {
		return mPlayerName;
	}

	public Iterable<NamedValue> getValues() {
		return values;
	}

	public ListePage getChart() {
		return chart;
	}
	
}
