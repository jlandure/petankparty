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

	def PopulateResource(Context context, Request request, Response response) {
		super(context, request, response)
		populate()
	}
	
	def populate() {
		def loadObjects = {objects -> objects.each{DAOManager.instance.save(it)}}
		loadObjects(BaremeUtil.instance.populate())
		loadObjects(PetankGroupUtil.instance.populate())
		loadObjects(PetankPlaceUtil.instance.populate())
		loadObjects(PetankUserUtil.instance.populate())
		loadObjects(MatchUtil.instance.populate())
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
		    	}
		    }
		}
		return writer.toString();
	}

}
