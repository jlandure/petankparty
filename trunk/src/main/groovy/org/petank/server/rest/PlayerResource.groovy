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
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;

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
		player = PetankUserUtil.instance.getUser(playerName, groupName)
		if(player == null) {
			quit();return
		}
	}
	
    def toXML(xml, writer) {

		int i = 1;

		xml.player(name:player.petankName) {
//			listUsers.each{ play ->
//				player(place:i, nom:play.petankName, score:play.points)
//				i++
//			}
		}
		return writer.toString()
    }

	def toHTML(html, writer) {

		int i = 1;
		def user 
		html.html {
		    head {
		        title "Détail de "+player.name+" | P\u00E9tank Party"
		    }
		    body {
		    	h1 "Détail de "+player.name
		        p "V0.4.1-beta"
		        p {
		    		a(href:"/${groupName}/match",  "Matchs")
		    		yield " / " 
		    		a(href:"/${groupName}/classement",  "Classement")
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
				    			td(class:"special", "$i")
				    			td(class:"special2") {
				    				a(href:"${user.evolution}", target:"_blank", "${user.petankName}")
				    			}
				    			td(class:"special", "${user.points}")
				    			td(class:"special", "${user.partiesJoues}")
				    			td(class:"special", "${user.partiesGagnes}")
				    			td(class:"special", "${user.partiesPerdus}")
				    			td(class:"special", "${String.format('%.2f', (user.totalPoints / user.partiesJoues))}")
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
