/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder;
import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;
/**
 * @author jlandure
 *
 */
public class BaremeResource extends DefaultResource {

	def BaremeResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
    def toXML() {
    	def listBaremes = BaremeUtil.populate();
		
    	def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		xml.setDoubleQuotes(true)

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

	def toHTML() {
		def listBaremes = BaremeUtil.populate();
		
		def bareme 
		def writer = new StringWriter()
		def html = new MarkupBuilder(writer)
		html.setDoubleQuotes(true)
		html.html {
			head {
		        title "Bareme P\u00E9tank Party"
		    }
		    body {
		    	h1 "Bareme"
		        p "V0.4.1-beta"
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
