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
import org.petank.server.DateUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.model.Bareme;
import org.petank.server.model.Match;
import org.petank.server.model.PetankUser;

/**
 * @author jlandure
 *
 */
public class TimeLineResource extends DefaultGroupResource {

	def playersName, players, points, listMatchs
	
	def expireCache() {
		return true
	}
	
	def TimeLineResource(Context context, Request request, Response response) {
		super(context, request, response)
		playersName = (String) request.getAttributes().get("players")
		if(playersName == null) {
			quit();return
		}
	}
	
	def prepareObjects() {
		players = PetankUserUtil.instance.getUsers(playersName, groupName)
		listMatchs = MatchUtil.instance.getMatchByGroupName(groupName)
	}
	
	def toHTML(html, writer) {
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
  <title>Courbe d'evolution / Petank Party</title>
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
		
//		if(date == null || !(DateUtil.getDateToFrString(it.jour).equals(date))) {
//			date = DateUtil.getDateToFrString(it.jour)
//			sameDate = false
//		} else {
//			sameDate = true
//		}
		if(/*!sameDate &&*/ matchAvantDay!=null) {
			hasPoints = false
			players.each{
				player = it
				point = MatchUtil.instance.getPlayerPoints(matchAvantDay.playersWithPoints, player.id as String);
				if(point != null) {
					hasPoints = true
					points."${player.id}" = point
				}
			}
			if(hasPoints) {
				j = 1
				gString += "\ndata.addRow();"
				gString += "\ndata.setValue("+i+", 0, new Date("+DateUtil.instance.getDateToGoogleDateString(matchAvantDay.jour)+"));"
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
