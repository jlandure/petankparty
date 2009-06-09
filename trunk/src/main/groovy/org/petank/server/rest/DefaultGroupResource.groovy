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


/**
 * @author jlandure
 *
 */
public class DefaultGroupResource extends DefaultResource {
	
	def groupName, group, listUsers, listMatchs
	
	def getGroupName() {
		return groupName = (String) request.getAttributes().get("group");
	}
	
	def getGroup() {
		return PetankGroupUtil.getGroup(getGroupName())
	}
	
	def DefaultGroupResource(Context context, Request request, Response response) {
		super(context, request, response)
		
		def group = getGroup()
		if(group == null) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return;
		}
		if(!group.matchApplied) {
			PetankGroupUtil.prepareGroup(group)
		}
		listUsers = group.listUsers
		listMatchs = group.listMatchs
	}
	
	//return true if cache is up to date
//	def expireCache() {
//		def group = getGroup()
//		return (!group.matchApplied)
//	}
	
	
}
