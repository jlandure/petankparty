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
public class ChartResource extends PlayerResource {

	def expireCache() {
		return true
	}
	
	def ChartResource(Context context, Request request, Response response) {
		super(context, request, response)
	}
	
	def toXML(html, writer) {
		return toHTML(html, writer)
	}

	def toHTML(html, writer) {
		def points = MatchUtil.instance.getPlayerEvolution(player)
		def allpoints = points.join(",")
		//def pointmin = points.min()
		//def pointmax = points.max()
		//String a = "http://chart.apis.google.com/chart?chs=500x200&cht=lc&chds=550,800&chxt=y&chxl=0:|550|600|650|700|750|800&chxp=550,600,650,700,750,800|&chtt=${player.name}&chd=t:${allpoints}"
		String gString = """<img src="http://chart.apis.google.com/chart?chs=500x200&amp;cht=lc&amp;chds=550,900&amp;chxt=y&amp;chxl=0:|550|600|650|700|750|800|850|900&amp;chxp=550,600,650,700,750,800,850,900|&amp;chtt=${player.name}&amp;chd=t:${allpoints}" alt="Chart ${player.name}" />"""		
	}

}
