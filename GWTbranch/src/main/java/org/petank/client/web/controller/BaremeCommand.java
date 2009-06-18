package org.petank.client.web.controller;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.model.BaremeProxy;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class BaremeCommand extends SimpleCommand {

	public void execute( INotification notification ){
		String name = notification.getName();
		if(PetankFacade.BAREME.equals(name)){
			BaremeProxy.getBaremeProxy().getBareme();
		}
	}
	
}
