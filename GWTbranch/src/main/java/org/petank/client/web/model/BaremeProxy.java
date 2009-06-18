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

public class BaremeProxy extends Proxy {

	private static final String NAME = "baremeProxy";
	private String baseUrl = PetankFacade.getRestURL();
	
	protected BaremeProxy() {
		super(NAME);
	}

	public static BaremeProxy getBaremeProxy(){
		BaremeProxy proxy = (BaremeProxy) PetankFacade.getInst().retrieveProxy(NAME);
		if(proxy == null){
			proxy = new BaremeProxy();
			PetankFacade.getInst().registerProxy(proxy);
		}
		return proxy;
	}
	
	public void getBareme(){
		String url = baseUrl + "/bareme";
		
		final Client client = new Client(Protocol.HTTP);
		Request request = new Request(Method.GET, url);

		// Indicates the client preferences and let the server handle the best representation with content negotiation.
		request.getClientInfo().getAcceptedMediaTypes().add(new Preference<MediaType>(MediaType.TEXT_XML));

		client.handle(request, new Callback() {

			@Override
			public void onEvent(Request request, Response response) {
				if(response.getStatus().isSuccess()){
					XmlRepresentation xml = response.getEntityAsXml();
					PetankFacade.getInst().sendNotification(PetankFacade.DISPLAY_BAREME, xml.getText());
				}
				
			}
			
		});
	}
	
}
