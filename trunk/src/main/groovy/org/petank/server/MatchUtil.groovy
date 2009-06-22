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
import org.petank.server.DateUtil;
import org.petank.server.dao.DAOManager


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
		listMatchs.addAll(this.populate2())
		listMatchs.addAll(this.populate3())
		listMatchs.addAll(this.populate4())
		listMatchs.addAll(this.populate5())
		listMatchs.addAll(this.populate6())
		listMatchs.addAll(this.populate7())
		listMatchs.addAll(this.populate8())
		listMatchs.addAll(this.populate9())
		return listMatchs
	}
	
	List<Match> populate1() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["SHS","RST","EBT"], ["GBE","CLC","JLE","JND"], 13, 4, "26/03/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["JLE","JND","BPT"], ["ADE","EBT","CBO"], 13, 4, "30/03/2009")
		listMatchs << createMatch("euriware", ["JLE","JND","BPT"], ["ADE","EBT","CBO"], 13, 8, "30/03/2009")
		listMatchs << createMatch("euriware", ["JLE","BPT","CBO"], ["GBE","ADE","JND"], 13, 11, "31/03/2009")
		listMatchs << createMatch("euriware", ["GBE","ADE","JND"], ["JLE","BPT","CBO"], 13, 6, "31/03/2009")
		listMatchs << createMatch("euriware", ["GBE","JLE","EBT"], ["ADE","JND","BPT"], 13, 3, "01/04/2009")
		listMatchs << createMatch("euriware", ["GBE","JLE","EBT"], ["ADE","JND","BPT"], 13, 4, "01/04/2009")
		listMatchs << createMatch("euriware", ["GBE","JLE","EBT"], ["ADE","JND","BPT"], 9, 13, "01/04/2009")
		listMatchs << createMatch("euriware", ["GBE","ADE"], ["JLE","JND"], 13, 12, "02/04/2009")
		listMatchs << createMatch("euriware", ["GBE","ADE"], ["JLE","JND"], 13, 7, "02/04/2009")
		listMatchs << createMatch("euriware", ["GBE","ADE"], ["JLE","JND"], 5, 13, "02/04/2009")
		listMatchs << createMatch("euriware", ["SHS","RST","EBT","JLE"], ["GBE","ADE","CLC","HDG"], 13, 1, "06/04/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["SHS","RST"], ["GBE","ADE"], 13, 5, "07/04/2009")
		listMatchs << createMatch("euriware", ["SHS","RST"], ["GBE","ADE"], 4, 13, "07/04/2009")
		listMatchs << createMatch("euriware", ["SHS","RST","CLC"], ["GBE","ADE","JLE"], 6, 13, "09/04/2009")
		listMatchs << createMatch("euriware", ["SHS","RST","CLC"], ["GBE","ADE","JLE"], 13, 3, "09/04/2009")
		listMatchs << createMatch("euriware", ["SHS","GHT","JND"], ["GBE","RST","JLE"], 2, 13, "21/04/2009")
		listMatchs << createMatch("euriware", ["RST","JLE"], ["EBT","FEE"], 13, 9, "22/04/2009")
		listMatchs << createMatch("euriware", ["SHS","JAY"], ["GBE","ADE"], 13, 12, "22/04/2009")
		listMatchs << createMatch("euriware", ["SHS","JAY"], ["RST","JLE"], 6, 13, "22/04/2009")
		listMatchs << createMatch("euriware", ["EBT","FEE"], ["GBE","ADE"], 13, 12, "22/04/2009")
		return listMatchs
	}
	List<Match> populate2() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["JLE","GBE"], ["SHS","RST"], 13, 8, "22/04/2009")
		listMatchs << createMatch("euriware", ["JLE","GBE"], ["SHS","RST"], 13, 4, "22/04/2009")
		listMatchs << createMatch("euriware", ["JLE","GBE"], ["SHS","RST"], 9, 13, "22/04/2009")
		listMatchs << createMatch("euriware", ["RST","JND","JAY"], ["JLE","EBT","CLC"], 0, 13, "23/04/2009")
		listMatchs << createMatch("euriware", ["RST","JND","JAY"], ["JLE","EBT","CLC"], 8, 13, "23/04/2009")
		listMatchs << createMatch("euriware", ["FRT","SHS"], ["FEE","ADE"], 9, 13, "23/04/2009")
		listMatchs << createMatch("euriware", ["JND","ADE","SEN"], ["JLE","FRT","JAY"], 9, 13, "24/04/2009")
		listMatchs << createMatch("euriware", ["FRT","ADE","SEN"], ["JLE","JND","JAY"], 9, 13, "24/04/2009")
		listMatchs << createMatch("euriware", ["FRT","GBE","JLE"], ["EBT","RST","JAY"], 6, 13, "29/04/2009")
		listMatchs << createMatch("euriware", ["FRT","GBE","JLE"], ["EBT","RST","JAY"], 13, 6, "29/04/2009")
		listMatchs << createMatch("euriware", ["FRT","GBE","JLE"], ["EBT","RST","JAY"], 13, 4, "29/04/2009")
		listMatchs << createMatch("euriware", ["FRT","GBE"], ["EBT","JND"], 10, 13, "04/05/2009")
		listMatchs << createMatch("euriware", ["RST","SHS"], ["JAY","ADE"], 13, 10, "04/05/2009")
		listMatchs << createMatch("euriware", ["ADE","JLE"], ["FEE","FRT"], 5, 13, "06/05/2009")
		listMatchs << createMatch("euriware", ["ADE","JLE"], ["FEE","FRT"], 13, 9, "06/05/2009")
		listMatchs << createMatch("euriware", ["SHS","BPT"], ["JND","PSR"], 13, 10, "06/05/2009")
		listMatchs << createMatch("euriware", ["GBE"], ["RST"], 13, 7, "06/05/2009")
		listMatchs << createMatch("euriware", ["GBE"], ["RST"], 13, 9, "06/05/2009")
		listMatchs << createMatch("euriware", ["GBE"], ["RST"], 13, 8, "06/05/2009")
		listMatchs << createMatch("euriware", ["GBE","PSR"], ["RST","JLE"], 11, 13, "07/05/2009")
		listMatchs << createMatch("euriware", ["GBE","PSR"], ["RST","JLE"], 12, 13, "07/05/2009")
		return listMatchs
	}
	List<Match> populate3() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["JND","MSI","BPT"], ["FEE","FRT","ADE"], 3, 13, "07/05/2009")
		listMatchs << createMatch("euriware", ["JND","MSI","BPT"], ["FEE","FRT","ADE"], 13, 10, "07/05/2009")
		listMatchs << createMatch("euriware", ["RST"], ["JLE"], 11, 13, "07/05/2009")
		listMatchs << createMatch("euriware", ["RST","JLE"], ["JND","ADE"], 13, 4, "07/05/2009")
		listMatchs << createMatch("euriware", ["RST","JND"], ["JLE","ADE"], 13, 0, "07/05/2009")
		listMatchs << createMatch("euriware", ["JND"], ["ADE"], 4, 13, "07/05/2009")
		listMatchs << createMatch("euriware", ["JND"], ["ADE"], 9, 13, "07/05/2009")
		listMatchs << createMatch("euriware", ["JND"], ["ADE"], 8, 13, "07/05/2009")
		listMatchs << createMatch("euriware", ["JLE","EBT"], ["RST","ADE"], 13, 11, "14/05/2009")
		listMatchs << createMatch("euriware", ["JLE","EBT"], ["RST","ADE"], 6, 13, "14/05/2009")
		listMatchs << createMatch("euriware", ["JLE","EBT"], ["RST","ADE"], 13, 11, "14/05/2009")
		listMatchs << createMatch("euriware", ["JLE"], ["FRT"], 9, 13, "15/05/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["EBT"], ["FRT"], 5, 13, "15/05/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["FEE","RST"], ["ADE","BPT"], 13, 1, "15/05/2009")
		listMatchs << createMatch("euriware", ["FEE","RST"], ["ADE","BPT"], 13, 3, "15/05/2009")
		listMatchs << createMatch("euriware", ["FEE"], ["RST"], 13, 11, "15/05/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["FEE"], ["BPT"], 13, 5, "15/05/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["FRT","JLE"], ["ADE","EBT"], 10, 13, "15/05/2009")
		listMatchs << createMatch("euriware", ["JND","RST","BPT"], ["EBT","FRT","ADE"], 7, 13, "19/05/2009")
		listMatchs << createMatch("euriware", ["PSR","JLE"], ["ADE","RST"], 4, 13, "19/05/2009")
		listMatchs << createMatch("euriware", ["EBT","BPT"], ["GBE","JND"], 13, 2, "19/05/2009")
		return listMatchs
	}
	List<Match> populate4() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["PSR","JLE"], ["GBE","JND"], 11, 13, "19/05/2009")
		listMatchs << createMatch("euriware", ["EBT","BPT"], ["ADE","RST"], 9, 13, "19/05/2009")
		listMatchs << createMatch("euriware", ["RST","ADE"], ["GBE","JND"], 11, 13, "19/05/2009")
		listMatchs << createMatch("euriware", ["PSR","JLE"], ["EBT","BPT"], 12, 13, "19/05/2009")
		listMatchs << createMatch("euriware", ["FRT","JLE"], ["EBT","GBE"], 13, 9, "20/05/2009")
		listMatchs << createMatch("euriware", ["ADE","BPT"], ["MSI","RST"], 12, 13, "20/05/2009")
		listMatchs << createMatch("euriware", ["FRT","JLE"], ["MSI","RST"], 13, 9, "20/05/2009")
		listMatchs << createMatch("euriware", ["ADE","BPT"], ["EBT","GBE"], 6, 13, "20/05/2009")
		listMatchs << createMatch("euriware", ["ADE","FRT","GBE"], ["JLE","RST","EBT"], 1, 13, "20/05/2009")
		listMatchs << createMatch("euriware", ["ADE","FRT","EBT"], ["JLE","RST","GBE"], 2, 13, "20/05/2009")
		listMatchs << createMatch("euriware", ["ADE","GBE","EBT"], ["JLE","RST","FRT"], 5, 13, "20/05/2009")
		listMatchs << createMatch("euriware", ["ADE","EBT"], ["JLE","RST"], 13, 10, "20/05/2009")
		listMatchs << createMatch("euriware", ["JLE","PSR"], ["BPT","FRT"], 13, 10, "27/05/2009")
		listMatchs << createMatch("euriware", ["EBT","FEE"], ["GBE","JND"], 10, 13, "27/05/2009")
		listMatchs << createMatch("euriware", ["JLE","PSR"], ["GBE","JND"], 6, 13, "27/05/2009")
		listMatchs << createMatch("euriware", ["EBT","FEE"], ["BPT","FRT"], 11, 13, "27/05/2009")
		listMatchs << createMatch("euriware", ["JLE","PSR","FEE"], ["JAY","EBT"], 13, 10, "28/05/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["JLE","PSR","FEE"], ["JAY","EBT"], 13, 9, "28/05/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["PSR","FEE"], ["JND","EBT"], 4, 13, "29/05/2009")
		listMatchs << createMatch("euriware", ["GBE","BPT"], ["JLE","FEE"], 3, 13, "29/05/2009")
		listMatchs << createMatch("euriware", ["JND","FEE","GBE"], ["PSR","EBT","JLE"], 4, 13, "29/05/2009")
		return listMatchs
	}
	List<Match> populate5() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["JND","RST"], ["GBE","JLE"], 5, 13, "29/05/2009")
		listMatchs << createMatch("euriware", ["GBE","JAY"], ["FRT","PSR"], 13, 8, "01/06/2009")
		listMatchs << createMatch("euriware", ["RST","SHS"], ["ADE","JND"], 11, 13, "01/06/2009")
		listMatchs << createMatch("euriware", ["GBE","JAY"], ["ADE","JND"], 13, 2, "01/06/2009")
		listMatchs << createMatch("euriware", ["RST","SHS"], ["FRT","PSR"], 13, 6, "01/06/2009")
		listMatchs << createMatch("euriware", ["GBE","FRT"], ["ADE","RST"], 13, 12, "01/06/2009")
		listMatchs << createMatch("euriware", ["GBE","FRT"], ["ADE","RST"], 6, 13, "01/06/2009")
		listMatchs << createMatch("euriware", ["GBE","EBT","FRT"], ["ADE","FEE","RST"], 8, 13, "02/06/2009")
		listMatchs << createMatch("euriware", ["GBE","EBT","FRT"], ["ADE","FEE","RST"], 11, 13, "02/06/2009")
		listMatchs << createMatch("euriware", ["ADE","GBE"], ["RST","EBT"], 3, 13, "02/06/2009")
		listMatchs << createMatch("euriware", ["ADE","GBE"], ["RST","EBT"], 13, 11, "02/06/2009")
		listMatchs << createMatch("euriware", ["ADE","GBE"], ["RST","EBT"], 8, 13, "02/06/2009")
		listMatchs << createMatch("euriware", ["GBE","FEE"], ["JAY","ADE"], 13, 6, "03/06/2009")
		listMatchs << createMatch("euriware", ["GBE","EBT"], ["RST","CBO"], 13, 3, "03/06/2009")
		listMatchs << createMatch("euriware", ["CBO","JAY"], ["ADE","FEE"], 3, 13, "03/06/2009")
		listMatchs << createMatch("euriware", ["RST"], ["EBT"], 13, 10, "03/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["RST"], ["GBE"], 13, 9, "03/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["EBT"], ["ADE"], 12, 13, "03/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["FRT"], ["ADE"], 4, 13, "03/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["RST","FEE"], ["GBE","JAY"], 13, 3, "04/06/2009", "mainguais")
		listMatchs << createMatch("euriware", ["JLE","JND"], ["ADE","SHS"], 13, 0, "04/06/2009", "mainguais")
		return listMatchs
	}
	List<Match> populate6() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["RST","FEE"], ["JLE","JND"], 13, 9, "04/06/2009", "mainguais")
		listMatchs << createMatch("euriware", ["ADE","SHS"], ["GBE","JAY"], 13, 10, "04/06/2009", "mainguais")
		listMatchs << createMatch("euriware", ["JND","JLE","RST"], ["GBE","JAY","SHS"], 13, 1, "04/06/2009")
		listMatchs << createMatch("euriware", ["JND","JAY","RST"], ["GBE","JLE","SHS"], 9, 13, "04/06/2009")
		listMatchs << createMatch("euriware", ["RST","SHS"], ["CLC","JLE"], 13, 9, "05/06/2009")
		listMatchs << createMatch("euriware", ["BPT","GBE"], ["FEE","JAY"], 3, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["MSI","PSR"], ["ADE","JND"], 4, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["RST","SHS"], ["CLC","JLE"], 13, 8, "05/06/2009")
		listMatchs << createMatch("euriware", ["MSI","PSR"], ["FEE","JAY"], 4, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["BPT","GBE"], ["ADE","JND"], 13, 9, "05/06/2009")
		listMatchs << createMatch("euriware", ["RST","SHS"], ["CLC","JLE"], 13, 0, "05/06/2009")
		listMatchs << createMatch("euriware", ["ADE","JND"], ["FEE","JAY"], 7, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["BPT","GBE"], ["MSI","PSR"], 13, 6, "05/06/2009")
		listMatchs << createMatch("euriware", ["JND","GBE","ADE"], ["JLE","RST","FRT"], 4, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["JND","RST","ADE"], ["JLE","GBE","FRT"], 2, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["JND","RST","ADE"], ["JLE","GBE","FRT"], 8, 13, "05/06/2009")
		listMatchs << createMatch("euriware", ["JND","FRT"], ["JLE","ADE"], 13, 0, "05/06/2009")
		listMatchs << createMatch("euriware", ["ADE","FRT"], ["JLE","JND"], 13, 8, "05/06/2009")
		listMatchs << createMatch("euriware", ["RST"], ["GBE"], 13, 9, "09/06/2009")
		listMatchs << createMatch("euriware", ["RST"], ["GBE"], 13, 7, "09/06/2009")
		listMatchs << createMatch("euriware", ["RST"], ["GBE"], 7, 13, "09/06/2009")
		return listMatchs
	}
	List<Match> populate7() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["EBT","FEE"], ["JLE","PSR"], 11, 13, "09/06/2009")
		listMatchs << createMatch("euriware", ["EBT","PSR"], ["JLE","FEE"], 5, 13, "09/06/2009")
		listMatchs << createMatch("euriware", ["EBT","PSR"], ["JLE","FEE"], 3, 13, "09/06/2009")
		listMatchs << createMatch("euriware", ["ADE"], ["FEE"], 13, 7, "10/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["ADE"], ["EBT"], 13, 12, "10/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["EBT","ADE"], ["JLE","FEE"], 13, 11, "10/06/2009")
		listMatchs << createMatch("euriware", ["ADE","FEE","JAY"], ["EBT","JLE"], 3, 13, "11/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["ADE","FEE","EBT"], ["JAY","JLE"], 0, 13, "11/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["ADE","FEE"], ["EBT","JAY","JLE"], 11, 13, "11/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["FEE","EBT"], ["ADE","JLE"], 13, 10, "11/06/2009")
		listMatchs << createMatch("euriware", ["BPT","JAY"], ["SHS","JND"], 5, 13, "12/06/2009")
		listMatchs << createMatch("euriware", ["JLE","ADE"], ["EBT","GBE"], 13, 7, "12/06/2009")
		listMatchs << createMatch("euriware", ["JLE","EBT"], ["BPT","GBE"], 2, 13, "12/06/2009")
		listMatchs << createMatch("euriware", ["JLE","EBT","GBE"], ["BPT","JAY","JND"], 13, 6, "12/06/2009")
		listMatchs << createMatch("euriware", ["JLE","ADE"], ["EBT","FEE"], 7, 13, "17/06/2009")
		listMatchs << createMatch("euriware", ["ADE","RST","BPT"], ["NHT","FEE","CLC"], 4, 13, "17/06/2009")
		listMatchs << createMatch("euriware", ["JLE","CBO"], ["EBT","JAY"], 9, 13, "17/06/2009")
		listMatchs << createMatch("euriware", ["ADE","RST","BPT"], ["NHT","FEE","CLC"], 10, 13, "17/06/2009")
		listMatchs << createMatch("euriware", ["RST","ADE"], ["PSR","JAY"], 13, 0, "17/06/2009")
		listMatchs << createMatch("euriware", ["RST","PSR"], ["ADE","JAY"], 13, 10, "17/06/2009")
		return listMatchs
	}
	List<Match> populate8() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("euriware", ["RST","JAY"], ["JLE","NBN"], 13, 9, "18/06/2009")
		listMatchs << createMatch("euriware", ["GBE"], ["FEE"], 13, 7, "18/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["GBE"], ["EBT"], 13, 6, "18/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["RST","GBE"], ["PSR","NBN"], 13, 0, "18/06/2009")
		listMatchs << createMatch("euriware", ["RST","GBE"], ["PSR","NBN"], 13, 10, "18/06/2009")
		listMatchs << createMatch("euriware", ["EBT","FEE"], ["JLE","JAY"], 13, 2, "18/06/2009")
		
		listMatchs << createMatch("euriware", ["RST","GBE"], ["FEE","ADE"], 13, 6, "19/06/2009")
		listMatchs << createMatch("euriware", ["RST","EBT"], ["JAY","PSR"], 10, 13, "19/06/2009")
		listMatchs << createMatch("euriware", ["RST"], ["JAY"], 13, 1, "19/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["RST"], ["EBT"], 13, 9, "19/06/2009", "souchais", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("euriware", ["PSR","GBE"], ["ADE","FEE"], 13, 11, "19/06/2009")
		
		listMatchs << createMatch("euriware", ["JAY","GBE"], ["RST","PSR"], 11, 13, "19/06/2009")
		listMatchs << createMatch("euriware", ["JAY","GBE"], ["RST","PSR"], 11, 13, "19/06/2009")
		
		listMatchs << createMatch("euriware", ["JAY","BPT"], ["EBT","MSI"], 13, 10, "19/06/2009")
		listMatchs << createMatch("euriware", ["JAY","BPT"], ["EBT","MSI"], 13, 12, "19/06/2009")
		return listMatchs
	}
	List<Match> populate9() {
		def listMatchs = new ArrayList<Match>()
		listMatchs << createMatch("orvault", ["DOM"], ["JRO"], 9, 13, "22/03/2009", "56", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("orvault", ["AXE"], ["JRO"], 0, 13, "22/03/2009", "56", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("orvault", ["JU"], ["JRO"], 8, 13, "11/04/2009", "56")
		listMatchs << createMatch("orvault", ["VAN","TYN"], ["JU","JRO"], 1, 13, "11/04/2009", "56")
		listMatchs << createMatch("orvault", ["JU"], ["JRO"], 6, 13, "17/04/2009", "56")
		listMatchs << createMatch("orvault", ["JU"], ["JRO"], 8, 13, "17/04/2009", "56")
		listMatchs << createMatch("orvault", ["JU"], ["JRO"], 0, 13, "17/04/2009", "56")
		listMatchs << createMatch("orvault", ["PDO"], ["JRO"], 9, 13, "17/04/2009", "56")
		listMatchs << createMatch("orvault", ["JU"], ["JRO"], 13, 9, "03/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM"], ["JRO"], 3, 13, "08/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PDO"], 13, 4, "09/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PDO"], 13, 12, "09/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 3, 13, "21/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 1, 13, "21/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 13, 11, "23/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 13, 1, "23/05/2009", "56")
		listMatchs << createMatch("orvault", ["PDO"], ["JRO"], 13, 6, "24/05/2009", "56")
		listMatchs << createMatch("orvault", ["PDO"], ["JRO"], 5, 13, "24/05/2009", "56")
		listMatchs << createMatch("orvault", ["PDO"], ["JRO"], 13, 11, "24/05/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 8, 13, "30/05/2009", "56")
		listMatchs << createMatch("orvault", ["PDO"], ["JRO"], 11, 13, "31/05/2009", "56", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("orvault", ["PLO"], ["JRO"], 10, 13, "31/05/2009", "56", TypeMatch.NON_OFFICIEL)
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 13, 5, "01/06/2009", "56")
		listMatchs << createMatch("orvault", ["DOM","GIL"], ["JRO","PLO"], 8, 13, "01/06/2009", "56")
		return listMatchs
	}
	
	List getMatchByGroupName(groupName) {
		def petankGroup = PetankGroupUtil.instance.getGroup(groupName)
		return DAOManager.instance.getAllFromIdGroup(Match.class, petankGroup.id)
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
	
	Match createMatch(group, play1, play2, sc1, sc2, dateString=null, place="souchais", type=TypeMatch.OFFICIEL) {
		def date = DateUtil.instance.makeDate(dateString)
		def player1 = []
		def player2 = []
		play1.each { player1 << PetankUserUtil.instance.getUser(it, group)}
		play2.each { player2 << PetankUserUtil.instance.getUser(it, group)}
		def match = new Match(score1:sc1, score2:sc2, typeMatch:type, jour:date, player1:"", player2:"", point1:0, point2:0, playersWithPoints:"");
		match.idGroup = PetankGroupUtil.instance.getGroup(group).id
		match.idPlace = PetankPlaceUtil.instance.getPlace(place).id
		//on ne créé plus les données sur les points des joueurs (et moyennes) au moment de la création mais 
		//au moment où l'on applique les barêmes
		
		def concatId = {players -> players.collect({player -> player.id}).join(JOIN_PLAYER)}
		//utilisation du name
		//def concatId = {players -> players.collect({player -> player.name}).join(JOIN_PLAYER)}
		match.player1 = concatId(player1)
		match.player2 = concatId(player2)
		
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
