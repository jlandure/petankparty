/**
 * 
 */
package org.petank.server.rest

import org.restlet.Application
import org.restlet.Restlet
import org.restlet.routing.Router
import javax.cache.CacheException
import javax.cache.CacheManager
import javax.cache.Cache
import java.util.HashMap
import com.google.appengine.api.memcache.stdimpl.GCacheFactory

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
        
        //Préparation du cache GAE
        prepareCache()
        
        //pas propre pour l'instant
		PetankGroupUtil.populate()
		PetankUserUtil.populate();
		MatchUtil.populate();
		BaremeUtil.populate();
        
        Router router = new Router(getContext());

        router.attach("/",BaseResource.class)
        router.attach("/{group}/classement", ClassementResource.class);
        router.attach("/{group}/match", MatchResource.class);
        router.attach("/bareme", BaremeResource.class);

        return router;
    }
    
    def prepareCache() {
    	try {
    		Map props = new HashMap();
            props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
    		Cache memcache = CacheManager.getInstance().getCacheFactory().createCache(props);
    		memcache.clear()
    		DefaultResource.MEMCACHE = memcache
        } catch (CacheException e) {
            println "CacheException>>"+e
        }
    }
	
	
}
