package main.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.dao.JsonUtil;
import main.dao.entity.Repository;
import main.dao.entity.Statistics;

public class RepoDaoImpl implements IRepoDao {

	final String gitmining_repo_url = "http://gitmining.net/api/repository/";

	/* 所有项目名称列表 */
	List<String> repoList;

	/* 项目-贡献者 */
	List<List<String>> mapR2Ctb;

	/* 项目-合作者 */
	List<List<String>> mapR2Clb;

	/* 项目-语言使用情况 */
	List<Map<String, Integer>> mapR2L;
	
	/*项目创建时间列表*/
	List<String> createdTimeList;
	
	/*项目语言列表*/
	List<String> languageList;

	/* 项目-fork项目 */
//	Map<String, List<String>> mapR2Fork;

	/* 所有项目jsonStr列表 */
	List<String> repoAll;

	public RepoDaoImpl() {
		

		String path = "src/main/data/gitmining-api/";
		this.repoList = DataInitHelper
				.getList(path+"repo_fullname.txt");

		
		mapR2Clb = DataInitHelper
				.getListList(path+"repo-collaborators.txt");
		

		mapR2Ctb = DataInitHelper
				.getListList(path+"repo-contributors.txt");
		

		mapR2L = DataInitHelper.getLanguages(
				 path+"repo-languageNames.txt",
				path+"repo-languageCounts.txt");
		

	//	mapR2Fork = DataInitHelper
	//			.getMap(path+"repo-forks.txt");
	
		
		this.repoAll = DataInitHelper
				.getAllReposJson(path + "repo-all.txt");
		

		System.out.println("RepoDaoImpl initialized!");
		
	}

	@Override
	public Repository getRepository(String name) throws IOException {


		int index = repoList.indexOf(name);

		if (index == -1) {
			return null;
		}

		Repository po = JsonUtil.parseJson2Bean(repoAll.get(index), Repository.class);

		if (po != null) {
			po.setContributors_login(mapR2Ctb.get(index));
			po.setCollaborators_login(mapR2Clb.get(index));
			po.setOwner_name(name.split("/")[0]);
		//	po.setForks_fullname(mapR2Fork.get(name));
			po.setLanguages(mapR2L.get(index));
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

}
