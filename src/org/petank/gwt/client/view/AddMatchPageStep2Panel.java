package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.AddMatchPage;
import org.petank.gwt.client.model.NamedValue;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class AddMatchPageStep2Panel extends AbstractPagePanel {

	public AddMatchPageStep2Panel(final AddMatchPage addMatchPage) {

		FlowPanel basePanel = new FlowPanel();
		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);
		for (final NamedValue player : addMatchPage.getPlayers()) {
			final GenericTextTag<String> li = new GenericTextTag<String>("li", player.getValue());
			li.setStyleName("item_not_checked");
			li.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
				@Override
				public void touchClick(TouchClickEvent<String> e) {
					if (!ViewSettings.AnimationRunning) {
						if(li.getStyleName().equals("item_checked")) {
							addMatchPage.getPlayers2().remove(player);
							li.setStyleName("item_not_checked");
						} else {
							addMatchPage.getPlayers2().add(player);
							li.setStyleName("item_checked");
						}
					}
				}
			});

			ul.add(li);
		}
		
		final GenericTextTag<String> liToStep3 = new GenericTextTag<String>("li", addMatchPage.getNextButtonText());
		liToStep3.setStyleName("group");
		liToStep3.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				if (!ViewSettings.AnimationRunning) {
					addMatchPage.step = 3;
					Controller.navigateToAddMatchStep3(addMatchPage);
				}
			}
		});

		ul.add(liToStep3);

		initWidget(basePanel);
	}

}
