/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder;
import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankUser;
import org.petank.client.model.PetankGroup;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.PetankGroupUtil;
import org.petank.server.PetankPlaceUtil;import org.petank.server.dao.DAOManager
/**
 * @author jlandure
 *
 */
public class ResetResource extends DefaultResource {

	def expireCache() {
		return true
	}
	
	def number
	
	def ResetResource(Context context, Request request, Response response) {
		super(context, request, response)
		number = request.getAttributes().get("number")
		populate()
	}
	
	def populate() {
		switch((number as int)) {
			case 0 :
				DefaultResource.MEMCACHE.clear()
				break;
			case 1 :
				def listMatchs
				def listUsers
				DAOManager.instance.getAll(PetankGroup.class).each{
					it.matchApplied = false
					listMatchs = MatchUtil.instance.getMatchByGroupName(it.name)
					listUsers = PetankUserUtil.instance.getUserByGroupName(it.name)
					listUsers.each{
						PetankUserUtil.instance.resetUser(it)
						DAOManager.instance.save(it)
					}
					listMatchs.each{
						MatchUtil.instance.resetMatch(it)
						DAOManager.instance.save(it)
					}
				}
				DefaultResource.MEMCACHE.clear()
				break;
		}
	}
	
    def toXML(xml, writer) {
		xml.ok()
		return writer.toString()
    }

	def toHTML(html, writer) {
		html.html {
			head {
		        title "Reset P\u00E9tank Party"
		    }
		    body {
		    	h1 "Reset"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        br()
		        br()
		        h1 {
		    		"ok"
		    		br()
		    		switch((number as int)) {
			        case 0 : 
						yield("cache html/xml deleted")
						break;
			        case 1 : 
						yield("petankGroup reinitialized : need calculation")
						break;
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
