package org.petank.gwt.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class AddMatchPage implements IPage {

	private static final String TITLE = "Ajout d'un match / Step ";
	private static final String NEXT = "Next";
	private static final String FINISH = "Envoyer";
	
	private ArrayList<NamedValue> mUsers = new ArrayList<NamedValue>();
	private ArrayList<NamedValue> mPlaces = new ArrayList<NamedValue>();
	private String mGroupName;
	private String uri;
	private String nextUri;
	
	private ArrayList<NamedValue> players1 = new ArrayList<NamedValue>();
	private ArrayList<NamedValue> players2 = new ArrayList<NamedValue>();
	
	private TextBox jour = new TextBox();
	private ListBox score1 = new ListBox();
	
	private ListBox score2 = new ListBox();
	public boolean officiel = true;

	public int step = 1;
	
	private AddMatchPage() {
		for(int i=0;i<=13;i++) {
			score1.addItem(String.valueOf(i));
			score2.addItem(String.valueOf(i));
		}
		
	}

	public static AddMatchPage convert(String messageXml) {

		// parse the XML document into a DOM
		Document messageDom = XMLParser.parse(messageXml);

		AddMatchPage result = new AddMatchPage();
		Element topGroup = messageDom.getDocumentElement();
		String date = topGroup.getAttribute("jour");
		result.jour.setText(date);
		result.mGroupName = topGroup.getAttribute("group");
		result.uri = topGroup.getAttribute("uri");
		result.nextUri = topGroup.getAttribute("next");
		NodeList playerList = topGroup.getElementsByTagName("players").item(0).getChildNodes();
		//les players
		for (int i = 0; i < playerList.getLength(); i++) {
			Node node = playerList.item(i);
			if (node.getNodeName().equals("player")) {
				Element playerElement = (Element) node;

				result.mUsers.add(
						new NamedValue(
								playerElement.getAttribute("name"),
								playerElement.getAttribute("label")));
			}
		}
		
		NodeList placeList = topGroup.getElementsByTagName("places").item(0).getChildNodes();
		//les places
		for (int i = 0; i < placeList.getLength(); i++) {
			Node node = placeList.item(i);
			if (node.getNodeName().equals("place")) {
				Element placeElement = (Element) node;

				result.mPlaces.add(
						new NamedValue(
								placeElement.getAttribute("id"),
								placeElement.getAttribute("label")));
			}
		}
		return result;

	}

	@Override
	public String getBackButtonText() {
		return mGroupName;
	}
	
	public String getNextButtonText() {
		return AddMatchPage.NEXT;
	}
	
	public String getFinalButtonText() {
		return AddMatchPage.FINISH;
	}
	
	public String getUri() {
		return uri;
	}

	public String getNextUri() {
		return nextUri;
	}

	@Override
	public String getTitle() {
		return AddMatchPage.TITLE + this.step;
	}
	
	public TextBox getJour() {
		return jour;
	}

	public ListBox getScore1() {
		return score1;
	}

	public ListBox getScore2() {
		return score2;
	}

	public Iterable<NamedValue> getPlayers() {
		return mUsers;
	}
	
	public Iterable<NamedValue> getPlaces() {
		return mPlaces;
	}
	
	public List<NamedValue> getPlayers1() {
		return players1;
	}
	
	public List<NamedValue> getPlayers2() {
		return players2;
	}

}
