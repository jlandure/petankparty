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
	
	def DefaultGroupResource(Context context, Request request, Response response) {
		super(context, request, response)
				
		//pas propre pour l'instant
		PetankGroupUtil.populate()
		PetankUserUtil.populate();
		MatchUtil.populate();
		BaremeUtil.populate();
		groupName = (String) request.getAttributes().get("group");
		def group = PetankGroupUtil.getGroup(groupName)
		PetankGroupUtil.prepareGroup(group)
		listUsers = group.listUsers
		listMatchs = group.listMatchs
	}
}
