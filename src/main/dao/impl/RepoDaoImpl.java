package main.dao.impl;

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
import main.dao.entity.User;

public class RepoDaoImpl implements IRepoDao {
	
	String gitmining_repo_url = "http://gitmining.net/api/repository/";
	
	public RepoDaoImpl() {
	}

	@Override
	public Repository getRepository(String name) {

		String s = HttpRequest.sendGet(gitmining_repo_url, name);
//		String s = HttpRequest.sendGet("https://api.github.com/repos/", name);
		Repository po = JsonUtil.<Repository> parseJson2Bean(s, Repository.class);

		po.setContributors_login(this.getContributors_login(name));
		po.setCollaborators_login(this.getCollaborators_login(name));
		po.setOwner_name(this.getOwner_name(name));
		po.setBranches_name(this.getBranches_name(name));
		po.setForks_fullname(this.getForks_fullname(name));
		po.setLanguages(this.getLanguages(name));

		return po;
	}

	@Override
	public List<Repository> getAllRepo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Branch> getBranches(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/branches");

		List<Branch> list = JsonUtil.<Branch> parseJson2Beanlist(s, Branch.class);
		return list;
	}

	@Override
	public List<Contributor> getContributors(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/contributors");

		List<Contributor> list = JsonUtil.parseJson2Beanlist(s, Contributor.class);
		return list;
	}

	@Override
	public List<Collaborator> getCollaborators(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/collaborators");

		List<Collaborator> list = JsonUtil.parseJson2Beanlist(s, Collaborator.class);
		return list;
	}

	@Override
	public List<Fork> getForks(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/forks");

		List<Fork> list = JsonUtil.parseJson2Beanlist(s, Fork.class);
		return list;
	}

	@Override
	public Owner getOwner(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name);

		return JsonUtil.getObjectfromJson(s, Owner.class, "owner");
	}

	private String getOwner_name(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/item/owner_name");

		return s;
	}

	private List<String> getForks_fullname(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/forks/names");
		return JsonUtil.parseJson2List(s);
	}

	private List<String> getBranches_name(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/branches/names");
		return JsonUtil.parseJson2List(s);
	}

	private List<String> getContributors_login(String name) {
	//	return HttpRequest.sendGetforList("http://gitmining.net/api/repository/", name + "/contributors/login");
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/contributors/login");
		return JsonUtil.parseJson2List(s);
	}

	private List<String> getCollaborators_login(String name) {
		String s =  HttpRequest.sendGet(gitmining_repo_url, name + "/collaborators/login");
		return JsonUtil.parseJson2List(s);
	}

	@Override
	public Map<String, Integer> getLanguages(String name) {
		String s = HttpRequest.sendGet(gitmining_repo_url, name + "/languages");
		return JsonUtil.<Integer> parseJSON2Map(s);
	}

}
