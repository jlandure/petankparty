package org.petank.client.web.view.components;

import org.petank.client.web.PetankFacade;
import org.petank.client.web.i18n.MessagesConstantsController;
import org.petank.client.web.util.GWTStringUtils;
import org.petank.client.web.view.LoginMediator;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.Hyperlink;

public class LoginScreen extends LayoutContainer {

	public static final String USER = null;
	public static final String PASSWORD = null;
	private LoginMediator mediator;
	private LayoutContainer smallContainer;
	private Button enterButton;
	private LabelField signInLabel;
	private Html signInLink;

	public LoginScreen(LoginMediator mediator){
		this.mediator = mediator;
		setLayout(new CenterLayout());
		smallContainer = new LayoutContainer(new ColumnLayout());
		add(smallContainer);
		enterButton = new Button(MessagesConstantsController.getConstants().enter());
		add(enterButton);
		enterButton.addListener(Events.Select, new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {					
				PetankFacade.getInst().identify();
			}
		});
		smallContainer.add(enterButton);
		layout();
	}

	public void promptSignIn(String loginURL) {
		signInLabel = new LabelField(MessagesConstantsController.getConstants().please());
		String htmlText = "<a href=\"" + loginURL + "\" >" 
		+ MessagesConstantsController.getConstants().login() + "</a>"; 
		signInLink = new Html(htmlText);
		smallContainer.add(signInLabel);
		smallContainer.add(signInLink);
		layout();
	}



}
