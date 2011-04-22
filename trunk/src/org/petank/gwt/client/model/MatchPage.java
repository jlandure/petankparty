package org.petank.gwt.client.model;

import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class MatchPage implements IPage {

	private ArrayList<Match> mMatchs = new ArrayList<Match>();
	private String date;
	private String mGroupName;

	private MatchPage() {

	}

	public static MatchPage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);
		MatchPage result = new MatchPage();
		Element topGroup = messageDom.getDocumentElement();
		result.mGroupName = topGroup.getAttribute("group");

		//result.date = topGroup.getAttribute("date");
		NodeList matchList = topGroup.getChildNodes();
		for (int i = 0; i < matchList.getLength(); i++) {
			Node node = matchList.item(i);
			if (node.getNodeName().equals("player")) {
				Element teamElement = (Element) node;

				result.mMatchs.add(
						result.new Match(
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

	public Iterable<Match> getTeams() {
		return mMatchs;
	}

	public class Match {

		private String name;
		private String classement;
		private String score;
		private String uri;

		public Match(String classement, String name, String score, String uri) {
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
