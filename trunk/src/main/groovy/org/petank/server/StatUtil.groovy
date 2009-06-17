/**
 * 
 */
package org.petank.server

import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.DateUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.PetankPlaceUtil;
import org.petank.client.model.Match;
import java.util.Date;
import org.petank.client.model.PetankUser;
import org.petank.client.model.TypeVictoire;
import org.petank.client.model.TypeMatch;
import org.petank.client.model.Bareme;

/**
 * @author jlandure
 *
 */
@Singleton
public class StatUtil{

	static Float getBetween(Match match) {
		return (match.point1 - match.point2).abs()
	}
	
	static void applyMatch(Match match) {
		
		def victory = match.score1 > match.score2
		def fanny1 = (victory && match.score2 == 0) 
		def fanny2 = (!victory && match.score1 == 0) 
		
		//preparation des baremes :
		def player1 = MatchUtil.getPlayers(match.player1)
		def player2 = MatchUtil.getPlayers(match.player2)
		
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
		Bareme bareme = BaremeUtil.chooseBareme(StatUtil.getBetween(match))
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
	
	static def getPlayerEvolution(player, listMatchs=null) {
		def point
		def points = []
		if(listMatchs == null) {
			listMatchs = MatchUtil.getMatchByPlayerNameAndGroupName(player.name, player.group.name)
		}
		listMatchs.each{
			point = getPlayerPoints(it.playersWithPoints, player.id as String);
			if(point != null) points << point;
		}
		points << player.points
		return points;
	}
	
}
