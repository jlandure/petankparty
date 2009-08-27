package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.ClassementPage;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class ClassementPagePanel extends AbstractPagePanel {

	public ClassementPagePanel(final ClassementPage groupPage) {

		FlowPanel basePanel = new FlowPanel();

		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);
		for (ClassementPage.User player : groupPage.getTeams()) {
			GenericTextTag<String> li = new GenericTextTag<String>("li", 
					"["+player.getClassement()+"] "+
					player.getName()
					+ "  "+
					player.getScore());
			li.setStyleName("group");
			li.setAttachedInfo(player.getUri());
			li.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
				@Override
				public void touchClick(TouchClickEvent<String> e) {
					if (!ViewSettings.AnimationRunning) {
						Controller.navigateToPlayer(e.getTarget()
								.getAttachedInfo(), groupPage);
					}
				}
			});

			ul.add(li);
		}

		initWidget(basePanel);
	}

}
