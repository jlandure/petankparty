package org.petank.server
/**
 * 
 */
import org.petank.server.dao.DAOManager
import org.petank.server.model.Bareme;
import org.petank.server.model.TypeMatch;

/**
 * @author jlandure
 *
 */
@Singleton
class BaremeUtil {

	static BaremeUtil getInstance() {
		return instance
	}
	
	List<Bareme> populate() {
		def listBaremes = new ArrayList<Bareme>(9)
		listBaremes = new ArrayList<Bareme>(9)
		listBaremes << new Bareme(minimum:0  ,maximum:4 ,victoireNormale:6  ,defaiteNormale:-5  ,victoireAnormale:6 ,defaiteAnormale:-5);
		listBaremes << new Bareme(minimum:5 ,maximum:9 ,victoireNormale:5.5,defaiteNormale:-4.5,victoireAnormale:7 ,defaiteAnormale:-6);
		listBaremes << new Bareme(minimum:10 ,maximum:19 ,victoireNormale:5  ,defaiteNormale:-4  ,victoireAnormale:8 ,defaiteAnormale:-7);
		listBaremes << new Bareme(minimum:20,maximum:39,victoireNormale:4  ,defaiteNormale:-3  ,victoireAnormale:10,defaiteAnormale:-8);
		listBaremes << new Bareme(minimum:40,maximum:69,victoireNormale:3  ,defaiteNormale:-2  ,victoireAnormale:13,defaiteAnormale:-10);
		listBaremes << new Bareme(minimum:70,maximum:99,victoireNormale:2  ,defaiteNormale:-1  ,victoireAnormale:17,defaiteAnormale:-12.5);
		listBaremes << new Bareme(minimum:100,maximum:100000,victoireNormale:0   ,defaiteNormale:0   ,victoireAnormale:40,defaiteAnormale:-29);
	}
	
	Bareme chooseBareme(between, listBaremes) {
		def c
		def roundBetween = between.round()
		listBaremes.each {
			if(roundBetween >= it.minimum && roundBetween <= it.maximum) {
				c = it
			}
		}
		return c
	}
	
	def getBaremes() {
		return DAOManager.instance.getAll(Bareme.class)
	}
	
	Bareme getBaremeById(id) {
		return DAOManager.instance.get(Bareme.class, id)
	}
	
	def getCoefficient(TypeMatch type) {
		switch(type) {
			case TypeMatch.OFFICIEL : return 1
			case TypeMatch.NON_OFFICIEL : return 0.5
		}
	}
	
	
}
