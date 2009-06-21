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
import org.petank.server.PetankPlaceUtil;
/**
 * @author jlandure
 *
 */
public class PopulateResource extends DefaultResource {

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
				loadObjects(MatchUtil.instance.populate()[0..50])
				break;
			case 6 :
				loadObjects(MatchUtil.instance.populate()[51..100])
				break;
			case 7 :
				loadObjects(MatchUtil.instance.populate()[101..150])
				break;
			case 8 : 
				loadObjects(MatchUtil.instance.populate()[150..-1])
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
						yield("MatchUtil.instance.populate()[0..50]")
						break;
					case 6 :
						yield("MatchUtil.instance.populate()[51..100]")
						break;
					case 7 :
						yield("MatchUtil.instance.populate()[101..150]")
						break;
					case 8 : 
						yield("MatchUtil.instance.populate()[150..-1]")
						break;
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}