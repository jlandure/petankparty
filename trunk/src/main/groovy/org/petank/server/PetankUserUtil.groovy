/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankUser;
import org.petank.server.dao.DAOManager

/**
 * @author jlandure
 *
 */
@Singleton
class PetankUserUtil {

	static PetankUserUtil getInstance() {
		return instance
	}
	
	private static def DEBUT_POINTS = 650
	
	List<PetankUser> populate() {
		def listUsers = new ArrayList<PetankUser>()
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
		listUsers << createPetankUser("NHT", "Nicolas Huet", "euriware")
		listUsers << createPetankUser("NBN", "Nicolas Bizien", "euriware")
		listUsers << createPetankUser("BRD", "Bruno Roulland", "euriware")
		
		
		listUsers << createPetankUser("JRO", "Jean-Ro", "orvault")
		listUsers << createPetankUser("TYN", "Martine", "orvault")
		listUsers << createPetankUser("JU", "Julien", "orvault")
		listUsers << createPetankUser("VAN", "Vanessa", "orvault")
		listUsers << createPetankUser("DOM", "Dominique", "orvault")
		listUsers << createPetankUser("AXE", "Axel", "orvault")
		listUsers << createPetankUser("PLO", "Polo", "orvault")
		listUsers << createPetankUser("PDO", "Pedro", "orvault")
		listUsers << createPetankUser("GIL", "Gilbert", "orvault")
		return listUsers
	}
	
	def resetUser(user) {
		user.points = DEBUT_POINTS
		user.partiesJoues = 0
		user.partiesGagnes = 0
		user.partiesPerdus = 0
		user.totalPoints = 0
		user.fannyGagnes = 0
		user.fannyPerdus = 0
		user.victoireNormale = 0
		user.victoireAnormale = 0
		user.defaiteNormale = 0
		user.defaiteAnormale = 0
		user.dayBefore = null
		user.placeDayBefore = 0
		user.pointsDayBefore = null
		user.nbMatchOfficiel = 0
		return user
	}
	
	PetankUser createPetankUser(name, petankName, group, dateString=null, points=PetankUserUtil.DEBUT_POINTS) {
		def user = new PetankUser(name:name, petankName:petankName, points:points)
		user.idGroup = PetankGroupUtil.instance.getGroup(group).id
		return user
	}

	def getUsers() {
		return DAOManager.instance.getAll(PetankUser.class)
	}
	
	PetankUser getUser(name, group) {
		//TODO filtrer par groupe : requete SQL
		return DAOManager.instance.getObjectByName(PetankUser.class, name)		
	}
	
	List getUsers(names, group) {
		def users = []
		names.split(",").each{
			users << PetankUserUtil.instance.getUser(it, group)
		}
		return users
	}
	
	List getUserByGroupName(groupName) {
		def petankGroup = PetankGroupUtil.instance.getGroup(groupName)
		return DAOManager.instance.getAllFromIdGroup(PetankUser.class, petankGroup.id)
	}
	
	PetankUser getUserById(id) {
		return DAOManager.instance.get(PetankUser.class, id)
	}
	
	PetankUser getUserById(id, listUsers) {
		def user
		int i = 0
		while(user == null) {
			if(listUsers[i].id == id) {
				user = listUsers[i]
			}
			i++
		}
		return user
	}
	
	def getClassementUser(user) {
		return user.classement
	}
	
	List<PetankUser> sortByPoint(players) {
		def mc= [compare:{a,b-> a?.points>b?.points? -1: a?.points<b?.points? 1: a?.name.compareTo(b?.name)}] as Comparator
		players.sort(mc)
		int i = 1
		players.each{
			it.classement = i
			i++
		}
		return players
	}
}
