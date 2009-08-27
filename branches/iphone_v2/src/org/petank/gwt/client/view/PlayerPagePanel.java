package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.NamedValue;
import org.petank.gwt.client.model.PlayerPage;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class PlayerPagePanel extends AbstractPagePanel {

	public PlayerPagePanel(final PlayerPage playerPage) {

		FlowPanel basePanel = new FlowPanel();

		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);
		
		GenericTextTag<String> li1 = new GenericTextTag<String>("li", 
				playerPage.getChart().getName());
		li1.setStyleName("group");
		ul.add(li1);
		li1.setAttachedInfo(playerPage.getChart().getUri());
		li1.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				// attachedInfo is the relative URL
				if (!ViewSettings.AnimationRunning) {
					Controller.navigateToChart(e.getTarget()
							.getAttachedInfo(), playerPage);
				}
			}
		});
		
		
		for (NamedValue value : playerPage.getValues()) {
			GenericTextTag<String> li = new GenericTextTag<String>("li", 
					value.toString());
			li.setStyleName("item");
			ul.add(li);
		}

		initWidget(basePanel);
	}

}
