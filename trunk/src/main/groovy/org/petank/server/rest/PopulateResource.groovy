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
public class PopulateResource extends DefaultResource {

	def expireCache() {
		return true
	}
	
	def number
	
	def PopulateResource(Context context, Request request, Response response) {
		super(context, request, response)
		number = request.getAttributes().get("number")
		populate()
	}
	
	def populate() {
		def loadObjects = {objects -> objects.each{DAOManager.instance.save(it)}}
		switch((number as int)) {
			case 1 : 
				loadObjects(BaremeUtil.instance.populate()); 
				break;
			case 2 : 
				loadObjects(PetankGroupUtil.instance.populate())
				break;
			case 3 :
				loadObjects(PetankPlaceUtil.instance.populate())
				break;
			case 4 :
				loadObjects(PetankUserUtil.instance.populate())
				break;
			case 5 :
				MatchUtil.instance.populate1()
				break;
			case 6 :
				MatchUtil.instance.populate2()
				break;
			case 7 :
				MatchUtil.instance.populate3()
				break;
			case 8 :
				MatchUtil.instance.populate4()
				break;
			case 9 :
				MatchUtil.instance.populate5()
				break;
			case 10 :
				MatchUtil.instance.populate6()
				break;
			case 11 :
				MatchUtil.instance.populate7()
				break;
			case 12 :
				MatchUtil.instance.populate8()
				break;
			case 13 :
				MatchUtil.instance.populate9()
				break;
			case 14 :
				MatchUtil.instance.populate10()
				break;
			case 15 :
				MatchUtil.instance.populate11()
				break;
			case 16 :
				MatchUtil.instance.populate12()
				break;
			case 17 :
				MatchUtil.instance.populate13()
				break;
			case 0 :
//				DAOManager.instance.getAll(PetankGroup.class).each{
//					it.matchApplied = false
//				}
//				DefaultResource.MEMCACHE.clear()
				//MatchUtil.instance.populate()
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
		        title "Loader P\u00E9tank Party"
		    }
		    body {
		    	h1 "Loader"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        br()
		        br()
		        h1 {
		    		"ok"
		    		br()
		    		switch((number as int)) {
			        case 1 : 
						yield("BaremeUtil.instance.populate()")
						break;
					case 2 : 
						yield("PetankGroupUtil.instance.populate()")
						break;
					case 3 :
						yield("PetankPlaceUtil.instance.populate()")
						break;
					case 4 :
						yield("PetankUserUtil.instance.populate()")
						break;
					case 5..17 :
						yield("MatchUtil.instance.populate()/$number")
						break;
					case 0 :
						yield("Use /reset/0 pour la remise à zéro [[populate()/0 Remise à zéro]]")
						break;
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
