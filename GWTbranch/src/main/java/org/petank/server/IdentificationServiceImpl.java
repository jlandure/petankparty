package org.petank.server;

import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.petank.client.model.PetankUser;
import org.petank.client.web.IdentificationService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class IdentificationServiceImpl extends RemoteServiceServlet implements
IdentificationService {

	@Override
	public PetankUser identify() {
		PetankUser currentUser = null;
		UserService userService = UserServiceFactory.getUserService();
		if (userService.isUserLoggedIn()) {
			User googleUser = userService.getCurrentUser();
			currentUser = this.getUserFromDb(googleUser);
			if(currentUser == null) {
				currentUser = this.saveUser(googleUser);
			}
		} else {
			currentUser = new PetankUser();
			currentUser.setEmail(userService.createLoginURL("/PetankParty.html"));
			//throw new UserNotFoundException(userService.createLoginURL("/"));
		}
		return currentUser;
	}

	public PetankUser getUserFromDb(User googleUser) {
		PetankUser user = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			String query = "select from " + PetankUser.class.getName()+" where name == '"+googleUser.getNickname()+"'";// and email == '"+googleUser.getEmail()+"'";
			List<PetankUser> users = (List<PetankUser>) pm.newQuery(query).execute();
			if(users != null && users.size() != 0) {
				user = users.get(0);
			}
		} finally {
			pm.close();
		}
		return user;
	}
	
	public PetankUser saveUser(User googleUser) {
		PetankUser user = new PetankUser();
		
		user.setName(googleUser.getNickname());
		user.setEmail(googleUser.getEmail());
		user.setDateInscription(Calendar.getInstance().getTime());
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
		return user;
	}
}
