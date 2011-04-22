package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.GroupPage;
import org.petank.gwt.client.model.ListePage;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class GroupPagePanel extends AbstractPagePanel {

	public GroupPagePanel(final GroupPage groupPage) {

		FlowPanel basePanel = new FlowPanel();

		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);

		int i = 1;
		for (ListePage group : groupPage.getListePage()) {
			GenericTextTag<String> li = new GenericTextTag<String>("li", group
					.getName());
			li.setAttachedInfo(group.getUri());
			if(i == 1) {
				li.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
					@Override
					public void touchClick(TouchClickEvent<String> e) {
						// attachedInfo is the relative URL
						if (!ViewSettings.AnimationRunning) {
							Controller.navigateToGroupClassement(e.getTarget()
									.getAttachedInfo(), groupPage);
						}
					}
				});
				
			} else if(i == 2) {
				li.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
					@Override
					public void touchClick(TouchClickEvent<String> e) {
						// attachedInfo is the relative URL
						if (!ViewSettings.AnimationRunning) {
							Controller.navigateToGroupMatch(e.getTarget()
									.getAttachedInfo(), groupPage);
						}
					}
				});
			} else if(i == 3) {
				li.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
					@Override
					public void touchClick(TouchClickEvent<String> e) {
						// attachedInfo is the relative URL
						if (!ViewSettings.AnimationRunning) {
							Controller.navigateToAddMatch(e.getTarget()
									.getAttachedInfo(), groupPage);
						}
					}
				});
			}
			li.setStyleName("group");
			ul.add(li);
			i++;
		}

		initWidget(basePanel);
	}

}