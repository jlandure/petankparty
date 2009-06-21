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
import org.petank.client.model.TypeMatch;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.DateUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.PetankPlaceUtil;
/**
 * @author jlandure
 *
 */
public class MatchResource extends DefaultGroupResource {
	
	def MatchResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	@Override
	boolean allowPost() {
		return true
	}
	
	@Override
	void acceptRepresentation(Representation entity) {
		// Parse the given representation and retrieve pairs of  
		// "name=value" tokens.  
		Form form = new Form(entity)
		def jour = form.getFirstValue("jour")
		def score1 = (form.getFirstValue("score1") as Float)
		def score2 = (form.getFirstValue("score2") as Float)
		def idPlace = form.getFirstValue("place")
		def place = PetankPlaceUtil.instance.getPlaceById(idPlace).name
		def type
		if(form.getFirstValue("officiel") != null) {
			type=TypeMatch.OFFICIEL
		} else {
			type=TypeMatch.NON_OFFICIEL
		}
		def player1 = []
		def player2 = []
		form.getNames().each {
			if(it?.startsWith("player1_")) {
				player1 << it.split("_")[1]
			}
			if(it?.startsWith("player2_")) {
				player2 << it.split("_")[1]
			}
		}
		
		Match match = MatchUtil.instance.createMatch(groupName, player1, player2, score1, score2, jour, place, type)
		// Register the new match
		DAOManager.instance.save(match)
		group.matchApplied = false
	}
	
	
	def toXML(xml, writer) {
		
		int i = 1;
		
		xml.matchs(group:'euriware') {
			listMatchs[-1..0].each{ ma ->
				match(id:i, date:DateUtil.instance.getDateToFrString(ma.jour), bareme:ma.bareme.id)
				i++
			}
		}
		return writer.toString()
	}
	
	def toHTML(html, writer) {
		
		int i = 1;
		def match
		def bareme
		def place
		html.html {
			head {
				title "Matchs P\u00E9tank Party"
			}
			body {
				h1 "Matchs"
				p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
				p { 
					a(href:"/${groupName}/classement",  "Classement")
					yield " / " 
					a(href:"/bareme",  "Bareme")
				}
				
				table(border:1, cellpadding:"2px", bordercolor:"black") {
					thead {
						tr {
							td(class:"special", "Date")
							td(class:"special", "Equipe 1")
							td(class:"special", "Equipe 2")
							td(class:"special", "Score Equipe 1")
							td(class:"special", "Score Equipe 2")
							td(class:"special", "Type Match")
							td(class:"special", "Type Victoire")
							td(class:"special", "Classement Equipe 1")
							td(class:"special", "Classement Equipe 2")
							td(class:"special", "Bareme Utilis\u00E9")
							td(class:"special", "Lieu")
						}
					}
					tbody {
						if(listMatchs != null) {
							listMatchs[-1..0].each{
								match = it
								bareme = BaremeUtil.getInstance().getBaremeById(match.idBareme);
								place = PetankPlaceUtil.getInstance().getPlaceById(match.idPlace);
								tr {
									td(class:"special", "${DateUtil.instance.getDateToFrString(match.jour)}")
									td(class:"special") {
										ul {
											MatchUtil.instance.getPlayers(match.player1).each{
												li(it.name + " [" + MatchUtil.instance.getPlayerPoints(match.playersWithPoints, it.id as String) + "]")
											}
										}
									}
									td(class:"special") {
										ul {
											MatchUtil.instance.getPlayers(match.player2).each{
												li(it.name + " [" + MatchUtil.instance.getPlayerPoints(match.playersWithPoints, it.id as String) + "]")
											}
										}
									}
									td(class:"special", "${match.score1 as Integer}")
									td(class:"special", "${match.score2 as Integer}")
									td(class:"special", "${match.typeMatch}")
									td(class:"special", "${match.typeVictoire}")
									td(class:"special", "${String.format('%.2f', match.point1)}")
									td(class:"special", "${String.format('%.2f', match.point2)}")
									td(class:"special") {
										yield "${bareme.minimum} - ${bareme.maximum}"
										br()
										if(match.isNormal()) {
											yield "[${bareme.victoireNormale} / ${bareme.defaiteNormale}]"
										} else {
											yield "[${bareme.victoireAnormale} / ${bareme.defaiteAnormale}]"
										}
									}
									td(class:"special") {
										a(href:("/place/"+place.name), target:"_blank", PetankPlaceUtil.instance.getPlaceName(place))
									}
								}
							}
						}//not null listMatchs
					}
				}
			}
		}
		return writer.toString();
	}
	
}