/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankGroup;
import org.petank.server.PetankUserUtil;
import org.petank.server.StatUtil;
import org.petank.server.dao.DAOManager
/**
 * @author jlandure
 *
 */
@Singleton
class PetankGroupUtil {

	static PetankGroupUtil getInstance() {
		return instance
	}
	
	List<PetankGroup> populate() {
		def listGroups = new ArrayList<PetankGroup>()
		listGroups = new ArrayList<PetankGroup>()
		listGroups << createPetankGroup("euriware", "Euriware", "euriware")
		listGroups << createPetankGroup("orvault", "Orvault", "56")
		return listGroups
	}
	
	PetankGroup createPetankGroup(name, petankName, password, matchApplied=false) {
		def group = new PetankGroup(name:name, petankName:petankName, password:password, matchApplied:matchApplied)
		return group
	}
	
	def getGroups() {
		return DAOManager.instance.getAll(PetankGroup.class)
	}

	PetankGroup getGroup(name) {
		return DAOManager.instance.getObjectByName(PetankGroup.class, name)
	}
	
	PetankGroup getGroupById(id) {
		return DAOManager.instance.get(PetankGroup.class, id)
	}
	
	def prepareGroup(group) {
		def listMatchs = MatchUtil.instance.getMatchByGroupName(group.name)
		def listUsers = PetankUserUtil.instance.getUserByGroupName(group.name)
		def listUsersReset = []
		def listMatchsReset = []
		listUsers.each {
			listUsersReset << PetankUserUtil.instance.resetUser(it)
		}
		listMatchs.each {
			listMatchsReset << MatchUtil.instance.resetMatch(it)
		}
		listUsers = StatUtil.instance.applyMatchs(listMatchsReset, listUsersReset)
		group.matchApplied = true;
		return [listUsersReset, listMatchsReset]
	}
}
