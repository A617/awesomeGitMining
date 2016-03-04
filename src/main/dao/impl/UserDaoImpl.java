package main.dao.impl;

import java.util.List;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.entity.User;

public class UserDaoImpl implements IUserDao {
	
	String gitmining_user_url = "http://gitmining.net/api/user/";
	String github_user_url = "https://api.github.com/users/";

	@Override
	public User getUser(String login) {
		String s = HttpRequest.sendGet(gitmining_user_url, login);
		User us = JsonUtil.<User> parseJson2Bean(s, User.class);

		us.setRepos_fullname(getRepos_fullname(login));
		us.setFollowers_name(getFollowers_name(login));
		us.setFollowing_name(getFollowing_name(login));

		return us;
	}
	
	@Override
	public List<String> searchUser(String input){
		String s = HttpRequest.sendGet("https://api.github.com/search/users?q=", input);
		
		return JsonUtil.<String>getListfromJsonArray(s, "items", "login");
	}
	

	private List<String> getFollowers_name(String login) {
		String s = HttpRequest.sendGet(github_user_url, login + "/followers");

		return JsonUtil.<String> getListfromJsonArray(s, "login");

	}

	private List<String> getFollowing_name(String login) {
		String s = HttpRequest.sendGet(github_user_url, login + "/following");

		return JsonUtil.<String> getListfromJsonArray(s, "login");

	}

	private List<String> getRepos_fullname(String login) {
		String s = HttpRequest.sendGet(github_user_url, login + "/repos");

		return JsonUtil.<String> getListfromJsonArray(s, "full_name");

	}
}
