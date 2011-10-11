/**
 * 
 */
package org.petank.server

import org.petank.server.BaremeUtil;
import org.petank.server.MatchUtil;
import org.petank.server.DateUtil;
import org.petank.server.PetankUserUtil;
import org.petank.server.PetankPlaceUtil;
import java.util.Date;
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

	def deleteMatch(Match match, listUsers) {
		def listBaremes = BaremeUtil.instance.getBaremes()
		//preparation des baremes :
		def player1 = MatchUtil.instance.getPlayers(match.player1, listUsers)
		def player2 = MatchUtil.instance.getPlayers(match.player2, listUsers)
		def victory = match.score1 > match.score2

		//récupération du bareme à appliquer
		Bareme bareme = BaremeUtil.instance.getBaremeById(match.idBareme)

		if(match.point1 >= match.point2) {
			if(victory) {
				deleteMatchNormal match, bareme, player1, player2, match.score1, match.score2
			} else {
				deleteMatchAnormal match, bareme, player2, player1, match.score2, match.score1
			}
		} else {
			if(victory) {
				deleteMatchAnormal match, bareme, player1, player2, match.score1, match.score2
			} else {
				deleteMatchNormal match, bareme, player2, player1, match.score2, match.score1
			}
		}
		player1.each{
			DAOManager.instance.save(it)
		}
		player2.each{
			DAOManager.instance.save(it)
		}
		DAOManager.instance.delete(match)
	}

	def deleteMatchAnormal(match, bareme, playerWinner, playerLooser, scoreWinner, scoreLooser) {
		def fanny = (scoreLooser == 0)
		def coefficient = BaremeUtil.instance.getCoefficient(match.typeMatch)
		playerLooser.each {
			it.partiesJoues--
			it.totalPoints -= scoreLooser
			it.partiesPerdus--
			if(match.isOfficiel()) it.nbMatchOfficiel--
			if(fanny) {
				it.fannyPerdus--
			}
			it.points -= bareme.defaiteAnormale * coefficient
			it.defaiteAnormale--
		}
		playerWinner.each {
			it.partiesJoues--
			it.totalPoints -= scoreWinner
			it.partiesGagnes--
			if(match.isOfficiel()) it.nbMatchOfficiel--
			if(fanny) {
				it.points -= bareme.victoireAnormale * coefficient * 1.5
				it.fannyGagnes--
			} else {
				it.points -= bareme.victoireAnormale * coefficient
			}
			it.victoireAnormale--
		}
	}

	def deleteMatchNormal(match, bareme, playerWinner, playerLooser, scoreWinner, scoreLooser) {
		def fanny = (scoreLooser == 0)
		def coefficient = BaremeUtil.instance.getCoefficient(match.typeMatch)
		playerWinner.each {
			it.partiesJoues--
			it.totalPoints -= scoreWinner
			it.partiesGagnes--
			if(match.isOfficiel()) it.nbMatchOfficiel--
			if(fanny) {
				it.points -= bareme.victoireNormale * coefficient * 1.5
				it.fannyGagnes--
			} else {
				it.points -= bareme.victoireNormale * coefficient
			}
			it.victoireNormale--
		}
		playerLooser.each {
			it.partiesJoues--
			it.totalPoints -= scoreLooser
			it.partiesPerdus--
			if(match.isOfficiel()) it.nbMatchOfficiel--
			if(fanny) {
				it.fannyPerdus--
			}
			it.points -= bareme.defaiteNormale * coefficient
			it.defaiteNormale--
		}
	}

	def applyMatchAnormal(match, bareme, playerWinner, playerLooser, fannny) {
		def coefficient = BaremeUtil.instance.getCoefficient(match.typeMatch)
		playerWinner.each {
			if(fannny) {
				it.points +=  bareme.victoireAnormale * coefficient * 1.5
				it.fannyGagnes++
			} else {
				it.points +=  bareme.victoireAnormale * coefficient
			}
			it.victoireAnormale++
			it.partiesGagnes++
			it.partiesJoues++;
			if(match.isOfficiel()) it.nbMatchOfficiel++
		}
		playerLooser.each {
			it.points += bareme.defaiteAnormale * coefficient
			if(fannny) {
				it.fannyPerdus++
			}
			it.defaiteAnormale++
			it.partiesPerdus++
			it.partiesJoues++;
			if(match.isOfficiel()) it.nbMatchOfficiel++
		}
	}

	def applyMatchNormal(match, bareme, playerWinner, playerLooser, fannny) {
		def coefficient = BaremeUtil.instance.getCoefficient(match.typeMatch)
		playerWinner.each {
			if(fannny) {
				it.points += bareme.victoireNormale * coefficient * 1.5
				it.fannyGagnes++
			} else {
				it.points += bareme.victoireNormale * coefficient
			}
			it.victoireNormale++
			it.partiesGagnes++
			it.partiesJoues++;
			if(match.isOfficiel()) it.nbMatchOfficiel++
		}
		playerLooser.each {
			it.points += bareme.defaiteNormale * coefficient
			if(fannny) {
				it.fannyPerdus++
			}
			it.defaiteNormale++
			it.partiesPerdus++
			it.partiesJoues++;
			if(match.isOfficiel()) it.nbMatchOfficiel++
		}
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
			it.totalPoints += match.score1
		}
		player2.each{
			match.playersWithPoints += it.id+ " ["+it.points+"];"
			match.point2 += it.points
			it.totalPoints += match.score2
		}
		match.playersWithPoints = match.playersWithPoints[0..-2]
		//calcul des points moyens
		match.point1 /= player1.size()

		match.point2 /= player2.size()
		//println match.playersWithPoints

		//récupération du bareme à appliquer
		Bareme bareme = BaremeUtil.instance.chooseBareme(this.getBetween(match), listBaremes)
		match.idBareme = bareme.id
		
		def player1points,player2points

		if(match.point1 >= match.point2) {
			if(victory) {
				match.typeVictoire = TypeVictoire.NORMAL
				applyMatchNormal(match, bareme, player1, player2, fanny1);
			} else {
				match.typeVictoire = TypeVictoire.ANORMAL
				applyMatchAnormal(match, bareme, player2, player1, fanny2);
			}
		} else {
			if(victory) {
				match.typeVictoire = TypeVictoire.ANORMAL
				applyMatchAnormal(match, bareme, player1, player2, fanny1);
			} else {
				match.typeVictoire = TypeVictoire.NORMAL
				applyMatchNormal(match, bareme, player2, player1, fanny2);
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
	
	def getBestTeamForPlayer(player) {
		def teamGagnant = [:] //new TreeMap( c )
		def teamPerdant = [:] // new TreeMap( c )
		def teamContreGagnant = [:] //new TreeMap( c )
		def teamContrePerdant = [:] // new TreeMap( c )
		def idJoueurTrie, idJoueurContreTrie
		def teamtemp
		def match
		def listMatchs = MatchUtil.instance.getMatchs()
		listMatchs.each{
			match = it
			//println match.player1 + " < " + player.name + " > " + match.player2  
			if(match.player1.contains(String.valueOf(player.id))) {
				idJoueurTrie = trieIdPlayer(match.player1)
				idJoueurContreTrie = trieIdPlayer(match.player2)
				if(match.score1 > match.score2) {
					addHistoryForTeam(teamGagnant, idJoueurTrie)
					addHistoryForTeam(teamContreGagnant, idJoueurContreTrie)
				} else {
					addHistoryForTeam(teamPerdant, idJoueurTrie)
					addHistoryForTeam(teamContrePerdant, idJoueurContreTrie)
				}
			} else if(match.player2.contains(String.valueOf(player.id))) {
				idJoueurTrie = trieIdPlayer(match.player2)
				idJoueurContreTrie = trieIdPlayer(match.player1)
				if(match.score1 < match.score2) {
					addHistoryForTeam(teamGagnant, idJoueurTrie)
					addHistoryForTeam(teamContreGagnant, idJoueurContreTrie)
				} else {
					addHistoryForTeam(teamPerdant, idJoueurTrie)
					addHistoryForTeam(teamContrePerdant, idJoueurContreTrie)
				}
			}
		}
		
		//println teamPerdant.toMapString()
		int numberReturn = 3;
		teamGagnant = teamGagnant.sort {a, b -> b.value <=> a.value}
		//println teamGagnant.toMapString()
		teamGagnant = teamGagnant.take(numberReturn)
		teamPerdant = teamPerdant.sort {a, b -> b.value <=> a.value}
		teamPerdant = teamPerdant.take(numberReturn)
		teamContreGagnant = teamContreGagnant.sort {a, b -> b.value <=> a.value}
		teamContreGagnant = teamContreGagnant.take(numberReturn)
		teamContrePerdant = teamContrePerdant.sort {a, b -> b.value <=> a.value}
		teamContrePerdant = teamContrePerdant.take(numberReturn)
		//println teamPerdant.toMapString()
		return [teamGagnant, teamPerdant, teamContreGagnant, teamContrePerdant]
		
	}
	
	def addHistoryForTeam(team, idJoueurTrie) {
		def element = team[idJoueurTrie]
		if(element == null) {
			team.put(idJoueurTrie, 1)
		} else {
			team.put(idJoueurTrie, element + 1)
		}
	}
	
	def trieIdPlayer(String listIdNonTrie) {
		def listId = MatchUtil.instance.getPlayersId(listIdNonTrie);
		listId.sort();
		def concatId = {players -> players.collect({id -> id}).join(MatchUtil.JOIN_PLAYER)}
		def listIdtriee = concatId(listId)
		return listIdtriee;
	}
}
