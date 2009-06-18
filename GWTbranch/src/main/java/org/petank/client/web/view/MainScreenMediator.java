package org.petank.client.web.view;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.view.components.MainScreen;
import org.petank.client.web.view.components.PageFrame;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class MainScreenMediator extends Mediator {

	private static final String NAME = "mainScreenMediator";
	
	protected MainScreenMediator(){
		super(NAME, null);
	}
	
	public static MainScreenMediator getMainScreenMediator(){
		MainScreenMediator mediator = (MainScreenMediator) PetankFacade.getInst().retrieveMediator(NAME);
		if(mediator == null){
			mediator = new MainScreenMediator();
			mediator.setViewComponent(new MainScreen(mediator));
			PetankFacade.getInst().registerMediator(mediator);
		}
		return mediator;
	}

	public MainScreen getMainScreen() {
		return (MainScreen) viewComponent;		
	}
}
