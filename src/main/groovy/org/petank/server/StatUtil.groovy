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
import org.petank.server.dao.DAOManager

/**
 * @author jlandure
 *
 */
@Singleton
public class StatUtil {

	static StatUtil getInstance() {
		return instance
	}
	
	Float getBetween(Match match) {
		return (match.point1 - match.point2).abs()
	}
	
	def applyMatchs(listMatchs, listUsers) {
		//listMatchs = MatchUtil.sortByDate(listMatchs)
		def listBaremes = BaremeUtil.instance.getBaremes()
		listMatchs.each {
			this.applyMatch(it, listUsers, listBaremes)
			listUsers = PetankUserUtil.instance.sortByPoint(listUsers)
		}
		return listUsers
	}
	
	def applyMatch(Match match, listUsers, listBaremes) {
		def victory = match.score1 > match.score2
		def fanny1 = (victory && match.score2 == 0) 
		def fanny2 = (!victory && match.score1 == 0) 
		
		//preparation des baremes :
		def player1 = MatchUtil.instance.getPlayers(match.player1, listUsers)
		def player2 = MatchUtil.instance.getPlayers(match.player2, listUsers)
		
		//maj progression
		listUsers.each{
			this.applyProgression(it, match.jour)
		}
		
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
		Bareme bareme = BaremeUtil.instance.chooseBareme(this.getBetween(match), listBaremes)
		match.idBareme = bareme.id	
		def coefficient = BaremeUtil.instance.getCoefficient(match.typeMatch)
		
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
		player1.each{
			DAOManager.instance.save(it)
		}
		player2.each{
			DAOManager.instance.save(it)
		}
		DAOManager.instance.save(match)
	}
	
	def applyProgression(player, jour) {
		if(player.dayBefore == null) {
			player.dayBefore = jour
		} else {
			if(DateUtil.instance.getDateToString(player.dayBefore) < DateUtil.instance.getDateToString(jour)) {
				player.pointsDayBefore = player.points
				player.placeDayBefore = PetankUserUtil.instance.getClassementUser(player)
				player.dayBefore = jour
			}
		}
	}
	
	def getBestTeam() {
		def team = [:]
		def teamtemp
		def match
		def listMatchs = MatchUtil.instance.getMatchs()
		listMatchs.each{
			match = it
			if(match.score1 > match.score2) {
				teamtemp = team[match.player1]
				if(teamtemp == null) {
					team[match.player1] = 1
				} else {
					team[match.player1] = team[match.player1] + 1
				}
				println team[match.player1]
			} else {
				teamtemp = team[match.player2]
				if(teamtemp == null) {
					team[match.player2] = 1
				} else {
					team[match.player2] = team[match.player2] + 1
				}
				println team[match.player2]
			}
		}
		println team.toMapString()
		def keys = lang.keySet().sort {lang[it]}
		println keys
		//def mc= [compare:{a,b-> a.value.compareTo(b.value)}] as Comparator
		//team.sort(mc)
		println team.findAll{it.value > 10}
		return team.toMapString()
	}
	
}