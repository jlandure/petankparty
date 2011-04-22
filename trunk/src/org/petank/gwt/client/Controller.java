package org.petank.gwt.client;


import org.petank.gwt.client.model.AddMatchPage;
import org.petank.gwt.client.model.BaremePage;
import org.petank.gwt.client.model.ChartPage;
import org.petank.gwt.client.model.ClassementPage;
import org.petank.gwt.client.model.GroupPage;
import org.petank.gwt.client.model.IPage;
import org.petank.gwt.client.model.MainPage;
import org.petank.gwt.client.model.MatchPage;
import org.petank.gwt.client.model.PlayerPage;
import org.petank.gwt.client.util.ObservableStack;
import org.petank.gwt.client.view.AddMatchPageStep1Panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;


public class Controller {

    
	private static boolean mIgnoreEvents = false;

	private static ObservableStack<IPage> mPageStack;
	
	/**
	public static final String HOST_URL = GWT.getHostPageBaseURL();
	 * en mode test
	 */
	public static final String HOST_URL = GWT.getHostPageBaseURL()+"petankparty/";
	public static final String FIRST_PAGE = "index";

	private Controller() {
	}

	public static void init(ObservableStack<IPage> pageStack) {

		mPageStack = pageStack;

		downloadXml(URL.encode(Controller.HOST_URL + Controller.FIRST_PAGE), new XmlHttpResponse() {
			@Override
			public void onSuccessfulReceive(String xmlText) {
				MainPage p = MainPage.convert(xmlText);
				mPageStack.push(p);
			}

			@Override
			public void onUnsuccessfulReceive(String error) {
				Window.alert(error);
			}
		});

	}

	public static void navigateBack() {
		mPageStack.pop();
	}
	
	public static void navigateToBareme(String uri, MainPage mainPage) {
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							BaremePage p = BaremePage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}

	
	public static void navigateToGroup(String uri, IPage currentPage) {
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							GroupPage p = GroupPage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}

	public static void navigateToGroupClassement(String uri, IPage currentPage) {
		
		if(currentPage instanceof AddMatchPage) {
			mPageStack.pop();
		}
		
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							ClassementPage p = ClassementPage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}
	
	public static void navigateToGroupMatch(String uri, GroupPage groupPage) {
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			System.out.println(uri);
			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							MatchPage p = MatchPage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}

	public static void navigateToAddMatch(String uri, GroupPage groupPage) {
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							AddMatchPage p = AddMatchPage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}
	
	public static void navigateToAddMatchStep2(AddMatchPage addMatchPage) {
		mPageStack.pop();
		mPageStack.push(addMatchPage);
	}
	
	public static void navigateToAddMatchStep3(AddMatchPage addMatchPage) {
		mPageStack.pop();
		mPageStack.push(addMatchPage);
	}
	
	public static void navigateToPlayer(String uri, IPage currentPage) {
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							PlayerPage p = PlayerPage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}
	
	public static void navigateToChart(String uri, IPage currentPage) {
		if (!mIgnoreEvents) {
			mIgnoreEvents = true;

			downloadXml(URL.encode(uri),
					new XmlHttpResponse() {
						@Override
						public void onSuccessfulReceive(String xmlText) {
							ChartPage p = ChartPage.convert(xmlText);
							mPageStack.push(p);
							mIgnoreEvents = false;
						}

						@Override
						public void onUnsuccessfulReceive(String error) {
							Window.alert(error);

							mIgnoreEvents = false;

						}
					});
		}
	}

	private static void downloadXml(String url, XmlHttpResponse responseCallback) {

		final XmlHttpResponse finalResponseCallback = responseCallback;
		try {
			RequestBuilder req = new RequestBuilder(RequestBuilder.GET, url);
			req.setHeader("Accept", "text/xml");
			req.sendRequest(null, new RequestCallback() {

				@Override
				public void onError(Request request, Throwable exception) {
					finalResponseCallback
							.onUnsuccessfulReceive("Unable to contact server");
				}

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					if (400 > response.getStatusCode()
							&& 0 != response.getStatusCode()) {
						finalResponseCallback.onSuccessfulReceive(response
								.getText());
					} else {
						switch (response.getStatusCode()) {
						case 0:
							finalResponseCallback
									.onUnsuccessfulReceive("Unable to contact server");
							break;
						case 404:
							finalResponseCallback
									.onUnsuccessfulReceive("Data file not found");
							break;
						default:
							finalResponseCallback
									.onUnsuccessfulReceive("Error loading file: "
											+ response.getStatusCode());

						}
					}
				}

			});
		} catch (RequestException e) {
			responseCallback.onUnsuccessfulReceive(e.getMessage());
		}

	}

	private interface XmlHttpResponse {
		void onSuccessfulReceive(String xmlText);

		void onUnsuccessfulReceive(String error);
	}

}
