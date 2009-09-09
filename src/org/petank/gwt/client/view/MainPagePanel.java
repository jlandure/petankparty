package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.MainPage;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class MainPagePanel extends AbstractPagePanel {

	private MainPage mMainPage;

	public MainPagePanel(MainPage mainPage) {

		FlowPanel basePanel = new FlowPanel();

		mMainPage = mainPage;

		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);

		GenericTextTag<String> li = new GenericTextTag<String>("li",
				mainPage.getGroup().getName());
		li.setAttachedInfo(mainPage.getGroup().getUri());

		li.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				// attachedInfo is the relative URL
				if (!ViewSettings.AnimationRunning) {
					Controller.navigateToGroup(e.getTarget()
							.getAttachedInfo(), mMainPage);
				}
			}
		});
		li.setStyleName("group");
		
		GenericTextTag<String> liBareme = new GenericTextTag<String>("li",
				mainPage.getBareme().getName());
		liBareme.setAttachedInfo(mainPage.getBareme().getUri());

		liBareme.addHandler(new TouchClickEvent.TouchClickHandler<String>() {
			@Override
			public void touchClick(TouchClickEvent<String> e) {
				// attachedInfo is the relative URL
				if (!ViewSettings.AnimationRunning) {
					Controller.navigateToBareme(e.getTarget()
							.getAttachedInfo(), mMainPage);
				}
			}
		});
		liBareme.setStyleName("group");

		ul.add(liBareme);
		ul.add(li);

		initWidget(basePanel);
	}

}
