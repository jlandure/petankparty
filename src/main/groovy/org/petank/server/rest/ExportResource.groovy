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
import org.petank.server.PetankGroupUtil;
import org.petank.server.PetankPlaceUtil;import org.petank.server.dao.DAOManagerimport org.petank.server.MailUtil
/**
 * @author jlandure
 *
 */
public class ExportResource extends MatchResource {

	private static final def ADMIN_MAIL = ["jujujuz@gmail.com"]
	
	def expireCache() {
		return true
	}
	
	def ExportResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def sendMail() {
		//String body = "...<br/><h1>TEST</h1>";
		String body = toXML(prepareXmlWriter())
		def subject = "Export Match Database done"
		MailUtil.instance.sendMail(ADMIN_MAIL, subject, body)
	}
	
	def toHTML(html, writer) {
		sendMail()
		html.html {
			head {
		        title "Export P\u00E9tank Party"
		    }
		    body {
		    	h1 "Export"
		        p "${org.petank.server.rest.PetankPartyRestApplication.VERSION}"
		        br()
		        br()
		        h1 {
		    		"ok to "+ADMIN_MAIL
		    	}
		    }
		}
		return writer.toString();
	}
	
}
