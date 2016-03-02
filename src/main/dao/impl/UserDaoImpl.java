package main.dao.impl;

import java.util.List;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.po.UserPO;
import net.sf.json.JSONObject;

public class UserDaoImpl implements IUserDao {

	@Override
	public UserPO getUser(String login) {
		String s = HttpRequest.sendGet("http://gitmining.net/api/user/", login);
		UserPO po = JsonUtil.<UserPO> parseJson2PO(s, UserPO.class);

		po.setRepos_fullname(getRepos_fullname(login));
		po.setFollowers_name(getFollowers_name(login));
		po.setFollowing_name(getFollowing_name(login));

		return po;
	}

	private List<String> getFollowers_name(String login) {
		String s = HttpRequest.sendGet("https://api.github.com/users/", login + "/followers");

		return JsonUtil.<String> getListfromJsonArray(s, "login");

	}

	private List<String> getFollowing_name(String login) {
		String s = HttpRequest.sendGet("https://api.github.com/users/", login + "/following");

		return JsonUtil.<String> getListfromJsonArray(s, "login");

	}

	private List<String> getRepos_fullname(String login) {
		String s = HttpRequest.sendGet("https://api.github.com/users/", login + "/repos");

		return JsonUtil.<String> getListfromJsonArray(s, "full_name");

	}
}
