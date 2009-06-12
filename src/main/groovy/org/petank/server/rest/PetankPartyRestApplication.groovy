/**
 * 
 */
package org.petank.server.rest

import org.restlet.Application
import org.restlet.Restlet
import org.restlet.routing.Router
import org.restlet.routing.Redirector
import org.restlet.security.Guard
import org.restlet.data.ChallengeScheme
import javax.cache.CacheException
import javax.cache.CacheManager
import javax.cache.Cache
import java.util.HashMap
import org.petank.server.*
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
        //pas propre pour l'instant
    	PetankGroupUtil.populate()
    	PetankUserUtil.populate()
    	PetankPlaceUtil.populate()
    	MatchUtil.populate()
    	BaremeUtil.populate()
    	
        //Préparation du cache GAE
        prepareCache()
        
        Router router = new Router(getContext());

        router.attach("/",BaseResource.class)
        
        Guard guard1
        Guard guard2
        Redirector redirector1
        Redirector redirector2
        PetankGroupUtil.listGroups.each{
        	
        	guard1 = new Guard(getContext(), ChallengeScheme.HTTP_BASIC/*HTTP_DIGEST*/, "Connexion PetankParty");
        	guard1.getSecrets().put(it.name, it.password.toCharArray());
        	
        	guard2 = new Guard(getContext(), ChallengeScheme.HTTP_BASIC/*HTTP_DIGEST*/, "Connexion PetankParty");
        	guard2.getSecrets().put(it.name, it.password.toCharArray());
        	
        	router.attach("/"+it.name+"/c",guard1)
        	router.attach("/"+it.name+"/m",guard2)
        	redirector1 = new Redirector(getContext(), "/"+it.name+"/classement",  
        			Redirector.MODE_CLIENT_PERMANENT);
        	redirector2 = new Redirector(getContext(), "/"+it.name+"/match",  
        			Redirector.MODE_CLIENT_PERMANENT);
        	
        	guard1.setNext(redirector1)
        	guard2.setNext(redirector2)
        }
        
//        Guard guard = new Guard(getContext(), ChallengeScheme.HTTP_BASIC/*HTTP_DIGEST*/, "Connexion PetankParty");
//        guard.getSecrets().put("euriware", "euriware".toCharArray());
//        router.attach("/euriware/",guard)
//        guard.setNext(ClassementResource.class)
//
		router.attach("/place/{place}", PlaceResource.class);
		router.attach("/{group}/classement", ClassementResource.class);
        router.attach("/{group}/match", MatchResource.class);
        router.attach("/{group}/timeline", TimeLineResource.class).extractQuery("players", "players", true)  ;
        router.attach("/{group}/{player}", PlayerResource.class);
        router.attach("/bareme", BaremeResource.class);
        
        return router;
    }
    
    def prepareCache() {
    	try {
    		//Map props = new HashMap();
            //props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
    		Cache memcache = CacheManager.getInstance().getCacheFactory().createCache();//props);
    		memcache.clear()
    		DefaultResource.MEMCACHE = memcache
        } catch (CacheException e) {
            println "CacheException>>"+e
        }
    }
	
	
}
