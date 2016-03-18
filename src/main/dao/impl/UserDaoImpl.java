package main.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.entity.Company;
import main.dao.entity.Statistics;
import main.dao.entity.Type;
import main.dao.entity.User;

public class UserDaoImpl implements IUserDao {


	final String gitmining_user_url = "http://gitmining.net/api/user/";
	final String github_user_url = "https://api.github.com/users/";

	/* 所有用户列表 */
	private List<String> userList;
	/*所有用户位置列表*/
	private List<String> locationList;
	/*所有用户公司列表*/
	private List<String> companyList;
	/*所有用户类型列表*/
	private List<String> typeList;
	/* 所有用户与贡献项目列表 */
	private List<List<String>> mapUser2Contrbutions;
	/* 所有用户与合作项目列表 */
	private List<List<String>> mapUser2Collaborations;
	/* 所有用户与创建项目列表 */
	private List<List<String>> mapUser2Repos;
	/*用户创建时间*/
	private List<String> createdTimeList;

	public UserDaoImpl() {



		String path = "src/main/data/gitmining-api/";
		this.userList = DataInitHelper.getList(path + "user_login.txt");
		this.locationList = DataInitHelper.getList(path + "user-location.txt");
		this.companyList = DataInitHelper.getList(path + "user-company.txt");
	//	this.typeList = DataInitHelper.getList(path + "user-type.txt");
		this.mapUser2Collaborations = DataInitHelper.getListList(path + "user-collaborated.txt");
		this.mapUser2Contrbutions = DataInitHelper.getListList(path + "user-contributed.txt");
		this.mapUser2Repos = DataInitHelper.getListList(path + "user-repos.txt");

		System.out.println("UserDaoImpl initialized!");
	}

	@Override
	public User getUser(String login) throws IOException {
		User us =null;
		int index = userList.indexOf(login);

		if (index == -1) {
			return us;
		}

		String s = HttpRequest.sendGet(gitmining_user_url, login);
		us = JsonUtil.<User> parseJson2Bean(s, User.class);

		if (us != null) {
			us.setRepos_fullname(mapUser2Repos.get(index));
			// us.setFollowers_name(getFollowers_name(login));
			// us.setFollowing_name(getFollowing_name(login));
			us.setContributions_fullname(mapUser2Contrbutions.get(index));
			us.setCollaboration_fullname(mapUser2Collaborations.get(index));
		}

		return us;

	}

	@Override
	public Image getAvatar(String url) throws IOException {

		InputStream is = HttpRequest.sendGetforStream(url);

		return is == null ? null : new Image(is, 170, 170, true, false);
	}

	@Override
	public List<String> searchUser(String input) {
		
		List<String> result = SearchHelper.fuzzySearch(userList, input);

		return result;

	}


	@Override
	public String getLocation(String login){
		int index = userList.indexOf(login);
		if(index == -1)
			return null;
		return locationList.get(index);
	}

	@Override
	public List<String> getAllUser() {
		return userList;
	}

	@Override
	public String getCompany(String login) {
		int index = userList.indexOf(login);
		if(index == -1)
			return null;
		return companyList.get(index);
	}

	@Override
	public Type getType(String login) {
		
		Type type =Type.valueOf("User");
		return type;
	}

	@Override
	public int[] getTypeStatistic() {
		int[] result = new int[2];
		for(String type: typeList){
			if(type.equals(Type.User.name()))
				result[0]++;
			else
				result[1]++;
		}
		return result;
	}

	@Override
	public int[] getCreatedTimeStatistics() {
		String path = "src/main/data/gitmining-api/";
		this.createdTimeList = DataInitHelper.getList(path + "user-createdTime.txt");

		String[] years = Statistics.year;
		int[] result = new int[years.length];
		int year;

		try {
			for (String time : createdTimeList) {
				year = Integer.parseInt(time.split("-")[0]);
				result[year - Integer.parseInt(years[0])]++;
			}
		} catch (NumberFormatException e) {
			System.out.println("数字不符合格式");
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public int[] getCompanyStatistics() {
		Company[] list = Company.values();
		int[] result = new int[list.length];
		
		for(String company:companyList){
			for(Company cp:list){
				if(company.toLowerCase().contains(cp.name().toLowerCase()))
					result[cp.ordinal()]++;
			}
		}
			
		return result;
	}
	
	
}
