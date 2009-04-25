import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;
import groovy.xml.MarkupBuilder;

def listBaremes = BaremeUtil.populate();
int i = 1;
def bareme 
html.setDoubleQuotes(true)
html.html {
    head {
        title "Bareme P\u00E9tank Party"
    }
    body {
    	h1 "Bareme"
        p "v0.2-beta"
        p {
    		a(href:"/classement.groovy",  "Classement")
    		yield " / " 
    		a(href:"/match.groovy",  "Matchs")
    	}
        
        table(border:1, cellpadding:"15px", bordercolor:"black") {
    		thead {
    			tr {
    				td(class:"special", "Ecart")
        			td(class:"special", "Victoire normale")
        			td(class:"special", "D\u00E9faite normale")
        			td(class:"special", "Victoire anormale")
        			td(class:"special", "D\u00E9faite anormale")
    			}
    		}
    		tbody {
    			listBaremes.each{
    				bareme = it
		    		tr {
		    			td(class:"special", "${bareme.minimum as Integer} - ${bareme.maximum as Integer}")
		    			td(class:"special", "${bareme.victoireNormale}")
		    			td(class:"special", "${bareme.defaiteNormale}")
		    			td(class:"special", "${bareme.victoireAnormale}")
		    			td(class:"special", "${bareme.defaiteAnormale}")
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

