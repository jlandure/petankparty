/**
 * 
 */
package org.petank.server.dao

import javax.jdo.JDOHelper
import javax.jdo.PersistenceManagerFactory
import javax.jdo.PersistenceManager
import javax.jdo.Transaction
import javax.jdo.Query
import org.petank.client.model.Match

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

    Transaction tx
	PersistenceManager pm
	
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
			object = pm.getObjectById(clazz, (id as Long))
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
			Query query = pm.newQuery("select from "+clazz.name+" where name == nameParam order by name asc")
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
	
	List getAllFromIdGroup(def clazz, Long idGroupParam) {
		def objects = []
		def PersistenceManager pm = initTransaction();
		try {
			Query query = pm.newQuery("select from "+clazz.name+" where idGroup == idGroupParam order by id")
			query.declareParameters("Long idGroupParam")
			objects = query.execute(idGroupParam)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects
	}
	
	List getUserOrderedByGroupName(def clazz, Long idGroupParam) {
		def objects = []
		def PersistenceManager pm = initTransaction();
		try {
			Query query = pm.newQuery("select from "+clazz.name+" where idGroup == idGroupParam order by points desc")
			query.declareParameters("Long idGroupParam")
			objects = query.execute(idGroupParam)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects
	}
	
	List getMatchFromIdGroup(def clazz, Long idGroupParam) {
		def objects = []
		def PersistenceManager pm = initTransaction();
		try {
			Query query = pm.newQuery("select from "+clazz.name+" where idGroup == idGroupParam order by jour")
			query.declareParameters("Long idGroupParam")
			objects = query.execute(idGroupParam)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects
	}
	
	List getLastMatchFromIdGroup(def clazz, Long idGroupParam, start, length) {
		def objects = []
		def PersistenceManager pm = initTransaction();
		try {
			Query query = pm.newQuery("select from "+clazz.name+" where idGroup == idGroupParam order by jour desc range "+start+","+length)
			query.declareParameters("Long idGroupParam")
			objects = query.execute(idGroupParam)
			commitTransaction()
		} finally {
			closeTransaction()
		}
		return objects
	}
	
	def countAllMatchFromIdGroupAndDate(def clazz, Long idGroupParam, date) {
		def object = 0
		PersistenceManager pmSpecial = initTransaction();
		try {
			//TODO : gérer un incrément par jour
			//Query query = pmSpecial.newQuery("select count(id) from "+clazz.name+" where idGroup == "+idGroupParam+" and this.jour.getYear() == "+date.getYear()+" and this.jour.getMonth() == "+date.getMonth()+" and this.jour.getDay() == "+date.getDay()+" import java.util.Date ")
//			query.declareParameters("Long idGroupParam, Date dateParam")
//			object = query.execute(idGroupParam, date)
			Query query = pmSpecial.newQuery("select count(id) from "+clazz.name)
//			query.declareImports("import java.util.Date");
//			query.setFilter("jour.getYear() == "+date.getYear())
//			query.setFilter("jour.getMonth() == "+date.getMonth())
//			query.setFilter("jour.getDay() == "+date.getDay())
			query.setFilter("idGroup == "+idGroupParam)
			//query.declareParameters("Long idGroupParam")
			object = query.execute()
		} finally {
			closeTransaction()
		}
		return object
	}
	
//	query.declareImports("import java.util.Date");
//	query.declareParameters("Date best_before_limit");

	
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
