package main.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.entity.Branch;
import main.dao.entity.Collaborator;
import main.dao.entity.Contributor;
import main.dao.entity.Fork;
import main.dao.entity.Owner;
import main.dao.entity.Repository;
import main.data.DataMining;

public class RepoDaoImpl implements IRepoDao {

	final String gitmining_repo_url = "http://gitmining.net/api/repository/";

	/* 所有项目名称列表 */
	List<String> repoList;

	/* 项目-贡献者 */
	Map<String, List<String>> mapR2Ctb;

	/* 项目-合作者 */
	Map<String, List<String>> mapR2Clb;

	/* 项目-语言使用情况 */
	Map<String, Map<String, Integer>> mapR2L;

	/* 项目-fork项目 */
	Map<String, List<String>> mapR2Fork;

	/* 所有项目jsonStr列表 */
	List<String> repoAll;

	public RepoDaoImpl() {

		String path = DataMining.class.getResource("gitmining-api/").getPath();
		this.repoList = DataInitHelper
				.getList(path+"repo_fullname.txt");

		System.out.println("repoList");
		
		this.mapR2Clb = DataInitHelper
				.getMap(path+"repo-collaborators.txt");
		
		System.out.println("repoList");

		this.mapR2Ctb = DataInitHelper
				.getMap(path+"repo-contributors.txt");
		
		System.out.println("ctb");

		this.mapR2L = DataInitHelper.getLanguages(
				 path+"repo-languageNames.txt",
				path+"repo-languageCounts.txt");
		
		System.out.println("language");

		this.mapR2Fork = DataInitHelper
				.getMap(path+"repo-forks.txt");
		
		System.out.println("fork");

		this.repoAll = DataInitHelper
				.getAllReposJson(path + "repo-all.txt");
		
		System.out.println("repo");

		System.out.println("RepoDaoImpl initialized!");
		
	}

	@Override
	public Repository getRepository(String name) throws IOException {

		// String s = HttpRequest.sendGet(gitmining_repo_url, name);
		// Repository po = JsonUtil.<Repository> parseJson2Bean(s,
		// Repository.class);

		int index = repoList.indexOf(name);

		if (index == -1) {
			return null;
		}

		Repository po = JsonUtil.parseJson2Bean(repoAll.get(index),Repository.class);

		if (po != null) {
			po.setContributors_login(mapR2Ctb.get(name));
			po.setCollaborators_login(mapR2Clb.get(name));
			po.setOwner_name(name.split("/")[0]);
			// po.setBranches_name(this.getBranches_name(name));
			po.setForks_fullname(mapR2Fork.get(name));
			po.setLanguages(mapR2L.get(name));
		}
		return po;
	}

	@Override
	public List<String> getAllRepo() {

		return repoList;
	}

	@Override
	public List<Branch> getBranches(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/branches");

		List<Branch> list = JsonUtil.<Branch> parseJson2Beanlist(s, Branch.class);
		return list;
	}

	@Override
	public List<Contributor> getContributors(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/contributors");

		List<Contributor> list = JsonUtil.parseJson2Beanlist(s, Contributor.class);
		return list;
	}

	@Override
	public List<Collaborator> getCollaborators(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/collaborators");

		List<Collaborator> list = JsonUtil.parseJson2Beanlist(s, Collaborator.class);
		return list;
	}

	@Override
	public List<Fork> getForks(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/forks");

		List<Fork> list = JsonUtil.parseJson2Beanlist(s, Fork.class);
		return list;
	}

	@Override
	public Owner getOwner(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name);

		return JsonUtil.getObjectfromJson(s, Owner.class, "owner");
	}

	private String getOwner_name(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/item/owner_name");

		return s;
	}
	
/*
	private List<String> getForks_fullname(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/forks/names");
		return JsonUtil.parseJson2List(s);
	}

	private List<String> getBranches_name(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/branches/names");
		return JsonUtil.parseJson2List(s);
	}

	private List<String> getContributors_login(String name) throws IOException {
		// return
		// HttpRequest.sendGetforList("http://gitmining.net/api/repository/",
		// name + "/contributors/login");
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/contributors/login");
		return JsonUtil.parseJson2List(s);
	}

	private List<String> getCollaborators_login(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/collaborators/login");
		return JsonUtil.parseJson2List(s);
	}
*/
	
	
	@Override
	public Map<String, Integer> getLanguages(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/languages");
		Map<String, Integer> result = JsonUtil.<Integer> parseJSON2Map(s);
		result.remove("fn");
		return result;
	}

	@Override
	public List<String> searchRepository(String name) {
		return SearchHelper.fuzzySearch(repoList, name);
	}

}
