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
public class MatchUtil{

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
		def match = new Match(lstplayer1:play1, lstplayer2:play2, score1:sc1, score2:sc2, coefficient:coef, jour:new Date(), player1:"", player2:"", point1:0, point2:0);
		//maj des id joueurs
		play1.each{
			match.player1 += it.id+ " ["+it.points+"];" 
		}
		play2.each{
			match.player2 += it.id+ " ["+it.points+"];" 
		}
		//calcul des points moyens
		play1.each{ match.point1 += it.points}
		match.point1 /= play1.size()
		
		play2.each{ match.point2 += it.points}
		match.point2 /= play2.size()
		return match
	}
	
	static Float getBetween(Match match) {
		return (match.point1 - match.point2).abs()
	}
	
	static void applyBareme(Match match, Bareme bareme, player1, player2) {
		def victory;
		if(match.score1 == 13) {
			victory = 1
		}
		if(match.score2 == 13) {
			victory = 2
		}
		
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
