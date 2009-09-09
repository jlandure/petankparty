package org.petank.gwt.client.model;

import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class MatchPage implements IPage {

	private ArrayList<User> mMatchs = new ArrayList<User>();
	private String date;
	private String mGroupName;

	private MatchPage() {

	}

	public static MatchPage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);

		MatchPage result = new MatchPage();
		Element topGroup = messageDom.getDocumentElement();
		result.date = topGroup.getAttribute("date");
		result.mGroupName = topGroup.getAttribute("group");

		NodeList teamList = topGroup.getChildNodes();
		for (int i = 0; i < teamList.getLength(); i++) {
			Node node = teamList.item(i);
			if (node.getNodeName().equals("player")) {
				Element teamElement = (Element) node;

				result.mMatchs.add(
						result.new User(
								teamElement.getAttribute("place"),
								teamElement.getAttribute("name"),
								teamElement.getAttribute("score"),
								teamElement.getAttribute("uri")
								));
			}
		}
		return result;

	}

	@Override
	public String getBackButtonText() {
		return mGroupName;
	}

	@Override
	public String getTitle() {
		return "Classement";
	}

	public Iterable<User> getTeams() {
		return mMatchs;
	}

	public class User {

		private String name;
		private String classement;
		private String score;
		private String uri;

		public User(String classement, String name, String score, String uri) {
			this.name = name;
			this.classement = classement;
			this.score = score;
			this.uri = uri;
		}

		public String getName() {
			return name;
		}

		public String getClassement() {
			return classement;
		}
		
		public String getScore() {
			return score;
		}
		
		public String getUri() {
			return uri;
		}

	}
}
