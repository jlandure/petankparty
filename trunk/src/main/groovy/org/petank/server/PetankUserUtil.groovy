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
	private static def DEBUT_POINTS = 650
	
	static List<PetankUser> populate() {
		if(listUsers == null) {
			listUsers = new ArrayList<PetankUser>()
			listUsers << createPetankUser("GBE", "Guillaume Bouclé","euriware")
			listUsers << createPetankUser("ADE", "Antoine Delarue", "euriware")
			listUsers << createPetankUser("SHS", "Sébastien Harnois", "euriware")
			listUsers << createPetankUser("CLC", "Claude Le Guellec", "euriware")
			listUsers << createPetankUser("JLE", "Julien Landuré", "euriware")
			listUsers << createPetankUser("JND", "Julien Normand", "euriware")
			listUsers << createPetankUser("RST", "Rodolphe Singeot", "euriware")
			listUsers << createPetankUser("EBT", "Eric Biaulet", "euriware")
			listUsers << createPetankUser("BPT", "Benjamin Petetot", "euriware")
			listUsers << createPetankUser("HDG", "Hugues Dubourg", "euriware")
			listUsers << createPetankUser("CBO", "Cédric Bertho", "euriware")
			listUsers << createPetankUser("GHT", "Guilherm Huchet", "euriware")
			listUsers << createPetankUser("JAY", "Julien Arbey", "euriware")
			listUsers << createPetankUser("FEE", "Florian Epinette", "euriware")
			listUsers << createPetankUser("FRT", "Florian Robert", "euriware")
			listUsers << createPetankUser("SEN", "Stanislav Emelin", "euriware")
			listUsers << createPetankUser("PSR", "Pierre Sehier", "euriware")
			listUsers << createPetankUser("MSI", "Marouane Skouri", "euriware")
			
			listUsers << createPetankUser("JRO", "Jean-Ro", "orvault")
			listUsers << createPetankUser("TYN", "Martine", "orvault")
			listUsers << createPetankUser("JU", "Julien", "orvault")
			listUsers << createPetankUser("VAN", "Vanessa", "orvault")
			listUsers << createPetankUser("DOM", "Dominique", "orvault")
			listUsers << createPetankUser("AXE", "Axel", "orvault")
			listUsers << createPetankUser("PLO", "Polo", "orvault")
			listUsers << createPetankUser("PDO", "Pedro", "orvault")
			listUsers << createPetankUser("GIL", "Gilbert", "orvault")
		}
		return listUsers
	}
	
	static PetankUser createPetankUser(name, petankName, group, dateString=null, points=PetankUserUtil.DEBUT_POINTS) {
		def user = new PetankUser(name:name, petankName:petankName, points:points)
		user.id = listUsers.size()
		user.group = PetankGroupUtil.getGroup(group)
		user.group.listUsers << user
		return user
	}

	static PetankUser getUser(name, group) {
		def c
		def petankGroup = PetankGroupUtil.getGroup(group)
		listUsers.each {
			if(name.equalsIgnoreCase(it.name) && petankGroup.id == it.group.id) {
				c = it
			}
		}
		return c
	}
	
	static List getUsers(names, group) {
		def users = []
		names.split(",").each{
			users << PetankUserUtil.getUser(it, group)
		}
		return users
	}
	
	static def getUserByGroupName(group) {
		def c = []
		def petankGroup = PetankGroupUtil.getGroup(group)
		listUsers.each {
			if(petankGroup.id == it.group.id) {
				c << it
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
	
//	static PetankUser[] getTest1() {
//		return [listUsers[0], listUsers[2]]
//	}
//	
//	static PetankUser[] getTest2() {
//		return [listUsers[1], listUsers[3]]
//	}
	
	static List<PetankUser> sortByPoint(players) {
		def mc= [compare:{a,b-> a?.points>b?.points? -1: a?.points<b?.points? 1: a?.name.compareTo(b?.name)}] as Comparator
		players.sort(mc)
		return players
	}
}
