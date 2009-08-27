package org.petank.gwt.client.model;

import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class GroupPage implements IPage {

	private ArrayList<ListePage> mGroups = new ArrayList<ListePage>();
	private String mName;

	private GroupPage() {

	}

	public static GroupPage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);

		GroupPage result = new GroupPage();
		
		Element topGroup = (Element) messageDom.getDocumentElement().getElementsByTagName("group").item(0);
		result.mName = topGroup.getAttribute("name");

		NodeList groupList = topGroup.getChildNodes();
		for (int i = 0; i < groupList.getLength(); i++) {
			Node node = groupList.item(i);
			if (node.getNodeName().equals("page")) {
				Element groupElement = (Element) node;
				result.mGroups.add(new ListePage(groupElement
						.getAttribute("name"), groupElement
						.getAttribute("uri")));
			}
		}

		return result;

	}

	@Override
	public String getBackButtonText() {
		return MainPage.TITLE;
	}

	@Override
	public String getTitle() {
		return mName;
	}

	public Iterable<ListePage> getListePage() {
		return mGroups;
	}

}
