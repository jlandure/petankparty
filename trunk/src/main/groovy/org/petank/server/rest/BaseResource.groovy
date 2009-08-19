/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder
import org.petank.server.PetankGroupUtil

import org.restlet.Application
import org.restlet.Restlet
import org.restlet.routing.Router
import org.restlet.routing.Redirector
import org.restlet.security.Guard
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
		
		def userAgent = request.getClientInfo().getAgent()
		def isMobile = userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("Mobile")
		if(isMobile) {
			response.redirectSeeOther(new Reference("/mobile/Sampleiphoneapp.html"));
		}
	}
	
	def prepareObjects() {
		listGroups = PetankGroupUtil.instance.getGroups()
	}
	
	def toXML(xml, writer) {
		xml.groups() {
			group(name:"Euriware") {
				page(name:"Classement", uri:getRootUri()+"/euriware/classement")
				page(name:"Match", uri:getRootUri()+"/euriware/match")
				page(name:"Ajouter un match", uri:getRootUri()+"/euriware/match/add")
			}
		}
		return writer.toString()
    }
	
	def toHTML(html, writer) {
		
		def router = this.getApplication().getRoot()
		
		Guard guard1
        Guard guard2
        Redirector redirector1
        Redirector redirector2
        listGroups.each{
        	
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
		
		html.html {
			head {
		        title "P\u00E9tank Party"
		    }
		    body {
		    	h1 "P\u00E9tank Party"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        p {
		    		a(href:"/bareme",  "Bareme")
		    		
		    		listGroups.each {
		    			br()
			    		br()
			    		a(href:"/"+it.name+"/c", it.petankName)
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
