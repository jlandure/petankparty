/**
 * 
 */
package org.petank.server.dao

import javax.jdo.JDOHelper
import javax.jdo.PersistenceManagerFactory
import javax.jdo.PersistenceManager
import javax.jdo.Transaction
import javax.jdo.Query;

/**
 * @author jlandure
 *
 */
@Singleton
public class DAOManager {

	private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }

    def Transaction tx
	def PersistenceManager pm
	
	def initTransaction() {
		if(pm == null) {
			pm = DAOManager.get().getPersistenceManager()
		}
		tx = pm.currentTransaction();
		tx.begin();
		return pm
	}
	
	def commitTransaction() {
		tx.commit();
	}
	
	def closeTransaction() {
		if (tx.isActive()) {
            tx.rollback();
        }
	}
	
	def get(def clazz, def id) {
		def object
		def PersistenceManager pm = initTransaction();
		try {
			object = pm.getObjectById(clazz, id)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return object
	}
	
	def getObjectByName(def clazz, def name) {
		def objects
		def PersistenceManager pm = initTransaction();
		try {
			Query query = pm.newQuery("select from "+clazz.name+" where name == nameParam")
			query.declareParameters("String nameParam")
			objects = query.execute(name)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects[0]
	}

	
	def save(def object) {
		def PersistenceManager pm = initTransaction();
		try {
			pm.makePersistent(object)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return object
	}
	
	def saveAll(List objects) {
		def PersistenceManager pm = initTransaction();
		try {
			pm.makePersistentAll(objects)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects
	}
	
	List getAll(def clazz) {
		def objects = []
		def PersistenceManager pm = initTransaction();
		try {
			objects = pm.newQuery("select from "+clazz.name).execute()
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects
	}
	
	def delete(def object) {
		def PersistenceManager pm = initTransaction();
		try {
			pm.deletePersistent(object);
		  commitTransaction()
		} finally {
			closeTransaction()
		}
	}
	
}
