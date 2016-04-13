package org.Server.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Common.po.Repository;
import org.Server.dao.DataFactory;
import org.Server.dao.DataInitHelper;
import org.Server.dao.HttpRequest;
import org.Server.dao.JsonUtil;

import net.sf.json.JSONException;

public class DataMining {
	private final static String path = "src/main/java/org/Server/data/gitmining-api/";
	private final static String repopath = "src/main/java/org/Server/data/gitmining-api/repo_fullname.txt";
	private final static String userpath = "src/main/java/org/Server/data/gitmining-api/user_login.txt";

	public static void main(String[] args) {

		long startTime = System.nanoTime();

	// caculateUserScores();
	//	getDataFromDtaMining("http://www.gitmining.net/api/repository/",path+"repo_size.txt", "size");

	getDataFromGithub("api.github.com/repos/", path + "repo_staredTime.txt", "starred_at");

		
	
		
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

	private static void caculateUserScores() {
		IUserDao userdao;
		IRepoDao repodao;
		try {
			userdao = DataFactory.getUserDataInstance();
			repodao = DataFactory.getRepoDataInstance();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		// contributions: 5*contributed + gist
		List<Integer> contributionsScoreList = new ArrayList<>();
		List<Integer> gistList = DataInitHelper.getIntList(path + "user_gists.txt");
		List<List<String>> contrilistlist = DataInitHelper.getListList(path + "user-contributed.txt");
		for (int i = 0; i < gistList.size(); i++) {
			contributionsScoreList.add(gistList.get(i) + contrilistlist.get(i).size() * 5);
		}

		// popularity
		List<Integer> popularityScoreList = DataInitHelper.getIntList(path + "user_followers.txt");

		// liveness
		List<String> updatedTimeList = DataInitHelper.getList(path + "user_updatedTime.txt");

		// quantity
		List<Integer> quantityScoreList = new ArrayList<>();
		List<Integer> starList = new ArrayList<>();
		List<Integer> watchList = new ArrayList<>();
		List<String> repoList = DataInitHelper.getList(path + "repo_fullname.txt");
		List<Integer> repostarlist = DataInitHelper.getIntList(path + "repo_stars.txt");
		List<List<String>> collalistlist = DataInitHelper.getListList(path + "user-collaborated.txt");

		for (List<String> collaList : collalistlist) {
			int stars = 0;
			for (String repo : collaList) {
				int index = repoList.indexOf(repo);
				stars += repostarlist.get(index);
			}
			starList.add(stars);

			int watches = 0;

		}

	}

	/**
	 * 
	 * @param url
	 *            下载路径
	 * @param path
	 *            保存路径
	 * @param key
	 *            要查找的关键字
	 */
	public static void getDataFromGithub(String url, String path, String key) {


		List<String> repos = readFromRepoTxt();
		System.out.println(repos.size());
		List<String> logins;

		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;
		boolean flag = false;

		try {

			fw = new FileWriter(file,true);
			writer = new BufferedWriter(fw);

			for (String repo : repos) {
				
				if(repo.equals("mde/timezone-js"))
					flag = true;
				
				if(!flag)
					continue;
				
				int i = 1;
				String page="";
				int sum = 0;
				loop:while (!page.equals("[]")) {
					try {
						page = HttpRequest.sendGetViaAcceptHeader(url + repo + "/stargazers", "?per_page=100&page=" + i);
					} catch (IOException e) {
						System.out.println(i);
						e.printStackTrace();
						break;
					}
					logins = JsonUtil.getListfromJsonArray(page, key);

					for (String login : logins) {
						if(login.compareTo("2016-03-15T00:00:00Z")>0)
							sum++;
					}
					i++;
				}
				writer.write(sum+"");
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 将每个仓库或用户的某项信息记录在本地文件 用api中的/item查询
	 * 
	 * @param url
	 * @param path
	 * @param key
	 *            要查询的属性
	 */
	public static void getDataFromDtaMining(String url, String path, String key) {

		List<String> repositories = readFromRepoTxt();
		System.out.println(repositories.size());

		String page = "";

		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			// boolean flag= false;
			int i = 0;
			for (String repoFull_name : repositories) {

				/*
				 * if(repoFull_name.equals("mklinik")) flag = true;
				 * 
				 * if(!flag) continue;
				 */

				try {
					page = HttpRequest.sendGet(url + repoFull_name + "/item/", key);
				} catch (IOException e) {
					System.out.println(i);
					e.printStackTrace();
					writer.newLine();
					writer.flush();
					continue;
				}

				try {
					writer.write(page);
				} catch (JSONException e) {
					writer.newLine();
					writer.flush();
					e.printStackTrace();
					continue;
				}

				writer.newLine();
				writer.flush();
				i++;
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * 用来读取项目列表
	 * 
	 * @return
	 */
	private static List<String> readFromRepoTxt() {
		List<String> repositories = new ArrayList<String>();

		try {
			File file = new File(repopath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					repositories.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return repositories;
	}

	/**
	 * 用来读取用户列表
	 * 
	 * @return
	 */
	private static List<String> readFromUserTxt() {
		List<String> repositories = new ArrayList<String>();

		try {
			File file = new File(userpath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					repositories.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return repositories;
	}

	private static List<Integer> readFromTxt(String path) {
		List<Integer> repositories = new ArrayList<>();

		try {
			File file = new File(path);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					repositories.add(Integer.parseInt(lineTxt));
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return repositories;
	}

	public static void test(String path) {
		File file = new File(path);

		List<String> list = null;
		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;
			String[] tmpMap;
			String[] users;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				list = new ArrayList<>();

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					tmpMap = line.split(": ");

					if (tmpMap.length < 2) {
						list.add("");
						continue;
					}

					list.add(tmpMap[1]); // 向mapR2U中加入一组 项目-用户
				}

				System.out.println(list.size());
			} catch (IOException e) {
				e.printStackTrace();

			} finally {

				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			System.out.println("找不到指定的文件" + path);
		}

		FileWriter fw = null;
		BufferedWriter writer = null;

		try {

			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (String full_name : list) {
				writer.write(full_name);
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<Integer> rankList(List<Integer> srcList, String path) {

		List<Integer> rankList = new ArrayList<>();

		List<Integer> sortList = new ArrayList<>(srcList);

		Collections.sort(sortList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		System.out.println(srcList);

		for (int element : srcList) {

			int rank = sortList.indexOf(element);

			rankList.add(rank);

		}

		writeToTxt(path, rankList);

		return rankList;
	}

	private static <T> void writeToTxt(String path, List<T> list) {
		// write to txt
		FileWriter fw = null;
		BufferedWriter writer = null;
		File file = new File(path);

		try {

			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (T i : list) {
				writer.write(i + "");
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void getUserLanguages(String savepath) {

		String path = "src/main/data/gitmining-api/";
		List<String> repoList = DataInitHelper.getList(path + "repo_fullname.txt");
		List<String> languageList = DataInitHelper.getList(path + "repo-language.txt");
		List<List<String>> collaborationsList = DataInitHelper.getListList(path + "user-collaborated.txt");
		List<List<String>> contrbutionsList = DataInitHelper.getListList(path + "user-contributed.txt");
		List<List<String>> reposList = DataInitHelper.getListList(path + "user-repos.txt");

		FileWriter fw = null;
		BufferedWriter writer = null;
		File file = new File(savepath);

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (int i = 0; i < contrbutionsList.size(); i++) {
				Set<String> set = new HashSet<>();

				for (String repo : collaborationsList.get(i)) {
					if (repoList.contains(repo))
						set.add(languageList.get(repoList.indexOf(repo)));
				}

				for (String repo : contrbutionsList.get(i)) {
					if (repoList.contains(repo))
						set.add(languageList.get(repoList.indexOf(repo)));
				}

				for (String repo : reposList.get(i)) {
					if (repoList.contains(repo))
						set.add(languageList.get(repoList.indexOf(repo)));
				}

				for (String language : set) {
					writer.write(language + " ");
				}
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
