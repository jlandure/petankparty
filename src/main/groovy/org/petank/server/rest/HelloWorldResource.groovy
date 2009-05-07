/**
 * 
 */
package org.petank.server.rest

import org.restlet.resource.Get
import org.restlet.resource.ServerResource
import org.restlet.resource.UniformResource
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.representation.DomRepresentation
import org.restlet.representation.Variant
import org.restlet.data.MediaType


/**
 * @author jlandure
 *
 */
public class HelloWorldResource extends ServerResource {

//méthode qui marchait
//	@Get
//    public String represent() {
//        return "hello, world (from the cloud!)";
//    }
	
	public boolean isNegotiated() {
		println "là ?"
		return true;
	}
//	
//    public def toHtml() {
//        return "<h1>hello, world (from the cloud!)</h1>";
//    }
//    
//    public def toXml() {
//        return "<test>hello, world (from the cloud!)</test>";
//    }
//
//    Representation get(Variant variant) {
//    	println ""+doGetInfo()
//    	switch (variant.mediaType) {
//	    	case MediaType.TEXT_XML : 
//	    		getResponse().setEntity(toXml(), MediaType.TEXT_XML)
//	    		break;
//	    	case MediaType.HTML :
//	    		getResponse().setEntity(toHtml, MediaType.HTML)
//	    		break;
//	    	default:
//	    		getResponse().setEntity(toHtml(), MediaType.TEXT_PLAIN)
//	    	}	
//    }
	
	 public DomRepresentation getRequestEntityAsDom() {
		 return new DomRepresentation("<test>hello, world (from the cloud!)</test>")
	 }
	 
	 @Get
	 public String getRequestEntityAsText() {
		 return "<h1>hello, world (from the cloud!)</h1>";
	 }
	 
	 public Representation handle() {
		 return new StringRepresentation("hello, world handle (from the cloud!)");
	 }
	 
	 public Representation get() {
		 return new StringRepresentation("hello, world get (from the cloud!)");
	 }
	 
}
