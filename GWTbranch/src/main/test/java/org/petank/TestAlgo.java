package org.petank;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.petank.client.model.Bareme;
import org.petank.client.model.Match;
import org.petank.client.model.PetankGroup;
import org.petank.client.model.PetankUser;
import org.petank.server.BaremeUtil;
import org.petank.server.DateUtil;
import org.petank.server.MatchUtil;
import org.petank.server.PetankGroupUtil;
import org.petank.server.PetankPlaceUtil;
import org.petank.server.PetankUserUtil;

public class TestAlgo extends TestCase {

	
	private List<Bareme> listBaremes;
	
	private List<PetankUser> listUsers;
	private List<PetankGroup> listGroups;
	private List<Match> listMatchs;
	
//	@Override
//	protected void setUp() throws Exception {
//		
//		
//	}
//
//	public void testChooseBareme() {
//		listBaremes = BaremeUtil.populate();
//		System.out.println(listBaremes.size());
//		
//		System.out.println(BaremeUtil.chooseBareme(2F).getMinimum());
//		System.out.println(BaremeUtil.chooseBareme(156F).getMinimum());
//		System.out.println(BaremeUtil.chooseBareme(605F).getMinimum());
//	}
//	
//	public void testAddMatch() {
//		listUsers = PetankUserUtil.populate();
//		listMatchs = MatchUtil.populate();
//		listBaremes = BaremeUtil.populate();
//		
//		
//		Match match = MatchUtil.createMatch(PetankUserUtil.getTest1(), PetankUserUtil.getTest2(), 13F, 12F, 1);
//		System.out.println(PetankUserUtil.getTest1());
//		System.out.println(match.getPlayer1());
//		System.out.println(match.getPoint1());
//		System.out.println(match.getPlayer2());
//		System.out.println(match.getPoint2());
//		
//		
//		//user 1 (GBE SHS 425 : 13 - 12 user 2 ADE CLC 825
//		//bareme : 400
//		//System.out.println(MatchUtil.getBetween(match));
//		Bareme bareme = BaremeUtil.chooseBareme(MatchUtil.getBetween(match));
//		System.out.println(bareme.getVictoireNormale());
//		System.out.println(bareme.getDefaiteNormale());
//		System.out.println(bareme.getVictoireAnormale());
//		System.out.println(bareme.getDefaiteAnormale());
//		MatchUtil.applyMatch(match);
//		System.out.println(PetankUserUtil.getTest1()[0].getName() +" - "+PetankUserUtil.getTest1()[0].getPoints());
//		System.out.println(PetankUserUtil.getTest1()[1].getName() +" - "+PetankUserUtil.getTest1()[1].getPoints());
//		System.out.println(PetankUserUtil.getTest2()[0].getName() +" - "+PetankUserUtil.getTest2()[0].getPoints());
//		System.out.println(PetankUserUtil.getTest2()[1].getName() +" - "+PetankUserUtil.getTest2()[1].getPoints());
//
//		MatchUtil.populate();
//		
//		
//	}
	
	public void testAll() {
		PetankGroupUtil.populate();
		PetankUserUtil.populate();
		PetankPlaceUtil.populate();
		MatchUtil.populate();
		listBaremes = BaremeUtil.populate();
		
		PetankGroup group = PetankGroupUtil.getGroup("euriware");
		PetankGroupUtil.prepareGroup(group);
		listUsers = group.getListUsers();
		listMatchs = group.getListMatchs();
		
//		for(Match match : listMatchs) {
//			
//			MatchUtil.applyMatch(match);
//			//MatchUtil.getPlayers(match.getPlayer1());
//			//MatchUtil.getPlayersWithPoints(match.getPlayer1());
//		}
//		
//		listUsers = PetankUserUtil.sortByPoint(listUsers);
		int i = 1;
		for(PetankUser user : listUsers) {
			System.out.println(i+"-"+user.getName() +" - "+user.getPoints());
			i++;
		}
		
		MatchUtil.getPlayerEvolution(PetankUserUtil.getUser("JLE", "euriware"));
		//MatchUtil.getPlayerEvolution(PetankUserUtil.getUser("GBE"));
		//MatchUtil.getPlayerEvolution(PetankUserUtil.getUser("RST"));
		//MatchUtil.getPlayerEvolution(PetankUserUtil.getUser("EBT"));
		//MatchUtil.getPlayerEvolution(PetankUserUtil.getUser("JND"));
		//System.out.println(MatchUtil.getPlayersEvolution(PetankUserUtil.getUsers("jle,gbe,rst", "euriware")));
		//MatchUtil.getPlayerEvolution(
		System.out.println(DateUtil.getDateToGoogleDateString(new Date()));
		System.out.println(DateUtil.getDateToFrString(new Date()));
		System.out.println(DateUtil.getDateToString(new Date()));
		
		Date date = MatchUtil.populate().get(MatchUtil.populate().size()-1).getJour();//01/06/09
		System.out.println(DateUtil.getDateToGoogleDateString(date));
		System.out.println(DateUtil.getDateToFrString(date));
		System.out.println(DateUtil.getDateToString(date));
	}

}
