/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankUser;

/**
 * @author jlandure
 *
 */
public class PetankUserUtil{

private static def listUsers = new ArrayList<PetankUser>(11)
	
	static List<PetankUser> populate() {
		listUsers << new PetankUser(id:1 , name:"GBE", points:650);
		listUsers << new PetankUser(id:2 , name:"ADE", points:650);
		listUsers << new PetankUser(id:3 , name:"SHS", points:650);
		listUsers << new PetankUser(id:4 , name:"CLC", points:650);
		listUsers << new PetankUser(id:5 , name:"JLE", points:650);
		listUsers << new PetankUser(id:6 , name:"JND", points:650);
		listUsers << new PetankUser(id:7 , name:"RST", points:650);
		listUsers << new PetankUser(id:8 , name:"EBT", points:650);
		listUsers << new PetankUser(id:9 , name:"BPT", points:650);
		listUsers << new PetankUser(id:10, name:"HDG", points:650);
		listUsers << new PetankUser(id:11, name:"CBO", points:650);
	}

	static PetankUser getUser(na) {
		def c
		listUsers.each {
			if(na == it.name) {
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
		def mc= [compare:{a,b-> a.points>b.points? -1: a.points<b.points? 1: 0}] as Comparator
		players.sort(mc)
		return players
	}
}