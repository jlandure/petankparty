package org.petank.client.web.view;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.view.components.LoginScreen;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;


public class LoginMediator extends Mediator {

	private static final String NAME = "loginMediator";
	
	
	protected LoginMediator() {
		super(NAME, null);
	}

	/**
	 * Retrieve existing LoginMediator from facade
	 * If doesn't exist, create and register a new one
	 * @return
	 */
	public static LoginMediator getLoginMediator(){
		LoginMediator mediator = (LoginMediator) PetankFacade.getInst().retrieveMediator(NAME);
		if(mediator == null){
			mediator = new LoginMediator();
			mediator.setViewComponent(new LoginScreen(mediator));
			PetankFacade.getInst().registerMediator(mediator);
		}
		return mediator;
	}
	
	/**
	 * Get associated LoginScreen
	 * @return
	 */
	public LoginScreen getLoginScreen(){
		return (LoginScreen) viewComponent;
	}
	
	/**
	 * PureMVC mediator pattern
	 * List the notifications this mediator should handle
	 */
	@Override
	public String[] listNotificationInterests(){
		return new String[]{PetankFacade.AUTH_ERROR};
	}
	
	
	/**
	 * PureMVC mediator pattern
	 * Handle the notification the mediator has registered
	 */
	@Override
	public void handleNotification(INotification notification){
		
	}

	public void promptSignIn(String url) {
		((LoginScreen)viewComponent).promptSignIn(url);
	}

	
	
	
}
