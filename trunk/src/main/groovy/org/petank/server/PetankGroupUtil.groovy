/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankGroup;

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
		def group = new PetankGroup(name:name, petankName:petankName, matchApplied:matchApplied)
		group.id = listGroups.size()
		return group
	}

	static PetankGroup getGroup(name) {
		def c
		listGroups.each {
			if(name == it.name) {
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
			listMatchs.each{MatchUtil.applyMatch(it)}
			listUsers.each{it.evolution = MatchUtil.getPlayerEvolution(it)}
			listUsers = PetankUserUtil.sortByPoint(listUsers)
			group.listUsers = listUsers
			group.listMatchs = listMatchs
			group.matchApplied = true;
		}
	}
}
