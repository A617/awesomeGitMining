package main.business.utility;

import java.util.HashMap;
import java.util.Map;

import main.dao.DataFactory;
import main.dao.impl.IRepoDao;
import main.dao.impl.IUserDao;

public class ScoreCalculator {
	private static final int REPOS_LEN = getReposLen();
	private static final int USER_LEN = getUserLen();
	private static IRepoDao daoImpl;
	private static IUserDao userDao;

	/**
	 * In case the algorithm would change
	 * 
	 * @param rates{star、fork、watchers、subscribers、issues、contributors、
	 *            collaborators}ranks
	 * @return the repository scores{famous,hot,popular,mature,contributor}
	 */
	public static Map<String, Double> getReposScore(int[] rates) {
		Map<String, Double> map = new HashMap<String,Double>();
		double star = (REPOS_LEN - rates[0]) / (REPOS_LEN * 1.0);
		double fork = (REPOS_LEN - rates[1]) / (REPOS_LEN * 1.0);
		double watcher = (REPOS_LEN - rates[2]) / (REPOS_LEN * 1.0);
		double subscriber = (REPOS_LEN - rates[3]) / (REPOS_LEN * 1.0);
		double contributor = (REPOS_LEN - rates[5]) / (REPOS_LEN * 1.0);
		// famous should be related to star,fork,watchers
		double fscore = (star + fork + watcher) / 3 * 8;
		map.put("famous",fscore);
		// hot should be related to star,subscribers,watchers
		double hscore = (star + subscriber + watcher) / 3 * 8;
		map.put("hot", hscore);
		// popular should be related to star,watcher
		double pscore = (star + watcher) / 2 * 8;
		map.put("popular", pscore);
		// mature should be related to fork
		double mscore = fork * 8;
		map.put("mature", mscore);
		double cscore = contributor * 8;
		map.put("contributor", cscore);
		return map;

	}
	public static Map<String,Integer> getUserScore(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		 //TODO
		
		return map;
	}

	private static int getReposLen() {
		daoImpl = DataFactory.getRepoDataInstance();
		return daoImpl.getAllRepo().size();
	}
	private static int getUserLen(){
		userDao = DataFactory.getUserDataInstance();
		return userDao.getAllUser().size();
	}
}
