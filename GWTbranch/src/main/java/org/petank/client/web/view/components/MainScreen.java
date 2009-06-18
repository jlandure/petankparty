package org.petank.client.web.view.components;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.i18n.MessagesConstantsController;
import org.petank.client.web.view.BaremeMediator;
import org.petank.client.web.view.MainScreenMediator;
import org.petank.client.web.view.MatchesMediator;
import org.petank.client.web.view.RankingMediator;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.fx.Resizable;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;

public class MainScreen extends LayoutContainer {

	private MainScreenMediator mediator;
	private PageFrame frame;

	public PageFrame getFrame(){return frame;}

	private LayoutContainer menu;

	public MainScreen(MainScreenMediator mediator){
		this.mediator = mediator;
		this.setLayout(new FitLayout());

		frame = new PageFrame();
		add(frame, new FitData());

		menu = new LayoutContainer(new FitLayout());
		frame.add(menu, new RowData());
		displayPortal();
	}

	public void setPage(Component page){
		frame.removeAll();
		frame.add(page);
	}

	public void backToMain(){
		frame.removeAll();
		frame.add(menu, new RowData());
		displayPortal();
	}

	private void displayPortal(){
		Portal portal = new Portal(2);
		Portlet bareme = new Portlet();
		Portlet matchs = new Portlet();
		Portlet race = new Portlet();

		bareme.add(BaremeMediator.getBaremeMediator().getBaremeScreen());
		configPanel(bareme, MessagesConstantsController.getConstants().bareme());
		PetankFacade.getInst().sendNotification(PetankFacade.BAREME);

		matchs.add(MatchesMediator.getMatchesMediator().getMatchesScreen());
		configPanel(matchs, MessagesConstantsController.getConstants().matchs());
		PetankFacade.getInst().sendNotification(PetankFacade.MATCHES);

		race.add(RankingMediator.getRankingMediator().getRankingScreen());
		configPanel(race, MessagesConstantsController.getConstants().rank());
		PetankFacade.getInst().sendNotification(PetankFacade.RACE);


		portal.add(race, 0);
		portal.add(bareme, 1);
		portal.add(matchs, 1);

		menu.add(portal);
	}


	private void configPanel(final ContentPanel panel, String title) {  
		panel.setCollapsible(true);  
		panel.setAnimCollapse(false);  
		panel.setBodyBorder(false);
		panel.setHeading(title);
		panel.setWidth(350);
		panel.getHeader().addTool(  
				new ToolButton("x-tool-close", new SelectionListener<ComponentEvent>() {  

					@Override  
					public void componentSelected(ComponentEvent ce) {  
						panel.removeFromParent();  
					}  

				}));  
		panel.getHeader().sinkEvents(Events.OnClick);
		panel.getHeader().addListener(Events.OnClick, new Listener<BaseEvent>() {

			public void handleEvent(BaseEvent be) {
				panel.setExpanded(! panel.isExpanded());

			}
		});
		panel.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
		
			@Override
			public void handleEvent(BoxComponentEvent be) {
				IGridScreen screen = (IGridScreen) panel.getItem(0);
				try{
					screen.getGrid().setSize(300, panel.getHeight() - 100);
				}catch (Throwable e) {
					e.printStackTrace();
				}
		
			}
		});
		panel.addListener(Events.Render, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				new Resizable(panel, "s");		
			}
		});
	}
}