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
import org.petank.client.model.*
import com.google.appengine.api.memcache.stdimpl.GCacheFactory
import org.petank.server.dao.DAOManager
/**
 * @author jlandure
 *
 */
public class PetankPartyRestApplication extends Application {

	public static String VERSION = "V2011-beta2"
	
    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createRoot() {
        //Préparation du cache GAE
        prepareCache()
        
        Router router = new Router(getContext());

        router.attach("/",RedirectResource.class)
        router.attach("/index",BaseResource.class)

        router.attach("/bareme", BaremeResource.class);
		router.attach("/addGroup", AddGroupResource.class);
		router.attach("/addPlace", AddPlaceResource.class);
        router.attach("/populate/{number}", PopulateResource.class);
        router.attach("/reset/{number}", ResetResource.class);
        router.attach("/place/{place}", PlaceResource.class);
		router.attach("/{group}", DefaultGroupResource.class);
		router.attach("/{group}/classement", ClassementResource.class);
        router.attach("/{group}/match", MatchResource.class);
        router.attach("/{group}/match/add", AddMatchResource.class);
		router.attach("/{group}/match/{idMatch}", DelMatchResource.class);
        router.attach("/{group}/timeline", TimeLineResource.class).extractQuery("players", "players", true)  ;
        router.attach("/{group}/export", ExportResource.class);
        router.attach("/{group}/{player}", PlayerResource.class);
		router.attach("/{group}/{player}/add", AddPlayerResource.class);
        router.attach("/{group}/{player}/chart", ChartResource.class);
        router.attach("/export", ExportResource.class);
        
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
