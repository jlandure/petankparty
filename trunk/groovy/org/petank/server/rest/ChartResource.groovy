/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*
import groovy.xml.MarkupBuilder;
import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.model.Bareme;
import org.petank.server.model.Match;
import org.petank.server.model.PetankUser;

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
		String gString = """<img src="http://chart.apis.google.com/chart?chs=500x200&amp;cht=lc&amp;chds=0,250&amp;chxt=y&amp;chxl=0:|0|50|100|150|200|250&amp;chxp=0,50,100,150,200,250|&amp;chtt=${player.name}&amp;chd=t:100.0,${allpoints}" alt="Chart ${player.name}" />"""		
	}

}
