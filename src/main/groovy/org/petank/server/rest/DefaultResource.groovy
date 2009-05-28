/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.representation.Variant


/**
 * @author jlandure
 *
 */
public class DefaultResource extends Resource {
	
	def DefaultResource(Context context, Request request, Response response) {
		super(context, request, response)
		//le 1er variant est le variant par défaut !
		variants.add(new Variant(MediaType.TEXT_HTML))
		variants.add(new Variant(MediaType.APPLICATION_XHTML))
		variants.add(new Variant(MediaType.TEXT_XML))
		variants.add(new Variant(MediaType.APPLICATION_XML))
		variants.add(new Variant(MediaType.TEXT_JAVASCRIPT))
		variants.add(new Variant(MediaType.APPLICATION_JSON))
		variants.add(new Variant(MediaType.TEXT_PLAIN))
	}
	
	Representation represent(Variant variant) {
    	switch (variant.mediaType) {
	    	case [MediaType.TEXT_HTML,MediaType.APPLICATION_XHTML]  :
	    		return new StringRepresentation(toHTML(), MediaType.TEXT_HTML)
	    		break;
	    	case [MediaType.TEXT_XML,MediaType.APPLICATION_XML] : 
	    		return new StringRepresentation(toXML(), MediaType.TEXT_XML)
	    		break
	    	case [MediaType.TEXT_JAVASCRIPT,MediaType.APPLICATION_JSON] : 
	    		return new StringRepresentation(toJSON(), MediaType.APPLICATION_JSON)
	    		break
	    	default:
	    		return new StringRepresentation(toPLAIN(), MediaType.TEXT_PLAIN);
	    	}	
    }
	
	def toHTML() {
		return "<html><body>HTML</body></html>"
	}
	def toXML() {
		return "<xml>xml</xml>"
	}
	def toJSON() {
		return "{json}"
	}
	def toPLAIN() {
		return "PLAIN TEXT"
	}
}
