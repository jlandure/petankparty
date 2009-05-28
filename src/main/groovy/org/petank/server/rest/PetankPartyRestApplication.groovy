/**
 * 
 */
package org.petank.server.rest

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;


/**
 * @author jlandure
 *
 */
public class PetankPartyRestApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createRoot() {
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());

        router.attach("/",BaseResource.class)
        router.attach("/{group}/classement", ClassementResource.class);
        router.attach("/{group}/match", MatchResource.class);
        router.attach("/bareme", BaremeResource.class);

        return router;
    }
	
	
}
