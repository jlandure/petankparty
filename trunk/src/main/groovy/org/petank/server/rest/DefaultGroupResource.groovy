/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.representation.Variant
import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.PetankGroupUtil;
import org.petank.server.dao.DAOManager

/**
 * @author jlandure
 *
 */
public class DefaultGroupResource extends DefaultResource {
	
	def groupName, group, listUsers, listMatchs
	def expire
	
	def getGroupName() {
		return groupName = (String) request.getAttributes().get("group")
	}
	
	def getGroup() {
		return PetankGroupUtil.instance.getGroup(getGroupName())
	}
	
	def DefaultGroupResource(Context context, Request request, Response response) {
		super(context, request, response)
		def group = getGroup()
		if(group == null) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND)
			return
		}
		expire = !group.matchApplied
		if(expire) {
			this.assign(PetankGroupUtil.instance.prepareGroup(group))
			//String classement = "/$groupName/classement"
			//String match = "/$groupName/match"
				//DefaultResource.MEMCACHE.delete(match)
			DefaultResource.MEMCACHE.remove("/$groupName/classement")
			DefaultResource.MEMCACHE.remove("/$groupName/match")
		} else {
			this.assign(PetankUserUtil.instance.getUserByGroupName(group.name), MatchUtil.instance.getMatchByGroupName(group.name))
		}
	}
	
	def assign(listUsers, listMatchs) {
		this.listUsers = listUsers
		this.listMatchs = listMatchs
	}
	
	//return true if cache is up to date
	//def expireCache() {
		//return expire
	//}
	
	
}
