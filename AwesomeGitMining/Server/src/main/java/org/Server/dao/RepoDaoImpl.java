package org.Server.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.Common.data.IRepoDao;
import org.Common.po.Repository;
import org.Common.po.Statistics;

import net.sf.json.JSONArray;

public class RepoDaoImpl extends UnicastRemoteObject implements IRepoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path = "src/main/java/org/Server/data/gitmining-api/";

	/* 所有项目名称列表 */
	private List<String> repoList;

	/* 项目-贡献者 */
	private List<List<String>> mapR2Ctb;

	/* 项目-合作者 */
	private List<List<String>> mapR2Clb;

	/* 项目-语言使用情况 */
	private List<Map<String, Integer>> mapR2L;

	/* 项目创建时间列表 */
	private List<String> createdTimeList;

	/* 项目语言列表 */
	private List<String> languageList;

	/* 所有项目jsonStr列表 */
	private List<String> repoAll;
	
	private List<String> reposByStar;
	private List<String> reposByContribute;
	private List<String> reposByFork;

	public RepoDaoImpl() throws RemoteException{
		long startTime = System.nanoTime();

		this.repoList = DataInitHelper.getList(path + "repo_fullname.txt");

		this.mapR2Clb = DataInitHelper.getListList(path + "repo-collaborators.txt");

		this.mapR2Ctb = DataInitHelper.getListList(path + "repo-contributors.txt");

		this.repoAll = DataInitHelper.getAllReposJson(path + "repo-all.txt");

		this.mapR2L = DataInitHelper.getLanguages(path + "repo-languageNames.txt", path + "repo-languageCounts.txt");

		this.languageList = DataInitHelper.getList(path + "repo-language.txt");
		
		this.reposByContribute = DataInitHelper.getList(path+"repo_ContriSort.txt");
		
		this.reposByFork = DataInitHelper.getList(path+"repo_forkSort.txt");
		
		this.reposByStar = DataInitHelper.getList(path+"repo_starSort.txt");

		System.out.println("RepoDaoImpl initialized!");
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");

	}

	@Override
	public Repository getRepository(String name) throws IOException {

		int index = repoList.indexOf(name);

		if (index == -1) {
			return null;
		}
		Repository po = null;

		po = JsonUtil.parseJson2Bean(repoAll.get(index), Repository.class);

		if (po != null) {
			po.setContributors_login(mapR2Ctb.get(index));
			po.setCollaborators_login(mapR2Clb.get(index));
			po.setOwner_name(name.split("/")[0]);
			po.setLanguages(mapR2L.get(index));

			int[] ranks = new int[7];
			ranks[0] = DataInitHelper.getIntList(path + "repo_starRank.txt").get(index);
			ranks[1] = DataInitHelper.getIntList(path + "repo_forkRank.txt").get(index);
			ranks[2] = DataInitHelper.getIntList(path + "repo_watchRank.txt").get(index);
			ranks[3] = DataInitHelper.getIntList(path + "repo_subscriRank.txt").get(index);
			ranks[4] = DataInitHelper.getIntList(path + "repo_issueRank.txt").get(index);
			ranks[5] = DataInitHelper.getIntList(path + "repo_contriRank.txt").get(index);
			ranks[6] = DataInitHelper.getIntList(path + "repo_collaRank.txt").get(index);
			po.setRanks(ranks);

		}
		return po;
	}

	@Override
	public List<String> getAllRepo() {

		return repoList;
	}

	@Override
	public List<String> searchRepository(String name) {
		return SearchHelper.fuzzySearch(repoList, name);
	}

	@Override
	public int[] getLanguageStatistics() {

		String[] languages = Statistics.language;
		int[] result = new int[languages.length];
		for (int i = 0; i < languages.length; i++)
			result[i] = 0;

		loop: for (String language : languageList) {
			if (language.equals(""))
				continue;
			for (int i = 0; i < languages.length; i++) {
				if (language.equals(languages[i])) {
					result[i]++;
					continue loop;
				}
			}
			result[languages.length - 1]++;
		}

		return result;
	}

	@Override
	public int[] getCreatedTimeStatistics() {

		this.createdTimeList = DataInitHelper.getList(path + "repo-createdTime.txt");

		String[] years = Statistics.year;
		int[] result = new int[years.length];
		int year;

		try {
			for (String time : createdTimeList) {
				year = Integer.parseInt(time.split("-")[0]);
				result[year - Integer.parseInt(years[0])]++;
			}
		} catch (NumberFormatException e) {
			System.out.println("数字不符合格式");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Integer> getForksStatistics() {

		List<Integer> forks = DataInitHelper.getIntList(path + "repo_forks.txt");

		Collections.sort(forks);

		return forks;
	}

	@Override
	public List<Integer> getStarsStatistics() throws RemoteException{
		List<Integer> stars = DataInitHelper.getIntList(path + "repo_stars.txt");
		Collections.sort(stars);

		return stars;
	}

	@Override
	public List<String> getReposByLanguage(int i) {
		String[] languages = Statistics.language;

		List<String> result = new ArrayList<>();

		for (int index = 0; index < languageList.size(); index++) {
			if (languageList.get(index).equals(languages[i]))
				result.add(repoList.get(index));

		}

		return result;
	}
	
	
	@Override
	public List<Integer> getCodeFrequency(String name) throws IOException{
	
		List<Integer> list = new ArrayList<>();
		
		String jsonStr = HttpRequest.sendGetWithAuth("api.github.com/repos/",name+"/stats/code_frequency");
		JSONArray ja = JSONArray.fromObject(jsonStr);

		Iterator<JSONArray> it = ja.iterator();
		while (it.hasNext()) {
			JSONArray line = it.next();
			list.add(line.getInt(1)+line.getInt(2));
		}
		
		return list;
	}
	
	@Override
	public List<String> getReposSortedByStar(){
		return reposByStar;
	}

	@Override
	public List<String> getReposSortedByContribute(){
		return reposByContribute;
	}

	@Override
	public List<String> getReposSortedByFork(){
		return reposByFork;
	}

}