package org.petank.client;



import org.petank.client.model.PetankUser;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("identify")
public interface IdentificationService extends RemoteService {
	PetankUser identify();
}