package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.AddMatchPage;
import org.petank.gwt.client.model.NamedValue;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

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
		
		final GenericTextTag<String> liOfficiel = new GenericTextTag<String>("li", "Officiel");
		liOfficiel.setStyleName("item_not_checked");
		liOfficiel.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				if (!ViewSettings.AnimationRunning) {
					if(liOfficiel.getStyleName().equals("item_checked")) {
						addMatchPage.officiel = true;
						liOfficiel.setStyleName("item_not_checked");
					} else {
						addMatchPage.officiel = false;
						liOfficiel.setStyleName("item_checked");
					}
				}
			}
		});
		
		final GenericTextTag<String> liToFinish = new GenericTextTag<String>("li", addMatchPage.getFinalButtonText());
		liToFinish.setStyleName("group");
		liToFinish.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				if (!ViewSettings.AnimationRunning) {
					
					//HTTPCLIENT TO POST EVENT
					Controller.navigateToGroupClassement(addMatchPage.getNextUri(), addMatchPage);
				}
			}
		});

		ul.add(liToFinish);

		initWidget(basePanel);
	}

}
