package main.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.po.BranchPO;
import main.dao.po.RepositoryPO;
import main.dao.po.UserPO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RepoDaoImpl implements IRepoDao {

	JsonUtil ju;

	/**
	 * @param owner椤圭洰鎵�鏈夎�呯櫥褰曞悕
	 *            reponame 椤圭洰鍚�
	 * @return json
	 */

	public RepoDaoImpl() {
		ju = new JsonUtil();
	}
	

	@Override
	public RepositoryPO getRepository(String name) {

		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/", name);
		RepositoryPO po = JsonUtil.<RepositoryPO>parseJson2PO(s,RepositoryPO.class);
		
		po.setContributors_login(this.getContributors_login(name));
		po.setCollaborators_login(this.getCollaborators_login(name));
		po.setOwner_name(this.getOwner_name(name));
		po.setBranches_name(this.getBranches_name(name));
		po.setForks_fullname(this.getForks_fullname(name));
		po.setLanguages(this.getLanguages(name));
		return po;
	}

	
	@Override
	public ArrayList<RepositoryPO> getAllRepo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<BranchPO> getBranches(String name){
		String s = HttpRequest.sendGet("https://api.github.com/repos/",name+"/branches");

		ArrayList<BranchPO> list =  JsonUtil.<BranchPO>parseJson2POlist(s, BranchPO.class);
        return list;
	}
	
	public ArrayList<UserPO> getContributors(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/contributors");
		
		ArrayList<UserPO> list =  JsonUtil.parseJson2POlist(s, UserPO.class);
        return list;
	}
	
	public ArrayList<UserPO> getCollaborators(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/collaborators");
		
		ArrayList<UserPO> list =  JsonUtil.parseJson2POlist(s, UserPO.class);
        return list;
	}
	
	public ArrayList<RepositoryPO> getForks(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/forks");
		
		ArrayList<RepositoryPO> list =  JsonUtil.parseJson2POlist(s, RepositoryPO.class);
        return list;
	}
	
	private UserPO getOwner(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name);
		
        return JsonUtil.getObjectfromJson(s, UserPO.class, "owner");
	}
	
	private String getOwner_name(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/item/owner_name");
		
        return s;
	}
	
	private List<String> getForks_fullname(String name){
		return HttpRequest.sendGetforList("http://gitmining.net/api/repository/",name+"/forks/names");
		
	}
	
	private List<String> getBranches_name(String name){
		return HttpRequest.sendGetforList("http://gitmining.net/api/repository/",name+"/branches/names");
		
	}
	
	private List<String> getContributors_login(String name){
		return HttpRequest.sendGetforList("http://gitmining.net/api/repository/",name+"/contributors/login");
		
	}
	
	private List<String> getCollaborators_login(String name){
		return HttpRequest.sendGetforList("http://gitmining.net/api/repository/",name+"/collaborators/login");
		
	}
	
	public Map<String,Integer> getLanguages(String name){
		String s =HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/languages");
		return JsonUtil.<Integer>parseJSON2Map(s);
	}
	
	
}
