package org.petank.server;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.petank.client.GreetingService;
import org.petank.client.model.PetankUser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
GreetingService {

	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");



		List<PetankUser> users = null;
		String tempUsers = "";
		PetankUser user = new PetankUser();
		//user.setName(input);
		user.setCommentaire("test");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);


		String query = "select from " + PetankUser.class.getName();
		users = (List<PetankUser>) pm.newQuery(query).execute() ;
		
		for(PetankUser userTemp : users) {
			//tempUsers += userTemp.getName() + " - " + userTemp.getCommentaire()+ "\n";
		}
		} finally {
			pm.close();
		}
	


		return "Hello, " + input + "!<br><br>I am running " + serverInfo

		+ tempUsers
		+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
}
