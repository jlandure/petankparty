package org.petank.client.web.view;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.view.components.RankingScreen;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import com.extjs.gxt.ui.client.widget.Component;

public class RankingMediator extends Mediator{

private static final String NAME = "rankingMediator";
	
	protected RankingMediator(){
		super(NAME, null);
	}
	
	public static RankingMediator getRankingMediator(){
		RankingMediator mediator = (RankingMediator) PetankFacade.getInst().retrieveMediator(NAME);
		if(mediator == null){
			mediator = new RankingMediator();
			mediator.setViewComponent(new RankingScreen(mediator));
			PetankFacade.getInst().registerMediator(mediator);
		}
		return mediator;
	}

	public RankingScreen getRankingScreen() {
		return (RankingScreen) viewComponent;
	}

	public void handleNotification( INotification notification ){
		String name = notification.getName();
		if(PetankFacade.DISPLAY_RANKS.equals(name)){
			((RankingScreen) viewComponent).createRankingGrid((String) notification.getBody());
		}
		
	}


	public String[] listNotificationInterests( ){
		return new String[]{PetankFacade.DISPLAY_RANKS};
	}
	
}
