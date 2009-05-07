/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankUser;

/**
 * @author jlandure
 *
 */
@Singleton
class PetankUserUtil{

	private static def listUsers
	
	static List<PetankUser> populate() {
		if(listUsers == null) {
			listUsers = new ArrayList<PetankUser>(15)
			listUsers << new PetankUser(id:1 , name:"GBE", petankName:"Guillaume Bouclé", points:650)
			listUsers << new PetankUser(id:2 , name:"ADE", petankName:"Antoine Delarue", points:650)
			listUsers << new PetankUser(id:3 , name:"SHS", petankName:"Sébastien Harnois", points:650)
			listUsers << new PetankUser(id:4 , name:"CLC", petankName:"Claude Le Guellec", points:650)
			listUsers << new PetankUser(id:5 , name:"JLE", petankName:"Julien Landuré", points:650)
			listUsers << new PetankUser(id:6 , name:"JND", petankName:"Julien Normand", points:650)
			listUsers << new PetankUser(id:7 , name:"RST", petankName:"Rodolphe Singeot", points:650)
			listUsers << new PetankUser(id:8 , name:"EBT", petankName:"Eric Biaulet", points:650)
			listUsers << new PetankUser(id:9 , name:"BPT", petankName:"Benjamin Petetot", points:650)
			listUsers << new PetankUser(id:10, name:"HDG", petankName:"Hugues Dubourg", points:650)
			listUsers << new PetankUser(id:11, name:"CBO", petankName:"Cédric Bertho", points:650)
			listUsers << new PetankUser(id:12, name:"GHT", petankName:"Guilherm Huchet", points:650)
			listUsers << new PetankUser(id:13, name:"JAY", petankName:"Julien Arbey", points:650)
			listUsers << new PetankUser(id:14, name:"FEE", petankName:"Florian Epinette", points:650)
			listUsers << new PetankUser(id:15, name:"FRT", petankName:"Florian Robert", points:650)
			listUsers << new PetankUser(id:16, name:"SEN", petankName:"Stanislav Emelin", points:650)
			listUsers << new PetankUser(id:17, name:"PSR", petankName:"Pierre Sehier", points:650)
			listUsers << new PetankUser(id:18, name:"MSI", petankName:"Marouane Skouri", points:650)
		}
		return listUsers
	}

	static PetankUser getUser(name) {
		def c
		listUsers.each {
			if(name == it.name) {
				c = it
			}
		}
		return c
	}
	
	static PetankUser getUserById(idt) {
		def c
		listUsers.each {
			if(idt == it.id) {
				c = it
			}
		}
		return c
	}
	
	static PetankUser[] getTest1() {
		return [listUsers[0], listUsers[2]]
	}
	
	static PetankUser[] getTest2() {
		return [listUsers[1], listUsers[3]]
	}
	
	static List<PetankUser> sortByPoint(players) {
		def mc= [compare:{a,b-> a?.points>b?.points? -1: a?.points<b?.points? 1: a?.name.compareTo(b?.name)}] as Comparator
		players.sort(mc)
		return players
	}
}
