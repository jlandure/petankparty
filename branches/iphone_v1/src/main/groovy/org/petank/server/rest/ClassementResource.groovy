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
public class ClassementResource extends DefaultGroupResource {

	def listUsers
	
	def ClassementResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def prepareObjects() {
		listUsers = PetankUserUtil.instance.getUserOrderedByGroupName(groupName)
	}
	
    def toXML(xml, writer) {
		int i = 1;
		xml.players(date:'15/05/09') {
			listUsers.each{ play ->
				player(place:i, nom:play.petankName, score:play.points)
				i++
			}
		}
		//return writer.toString()
		return writer.toString()
    }

	def toHTML(html, writer) {
		int i = 1;
		def user 
		html.html {
		    head {
		        title "Classement P\u00E9tank Party"
		    }
		    body {
		    	h1 "Classement"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        p {
		    		a(href:"/${groupName}/match",  "Matchs")
		    		yield " / " 
		    		a(href:"/bareme",  "Bareme")
		    		yield " / " 
		    		a(href:("/${groupName}/timeline/?players="+listUsers[0].name+","+listUsers[1].name+","+listUsers[2].name), target:"_blank", "Courbes d'\u00E9volution")
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
		    			listUsers.each{
		    				user = it
				    		tr {
				    			td(class:"special") {
				    				yield "$i"
				    				br()
				    				yield "[${(user.placeDayBefore - i)}]"
				    			}
				    			td(class:"special2") {
				    				a(href:"/${groupName}/${user.name}/chart", target:"_blank", "${user.petankName}")
				    			}
				    			td(class:"special") {
				    				yield "${user.points}"
				    				br()
				    				yield "[${(user.points - (user.pointsDayBefore ?: 0))}]"
				    			}
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
		    				i++
		    			}
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
