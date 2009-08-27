package org.petank.gwt.client.model;

import java.util.ArrayList;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.ClassementPage.User;

import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class MainPage implements IPage {

	public static final String TITLE = "Petank Party";
	private ListePage group;
	
	private MainPage() {

	}

	public static MainPage convert(String messageXml) throws DOMException {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);

		MainPage result = new MainPage();

		Element group = (Element) messageDom.getElementsByTagName("group").item(0);

		result.group = new ListePage(group.getAttribute("name"), Controller.HOST_URL + Controller.FIRST_PAGE);
		
		return result;

	}

	public ListePage getGroup() {
		return group;
	}

	@Override
	public String getBackButtonText() {
		return null;
	}

	@Override
	public String getTitle() {
		return MainPage.TITLE;
	}

}
