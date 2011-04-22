/**
 * 
 */
package org.petank.server

import org.petank.server.dao.DAOManager
import org.petank.server.model.PetankPlace;

/**
 * @author jlandure
 *
 */
@Singleton
class PetankPlaceUtil {

	static PetankPlaceUtil getInstance() {
		return instance
	}
	
	List<PetankPlace> populate() {
		def listPlaces = new ArrayList<PetankPlace>()
		listPlaces = new ArrayList<PetankPlace>()
		listPlaces << createPetankPlace("souchais", "Boulodrome du Souchais‎", 47.30316, -1.501603, "Boulodrome du Souchais <br/>Espace Sportif du Souchais<br/>Avenue de la Loire, 44470 Carquefou")
		listPlaces << createPetankPlace("mainguais", "Boulodrome de la Mainguais‎", 47.280328, -1.489656, "Boulodrome de la Mainguais‎ <br/>Rue de la Mainguais, 44470 Carquefou")
		listPlaces << createPetankPlace("lorient1", "Boulodrome du vieux Stade‎", 47.755142, -3.333606, " Boulodrome du vieux Stade <br/>Rue du Toulhouet, 56600 Lorient")
		return listPlaces
	}
	
	PetankPlace createPetankPlace(name, petkName, lat, lng, content) {
		def place = new PetankPlace(name:name, petankName:petkName, lat:lat, lng:lng, content:content)
		return place
	}

	PetankPlace getPlace(name) {
		return DAOManager.instance.getObjectByName(PetankPlace.class, name)
	}
	
	def getPlaces() {
		return DAOManager.instance.getAll(PetankPlace.class)
	}
	
	PetankPlace getPlaceById(id) {
		return DAOManager.instance.get(PetankPlace.class, id)
	}
	
	def getPlaceName(place) {
		return place.petankName[0..-2]
	}
	
}
