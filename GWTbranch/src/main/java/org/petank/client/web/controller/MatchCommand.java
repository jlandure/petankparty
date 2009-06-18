package org.petank.client.web.controller;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.model.MatchProxy;
import org.petank.client.web.model.RankingProxy;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class MatchCommand extends SimpleCommand {

	public void execute( INotification notification ){
		String name = notification.getName();
		if(PetankFacade.MATCHES.equals(name)){
			MatchProxy.getMatchProxy().getMatches("euriware");
		}
	}
	
}
