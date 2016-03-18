package main.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import main.dao.JsonUtil;
import main.dao.entity.Repository;
import main.dao.entity.Statistics;

public class RepoDaoImpl implements IRepoDao {

	/* 所有项目名称列表 */
	private List<String> repoList;

	/* 项目-贡献者 */
	private List<List<String>> mapR2Ctb;

	/* 项目-合作者 */
	private List<List<String>> mapR2Clb;

	/* 项目-语言使用情况 */
	private List<Map<String, Integer>> mapR2L;
	
	/*项目创建时间列表*/
	private List<String> createdTimeList;
	
	/*项目语言列表*/
	private List<String> languageList;

	/* 所有项目jsonStr列表 */
	private List<String> repoAll;

	
	public RepoDaoImpl() {

		String path = "src/main/data/gitmining-api/";
		this.repoList = DataInitHelper
				.getList(path+"repo_fullname.txt");

		
		this.mapR2Clb = DataInitHelper
				.getListList(path+"repo-collaborators.txt");
		

		this.mapR2Ctb = DataInitHelper
				.getListList(path+"repo-contributors.txt");
		
		
		this.repoAll = DataInitHelper
				.getAllReposJson(path + "repo-all.txt");
		
		
		
		this.mapR2L = DataInitHelper.getLanguages(
				 path+"repo-languageNames.txt",
				path+"repo-languageCounts.txt");
		
		

		System.out.println("RepoDaoImpl initialized!");
		
	}

	@Override
	public Repository getRepository(String name) throws IOException{


		int index = repoList.indexOf(name);

		if (index == -1) {
			return null;
		}

		Repository po = JsonUtil.parseJson2Bean(repoAll.get(index), Repository.class);

		if (po != null) {
			po.setContributors_login(mapR2Ctb.get(index));
			po.setCollaborators_login(mapR2Clb.get(index));
			po.setOwner_name(name.split("/")[0]);
			po.setLanguages(mapR2L.get(index));
			
			int[] ranks = new int[7];
			String path = "src/main/data/gitmining-api/";
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
		String path = "src/main/data/gitmining-api/";
		this.languageList = DataInitHelper.getList(path + "repo-language.txt");

		String[] languages = Statistics.language;
		int[] result = new int[languages.length];

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

		String path = "src/main/data/gitmining-api/";
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

	private List<Integer> rankList(List<Integer> srcList) {  
		   
		List<Integer> rankList = new ArrayList<>();
		
		List<Integer> sortList = new ArrayList<>(srcList);
		
		Collections.sort(sortList, new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
		System.out.println(srcList);
		
		for(int element: srcList){
			
			int rank = sortList.indexOf(element);
			
			rankList.add(rank);
			
		}
		
	    return rankList;  
	}
	
}
