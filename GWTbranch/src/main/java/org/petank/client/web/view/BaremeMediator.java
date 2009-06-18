package org.petank.client.web.view;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.view.components.BaremeScreen;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class BaremeMediator extends Mediator{

private static final String NAME = "baremeMediator";
	
	 

	protected BaremeMediator(){
		super(NAME, null);
	}
	
	public static BaremeMediator getBaremeMediator(){
		BaremeMediator mediator = (BaremeMediator) PetankFacade.getInst().retrieveMediator(NAME);
		if(mediator == null){
			mediator = new BaremeMediator();
			mediator.setViewComponent(new BaremeScreen(mediator));
			PetankFacade.getInst().registerMediator(mediator);
		}
		return mediator;
	}
	
	public void handleNotification( INotification notification ){
		String name = notification.getName();
		if(PetankFacade.DISPLAY_BAREME.equals(name)){
			((BaremeScreen)viewComponent).createBaremeGrid((String)notification.getBody());
		}
		
	}

	public String[] listNotificationInterests( ){
		return new String[]{PetankFacade.DISPLAY_BAREME};
	}
	
	public BaremeScreen getBaremeScreen(){
		return (BaremeScreen)viewComponent;
	}
	
}
