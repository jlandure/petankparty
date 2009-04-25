import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;
import groovy.xml.MarkupBuilder;

def listUsers = PetankUserUtil.populate();
def listMatchs = MatchUtil.populate();
def listBaremes = BaremeUtil.populate();

if(!MatchUtil.APPLIED) {
	listMatchs.each{MatchUtil.applyMatch(it)}
	MatchUtil.APPLIED = true;
}

def match 
html.setDoubleQuotes(true)
html.html {
    head {
        title "Matchs P\u00E9tank Party"
    }
    body {
    	h1 "Match"
        p "v0.2-beta"
        p {
    		a(href:"/classement.groovy",  "Classement")
    		yield " / " 
    		a(href:"/bareme.groovy",  "Bareme")
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
    			listMatchs.each{
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
		    			td(class:"special") {
		    				if(match.coefficient == 1) {
		    					p "Officiel"
		    				} else {
		    					p "Non Officiel"
		    				}
		    			}
		    			td(class:"special", "${match.typeVictoire}")
		    			td(class:"special", "${match.point1}")
		    			td(class:"special", "${match.point2}")
		    			td(class:"special") {
		    				a(href:"/bareme.groovy", "${match.bareme.minimum} - ${match.bareme.maximum}")
		    			}
		    		}
    			}
    		}
    	}
    }
}

