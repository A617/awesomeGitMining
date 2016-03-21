package main.business.utility;

import java.util.HashMap;
import java.util.Map;

import main.dao.DataFactory;
import main.dao.impl.IRepoDao;

public class ScoreCalculator {
	private static final int LEN = getLen();
	private static IRepoDao daoImpl;

	/**
	 * In case the algorithm would change
	 * 
	 * @param rates{star、fork、watchers、subscribers、issues、contributors、
	 *            collaborators}ranks
	 * @return the repository scores{famous,hot,popular,mature,contributor}
	 */
	public static Map<String, Integer> getReposScore(int[] rates) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		double star = (LEN - rates[0]) / (LEN * 1.0);
		double fork = (LEN - rates[1]) / (LEN * 1.0);
		double watcher = (LEN - rates[2]) / (LEN * 1.0);
		double subscriber = (LEN - rates[3]) / (LEN * 1.0);
		double issue = (LEN - rates[4]) / (LEN * 1.0);
		double contributor = (LEN - rates[5]) / (LEN * 1.0);
		double collaborator = (LEN - rates[6]) / (LEN * 1.0);
		// famous should be related to star,fork,watchers
		double fscore = (star + fork + watcher) / 3 * 8;
		System.out.println(fscore);
		map.put("famous", (int) fscore);
		// hot should be related to star,subscribers,watchers
		double hscore = (star + subscriber + watcher) / 3 * 8;
		System.out.println(hscore);
		map.put("hot", (int) hscore);
		// popular should be related to star,watcher
		double pscore = (star + watcher) / 2 * 8;
		System.out.println(pscore);
		map.put("popular", (int) pscore);
		// mature should be related to fork
		double mscore = fork * 8;
		map.put("mature", (int) mscore);
		double cscore = contributor * 8;
		map.put("contributor", (int) cscore);
		return map;

	}

	private static int getLen() {
		daoImpl = DataFactory.getRepoDataInstance();
		return daoImpl.getAllRepo().size();
	}
}
