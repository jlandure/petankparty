package org.petank.server;

import com.google.appengine.api.users.UserServiceFailureException;


public class UserNotFoundException extends UserServiceFailureException {

	private static final long serialVersionUID = -5901128470280621217L;

	public UserNotFoundException(String loginUrl) {
		super(loginUrl);
	}
	
}
