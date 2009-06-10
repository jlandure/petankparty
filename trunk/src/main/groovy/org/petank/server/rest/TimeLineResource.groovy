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
public class TimeLineResource extends DefaultGroupResource {

	def playersName, players, points
	
	def expireCache() {
		return true
	}
	
	def TimeLineResource(Context context, Request request, Response response) {
		super(context, request, response)
		playersName = (String) request.getAttributes().get("players")
		println playersName
		if(playersName == null) {
			quit();return
		}
		players = PetankUserUtil.getUsers(playersName, groupName)
		players.each{println it.name}
		if(players == null || players.size == 0) {
			quit();return
		}
	}
	
    def toXML() {

		int i = 1;
    	def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		xml.setDoubleQuotes(true)

		xml.test{//(name:player.petankName) {
//			listUsers.each{ play ->
//				player(place:i, nom:play.petankName, score:play.points)
//				i++
//			}
		}
		return writer.toString()
    }

	def toHTML() {

		String gString = """
		
<!--
  copyright (c) 2009 Google inc.

  You are free to copy and use this sample.
  License can be found here: http://code.google.com/apis/ajaxsearch/faq/#license
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <title>Google Visualization API Sample</title>
  <script type="text/javascript" src="http://www.google.com/jsapi"></script>
  <script type="text/javascript"><!--
    google.load('visualization', '1', {packages: ['annotatedtimeline'], 'language' : 'fr'});
    function drawVisualization() {
      var data = new google.visualization.DataTable();
      data.addColumn('date', 'Date');
"""
	def player
	players.each{
		player = it
		gString += "\ndata.addColumn('number','"+player.name+"');"
	}
	int i=0, j
	def point
	def points = [:]
	def date
	def hasPoints
	def sameDate
	def matchAvantDay
	listMatchs.each{
		
//		if(date == null || !(MatchUtil.getDateToFrString(it.jour).equals(date))) {
//			date = MatchUtil.getDateToFrString(it.jour)
//			sameDate = false
//		} else {
//			sameDate = true
//		}
		if(/*!sameDate &&*/ matchAvantDay!=null) {
			hasPoints = false
			players.each{
				player = it
				println player.name
				point = MatchUtil.getPlayerPoints(matchAvantDay.playersWithPoints, player.id as String);
				if(point != null) {
					hasPoints = true
					points."${player.id}" = point
					println points."${player.id}"
				}
			}
			if(hasPoints) {
				j = 1
				gString += "\ndata.addRow();"
				gString += "\ndata.setValue("+i+", 0, new Date("+MatchUtil.getDateToGoogleDateString(matchAvantDay.jour)+"));"
				players.each{
					player = it
					gString += "\ndata.setValue("+i+", "+j+", "+points."${player.id}"+");"
					j++
				}
				i++;
			}
		}
		
		j = 0
		matchAvantDay = it
	}
//      data.addColumn('number', 'RST');
//      data.addColumn('number', 'JLE');
//      data.setValue(0, 0, new Date(2009, 1 ,3));
//      data.setValue(0, 1, 650);
//      data.setValue(0, 2, 650);
//      data.setValue(1, 0, new Date(2009, 1 ,5));
//      data.setValue(1, 1, 675);
//      data.setValue(1, 2, 680);
//      data.setValue(2, 0, new Date(2009, 4 ,6));
//      data.setValue(2, 1, 670);
//      data.setValue(2, 2, 692);
//      data.setValue(3, 0, new Date(2009, 8 ,7));
//      data.setValue(3, 1, 698);
//      data.setValue(3, 2, 701);

      

      gString += """
      var annotatedtimeline = new google.visualization.AnnotatedTimeLine(
          document.getElementById('visualization'));
      annotatedtimeline.draw(data, {scaleType:'maximize'});
    }
    
    google.setOnLoadCallback(drawVisualization);
  --></script>
</head>

<body style="font-family: Arial;border: 0 none;">
<div id="visualization" style="width: 800px; height: 400px;"></div>
</body>
</html>
""";

		
	}

}
