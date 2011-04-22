package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.AddMatchPage;
import org.petank.gwt.client.model.NamedValue;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class AddMatchPageStep1Panel extends AbstractPagePanel {

	public AddMatchPageStep1Panel(final AddMatchPage addMatchPage) {

		FlowPanel basePanel = new FlowPanel();
		addMatchPage.step = 1;
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
							addMatchPage.getPlayers1().remove(player);
							li.setStyleName("item_not_checked");
						} else {
							addMatchPage.getPlayers1().add(player);
							li.setStyleName("item_checked");
						}
					}
				}
			});

			ul.add(li);
		}
		
		final GenericTextTag<String> liToStep2 = new GenericTextTag<String>("li", addMatchPage.getNextButtonText());
		liToStep2.setStyleName("group");
		liToStep2.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				if (!ViewSettings.AnimationRunning) {
					addMatchPage.step = 2;
					Controller.navigateToAddMatchStep2(addMatchPage);
				}
			}
		});

		ul.add(liToStep2);

		initWidget(basePanel);
	}

}
