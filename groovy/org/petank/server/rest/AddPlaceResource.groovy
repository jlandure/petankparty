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
public class AddPlaceResource extends DefaultResource {
	
	def AddPlaceResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def toHTML(html, writer) {
		html.html {
			head {
				title "Ajout d'un Boulodrome P\u00E9tank Party"
			}
			body {
				h1 "Ajout d'un Boulodrome"
				p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
				p { 
					a(href:getRootUri()+"/index",  "Retour")
				}
				
				form(method:"post", action:getRootUri()+"/place", id:"placeForm") {
					ul {
										li {
											yield "Identifiant : "
											input(size:10, type:"text", name:"name", id:"name")
										}
										li {
											yield "Nom : "
											input(size:50, type:"text", name:"petankName", id:"petankName")
										}
										li {
											yield "Commentaire : "
											input(size:50, type:"text", name:"content", id:"content")
										}
										li {
											yield "Latitude : "
											input(size:10, type:"text", name:"lat", id:"lat")
										}
										li {
											yield "Longitude : "
											input(size:10, type:"text", name:"lng", id:"lng")
										}
										br()
										br()
										br()
										input(type:"submit", value:"sauvegarder")
										br()
										input(type:"reset", value:"effacer")
							}
						
								
				}//form
			}//body
		}
		return writer.toString();
	}
	
}
