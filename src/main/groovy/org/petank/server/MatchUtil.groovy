/**
 * 
 */
package org.petank.server

import org.petank.client.model.Match;
import java.util.Date;
import org.petank.client.model.PetankUser;
import org.petank.client.model.Bareme;
import org.petank.server.PetankUserUtil;

/**
 * @author jlandure
 *
 */
@Singleton
class MatchUtil{

	private static def listMatchs = new ArrayList<Match>()
	
	static List<Match> populate() {
		listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("CLC"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 13, 4, 0.5)
		listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("CBO")], 13, 4, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("CBO")], 13, 8, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("BPT"),PetankUserUtil.getUser("CBO")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND")], 13, 11, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("BPT"),PetankUserUtil.getUser("CBO")], 13, 6, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], 13, 3, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], 13, 4, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("EBT")], [PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JND"),PetankUserUtil.getUser("BPT")], 9, 13, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 13, 12, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 13, 7, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], [PetankUserUtil.getUser("JLE"),PetankUserUtil.getUser("JND")], 5, 13, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("EBT"),PetankUserUtil.getUser("JLE")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("CLC"),PetankUserUtil.getUser("HDG")], 13, 1, 0.5)
		listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], 13, 5, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE")], 4, 13, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("CLC")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JLE")], 6, 13, 1)
		listMatchs << createMatch([PetankUserUtil.getUser("SHS"),PetankUserUtil.getUser("RST"),PetankUserUtil.getUser("CLC")], [PetankUserUtil.getUser("GBE"),PetankUserUtil.getUser("ADE"),PetankUserUtil.getUser("JLE")], 13, 3, 1)
	}
	
	static Match createMatch(play1, play2, sc1, sc2, coef) {
		def match = new Match(score1:sc1, score2:sc2, coefficient:coef, jour:new Date(), player1:"", player2:"", point1:0, point2:0);
		//maj des id joueurs
		play1.each{
			match.player1 += it.id+ " ["+it.points+"];"
			match.point1 += it.points
		}
		play2.each{
			match.player2 += it.id+ " ["+it.points+"];"
			match.point2 += it.points
		}
		//calcul des points moyens
		match.point1 /= play1.size()
		
		match.point2 /= play2.size()
		return match
	}
	
	static List getPlayersId(player) {
		//def names = "JLE [3]; SHS [4];GBE[55];RST [9] ".split(";")
		//println player
		def data = player.split(";")
		def id = []
		
		def printId = {
				def matcher = (it.trim() =~ /([0-9]+).*(\[)([0-9]+\.*[0-9]*)(\])/);
					//println matcher[0][1]+ " " + matcher[0][3];
				if (matcher.matches()) {
					id << matcher[0][1];
				}
			}
		data.each(printId)
		//id.each{println(it)}
		return id;
	}
	
	static List getPlayers(player) {
		PetankUserUtil.getUserById(3);
		def id = getPlayersId(player)
		//println id.join("-")
		def players = []
		//id.each{println it}
		id.each{players << PetankUserUtil.getUserById(it as Long)}
		//players.each{println it.name}
		return players;
	}
	
	static Map getPlayersWithPoints(player) {
		//def names = "JLE [3]; SHS [4];GBE[55];RST [9] ".split(";").each{it.trim()}
		def data = player.split(";")
		def id = [:]
		
		def printId = {
				def matcher = (it.trim() =~ /([0-9]+).*(\[)([0-9]+)(\])/);
				if (matcher.matches()) {
					println(matcher[0][1] + ">" + matcher[0][3])
					id."${matcher[0][1]}" = matcher[0][3]
				}
			}
		data.each(printId)
		//println(id)
		//id.each{println(it + " [" + it[1]+"]")}
		return id;
		//player1.split(";").each(printClosure)
	}
	
	static Float getBetween(Match match) {
		return (match.point1 - match.point2).abs()
	}
	
	static void applyBareme(Match match, Bareme bareme) {
		def victory;
		if(match.score1 > match.score2) {
			victory = 1
		}
		if(match.score1 < match.score2) {
			victory = 2
		}
		
		def player1 = getPlayers(match.player1);
		def player2 = getPlayers(match.player2);
		
		if(match.point1 >= match.point2) {
			if(victory == 1) {
				player1.each {
					it.points += bareme.victoireNormale * match.coefficient
				}
				player2.each {
					it.points += bareme.defaiteNormale * match.coefficient
				}
			} else {
				player1.each {
					it.points += bareme.victoireAnormale * match.coefficient
				}
				player2.each {
					it.points += bareme.defaiteAnormale * match.coefficient
				}
			}
		} else {
			if(victory == 1) {
				player1.each {
					it.points += bareme.victoireAnormale * match.coefficient
				}
				player2.each {
					it.points += bareme.defaiteAnormale * match.coefficient
				}
			} else {
				player1.each {
					it.points += bareme.victoireNormale * match.coefficient
				}
				player2.each {
					it.points += bareme.defaiteNormale * match.coefficient
				}
			}
		}
	}
}
