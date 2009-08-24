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
public class RedirectResource extends DefaultResource {

	def expireCache() {
		return true
	}
	
	def RedirectResource(Context context, Request request, Response response) {
		super(context, request, response)
		
		def userAgent = request.getClientInfo().getAgent()
		def isMobile = userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("Mobile")
		if(isMobile) {
			response.redirectSeeOther(new Reference("/mobile/Sampleiphoneapp.html"));
		} else {
			response.redirectSeeOther(new Reference("/index"));	
		}
	}

}
