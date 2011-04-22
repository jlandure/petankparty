package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.AddMatchPage;
import org.petank.gwt.client.model.NamedValue;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class AddMatchPageStep3Panel extends AbstractPagePanel {

	public AddMatchPageStep3Panel(final AddMatchPage addMatchPage) {

		FlowPanel basePanel = new FlowPanel();
		addMatchPage.step = 3;
		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);
		
		String players1 = "";
		for(NamedValue player : addMatchPage.getPlayers1()) {
			players1 += player.getName() + " ";
		}
		
		String players2 = "";
		for(NamedValue player : addMatchPage.getPlayers2()) {
			players2 += player.getName() + " ";
		}
		
		final GenericTextTag<String> liPlayers1Label = new GenericTextTag<String>("li", "Equipe 1");
		liPlayers1Label.setStyleName("item");
		ul.add(liPlayers1Label);
		
		final GenericTextTag<String> liPlayers1 = new GenericTextTag<String>("li", players1);
		liPlayers1.setStyleName("item");
		ul.add(liPlayers1);
		
		final GenericTextTag<String> liPlayers2Label = new GenericTextTag<String>("li", "Equipe 2");
		liPlayers2Label.setStyleName("item");
		ul.add(liPlayers2Label);
		
		final GenericTextTag<String> liPlayers2 = new GenericTextTag<String>("li", players2);
		liPlayers2.setStyleName("item");
		ul.add(liPlayers2);
		
		final GenericTextTag<String> liJourLabel = new GenericTextTag<String>("li", "Jour");
		liJourLabel.setStyleName("item");
		ul.add(liJourLabel);

		final GenericContainerTag liJour = new GenericContainerTag("li");
		liJour.add(addMatchPage.getJour());
		liJourLabel.setStyleName("item");
		ul.add(liJour);
		
		final GenericTextTag<String> liScore1Label = new GenericTextTag<String>("li", "Score 1");
		liScore1Label.setStyleName("item");
		ul.add(liScore1Label);

		final GenericContainerTag liScore1 = new GenericContainerTag("li");
		liScore1.add(addMatchPage.getScore1());
		liScore1.setStyleName("item");
		ul.add(liScore1);
		
		final GenericTextTag<String> liScore2Label = new GenericTextTag<String>("li", "Score 2");
		liScore2Label.setStyleName("item");
		ul.add(liScore2Label);

		final GenericContainerTag liScore2 = new GenericContainerTag("li");
		liScore2.add(addMatchPage.getScore2());
		liScore2.setStyleName("item");
		ul.add(liScore2);
		
		final GenericTextTag<String> liOfficiel = new GenericTextTag<String>("li", "Officiel");
		liOfficiel.setStyleName("item_checked");
		liOfficiel.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				if (!ViewSettings.AnimationRunning) {
					if(liOfficiel.getStyleName().equals("item_checked")) {
						addMatchPage.officiel = false;
						liOfficiel.setStyleName("item_not_checked");
					} else {
						addMatchPage.officiel = true;
						liOfficiel.setStyleName("item_checked");
					}
				}
			}
		});
		
		ul.add(liOfficiel);
		
		final GenericTextTag<String> liToFinish = new GenericTextTag<String>("li", addMatchPage.getFinalButtonText());
		liToFinish.setStyleName("group");
		liToFinish.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				if (!ViewSettings.AnimationRunning) {
					
					//HTTPCLIENT TO POST EVENT
					String url = Controller.HOST_URL + "euriware2011/match";
					RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
					String requestData = "";
					for(NamedValue player : addMatchPage.getPlayers1()) {
						requestData += "player1_" + player.getName()+"&";
					}
					for(NamedValue player : addMatchPage.getPlayers2()) {
						requestData += "player2_" + player.getName()+"&";
					}
					requestData += "score1="+addMatchPage.getScore1().getValue(addMatchPage.getScore1().getSelectedIndex())+"&";
					requestData += "score2="+addMatchPage.getScore2().getValue(addMatchPage.getScore1().getSelectedIndex())+"&";
					if(addMatchPage.officiel) {
						requestData += "officiel&";
					}
					requestData += "jour="+addMatchPage.getJour().getValue()+"&";
					//builder.setRequestData(URL.encodeQueryString(requestData));
					
					try {
						  Request request = builder.sendRequest(requestData, new RequestCallback() {
						    public void onError(Request request, Throwable exception) {
						       // Couldn't connect to server (could be timeout, SOP violation, etc.)
						    }

						    @Override
						    public void onResponseReceived(Request request, Response response) {
						      if (200 == response.getStatusCode()) {
						          // Process the response in response.getText()
						      } else {
						        // Handle the error.  Can get the status text from response.getStatusText()
						      }
						    }

						  });
						} catch (RequestException exc) {
						  // Couldn't connect to server
						}
					
					Controller.navigateToGroupClassement(addMatchPage.getNextUri(), addMatchPage);
				}
			}
		});

		ul.add(liToFinish);

		initWidget(basePanel);
	}

}
