/**
 * 
 */
package org.petank.server

import groovy.lang.Singleton

/**
 * @author jlandure
 *
 */
@Singleton
public class DateUtil {

	static DateUtil getInstance() {
		return instance
	}
	
	def makeDate(event) {
	    def eventDate = Calendar.getInstance()
		if (event != null) {
			def dmy = event.split("/").collect { num -> Integer.parseInt(num.trim()) }
			eventDate.set(dmy[2], (dmy[1] - 1), dmy[0], 0, 0, 0)
		}
	    return eventDate.getTime()
	}
	
	def getDateToString(date) {
		//le nouveau est mieux car c'est ordonné niveau temps
		return String.format('%tY/%<tm/%<td', date)
	}
	
	def getDateToFrString(date) {
		//format FR
		return String.format('%td/%<tm/%<tY', date)
	}
	
	def getDateToGoogleDateString(date) {
		//format new Date(2009, 1 ,3)); //Y, M-1, D (3 février 2009)
		def dateStringFormat = String.format('%tY, %<tm, %<td', date)
		def dateString = dateStringFormat
		def matcher = (dateStringFormat =~ /([0-9]+), ([0-9]+), ([0-9]+)/)
		if (matcher.matches()) {
			dateString = ""+matcher[0][1]+", "+(Integer.parseInt(matcher[0][2])-1)+", "+matcher[0][3]
		}
		return dateString
	}
	
}
