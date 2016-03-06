package main.dao.impl;

import java.io.File;
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

public class RepoDaoImpl implements IRepoDao {

	final String gitmining_repo_url = "http://gitmining.net/api/repository/";

	List<String> repoList;
	Map<String,List<String>> mapR2Ctb;
	Map<String,List<String>> mapR2Clb;

	public RepoDaoImpl() {
		repoList = DataInitHelper
				.getList(new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_fullname.txt");
		mapR2Clb=DataInitHelper.getMap(new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo-collaborators.txt");
		mapR2Ctb=DataInitHelper.getMap(new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo-contributors.txt");
		System.out.println("RepoDaoImpl initialized!");
	}

	@Override
	public Repository getRepository(String name) throws IOException {

		String s = HttpRequest.sendGet(gitmining_repo_url, name);
		Repository po = JsonUtil.<Repository> parseJson2Bean(s, Repository.class);

		if(po!=null){
		po.setContributors_login(mapR2Ctb.get(name));
		po.setCollaborators_login(mapR2Clb.get(name));
		po.setOwner_name(name.split("/")[0]);
	//	po.setBranches_name(this.getBranches_name(name));
	//	po.setForks_fullname(this.getForks_fullname(name));
	//	po.setLanguages(this.getLanguages(name));
		}
		return po;
	}

	@Override
	public List<String> getAllRepo(){
		
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

	@Override
	public Map<String, Integer> getLanguages(String name) throws IOException {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/languages");
		return JsonUtil.<Integer> parseJSON2Map(s);
	}

	@Override
	public List<String> searchRepository(String name) {
		return SearchHelper.fuzzySearch(repoList, name);
	}

}
