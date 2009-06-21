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
	
	def number, start, end
	
	def PopulateResource(Context context, Request request, Response response) {
		super(context, request, response)
		number = request.getAttributes().get("number")
		start = (getQuery().getFirstValue('start') as int);  
		end = (getQuery().getFirstValue('end') as int);  
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
				def listMatchs = MatchUtil.instance.populate()
					if(start > listMatchs.size()) {
						quit()
					} else {
						if(end > listMatchs.size()) {
							end = -1
						}
						 if(start != null && end != null) {
							loadObjects(listMatchs[start..end])
						 }
					}
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
					case 5 :
						if(start != null && end != null) {
							 yield("MatchUtil.instance.populate()[$start..$end]")
						}
						break;
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
