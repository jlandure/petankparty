/**
 * 
 */
package org.petank.server

import org.petank.client.model.PetankPlace

/**
 * @author jlandure
 *
 */
@Singleton
class PetankPlaceUtil{

	private static def listPlaces
	
	static List<PetankPlace> populate() {
		if(listPlaces == null) {
			listPlaces = new ArrayList<PetankPlace>()
			listPlaces << createPetankPlace("souchais", "Boulodrome du Souchais‎", 47.30316, -1.501603, "Boulodrome du Souchais<br/>Espace Sportif du Souchais<br/>Avenue de la Loire, 44470 Carquefou")
			listPlaces << createPetankPlace("mainguais", "Boulodrome de la Mainguais‎", 47.280328, -1.489656, "Boulodrome de la Mainguais‎<br/>Rue de la Mainguais, 44470 Carquefou")
		}
		return listPlaces
	}
	
	static PetankPlace createPetankPlace(name, petkName, lat, lng, content) {
		def place = new PetankPlace(name:name, petankName:petkName, lat:lat, lng:lng, content:content)
		place.id = listPlaces.size()
		place.listMatchs = []
		return place
	}

	static PetankPlace getPlace(name) {
		def c
		listPlaces.each {
			if(name.equalsIgnoreCase(it.name)) {
				c = it
			}
		}
		return c
	}
	
	static PetankPlace getPlaceById(idt) {
		def c
		listPlaces.each {
			if(idt == it.id) {
				c = it
			}
		}
		return c
	}
	
}
