package main.business.utility;

import java.util.HashMap;
import java.util.Map;

import main.dao.DataFactory;
import main.dao.impl.IRepoDao;

public class ScoreCalculator {
	/**
	 * In case the algorithm would change
	 * 
	 * @param rates{star、fork、watchers、subscribers、issues、contributors、
	 *            collaborators}ranks
	 * @return the repository scores{famous,hot,popular,mature,contributor}
	 */
	private static final int LEN = getLen();
	private static IRepoDao daoImpl;

	public static Map<String, Integer> getReposScore(int[] rates) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		// famous should be related to star,fork and subscribers
		double fscore = ((rates[0]+rates[1]+rates[3])/3.0*LEN)*8;
		map.put("famous", (int) fscore);
		return map;

	}

	private static int getLen() {
		daoImpl = DataFactory.getRepoDataInstance();
		return daoImpl.getAllRepo().size();
	}
}
