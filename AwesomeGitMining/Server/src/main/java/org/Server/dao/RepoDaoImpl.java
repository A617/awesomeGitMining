package org.Server.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private List<List<String>> contributionsList;

	/* 项目-合作者 */
	private List<List<String>> collaboratorsList;

	/* 项目-语言使用情况 */
	private List<Map<String, Integer>> mapR2L;

	/* 项目创建时间列表 */
	private List<String> createdTimeList;

	/* 项目语言列表 */
	private List<String> languageList;

	private List<String> descriptionList;

	/* 所有项目jsonStr列表 */
	private List<String> repoAll;

	private List<String> reposByStar;
	private List<String> reposByContribute;
	private List<String> reposByFork;

	private List<Integer> sizeList;

	private ArrayList<Integer> collaNumList;

	/* 每种语言的项目个数 */
	private int[] languageRepoCounts;

	private List<Integer> sizeRank;
	private List<Integer> scaleRank;
	private List<Integer> hotRank;
	private List<Integer> participationRank;
	private List<Integer> promisingRank;

	private int len;

	public RepoDaoImpl() throws RemoteException {
		long startTime = System.nanoTime();

		this.repoList = DataInitHelper.getList(path + "repo_fullname.txt");
		this.collaboratorsList = DataInitHelper.getListList(path + "repo-collaborators.txt");
		this.contributionsList = DataInitHelper.getListList(path + "repo-contributors.txt");
		this.repoAll = DataInitHelper.getAllReposJson(path + "repo-all.txt");
		this.mapR2L = DataInitHelper.getLanguages(path + "repo-languageNames.txt", path + "repo-languageCounts.txt");
		this.languageList = DataInitHelper.getList(path + "repo-language.txt");
		this.reposByContribute = DataInitHelper.getList(path + "repo_ContriSort.txt");
		this.reposByFork = DataInitHelper.getList(path + "repo_forkSort.txt");
		this.reposByStar = DataInitHelper.getList(path + "repo_starSort.txt");
		this.createdTimeList = DataInitHelper.getList(path + "repo-createdTime.txt");
		this.sizeList = DataInitHelper.getIntList(path + "repo_size.txt");
		this.descriptionList = DataInitHelper.getList(path+"repo_description.txt");

		String[] languages = Statistics.language;
		this.languageRepoCounts = new int[languages.length];
		for (int i = 0; i < languages.length; i++)
			languageRepoCounts[i] = 0;
		loop: for (String language : languageList) {
			if (language.equals(""))
				continue;
			for (int i = 0; i < languages.length; i++) {
				if (language.equals(languages[i])) {
					languageRepoCounts[i]++;
					continue loop;
				}
			}
			this.languageRepoCounts[languages.length - 1]++;
		}
		this.len = repoList.size();
		
		this.hotRank = DataInitHelper.getIntList(path+"repo_hotRank.txt");
		this.participationRank = DataInitHelper.getIntList(path+"repo_participationRank.txt");
		this.sizeRank = DataInitHelper.getIntList(path+"repo_sizeRank.txt");
		this.scaleRank=DataInitHelper.getIntList(path+"repo_scaleRank.txt");
		this.promisingRank=DataInitHelper.getIntList(path+"repo_promisingRank.txt");
		

		
		
		System.out.println("RepoDaoImpl initialized!");
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");

	}

	

	private static <T> void writeToTxt(String path, List<T> list) {
		// write to txt
		FileWriter fw = null;
		BufferedWriter writer = null;
		File file = new File(path);

		try {

			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (T i : list) {
				writer.write(i + "");
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			po.setContributors_login(contributionsList.get(index));
			po.setCollaborators_login(collaboratorsList.get(index));
			po.setOwner_name(name.split("/")[0]);
			po.setLanguages(mapR2L.get(index));
			double[] scores = new double[5];
			scores[0] = 1.0
					- 1.0 * sizeRank.get(index) / languageRepoCounts[Statistics.getLanguageIndex(po.getLanguage())];
			scores[1] = 1.0 - 1.0 * scaleRank.get(index) / len;
			scores[2] = 1.0 - 1.0 * hotRank.get(index) / len;
			scores[3] = 1.0 - 1.0 * participationRank.get(index) / len;
			scores[4] = 1.0 - 1.0 * promisingRank.get(index) / len;
			po.setScores(scores);

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

		return this.languageRepoCounts;
	}

	@Override
	public int[] getCreatedTimeStatistics() {

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

		List<Integer> forks = DataInitHelper.getIntList(path + "repo_forkedCount.txt");

		Collections.sort(forks);

		return forks;
	}

	@Override
	public List<Integer> getStarsStatistics() throws RemoteException {
		List<Integer> stars = DataInitHelper.getIntList(path + "repo_stargazers.txt");
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
	public List<String> getReposByYear(int i) {
		String[] years = Statistics.year;
		List<String> result = new ArrayList<>();

		for (int index = 0; index < createdTimeList.size(); index++) {
			if (createdTimeList.get(index).split("-")[0].equals(years[i]))
				result.add(repoList.get(index));

		}

		return result;

	}

	@Override
	public List<Integer> getCodeFrequency(String name) throws IOException {

		List<Integer> list = new ArrayList<>();

		String jsonStr = HttpRequest.sendGetWithAuth("api.github.com/repos/", name + "/stats/code_frequency");
		JSONArray ja = JSONArray.fromObject(jsonStr);

		Iterator<JSONArray> it = ja.iterator();
		while (it.hasNext()) {
			JSONArray line = it.next();
			list.add(line.getInt(1) + line.getInt(2));
		}

		return list;
	}

	@Override
	public List<String> getReposSortedByStar() {
		return reposByStar;
	}

	@Override
	public List<String> getReposSortedByContribute() {
		return reposByContribute;
	}

	@Override
	public List<String> getReposSortedByFork() {
		return reposByFork;
	}

	

	// 将列表中的数据由大到小排列，返回每个项目的排名
	private List<Integer> rankList(List<Integer> srcList) {

		List<Integer> rankList = new ArrayList<>();

		List<Integer> sortList = new ArrayList<>(srcList);

		Collections.sort(sortList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int element : srcList) {

			int rank = sortList.indexOf(element);

			rankList.add(rank);

		}

		return rankList;
	}

	public List<Integer> getSizeByLanguage(int i) {
		String[] languages = Statistics.language;

		List<Integer> result = new ArrayList<>();

		for (int k = 0; k < repoList.size(); k++) {
			if (languageList.get(k).equals(languages[i]))
				result.add(sizeList.get(k));
			else
				result.add(-1);

		}

		return rankList(result);
	}


	
	@Override
	public List<String> getReposByKeyword(String key) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			String fullname = repoList.get(i);
			if (fullname.split("/")[1].contains(key)) {
				result.add(fullname);
				continue;
			}
			if (descriptionList.get(i)!=null&&descriptionList.get(i).toLowerCase().contains(key.toLowerCase()))
				result.add(fullname);
		}
		return result;
	}
	
	
	@Override
	public List<Integer> getAllStar(){
		return DataInitHelper.getIntList(path+"repo_stargazers.txt");
	}
	
	
	@Override
	public List<Integer> getAllFork(){
		return DataInitHelper.getIntList(path+"repo_forkedCount.txt");
	}
	
	@Override
	public List<Integer> getAllSize(){
		return DataInitHelper.getIntList(path+"repo_size.txt");
	}
	
	@Override
	public List<String> getAllLanguage(){
		return DataInitHelper.getList(path+"repo-language.txt");
	}
	
/*
	private void initRanks(){
		// scale
		this.collaNumList = new ArrayList<>();
		for (int i = 0; i < repoList.size(); i++) {
			collaNumList.add(contributionsList.get(i).size());
		}
		this.scaleRank = rankList(collaNumList);

		
		// hot
		int[] starCountsList = DataInitHelper.getIntArray(path + "repo_stargazers.txt", len);
		int[] forkCountsList = DataInitHelper.getIntArray(path + "repo_forkedCount.txt", len);
		int[] watchCountsList = DataInitHelper.getIntArray(path + "repo_watchedCount.txt", len);
		ArrayList<Integer> hotScoreList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			hotScoreList.add(starCountsList[i] + forkCountsList[i] + watchCountsList[i]);
		}
		this.hotRank = rankList(hotScoreList);

		
		// participatation
		int[] issueCountsList = DataInitHelper.getIntArray(path + "repo_issuesCount.txt", len);
		int[] contriNumList = new int[len];
		for (int i = 0; i < repoList.size(); i++) {
			contriNumList[i] = contributionsList.get(i).size();
		}
		ArrayList<Integer> participationScoreList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			participationScoreList.add(issueCountsList[i] + forkCountsList[i] * 5 + contriNumList[i] * 5);
		}
		this.participationRank = rankList(participationScoreList);

		
		// promising
		int[] starCountsIn28DaysList = DataInitHelper.getIntArray(path + "repo_staredTime.txt", len);
		int[] forkCountsIn28DaysList = DataInitHelper.getIntArray(path + "repo_forkedTime.txt", len);
		ArrayList<Integer> promisingScoreList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			promisingScoreList.add(starCountsIn28DaysList[i] + forkCountsIn28DaysList[i]);
		}
		this.promisingRank = rankList(promisingScoreList);
		writeToTxt(path+"repo_promisingRank.txt", promisingRank);
		
		// size
		String[] languages = Statistics.language;
		List<Integer>[] languageRepoSize = new List[languages.length];
		for (int i = 0; i < languages.length; i++) {
			languageRepoSize[i] = getSizeByLanguage(i);
		}
		this.sizeRank = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			int languageIndex = Statistics.getLanguageIndex(languageList.get(i));
			sizeRank.add(languageRepoSize[languageIndex].get(i));
		}
	}

	*/
}
