package org.petank.client.web.configuration;

import com.google.gwt.core.client.GWT;

public class ConfigurationConstantsController {

	private static final ConfigurationConstants MESSAGES = (ConfigurationConstants) GWT.create(ConfigurationConstants.class);
	
	public static ConfigurationConstants getConstants() {
		return MESSAGES;
	}
}
