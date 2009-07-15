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
import org.petank.server.DateUtilimport java.io.File
/**
 * @author jlandure
 *
 */
public class ExportResource extends MatchResource {

	private static final def ADMIN_MAIL = ["julien.landure@gmail.com"]
	
	def expireCache() {
		return true
	}
	
	def ExportResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def sendMail() {
		String content = ""
		def ma
		listMatchs[-1..0].each {
			ma = it
			content += "listMatchs << createMatch(\""+groupName+"\", ["
			content += MatchUtil.instance.getPlayers(ma.player1, listUsers).collect{"\""+it.name+"\""}.join(",")
			content += "], ["
			content += MatchUtil.instance.getPlayers(ma.player2, listUsers).collect{"\""+it.name+"\""}.join(",")
			content += "], "+ma.score1+", "+ma.score2+", \""+DateUtil.instance.getDateToFrString(ma.jour)+"\", \""
			content += PetankPlaceUtil.getInstance().getPlaceById(ma.idPlace).name
			content += "\", TypeMatch."+ma.typeMatch+")\n\n"
		}
		println content
		//String contenu = toXML(prepareXmlWriter())
		//byte[] contentFile = contenu.getBytes("UTF-8")
		def subject = "Export Match Database done"
		MailUtil.instance.sendMail(ADMIN_MAIL, subject, content)//, contentFile, "match.txt", "text/plain") //"application/rss+xml"
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
		        h1 "ok to $ADMIN_MAIL"
		    }
		}
		return writer.toString();
	}
	
}
