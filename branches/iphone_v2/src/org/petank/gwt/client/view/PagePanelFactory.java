package org.petank.gwt.client.view;

import org.petank.gwt.client.model.ChartPage;
import org.petank.gwt.client.model.ClassementPage;
import org.petank.gwt.client.model.GroupPage;
import org.petank.gwt.client.model.IPage;
import org.petank.gwt.client.model.MainPage;
import org.petank.gwt.client.model.PlayerPage;

public class PagePanelFactory {

	private PagePanelFactory() {

	}

	public static AbstractPagePanel createPagePanel(IPage page) {
		if (page instanceof MainPage) {
			return new MainPagePanel((MainPage) page);
		} else if (page instanceof GroupPage) {
			return new GroupPagePanel((GroupPage) page);
		} else if (page instanceof ClassementPage) {
			return new ClassementPagePanel((ClassementPage) page);
		} else if (page instanceof PlayerPage) {
			return new PlayerPagePanel((PlayerPage) page);
		} else if (page instanceof ChartPage) {
			return new ChartPagePanel((ChartPage) page);
		}
		throw new IllegalArgumentException("The page is unknown:"
				+ page.getClass().getName());
	}
}
