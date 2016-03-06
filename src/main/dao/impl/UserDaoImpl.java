package main.dao.impl;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import main.dao.DataInitHelper;
import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.entity.User;

public class UserDaoImpl implements IUserDao {

	final String gitmining_user_url = "http://gitmining.net/api/user/";
	final String github_user_url = "https://api.github.com/users/";

	private List<String> userList;
	private Map<String, List<String>> mapUser2Contrbutions;
	private Map<String, List<String>> mapUser2Collaborations;

	public UserDaoImpl() {

		this.userList = DataInitHelper
				.getList(new File("").getAbsolutePath() + "\\src\\main\\data\\gitmining-api\\user_login.txt");
		this.mapUser2Collaborations = DataInitHelper
				.getMap(new File("").getAbsolutePath() + "\\src\\main\\data\\gitmining-api\\collaborator-repos.txt");
		this.mapUser2Contrbutions = DataInitHelper
				.getMap(new File("").getAbsolutePath() + "\\src\\main\\data\\gitmining-api\\contributor-repos.txt");

	}

	@Override
	public User getUser(String login) throws IOException {
		String s = HttpRequest.sendGet(gitmining_user_url, login);
		User us = JsonUtil.<User> parseJson2Bean(s, User.class);

		us.setRepos_fullname(getRepos_fullname(login));
		us.setFollowers_name(getFollowers_name(login));
		us.setFollowing_name(getFollowing_name(login));
		us.setContributions_fullname(mapUser2Contrbutions.get(login));
		
		us.setAvatar(new Image(HttpRequest.sendGetforStream(us.getAvatar_url()), 50,50,true,false));

		return us;
	}

	@Override
	public List<String> searchUser(String input) throws IOException {
		/*
		 * String s =
		 * HttpRequest.sendGet("https://api.github.com/search/users?q=", input);
		 * return JsonUtil.<String>getListfromJsonArray(s, "items", "login");
		 */

		List<String> result = SearchHelper.fuzzySearch(userList, input);

		return result;

	}

	private List<String> getFollowers_name(String login) throws IOException {
		String s = HttpRequest.sendGet(github_user_url, login + "/followers");

		return JsonUtil.<String> getListfromJsonArray(s, "login");

	}

	private List<String> getFollowing_name(String login) throws IOException {
		String s = HttpRequest.sendGet(github_user_url, login + "/following");

		return JsonUtil.<String> getListfromJsonArray(s, "login");

	}

	private List<String> getRepos_fullname(String login) throws IOException {
		String s = HttpRequest.sendGet(github_user_url, login + "/repos");

		return JsonUtil.<String> getListfromJsonArray(s, "full_name");

	}
}
