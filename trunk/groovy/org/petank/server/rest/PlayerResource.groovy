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
		        title "Détail de "+player.name+" | P\u00E9tank Party"
		    }
		    body {
		    	h1 "Détail de "+player.name
		    	p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        p {
		    		a(href:getRootUri()+"/${groupName}/match",  "Matchs")
		    		yield " / " 
		    		a(href:getRootUri()+"/${groupName}/classement",  "Classement")
		    	}
		        
		        table(border:1, cellpadding:"15px", bordercolor:"black") {
		    		thead {
		    			tr {
		    				td(class:"special", "Place")
		        			td(class:"special", "Nom")
		        			td(class:"special", "Points")
		        			td(class:"special", "Jou\u00E9s")
		        			td(class:"special", "Gagn\u00E9s")
		        			td(class:"special", "Perdus")
		        			td(class:"special", "Points par match")
		        			td(class:"special", "Fanny gagn\u00E9s")
		        			td(class:"special", "Fanny encaiss\u00E9s")
		        			td(class:"special", "Nb victoire normale")
		        			td(class:"special", "Nb victoire anormale")
		        			td(class:"special", "Nb d\u00E9faite normale")
		        			td(class:"special", "Nb d\u00E9faite anormale")
		        			td(class:"special", "Nb Match officiel")
		    			}
		    		}
		    		tbody {
		    				user = player
				    		tr {
				    			td(class:"special", "${user.classement}")
				    			td(class:"special2") {
				    				a(href:getRootUri()+"/${groupName}/${user.name}/chart", target:"_blank", "${user.petankName}")
				    			}
				    			td(class:"special", "${user.points}")
				    			td(class:"special", "${user.partiesJoues}")
				    			td(class:"special", "${user.partiesGagnes}")
				    			td(class:"special", "${user.partiesPerdus}")
				    			td(class:"special", "${String.format('%.2f', (user.totalPoints / (user.partiesJoues ?: 1)))}")
				    			td(class:"special", "${user.fannyGagnes}")
				    			td(class:"special", "${user.fannyPerdus}")
				    			td(class:"special", "${user.victoireNormale}")
				    			td(class:"special", "${user.victoireAnormale}")
				    			td(class:"special", "${user.defaiteNormale}")
				    			td(class:"special", "${user.defaiteAnormale}")
				    			td(class:"special", "${user.nbMatchOfficiel}")
				    		}
		    		}
		    	}//fin table
		    }
		}
		return writer.toString();
	}

}
