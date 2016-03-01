package main.dao.impl;

import java.util.ArrayList;

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
	 * @param owner项目所有者登录名
	 *            reponame 项目名
	 * @return json
	 */

	public RepoDaoImpl() {
		ju = new JsonUtil();
	}
	

	@Override
	public RepositoryPO getRepository(String name) {

		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/", name);
		RepositoryPO po = JsonUtil.<RepositoryPO>parseJson2PO(JSONObject.fromObject(s),RepositoryPO.class);
		
		return po;
	}

	
	@Override
	public ArrayList<RepositoryPO> getAllRepo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<BranchPO> getBranches(String name){
		String s = HttpRequest.sendGet("https://api.github.com/repos/",name+"/branches");

		ArrayList<BranchPO> list =  JsonUtil.<BranchPO>parseJson2POlist(JSONArray.fromObject(s), BranchPO.class);
        return list;
	}
	
	public ArrayList<UserPO> getContributors(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/contributors");
		
		ArrayList<UserPO> list =  JsonUtil.parseJson2POlist(JSONArray.fromObject(s), UserPO.class);
        return list;
	}
	
	public ArrayList<UserPO> getCollaborators(String name){
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/",name+"/collaborators");
		
		ArrayList<UserPO> list =  JsonUtil.parseJson2POlist(JSONArray.fromObject(s), UserPO.class);
        return list;
	}
}
