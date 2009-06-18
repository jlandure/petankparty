package org.petank.client.web.model;

import org.petank.client.web.PetankFacade;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import org.restlet.gwt.Callback;
import org.restlet.gwt.Client;
import org.restlet.gwt.data.MediaType;
import org.restlet.gwt.data.Method;
import org.restlet.gwt.data.Preference;
import org.restlet.gwt.data.Protocol;
import org.restlet.gwt.data.Request;
import org.restlet.gwt.data.Response;
import org.restlet.gwt.resource.XmlRepresentation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;

public class RankingProxy extends Proxy {

private static final String NAME = "rankingProxy";
	
	private String baseUrl = PetankFacade.getRestURL();

	protected RankingProxy() {
		super(NAME);
	}

	public static RankingProxy getRankingProxy(){
		RankingProxy proxy = (RankingProxy) PetankFacade.getInst().retrieveProxy(NAME);
		if(proxy == null){
			proxy = new RankingProxy();
			PetankFacade.getInst().registerProxy(proxy);
		}
		return proxy;
	}
	
	public void getRanking(String groupe){
		String url = baseUrl + "/" +groupe + "/classement";
		
		final Client client = new Client(Protocol.HTTP);
		Request request = new Request(Method.GET, url);

		// Indicates the client preferences and let the server handle the best representation with content negotiation.
		request.getClientInfo().getAcceptedMediaTypes().add(new Preference<MediaType>(MediaType.TEXT_XML));

		client.handle(request, new Callback() {

			@Override
			public void onEvent(Request request, Response response) {
				if(response.getStatus().isSuccess()){
					XmlRepresentation xml = response.getEntityAsXml();
					PetankFacade.getInst().sendNotification(PetankFacade.DISPLAY_RANKS, xml.getText());
				}
				
			}
			
		});
	}
	
}
