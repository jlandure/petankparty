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
public class MatchResource extends DefaultResource {

	def MatchResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
    def toXML() {
    	def listUsers = PetankUserUtil.populate();
		def listMatchs = MatchUtil.populate();
		def listBaremes = BaremeUtil.populate();

		if(!MatchUtil.APPLIED) {
			listMatchs.each{MatchUtil.applyMatch(it)}
			listUsers.each{it.evolution = MatchUtil.getPlayerEvolution(it)}
			listUsers = PetankUserUtil.sortByPoint(listUsers);
			MatchUtil.APPLIED = true;
		}

		int i = 1;
    	def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		xml.setDoubleQuotes(true)

		xml.matchs(group:'euriware') {
			listMatchs[-1..0].each{ ma ->
				match(id:i, date:MatchUtil.getDateToFrString(ma.jour), bareme:ma.bareme.id)
				i++
			}
		}
		return writer.toString()
    }

	def toHTML() {
		def listUsers = PetankUserUtil.populate();
		def listMatchs = MatchUtil.populate();
		def listBaremes = BaremeUtil.populate();

		if(!MatchUtil.APPLIED) {
			listMatchs.each{MatchUtil.applyMatch(it)}
			listUsers.each{it.evolution = MatchUtil.getPlayerEvolution(it)}
			listUsers = PetankUserUtil.sortByPoint(listUsers);
			MatchUtil.APPLIED = true;
		}

		int i = 1;
		def match
		def writer = new StringWriter()
		def html = new MarkupBuilder(writer)
		html.setDoubleQuotes(true)
		html.html {
			head {
		        title "Matchs P\u00E9tank Party"
		    }
		    body {
		    	h1 "Matchs"
		        p "v0.3-beta"
		        p { 
		    		a(href:"/classement",  "Classement")
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
		    			}
		    		}
		    		tbody {
		    			listMatchs[-1..0].each{
		    				match = it
				    		tr {
				    			td(class:"special", "${MatchUtil.getDateToFrString(match.jour)}")
				    			td(class:"special") {
				    				ul {
				    					MatchUtil.getPlayers(match.player1).each{
				    						li(it.name + " [" + MatchUtil.getPlayerPoints(match.playersWithPoints, it.id as String) + "]")
				    					}
				    				}
				    			}
				    			td(class:"special") {
				    				ul {
				    					MatchUtil.getPlayers(match.player2).each{
				    						li(it.name + " [" + MatchUtil.getPlayerPoints(match.playersWithPoints, it.id as String) + "]")
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
				    				a(href:"/bareme", "${match.bareme.minimum} - ${match.bareme.maximum}")
				    			}
				    		}
		    			}
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
