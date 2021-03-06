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
public class AddMatchResource extends DefaultGroupResource {
	
	def listUsers
	
	def AddMatchResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def prepareObjects() {
		listUsers = PetankUserUtil.instance.getUserByGroupName(groupName)
	}
	
	def toXML(xml, writer) {
		def listPlaces = []
		listPlaces << PetankPlaceUtil.instance.getPlace("souchais")
		listPlaces << PetankPlaceUtil.instance.getPlace("mainguais")
		listPlaces << PetankPlaceUtil.instance.getPlace("lorient1")
		xml.match(group:groupName, jour:DateUtil.instance.getNewDateToFrString(), uri:getRootUri()+"/${groupName}/match/add", next:getRootUri()+"/${groupName}/classement") {
			players {
				listUsers.each{ user ->
					player(name:user.name, label:user.petankName)
				}
			}
			places {
				listPlaces.each{ pl ->
					place(id:pl.id, label:PetankPlaceUtil.instance.getPlaceName(pl))
				}
			}
		}
		return writer.toString()
    }
	
	def toHTML(html, writer) {
		int i = 1;
		def match
		def user
		def listPlaces = []
		listPlaces << PetankPlaceUtil.instance.getPlace("souchais")
		listPlaces << PetankPlaceUtil.instance.getPlace("mainguais")
		listPlaces << PetankPlaceUtil.instance.getPlace("lorient1")
		def place
		html.html {
			head {
				title "Ajout d'un Match P\u00E9tank Party"
			}
			body {
				h1 "Ajout d'un Match"
				p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
				p { 
					a(href:getRootUri()+"/${groupName}/classement",  "Classement")
					yield " / " 
					a(href:getRootUri()+"/bareme",  "Bareme")
				}
				
				form(method:"post", action:getRootUri()+"/${groupName}/match", id:"matchForm") {
					input(type:"hidden", name:"type", value:"POST")
					table(border:1, cellpadding:"2px", bordercolor:"black") {
						thead {
							tr {
								td(class:"special", "Equipe 1")
								td(class:"special", "Equipe 2")
								td(class:"special", "Propriétés")
							}
						}
						tbody {
							tr {
								td(class:"special") {
									ul {
										listUsers.each{
											user = it
											li {
												input(type:"checkbox", name:"player1_"+user.name, id:"player1_"+user.name)
												label(for:"player1_"+user.name, user.petankName) 
											}
										}
									}
								}
								td(class:"special") {
									ul {
										listUsers.each{
											user = it
											li {
												input(type:"checkbox", name:"player2_"+user.name, id:"player2_"+user.name)
												label(for:"player2_"+user.name, user.petankName) 
											}
										}
									}
								}
								td(class:"special") {
									ul {
										li {
											yield "Date : "
											input(size:10, type:"text", name:"jour", id:"jour", value:DateUtil.instance.getNewDateToFrString())
										}
										li {
											yield "Score Equipe 1 : "
											input(size:5, type:"text", name:"score1", id:"score1")
										}
										li {
											yield "Score Equipe 2 : "
											input(size:5, type:"text", name:"score2", id:"score2")
										}
										li {
											label(for:"officiel",  "Officiel : ") 
											input(type:"checkbox", checked:"checked", name:"officiel", id:"officiel")
										}
										li {
											yield "Lieu : "
											select(name:"place", id:"place") {
												listPlaces.each {
													place = it
													option(value:place.id, PetankPlaceUtil.instance.getPlaceName(place))
												}
											}
										}
										br()
										br()
										br()
										li {
											input(type:"submit", value:"sauvegarder")
										}
										li {
											input(type:"reset", value:"effacer")
										}
									}
								}
							}
						}//tobdy
					}//table
				}//form
			}//body
		}
		return writer.toString();
	}
	
}
