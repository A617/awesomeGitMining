package org.Server.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
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
	private List<Integer> teamworkRank;

	private int len;

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
		this.len = userList.size();

		this.experienceRank = DataInitHelper.getIntList(path + "user_experienceRank.txt");
		this.livenessRank = DataInitHelper.getIntList(path + "user_livenessRank.txt");
		this.popularityRank = DataInitHelper.getIntList(path + "user_popularRank.txt");
		this.teamworkRank = DataInitHelper.getIntList(path + "user_teamworkRank.txt");
		this.quantityRank = DataInitHelper.getIntList(path + "user_quantityRank.txt");

		System.out.println("UserDaoImpl initialized!");
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

	@Override
	public User getUser(String login) {
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
		us.setCompany(companyList.get(index));
		us.setLocation(companyList.get(index));

		// popular,teamwork,liveness,experience,quantity
		double[] scores = new double[5];
		scores[0] = 1.0 - 1.0 * popularityRank.get(index) / len;
		scores[1] = 1.0 - 1.0 * teamworkRank.get(index) / len;
		scores[2] = 1.0 - 1.0 * livenessRank.get(index) / len;
		scores[3] = 1.0 - 1.0 * experienceRank.get(index) / len;
		scores[4] = 1.0 - 1.0 * quantityRank.get(index) / len;
		us.setScores(scores);

		return us;

	}

	@Override
	public String getAvatar(String login) {
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

	@Override
	public List<Integer> getUserFollowers() {
		return followersList;
	}

	@Override
	public List<Double> getUserRepoAvgStars() {
		List<Double> result = new ArrayList<>();
		
		
//		for(int rank: quantityRank){
//			result.add(5.0 - 5.0 * quantityRank.get(rank)/len);
//		}
		
		
		List<Integer> starsList = DataInitHelper.getIntList(path + "repo_stargazers.txt");
		List<String> repoList = DataInitHelper.getList(path + "repo_fullname.txt");
		int i = 0;
		for (List<String> repos : collaborationsList) {
			double sum = 0;
			int len = repos.size();
			for (String repo : repos) {
				int index = repoList.indexOf(repo);
				if (index != -1) {
					sum += starsList.get(index);
				} else {
					len--;
				}
			}
			if (len != 0&&len<20) {
				result.add(sum/len);
			} else
				result.add(0.0);
			i++;
		}
		return result;
	}

	// private void initRanks(IRepoDao repodao) {
	// // popularity
	// List<Integer> popularityScoreList = DataInitHelper.getIntList(path +
	// "user_followers.txt");
	// this.popularityRank = rankList(popularityScoreList);
	//
	// // liveness
	// this.livenessRank = DataInitHelper.getIntList(path +
	// "user_livenessRank.txt");
	//
	// // quantity&teamwork
	// List<Double> quantityList = new ArrayList<>();
	// List<Double> teamworkList = new ArrayList<>();
	// for (List<String> repos : collaborationsList) {
	// double qsum = 0;
	// double tsum = 0;
	// for (String repo : repos) {
	// try {
	// qsum += repodao.getHotScore(repo);
	// tsum += repodao.getCollaboratorNum(repo);
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// }
	// }
	// qsum = qsum / repos.size();
	// quantityList.add(qsum);
	// tsum = tsum / repos.size();
	// teamworkList.add(tsum);
	// }
	// quantityRank = rankListDouble(quantityList);
	// teamworkRank = rankListDouble(teamworkList);
	//
	// // experience: 5contributed + gist
	// List<Integer> experienceScoreList = new ArrayList<>();
	// List<Integer> gistList = DataInitHelper.getIntList(path +
	// "user_gists.txt");
	// List<List<String>> contrilistlist = DataInitHelper.getListList(path +
	// "user-contributed.txt");
	// for (int i = 0; i < gistList.size(); i++) {
	// experienceScoreList.add(gistList.get(i) + contrilistlist.get(i).size() *
	// 5);
	// }
	// this.experienceRank = rankList(experienceScoreList);
	// }

}
