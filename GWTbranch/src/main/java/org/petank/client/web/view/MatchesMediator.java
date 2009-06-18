package org.petank.client.web.view;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.view.components.MatchesScreen;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import com.google.gwt.core.client.GWT;

public class MatchesMediator extends Mediator{

private static final String NAME = "matchesMediator";
	
	 

	protected MatchesMediator(){
		super(NAME, null);
	}
	
	public static MatchesMediator getMatchesMediator(){
		MatchesMediator mediator = (MatchesMediator) PetankFacade.getInst().retrieveMediator(NAME);
		if(mediator == null){
			mediator = new MatchesMediator();
			mediator.setViewComponent(new MatchesScreen(mediator));
			PetankFacade.getInst().registerMediator(mediator);
		}
		return mediator;
	}
	
	public void handleNotification( INotification notification ){
		String name = notification.getName();
		if(PetankFacade.DISPLAY_LAST_MATCHES.equals(name)){
			((MatchesScreen)viewComponent).createMatchGrid((String)notification.getBody());
		}
		
	}

	public String[] listNotificationInterests( ){
		return new String[]{PetankFacade.DISPLAY_LAST_MATCHES};
	}
	
	public MatchesScreen getMatchesScreen(){
		return (MatchesScreen)viewComponent;
	}
	
}
