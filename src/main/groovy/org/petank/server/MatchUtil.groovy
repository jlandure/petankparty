/**
 * 
 */
package org.petank.server

import org.petank.client.model.Match;
import java.util.Date;
import org.petank.client.model.PetankUser;
import org.petank.client.model.TypeVictoire;
import org.petank.client.model.TypeMatch;
import org.petank.client.model.Bareme;
import org.petank.server.PetankUserUtil;

/**
 * @author jlandure
 *
 */
@Singleton
class MatchUtil{

	private static def listMatchs
	private static String JOIN_PLAYER = ";"
	
		private static def APPLIED = false
	
	static List<Match> populate() {
		if(listMatchs == null) {
			listMatchs = new ArrayList<Match>()
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("CLC"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 13, 4, "26/03/2009", TypeMatch.NON_OFFICIEL)
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("CBO")], 13, 4, "30/03/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("CBO")], 13, 8, "30/03/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("BPT"),PetankUserUtil.getUser("CBO")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND")], 13, 11, "31/03/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("BPT"),PetankUserUtil.getUser("CBO")], 13, 6, "31/03/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], 13, 3, "01/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], 13, 4, "01/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], 9, 13, "01/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 13, 12, "02/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 13, 7, "02/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 5, 13, "02/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("CLC"),PetankUserUtil.getUser("HDG")], 13, 1, "06/04/2009", TypeMatch.NON_OFFICIEL)
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], 13, 5, "07/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], 4, 13, "07/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("CLC")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JLE")], 6, 13, "09/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("CLC")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JLE")], 13, 3, "09/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("GHT"),PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JLE")], 2, 13, "21/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("FEE")], 13, 9, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("JAY")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], 13, 12, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("JAY")], [PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JLE")], 6, 13, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("FEE")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], 13, 12, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], 13, 8, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], 13, 4, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], 9, 13, "22/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("JAY")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("CLC")], 0, 13, "23/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("JAY")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("CLC")], 8, 13, "23/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("SHS")], [PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("ADE")], 9, 13, "23/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("SEN")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("JAY")], 9, 13, "24/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("SEN")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("JAY")], 9, 13, "24/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JAY")], 6, 13, "29/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JAY")], 13, 6, "29/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JAY")], 13, 4, "29/04/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("JND")], 10, 13, "04/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("SHS")], [PetankUserUtil.getUser("JAY"),PetankUserUtil.getUser("ADE")], 13, 10, "04/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("FRT")], 5, 13, "06/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("FRT")], 13, 9, "06/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("PSR")], 13, 10, "06/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("RST")], 13, 7, "06/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("RST")], 13, 9, "06/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE")], [PetankUserUtil.getUser("RST")], 13, 8, "06/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("PSR")], [PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JLE")], 11, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("PSR")], [PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JLE")], 12, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("MSI"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("ADE")], 3, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("MSI"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("ADE")], 13, 10, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("JLE")], 11, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("ADE")], 13, 4, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("ADE")], 13, 0, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("ADE")], 4, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("ADE")], 9, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("ADE")], 8, 13, "07/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("ADE")], 13, 11, "14/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("ADE")], 6, 13, "14/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("ADE")], 13, 11, "14/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("FRT")], 9, 13, "15/05/2009", TypeMatch.NON_OFFICIEL)
			listMatchs << createMatch([PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("FRT")], 5, 13, "15/05/2009", TypeMatch.NON_OFFICIEL)
			listMatchs << createMatch([PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("BPT")], 13, 1, "15/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FEE"),PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("BPT")], 13, 3, "15/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FEE")], [PetankUserUtil.getUser("RST")], 13, 11, "15/05/2009", TypeMatch.NON_OFFICIEL)
			listMatchs << createMatch([PetankUserUtil.getUser("FEE")], [PetankUserUtil.getUser("BPT")], 13, 5, "15/05/2009", TypeMatch.NON_OFFICIEL)
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("EBT")], 10, 13, "15/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("ADE")], 7, 13, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("PSR"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("RST")], 4, 13, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JND")], 13, 2, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("PSR"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JND")], 11, 13, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("RST")], 9, 13, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JND")], 11, 13, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("PSR"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("BPT")], 12, 13, "19/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("GBE")], 13, 9, "20/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("MSI"),PetankUserUtil.getUser("RST")], 12, 13, "20/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("FRT"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("MSI"),PetankUserUtil.getUser("RST")], 13, 9, "20/05/2009")
			listMatchs << createMatch([PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("GBE")], 6, 13, "20/05/2009")
		}
		return listMatchs
	}
	
	static def makeDate(event) {
	    def eventDate = Calendar.getInstance()
		if (event != null) {
			def dmy = event.split("/").collect { num -> Integer.parseInt(num.trim()) }
			eventDate.set(dmy[2], dmy[1] - 1, dmy[0], 0, 0, 0)
		}
	    return eventDate.getTime()
	}
	
	static def getDateToString(date) {
		//le nouveau est mieux car c'est ordonné niveau temps
		return String.format('%tY/%<tm/%<td', date)
	}
	
	static def getDateToFrString(date) {
		//format FR
		return String.format('%td/%<tm/%<tY', date)
	}
	
	static Match createMatch(play1, play2, sc1, sc2, dateString=null, type=TypeMatch.OFFICIEL) {
		def date = makeDate(dateString)
		def match = new Match(score1:sc1, score2:sc2, typeMatch:type, jour:date, player1:"", player2:"", point1:0, point2:0, playersWithPoints:"");

		//on ne créé plus les données sur les points des joueurs (et moyennes) au moment de la création mais 
		//au moment où l'on applique les barêmes

		def concatId = {players -> players.collect({player -> player.id}).join(JOIN_PLAYER)}
		match.player1 = concatId(play1)
		match.player2 = concatId(play2)
		
		return match
	}
	
	static List getPlayersId(player) {
		//retourn un tableau de id en splittant
		return player.split(JOIN_PLAYER)
	}
	
	static List getPlayers(player) {
		//retourn les PetankUser à partir des id
		def id = getPlayersId(player)
		def players = []
		id.each{players << PetankUserUtil.getUserById(it as Long)}
		return players;
	}

	
	static List getPlayersIdFromComment(player) {
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
	
	static Map getPlayersWithPointsFromComment(player) {
		def data = getPlayersId(player)
		def id = [:]
		def printId = {
				def matcher = (it.trim() =~ /([0-9]+).*(\[)([0-9]+\.*[0-9]*)(\])/)
				if (matcher.matches()) {
					//println(matcher[0][1] + ">" + matcher[0][3])
					id."${matcher[0][1]}" = matcher[0][3]
				}
			}
		data.each(printId)
		return id;
	}
	
	static Float getBetween(Match match) {
		return (match.point1 - match.point2).abs()
	}
	
	static void applyMatch(Match match) {
		
		def victory = match.score1 > match.score2
		def fanny1 = (victory && match.score2 == 0) 
		def fanny2 = (!victory && match.score1 == 0) 
		
		//preparation des baremes :
		def player1 = getPlayers(match.player1)
		def player2 = getPlayers(match.player2)
		
		//maj des id/points joueurs pour sauvegarde
		player1.each{
			match.playersWithPoints += it.id+ " ["+it.points+"];"
			match.point1 += it.points
			it.partiesJoues++;
			it.totalPoints += match.score1
			if(victory) {
				it.partiesGagnes++
			} else {
				it.partiesPerdus++
			}
			if(fanny1) it.fannyGagnes++
			if(fanny2) it.fannyPerdus++
			if(match.isOfficiel()) it.nbMatchOfficiel++
		}
		player2.each{
			match.playersWithPoints += it.id+ " ["+it.points+"];"
			match.point2 += it.points
			it.partiesJoues++;
			it.totalPoints += match.score2
			if(!victory) {
				it.partiesGagnes++
			} else {
				it.partiesPerdus++
			}
			if(fanny2) it.fannyGagnes++
			if(fanny1) it.fannyPerdus++
			if(match.isOfficiel()) it.nbMatchOfficiel++
		}
		match.playersWithPoints = match.playersWithPoints[0..-2]
		//calcul des points moyens
		match.point1 /= player1.size()
		
		match.point2 /= player2.size()
		//println match.playersWithPoints
		
		//récupération du bareme à appliquer
		Bareme bareme = BaremeUtil.chooseBareme(MatchUtil.getBetween(match))
		match.bareme = bareme
		def coefficient = BaremeUtil.getCoefficient(match.typeMatch)
		
		if(match.point1 >= match.point2) {
			if(victory) {
				match.typeVictoire = TypeVictoire.NORMAL 
				player1.each {
					it.points += bareme.victoireNormale * coefficient
					it.victoireNormale++
				}
				player2.each {
					it.points += bareme.defaiteNormale * coefficient
					it.defaiteNormale++
				}
			} else {
				match.typeVictoire = TypeVictoire.ANORMAL 
				player1.each {
					it.points += bareme.defaiteAnormale * coefficient
					it.defaiteAnormale++
				}
				player2.each {
					it.points += bareme.victoireAnormale * coefficient
					it.victoireAnormale++
				}
			}
		} else {
			if(victory) {
				match.typeVictoire = TypeVictoire.ANORMAL
				player1.each {
					it.points += bareme.victoireAnormale * coefficient
					it.victoireAnormale++
				}
				player2.each {
					it.points += bareme.defaiteAnormale * coefficient
					it.defaiteAnormale++
				}
			} else {
				match.typeVictoire = TypeVictoire.NORMAL
				player1.each {
					it.points += bareme.defaiteNormale * coefficient
					it.defaiteNormale++
				}
				player2.each {
					it.points += bareme.victoireNormale * coefficient
					it.victoireNormale++
				}
			}
		}
	}
	
	static Float getPlayerPoints(playersWithPoints, String id) {
		def points = getPlayersWithPointsFromComment(playersWithPoints)
		return (points.(""+id))?.toFloat()
	}
	
	static def getPlayerEvolution(player) {
		def point
		def points = []
		listMatchs.each{
			point = getPlayerPoints(it.playersWithPoints, player.id as String);
			if(point != null) points << point;
		}
		points << player.points
		def allpoints = points.join(",")
		def pointmin = points.min()
		def pointmax = points.max()
		
		String a = "http://chart.apis.google.com/chart?chs=500x200&cht=lc&chd=t:${allpoints}&chds=${pointmin},${pointmax}&cht=lc"
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
		return a;
	}
	
}
