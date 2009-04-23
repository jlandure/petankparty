import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;

def listUsers = PetankUserUtil.populate();
def listMatchs = MatchUtil.populate();
def listBaremes = BaremeUtil.populate();
listMatchs.each{MatchUtil.applyMatch(it)}
listUsers.each{it.evolution = MatchUtil.getPlayerEvolution(it)}
listUsers = PetankUserUtil.sortByPoint(listUsers);
int i = 1;
def evolution
def name

html.html {
    head {
        title "Classement P\u00E9tank Party"
    }
    body {
    	h1 "Classement"
        p "v0.1-beta"
        p "23/04/09 14h00"
        listUsers.each{
    		p "$i - ${it.name} - ${it.points}"
//    		println "$i - ";
//    		evolution = it.evolution;
//    		name = it.name
//    		a(href:evolution) {
//    			name
//    		}
//    		println  " - ${it.points}";
    		i++
    	}
    }
}