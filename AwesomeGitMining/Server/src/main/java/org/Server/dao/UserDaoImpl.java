package org.Server.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Common.po.Statistics;
import org.Common.po.Type;
import org.Common.po.User;

public class UserDaoImpl extends UnicastRemoteObject implements IUserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path = "src/main/java/org/Server/data/gitmining-api/";
	/* 所有用户列表 */
	private List<String> userList;
	/* 用户昵称列表 */
	private List<String> nameList;
	/* 所有用户位置列表 */
	private List<String> locationList;
	/* 所有用户公司列表 */
	private List<String> companyList;
	/* 所有用户邮箱列表 */
	private List<String> emailList;
	/* 所有用户博客列表 */
	private List<String> blogList;
	/* 所有用户主页列表 */
	private List<String> htmlList;
	/* 所有用户类型列表 */
	private List<String> typeList;
	/* 所有用户与贡献项目列表 */
	private List<List<String>> contrbutionsList;
	/* 所有用户与合作项目列表 */
	private List<List<String>> collaborationsList;
	/* 所有用户与创建项目列表 */
	private List<List<String>> reposList;
	/* 用户创建时间 */
	private List<String> createdTimeList;
	/* 用户关注人数 */
	private List<Integer> followingList;
	/* 用户粉丝人数 */
	private List<Integer> followersList;
	/* 用户头像地址 */
	private List<String> avatar_urlList;
	/* 用户使用的语言列表 */
	private List<List<String>> languageList;

	private List<Integer> quantityRank;
	private List<Integer> popularityRank;
	private List<Integer> experienceRank;
	private List<Integer> livenessRank;
	private List<Integer> contributionRank;

	public UserDaoImpl(IRepoDao repodao) throws RemoteException {
		long startTime = System.nanoTime();

		this.userList = DataInitHelper.getList(path + "user_login.txt");
		this.locationList = DataInitHelper.getList(path + "user-location.txt");
		this.companyList = DataInitHelper.getList(path + "user-company.txt");
		this.collaborationsList = DataInitHelper.getListList(path + "user-collaborated.txt");
		this.contrbutionsList = DataInitHelper.getListList(path + "user-contributed.txt");
		this.reposList = DataInitHelper.getListList(path + "user-repos.txt");
		this.blogList = DataInitHelper.getList(path + "user_blog.txt");
		this.emailList = DataInitHelper.getList(path + "user_email.txt");
		this.htmlList = DataInitHelper.getList(path + "user_html_url.txt");
		this.followersList = DataInitHelper.getIntList(path + "user_followers.txt");
		this.followingList = DataInitHelper.getIntList(path + "user_following.txt");
		this.createdTimeList = DataInitHelper.getList(path + "user-createdTime.txt");
		this.nameList = DataInitHelper.getList(path + "user_name.txt");
		this.avatar_urlList = DataInitHelper.getList(path + "user_avatar_url.txt");
		this.languageList = DataInitHelper.getListList(path + "user_languages.txt");

		// experience: 5*contributed + gist
		List<Integer> experienceScoreList = new ArrayList<>();
		List<Integer> gistList = DataInitHelper.getIntList(path + "user_gists.txt");
		List<List<String>> contrilistlist = DataInitHelper.getListList(path + "user-contributed.txt");
		for (int i = 0; i < gistList.size(); i++) {
			experienceScoreList.add(gistList.get(i) + contrilistlist.get(i).size() * 5);
		}
		this.experienceRank = rankList(experienceScoreList);
		

		// popularity
		List<Integer> popularityScoreList = DataInitHelper.getIntList(path + "user_followers.txt");
		this.popularityRank = rankList(popularityScoreList);

		
		// liveness
		this.livenessRank = DataInitHelper.getIntList(path+"user_liveness.txt");
		
		
		// quantity
		
		
		//contribution
		

		System.out.println("UserDaoImpl initialized!");
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

	@Override
	public User getUser(String login) throws IOException {
		int index = userList.indexOf(login);

		if (index == -1) {
			return null;
		}

		User us = new User();
		us.setLogin(login);
		us.setName(nameList.get(index));
		us.setRepos_fullname(reposList.get(index));
		us.setContributions_fullname(contrbutionsList.get(index));
		us.setCollaboration_fullname(collaborationsList.get(index));
		us.setFollowers(followersList.get(index));
		us.setFollowing(followingList.get(index));
		us.setHtml_url(htmlList.get(index));
		us.setBlog(blogList.get(index));
		us.setEmail(emailList.get(index));
		us.setCreated_at(createdTimeList.get(index));
		us.setAvatar_url(avatar_urlList.get(index));

		return us;

	}

	@Override
	public String getAvatar(String login) throws IOException {
		int index = userList.indexOf(login);

		if (index != -1) {
			return avatar_urlList.get(index);
		}
		return null;
	}

	@Override
	public List<String> searchUser(String input) {

		List<String> result = SearchHelper.fuzzySearch(userList, input);

		return result;

	}

	@Override
	public String getLocation(String login) {
		int index = userList.indexOf(login);
		if (index == -1)
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
		if (index == -1)
			return null;
		return companyList.get(index);
	}

	@Override
	public int getFollowers(String login) {
		int index = userList.indexOf(login);
		if (index == -1)
			return 0;
		return followersList.get(index);
	}

	@Override
	public Type getType(String login) {

		Type type = Type.valueOf("User");
		return type;
	}

	@Override
	public int[] getTypeStatistic() {
		int[] result = new int[2];
		for (String type : typeList) {
			if (type.equals(Type.User.name()))
				result[0]++;
			else
				result[1]++;
		}
		return result;
	}

	@Override
	public int[] getCreatedTimeStatistics() {

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
		String[] list = Statistics.company;
		int[] result = new int[list.length];

		for (String company : companyList) {
			for (int i = 0; i < list.length; i++) {
				if (company.toLowerCase().contains(list[i].toLowerCase()))
					result[i]++;
			}
		}

		return result;
	}

	@Override
	public List<Integer> getRepoCreatedStatistics() {

		List<Integer> result = new ArrayList<Integer>();
		Iterator<List<String>> it = reposList.iterator();
		while (it.hasNext()) {
			result.add(it.next().size());
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public List<Integer> getRepoCollabortedStatistics() {
		List<Integer> result = new ArrayList<Integer>();
		Iterator<List<String>> it = collaborationsList.iterator();
		while (it.hasNext()) {
			result.add(it.next().size());
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public List<Integer> getRepoContributedStatistics() {
		List<Integer> result = new ArrayList<Integer>();
		Iterator<List<String>> it = contrbutionsList.iterator();
		while (it.hasNext()) {
			result.add(it.next().size());
		}
		return result;
	}

	@Override
	public List<String> getLanguageSkills(String login) {

		int index = userList.indexOf(login);

		if (index != -1) {

			return languageList.get(index);

		} else {

			return null;

		}
	}

	@Override
	public List<String> getUsersByLanguage(int i) {
		String[] languages = Statistics.language;

		List<String> result = new ArrayList<>();

		for (int index = 0; index < userList.size(); index++) {
			if (languageList.get(index).contains(languages[i]))
				result.add(userList.get(index));

		}

		return result;
	}

	@Override
	public List<String> getUsersByCompany(int i) {
		String[] list = Statistics.company;
		List<String> result = new ArrayList<>();

		for (int index = 0; index < companyList.size(); index++) {
			if (companyList.get(index).equals(list[i]))
				result.add(userList.get(index));

		}

		return result;
	}

	private List<Integer> rankList(List<Integer> srcList) {

		List<Integer> rankList = new ArrayList<>();

		List<Integer> sortList = new ArrayList<>(srcList);

		Collections.sort(sortList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int element : srcList) {

			int rank = sortList.indexOf(element);

			rankList.add(rank);

		}

		return rankList;
	}
	
	
	
	
	
}
