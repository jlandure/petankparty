/**
 * 
 */
package org.petank.server.dao

import javax.jdo.PersistenceManager


/**
 * @author jlandure
 *
 */
public class DAOManager {

	def get(def id) {
		def object
		PersistenceManager pm = PMF.get().getPersistenceManager()
		try {
			object = pm.getObjectById(id)
		} finally {
			pm.close();
		}
		return object
	}
	
	def save(def object) {
		PersistenceManager pm = PMF.get().getPersistenceManager()
		try {
			pm.makePersistent(object)
		} finally {
			pm.close();
		}
		return object
	}
	
	def getAll(def clazz) {
		def objects
		PersistenceManager pm = PMF.get().getPersistenceManager()
		try {
			objects = pm.newQuery("select from " +clazz).execute()
		} finally {
			pm.close();
		}
		return object
	}
	
}
