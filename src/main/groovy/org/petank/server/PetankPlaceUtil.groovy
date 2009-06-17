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
			listPlaces << createPetankPlace("souchais", "Boulodrome du Souchais‎", 47.30316, -1.501603, "Boulodrome du Souchais <br/>Espace Sportif du Souchais<br/>Avenue de la Loire, 44470 Carquefou")
			listPlaces << createPetankPlace("mainguais", "Boulodrome de la Mainguais‎", 47.280328, -1.489656, "Boulodrome de la Mainguais‎ <br/>Rue de la Mainguais, 44470 Carquefou")
			listPlaces << createPetankPlace("56", "Boulodrome du 56‎", 47.258628, -1.59958, "Boulodrome du 56 ‎<br/>44700 Orvault")
			listPlaces << createPetankPlace("chantilly", "Boulodrome du Petit Chantilly‎", 47.254075, -1.598575, "Boulodrome du Petit Chantilly‎ <br/>Avenue Alexandre Goupil, 44700 Orvault")
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
	
	static def getPlaceName(place) {
		return place.petankName[0..-2]
	}
	
}
