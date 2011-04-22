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
public class AddPlayerResource extends DefaultGroupResource {
	
	def AddPlayerResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def toHTML(html, writer) {
		html.html {
			head {
				title "Ajout d'un Player P\u00E9tank Party"
			}
			body {
				h1 "Ajout d'un Player"
				p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
				p { 
					a(href:getRootUri()+"/${groupName}/classement",  "Classement")
					yield " / " 
					a(href:getRootUri()+"/bareme",  "Bareme")
				}
				
				form(method:"post", action:getRootUri()+"/${groupName}/player", id:"playerForm") {
					ul {
										li {
											yield "Trigramme : "
											input(size:10, type:"text", name:"name", id:"name")
										}
										li {
											yield "Name : "
											input(size:50, type:"text", name:"petankName", id:"petankName")
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
