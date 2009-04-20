/**
 * 
 */
package org.petank.server.rest

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


/**
 * @author jlandure
 *
 */
public class HelloWorldResource extends ServerResource {

    @Get
    public String represent() {
        return "hello, world (from the cloud!)";
    }


	
	
}
