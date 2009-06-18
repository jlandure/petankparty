package org.petank.client.web.controller;

import org.petank.client.web.PetankFacade;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.google.gwt.user.client.History;

public class ProcessLoginCommand extends SimpleCommand {

	
	@Override
	public void execute(INotification notification){
		if(PetankFacade.AUTH_OK.equals(notification.getName())){
			History.newItem(PetankFacade.MAIN);
		}
		
	}
	
}
