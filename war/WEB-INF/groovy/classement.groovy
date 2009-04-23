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
listUsers = PetankUserUtil.sortByPoint(listUsers);
int i = 1;


html.html {
    head {
        title "Classement Pétank Party"
    }
    body {
    	h1 "Classement"
        p "v0.1-beta"
        listUsers.each{
    		p "$i - ${it.name} - ${it.points}"
    		i++
    	}
    }
}