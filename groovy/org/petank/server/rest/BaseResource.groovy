/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import org.petank.server.dao.DAOManager
import org.petank.server.model.PetankGroup;

import groovy.xml.MarkupBuilder

import org.petank.server.PetankGroupUtil

import org.restlet.Application
import org.restlet.Restlet
import org.restlet.routing.Router
import org.restlet.routing.Redirector
import org.restlet.data.ChallengeScheme
/**
 * @author jlandure
 *
 */
public class BaseResource extends DefaultResource {

	def listGroups
	
	def expireCache() {
		return true
	}
	
	def BaseResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def prepareObjects() {
		listGroups = PetankGroupUtil.instance.getGroups()
	}
	
	@Override
	boolean allowPost() {
		return true
	}
	
	@Override
	void acceptRepresentation(Representation entity) {
		Form form = new Form(entity)
		def name = form.getFirstValue("name")
		def petankName = form.getFirstValue("petankName")
		
		// Register the new player
		PetankGroup group = PetankGroupUtil.instance.createPetankGroup(name, petankName)
		DAOManager.instance.save(group)
		DefaultResource.MEMCACHE.clear()
		getResponse().redirectSeeOther(new Reference(getRootUri()+"/index"));
	}
	
	def toXML(xml, writer) {
		xml.groups() {
			group(name:"Euriware") {
				page(name:"Classement", uri:getRootUri()+"/euriware2011/classement")
				page(name:"Match", uri:getRootUri()+"/euriware2011/match")
				page(name:"Ajouter un match", uri:getRootUri()+"/euriware2011/match/add")
				//page(name:"Ajouter un groupe", uri:getRootUri()+"/addGroup")
				//page(name:"Ajouter un boulodrome", uri:getRootUri()+"/addPlace")
			}
			bareme(name:"Bareme", uri:getRootUri()+"/bareme")
		}
		return writer.toString()
    }
	
	def toHTML(html, writer) {
		
		html.html {
			head {
		        title "P\u00E9tank Party"
		    }
		    body {
		    	h1 "P\u00E9tank Party"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        p {
		    		a(href:getRootUri()+"/bareme",  "Bareme")
					yield " / "
					a(href:getRootUri()+"/addGroup",  "Ajouter un groupe")
					yield " / "
					a(href:getRootUri()+"/addPlace",  "Ajouter un boulodrome")
		    		listGroups.each {
		    			br()
			    		br()
			    		a(href:getRootUri()+"/"+it.name+"/classement", it.petankName)
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
