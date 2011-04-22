/**
 * 
 */
package org.petank.server

import org.petank.server.dao.DAOManager
import org.petank.server.model.PetankUser;

/**
 * @author jlandure
 *
 */
@Singleton
class PetankUserUtil {

	static PetankUserUtil getInstance() {
		return instance
	}
	
	private static def DEBUT_POINTS = 100
	
	List<PetankUser> populate() {
		def listUsers = new ArrayList<PetankUser>()
		listUsers << createPetankUser("GBE", "Guillaume Bouclé","euriware2011")
		listUsers << createPetankUser("ADE", "Antoine Delarue", "euriware2011")
		listUsers << createPetankUser("SHS", "Sébastien Harnois", "euriware2011")
		listUsers << createPetankUser("CLC", "Claude Le Guellec", "euriware2011")
		listUsers << createPetankUser("JLE", "Julien Landuré", "euriware2011")
		listUsers << createPetankUser("JND", "Julien Normand", "euriware2011")
		listUsers << createPetankUser("RST", "Rodolphe Singeot", "euriware2011")
		listUsers << createPetankUser("EBT", "Eric Biaulet", "euriware2011")
		listUsers << createPetankUser("BPT", "Benjamin Petetot", "euriware2011")
		listUsers << createPetankUser("AHS", "Alexis Hugues", "euriware2011")
		listUsers << createPetankUser("CBO", "Cédric Bertho", "euriware2011")
		listUsers << createPetankUser("GHT", "Guilherm Huchet", "euriware2011")
		listUsers << createPetankUser("JAY", "Julien Arbey", "euriware2011")
		listUsers << createPetankUser("FEE", "Florian Epinette", "euriware2011")
		listUsers << createPetankUser("FRT", "Florian Robert", "euriware2011")
		listUsers << createPetankUser("PSR", "Pierre Sehier", "euriware2011")
		listUsers << createPetankUser("MSI", "Marouane Skouri", "euriware2011")
		listUsers << createPetankUser("NHT", "Nicolas Huet", "euriware2011")
		listUsers << createPetankUser("NBN", "Nicolas Bizien", "euriware2011")
		listUsers << createPetankUser("BRD", "Bruno Roulland", "euriware2011")
		listUsers << createPetankUser("TLT", "Thierry Le Conniat", "euriware2011")
		listUsers << createPetankUser("GPE", "Guillaume Prime", "euriware2011")
		listUsers << createPetankUser("SLE", "Samuel Lemoine", "euriware2011")
		listUsers << createPetankUser("THN", "Thierry Hennequin", "euriware2011")
		listUsers << createPetankUser("YTU", "Youenn Thareau", "euriware2011")
		listUsers << createPetankUser("VLY", "Véronique Le Jolly", "euriware2011")
		listUsers << createPetankUser("PMT", "Pierre Mahot", "euriware2011")
		listUsers << createPetankUser("VPT", "Vincent Poilvert", "euriware2011")
		listUsers << createPetankUser("FBN", "Frédéric Brinquin", "euriware2011")
		listUsers << createPetankUser("ADN", "Arnaud Doyen", "euriware2011")
		listUsers << createPetankUser("ADN", "Arnaud Doyen", "euriware2011")
		
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
	
	List getUserOrderedByGroupName(groupName) {
		def petankGroup = PetankGroupUtil.instance.getGroup(groupName)
		List users = DAOManager.instance.getUserOrderedByGroupName(PetankUser.class, petankGroup.id)
		List players = new ArrayList();
		for(PetankUser user : users) {
			if(user.getPartiesJoues() != 0) {
				players.add(user);
			}
		}
		return players
	}
	
	PetankUser getUserById(id) {
		return DAOManager.instance.get(PetankUser.class, id)
	}
	
	PetankUser getUserById(id, listUsers) {
		def user
		int i = 0
		// normalement tjs ok car un user est forcément dans le groupe !
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
