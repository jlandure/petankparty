package org.petank.client.web;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PetankParty implements EntryPoint {

	public static Viewport viewPort;
	
	
	public void setDisplay(Component page){
		viewPort.removeAll();	
		viewPort.add(page);
		viewPort.layout();
	}	
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		PetankFacade facade = PetankFacade.getInst();
		viewPort = new Viewport();
		viewPort.setLayout(new FitLayout());
		RootPanel.get().add(viewPort);
		facade.startup(this);
	}
}
