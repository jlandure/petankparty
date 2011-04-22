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
import org.restlet.representation.OutputRepresentation
import org.restlet.representation.Variant
import java.util.Collections
import javax.cache.Cache
import org.restlet.data.Encoding
import java.util.zip.GZIPOutputStream
import groovy.xml.MarkupBuilder

import javax.jdo.PersistenceManager
import org.petank.server.PMF
import javax.jdo.Transaction
import org.petank.server.dao.DAOManager



/**
 * @author jlandure
 *
 */
public class DefaultResource extends Resource {
	
	public static Cache MEMCACHE
	
	def expireCache() {
		return false
	}
	
	def prepareObjects() {
		
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
		def representation
		String text
		def key
		switch (variant.mediaType) {
			case [MediaType.TEXT_HTML,MediaType.APPLICATION_XHTML]  :
				key = getRequest().getOriginalRef().getPath()+MediaType.TEXT_HTML
			
				//Get the value from the cache.
				text = (MEMCACHE.get(key) as String);
			
				if(text == null || expireCache()) {
					prepareObjects()
					//Put the value into the cache.
					text = toHTML(prepareHtmlWriter())
					MEMCACHE.put(key, text);
				}
			
				representation = new StringRepresentation(text, MediaType.TEXT_HTML)
				break;
			case [MediaType.TEXT_XML,MediaType.APPLICATION_XML] : 
				key = getRequest().getOriginalRef().getPath()+MediaType.TEXT_XML
				
				//Get the value from the cache.
				text = (MEMCACHE.get(key) as String);
			
				if(text == null || expireCache()) {
					prepareObjects()
					//Put the value into the cache.
					text = toXML(prepareXmlWriter())
					MEMCACHE.put(key, text);
				}
			
				representation = new StringRepresentation(text, MediaType.TEXT_XML)
				break
			case [MediaType.TEXT_JAVASCRIPT,MediaType.APPLICATION_JSON] : 
				representation = new StringRepresentation(toJSON(), MediaType.APPLICATION_JSON)
				break
			default:
				representation = new StringRepresentation(toPLAIN(), MediaType.TEXT_PLAIN);
		}
		return representation
	}
	
	def prepareHtmlWriter() {
		def writer = new StringWriter()
		writer.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n")
		def html = new MarkupBuilder(writer)
		html.setDoubleQuotes(true)
		return [html, writer]
	}
	
	def prepareXmlWriter() {
		def writer = new StringWriter()
		writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
		def xml = new MarkupBuilder(writer)
		xml.setDoubleQuotes(true)
		return [xml, writer]
	}
	
	def toHTML(html, writer) {
		return "<html><body>HTML</body></html>"
	}
	def toXML(xml, writer) {
		return "<xml>xml</xml>"
	}
	def toJSON() {
		return "{json}"
	}
	def toPLAIN() {
		return "PLAIN TEXT"
	}
	
	def quit() {
		getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND)
		return;
	}
	
	String rootUri;
	
	def getRootUri() {
		if(rootUri == null) {
			rootUri = request.getRootRef().toString()
		}
		return rootUri;
	}
	
}
