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
import org.petank.server.StatUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.dao.DAOManager
import org.petank.server.model.Bareme;
import org.petank.server.model.Match;
import org.petank.server.model.PetankUser;

/**
 * @author jlandure
 *
 */
public class PlayerResource extends DefaultGroupResource {

	def expireCache() {
		return true
	}
	
	def playerName, player
	
	def PlayerResource(Context context, Request request, Response response) {
		super(context, request, response)
		playerName = (String) request.getAttributes().get("player")
	}
	
	def prepareObjects() {
		player = PetankUserUtil.instance.getUser(playerName, groupName)
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
		PetankUser player = PetankUserUtil.instance. createPetankUser(name, petankName, groupName)
		DAOManager.instance.save(player)
		DefaultResource.MEMCACHE.clear()
		getResponse().redirectSeeOther(new Reference(getRootUri()+"/$groupName/classement"));
	}
	
    def toXML(xml, writer) {
    	if(player == null) {
			quit();return
		}
		xml.player(name:player.petankName, points:player.points, joues:player.partiesJoues, gagnes:player.partiesGagnes, perdus:player.partiesPerdus, pointsMoyen:"${String.format('%.2f', (player.totalPoints / (player.partiesJoues ?: 1)))}") {
			page(name:"Chart", uri:getRootUri()+"/${groupName}/${playerName}/chart")
			
		}
		return writer.toString()
    }

	def toHTML(html, writer) {
		if(player == null) {
			quit();return
		}
		def user 
		html.html {
		    head {
		        title "Détail de "+player.petankName+" | P\u00E9tank Party"
		    }
		    body {
		    	h1 "Détail de "+player.name
		    	p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        p {
		    		a(href:getRootUri()+"/${groupName}/match",  "Matchs")
		    		yield " / " 
		    		a(href:getRootUri()+"/${groupName}/classement",  "Classement")
		    	}
		        br()
				h2 "Informations générales de "+player.petankName
		        ol {
		    				li(style:"list-style-type:circle;") {
		    					span "Place: "//${player.classement}"
		        			}
							li(style:"list-style-type:circle;") {
								span "Nom:" 
								span {
				    				a(href:getRootUri()+"/${groupName}/${player.name}/chart", target:"_blank", "${player.petankName}")
				    			}
		        			}
							li(style:"list-style-type:circle;") {
								span "Points: ${player.points}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Jou\u00E9s: ${player.partiesJoues}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Gagn\u00E9s: ${player.partiesGagnes}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Perdus: ${player.partiesPerdus}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Points par match: ${String.format('%.2f', (player.totalPoints / (player.partiesJoues ?: 1)))}"
		        			}
							 li(style:"list-style-type:circle;") { 
								span "Fanny gagn\u00E9s: ${player.fannyGagnes}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Fanny encaiss\u00E9s: ${player.fannyPerdus}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Nb victoire normale: ${player.victoireNormale}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Nb victoire anormale: ${player.victoireAnormale}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Nb d\u00E9faite normale: ${player.defaiteNormale}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Nb d\u00E9faite anormale: ${player.defaiteAnormale}"
		        			}
							li(style:"list-style-type:circle;") { 
								span "Nb Match officiel: ${player.nbMatchOfficiel}"
		        			}
		    }
				
			def points = MatchUtil.instance.getPlayerEvolution(player)
			def allpoints = points.join(",")
			h2 "Evolution de "+player.petankName
			br()
			img(src:"http://chart.apis.google.com/chart?chs=500x200&cht=lc&chds=0,250&chxt=y&chxl=0:|0|50|100|150|200|250&chxp=0,50,100,150,200,250|&chtt=${player.name}&chd=t:100.0,${allpoints}", alt:"Chart ${player.name}")
			
			br()
			h2 "Statistiques de "+player.petankName
			br()
			
			//def teamGagnant, teamPerdant;
			def (teamGagnant, teamPerdant) = StatUtil.instance.getBestTeamForPlayer(player)
			def team
			def users;
			h3 "Meilleur équipe"
			ol {
				teamGagnant.each{
					team = it
					li(style:"list-style-type:circle;") {
						span "Equipe: " + PetankUserUtil.instance.getUsersNameFromIdInString(team.key) + " : " + team.value + " victoires"
					}
				}
			}
			
			br()
			h3 "Mauvaise équipe"
			ol {
				teamPerdant.each{
					team = it
					li(style:"list-style-type:circle;") {
						span "Equipe: " + PetankUserUtil.instance.getUsersNameFromIdInString(team.key) + " : " + team.value + " défaites"
					}
				}
			}
			
		} // body
		}//html
		return writer.toString();
	}

}
