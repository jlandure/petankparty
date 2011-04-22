/**
 * 
 */
package org.petank.server.rest

import org.restlet.*  
import org.restlet.data.*  
import org.restlet.resource.*  
import org.restlet.representation.*

import groovy.xml.MarkupBuilder;
import org.petank.server.PetankPlaceUtil;
import org.petank.server.dao.DAOManager
import org.petank.server.model.PetankPlace;

/**
 * @author jlandure
 *
 */
public class PlaceResource extends DefaultResource {

	def placeName, place
	
	def expireCache() {
		return true
	}
	
	def PlaceResource(Context context, Request request, Response response) {
		super(context, request, response)
		placeName = (String) request.getAttributes().get("place")
		if(placeName == null) {
			quit();return
		}
	}
	
	def prepareObjects() {
		place = PetankPlaceUtil.instance.getPlace(placeName)
	}
	
	@Override
	boolean allowPost() {
		return true
	}
	
	@Override
	void acceptRepresentation(Representation entity) {
		Form form = new Form(entity)
		def name = form.getFirstValue("name")
		def petankName = form.getFirstValue("petankName")
		def lat = form.getFirstValue("lat")
		def lng = form.getFirstValue("lng")
		def content = form.getFirstValue("content")
		
		// Register the new player
		PetankPlace place = PetankPlaceUtil.instance. createPetankPlace(name, petankName, lat, lng, content)
		DAOManager.instance.save(place)
		DefaultResource.MEMCACHE.clear()
		getResponse().redirectSeeOther(new Reference(getRootUri()+"/index"));
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
  <title>Boulodrome Maps / Petank Party</title> 
  <!-- <script type="text/javascript" src="http://www.google.com/jsapi"></script> -->
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
  <script type="text/javascript"><!--
    //google.load('maps', '2', {'sensor':false, 'language' : 'fr'});
	//Call this function when the page has been loaded
	 var geocoder;
  	 var map;
	  function initialize() {
		  var myLatlng = new google.maps.LatLng("""+place.lat+","+place.lng+""");

		 var myOptions = {
		      zoom: 15,
		      center: myLatlng,
		      mapTypeId: google.maps.MapTypeId.HYBRID
		    }
	    map = new google.maps.Map(document.getElementById("map"), myOptions);
		    var marker = new google.maps.Marker({
		        position: myLatlng, 
		        map: map,
		        title :" """+PetankPlaceUtil.instance.getPlaceName(place)+""" " 
		    });      

			var infowindow = new google.maps.InfoWindow(
		    	      { content: '"""+place.content+"""'
		    	      });

		    google.maps.event.addListener(marker, 'click', function() {
		        infowindow.open(map,marker);
		      });
	    //geocoder = new google.maps.Geocoder();
		//codeAddress();
	    
	  }
	 
		    function codeAddress() {
		    	var address = 'Espace Sportif du Souchais, carquefou, FR';
		        if (geocoder) {
		          geocoder.geocode( { 'address': address}, function(results, status) {
		            if (status == google.maps.GeocoderStatus.OK) {
		              if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
		                map.set_center(results[0].geometry.location);
		                var marker = new google.maps.Marker({
		                    map: map, 
		                    position: results[0].geometry.location
		                });
		              } else {
		                alert("No results found");
		              }
		            } else {
		              alert("Geocode was not successful for the following reason: " + status);
		            }
		          });
		        }
		      }
    //google.setOnLoadCallback(initialize);
  --></script> 
</head> 
 
<body style="font-family: Arial;border: 0 none;" onload="initialize()"> 
<div id="map" style="width: 640px; height: 480px;"></div> 
</body> 
</html> 
"""
	  }
}