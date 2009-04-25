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
	listUsers.each{it.evolution = MatchUtil.getPlayerEvolution(it)}
	listUsers = PetankUserUtil.sortByPoint(listUsers);
	MatchUtil.APPLIED = true;
}

int i = 1;
def user 
html.setDoubleQuotes(true)
html.html {
    head {
        title "Classement P\u00E9tank Party"
    }
    body {
    	h1 "Classement"
        p "v0.2-beta"
        p {
    		a(href:"/match.groovy",  "Matchs")
    		yield " / " 
    		a(href:"/bareme.groovy",  "Bareme")
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
        			td(class:"special", "Total points")
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
		    			td(class:"special", "$i")
		    			td(class:"special2") {
		    				a(href:"${user.evolution}", target:"_blank", "${user.name}")
		    			}
		    			td(class:"special", "${user.points}")
		    			td(class:"special", "${user.partiesJoues}")
		    			td(class:"special", "${user.partiesGagnes}")
		    			td(class:"special", "${user.partiesPerdus}")
		    			td(class:"special", "${user.totalPoints}")
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

        
    		
//    		evolution = it.evolution;
//    		name = it.name
//    		p {
//    			  yield("$i - ")
//    		      
//    		      a(href:"http:groovy.codehaus.org", "\"Groovy\"")
//    		      p("${user..points}")
//    		    }
//    		p "$i - ${it.name} - ${it.points}"
//    		println "$i - ";
//    		
//    		a(href:"${evolution}") {
//    			"${name}"
//    		}
//    		println  " - ${it.points}";
//    		
//    	}
    }
}

//def html = html.html() {
//  head {
//    title('My Title')
//  }
//  body(bgcolor:"#FFFFFF") {
//    p {
//      a(href:"http://groovy.codehaus.org", "Groovy")
//      a(href:"http://groovy.codehaus.org", "\"Groovy\"")
//    }
//    table {
//      tr {
//        td(class:"special", "cell value")
//        td(class:"special2", "another cell value")
//      }
//    }
//  }
//}
//println "${html}"

