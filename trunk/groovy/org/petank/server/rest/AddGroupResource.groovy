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
public class AddGroupResource extends DefaultResource {
	
	def AddGroupResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def toHTML(html, writer) {
		html.html {
			head {
				title "Ajout d'un Groupe P\u00E9tank Party"
			}
			body {
				h1 "Ajout d'un Group"
				p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
				p { 
					a(href:getRootUri()+"/index",  "Retour")
				}
				
				form(method:"post", action:getRootUri()+"/index", id:"groupForm") {
						
										yield "Id du groupe : "
										input(size:25, type:"text", name:"name", id:"name")
										br()
										yield "Nom du groupe : "
										input(size:50, type:"text", name:"petankName", id:"petankName")
										br()
										br()
										li {
											input(type:"submit", value:"sauvegarder")
										}
										li {
											input(type:"reset", value:"effacer")
										}
				}//form
			}//body
		}
		return writer.toString();
	}
	
}
