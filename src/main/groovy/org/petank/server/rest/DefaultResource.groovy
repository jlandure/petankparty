/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*
import org.petank.server.*
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.representation.Variant
import java.util.Collections
import javax.cache.Cache




/**
 * @author jlandure
 *
 */
public class DefaultResource extends Resource {
	
	public static Cache MEMCACHE
	
	def expireCache() {
		return false
	}
	
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
		String text
		def representation
		def key
    	switch (variant.mediaType) {
	    	case [MediaType.TEXT_HTML,MediaType.APPLICATION_XHTML]  :
	    		key = getRequest().getOriginalRef().getPath()
	    		
	    		//Get the value from the cache.
	    		text = (MEMCACHE.get(key) as String);
	    	
		    	if(text == null || expireCache()) {
	    			//Put the value into the cache.
	    			text = toHTML()
	    	        MEMCACHE.put(key, text);
	    		}
	    	
    			representation = new StringRepresentation(text, MediaType.TEXT_HTML)
	    		break;
	    	case [MediaType.TEXT_XML,MediaType.APPLICATION_XML] : 
	    		representation = new StringRepresentation(toXML(), MediaType.TEXT_XML)
	    		break
	    	case [MediaType.TEXT_JAVASCRIPT,MediaType.APPLICATION_JSON] : 
	    		representation = new StringRepresentation(toJSON(), MediaType.APPLICATION_JSON)
	    		break
	    	default:
	    		representation = new StringRepresentation(toPLAIN(), MediaType.TEXT_PLAIN);
	    	}
		return representation
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
