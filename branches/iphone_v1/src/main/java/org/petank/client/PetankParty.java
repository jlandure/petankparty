package org.petank.client;

import org.petank.client.model.PetankUser;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PetankParty implements EntryPoint {
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
//	private final GreetingServiceAsync greetingService = GWT
//			.create(GreetingService.class);
	
	private final IdentificationServiceAsync identificationService = GWT
	.create(IdentificationService.class);


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button enterButton = new Button("Entrer");
		//final TextBox nameField = new TextBox();
		//nameField.setText("Pétanque USER");

		// We can add style names to widgets
		enterButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		//RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(enterButton);

		// Focus the cursor on the name field when the app loads
		//nameField.setFocus(true);
		//nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		final VerticalPanel dialogVPanel = new VerticalPanel();
		final VerticalPanel dialogVPanel2 = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(dialogVPanel2);
		Grid resultatsOfficiels = new Grid(12,3);
		resultatsOfficiels.setHTML(0, 0, "<em>Place</em>");
		resultatsOfficiels.setHTML(0, 1, "<em>Nom</em>");
		resultatsOfficiels.setHTML(0, 2, "<em>Points</em>");
		resultatsOfficiels.setHTML(1, 0, "<strong>1</strong>");
		resultatsOfficiels.setHTML(1, 1 , "<strong>RST</strong>");
		resultatsOfficiels.setHTML(1, 2, "<strong>680</strong>");
		resultatsOfficiels.setHTML(2, 0, "<strong>1bis</strong>");
		resultatsOfficiels.setHTML(2, 1 , "<strong>SHS</strong>");
		resultatsOfficiels.setHTML(2, 2, "<strong>680</strong>");
		resultatsOfficiels.setText(3, 0, "3");
		resultatsOfficiels.setText(3, 1 , "EBT");
		resultatsOfficiels.setText(3, 2, "664");
		resultatsOfficiels.setText(4, 0, "4");
		resultatsOfficiels.setText(4, 1 , "GBE");
		resultatsOfficiels.setText(4, 2, "662");
		resultatsOfficiels.setText(5, 0, "5");
		resultatsOfficiels.setText(5, 1 , "CLC");
		resultatsOfficiels.setText(5, 2, "657");
		resultatsOfficiels.setText(6, 0, "6");
		resultatsOfficiels.setText(6, 1 , "JLE");
		resultatsOfficiels.setText(6, 2, "656.5");
		resultatsOfficiels.setText(7, 0, "7");
		resultatsOfficiels.setText(7, 1 , "BPT");
		resultatsOfficiels.setText(7, 2, "648");
		resultatsOfficiels.setText(8, 0, "8");
		resultatsOfficiels.setText(8, 1 , "HDG");
		resultatsOfficiels.setText(8, 2, "647.5");
		resultatsOfficiels.setText(9, 0, "9");
		resultatsOfficiels.setText(9, 1 , "CBO");
		resultatsOfficiels.setText(9, 2, "641");
		resultatsOfficiels.setText(10, 0, "10");
		resultatsOfficiels.setText(10, 1 , "JND");
		resultatsOfficiels.setText(10, 2, "630.5");
		resultatsOfficiels.setText(11, 0, "11");
		resultatsOfficiels.setText(11, 1 , "ADE");
		resultatsOfficiels.setText(11, 2, "621.5");
		
		dialogVPanel2.add(resultatsOfficiels);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				enterButton.setEnabled(true);
				enterButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				identify();
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void identify() {
				enterButton.setEnabled(false);
				//String textToServer = nameField.getText();
				//textToServerLabel.setText(textToServer);
				//serverResponseLabel.setText("");
				
				identificationService.identify(new AsyncCallback<PetankUser>() {
					public void onFailure(Throwable caught) {
						//Window.Location.assign(caught.getMessage());
						RootPanel.get("loginContainer").add(new HTML("<p>Please <a href=\""
								+caught.getMessage()
								+"\">sign in</a>.</p>"));
					}
					public void onSuccess(PetankUser result) {
						if(result.getName() == null) {
							RootPanel.get("loginContainer").add(new HTML("<p>Please <a href=\""
									+result.getEmail()
									+"\">sign in</a>.</p>"));
						} else {
							dialogBox.setText("Vous êtes : "+result.getName()+ " ["+result.getEmail()+"]");
							serverResponseLabel
									.removeStyleName("serverResponseLabelError");
							
							serverResponseLabel.setHTML(result.getName());
							dialogBox.center();
							closeButton.setFocus(true);
						}
					}
				});
//				greetingService.greetServer(textToServer,
//						new AsyncCallback<String>() {
//							public void onFailure(Throwable caught) {
//								// Show the RPC error message to the user
//								dialogBox
//										.setText("Remote Procedure Call - Failure");
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//
//							public void onSuccess(String result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(result);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		enterButton.addClickHandler(handler);
		//nameField.addKeyUpHandler(handler);
	}
}
