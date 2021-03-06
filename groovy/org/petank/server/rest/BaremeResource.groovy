/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;import org.petank.server.dao.DAOManagerimport org.petank.server.model.Bareme;
import org.petank.server.model.Match;
import org.petank.server.model.PetankUser;

/**
 * @author jlandure
 *
 */
public class BaremeResource extends DefaultResource {

	def listBaremes
	
	def BaremeResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	static def mc= [compare:{a,b-> a?.minimum.compareTo(b?.minimum)}] as Comparator
	
	def prepareObjects() {
		listBaremes = DAOManager.instance.getAll(Bareme.class)
		listBaremes.sort(BaremeResource.mc)
	}
	
    def toXML(xml, writer) {
		xml.baremes() {
			listBaremes.each{ ba ->
				bareme( id:ba.id, 
						minimum:(ba.minimum as Integer),
						maximum:(ba.maximum as Integer),
						victoireNormale:ba.victoireNormale,
						defaiteNormale:ba.defaiteNormale,
						victoireAnormale:ba.victoireAnormale,
						defaiteAnormale:ba.defaiteAnormale
					)
			}
		}
		return writer.toString()
    }

	def toHTML(html, writer) {
		def bareme 
		html.html {
			head {
		        title "Bareme P\u00E9tank Party"
		    }
		    body {
		    	h1 "Bareme"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        br()
		        br()
		        
		        table(border:1, cellpadding:"15px", bordercolor:"black") {
		    		thead {
		    			tr {
		    				td(class:"special", "Ecart")
		        			td(class:"special", "Victoire normale")
		        			td(class:"special", "D\u00E9faite normale")
		        			td(class:"special", "Victoire anormale")
		        			td(class:"special", "D\u00E9faite anormale")
		    			}
		    		}
		    		tbody {
		    			listBaremes.each{
		    				bareme = it
				    		tr {
				    			td(class:"special", "${bareme.minimum as Integer} - ${bareme.maximum as Integer}")
				    			td(class:"special", "${bareme.victoireNormale}")
				    			td(class:"special", "${bareme.defaiteNormale}")
				    			td(class:"special", "${bareme.victoireAnormale}")
				    			td(class:"special", "${bareme.defaiteAnormale}")
				    		}
		    			}
		    		}
		    	}
		    }
		}
		return writer.toString();
	}

}
