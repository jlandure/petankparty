package org.petank.gwt.client;

import org.petank.gwt.client.model.IPage;
import org.petank.gwt.client.model.Screen;
import org.petank.gwt.client.model.ScreenOrientation;
import org.petank.gwt.client.util.ObservableStack;
import org.petank.gwt.client.view.HeaderPanel;
import org.petank.gwt.client.view.PagesContainerPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sampleiphoneapp implements EntryPoint,
		Screen.OrientationChangedHandler {

	private RootPanel mBrowserPanel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		DeferredCommand.addCommand(new Command() {
			public void execute() {
				Window.scrollTo(0, 0);
			}
		});

		new Image("../images/chevron_touched.png");
		new Image("../images/item_background_touched.png");
		new Image("../images/back_button_touched.png");

		mBrowserPanel = RootPanel.get("browser");

		HeaderPanel headerPanel = new HeaderPanel();
		mBrowserPanel.add(headerPanel);
		PagesContainerPanel pagesPanel = new PagesContainerPanel();
		mBrowserPanel.add(pagesPanel);

		ObservableStack<IPage> pageStack = new ObservableStack<IPage>();

		pagesPanel.bind(pageStack);
		headerPanel.bind(pageStack);
		Controller.init(pageStack);

		Screen screen = new Screen();
		screen.addHandler(this);
		orientationChanged(screen.getScreenOrientation());

		Element startup = Document.get().getElementById("startup");
		startup.getParentElement().removeChild(startup);

		mBrowserPanel.getElement().getStyle().setProperty("display", "inline");

	}

	@Override
	public void orientationChanged(ScreenOrientation newOrientation) {
		if (newOrientation == ScreenOrientation.Portrait) {
			mBrowserPanel.removeStyleName("landscape");
			mBrowserPanel.addStyleName("portrait");
		} else {
			mBrowserPanel.addStyleName("landscape");
			mBrowserPanel.removeStyleName("portrait");
		}
	}

}
