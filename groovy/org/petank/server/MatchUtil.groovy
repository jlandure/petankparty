/**
 * 
 */
package org.petank.server

import java.util.Date;
import org.petank.server.PetankUserUtil;
import org.petank.server.DateUtil;
import org.petank.server.dao.DAOManager
import org.petank.server.model.Bareme;
import org.petank.server.model.Match;
import org.petank.server.model.PetankUser;
import org.petank.server.model.TypeMatch;
import org.petank.server.model.TypeVictoire;


/**
 * @author jlandure
 *
 */
@Singleton
class MatchUtil {
	
	static MatchUtil getInstance() {
		return instance
	}
	
	private static String JOIN_PLAYER = ";"
	
	List<Match> populate() {
		def listMatchs = new ArrayList<Match>()
		listMatchs.addAll(this.populate1())
		return listMatchs
	}
	
	List<Match> populate1() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware2011", ["ADE","GBE"], ["EBT","FRT"], 12, 13, "31/03/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["RST","BPT"], ["JND","AHS"], 13, 6, "31/03/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["ADE","GBE"], ["JND","AHS"], 13, 10, "31/03/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["RST","BPT"], ["EBT","FRT"], 0, 13, "31/03/2011", "souchais")

		listMatchs << createMatch("euriware2011", ["AHS","BPT"], ["ADE","RST"], 0, 13, "01/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["AHS","BPT"], ["ADE","RST"], 6, 13, "01/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["AHS","ADE"], ["BPT","RST"], 7, 13, "01/04/2011", "souchais")
		//vend soir
		listMatchs << createMatch("euriware2011", ["RST","JLE","BPT"], ["EBT","GPE","GBE"], 13, 10, "01/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["RST","JLE","BPT"], ["EBT","GPE","GBE"], 13, 11, "01/04/2011", "souchais")
		
		listMatchs << createMatch("euriware2011", ["JLE","FEE"], ["AHS","RST"], 13, 6, "07/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["JLE","FEE"], ["AHS","RST"], 10, 13, "07/04/2011", "souchais")
		
		listMatchs << createMatch("euriware2011", ["GBE","GHT","NBN"], ["FRT","SHS"], 13, 11, "07/04/2011", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware2011", ["GBE","GHT","NBN"], ["FRT","SHS"], 13, 11, "07/04/2011", "souchais", TypeMatch.NON_OFFICIEL)
		
		listMatchs << createMatch("euriware2011", ["JLE","AHS","SHS"], ["FRT","FEE","GBE"], 13, 5, "07/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["JLE","AHS","SHS"], ["FRT","FEE","GBE"], 5, 13, "07/04/2011", "souchais")
		
		listMatchs << createMatch("euriware2011", ["BPT","AHS"], ["SHS","GPE"], 13, 11, "08/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["BPT","AHS"], ["SHS","GPE"], 8, 13, "08/04/2011", "souchais")
		
		listMatchs << createMatch("euriware2011", ["JLE","FRT","EBT"], ["GBE","RST","MSI"], 13, 6, "08/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["JLE","FRT","EBT"], ["GBE","RST","MSI"], 10, 13, "08/04/2011", "souchais")
		
		listMatchs << createMatch("euriware2011", ["GBE","EBT"], ["JLE","RST"], 13, 6, "08/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["GBE","EBT","FRT"], ["JLE","RST","GPE"], 13, 9, "08/04/2011", "souchais")
		listMatchs << createMatch("euriware2011", ["GBE","GPE"], ["BPT","JLE"], 8, 13, "08/04/2011", "souchais")
		
		return listMatchs
	}
	
	List getMatchByGroupName(groupName) {
		def petankGroup = PetankGroupUtil.instance.getGroup(groupName)
		return DAOManager.instance.getMatchFromIdGroup(Match.class, petankGroup.id)
	}
	
	Match getMatchById(idMatch) {
		return DAOManager.instance.get(Match.class, idMatch)
	}
	
	List getLastMatchByGroupName(groupName, start, length) {
		def petankGroup = PetankGroupUtil.instance.getGroup(groupName)
		return DAOManager.instance.getLastMatchFromIdGroup(Match.class, petankGroup.id, start, length)
	}
	
	def resetMatch(match) {
		match.playersWithPoints = ""
		match.point1 = 0
		match.point2 = 0
		return match
	}
	
	def getMatchs() {
		return DAOManager.instance.getAll(Match.class)
	}
	
	def sortByDate(listMatchs) {
		def mc= [compare:{a,b-> a.jour.compareTo(b.jour)}] as Comparator
		listMatchs.sort(mc)
		return listMatchs
	}
	
	def getMatchByPlayerNameAndGroupName(playerName, groupName) {
		def c = []
		def player = PetankUserUtil.getUser(playerName, groupName)
		def listMatchs = MatchUtil.instance.getMatchs()
		listMatchs.each {
			if(match.player1.contains(player.name) || match.player2.contains(player.name)) {
				c << it
			}
		}
		return c
	}
	
	Date getMatchDate(idGroup, dateString) {
		def nb = DAOManager.instance.countAllMatchFromIdGroupAndDate(Match.class, idGroup, DateUtil.instance.makeDate())
		return DateUtil.instance.makeDate(dateString, nb)
	}
	
	Match createMatch(group, play1, play2, sc1, sc2, dateString=null, place="souchais", type=TypeMatch.OFFICIEL) {
		def idGroup = PetankGroupUtil.instance.getGroup(group).id
		def date = this.getMatchDate(idGroup, dateString)
		def player1 = []
		def player2 = []
		play1.each { player1 << PetankUserUtil.instance.getUser(it, group)}
		play2.each { player2 << PetankUserUtil.instance.getUser(it, group)}
		def match = new Match(score1:sc1, score2:sc2, typeMatch:type, jour:date, player1:"", player2:"", point1:0, point2:0, playersWithPoints:"");
		match.idGroup = idGroup
		match.idPlace = PetankPlaceUtil.instance.getPlace(place).id
		//on ne créé plus les données sur les points des joueurs (et moyennes) au moment de la création mais 
		//au moment où l'on applique les barêmes
		
		def concatId = {players -> players.collect({player -> player.id}).join(JOIN_PLAYER)}
		//utilisation du name
		//def concatId = {players -> players.collect({player -> player.name}).join(JOIN_PLAYER)}
		match.player1 = concatId(player1)
		match.player2 = concatId(player2)
		
		DAOManager.instance.save(match)
		return match
	}
	
	List getPlayersId(player) {
		//retourn un tableau de id en splittant
		return player.split(JOIN_PLAYER)
	}
	
	List getPlayers(player, listUsers) {
		//retourn les PetankUser à partir des id
		def id = getPlayersId(player)
		def players = []
		//chargement tout le temps : mauvais
		//id.each{players << PetankUserUtil.instance.getUserById(it)}
		id.each{players << PetankUserUtil.instance.getUserById(it as Long, listUsers)}
		return players;
	}
	
	
	List getPlayersIdFromComment(player) {
		def data = getPlayersId(player)
		def id = []
		
		def printId = {
			def matcher = (it.trim() =~ /([0-9]+).*(\[)([0-9]+\.*[0-9]*)(\])/)
			if (matcher.matches()) {
				id << matcher[0][1]
			}
		}
		data.each(printId)
		return id;
	}
	
	/*static List getPlayersPointsFromId(playersWithPoints, id) {
	 def names = "5 [3]; SHS [4];GBE[55];RST [9] "
	 //println player
	 def data = names
	 def point
	 def printId = {
	 def matcher = (it.trim() =~ /.*'${id}'.*[([0-9]+\.*[0-9]*)]/)
	 println matcher[0][1]
	 if (matcher.matches()) {
	 point << matcher[0][1]
	 }
	 }
	 data(printId)
	 println point
	 //id.each{println(it)}
	 return point
	 }*/
	
	Map getPlayersWithPointsFromComment(player) {
		def data = getPlayersId(player)
		def id = [:]
		def printId = {
			def matcher = (it.trim() =~ /([0-9]+).*(\[)([0-9]+\.*[0-9]*)(\])/)
			if (matcher.matches()) {
				id."${matcher[0][1]}" = matcher[0][3]
			}
		}
		data.each(printId)
		return id;
	}
	
	Float getPlayerPoints(playersWithPoints, String id) {
		def points = getPlayersWithPointsFromComment(playersWithPoints)
		return (points.(""+id))?.toFloat()
	}
	
	def getPlayerEvolution(player) {
		def point
		def points = []
		def listMatchs = MatchUtil.instance.getMatchs()
		listMatchs.each{
			point = getPlayerPoints(it.playersWithPoints, player.id as String);
			if(point != null) points << point;
		}
		points << player.points
		
		//couleur bleuté : &chco=76A4FB
		//style : &cht=lc
		//label sur x : chxt=x,x
		//deux labels sur x donc valeurs indexés : chxl=1:||Mar|Avr||0:|1st|15th|1st|15th|1st
		//taille du graphe : &chs=400x150
		//?? : &chls=2.0
		//min max en ordonnées pour la taille : &chds=647.5,680.5
		
		//		def debut = getDateMonth(listMatchs[0].jour)
		//		println debut
		//		def diffDates = listMatchs[-1].jour - debut
		//	    println diffDates
		
		//	    def info = ""
		//	    def i = 0;
		//	    diffDates.times{
		//			debut++
		//			println getDateToString(debut)
		//			if(i == 1 || i == 15) {
		//			info += "300,"
		//				
		//			} else {
		//				info += "650,"
		//			}
		//			i++
		//		}
		//		println info
		//	http://chart.apis.google.com/chart?chxt=x,x&chxl=1:||Mar|Avr||0:|1st|15th|1st|15th|1st&cht=lc&chd=s:cEAELFJHHHKUju9uuXUc&chco=76A4FB&chls=2.0&chs=400x150&chxs=0,0000dd,10|1,0000dd,12,0
		//	http://chart.apis.google.com/chart?chd=s:cEAELFJHHHKUju9uuXUc&chxs=0,0000dd,10|1,0000dd,12,0
		// http://chart.apis.google.com/chart?chxt=x,x&chxl=1:||Mar|Avr||0:|1st|15th|1st|15th|1st&cht=lc&
		//println a;
		return points;
	}
	
	def getPlayersEvolution(players) {
		def player
		def point
		def points = [][]
		
		def colors = ["FF9900","0000FF","00FF00","000000","FF0000"]
		//	    * FF0000 = red
		//	    * 00FF00 = green
		//	    * 0000FF = blue
		//	    * 000000 = black
		//	    * FFFFFF = white
		//	    * FF9900 = jaune
		
		def listMatchs = MatchUtil.instance.getMatchs()
		players.each{
			player = it
			println player.name
			listMatchs.each{
				point = getPlayerPoints(it.playersWithPoints, player.id as String);
				println point
				if(point != null) points[player.name] << point;
			}
			points[player.name] << player.points
		}
		
		players.each{
			println points[it.name]
		}
		
		//		def allpoints = points.join(",")
		//		def pointmin = points.min()
		//		def pointmax = points.max()
		//		
		//		String a = "http://chart.apis.google.com/chart?chs=500x200&cht=lc&chd=t:${allpoints}&chds=600,750&cht=lc&chxt=y&chxl=0:|600|650|700|750&chxp=600,650,700,750|&chtt=${player.name}"
		//couleur bleuté : &chco=76A4FB
		//style : &cht=lc
		//label sur x : chxt=x,x
		//deux labels sur x donc valeurs indexés : chxl=1:||Mar|Avr||0:|1st|15th|1st|15th|1st
		//taille du graphe : &chs=400x150
		//?? : &chls=2.0
		//min max en ordonnées pour la taille : &chds=647.5,680.5
		
		//		def debut = getDateMonth(listMatchs[0].jour)
		//		println debut
		//		def diffDates = listMatchs[-1].jour - debut
		//	    println diffDates
		
		//	    def info = ""
		//	    def i = 0;
		//	    diffDates.times{
		//			debut++
		//			println getDateToString(debut)
		//			if(i == 1 || i == 15) {
		//			info += "300,"
		//				
		//			} else {
		//				info += "650,"
		//			}
		//			i++
		//		}
		//		println info
		//	http://chart.apis.google.com/chart?chxt=x,x&chxl=1:||Mar|Avr||0:|1st|15th|1st|15th|1st&cht=lc&chd=s:cEAELFJHHHKUju9uuXUc&chco=76A4FB&chls=2.0&chs=400x150&chxs=0,0000dd,10|1,0000dd,12,0
		//	http://chart.apis.google.com/chart?chd=s:cEAELFJHHHKUju9uuXUc&chxs=0,0000dd,10|1,0000dd,12,0
		// http://chart.apis.google.com/chart?chxt=x,x&chxl=1:||Mar|Avr||0:|1st|15th|1st|15th|1st&cht=lc&
		//println a;
		//return a;
		return points
	}
	
}
