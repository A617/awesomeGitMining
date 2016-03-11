package main.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.entity.User;
import main.data.DataMining;

public class UserDaoImpl implements IUserDao {

	final String gitmining_user_url = "http://gitmining.net/api/user/";
	final String github_user_url = "https://api.github.com/users/";

	/*所有用户列表 */
	private List<String> userList;
	/*所有用户与贡献项目列表 */
	private Map<String, List<String>> mapUser2Contrbutions;
	/*所有用户与合作项目列表 */
	private Map<String, List<String>> mapUser2Collaborations;
	/*所有用户与语言列表 */
	private Map<String, List<String>> mapUser2Repos;

	public UserDaoImpl() {
		String path = "src/main/data/gitmining-api/";
	//	String path = DataMining.class.getResource("gitmining-api/").getPath();
		this.userList = DataInitHelper
				.getList(path+"user_login.txt");
		this.mapUser2Collaborations = DataInitHelper
				.getMap(path+"collaborator-repos.txt");
		this.mapUser2Contrbutions = DataInitHelper
				.getMap(path+"contributor-repos.txt");
		this.mapUser2Repos = DataInitHelper
				.getMap(path+"user-repos.txt");
		
//		System.out.println("UserDaoImpl initialized!");
	}

	@Override
	public User getUser(String login) throws IOException {
		String s = HttpRequest.sendGet(gitmining_user_url, login);
		User us = JsonUtil.<User> parseJson2Bean(s, User.class);

		if (us != null) {
			us.setRepos_fullname(mapUser2Repos.get(login));
			// us.setFollowers_name(getFollowers_name(login));
			// us.setFollowing_name(getFollowing_name(login));
			us.setContributions_fullname(mapUser2Contrbutions.get(login));
			// us.setAvatar(new
			// Image(HttpRequest.sendGetforStream(us.getAvatar_url()), 50, 50,
			// true, false));
		}
		
		return us;
	}

	@Override
	public Image getAvatar(String url) throws IOException {
		
		InputStream is = HttpRequest.sendGetforStream(url);
		
		return is==null?null:new Image(is, 50, 50, true, false);
	}

	@Override
	public List<String> searchUser(String input) {
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
