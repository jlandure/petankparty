package org.petank.client.web;

import org.petank.client.model.PetankUser;
import org.petank.client.web.controller.BaremeCommand;
import org.petank.client.web.controller.MatchCommand;
import org.petank.client.web.controller.ProcessLoginCommand;
import org.petank.client.web.controller.RaceCommand;
import org.petank.client.web.i18n.MessagesConstantsController;
import org.petank.client.web.util.GWTStringUtils;
import org.petank.client.web.view.BaremeMediator;
import org.petank.client.web.view.LoginMediator;
import org.petank.client.web.view.MainScreenMediator;
import org.petank.client.web.view.MatchesMediator;
import org.petank.client.web.view.RankingMediator;
import org.puremvc.java.multicore.patterns.facade.Facade;
import org.puremvc.java.multicore.patterns.observer.Notification;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * Facade for the PetankParty GWT module
 * Singleton pattern instead of PureMVC multiton
 * @author hdubourg
 *
 */
public class PetankFacade extends Facade implements ValueChangeHandler<String>{

	/**
	 * singleton name for multiton
	 */
	private static final String NAME = "petankPartySingletonFacade";
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final IdentificationServiceAsync identificationService = GWT
											.create(IdentificationService.class);
	private PetankParty module;

	//Notifications list
	public static final String PROCESS_LOGIN_CLICK = "processLoginClick";
	public static final String AUTH = "authProc";
	public static final String AUTH_ERROR = "authError";
	public static final String AUTH_OK = "authOK";

	public static final String MAIN = "main";
	public static final String RACE = "race";
	public static final String MATCHES = "match";
	public static final String BAREME = "bareme";
	

	public static final String DISPLAY_RANKS = "displayRanks";
	public static final String DISPLAY_USER_PAGE = "displayUserPage";
	public static final String DISPLAY_LAST_MATCHES = "displayLastMatches";
	public static final String DISPLAY_BAREME = "displayBareme";

	
	
	

	protected PetankFacade(String key) {
		super(key);
	}

	/**
	 * 
	 * @return
	 */
	public static PetankFacade getInst(){
		Facade facade = instanceMap.get(NAME);
		if(facade == null){
			facade = new PetankFacade(NAME);
			instanceMap.put(NAME, facade);
		}
		
		return (PetankFacade) facade;
	}

	/**
	 * Declare the notification -> commands mapping
	 */
	@Override
	protected void initializeController(){
		super.initializeController();
		this.registerCommand(RACE, new RaceCommand());
		this.registerCommand(MATCHES, new MatchCommand());
		this.registerCommand(BAREME, new BaremeCommand());
		this.registerCommand(AUTH_OK, new ProcessLoginCommand());
		
	}

	@Override
	protected void initializeView(){
		super.initializeView();
	}

	/**
	 * History support
	 */
	public void initHistorySupport() {
		History.addValueChangeHandler(this);
		History.fireCurrentHistoryState();
	}
	
	
	/**
	 * History token change handler
	 */
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String value = event.getValue();
		if(MAIN.equals(value)){
			module.setDisplay((Component) MainScreenMediator.getMainScreenMediator().getViewComponent());
		}else if (RACE.equals(value)){
			module.setDisplay((Component) MainScreenMediator.getMainScreenMediator().getViewComponent());
			MainScreenMediator.getMainScreenMediator().getMainScreen().setPage(RankingMediator.getRankingMediator().getRankingScreen());
			sendNotification(RACE);
		}else if (MATCHES.equals(value)){
			module.setDisplay((Component) MainScreenMediator.getMainScreenMediator().getViewComponent());
			MainScreenMediator.getMainScreenMediator().getMainScreen().setPage(MatchesMediator.getMatchesMediator().getMatchesScreen());
			sendNotification(MATCHES);
		}else if (BAREME.equals(value)){
			module.setDisplay((Component) MainScreenMediator.getMainScreenMediator().getViewComponent());
			MainScreenMediator.getMainScreenMediator().getMainScreen().setPage(BaremeMediator.getBaremeMediator().getBaremeScreen());
			sendNotification(BAREME);
		} else{
			notifyObservers( new Notification( parseForToken(value), parseForParams(value), null) );
		}
	}


	private Object parseForParams(String value) {
		return GWTStringUtils.EMPTY;
	}

	private String parseForToken(String value) {
		return GWTStringUtils.EMPTY;
	}

	/**
	 * Startup method
	 * Use from the entry point
	 */
	public void startup(PetankParty petankModule) {
		this.module = petankModule;
		initializeController();
		initializeView();
		module.setDisplay(LoginMediator.getLoginMediator().getLoginScreen());
		initHistorySupport();
	}

	/**
	 * Identify method
	 */

	public void identify() {

		identificationService.identify(new AsyncCallback<PetankUser>() {
			public void onFailure(Throwable caught) {
				MessageBox.alert(MessagesConstantsController.getConstants().serverError(), SERVER_ERROR, null);
			}
			public void onSuccess(PetankUser result) {
				if(result != null && result.getName() == null) {
					LoginMediator.getLoginMediator().promptSignIn(result.getEmail());		
				} else {
					PetankFacade.getInst().sendNotification(PetankFacade.AUTH_OK, result);
				}
			}
		});
	}



	/**
	 * PureMVC notification sending method
	 * The override ensures the token management
	 */
	@Override
	public void sendNotification( String notificationName, Object body, String type ){
		
		//manage the notifications that trigger an URL change
		if(true){
			notifyObservers(new Notification(notificationName, body, type));
		}else{
			History.newItem(notificationName + "?" + (String)body);
		}
	}

	
	public static native String getRestURL()/*-{ return $wnd.restURL; }-*/;

}
