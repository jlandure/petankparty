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
	
	def groupName, group
	def expire
	
	def DefaultGroupResource(Context context, Request request, Response response) {
		super(context, request, response)
		groupName = (String) request.getAttributes().get("group")
		group = PetankGroupUtil.instance.getGroup(groupName)
		if(group==null) {
			quit()
			return
		}
		expire = !group.matchApplied
		if(expire) {
			PetankGroupUtil.instance.prepareGroup(group)
			DefaultResource.MEMCACHE.remove("/$groupName/classement")
			DefaultResource.MEMCACHE.remove("/$groupName/match")
		}
	}
	
}
