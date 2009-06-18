package org.petank.client.web.i18n;

import com.google.gwt.core.client.GWT;

public class MessagesConstantsController {

	private static final MessagesConstants MESSAGES = (MessagesConstants) GWT.create(MessagesConstants.class);

	public static MessagesConstants getConstants() {
		return MESSAGES;
	}
}