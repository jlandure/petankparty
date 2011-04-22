package org.petank.gwt.client.view;

import org.petank.gwt.client.model.ChartPage;
import org.petank.gwt.client.util.GenericContainerTag;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class ChartPagePanel extends AbstractPagePanel {

	public ChartPagePanel(final ChartPage chartPage) {

		FlowPanel basePanel = new FlowPanel();

		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);
		
		Image li1 = new Image(chartPage.getImgUrl(), 0, 0, 500, 200);
		//li1.setStyleName("item");
		ul.add(li1);

		initWidget(basePanel);
	}

}
