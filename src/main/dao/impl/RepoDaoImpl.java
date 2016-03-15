package main.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.dao.JsonUtil;
import main.dao.entity.Language;
import main.dao.entity.Repository;

public class RepoDaoImpl implements IRepoDao {

	final String gitmining_repo_url = "http://gitmining.net/api/repository/";

	/* 所有项目名称列表 */
	List<String> repoList;

	/* 项目-贡献者 */
	Map<String, List<String>> mapR2Ctb;

	/* 项目-合作者 */
	Map<String, List<String>> mapR2Clb;

	/* 项目-语言使用情况 */
	List<Map<String, Integer>> mapR2L;

	/* 项目-fork项目 */
//	Map<String, List<String>> mapR2Fork;

	/* 所有项目jsonStr列表 */
	List<String> repoAll;

	public RepoDaoImpl() {
		

		String path = "src/main/data/gitmining-api/";
		this.repoList = DataInitHelper
				.getList(path+"repo_fullname.txt");

		
		mapR2Clb = DataInitHelper
				.getMap(path+"repo-collaborators.txt");
		

		mapR2Ctb = DataInitHelper
				.getMap(path+"repo-contributors.txt");
		

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
			po.setContributors_login(mapR2Ctb.get(name));
			po.setCollaborators_login(mapR2Clb.get(name));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCreatedTimeStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

}
