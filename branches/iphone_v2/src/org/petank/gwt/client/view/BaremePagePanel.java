package org.petank.gwt.client.view;

import org.petank.gwt.client.Controller;
import org.petank.gwt.client.model.BaremePage;
import org.petank.gwt.client.model.GroupPage;
import org.petank.gwt.client.model.ListePage;
import org.petank.gwt.client.model.NamedValue;
import org.petank.gwt.client.util.GenericContainerTag;
import org.petank.gwt.client.util.GenericTextTag;
import org.petank.gwt.client.util.TouchClickEvent;

import com.google.gwt.user.client.ui.FlowPanel;

public class BaremePagePanel extends AbstractPagePanel {

	public BaremePagePanel(final BaremePage baremePage) {

		FlowPanel basePanel = new FlowPanel();

		GenericContainerTag ul = new GenericContainerTag("ul");
		basePanel.add(ul);

		for (NamedValue bareme : baremePage.getNamedValues()) {
			GenericTextTag<String> li = new GenericTextTag<String>("li", 
					bareme.getName()
					+ "  "+
					bareme.getValue());
			li.setStyleName("item");
			ul.add(li);
		}

		initWidget(basePanel);
	}

}