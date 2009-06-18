package org.petank.client.web;



import org.petank.client.model.PetankUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>IdentificationService</code>.
 */
public interface IdentificationServiceAsync {
	void identify(AsyncCallback<PetankUser> callback);
}
