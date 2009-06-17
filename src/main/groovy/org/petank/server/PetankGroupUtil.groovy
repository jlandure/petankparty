/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankGroup;
import org.petank.server.StatUtil;

/**
 * @author jlandure
 *
 */
@Singleton
class PetankGroupUtil{

	private static def listGroups
	
	static List<PetankGroup> populate() {
		if(listGroups == null) {
			listGroups = new ArrayList<PetankGroup>()
			listGroups << createPetankGroup("euriware", "Euriware", "euriware")
			listGroups << createPetankGroup("orvault", "Orvault", "56")
		}
		return listGroups
	}
	
	static PetankGroup createPetankGroup(name, petankName, password, matchApplied=false) {
		def group = new PetankGroup(name:name, petankName:petankName, password:password, matchApplied:matchApplied)
		group.id = listGroups.size()
		group.listMatchs = []
		group.listUsers = []
		return group
	}

	static PetankGroup getGroup(name) {
		def c
		listGroups.each {
			if(name.equalsIgnoreCase(it.name)) {
				c = it
			}
		}
		return c
	}
	
	static PetankGroup getGroupById(idt) {
		def c
		listGroups.each {
			if(idt == it.id) {
				c = it
			}
		}
		return c
	}
	
	static def prepareGroup(group) {
		if(!group.matchApplied) {
			def listMatchs = MatchUtil.getMatchByGroupName(group.name)
			def listUsers = PetankUserUtil.getUserByGroupName(group.name)
			listUsers = StatUtil.applyMatchs(listMatchs, listUsers)
			group.listUsers = listUsers
			group.listMatchs = listMatchs
			group.matchApplied = true;
		}
	}
}
