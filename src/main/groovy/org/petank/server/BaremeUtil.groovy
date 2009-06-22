package org.petank.server
/**
 * 
 */
import org.petank.client.model.Bareme;
import org.petank.client.model.TypeMatch;
import org.petank.server.dao.DAOManager

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
		listBaremes << new Bareme(minimum:0  ,maximum:9 ,victoireNormale:6  ,defaiteNormale:-5  ,victoireAnormale:6 ,defaiteAnormale:-5);
		listBaremes << new Bareme(minimum:10 ,maximum:29 ,victoireNormale:5.5,defaiteNormale:-4.5,victoireAnormale:7 ,defaiteAnormale:-6);
		listBaremes << new Bareme(minimum:30 ,maximum:49 ,victoireNormale:5  ,defaiteNormale:-4  ,victoireAnormale:8 ,defaiteAnormale:-7);
		listBaremes << new Bareme(minimum:50,maximum:79,victoireNormale:4  ,defaiteNormale:-3  ,victoireAnormale:10,defaiteAnormale:-8);
		listBaremes << new Bareme(minimum:80,maximum:109,victoireNormale:3  ,defaiteNormale:-2  ,victoireAnormale:13,defaiteAnormale:-10);
		listBaremes << new Bareme(minimum:110,maximum:299,victoireNormale:2  ,defaiteNormale:-1  ,victoireAnormale:17,defaiteAnormale:-12.5);
		listBaremes << new Bareme(minimum:300,maximum:399,victoireNormale:1  ,defaiteNormale:-0.5,victoireAnormale:22,defaiteAnormale:-16);
		listBaremes << new Bareme(minimum:400,maximum:499,victoireNormale:0.5,defaiteNormale:0   ,victoireAnormale:28,defaiteAnormale:-20);
		listBaremes << new Bareme(minimum:500,maximum:100000,victoireNormale:0   ,defaiteNormale:0   ,victoireAnormale:40,defaiteAnormale:-29);
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
