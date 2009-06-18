/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder
import org.petank.server.PetankGroupUtil

/**
 * @author jlandure
 *
 */
public class BaseResource extends DefaultResource {

	def BaseResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def toHTML() {
		def writer = new StringWriter()
		def html = new MarkupBuilder(writer)
		html.setDoubleQuotes(true)
		html.html {
			head {
		        title "P\u00E9tank Party"
		    }
		    body {
		    	h1 "P\u00E9tank Party"
		        p "V0.4.1-beta"
		        p {
		    		a(href:"/bareme",  "Bareme")
		    		
		    		PetankGroupUtil.listGroups.each {
		    			br()
			    		br()
			    		a(href:"/"+it.name+"/c", it.petankName)
		    		}
		    		
		    		
		    	}
		    }
		}
		return writer.toString();
	}

}
