/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.DateUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.PetankPlaceUtil;import org.petank.server.model.Bareme;
import org.petank.server.model.Match;
import org.petank.server.model.PetankUser;

/**
 * @author jlandure
 *
 */
public class DelMatchResource extends DefaultGroupResource {
	
	def idMatch
	
	def DelMatchResource(Context context, Request request, Response response) {
		super(context, request, response)
		idMatch = (String) request.getAttributes().get("idMatch")
	}
	
	def prepareObjects() {
		
	}
	
	def toHTML(html, writer) {
		html.html {
			head {
				title "Delete d'un Match P\u00E9tank Party"
			}
			body {
				h1 "Delete d'un Match"
				p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
				p { 
					a(href:getRootUri()+"/${groupName}/classement",  "Classement")
					yield " / " 
					a(href:getRootUri()+"/${groupName}/match",  "Matchs")
					yield " / "
					a(href:getRootUri()+"/bareme",  "Bareme")
				}
				
				form(method:"post", action:getRootUri()+"/$groupName/match", id:"matchForm") {
					label("Are you sure to delete this match ?")
					br()
					input(type:"hidden", name:"type", value:"DELETE")
					input(type:"hidden", name:"idMatch", value:"$idMatch")
					input(type:"submit", value:"Delete")
				}//form
			}//body
		}
		return writer.toString();
	}
	
}
